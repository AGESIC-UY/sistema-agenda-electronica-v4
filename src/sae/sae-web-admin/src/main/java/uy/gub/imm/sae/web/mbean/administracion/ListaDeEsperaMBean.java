package uy.gub.imm.sae.web.mbean.administracion;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.primefaces.component.column.Column;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.subtable.SubTable;
import org.primefaces.context.RequestContext;

import uy.gub.imm.sae.business.dto.ReservaDTO;
import uy.gub.imm.sae.business.ejb.facade.LlamadasLocal;
import uy.gub.imm.sae.business.ejb.facade.RecursosLocal;
import uy.gub.imm.sae.common.enumerados.Estado;
import uy.gub.imm.sae.entity.AgrupacionDato;
import uy.gub.imm.sae.entity.DatoASolicitar;
import uy.gub.imm.sae.entity.Recurso;
import uy.gub.imm.sae.entity.Reserva;
import uy.gub.imm.sae.entity.TextoRecurso;
import uy.gub.imm.sae.exception.UserException;
import uy.gub.imm.sae.web.common.FormularioDinReservaClient;
import uy.gub.imm.sae.web.common.reporte.Columna;

@ManagedBean
@ViewScoped
public class ListaDeEsperaMBean extends BaseMBean {

    public static final String MSG_ID = "pantalla";

    @EJB
    private RecursosLocal recursosEJB;

    @EJB
    private LlamadasLocal llamadasEJB;

    @ManagedProperty(value = "#{sessionMBean}")
    private SessionMBean sessionMBean;

    @ManagedProperty(value = "#{listaDeEsperaSessionMBean}")
    private ListaDeEsperaSessionMBean listaDeEsperaSessionMBean;

    private List<Columna> definicionColumnas;

    private Column columnaHoraListaDeEspera;
    private DataTable tablaReservas;
    private SubTable subTablaListaDeEspera;

    private List<SelectItem> itemsEstado;    

    public void beforePhaseListaDeEspera(PhaseEvent event) {
        if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            sessionMBean.setPantallaTitulo(sessionMBean.getTextos().get("lista_de_espera"));
        }
    }

    public ListaDeEsperaMBean() {
    }


	@PostConstruct
    public void init() {
        boolean huboError = false;
        if (sessionMBean.getAgendaMarcada() == null) {
            huboError = true;
            addErrorMessage(sessionMBean.getTextos().get("debe_haber_una_agenda_seleccionada"), MSG_ID);
        }
        if (sessionMBean.getRecursoMarcado() == null) {
            huboError = true;
            addErrorMessage(sessionMBean.getTextos().get("debe_haber_un_recurso_seleccionado"), MSG_ID);
        }
        if (huboError) {
            return;
        }
        try {
            List<AgrupacionDato> agrupaciones = recursosEJB.consultarDefinicionDeCampos(sessionMBean.getRecursoMarcado(), sessionMBean.getTimeZone());
            listaDeEsperaSessionMBean.setAgrupaciones(agrupaciones);
            refrescarHorariosDeEspera(null, listaDeEsperaSessionMBean.getAtencionPresencial());
            
            Map<Integer, String> puestos = new HashMap<Integer, String>();
            for (int i = 0; i < listaDeEsperaSessionMBean.getHorarios().size(); i++) {
                Horario horario = listaDeEsperaSessionMBean.getHorarios().get(i);
                for (int j = 0; j < horario.getListaEspera().size(); j++) {
                    puestos.put(horario.getListaEspera().get(j).getReserva().getId(), "");
                }
            }

            this.setPuestos(puestos);
    
        } catch (UserException ex) {
            addErrorMessage(ex, MSG_ID);
        }
    }

    public ListaDeEsperaSessionMBean getListaDeEsperaSessionMBean() {
        return listaDeEsperaSessionMBean;
    }

    public void setListaDeEsperaSessionMBean(
            ListaDeEsperaSessionMBean listaDeEsperaSessionMBean) {
        this.listaDeEsperaSessionMBean = listaDeEsperaSessionMBean;
    }

    public SessionMBean getSessionMBean() {
        return sessionMBean;
    }

    public void setSessionMBean(SessionMBean sessionMBean) {
        this.sessionMBean = sessionMBean;
    }

    public List<Columna> getDefinicionColumnas() {
        if (definicionColumnas == null) {
            definicionColumnas = new ArrayList<>();
            if (sessionMBean.getRecursoMarcado() != null) {
                try {
                    //Definicion de los campos dinamicos del reporte
                    List<AgrupacionDato> agrupaciones = listaDeEsperaSessionMBean.getAgrupaciones();
                    for (AgrupacionDato grupo : agrupaciones) {
                        for (DatoASolicitar campo : grupo.getDatosASolicitar()) {
                            if (campo.getIncluirEnReporte()) {
                                Columna col = new Columna();
                                col.setId(campo.getNombre());
                                col.setNombre(campo.getEtiqueta());
                                col.setClase(String.class);
                                col.setAncho(campo.getAnchoDespliegue());
                                definicionColumnas.add(col);
                            }
                        }
                    }

                } catch (Exception e) {
                    addErrorMessage(e, MSG_ID);
                }
            }
        }
        return definicionColumnas;
    }

    public Column getColumnaHoraListaDeEspera() {
        return columnaHoraListaDeEspera;
    }

    public void setColumnaHoraListaDeEspera(Column columnaHoraListaDeEspera) {
        this.columnaHoraListaDeEspera = columnaHoraListaDeEspera;
        //Seteo el colspan de la columna de horas, para adecuarlo a la cantidad dinamica de campos a desplegar
        columnaHoraListaDeEspera.setColspan(getDefinicionColumnas().size() + 2);
    }

    public DataTable getTablaReservas() {
        return tablaReservas;
    }

    public void setTablaReservas(DataTable tablaReservas) {
        this.tablaReservas = tablaReservas;
    }

    public SubTable getSubTablaListaDeEspera() {
        return subTablaListaDeEspera;
    }

    public void setSubTablaListaDeEspera(SubTable subTablaListaDeEspera) {
        this.subTablaListaDeEspera = subTablaListaDeEspera;
    }

    @SuppressWarnings("unchecked")
    public void cambiaSeleccionEstados(ValueChangeEvent event) {
        List<Estado> estados = (List<Estado>) event.getNewValue();
        refrescarHorariosDeEspera(estados, listaDeEsperaSessionMBean.getAtencionPresencial());
    }

    /**
     * Marca la reserva indicando que el el usuario estuvo presente en la cita
     */
    //public void asistio(ActionEvent event) {
    public void asistio(String observaciones) {
        try {
            Reserva reserva = listaDeEsperaSessionMBean.getSiguienteReserva();
            reserva.setObservaciones(observaciones.trim());
            llamadasEJB.marcarAsistencia(sessionMBean.getEmpresaActual(), sessionMBean.getRecursoMarcado(), reserva);
            cierroDatosSiguiente();
        } catch (Exception ex) {
            addErrorMessage(ex, MSG_ID);
            ex.printStackTrace();
        }
    }

    /**
     * Marca la reserva indicando que el el usuario estuvo ausente en la cita
     */
    public void falto(String observaciones) {
        try {
            Reserva reserva = listaDeEsperaSessionMBean.getSiguienteReserva();
            reserva.setObservaciones(observaciones.trim());
            llamadasEJB.marcarInasistencia(sessionMBean.getEmpresaActual(), sessionMBean.getRecursoMarcado(), reserva);
            cierroDatosSiguiente();
        } catch (Exception ex) {
            addErrorMessage(ex, MSG_ID);
        }
    }

    private void cierroDatosSiguiente() {
        refrescarHorariosDeEspera(null, listaDeEsperaSessionMBean.getAtencionPresencial());
        listaDeEsperaSessionMBean.setMostrarDatosSiguiente(false);
    }

    private void refrescarHorariosDeEspera(List<Estado> estados, boolean atencionPresencial) {
        listaDeEsperaSessionMBean.setHorarios(new ArrayList<>());
        if (sessionMBean.getRecursoMarcado() != null) {
            try {
                if (getListaDeEsperaSessionMBean().getEstadosSeleccionado() == null) {
                    getListaDeEsperaSessionMBean().setEstadosSeleccionado(new ArrayList<>());
                    getListaDeEsperaSessionMBean().getEstadosSeleccionado().add(Estado.R);
                }
                if (estados == null) {
                    estados = getListaDeEsperaSessionMBean().getEstadosSeleccionado();
                }
                Recurso recursoMarcado = sessionMBean.getRecursoMarcado();
                List<ReservaDTO> reservas = llamadasEJB.obtenerReservasEnEspera(recursoMarcado, estados, atencionPresencial, sessionMBean.getTimeZone());
                Horario horario = null;
                //Recorro las reservas agrupándolas por horario
                for (ReservaDTO reserva : reservas) {
                    //Si no es presencial, si el horario es nulo o la reserva no tiene el mismo horario 
                    //se crea un nuevo grupo con esta reserva, sino se agrega al grupo actual
                    if (horario == null || (!atencionPresencial && !reserva.getHoraInicio().equals(horario.getHora()))) {
                        horario = new Horario();
                        horario.setHora(reserva.getHoraInicio());
                        horario.getListaEspera().add(crearEspera(reserva));
                        listaDeEsperaSessionMBean.getHorarios().add(horario);
                    } else {
                        horario.getListaEspera().add(crearEspera(reserva));
                    }
                }
            } catch (UserException uEx) {
                addErrorMessage(uEx, MSG_ID);
            }
        }
    }

    public UIComponent getCamposSiguienteReserva() {
        return listaDeEsperaSessionMBean.getCamposSiguienteReserva();
    }

    public void setCamposSiguienteReserva(UIComponent camposSiguienteReserva) {
        listaDeEsperaSessionMBean.setCamposSiguienteReserva(camposSiguienteReserva);
    }

    public Boolean getMostrarDatosSiguiente() {
        return listaDeEsperaSessionMBean.getMostrarDatosSiguiente();
    }    


    //Llama a la capa de negocio consumiendo la siguiente reserva en la lista de espera y la despliega al usuario.
    public void siguiente(ActionEvent event) {
    	limpiarMensajesError();
    	Boolean flagLlamo = false;    	
    	for(Horario horario: this.getListaDeEsperaSessionMBean().getHorarios()) {
    		for(Espera espera: horario.getListaEspera()) {
    			if (Boolean.TRUE.equals(espera.getLlamarProximo())) {
    				this.getListaDeEsperaSessionMBean().getPuestos().replace(espera.getReserva().getId(), this.getListaDeEsperaSessionMBean().getPuesto());    				
    				this.llamarSiguiente(espera.getReserva().getId());
    				espera.setLlamarProximo(false);
    				flagLlamo = true;
    				break;
    			}
    		}
    	}
    	if (Boolean.FALSE.equals(flagLlamo)) {this.llamarSiguiente(0);}
    }

    public void llamarDirectamente(Integer reservaId) {
        this.llamarSiguiente(reservaId);
    }

    private void llamarSiguiente(Integer reservaId) {
        Integer iPuesto;
        try {
            iPuesto = (reservaId == 0) ? Integer.valueOf(listaDeEsperaSessionMBean.getPuesto()) : Integer.valueOf(listaDeEsperaSessionMBean.getPuestos().get(reservaId));
        } catch (Exception ex) {
            if (reservaId == 0) {
                addErrorMessage(sessionMBean.getTextos().get("el_numero_de_puesto_no_es_valido"));
            } else {
                addErrorMessage(sessionMBean.getTextos().get("ingrese_el_numero_de_puesto_en_el_campo_correcto"));
            }
            return;
        }
        if (sessionMBean.getRecursoMarcado() != null) {
            Reserva siguienteReserva = null;
            try {
                //Obtener la siguiente reserva (no se hace con los datos en sesión porque es posible que otros usuarios hayan hecho más llamadas) 
                siguienteReserva = llamadasEJB.llamarASiguientePorReservaId(sessionMBean.getRecursoMarcado(), iPuesto, listaDeEsperaSessionMBean.getAtencionPresencial(), reservaId);
                listaDeEsperaSessionMBean.setSiguienteReserva(siguienteReserva);
            } catch (Exception e) {
                addErrorMessage(e, MSG_ID);
            }
            listaDeEsperaSessionMBean.getCamposSiguienteReserva().getChildren().clear();
            if (siguienteReserva != null) {
                try {
                    List<AgrupacionDato> agrupaciones = listaDeEsperaSessionMBean.getAgrupaciones();
                    FormularioDinReservaClient.armarFormularioLecturaDinamico(sessionMBean.getRecursoMarcado(), siguienteReserva,
                            listaDeEsperaSessionMBean.getCamposSiguienteReserva(), agrupaciones, sessionMBean.getFormatoFecha(), new Locale(sessionMBean.getIdiomaActual()));
                } catch (Exception e) {
                    addErrorMessage(e, MSG_ID);
                }
                listaDeEsperaSessionMBean.setMostrarDatosSiguiente(true); //Para que al rerenderizar se muestre el formulario con los datos de la siguiente reserva
            }
        }
    }

    //Llama a la capa de negocio re-consumiendo la reserva indicada y la despliega al usuario.
    public void llamar(ActionEvent event) {
        Integer iPuesto;
        try {
            iPuesto = Integer.valueOf(listaDeEsperaSessionMBean.getPuesto());
        } catch (Exception ex) {
            addErrorMessage(sessionMBean.getTextos().get("el_numero_de_puesto_no_es_valido"));
            return;
        }
        Reserva siguienteReserva = null;
        try {
            Espera espera = (Espera) getSubTablaListaDeEspera().getRowData();
            if (espera != null) {
                Reserva r = new Reserva();
                r.setId(espera.getReserva().getId());
                siguienteReserva = llamadasEJB.volverALlamar(sessionMBean.getRecursoMarcado(), iPuesto, r);
                listaDeEsperaSessionMBean.setSiguienteReserva(siguienteReserva);
            }
        } catch (Exception e) {
            addErrorMessage(e, MSG_ID);
        }

        listaDeEsperaSessionMBean.getCamposSiguienteReserva().getChildren().clear();

        if (siguienteReserva != null) {
            try {
                List<AgrupacionDato> agrupaciones = listaDeEsperaSessionMBean.getAgrupaciones();
                FormularioDinReservaClient.armarFormularioLecturaDinamico(sessionMBean.getRecursoMarcado(), siguienteReserva,
                        listaDeEsperaSessionMBean.getCamposSiguienteReserva(), agrupaciones, sessionMBean.getFormatoFecha(), new Locale(sessionMBean.getIdiomaActual()));
            } catch (Exception e) {
                addErrorMessage(e, MSG_ID);
            }
            listaDeEsperaSessionMBean.setMostrarDatosSiguiente(true); //Para que al rerenderizar se muestre el formulario con los datos de la siguiente reserva
        }
    }

    public List<SelectItem> getItemsEstado() {

        if (itemsEstado == null) {
            List<Estado> valorItem1 = new ArrayList<>();
            valorItem1.add(Estado.R);
            List<Estado> valorItem2 = new ArrayList<>();
            valorItem2.add(Estado.U);
            itemsEstado = new ArrayList<>();
            itemsEstado.add(new SelectItem(valorItem1, "Pendientes"));
            itemsEstado.add(new SelectItem(valorItem2, "Llamadas"));
        }

        return itemsEstado;
    }

    public String getNombreColumnaPuesto() {
        Recurso recurso = sessionMBean.getRecursoMarcado();
        if (recurso != null) {
            TextoRecurso textoRecurso = getTextoRecurso(recurso, sessionMBean.getIdiomaActual());
            if (textoRecurso != null && textoRecurso.getTituloPuestoEnLlamador() != null) {
                return textoRecurso.getTituloPuestoEnLlamador();
            }
        }
        return "";
    }

    private Espera crearEspera(ReservaDTO reserva) {
        Espera espera = new Espera();
        espera.setReserva(reserva);
        List<Columna> cols = getDefinicionColumnas();
        for (Columna columna : cols) {
            Object dato = reserva.getDatos().get(columna.getId());
            //Si el dato es nulo, por ejemplo campo opcional agrego vacio
            espera.getDatos().add((dato == null ? "" : dato.toString()));
        }

        return espera;
    }

    public class Horario {

        private Date hora;
        private List<Espera> listaEspera = new ArrayList<>();

        public Date getHora() {

            return hora;
        }

        public void setHora(Date hora) {
            this.hora = hora;
        }

        public List<Espera> getListaEspera() {
            return listaEspera;
        }

        public void setListaEspera(List<Espera> listaEspera) {
            this.listaEspera = listaEspera;
        }
    }

    public class Espera {

        private ReservaDTO reserva;
        private List<String> datos = new ArrayList<>();
        private Boolean llamarProximo;

        public ReservaDTO getReserva() {
            return reserva;
        }

        public void setReserva(ReservaDTO reserva) {
            this.reserva = reserva;
        }

        public List<String> getDatos() {
            return datos;
        }

        public void setDatos(List<String> datos) {
            this.datos = datos;
        }

		public Boolean getLlamarProximo() {
			return llamarProximo;
		}

		public void setLlamarProximo(Boolean llamarProximo) {
			this.llamarProximo = llamarProximo;
		}		
    }

    public void cambioAtencionPresencial() {
        refrescarHorariosDeEspera(null, listaDeEsperaSessionMBean.getAtencionPresencial());
    }

    public Boolean getAtencionPresencial() {
        return listaDeEsperaSessionMBean.getAtencionPresencial();
    }

    public void setAtencionPresencial(Boolean atencionPresencial) {
        listaDeEsperaSessionMBean.setAtencionPresencial(atencionPresencial);
    }

    public String getPuesto() {
        return listaDeEsperaSessionMBean.getPuesto();
    }

    public void setPuesto(String puesto) {
        listaDeEsperaSessionMBean.setPuesto(puesto);
    }

    public Map<Integer, String> getPuestos() {
        return listaDeEsperaSessionMBean.getPuestos();
    }

    public void setPuestos(Map<Integer, String> puestos) {
        listaDeEsperaSessionMBean.setPuestos(puestos);
    }
}
