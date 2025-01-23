package uy.gub.imm.sae.web.mbean.administracion;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.BooleanUtils;

import uy.gub.imm.sae.business.dto.InformacionRequestDTO;
import uy.gub.imm.sae.business.ejb.facade.AgendarReservasLocal;
import uy.gub.imm.sae.business.ejb.facade.DisponibilidadesLocal;
import uy.gub.imm.sae.business.ejb.facade.RecursosLocal;
import uy.gub.imm.sae.common.enumerados.ModoAutocompletado;
import uy.gub.imm.sae.common.enumerados.OrigenReserva;
import uy.gub.imm.sae.entity.Agenda;
import uy.gub.imm.sae.entity.AgrupacionDato;
import uy.gub.imm.sae.entity.DatoASolicitar;
import uy.gub.imm.sae.entity.DatoReserva;
import uy.gub.imm.sae.entity.Disponibilidad;
import uy.gub.imm.sae.entity.ParametrosAutocompletar;
import uy.gub.imm.sae.entity.Recurso;
import uy.gub.imm.sae.entity.Reserva;
import uy.gub.imm.sae.entity.ServicioAutocompletarPorDato;
import uy.gub.imm.sae.entity.ServicioPorRecurso;
import uy.gub.imm.sae.entity.TramiteAgenda;
import uy.gub.imm.sae.entity.global.Empresa;
import uy.gub.imm.sae.exception.AccesoMultipleException;
import uy.gub.imm.sae.exception.ApplicationException;
import uy.gub.imm.sae.exception.AutocompletarException;
import uy.gub.imm.sae.exception.BusinessException;
import uy.gub.imm.sae.exception.ErrorAutocompletarException;
import uy.gub.imm.sae.exception.ErrorValidacionCommitException;
import uy.gub.imm.sae.exception.ErrorValidacionException;
import uy.gub.imm.sae.exception.UserException;
import uy.gub.imm.sae.exception.ValidacionClaveUnicaException;
import uy.gub.imm.sae.exception.ValidacionException;
import uy.gub.imm.sae.exception.ValidacionPorCampoException;
import uy.gub.imm.sae.exception.WarningAutocompletarException;
import uy.gub.imm.sae.web.common.FormularioDinamicoReserva;
import uy.gub.imm.sae.web.common.SelectItemComparator;
import uy.gub.imm.sae.web.common.TicketUtiles;

/**
 * Managed bean para la reserva presencial
 *
 * @author im2716295
 *
 *
 */
@ManagedBean
@ViewScoped
public class AtencionPresencialMBean extends BaseMBean {

    private static final Logger LOGGER = Logger.getLogger(AtencionPresencialMBean.class.getName());
    
    public static final String MSG_ID = "msgAtencionPresencial";

    public static final String FORMULARIO_ID = "datosReserva";
    public static final String DATOS_RESERVA_MBEAN = "datosReservaMBean";

    @EJB
    private AgendarReservasLocal agendarReservasEJB;

    @EJB
    private RecursosLocal recursosEJB;

    @EJB
    private DisponibilidadesLocal disponibilidadesEJB;

    @ManagedProperty(value = "#{sessionMBean}")
    private SessionMBean sessionMBean;

    @ManagedProperty(value = "#{datosReservaMBean}")
    private Map<String, Object> datosReservaMBean;

    private List<SelectItem> agendasDisponibles;
    private Map<Integer, List<SelectItem>> recursosDisponibles; //Id de agenda -> recursos

    private Integer agendaSeleccionadaId;
    private Agenda agendaSeleccionada;
    private Integer recursoSeleccionadoId;
    private Recurso recursoSeleccionado;

    private Map<String, DatoASolicitar> datosASolicitar;
    private boolean admiteAtencionPresencial = false;
    
    private UIComponent campos;
    private FormularioDinamicoReserva formularioDin;

    private Map<String, TramiteAgenda> tramitesAgenda;
    private List<SelectItem> tramites;
    private String tramiteCodigo;
    private String aceptaCondiciones;

    private Reserva reserva;
    private Reserva reservaConfirmada;

    public void beforePhase(PhaseEvent event) {
        disableBrowserCache(event);
        if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            sessionMBean.setPantallaTitulo(sessionMBean.getTextos().get("atencion_presencial"));
        }
    }

    @PostConstruct
    @SuppressWarnings("UseSpecificCatch")
    public void init() {
        try {
           cambioAgendaSeleccionadaInit();
           cambioRecursoSeleccionadoInit();

            for (Agenda agenda : agendarReservasEJB.consultarAgendas()) {
                agendasDisponibles.add(new SelectItem(agenda.getId(), agenda.getNombre()));
                List<SelectItem> recursosDisponibles1 = new ArrayList<>();
                for (Recurso recurso : agendarReservasEJB.consultarRecursos(agenda)) {
                    if (true || BooleanUtils.isTrue(recurso.getPresencialAdmite())) {
                        recursosDisponibles1.add(new SelectItem(recurso.getId(), recurso.getNombre()));
                    }
                }
                Collections.sort(recursosDisponibles1, new SelectItemComparator());
                recursosDisponibles1.add(0, new SelectItem(null, sessionMBean.getTextos().get("seleccionar")));
                recursosDisponibles.put(agenda.getId(), recursosDisponibles1);
            }
            Collections.sort(agendasDisponibles, new SelectItemComparator());
            agendasDisponibles.add(0, new SelectItem(null, sessionMBean.getTextos().get("seleccionar")));
            recursosDisponibles.put(null, Arrays.asList(new SelectItem[]{new SelectItem(null, sessionMBean.getTextos().get("seleccionar"))}));

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error al inicializar", ex);
            addErrorMessage(ex.getMessage());
        }
    }

    public void setAgendarReservasEJB(AgendarReservasLocal agendarReservasEJB) {
        this.agendarReservasEJB = agendarReservasEJB;
    }

    public Reserva getReservaConfirmada() {
        return reservaConfirmada;
    }

    public SessionMBean getSessionMBean() {
        return sessionMBean;
    }

    public void setSessionMBean(SessionMBean sessionMBean) {
        this.sessionMBean = sessionMBean;
    }

    public String getClausulaConsentimiento() {
        Empresa empresa = sessionMBean.getEmpresaActual();
        String texto = sessionMBean.getTextos().get("clausula_de_consentimiento_informado_texto");
        texto = texto.replace("{finalidad}", empresa.getCcFinalidad());
        texto = texto.replace("{responsable}", empresa.getCcResponsable());
        texto = texto.replace("{direccion}", empresa.getCcDireccion());
        return texto;
    }

    public UIComponent getCampos() {
        return campos;
    }

    public Map<String, Object> getDatosReservaMBean() {
        return datosReservaMBean;
    }

    public void setDatosReservaMBean(Map<String, Object> datosReservaMBean) {
        this.datosReservaMBean = datosReservaMBean;
    }

    private Map<String, DatoASolicitar> obtenerCampos(List<AgrupacionDato> agrupaciones) {
        Map<String, DatoASolicitar> camposXnombre = new HashMap<>();
        for (AgrupacionDato agrupacion : agrupaciones) {
            for (DatoASolicitar dato : agrupacion.getDatosASolicitar()) {
                camposXnombre.put(dato.getNombre(), dato);
            }
        }
        return camposXnombre;
    }

    @SuppressWarnings("UseSpecificCatch")
    public String autocompletarCampo() {
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String claves = (String) requestParameterMap.get("paramIdsServicio");
        try {
            List<String> idComponentes = new ArrayList<>();
            for (String nombre : datosReservaMBean.keySet()) {
                idComponentes.add(nombre);
            }
            FormularioDinamicoReserva.desmarcarCampos(idComponentes, campos);
            String[] arrParamIdServicio = claves.split("\\|");
            for (String paramIdServicio : arrParamIdServicio) {
                ServicioPorRecurso sRec = new ServicioPorRecurso();
                sRec.setId(Integer.parseInt(paramIdServicio));
                Map<String, Object> valoresAutocompletar = this.agendarReservasEJB.autocompletarCampo(sRec, datosReservaMBean);
                for (String nombre : valoresAutocompletar.keySet()) {
                    datosReservaMBean.put(nombre, valoresAutocompletar.get(nombre).toString());
                }
            }
        } catch (ErrorAutocompletarException e) {
            List<String> idComponentesError = new ArrayList<>();
            for (int i = 0; i < e.getCantCampos(); i++) {
                idComponentesError.add(e.getNombreCampo(i));
            }
            String mensaje = e.getMensaje(0);
            for (int i = 1; i < e.getCantMensajes(); i++) {
                mensaje += "  |  " + e.getMensaje(i);
            }
            addErrorMessage(mensaje, FORMULARIO_ID);
            FormularioDinamicoReserva.marcarCamposError(idComponentesError, campos);
            return null;
        } catch (WarningAutocompletarException e) {
            List<String> idComponentesError = new ArrayList<>();
            for (int i = 0; i < e.getCantCampos(); i++) {
                idComponentesError.add(e.getNombreCampo(i));
            }
            String mensaje = e.getMensaje(0);
            for (int i = 1; i < e.getCantMensajes(); i++) {
                mensaje += "  |  " + e.getMensaje(i);
            }
            addInfoMessage(mensaje, FORMULARIO_ID);
            FormularioDinamicoReserva.marcarCamposError(idComponentesError, campos);
            return null;
        } catch (AutocompletarException e) {
            // Faltan campos requeridos
            addErrorMessage(e.getMessage(), FORMULARIO_ID);
            List<String> idComponentesError = new ArrayList<>();
            for (int i = 0; i < e.getCantCampos(); i++) {
                idComponentesError.add(e.getNombreCampo(i));
            }
            FormularioDinamicoReserva.marcarCamposError(idComponentesError, campos);
            return null;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error al autocompletar", ex);
            addErrorMessage(ex, FORMULARIO_ID);
            return null;
        }
        return null;
    }

    public String getAceptaCondiciones() {
        return aceptaCondiciones;
    }

    public void setAceptaCondiciones(String aceptaCondiciones) {
        this.aceptaCondiciones = aceptaCondiciones;
    }

    public String getTramiteCodigo() {
        return tramiteCodigo;
    }

    public void setTramiteCodigo(String tramiteCodigo) {
        this.tramiteCodigo = tramiteCodigo;
    }

    public List<SelectItem> getTramites() {
        return tramites;
    }

    public String imprimirTicket() {
        if (reservaConfirmada == null) {
            addErrorMessage(sessionMBean.getTextos().get("debe_confirmar_la_reserva"), MSG_ID);
            return null;
        }
        TicketUtiles ticketUtiles = new TicketUtiles();
        ticketUtiles.generarTicketPresencial(sessionMBean.getEmpresaActual(), agendaSeleccionada, recursoSeleccionado, sessionMBean.getTimeZone(),
                reservaConfirmada, sessionMBean.getFormatoFecha(), sessionMBean.getFormatoHora(), sessionMBean.getTextos(), true);
        return null;
    }

    public String nuevaReserva() {
        aceptaCondiciones = null;
        reservaConfirmada = null;
        tramiteCodigo = null;
        return null;
    }

    public Integer getAgendaSeleccionadaId() {
        return agendaSeleccionadaId;
    }

    public void setAgendaSeleccionadaId(Integer agendaSeleccionadaId) {
        this.agendaSeleccionadaId = agendaSeleccionadaId;
    }

    public Integer getRecursoSeleccionadoId() {
        return recursoSeleccionadoId;
    }

    public void setRecursoSeleccionadoId(Integer recursoSeleccionadoId) {
        this.recursoSeleccionadoId = recursoSeleccionadoId;
    }

    public List<SelectItem> getAgendasDisponibles() {
        return agendasDisponibles;
    }

    public List<SelectItem> getRecursosDisponiblesAgenda() {
        return recursosDisponibles.get(agendaSeleccionadaId);
    }

    public boolean isAdmiteAtencionPresencial() {
        return admiteAtencionPresencial;
    }

    public void determinarSiRecursoAdmiteAtencionPresencial() {
        limpiarMensajesError();
        try {
            admiteAtencionPresencial = false;
            if (agendaSeleccionadaId == null || recursoSeleccionadoId == null ) {
                return;
            }
            if (recursoSeleccionado.getPresencialAdmite() == null || !recursoSeleccionado.getPresencialAdmite()) {
                addErrorMessage(sessionMBean.getTextos().get("el_recurso_no_admite_atencion_presencial"), MSG_ID);
                return;
            }
            //Determinar si el recurso está vigente para el día actual
            Calendar hoy = new GregorianCalendar();
            hoy.add(Calendar.MILLISECOND, sessionMBean.getTimeZone().getOffset(hoy.getTimeInMillis()));
            hoy.set(Calendar.HOUR_OF_DAY, 0);
            hoy.set(Calendar.MINUTE, 0);
            hoy.set(Calendar.SECOND, 0);
            hoy.set(Calendar.MILLISECOND, 0);
            if (recursoSeleccionado.getFechaInicioDisp().after(hoy.getTime()) || recursoSeleccionado.getFechaFinDisp().before(hoy.getTime())) {
                addErrorMessage(sessionMBean.getTextos().get("la_recurso_no_esta_vigente"), MSG_ID);
                return;
            }
            //Determinar si el recurso admite atención presencial para el día actual
            Calendar cal = new GregorianCalendar();
            cal.add(Calendar.MILLISECOND, sessionMBean.getTimeZone().getOffset(cal.getTimeInMillis()));
            int diaSemana = cal.get(Calendar.DAY_OF_WEEK);
            if ((diaSemana == Calendar.MONDAY && (recursoSeleccionado.getPresencialLunes() == null || !recursoSeleccionado.getPresencialLunes()))
                    || (diaSemana == Calendar.TUESDAY && (recursoSeleccionado.getPresencialMartes() == null || !recursoSeleccionado.getPresencialMartes()))
                    || (diaSemana == Calendar.WEDNESDAY && (recursoSeleccionado.getPresencialMiercoles() == null || !recursoSeleccionado.getPresencialMiercoles()))
                    || (diaSemana == Calendar.THURSDAY && (recursoSeleccionado.getPresencialJueves() == null || !recursoSeleccionado.getPresencialJueves()))
                    || (diaSemana == Calendar.FRIDAY && (recursoSeleccionado.getPresencialViernes() == null || !recursoSeleccionado.getPresencialViernes()))
                    || (diaSemana == Calendar.SATURDAY && (recursoSeleccionado.getPresencialSabado() == null || !recursoSeleccionado.getPresencialSabado()))
                    || (diaSemana == Calendar.SUNDAY && (recursoSeleccionado.getPresencialDomingo() == null || !recursoSeleccionado.getPresencialDomingo()))) {
                addErrorMessage(sessionMBean.getTextos().get("el_recurso_no_admite_atencion_presencial_para_hoy"), MSG_ID);
                return;
            }
            Disponibilidad disponibilidad = disponibilidadesEJB.obtenerDisponibilidadPresencial(recursoSeleccionado, sessionMBean.getTimeZone());
            if(disponibilidad == null || disponibilidad.getCupo() <= disponibilidad.getNumerador()) {
                addErrorMessage(sessionMBean.getTextos().get("no_hay_cupos_disponibles_para_hoy"), MSG_ID);
                return;
            }
            admiteAtencionPresencial = true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error al determinar si se admite la reserva presencial", ex);
            return;
        }
    }

    public void cambioAgendaSeleccionadaInit() throws ApplicationException {

        agendasDisponibles = new ArrayList<>();
        recursosDisponibles = new HashMap<>();

        agendaSeleccionada = sessionMBean.getAgendaMarcada();
        if(agendaSeleccionada == null){
            addErrorMessage(sessionMBean.getTextos().get("debe_haber_una_agenda_seleccionada"), MSG_ID);
        }

        else{
            agendaSeleccionadaId = agendaSeleccionada.getId();
            //Cargar los tramites de la agenda
            this.tramiteCodigo = null;
            tramitesAgenda = new HashMap<>();
            tramites = new ArrayList<>();
            List<TramiteAgenda> tramites0 = agendarReservasEJB.consultarTramites(agendaSeleccionada);
            if (tramites0.size() == 1) {
                TramiteAgenda tramite = tramites0.get(0);
                tramiteCodigo = tramite.getTramiteCodigo();
                tramitesAgenda.put(tramiteCodigo, tramite);
            } else {
                tramites.add(new SelectItem("", "Sin especificar"));
                for (TramiteAgenda tramite : tramites0) {
                    tramitesAgenda.put(tramite.getTramiteCodigo(), tramite);
                    tramites.add(new SelectItem(tramite.getTramiteCodigo(), tramite.getTramiteNombre()));
                }
            }

        }

    }

    public void cambioRecursoSeleccionadoInit(){

        recursoSeleccionado = sessionMBean.getRecursoMarcado();
        if (recursoSeleccionado == null) {
            addErrorMessage(sessionMBean.getTextos().get("debe_haber_un_recurso_seleccionado"), MSG_ID);
        }
        else {
            recursoSeleccionadoId = recursoSeleccionado.getId();
        }
        if (recursoSeleccionadoId == null) {
            recursoSeleccionado = null;
        }
        determinarSiRecursoAdmiteAtencionPresencial();

    }
    
    @SuppressWarnings("UseSpecificCatch")
    public void setCampos(UIComponent campos) {
        this.campos = campos;
        try {
            // El chequeo de recurso != null es en caso de un acceso directo a la pagina, es solo para que no salte la excepcion en el log, 
            //pues de todas formas sera redirigido a una pagina de error.
            if (campos.getChildCount() == 0 && recursoSeleccionado != null && admiteAtencionPresencial) {
                List<AgrupacionDato> agrupaciones = recursosEJB.consultarDefinicionDeCampos(recursoSeleccionado, sessionMBean.getTimeZone());
                datosASolicitar = obtenerCampos(agrupaciones);
                formularioDin = new FormularioDinamicoReserva(DATOS_RESERVA_MBEAN, FORMULARIO_ID,
                        FormularioDinamicoReserva.TipoFormulario.EDICION, null, sessionMBean.getFormatoFecha(), new Locale(sessionMBean.getIdiomaActual()));
                HashMap<Integer, HashMap<Integer, ServicioPorRecurso>> serviciosAutocompletar = new HashMap<>();
                List<ServicioPorRecurso> lstServiciosPorRecurso = recursosEJB.consultarServicioAutocompletar(recursoSeleccionado);
                for (ServicioPorRecurso sRec : lstServiciosPorRecurso) {
                    List<ServicioAutocompletarPorDato> lstDatos = sRec.getAutocompletadosPorDato();
                    List<ParametrosAutocompletar> parametros = sRec.getAutocompletado().getParametrosAutocompletados();
                    DatoASolicitar ultimo = null;
                    for (ParametrosAutocompletar param : parametros) {
                        if (ModoAutocompletado.SALIDA.equals(param.getModo())) {
                            for (ServicioAutocompletarPorDato sDato : lstDatos) {
                                if (sDato.getNombreParametro().equals(param.getNombre())) {
                                    if (ultimo == null) {
                                        ultimo = sDato.getDatoASolicitar();
                                    } else if (sDato.getDatoASolicitar().getAgrupacionDato().getOrden() > ultimo.getAgrupacionDato().getOrden()) {
                                        HashMap<Integer, ServicioPorRecurso> auxServiciosRecurso = serviciosAutocompletar.get(ultimo.getId());
                                        if (auxServiciosRecurso.size() > 1) {
                                            auxServiciosRecurso.remove(sRec.getId());
                                        } else {
                                            serviciosAutocompletar.remove(ultimo.getId());
                                        }
                                        ultimo = sDato.getDatoASolicitar();
                                    } else if (sDato.getDatoASolicitar().getAgrupacionDato().getOrden().intValue() == ultimo.getAgrupacionDato().getOrden().intValue()) {
                                        if (sDato.getDatoASolicitar().getFila() > ultimo.getFila()) {
                                            HashMap<Integer, ServicioPorRecurso> auxServiciosRecurso = serviciosAutocompletar.get(ultimo.getId());
                                            if (auxServiciosRecurso.size() > 1) {
                                                auxServiciosRecurso.remove(sRec.getId());
                                            } else {
                                                serviciosAutocompletar.remove(ultimo.getId());
                                            }
                                            ultimo = sDato.getDatoASolicitar();
                                        } else if (sDato.getDatoASolicitar().getFila().intValue() == ultimo.getFila().intValue()) {
                                            if (sDato.getDatoASolicitar().getColumna() > ultimo.getColumna()) {
                                                HashMap<Integer, ServicioPorRecurso> auxServiciosRecurso = serviciosAutocompletar.get(ultimo.getId());
                                                if (auxServiciosRecurso.size() > 1) {
                                                    auxServiciosRecurso.remove(sRec.getId());
                                                } else {
                                                    serviciosAutocompletar.remove(ultimo.getId());
                                                }
                                                ultimo = sDato.getDatoASolicitar();
                                            }
                                        }
                                    }
                                    if (serviciosAutocompletar.containsKey(ultimo.getId())) {
                                        serviciosAutocompletar.get(ultimo.getId()).put(sRec.getId(), sRec);
                                    } else {
                                        HashMap<Integer, ServicioPorRecurso> auxServiciosRecurso = new HashMap<>();
                                        auxServiciosRecurso.put(sRec.getId(), sRec);
                                        serviciosAutocompletar.put(ultimo.getId(), auxServiciosRecurso);
                                    }
                                }
                            }
                        }
                    }
                }
                formularioDin.armarFormulario(agrupaciones, serviciosAutocompletar);
                UIComponent formulario = formularioDin.getComponenteFormulario();
                campos.getChildren().add(formulario);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error al armar el formulario dinámico", ex);
            addErrorMessage(ex, FORMULARIO_ID);
        }
    }

    @SuppressWarnings("UseSpecificCatch")
    public String confirmarReserva() {
        limpiarMensajesError();
        try {
            boolean hayError = false;
            if (this.tramiteCodigo == null || this.tramiteCodigo.isEmpty()) {
                hayError = true;
                addErrorMessage(sessionMBean.getTextos().get("debe_seleccionar_el_tramite"), FORM_ID + ":tramite");
            }
            HtmlPanelGroup clausulaGroup = (HtmlPanelGroup) FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:clausula");
            String clausulaStyleClass = clausulaGroup.getStyleClass();
            if (this.aceptaCondiciones == null || !this.aceptaCondiciones.equals("SI")) {
                hayError = true;
                addErrorMessage(sessionMBean.getTextos().get("debe_aceptar_los_terminos_de_la_clausula_de_consentimiento_informado"), FORM_ID + ":condiciones");
                if (!clausulaStyleClass.contains(FormularioDinamicoReserva.STYLE_CLASS_DATO_CON_ERROR)) {
                    clausulaStyleClass = clausulaStyleClass + " " + FormularioDinamicoReserva.STYLE_CLASS_DATO_CON_ERROR;
                    clausulaGroup.setStyleClass(clausulaStyleClass);
                }
            } else if (clausulaStyleClass.contains(FormularioDinamicoReserva.STYLE_CLASS_DATO_CON_ERROR)) {
                clausulaStyleClass = clausulaStyleClass.replace(FormularioDinamicoReserva.STYLE_CLASS_DATO_CON_ERROR, "");
                clausulaGroup.setStyleClass(clausulaStyleClass);
            }
            List<String> idComponentes = new ArrayList<>();
            Set<DatoReserva> datos = new HashSet<>();
            for (String nombre : datosReservaMBean.keySet()) {
                Object valor = datosReservaMBean.get(nombre);
                idComponentes.add(nombre);
                if (valor != null && !valor.toString().trim().equals("")) {
                    DatoReserva dato = new DatoReserva();
                    dato.setDatoASolicitar(datosASolicitar.get(nombre));
                    dato.setValor(valor.toString());
                    datos.add(dato);
                }
            }
            FormularioDinamicoReserva.desmarcarCampos(idComponentes, campos);
            if (reserva == null) {
                Disponibilidad disponibilidad = disponibilidadesEJB.obtenerDisponibilidadPresencial(recursoSeleccionado, sessionMBean.getTimeZone());
                reserva = agendarReservasEJB.marcarReserva(disponibilidad, OrigenReserva.PRESENCIAL, null, null);
            }
            reserva.setDatosReserva(datos);
            agendarReservasEJB.validarDatosReserva(sessionMBean.getEmpresaActual(), reserva);
            if (hayError) {
                return null;
            }
            reserva.setTramiteCodigo(this.tramiteCodigo);
            reserva.setTramiteNombre(tramitesAgenda.get(this.tramiteCodigo).getTramiteNombre());
            boolean confirmada = false;
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            while (!confirmada) {
                try {
                    Reserva rConfirmada = agendarReservasEJB.confirmarReservaPresencial(sessionMBean.getEmpresaActual(), reserva, new InformacionRequestDTO(request.getScheme(), request.getServerName(), request.getContextPath(), request.getServerPort()));
                    reserva.setSerie(rConfirmada.getSerie());
                    reserva.setNumero(rConfirmada.getNumero());
                    reserva.setCodigoSeguridad(rConfirmada.getCodigoSeguridad());
                    reserva.setTrazabilidadGuid(rConfirmada.getTrazabilidadGuid());
                    confirmada = true;
                } catch (AccesoMultipleException e) {
                    //Reintento hasta tener exito, en algun momento no me va a dar acceso multiple.
                }
            }
            reservaConfirmada = reserva;
            reserva = null;
        } catch (ValidacionPorCampoException e) {
            //Alguno de los campos no tiene el formato esperado
            List<String> idComponentesError = new ArrayList<>();
            for (int i = 0; i < e.getCantCampos(); i++) {
                if (e.getMensaje(i) != null) {
                    DatoASolicitar dato = datosASolicitar.get(e.getNombreCampo(i));
                    String mensaje = sessionMBean.getTextos().get(e.getMensaje(i));
                    if (mensaje == null) {
                        mensaje = sessionMBean.getTextos().get("el_valor_ingresado_no_es_aceptable");
                    }
                    mensaje = mensaje.replace("{campo}", dato.getEtiqueta());
                    addErrorMessage(mensaje, "form1:" + dato.getNombre());
                    idComponentesError.add(e.getNombreCampo(i));
                }
            }
            FormularioDinamicoReserva.marcarCamposError(idComponentesError, campos);
            return null;
        } catch (ErrorValidacionException e) {
            //Algun grupo de campos no es valido según alguna validacion
            List<String> idComponentesError = new ArrayList<>();
            for (int i = 0; i < e.getCantCampos(); i++) {
                idComponentesError.add(e.getNombreCampo(i));
            }
            String mensaje = e.getMensaje(0);
            for (int i = 1; i < e.getCantMensajes(); i++) {
                mensaje += "  |  " + e.getMensaje(i);
            }
            addErrorMessage(mensaje);
            FormularioDinamicoReserva.marcarCamposError(idComponentesError, campos);

            return null;
        } catch (ErrorValidacionCommitException e) {
            //Algun grupo de campos no es valido según alguna validacion
            List<String> idComponentesError = new ArrayList<>();
            for (int i = 0; i < e.getCantCampos(); i++) {
                idComponentesError.add(e.getNombreCampo(i));
            }
            String mensaje = e.getMensaje(0);
            for (int i = 1; i < e.getCantMensajes(); i++) {
                mensaje += "  |  " + e.getMensaje(i);
            }
            addErrorMessage(mensaje);
            FormularioDinamicoReserva.marcarCamposError(idComponentesError, campos);
            return null;
        } catch (ValidacionClaveUnicaException vcuEx) {
            addErrorMessage(vcuEx);
            List<String> idComponentesError = new ArrayList<>();
            for (int i = 0; i < vcuEx.getCantCampos(); i++) {
                idComponentesError.add(vcuEx.getNombreCampo(i));
            }
            FormularioDinamicoReserva.marcarCamposError(idComponentesError, campos);
            return null;
        } catch (ValidacionException e) {
            //Faltan campos requeridos
            List<String> idComponentesError = new ArrayList<>();
            for (int i = 0; i < e.getCantCampos(); i++) {
                DatoASolicitar dato = datosASolicitar.get(e.getNombreCampo(i));
                String mensaje = sessionMBean.getTextos().get("debe_completar_el_campo_campo").replace("{campo}", dato.getEtiqueta());
                addErrorMessage(mensaje, "form1", "form1:" + dato.getNombre());
                idComponentesError.add(e.getNombreCampo(i));
            }
            FormularioDinamicoReserva.marcarCamposError(idComponentesError, campos);
            return null;
        } catch (UserException uEx) {
            addErrorMessage(sessionMBean.getTextos().get(uEx.getCodigoError()));
            return null;
        } catch (BusinessException bEx) {
            LOGGER.log(Level.SEVERE, "Error al confirmar la reserva", bEx);
            //Seguramente esto fue lanzado por una Accion
            addErrorMessage(bEx.getMessage());
            return null;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error al confirmar la reserva", ex);
            addErrorMessage(sessionMBean.getTextos().get("sistema_en_mantenimiento"));
            return null;
        }
        //Blanqueo el formulario de datos de la reserva
        datosReservaMBean.clear();
        return null;
    }
    
}
