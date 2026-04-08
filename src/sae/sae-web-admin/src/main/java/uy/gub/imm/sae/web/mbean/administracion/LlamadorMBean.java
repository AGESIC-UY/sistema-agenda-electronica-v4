/*
 * SAE - Sistema de Agenda Electronica
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.PrimeFaces;

import uy.gub.imm.sae.business.ejb.facade.AgendarReservasLocal;
import uy.gub.imm.sae.business.ejb.facade.LlamadasLocal;
import uy.gub.imm.sae.business.ejb.facade.RecursosLocal;
import uy.gub.imm.sae.entity.Agenda;
import uy.gub.imm.sae.entity.DatoASolicitar;
import uy.gub.imm.sae.entity.DatoReserva;
import uy.gub.imm.sae.entity.Disponibilidad;
import uy.gub.imm.sae.entity.Llamada;
import uy.gub.imm.sae.entity.Recurso;
import uy.gub.imm.sae.entity.Reserva;
import uy.gub.imm.sae.entity.TextoRecurso;
import uy.gub.imm.sae.exception.UserException;
import uy.gub.imm.sae.web.common.TipoMonitor;

@ManagedBean
@ViewScoped
public class LlamadorMBean extends BaseMBean {

    private static final Logger LOGGER = Logger.getLogger(LlamadorMBean.class.getName());
    
    public static final String MSG_ID = "pantalla";

    @EJB
    private RecursosLocal recursosEJB;

    @EJB
    private LlamadasLocal llamadasEJB;

    @EJB
    private AgendarReservasLocal agendarReservasEJB;

    @ManagedProperty(value = "#{sessionMBean}")
    private SessionMBean sessionMBean;

    @ManagedProperty(value = "#{llamadorSessionMBean}")
    private LlamadorSessionMBean llamadorSessionMBean;

    private static final String URL_BASE_TO_FORWARD_LLAMADOR_EXTERNO = "/administracion/llamador/listaDeLlamadasExterno.xhtml";
    private static final String URL_BASE_TO_FORWARD_LLAMADOR_INTERNO = "/administracion/llamador/listaDeLlamadasInterno.xhtml";
    
    private List<SelectItem> recursosSeleccionables;
    private Integer[] recursosSeleccionados;

    @PostConstruct
    public void init() {
        try {
            String[] rolesRequeridos = new String[]{"RA_AE_LLAMADOR", "RA_AE_ADMINISTRADOR_DE_RECURSOS","RA_AE_ADMINISTRADOR"};
            recursosSeleccionables = new ArrayList<>();
            List<Agenda> agendas = agendarReservasEJB.consultarAgendas();
            for(Agenda agenda : agendas) {
                SelectItemGroup grupo = new SelectItemGroup(agenda.getNombre());
                List<Recurso> recursos = agendarReservasEJB.consultarRecursos(agenda);
                int recursosSize = 0;
                for(int i=0; i<recursos.size(); i++) {
                    if(sessionMBean.tieneRoles(recursos.get(i), rolesRequeridos)) {
                        recursosSize++;
                    }
                }
                SelectItem[] items = new SelectItem[recursosSize];
                for(int i=0; i<recursos.size(); i++) {
                    if(sessionMBean.tieneRoles(recursos.get(i), rolesRequeridos)) {
                        items[i] = new SelectItem(recursos.get(i).getId(), recursos.get(i).getNombre());
                    }
                }
                if (recursosSize>0) {
	                grupo.setSelectItems(items);
	                recursosSeleccionables.add(grupo);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(LlamadorMBean.class.getName()).log(Level.SEVERE, null, ex);
            addErrorMessage(sessionMBean.getTextos().get("debe_haber_un_recurso_seleccionado"));
        }
    }

    /**
     * Configura titulo pantalla de configuracion del llamador cuando se accede desde la administracion
     * @param event
     */
    public void beforePhaseConfiguracionLlamador(PhaseEvent event) {
        if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            sessionMBean.setPantallaTitulo(sessionMBean.getTextos().get("configuracion_del_llamador"));
        }
    }

    /**
     * Valida parametros para armar el llamador generico
     * @param event
     */
    @SuppressWarnings("UseSpecificCatch")
    public void beforePhaseListaDeLlamadas(PhaseEvent event) {
        if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            sessionMBean.setPantallaTitulo(sessionMBean.getTextos().get("lista_de_llamadas"));
            if(llamadorSessionMBean.getRecursos()==null || llamadorSessionMBean.getRecursos().isEmpty()) {
                addErrorMessage(sessionMBean.getTextos().get("debe_seleccionar_al_menos_un_recurso"), MSG_ID);
            }
        }
    }

    public LlamadorSessionMBean getLlamadorSessionMBean() {
        return llamadorSessionMBean;
    }

    public void setLlamadorSessionMBean(LlamadorSessionMBean llamadorSessionMBean) {
        this.llamadorSessionMBean = llamadorSessionMBean;
    }

    public SessionMBean getSessionMBean() {
        return sessionMBean;
    }

    public void setSessionMBean(SessionMBean sessionMBean) {
        this.sessionMBean = sessionMBean;
    }

    public String getPulgadasMonitor() {
        return llamadorSessionMBean.getTipoMonitor().getPulgadas().toString();
    }

    public String[] getPulgadasMonitorArray() {
        String[] ret = new String[1];
        ret[0] = llamadorSessionMBean.getTipoMonitor().getPulgadas().toString();
        return ret;
    }

    public Integer getCantLlamadas() {
        if (llamadorSessionMBean.getTipoMonitor() == null) {
            return 0;
        }
        return llamadorSessionMBean.getTipoMonitor().getLineas();
    }

    public String getNombreAgenda() {
        if (sessionMBean.getAgendaMarcada() != null) {
            return sessionMBean.getAgendaMarcada().getNombre().toUpperCase();
        } else {
            return null;
        }
    }

    public String getDescripcionRecurso() {
        if (sessionMBean.getRecursoMarcado() != null) {
            return sessionMBean.getRecursoMarcado().getNombre().toUpperCase();
        } else {
            return "";
        }
    }

    public String getNombreColumnaPuesto() {
        TextoRecurso textoRecurso = getTextoRecurso(sessionMBean.getRecursoMarcado(), sessionMBean.getIdiomaActual());
        if (textoRecurso != null) {
            return textoRecurso.getTituloPuestoEnLlamador();
        } else {
            return "";
        }
    }

    public String getNombreColumnaDatos() {
        TextoRecurso textoRecurso = getTextoRecurso(sessionMBean.getRecursoMarcado(), sessionMBean.getIdiomaActual());
        if (sessionMBean.getRecursoMarcado() != null) {
            return textoRecurso.getTituloCiudadanoEnLlamador();
        } else {
            return "";
        }
    }

    public Boolean getMostrarNumero() {
        if (sessionMBean.getRecursoMarcado() != null) {
            return sessionMBean.getRecursoMarcado().getMostrarNumeroEnLlamador();
        } else {
            return false;
        }
    }

    public Boolean getMostrarDatos() {
        return llamadorSessionMBean.getMostrarDatos();
    }

    /*
	 * Determina si hay nueva llamada desde el ultimo refresco
     */
    public void refrescarLlamadasExterno() {
        try {
            List<Llamada> llamadasNuevas = llamadasEJB.obtenerLlamadasExterno(llamadorSessionMBean.getRecursos(), getCantLlamadas());
            List<Llamada> llamadasAnteriores = llamadorSessionMBean.getLlamadas();
            if (llamadasAnteriores == null) {
                llamadasAnteriores = new ArrayList<>();
            }
            //Ver si son llamadas nuevas o estaban desde antes
            //A las nuevas hay que destacarlas..
            for (Llamada llamada : llamadasNuevas) {
                if (!llamadasAnteriores.contains(llamada)) {
                    llamadorSessionMBean.getLlamadasADestacar().add(llamada);
                }
            }
            llamadorSessionMBean.setLlamadas(llamadasNuevas);
            Llamada llamadaADestacar = llamadorSessionMBean.getLlamadasADestacar().poll();
            if (llamadaADestacar != null) {
                Reserva reserva = llamadaADestacar.getReserva();
                PrimeFaces requestContext = PrimeFaces.current();
                requestContext.executeScript("setearValor('varPuesto','" + reserva.getLlamada().getPuesto() + "');");
                requestContext.executeScript("setearValor('varDocumento','" + (reserva.getNumeroDocumento() != null ? reserva.getNumeroDocumento() : "") + "');");
                requestContext.executeScript("PF('llamadaDestacada').show();");
            }
        } catch (UserException uEx) {
            addErrorMessage(uEx, MSG_ID);
        }
    }

    public void refrescarLlamadasInterno() {
        try {
            List<Llamada> llamadasNuevas = llamadasEJB.obtenerLlamadasInterno(llamadorSessionMBean.getRecursos(), getCantLlamadas());
            List<Llamada> llamadasAnteriores = llamadorSessionMBean.getLlamadas();
            if (llamadasAnteriores == null) {
                llamadasAnteriores = new ArrayList<>();
            }
            //Ver si son llamadas nuevas o estaban desde antes
            //A las nuevas hay que destacarlas..
            for (Llamada llamada : llamadasNuevas) {
                if (!llamadasAnteriores.contains(llamada)) {
                    llamadorSessionMBean.getLlamadasADestacar().add(llamada);
                }
            }
            llamadorSessionMBean.setLlamadas(llamadasNuevas);
            Llamada llamadaADestacar = llamadorSessionMBean.getLlamadasADestacar().poll();
            if (llamadaADestacar != null) {
                Reserva reserva = llamadaADestacar.getReserva();
                PrimeFaces requestContext = PrimeFaces.current();
                requestContext.executeScript("setearValor('varAgenda','" + reserva.getDisponibilidades().get(0).getRecurso().getAgenda().getNombre() + "');");
                requestContext.executeScript("setearValor('varRecurso','" + reserva.getDisponibilidades().get(0).getRecurso().getNombre() + "');");
                requestContext.executeScript("setearValor('varSerie','" + (reserva.getSerie() != null ? reserva.getSerie() : "") + "');");
                requestContext.executeScript("setearValor('varNumero','" + (reserva.getNumero() != null ? reserva.getNumero().toString() : "") + "');");
                requestContext.executeScript("setearValor('varDocumento','" + (reserva.getNumeroDocumento() != null ? reserva.getNumeroDocumento() : "") + "');");
                requestContext.executeScript("PF('llamadaDestacada').show();");
            }
        } catch (UserException uEx) {
            addErrorMessage(uEx, MSG_ID);
        }
    }
    
    public void abrirLlamadorExterno() {
        if(recursosSeleccionados.length==0) {
            addErrorMessage(sessionMBean.getTextos().get("debe_seleccionar_al_menos_un_recurso"), MSG_ID);
            return;
        }
        try {
            String[] rolesRequeridos = new String[]{"RA_AE_LLAMADOR", "RA_AE_ADMINISTRADOR_DE_RECURSOS","RA_AE_ADMINISTRADOR"};
            List<Recurso> recursos = new ArrayList<>();
            for(Integer recursoId : recursosSeleccionados) {
                try {
                    Recurso recurso = recursosEJB.obtenerRecursoPorId(recursoId);
                    if(recurso!=null && sessionMBean.tieneRoles(recurso, rolesRequeridos)) {
                        recursos.add(recurso);
                    }
                }catch(Exception ex) {
                    LOGGER.log(Level.WARNING, "No se pudo cargar el recurso con id "+recursoId+".", ex);
                }
            }
            if(recursos.isEmpty()) {
                addErrorMessage(sessionMBean.getTextos().get("debe_seleccionar_al_menos_un_recurso"), MSG_ID);
                return;
            }
            llamadorSessionMBean.setRecursos(recursos);
            llamadorSessionMBean.setLlamadas(new ArrayList());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + URL_BASE_TO_FORWARD_LLAMADOR_EXTERNO);
        }catch(Exception ex) {
            LOGGER.log(Level.SEVERE, "No se pudo abrir el llamador.", ex);
            addErrorMessage(sessionMBean.getTextos().get("no_se_pudo_abrir_el_llamador"), MSG_ID);
        }
    }

    public void abrirLlamadorInterno() {
        if(recursosSeleccionados.length==0) {
            addErrorMessage(sessionMBean.getTextos().get("debe_seleccionar_al_menos_un_recurso"), MSG_ID);
            return;
        }
        try {
            String[] rolesRequeridos = new String[]{"RA_AE_LLAMADOR", "RA_AE_ADMINISTRADOR_DE_RECURSOS","RA_AE_ADMINISTRADOR"};
            List<Recurso> recursos = new ArrayList<>();
            for(Integer recursoId : recursosSeleccionados) {
                try {
                    Recurso recurso = recursosEJB.obtenerRecursoPorId(recursoId);
                    if(recurso!=null && sessionMBean.tieneRoles(recurso, rolesRequeridos)) {
                        recursos.add(recurso);
                    }
                }catch(Exception ex) {
                    LOGGER.log(Level.WARNING, "No se pudo cargar el recurso con id "+recursoId+".", ex);
                }
            }
            if(recursos.isEmpty()) {
                addErrorMessage(sessionMBean.getTextos().get("debe_seleccionar_al_menos_un_recurso"), MSG_ID);
                return;
            }
            llamadorSessionMBean.setRecursos(recursos);
            llamadorSessionMBean.setLlamadas(new ArrayList());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + URL_BASE_TO_FORWARD_LLAMADOR_INTERNO);
        }catch(Exception ex) {
            LOGGER.log(Level.SEVERE, "No se pudo abrir el llamador.", ex);
            addErrorMessage(sessionMBean.getTextos().get("no_se_pudo_abrir_el_llamador"), MSG_ID);
        }
    }
    
    /*
     * Retorna la lista de monitores para configurar el llamador
     */
    public List<SelectItem> getTiposDeMonitores() {
        List<SelectItem> listaMonitores = new ArrayList<>();
        for (TipoMonitor tipoMonitor : TipoMonitor.values()) {
            listaMonitores.add(new SelectItem(tipoMonitor.getPulgadas(), tipoMonitor.getEtiqueta()));
        }
        return listaMonitores;
    }

    public void cambioTipoMonitor(ValueChangeEvent e) {
        llamadorSessionMBean.setTipoMonitor(TipoMonitor.fromPulgadas(Integer.valueOf(e.getNewValue().toString())));
    }

    public List<LlamadasPorHorario> getLlamadasPorHorario() {
        List<Llamada> llamadas = llamadorSessionMBean.getLlamadas();
        if (llamadas == null) {
            return new ArrayList<>(0);
        }
        Map<String, LlamadasPorHorario> mapLlamadasPorHorario = new HashMap<>();

        DateFormat df = new SimpleDateFormat("HHmm");
        for (Llamada llamada : llamadas) {
            Reserva reserva = llamada.getReserva();
            String horaLLamada;
            if (reserva.getPresencial() != null && reserva.getPresencial()) {
                horaLLamada = "9999";
            } else {
                Disponibilidad disp = reserva.getDisponibilidades().get(0);
                horaLLamada = df.format(disp.getHoraInicio());
            }
            LlamadasPorHorario llamadasPorHorario = mapLlamadasPorHorario.get(horaLLamada);
            if (llamadasPorHorario == null) {
                Disponibilidad disp = reserva.getDisponibilidades().get(0);
                llamadasPorHorario = new LlamadasPorHorario(disp.getHoraInicio(), disp.getPresencial());
                mapLlamadasPorHorario.put(horaLLamada, llamadasPorHorario);
            }
            String puesto = llamada.getPuesto() != null ? llamada.getPuesto().toString() : "";
            String documento = "";
            for (DatoReserva dato : reserva.getDatosReserva()) {
                DatoASolicitar datoSol = dato.getDatoASolicitar();
                if (DatoASolicitar.NUMERO_DOCUMENTO.equalsIgnoreCase(datoSol.getNombre()) && !datoSol.getAgrupacionDato().getBorrarFlag()) {
                    documento = dato.getValor();
                }
            }
            String serie = reserva.getSerie() != null ? reserva.getSerie() : "";
            String numero = reserva.getNumero() != null ? reserva.getNumero().toString() : "";
            Boolean presencial = reserva.getDisponibilidades().get(0).getPresencial();
            String nombreAgenda = llamada.getRecurso()!=null?llamada.getRecurso().getAgenda().getNombre():null;
            String nombreRecurso = llamada.getRecurso()!=null?llamada.getRecurso().getNombre():null;
            LlamadaPorHorario llamada1 = new LlamadaPorHorario(puesto, documento, serie, numero, presencial, nombreAgenda, nombreRecurso);
            llamadasPorHorario.getLlamadas().add(llamada1);
        }

        List<String> horarios = new ArrayList<>();
        horarios.addAll(mapLlamadasPorHorario.keySet());
        Collections.sort(horarios);

        List<LlamadasPorHorario> llamadasPorHorario = new ArrayList<>(llamadas.size());
        for (String horario : horarios) {
            llamadasPorHorario.add(mapLlamadasPorHorario.get(horario));
        }

        return llamadasPorHorario;
    }

    public class LlamadasPorHorario {

        private final Date hora;
        private final Boolean presencial;
        private final List<LlamadaPorHorario> llamadas;

        public LlamadasPorHorario(Date hora, Boolean presencial) {
            this.hora = hora;
            this.presencial = presencial;
            this.llamadas = new ArrayList<>();
        }

        public Date getHora() {
            return this.hora;
        }

        public List<LlamadaPorHorario> getLlamadas() {
            return this.llamadas;
        }

        public Boolean getPresencial() {
            return presencial;
        }

    }

    public class LlamadaPorHorario {

        private final String puesto;
        private final String documento;
        private final String serie;
        private final String numero;
        private final Boolean presencial;
        private final String agenda;
        private final String recurso;

        public LlamadaPorHorario(String puesto, String documento, String serie, String numero, Boolean presencial, String agenda, String recurso) {
            super();
            this.puesto = puesto;
            this.documento = documento;
            this.serie = serie;
            this.numero = numero;
            this.presencial = presencial;
            this.agenda = agenda;
            this.recurso = recurso;
        }

        public String getPuesto() {
            return puesto;
        }

        public String getDocumento() {
            return documento;
        }

        public String getNumero() {
            return numero;
        }

        public String getSerie() {
            return serie;
        }

        public Boolean getPresencial() {
            return presencial;
        }

        public String getAgenda() {
            return agenda;
        }

        public String getRecurso() {
            return recurso;
        }
    }

    private boolean detenerPolling = false;

    public boolean isDetenerPolling() {
        return detenerPolling;
    }

    public List<SelectItem> getRecursosSeleccionables() {
        return recursosSeleccionables;
    }

    public Integer[] getRecursosSeleccionados() {
        return recursosSeleccionados;
    }

    public void setRecursosSeleccionados(Integer[] recursosSeleccionados) {
        this.recursosSeleccionados = recursosSeleccionados;
    }

}
