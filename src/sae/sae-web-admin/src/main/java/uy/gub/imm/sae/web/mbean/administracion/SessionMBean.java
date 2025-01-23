/*
 * SAE - Sistema de Agenda Electrónica
 * Copyright (C) 2009  IMM - Intendencia Municipal de Montevideo
 *
 * This file is part of SAE.

 * SAE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package uy.gub.imm.sae.web.mbean.administracion;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.security.SecurityContext;
import org.jboss.security.SecurityContextAssociation;
import org.jboss.security.SecurityContextFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import uy.gub.imm.sae.business.ejb.facade.AgendaGeneralLocal;
import uy.gub.imm.sae.business.ejb.facade.AgendarReservasLocal;
import uy.gub.imm.sae.business.ejb.facade.ConfiguracionLocal;
import uy.gub.imm.sae.business.ejb.facade.RecursosLocal;
import uy.gub.imm.sae.business.ejb.facade.UsuariosEmpresasLocal;
import uy.gub.imm.sae.common.SofisHashMap;
import uy.gub.imm.sae.common.VentanaDeTiempo;
import uy.gub.imm.sae.entity.Agenda;
import uy.gub.imm.sae.entity.DatoASolicitar;
import uy.gub.imm.sae.entity.DatoDelRecurso;
import uy.gub.imm.sae.entity.Disponibilidad;
import uy.gub.imm.sae.entity.Recurso;
import uy.gub.imm.sae.entity.Reserva;
import uy.gub.imm.sae.entity.RolesUsuarioRecurso;
import uy.gub.imm.sae.entity.constantes.Constantes;
import uy.gub.imm.sae.entity.global.Empresa;
import uy.gub.imm.sae.entity.global.Organismo;
import uy.gub.imm.sae.entity.global.Usuario;
import uy.gub.imm.sae.exception.ApplicationException;
import uy.gub.imm.sae.web.common.AgendaComparatorNombre;
import uy.gub.imm.sae.web.common.RecursoComparatorNombre;
import uy.gub.imm.sae.web.common.Row;
import uy.gub.imm.sae.web.common.RowList;
import uy.gub.imm.sae.web.common.SelectItemComparator;

@ManagedBean
@SessionScoped
public class SessionMBean extends SessionCleanerMBean {

    public static final String MSG_ID = "pantalla";

    private static final Logger LOGGER = Logger.getLogger(SessionMBean.class.getName());

    private static enum TipoLogin {
        LOCAL, CDA, CAS, IDURUGUAY
    };

    @EJB
    private AgendaGeneralLocal generalEJB;

    @EJB
    private RecursosLocal recursosEJB;

    @EJB
    private UsuariosEmpresasLocal usuariosEmpresasEJB;

    @EJB
    private AgendarReservasLocal agendarReservasEJB;

    @EJB
    private ConfiguracionLocal configuracionEJB;

    private TipoLogin tipoLogin;
    
    private Integer organismoIdSelec;
    private Usuario usuarioActual;
    private Empresa empresaActual;
    private Organismo organismoActual;
    private List<SelectItem> empresasUsuario;
    private List<SelectItem> organismosUsuario;
    private byte[] empresaActualLogoBytes;
    private List<String> rolesPorEmpresa;
    private List<Organismo> organismos = new ArrayList();
    private Map<Integer, List<String>> rolesPorRecurso;

    private boolean mostrarFechaActual = false;

    private String idiomaActual = Locale.getDefault().getLanguage();

    // Pagina que se debe desplegar en la sección "pantalla" de la pagina principal
    private String viewId;
    private String pantallaTitulo;

    private RowList<Agenda> agendas;
    private Row<Agenda> rowSelectAgenda;

    private RowList<Recurso> recursos;
    private RowList<Recurso> recursosAux;
    private Row<Recurso> rowSelectRecurso;
    
    private RowList<DatoDelRecurso> datosDelRecurso;

    // Agenda/Recurso seleccionados para modificacion es necesario pues al navegar de la pagina 
    // modificarConsultar a modificar se pierde la row seleccionada en la tabla de agendas/recursos :(
    private Agenda agendaSeleccionada;
    private Recurso recursoSeleccionado;
    private DatoDelRecurso datoDelRecursoSeleccionado;

    // Booleana para saber si se despliega la tabla de Dato del Recurso
    private Boolean mostrarDato = true;
    // Booleana para saber si se despliega la tabla para agregar Dato del Recurso
    private Boolean mostrarAgregarDato = false;

    // Booleana para saber si se utiliza llamador o no
    private Boolean mostrarLlamador = true;
    
    // Booleana para saber si se muestra el dialog de selección de organismo o no
    private Boolean mostrarDialogOrganismo = true;
    
    // Booleana para saber si se muestra el botón Ok en el dialog de seleccipon de organismo
    private Boolean mostrarBtnOk = Boolean.TRUE;
    
    // Booleana para saber si el usuario es administrador del Organismo Actual
    private Boolean adminOrganismoActual = Boolean.FALSE;

    // Variables para redirigir a sitio de Reservas
    private static final String URL_BASE_TO_FORWARD_RESERVA = "/agendarReserva/Paso1.xhtml?agenda=";

    private DatosUrlToForwardReserva datosUrlToForwardReserva = new DatosUrlToForwardReserva();

    private int pagina;

    private Map<String, DatoASolicitar> datosASolicitar;

    private String codigoSeguridadReserva;

    private List<SelectItem> idiomasSoportados = null;

    //Cantidad de filas por página que se muestra en las tablas
    private final Integer tablasFilasPorPagina = 25;
    
    //private String ssoLogoutUrl = null;
    private String ssoBaseUrl = null;
    
    private Boolean envioCorreoReserva = Boolean.TRUE;



    @PostConstruct
    public void postConstruct() {
        //Se cargan los textos antes que los datos del usuario porque hay cosas que dependen de esto
        cargarTextos();
        //Se cargan los datos del usuario
        cargarDatosUsuario();
        //Se vuelven a cargar los textos despues de los datos del usuario para incluir los traducidos en el idioma actual
        cargarTextos();
        
    }

    public TimeZone getTimeZone() {
        //Primero se devuelve la de la Agenda, si tiene
        Agenda agendaTz = getAgendaMarcada();
        if (agendaTz != null && agendaTz.getTimezone() != null && !agendaTz.getTimezone().trim().isEmpty()) {
            return TimeZone.getTimeZone(agendaTz.getTimezone());
        }
        //Si no tiene la Agenda, se devuelve la de la Empresa
        if (empresaActual != null && empresaActual.getTimezone() != null) {
            return TimeZone.getTimeZone(empresaActual.getTimezone());
        }
        //En otro caso se devuelve uno por defecto
        return TimeZone.getDefault();
    }

    public String getFormatoFecha() {
        if (empresaActual != null && empresaActual.getFormatoFecha() != null) {
            return empresaActual.getFormatoFecha();
        }
        return "dd/MM/yyyy";
    }

    public String getLocale() {
        return idiomaActual;
    }

    /*
	 * En el DatePicker el formato usa lo siguiente:
	 *   dd: día en dos dígitos
	 *   mm: mes en dos dígitos
	 *   yy: año en cuatro dígitos
     */
    public String getFormatoFechaDatepicker() {
        String formatoJava = "dd/MM/yyyy";
        if (empresaActual != null && empresaActual.getFormatoFecha() != null) {
            formatoJava = empresaActual.getFormatoFecha();
        }
        return formatoJava.replace("yyyy", "yy").replace("MM", "mm");
    }

    public String getFormatoHora() {
        if (empresaActual != null && empresaActual.getFormatoHora() != null) {
            return empresaActual.getFormatoHora();
        }
        return "HH:mm";
    }

    public boolean isMostrarFechaActual() {
        return this.mostrarFechaActual;
    }

    public String getFechaActual() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getFormatoFecha() + " " + getFormatoHora());
        return formatter.format(getCurrentLocalDateTime(getTimeZone().toZoneId()));
    }

    LocalDateTime getCurrentLocalDateTime(ZoneId zoneId) {
        return LocalDateTime.now(zoneId);
    }

    public Integer getTablasFilasPorPagina() {
        return tablasFilasPorPagina;
    }

    // *****************************************************************************************************
    // ***************************Pasos para la reserva
    // *****************************************************************************************************
    private RowList<Disponibilidad> disponibilidadesDelDiaMatutina;
    private RowList<Disponibilidad> disponibilidadesDelDiaVespertina;
    private Disponibilidad disponibilidad;
    private Reserva reserva;
    //Estos dos datos solo se deben usar para la reserva, en el resto de los casos se debe usar getAgendaMarcada y getRecursoMarcado
    private Agenda agenda;
    private Recurso recurso;
    private Date diaSeleccionado;
    private VentanaDeTiempo ventanaCalendario;
    private VentanaDeTiempo ventanaMesSeleccionado;
    private List<Integer> cuposXdiaMesSeleccionado;
    private Date currentDate;
    private Reserva reservaConfirmada;

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public String getViewId() {
        if (viewId == null) {
            viewId = "/administracion/inicio.xhtml";
        }
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    public String getPantallaTitulo() {
        return pantallaTitulo;
    }

    public void setPantallaTitulo(String pantallaTitulo) {
        this.pantallaTitulo = pantallaTitulo;
    }

    public void beforePhaseInicio(PhaseEvent event) {
        if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            setPantallaTitulo(getTextos().get("inicio"));
        }
    }

    public void beforePhaseSeleccionAgendaRecurso(PhaseEvent event) {
        if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            setPantallaTitulo(getTextos().get("seleccionar_agenda_recurso"));
        }
    }

    public RowList<Agenda> getAgendas() {
        return agendas;
    }

    // Agenda seleccionada en pantalla de selección de agendas y recursos
    public Agenda getAgendaMarcada() {
        if (agendas != null && agendas.getSelectedRow() != null) {
            return agendas.getSelectedRow().getData();
        } else {
            return null;
        }
    }

    // glabandera seteo agernda marcada
    public void setAgendaMarcada(Row<Agenda> SelectAgenda) {
        if (agendas != null) {
            agendas.setSelectedRow(SelectAgenda);
        }

    }

    public void desmarcarAgenda() {
        agendas.setSelectedRow(null);
    }

    public RowList<Recurso> getRecursos() {
        return recursos;
    }

    public RowList<Recurso> getRecursosAux() {
        if (this.tieneRoles(new String[]{"RA_AE_ADMINISTRADOR_DE_RECURSOS"}) ) {
            return recursosAux;
        }
        return recursos;
    }

    // Recurso seleccionado en pantalla de selección de agendas y recursos
    public Recurso getRecursoMarcado() {
        if (recursos != null && recursos.getSelectedRow() != null) {
            return recursos.getSelectedRow().getData();
        } else {
            return null;
        }
    }

    public void desmarcarRecurso() {
        recursos.setSelectedRow(null);
    }

    public RowList<DatoDelRecurso> getDatosDelRecurso() {
        cargarDatosDelRecurso();
        return datosDelRecurso;
    }

    public void setDatosDelRecurso(RowList<DatoDelRecurso> datosDelRecurso) {
        this.datosDelRecurso = datosDelRecurso;
    }

    public Agenda getAgendaSeleccionada() {
        return agendaSeleccionada;
    }

    public void setAgendaSeleccionada(Agenda agenda) {
        this.agendaSeleccionada = agenda;
    }

    public Recurso getRecursoSeleccionado() {
        return recursoSeleccionado;
    }

    public void setRecursoSeleccionado(Recurso recurso) {
        this.recursoSeleccionado = recurso;
        if (recurso != null) {
            /*
		 Se hace este chequeo porque cuando se accede con un usuario 'Administrador de recursos', luego de hacer alguna
		modificación en alguno de sus recursos, la aplicación recargaba la lista de recursos y se perdía la selección
		del recurso que le daba acceso a las opciones del menú
             */
            if (!this.tieneRoles(new String[]{"RA_AE_ADMINISTRADOR_DE_RECURSOS"})) {
                this.desmarcarRecurso();
            }
        }
    }

    public DatoDelRecurso getDatoDelRecursoSeleccionado() {
        return datoDelRecursoSeleccionado;
    }

    public void setDatoDelRecursoSeleccionado(
            DatoDelRecurso datoDelRecursoSeleccionado) {
        this.datoDelRecursoSeleccionado = datoDelRecursoSeleccionado;
    }

    public Boolean getMostrarAgregarDato() {
        return mostrarAgregarDato;
    }

    public void setMostrarAgregarDato(Boolean mostrarAgregarDato) {
        this.mostrarAgregarDato = mostrarAgregarDato;
    }

    public void seleccionarAgenda(SelectEvent event) {
        removeMBeansFromSession();
        if (this.rowSelectAgenda == null || this.rowSelectAgenda.getData().getId() == 0) {
            setAgendaMarcada(null);
        } else {
            setAgendaMarcada(this.rowSelectAgenda);
        }
        cargarRecursos();
    }

    public void desseleccionarAgenda() {
        removeMBeansFromSession();
        setAgendaMarcada(null);
        cargarRecursos();
    }

    public void desseleccionarRecurso(SelectEvent a) {
        desseleccionarRecurso();
    }

    public void desseleccionarRecurso() {
        removeMBeansFromSession();
        if (this.rowSelectRecurso == null || this.rowSelectRecurso.getData().getId() == 0) {
            this.recursos.setSelectedRow(null);
        } else {
            this.recursos.setSelectedRow(rowSelectRecurso);
        }
        this.setRecursoSeleccionado(null);
    }

    
    
    public void cargarAgendas() {
        try {
            List<Agenda> entidades;            
            if(usuarioActual==null) {
                entidades = new ArrayList();
            }
            else if (empresaActual==null){
                entidades = new ArrayList();
            }
            else {
                List<Agenda> agendas1 = generalEJB.consultarAgendas();
                //si es administrador del organismo o administrador de la empresa cargar todas las agendas
                if(adminOrganismoActual || !rolesPorEmpresa.isEmpty()) {
                    //Tiene acceso a todas las agendas
                    entidades = agendas1;
                }else {
                    //Solo tiene acceso a las agendas que tienen recursos para los cuales tiene un rol
                    entidades = new ArrayList();
                    for(Agenda agenda1 : agendas1) {
                        List<Recurso> recursos1 = generalEJB.consultarRecursos(agenda1);
                        for(Recurso recurso1 : recursos1) {
                            if(rolesPorRecurso.containsKey(recurso1.getId())) {
                                if(!entidades.contains(agenda1)) {
                                    entidades.add(agenda1);
                                }
                            }
                        }                        
                    }
                }
            }
            //Ordenar las agendas
            Collections.sort(entidades, new AgendaComparatorNombre());
            //Añadir un item al inicio para la "no-selección"
            Agenda ninguna = new Agenda();
            ninguna.setId(0);
            ninguna.setNombre(getTextos().get("ninguna"));
            ninguna.setDescripcion(getTextos().get("ninguna"));
            entidades.add(0, ninguna);
            agendas = new RowList<>(entidades);
            if (recursos != null) {
                recursos.clear();
            }
        } catch (Exception e) {
            addErrorMessage(e, MSG_ID);
        }
    }
    
    
    private void cargarRoles() {
        try {
            if(empresaActual!=null){
                rolesPorEmpresa = usuariosEmpresasEJB.obtenerRolesUsuarioEmpresa(usuarioActual.getId(), empresaActual.getId());
                rolesPorRecurso = new HashMap<>();
                List<RolesUsuarioRecurso> rolesRecursoUsuario = recursosEJB.getRolesUsuarioRecurso(usuarioActual.getId());
                for (RolesUsuarioRecurso rolRecursoUsuario : rolesRecursoUsuario) {
                    String[] roles = rolRecursoUsuario.getRoles().split(",");
                    for (int i = 0; i < roles.length; i++) {
                        roles[i] = roles[i].trim();
                    }
                    rolesPorRecurso.put(rolRecursoUsuario.getId().getRecursoId(), Arrays.asList(roles));
                }
            }
            
        } catch (ApplicationException ex) {
            LOGGER.log(Level.SEVERE, "No se pudo cargar los roles del usuario en la empresa", ex);
        }
    }

    // Si hay agenda selecciondada, se cargan los recursos asociados.
    // En caso contrario se vacía la lista de recursos
    public void cargarRecursos() {
        if (getAgendaMarcada() != null) {
            try {
                List<Recurso> entidades = new ArrayList();
                List<Recurso> entidades1 = new ArrayList();
                List<Recurso> recursos1 = generalEJB.consultarRecursos(getAgendaMarcada());
                //si es administrador del organismo o administrador de la empresa cargar todas las agendas
                if(adminOrganismoActual || !rolesPorEmpresa.isEmpty()) {
                    //Tiene acceso a todas las agendas
                    entidades = recursos1;
                    //el || adminOrganismoActual es solo para que no se modifique entidades por el else, ya en el el getRecursosAux
                    // le devuelve el entidades resultado del else, que si el usuario no es admin de recursos
                    // no los ve, pero por ser admin de orgenismos no deberia de importar, debe ver todos los
                    // recursos, TODO arreglar esto y el getRecursosAux, no es facil porque es usado por otras funcionalidades
                    if (rolesPorEmpresa.contains("RA_AE_ADMINISTRADOR") || adminOrganismoActual )  {
                        entidades1 = recursos1;
                    } else {
                        /*
                         * Se hace este chequeo porque cuando se accede con un
			 * usuario 'Administrador de recursos', en el consultar
			 * recursos se muestren solamente los recursos en los
			 * cuáles el usuario tiene dicho rol
                         */
                        for (Recurso recurso1 : recursos1) {
                            if (rolesPorRecurso.containsKey(recurso1.getId())) {
                                if (rolesPorRecurso.get(recurso1.getId()).contains("RA_AE_ADMINISTRADOR_DE_RECURSOS")) {
                                    if (!entidades1.contains(recurso1)) {
                                        entidades1.add(recurso1);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    entidades = new ArrayList();
                    for (Recurso recurso1 : recursos1) {
                        if (rolesPorRecurso.containsKey(recurso1.getId())) {
                            if (!entidades.contains(recurso1)) {
                                entidades.add(recurso1);
                            }
                            /*
							 * Se hace este chequeo porque cuando se accede con
							 * un usuario 'Administrador de recursos', en el
							 * consultar recursos se muestren solamente los
							 * recursos en los cuáles el usuario tiene dicho rol
                             */
                            if (rolesPorRecurso.get(recurso1.getId()).contains("RA_AE_ADMINISTRADOR_DE_RECURSOS")) {
                                if (!entidades1.contains(recurso1)) {
                                    entidades1.add(recurso1);
                                }
                            }
                        }
                    }
                }
                //Ordenar los recursos
                Collections.sort(entidades, new RecursoComparatorNombre());
                Collections.sort(entidades1, new RecursoComparatorNombre());
                recursos = new RowList<>(entidades);
                recursosAux = new RowList<>(entidades1);

            } catch (Exception e) {
                addErrorMessage(e, MSG_ID);
            }
        } else if (recursos != null) {
            recursos.clear();
            recursosAux.clear();
        }
    }
    
    
	/**
	 * Este método se utiliza para modificar recurso, cuando se accede con un usuario que tenga el rol de recursos
	 * 'Administrador de recursos'
	 */
	public void setRecursoEnLista(Recurso newR) {
		int pos = 0;
		for (Row<Recurso> rEnLista : this.getRecursosAux()) {
			if (rEnLista.getData().getId().equals(newR.getId())) {
				this.getRecursosAux().set(pos, new Row<Recurso>(newR, this.getRecursosAux()));
				break;
			}
			pos++;
		}
	}
	
	/**
	 * Este método se utiliza para eliminar recurso, cuando se accede con un usuario que tenga el rol de recursos
	 * 'Administrador de recursos'
	 */
	public void removeRecursoEnLista(Recurso newR) {
		this.getRecursosAux().remove(new Row<Recurso>(newR, this.getRecursosAux()));
	}
	
	/**
	 * Este método se utiliza para copiar o importar recurso, cuando se accede con un usuario que tenga el rol de recursos
	 * 'Administrador de recursos'
	 */
	public void addRecursoALista(Recurso newR) {
		this.getRecursosAux().add(0, new Row<Recurso>(newR, this.getRecursosAux()));
	}


    /**
     * Este método es usado para la pantalla de selección de recurso. Devuelve la lista de recursos de la agenda actual, a la cual añade un recurso adicional,
     * con id=0 y nombre "ninguno" para permitir "desseleccionar" el recurso seleccionado (usado para generar reportes independientes del recurso)	*
     *
     * @return Recursos de la agenda seleccionada.
     */
    public RowList<Recurso> getRecursosSeleccion() {
        if (recursos == null || recursos.isEmpty()) {
            cargarRecursos();
        }
        RowList<Recurso> ret = new RowList<>();
        if (recursos != null) {
            ret.addAll(recursos);
        }
        Recurso ninguno = new Recurso();
        ninguno.setId(0);
        ninguno.setNombre(getTextos().get("ninguno"));
        ninguno.setDescripcion(getTextos().get("ninguno"));
        ret.add(0, new Row<>(ninguno, ret));
        return ret;
    }

    // Si hay recurso selecciondada, se cargan los datos del recurso asociados.
    // En caso contrario se vacía la lista de datosDelRecurso
    @SuppressWarnings("UseSpecificCatch")
    public void cargarDatosDelRecurso() {
        if (this.getRecursoSeleccionado() != null) {
            try {
                List<DatoDelRecurso> entidades;
                entidades = recursosEJB.consultarDatosDelRecurso(this
                        .getRecursoSeleccionado());
                datosDelRecurso = new RowList<>(entidades);
            } catch (Exception ex) {
                addErrorMessage(ex, MSG_ID);
            }
        } else if (datosDelRecurso != null) {
            datosDelRecurso.clear();
        }
    }

    public Boolean getMostrarDato() {
        mostrarDato = this.getDatoDelRecursoSeleccionado() != null;
        return mostrarDato;
    }

    public DatosUrlToForwardReserva getDatosUrlToForwardReserva() {
        return datosUrlToForwardReserva;
    }

    public void setDatosUrlToForwardReserva(
            DatosUrlToForwardReserva datosUrlToForwardReserva) {
        this.datosUrlToForwardReserva = datosUrlToForwardReserva;
    }

    public String getUrlAgendarReservas() {
        String urlAgendarReserva = "#";
        if (getAgendaMarcada() != null && getRecursoMarcado() != null) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
            try {
                String urlRetorno = URLEncoder.encode(request.getRequestURL().toString(), "utf-8");
                urlAgendarReserva = request.getContextPath()
                        + URL_BASE_TO_FORWARD_RESERVA
                        + URLEncoder.encode(getAgendaMarcada().getNombre(), "utf-8")
                        + "&recurso="
                        + URLEncoder.encode(getRecursoMarcado().getNombre(), "utf-8") + "&pagina_retorno=" + urlRetorno;
            } catch (UnsupportedEncodingException ex) {
                LOGGER.log(Level.SEVERE, "Error al  obtener la URL para agendar reservas", ex);
            }
        }
        return urlAgendarReserva;
    }

    public class DatosUrlToForwardReserva {

        private String urlReservaAgendaToForward;
        private boolean sePuedeHacerForwardAgendar;

        public DatosUrlToForwardReserva() {
            urlReservaAgendaToForward = "#";
            sePuedeHacerForwardAgendar = false;
        }

        public String getUrlReservaAgendaToForward() {
            return urlReservaAgendaToForward;
        }

        public void setUrlReservaAgendaToForward(
                String urlReservaAgendaToForward) {
            this.urlReservaAgendaToForward = urlReservaAgendaToForward;
        }

        public boolean isSePuedeHacerForwardAgendar() {
            return sePuedeHacerForwardAgendar;
        }

        public void setSePuedeHacerForwardAgendar(
                boolean sePuedeHacerForwardAgendar) {
            this.sePuedeHacerForwardAgendar = sePuedeHacerForwardAgendar;
        }
    }

    public Map<String, DatoASolicitar> getDatosASolicitar() {
        return datosASolicitar;
    }

    public void setDatosASolicitar(Map<String, DatoASolicitar> datosASolicitar) {
        this.datosASolicitar = datosASolicitar;
    }

    public Boolean getMostrarLlamador() {
        if (recursos != null && recursos.getSelectedRow() != null) {
            mostrarLlamador = recursos.getSelectedRow().getData().getUsarLlamador();
        } else {
            mostrarLlamador = true;
        }
        return mostrarLlamador;
    }

    public void setMostrarLlamador(Boolean mostrarLlamador) {
        this.mostrarLlamador = mostrarLlamador;
    }

    public Row<Agenda> getRowSelectAgenda() {
        if (getAgendaMarcada() == null) {
            return getAgendas().get(0);
        }
        return rowSelectAgenda;
    }

    public void setRowSelectAgenda(Row<Agenda> rowSelectAgenda) {
        this.rowSelectAgenda = rowSelectAgenda;
    }

    public Row<Recurso> getRowSelectRecurso() {
        if (getRecursoMarcado() == null) {
            return getRecursosSeleccion().get(0);
        }
        return rowSelectRecurso;
    }

    public void setRowSelectRecurso(Row<Recurso> rowSelectRecurso) {
        this.rowSelectRecurso = rowSelectRecurso;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public Empresa getEmpresaActual() {
        return empresaActual;
    }

    public void setEmpresaActual(Empresa empresaActual) {
        this.empresaActual = empresaActual;
    }

    public void setEmpresasUsuario(List<SelectItem> empresasUsuario) {
        this.empresasUsuario = empresasUsuario;
    }

    public List<SelectItem> getEmpresasUsuario() {
        return empresasUsuario;
    }

    public String getEmpresaActualId() {
        if (empresaActual != null) {
            return empresaActual.getId().toString();
        }
        return null;
    }
    
    public String getOrganismoActualId() {
        if (organismoActual != null) {
            return organismoActual.getId().toString();
        }
        return null;
    }

    public void cargarDatosUsuario() {
        LOGGER.log(Level.INFO, "cargar datos ");
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpSession session = request.getSession();
            //Esto es para prevenir el ataque de Session Fixation: cada vez que se pide recargar los datos del usuario se
            //genera un id de sesión nuevo, y se copian todos los datos que estaban de la anterior
            //1-Almacenar en un map temporal todas las propiedades de la sesión actual
            Map<String, Object> map0 = new HashMap<>();
            Enumeration<String> en = session.getAttributeNames();
            while (en.hasMoreElements()) {
                String attNombre = en.nextElement();
                map0.put(attNombre, session.getAttribute(attNombre));
            }
            //2-Invalidar la sesión actual
            session.invalidate();
            //3-Crear una nueva sesión
            session = request.getSession(true);
            //4-Poner en la sesión nueva los datos de la sesión anterior
            for (String nombre : map0.keySet()) {
                session.setAttribute(nombre, map0.get(nombre));
            }
            if (usuarioActual == null) {
                // No esta definido el usuario actual, se carga ahora
                Map<String, Object> sessionAttrs = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
                //Si está en la sesión el atributo "_const_cas_assertion_" es porque viene de CAS
                //Se utiliza como código de usuario el atributo remoteUser y hay que autenticarlo programáticamente (se genera una contraseña encriptado el
                //código de usuario con la clave privada del certificado configurado para CAS)
                
                if (sessionAttrs.containsKey("IDURUGUAY_ID_TOKEN")) {
                    String codigoIdUruguay = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();  
                    if (codigoIdUruguay != null) {
                        tipoLogin = TipoLogin.IDURUGUAY;
                        try {
                            if (request.getUserPrincipal() != null) {
                                //Hay que hacer un logout del usuario de CAS para poder hacer el login de SAE (cambia la clase de Principal)
                                request.logout();
                            }
                            hacerLoginAutomatico(request, codigoIdUruguay, null);
                            Principal principal = request.getUserPrincipal();
                            SecurityContextAssociation.setPrincipal(principal);
                        } catch (Exception ex) {
                            LOGGER.log(Level.SEVERE, "NO se pudo loguear al usuario desde IDURUGUAY", ex);
                            //No se pudo loguear al usuario
                            //Se crea un usuario artificial solo para poder ofrecer el link "logout" si es de IDURUGUAY
                            usuarioActual = new Usuario();
                            usuarioActual.setCodigo(codigoIdUruguay);
                            usuarioActual.setNombre(codigoIdUruguay);
                            FacesContext fCtx = FacesContext.getCurrentInstance();
                            fCtx.getApplication().getNavigationHandler().handleNavigation(fCtx, "", "noAutorizado");
                            return;
                        }
                    } else {
                        //Viene de cas pero no hay un usuario autenticado
                        LOGGER.log(Level.INFO, "[cargarDatosUsuario] 5a ");
                        cerrarSesion();
                    }
                }
                
                if (sessionAttrs.containsKey("_const_cas_assertion_")) {
                    String codigoCas = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
                    if (codigoCas != null) {
                        tipoLogin = TipoLogin.CAS;
                        try {
                            if (request.getUserPrincipal() != null) {
                                //Hay que hacer un logout del usuario de CAS para poder hacer el login de SAE (cambia la clase de Principal)
                                request.logout();
                            }
                            SecurityContext secContext = SecurityContextFactory.createSecurityContext("SDSAE");
                            SecurityContextAssociation.setSecurityContext(secContext);
                            hacerLoginAutomatico(request, codigoCas, null);
                            Principal principal = request.getUserPrincipal();
                            SecurityContextAssociation.setPrincipal(principal);
                        } catch (Exception ex) {
                            LOGGER.log(Level.SEVERE, "NO se pudo loguear al usuario desde CAS", ex);
                            //No se pudo loguear al usuario
                            //Se crea un usuario artificial solo para poder ofrecer el link "logout" si es de CDA
                            usuarioActual = new Usuario();
                            usuarioActual.setCodigo(codigoCas);
                            usuarioActual.setNombre(codigoCas);
                            FacesContext fCtx = FacesContext.getCurrentInstance();
                            fCtx.getApplication().getNavigationHandler().handleNavigation(fCtx, "", "noAutorizado");
                            return;
                        }
                    } else {
                        //Viene de cas pero no hay un usuario autenticado
                        LOGGER.log(Level.INFO, "[cargarDatosUsuario] 5a ");
                        cerrarSesion();
                    }
                }

                //Si está en la sesión el atributo "codigocda" se utiliza como código de usuario el atributo "documentocda",
                //sino se utiliza el atributo remoteUser; además, si viene de CDA hay que autenticarlo programáticamente (la
                //contraseña usada es la encriptacion con RSA y la clave privada del certificado usado por la válvula, se debe
                //verificar con la clave pública del mismo certificado)
                //Nota: la válvula pone valores como los siguientes: codigocda=uy-ci-88888889, documentocda=88888889
                if (sessionAttrs.containsKey("IDURUGUAY_SAML")) {
                    
                    String codigoCDA = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();  
                    if (codigoCDA != null) {
                        //Viene de la válvula de CDA
                        tipoLogin = TipoLogin.CDA;
                        try {
                            if (request.getUserPrincipal() != null) {
                                //Hay que hacer un logout del usuario de IDURUGUAY para poder hacer el login de SAE (cambia la clase de Principal)
                                request.logout();
                            }
                            hacerLoginAutomatico(request, codigoCDA, null);
                            Principal principal = request.getUserPrincipal();
                            SecurityContextAssociation.setPrincipal(principal);
                   
                        } catch (Exception ex) {
                            //No se pudo loguear al usuario
                            //Se crea un usuario artificial solo para poder ofrecer el link "logout" si es de CDA
                            usuarioActual = new Usuario();
                            usuarioActual.setCodigo(codigoCDA);
                            usuarioActual.setNombre(codigoCDA);
                            FacesContext ctx = FacesContext.getCurrentInstance();
                            ctx.getApplication().getNavigationHandler().handleNavigation(ctx, "", "noAutorizado");
                            return;
                        }
                    }
                    else{
                        //Viene de cas pero no hay un usuario autenticado
                        LOGGER.log(Level.INFO, "[cargarDatosUsuario] 5a ");
                        cerrarSesion();
                    }
                }
                
                String codigo = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
                //Cargar organismos del usuario
                if (codigo != null) {
                    try {
                        usuarioActual = usuariosEmpresasEJB.obtenerUsuarioPorCodigo(codigo);
                        organismosUsuario = new ArrayList<>();
                        if (usuarioActual != null) {
                            //aqui se van a consultar los organismos del usuario
                            organismos =  usuariosEmpresasEJB.consultarOrganismosEmpresaUsuario(usuarioActual);
                            LOGGER.log(Level.SEVERE, MSG_ID);
                            if(!organismos.isEmpty()){
                                organismosUsuario.add(new SelectItem(0, "Sin especificar"));
                                for(Organismo org : organismos){
                                    organismosUsuario.add(new SelectItem(org.getId(), org.getNombre()));
                                }
                            }
                            
                            
                            if (!organismos.isEmpty()) {
                                if(organismos.size()==1){
                                    mostrarDialogOrganismo = Boolean.FALSE;
                                    organismoActual = organismos.get(0);
                                    if(!usuarioActual.getOrganismos().isEmpty()){
                                        if(usuarioActual.getOrganismos().stream().filter(o-> o.getCodigo().equals(organismoActual.getCodigo())).findFirst().orElse(null)!=null){
                                            adminOrganismoActual = Boolean.TRUE;
                                        }
                                    }
                                    cargarEmpresasUsuario();
                                    
                                    if(empresaActual!=null){
                                        cambioEmpresa(empresaActual.getId());
                                    }
                                    else{
                                        Integer emp = null;
                                        cambioEmpresa(emp);
                                    }
                
                                    //cambioEmpresa(empresaActual.getId());
                                    
                                    //Cargar las propiedades de configuracion
                                    Boolean bMostrarFechaActual = configuracionEJB.getBoolean("MOSTRAR_FECHA_ACTUAL",organismoActual.getId());
                                    if (bMostrarFechaActual != null) {
                                        mostrarFechaActual = bMostrarFechaActual;
                                    }
                                }
                                else{
                                    hacerLoginAutomatico(request, usuarioActual.getCodigo(), null);
                                    mostrarDialogOrganismo = Boolean.TRUE;
                                }
                                
                            } else {
                                FacesContext ctx = FacesContext.getCurrentInstance();
                                ctx.getApplication().getNavigationHandler().handleNavigation(ctx, "", "noAutorizado");
                            }    
                        }
                        
                    } catch (ApplicationException aEx) {
                        LOGGER.log(Level.SEVERE, "Error al  cargar datos del usuario", aEx);
                    }
                }
                else if (tipoLogin == null) {
                    cerrarSesion();
                } else {
                    switch (tipoLogin) {
                        case CAS:
                            cerrarSesionCAS();
                            break;
                        case CDA:
                            ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
                            ectx.redirect(request.getContextPath() + "/logoutcda");
                            break;
                        case LOCAL:
                            cerrarSesion();
                            break;
                        case IDURUGUAY:
                            cerrarSesionIDURUGUAY();
                            break;
                    }
                }
                                
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error al  cargar datos del usuario", ex);
        }
    }

    //Genera un password de uso único
    private String hacerLoginAutomatico(HttpServletRequest request, String codigo, Integer empresaId) throws NamingException, ServletException {
        //Cerrar la sesión del usuario
        request.logout();
        //Cargar los datos en el contexto JNDI para que el AutoLoginModule tome la contraseña de uso único para el usuario
        InitialContext ctx = new InitialContext();
        String password = UUID.randomUUID().toString();
        String sessionId = request.getSession().getId();
        try {
            ctx.bind("{LOGIN}{"+sessionId+"}", password);
        } catch (NamingException nabEx) {
            ctx.rebind("{LOGIN}{"+sessionId+"}", password);
        }
        
        //Hacer el login
        if (empresaId != null) {
            request.login(sessionId+"/"+codigo + "/" + empresaId, password);
        } else {
            request.login(sessionId+"/"+codigo, password);
        }
        return password;
    }

    public void cargarEmpresasUsuario() {
        empresasUsuario = new ArrayList<>();
        try {
            if (usuarioActual != null) {
                List<Empresa> empresas;
                // preguntar si es admin de un organismo 
                if (adminOrganismoActual) {
                    empresas = usuariosEmpresasEJB.consultarEmpresasPorOrganismo(organismoActual);
                } else {
                    empresas = usuariosEmpresasEJB.consultarEmpresasPorUsuarioOrganismo(usuarioActual, organismoActual);
                }
                if (empresas != null && !empresas.isEmpty()) {
                    for (Empresa emp : empresas) {
                        empresasUsuario.add(new SelectItem(emp.getId(), emp.getNombre()));
                    }
                    if (empresaActual == null || !empresas.contains(empresaActual)) {
                        empresaActual = empresas.get(0);
                    }
                } else {
                    empresaActual = null;
                }
            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error al  cargar las empresas del usuario", ex);
        }
    }

    public void cambioEmpresa(ValueChangeEvent event) {
        try {
            Integer empId = Integer.valueOf((String) event.getNewValue());
            cambioEmpresa(empId);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error al cambiar la empresa", ex);
        }
    }

    public void cambioEmpresa(Integer empId) {
        empresaActual = null;
        try {
            // Seleccionar la nueva empresa
            seleccionarEmpresa(empId);
            //Buscar el logo de la empresa
            if(empId!=null){
                empresaActualLogoBytes = usuariosEmpresasEJB.obtenerLogoEmpresaPorEmpresaId(empId);
            }
            // Desmarcar la agenda y el recurso (pertenecen a otra empresa)
            if (recursos != null) {
                recursos.setSelectedRow(null);
            }
            if (agendas != null) {
                agendas.setSelectedRow(null);
            }
            // Cargar nuevamente los textos
            cargarTextos();
            // Navegar a la pagina de inicio
            ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
            String contextPath = ectx.getRequestContextPath();
            ectx.redirect(contextPath);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error al cambiar la empresa", ex);
        }
    }

    /**
     * Metodo que dado el id de una empresa busca la empresa correspondiente entre las empresas del usuario actual
     *
     * @param empresaId Identificador de la empresa
     */
    @SuppressWarnings("UseSpecificCatch")
    public void seleccionarEmpresa(Integer empresaId) {
        
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            //String codigoUsuario = request.getRemoteUser();
            if (empresaId == null) {
                hacerLoginAutomatico(request, usuarioActual.getCodigo(), null);
            }
            
            if (usuarioActual != null) {
                if (empresaActual == null || !empresaId.equals(empresaActual.getId())) {
                    // Loguear al usuario en la nueva empresa
                    hacerLoginAutomatico(request, usuarioActual.getCodigo(), empresaId);
                    // Cargar los datos de la empresa
                    empresaActual = usuariosEmpresasEJB.obtenerEmpresaPorId(empresaId);
                    //Cargar los roles del usuario en la empresa y en cada uno de los recursos
                    cargarRoles();
                    // Cargar las agendas del usuario para la empresa actual
                    cargarAgendas();
                } else {
                    //Nada para hacer, es la misma empresa
                }
            } else {
                //Viene de cas pero no hay un usuario autenticado
                cerrarSesion();
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error al seleccionar una empresa", ex);
        }
    }
    

    /**
     * Metodo que dado el id de una empresa busca la empresa correspondiente entre las empresas del usuario actual
     *
     */
    public void seleccionarOrganismo() {
        if (organismoIdSelec == null || organismoIdSelec.equals(0)) {
            addErrorMessage(this.getTextos().get("debe_selecionar_un_organismo"), "form:organismo");
            mostrarDialogOrganismo = Boolean.TRUE;
            return;
        }
        else{
            organismoActual = organismos.stream().filter(o -> o.getId().equals(organismoIdSelec)).findFirst().orElse(null);
            if(organismoActual!=null){
                if(!usuarioActual.getOrganismos().isEmpty()){
                    if(usuarioActual.getOrganismos().stream().filter(o-> o.getCodigo().equals(organismoActual.getCodigo())).findFirst().orElse(null)!=null){
                        adminOrganismoActual = Boolean.TRUE;
                    }
                }
                cargarEmpresasUsuario();
                if(empresaActual!=null){
                    cambioEmpresa(empresaActual.getId());
                }
                else{
                    Integer emp = null;
                    cambioEmpresa(emp);
                }
                
                mostrarDialogOrganismo = Boolean.FALSE;
                
                //Cargar las propiedades de configuracion
                Boolean bMostrarFechaActual = configuracionEJB.getBoolean("MOSTRAR_FECHA_ACTUAL",organismoActual.getId());
                if (bMostrarFechaActual != null) {
                    mostrarFechaActual = bMostrarFechaActual;
                }
                
            }
            
        }
    }

    public String cerrarSesion() {
        //Si la autenticación fue con la válvula de CDA se invoca al logout de CDA, sino se hace logout local
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException ex) {
            LOGGER.log(Level.SEVERE, "Error al cerrar la sesión del usuario, pero de todas maneras se invalida", ex);
        }
        fc.getExternalContext().invalidateSession();
        return "inicio";
    }
    
    public String cerrarSesionCAS() {
        if(!TipoLogin.CAS.equals(tipoLogin)) {
            return null;
        }
        try {
            String casLogoutURL = System.getProperty("cas.server.url");
            if(casLogoutURL!=null) {
                casLogoutURL = casLogoutURL+"/logout";
                ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
                ectx.invalidateSession();
                ectx.redirect(casLogoutURL);
            }            
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public String cerrarSesionIDURUGUAY() {
        if(!TipoLogin.IDURUGUAY.equals(tipoLogin)) {
            return null;
        }
        try {
            Context initContext = new InitialContext();
            String urlLogout = (String)initContext.lookup("java:global/iduruguayhttphandler/ssoRedirectUrl");
            ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession sesion = (HttpSession) ctx.getSession(false);
            String idToken = (String) sesion.getAttribute("IDURUGUAY_ID_TOKEN");
            String ssoBseUrl = getSsoBaseUrl();
            String logoutUrl = ssoBseUrl + "logout?id_token_hint="+idToken+"&post_logout_redirect_uri="+URLEncoder.encode(urlLogout, "UTF-8");
            ctx.redirect(logoutUrl);
            sesion.invalidate();
     
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getTipoLogin() {
        if (tipoLogin == null) {
            return "XXX";
        }
        return tipoLogin.toString();
    }

    //private String loginUsername;
    //private String loginPassword;
    /**
     * Operacion para autenticar usuarios. Nota: aunque se utiliza JAAS, no se usa el mecanismo directo (j_security_check, j_username, j_password) porque es
     * necesario redirigir al usuario siempre a la pagina de inicio, y el mecanismo directo siempre lleva al usuario a la pagina que solicito (la cual podria
     * depender de que el usuario haga algo antes de acceder a ella, por ejemplo seleccionar una empresa, o una agenda). Los parametros se toman directamente
     * del request ("formLogin:username") y ("formLogin:password") para no inroducir dos atributos en el session bean que despues no se van a usar nunca mas.
     * Hay que tener cuidado de que si cambia el nombre del form ("formLogin") se puede afectar a este metodo.
     *
     * @return
     */
    public String iniciarSesion() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();

        String username = request.getParameter("formLogin:username");
        String password = request.getParameter("formLogin:password");

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar su código de usuario  y contraseña",""));
            return null;
        }

        try {
            //Este login es normal, usando las credenciales proporcionadas por el usuario
            request.login(username, password);
            tipoLogin = TipoLogin.LOCAL;
            cargarDatosUsuario();
            return "inicio";
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(),ex);
            if (ex.getMessage() == null || !ex.getMessage().contains("Login failed")) {
                LOGGER.log(Level.SEVERE, "Error al validar las credenciales", ex);
            }
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Código de usuario o contraseña incorrectos",""));
            return null;
        }
    }

    public StreamedContent getEmpresaActualLogo() {
        // No se puede cachear porque un stream y la segunda vez que el cliente
        // lo pide está cerrado y no es muestra la imagen
        if (empresaActual != null && empresaActualLogoBytes != null) {
            return new DefaultStreamedContent(new ByteArrayInputStream(empresaActualLogoBytes));
        }
        return null;
    }

    public String getEmpresaActualLogoTexto() {
        if (empresaActual != null) {
            if (empresaActual.getLogoTexto() != null
                    && !empresaActual.getLogoTexto().trim().isEmpty()) {
                return empresaActual.getLogoTexto();
            } else {
                return empresaActual.getNombre();
            }
        }
        return "Logo";
    }

    public String getEmpresaActualNombre() {
        if (empresaActual != null) {
            return empresaActual.getNombre();
        } else {
            return "";
        }
    }

    // *****************************************************************************************************
    // ***************************Pasos para la reserva
    // Admin***********************************************
    // *****************************************************************************************************
    public void limpiarPaso2() {

        setDisponibilidadesDelDiaMatutina(null);
        setDisponibilidadesDelDiaVespertina(null);

        setDisponibilidad(null);
        setReserva(null);

        limpiarPaso3();
    }

    public void limpiarPaso3() {
    }

    public RowList<Disponibilidad> getDisponibilidadesDelDiaMatutina() {
        return disponibilidadesDelDiaMatutina;
    }

    public void setDisponibilidadesDelDiaMatutina(
            RowList<Disponibilidad> disponibilidadesDelDiaMatutina) {
        this.disponibilidadesDelDiaMatutina = disponibilidadesDelDiaMatutina;
    }

    public RowList<Disponibilidad> getDisponibilidadesDelDiaVespertina() {
        return disponibilidadesDelDiaVespertina;
    }

    public void setDisponibilidadesDelDiaVespertina(
            RowList<Disponibilidad> disponibilidadesDelDiaVespertina) {
        this.disponibilidadesDelDiaVespertina = disponibilidadesDelDiaVespertina;
    }

    public Disponibilidad getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Disponibilidad disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public Date getDiaSeleccionado() {
        return diaSeleccionado;
    }

    public void setDiaSeleccionado(Date diaSeleccionado) {
        this.diaSeleccionado = diaSeleccionado;
    }

    public VentanaDeTiempo getVentanaMesSeleccionado() {
        return ventanaMesSeleccionado;
    }

    public void setVentanaMesSeleccionado(VentanaDeTiempo ventanaMesSeleccionado) {
        this.ventanaMesSeleccionado = ventanaMesSeleccionado;
    }

    public List<Integer> getCuposXdiaMesSeleccionado() {
        return cuposXdiaMesSeleccionado;
    }

    public void setCuposXdiaMesSeleccionado(
            List<Integer> cuposXdiaMesSeleccionado) {
        this.cuposXdiaMesSeleccionado = cuposXdiaMesSeleccionado;
    }

    public VentanaDeTiempo getVentanaCalendario() {
        return ventanaCalendario;
    }

    public void setVentanaCalendario(VentanaDeTiempo ventanaCalendario) {
        this.ventanaCalendario = ventanaCalendario;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Reserva getReservaConfirmada() {
        return reservaConfirmada;
    }

    public void setReservaConfirmada(Reserva reservaConfirmada) {
        this.reservaConfirmada = reservaConfirmada;
    }

    public String getCodigoSeguridadReserva() {
        return codigoSeguridadReserva;
    }

    public void setCodigoSeguridadReserva(String codigoSeguridadReserva) {
        this.codigoSeguridadReserva = codigoSeguridadReserva;
    }

    public Organismo getOrganismoActual() {
        return organismoActual;
    }

    public void setOrganismoActual(Organismo organismoActual) {
        this.organismoActual = organismoActual;
    }

    public List<SelectItem> getOrganismosUsuario() {
        return organismosUsuario;
    }

    public void setOrganismosUsuario(List<SelectItem> organismosUsuario) {
        this.organismosUsuario = organismosUsuario;
    }

    public Boolean getMostrarDialogOrganismo() {
        return mostrarDialogOrganismo;
    }

    public void setMostrarDialogOrganismo(Boolean mostrarDialogOrganismo) {
        this.mostrarDialogOrganismo = mostrarDialogOrganismo;
    }

    public Integer getOrganismoIdSelec() {
        return organismoIdSelec;
    }

    public void setOrganismoIdSelec(Integer organismoIdSelec) {
        this.organismoIdSelec = organismoIdSelec;
    }

    
    
    

    //*******************************************************************
    // PARA LOS TEXTOS FIJOS
    //*******************************************************************
    private Map<String, String> textos = new SofisHashMap();

    public void cargarTextos() {
        try {
            textos = generalEJB.consultarTextos(idiomaActual);
        } catch (ApplicationException ex) {
            textos = new SofisHashMap();
            LOGGER.log(Level.SEVERE, "Error al cargar los textos", ex);
        }
    }

    public Map<String, String> getTextos() {
        return textos;
    }

    public List<SelectItem> getIdiomasSoportados() {
        List<String> sIdiomas = new ArrayList();
        if(organismoActual!=null){
            sIdiomas = usuariosEmpresasEJB.obtenerIdiomasSoportados(organismoActual.getId());
        }

        Locale localeActual = new Locale(idiomaActual);
        idiomasSoportados = new ArrayList<>();
        if (sIdiomas != null && !sIdiomas.isEmpty()) {
            for (String sIdioma : sIdiomas) {
                Locale locale = new Locale(sIdioma);
                idiomasSoportados.add(new SelectItem(locale.getLanguage(), locale.getDisplayLanguage(localeActual)));
            }
        } else {
            for (Locale locale : Locale.getAvailableLocales()) {
                if (locale.getCountry().isEmpty()) {
                    idiomasSoportados.add(new SelectItem(locale.getLanguage(), locale.getDisplayLanguage(localeActual)));
                }
            }
        }
        Collections.sort(idiomasSoportados, new SelectItemComparator());
        return idiomasSoportados;
    }

    public String getIdiomaActual() {
        return idiomaActual;
    }

    public void setIdiomaActual(String idiomaActual) {
        this.idiomaActual = idiomaActual;
    }

    public void cambioIdiomaActual(ValueChangeEvent event) {
        limpiarMensajesError();
        idiomasSoportados = null;
        try {
            String idiomaSeleccionado = (String) event.getNewValue();
            List<String> idiomasDisponibles = usuariosEmpresasEJB.obtenerIdiomasSoportados(organismoActual.getId());
            if (!idiomasDisponibles.contains(idiomaSeleccionado)) {
                return;
            }
            idiomaActual = idiomaSeleccionado;
            RequestContext.getCurrentInstance().execute("document.documentElement.lang='" + idiomaActual + "'");
            cargarTextos();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "No se pudo cambiar el idioma", ex);
        }
    }

    public byte[] getEmpresaActualLogoBytes() {

        return empresaActualLogoBytes;
    }

    public void setEmpresaActualLogoBytes(byte[] empresaActualLogoBytes) {
        this.empresaActualLogoBytes = empresaActualLogoBytes;
    }

    public boolean getBackendConCda() {
        Boolean backendConCda=Boolean.FALSE;
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Constantes.IDURUGUAY_SAML_SESION_ATTRIBUTE)!=null){
            backendConCda = Boolean.TRUE;
        }
        else if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Constantes.IDURUGUAY_OPENID_SESION_ATTRIBUTE)!=null){
            backendConCda = Boolean.TRUE;
        }
        return backendConCda;
    }

    public void controlarAccesoRestringido(ComponentSystemEvent event) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            
            //Si no hay un usuario, no tiene acceso
            Principal principal = ctx.getExternalContext().getUserPrincipal();
            if (principal == null) {
                ctx.getApplication().getNavigationHandler().handleNavigation(ctx, "", "noAutorizado");
            }
            //Si hay un usuario, se verifica que tenga al menos uno de los roles requeridos para la página solicitada
            String path = ctx.getExternalContext().getRequestServletPath();
            if(path != null) {
                String[] rolesRequeridos = appMBean.getPermisosRequeridos(path);
                if(!tieneRoles(rolesRequeridos)) {                    
                    ctx.getApplication().getNavigationHandler().handleNavigation(ctx, "", "noAutorizado");
                }
            }            
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, "Error al validar el acceso a la página", ex);
            ctx.getApplication().getNavigationHandler().handleNavigation(ctx, "", "noAutorizado");
        }
    }

    public void limpiarTrazas() {
        agendarReservasEJB.limpiarTrazas();
    }

    public boolean tieneRol(String rol) {
        return tieneRoles(this.getRecursoMarcado(), new String[]{rol});
    }

    public boolean tieneRoles(String[] roles) {
        return tieneRoles(this.getRecursoMarcado(), roles);
    }
    
    public boolean tieneRoles(Recurso recurso, String[] roles) {
        if (usuarioActual == null) {
            return false;
        }        
        
        if(adminOrganismoActual){
            return true;
        }
        
        //Si la lista es vacía (no nula) es porque no se requiere ningún rol
        if(roles!=null && roles.length == 0) {
            return true;
        }
        
        //Verificar si tiene el rol a nivel global
        if(rolesPorEmpresa!=null && !rolesPorEmpresa.isEmpty()){
            if(roles!=null && roles.length>0){
                for (String rol : roles) {
                    if(rolesPorEmpresa.contains(rol)) {
                        return true;
                    }
                }
            }
        }
        //Verificar si tiene el rol a nivel de recurso
        if(recurso!=null && rolesPorRecurso.containsKey(recurso.getId())) {
            for (String rol : roles) {
                if(rolesPorRecurso.get(recurso.getId()).contains(rol)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getMensajeReservaExistente() {
        if (this.recurso == null || this.recurso.getId() == null) {
            return "";
        }
        if (this.recurso.getPeriodoValidacion() == null || this.getRecurso().getPeriodoValidacion() == 0) {
            return textos.get("solo_se_permite_una_reserva_diaria");
        }
        return textos.get("solo_se_permite_una_reserva_en_un_periodo_de_dias").replace("{dias}", "" + (this.recurso.getPeriodoValidacion() * 2));
    }
    
    public String getOrganismoActualNombre() {
        if (organismoActual != null) {
            return organismoActual.getNombre();
        } else {
            return "";
        }
    }
    
    public void cambioOrganismo(ValueChangeEvent event) {
        Integer orgId = (Integer) event.getNewValue();
        if(!orgId.equals(0)){
            mostrarBtnOk = Boolean.FALSE;
        }
        else{
            mostrarBtnOk = Boolean.TRUE;
        }
    }

    public Boolean getMostrarBtnOk() {
        return mostrarBtnOk;
    }

    public void setMostrarBtnOk(Boolean mostrarBtnOk) {
        this.mostrarBtnOk = mostrarBtnOk;
    }
    
    public String getSsoBaseUrl() {
        if(ssoBaseUrl == null) {
            Context initContext = null;
            try {
                initContext = new InitialContext();
                ssoBaseUrl = (String)initContext.lookup("java:global/iduruguayhttphandler/ssoBaseUrl");
            } catch (NamingException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            } finally {
                try {
                    initContext.close();
                } catch (NamingException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
            }        
        }
        return ssoBaseUrl;
    }

    public Boolean getAdminOrganismoActual() {
        return adminOrganismoActual;
    }

    public void setAdminOrganismoActual(Boolean adminOrganismoActual) {
        this.adminOrganismoActual = adminOrganismoActual;
    }

    public Boolean getEnvioCorreoReserva() {
        return envioCorreoReserva;
    }

    public void setEnvioCorreoReserva(Boolean envioCorreoReserva) {
        this.envioCorreoReserva = envioCorreoReserva;
    }

    public boolean isLanguageComboShown(){
        return  getIdiomasSoportados().size() > 1 && getOrganismoActual() != null;
    }

    public void setUsuariosEmpresasEJB(UsuariosEmpresasLocal usuariosEmpresasEJB) {
        this.usuariosEmpresasEJB = usuariosEmpresasEJB;
    }

}
