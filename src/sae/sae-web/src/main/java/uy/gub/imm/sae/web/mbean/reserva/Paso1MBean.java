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
package uy.gub.imm.sae.web.mbean.reserva;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import uy.gub.imm.sae.business.ejb.facade.AgendarReservasLocal;
import uy.gub.imm.sae.business.ejb.facade.DisponibilidadesLocal;
import uy.gub.imm.sae.business.ejb.facade.RecursosLocal;
import uy.gub.imm.sae.common.Utiles;
import uy.gub.imm.sae.common.VentanaDeTiempo;
import uy.gub.imm.sae.common.enumerados.OrigenReserva;
import uy.gub.imm.sae.entity.Agenda;
import uy.gub.imm.sae.entity.Disponibilidad;
import uy.gub.imm.sae.entity.Recurso;
import uy.gub.imm.sae.entity.Reserva;
import uy.gub.imm.sae.entity.TextoAgenda;
import uy.gub.imm.sae.entity.global.Empresa;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import uy.gub.imm.sae.entity.constantes.Constantes;
import uy.gub.imm.sae.login.Utilidades;
import uy.gub.imm.sae.web.common.SofisJSFUtils;

@ManagedBean
@ViewScoped
public class Paso1MBean extends BaseMBean {

    private static final Logger logger = Logger.getLogger(Paso1MBean.class);
    public static final String MSG_ID = "pantalla";

    @EJB private AgendarReservasLocal agendarReservasEJB;
    @EJB private DisponibilidadesLocal disponibilidadEJB;
    @EJB private RecursosLocal recursosEJB;

    @ManagedProperty(value = "#{sesionMBean}")
    private SesionMBean sesionMBean;

    private boolean errorInit = false;

    private List<Recurso> recursos;
    private List<SelectItem> recursosItems;

    // Calendario
    private boolean mostrarCalendarioCompleto = false;
    private Date fechaSeleccionada;
    private Date minDateTime;
    private Date maxDateTime;
    private List<Date> invalidDates = new ArrayList<>();

    // Turnos (botones + combo)
    public static class TurnoDTO {
        private final Disponibilidad disponibilidad;
        private final String label;
        public TurnoDTO(Disponibilidad d, String label) { this.disponibilidad = d; this.label = label; }
        public Disponibilidad getDisponibilidad() { return disponibilidad; }
        public String getLabel() { return label; }
    }

    private List<TurnoDTO> primerasDisponibilidades = new ArrayList<>();
    private List<SelectItem> todasDisponibilidadesItems = new ArrayList<>();
    private Integer disponibilidadSeleccionadaId;
    private Disponibilidad disponibilidadSeleccionada;
    private boolean recursoTieneDisponibilidad = true;

    private List<Disponibilidad> disponibilidadesParaPrimerosTurnos = new ArrayList<>();
    
    public SesionMBean getSesionMBean() { return sesionMBean; }
    public void setSesionMBean(SesionMBean sesionMBean) { this.sesionMBean = sesionMBean; }

    @PostConstruct
    @SuppressWarnings("UseSpecificCatch")
    public void init() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            logger.debug("Parámetros URL: e=[" + request.getParameter("e") + "], a=[" + request.getParameter("a") + "], r=[" + request.getParameter("r") + "]");

            String sEmpresaId = request.getParameter("e");
            String sAgendaId = request.getParameter("a");
            String sRecursoId = request.getParameter("r");
            String sIdioma = request.getParameter("i");
            String sUrl = request.getParameter("u");
            String sParms = request.getParameter("p");
            String sTraza = request.getParameter("t");
            String sTramite = request.getParameter("q");

            // Configurar parámetros opcionales en sesión
            if (sParms != null) sesionMBean.setParmsDatosCiudadano(sParms);
            else sesionMBean.setParmsDatosCiudadano(null);

            if (sUrl != null) sesionMBean.setUrlTramite(sUrl);
            else sesionMBean.setUrlTramite(null);

            if (sTraza != null) sesionMBean.setCodigoTrazabilidadPadre(sTraza);
            else sesionMBean.setCodigoTrazabilidadPadre(null);

            if (sTramite != null) sesionMBean.setCodigoTramite(sTramite);
            else sesionMBean.setCodigoTramite(null);

            // Configurar idioma
            if (sIdioma != null) {
                sesionMBean.setIdiomaActual(sIdioma);
            } else if (request.getLocale() != null) {
                sesionMBean.setIdiomaActual(request.getLocale().getLanguage());
            } else {
                sesionMBean.setIdiomaActual(Locale.getDefault().getLanguage());
            }

            errorInit = false;
            Integer empresaId;
            Integer agendaId;
            Integer recursoId;

            // Validar parámetros obligatorios
            if (sEmpresaId == null || sAgendaId == null) {
                addErrorMessage(sesionMBean.getTextos().get("la_combinacion_de_parametros_especificada_no_es_valida"));
                errorInit = true;
                return;
            }

            // Parsear IDs
            try {
                empresaId = Integer.valueOf(sEmpresaId);
                agendaId = Integer.valueOf(sAgendaId);
                recursoId = (sRecursoId != null) ? Integer.valueOf(sRecursoId) : null;
            } catch (Exception e) {
                addErrorMessage(sesionMBean.getTextos().get("la_combinacion_de_parametros_especificada_no_es_valida"));
                errorInit = true;
                return;
            }

            // Poner en sesión HTTP los datos para la válvula de CDA
            HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            httpSession.setAttribute("e", empresaId.toString());
            httpSession.setAttribute("a", agendaId.toString());
            Map<String, Object> sessionAttrs = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            String remoteUser = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

            // Autenticación con usuario anónimo o IdUruguay
            try {
                String falsoUsuario;
                if (remoteUser == null) {
                    sesionMBean.setUsuarioCda(null);
                    falsoUsuario = "sae" + empresaId;
                } else {
                    if(sessionAttrs.containsKey(Constantes.IDURUGUAY_OPENID_SESION_ATTRIBUTE)){
                        sesionMBean.setUsuarioIduruguay(remoteUser);
                        falsoUsuario = remoteUser;
                    }
                    else if (sessionAttrs.containsKey(Constantes.IDURUGUAY_SAML_SESION_ATTRIBUTE)) {
                        sesionMBean.setUsuarioCda(remoteUser);
                        falsoUsuario = remoteUser;
                    }
                    else {
                        falsoUsuario = "sae" + empresaId;
                        sesionMBean.setUsuarioCda(null);
                    }
                    try {
                        request.logout();
                    } catch (Exception ex) {
                        logger.error("Error al logout", ex);
                    }
                }

                if (falsoUsuario.startsWith("sae")) {
                    Random random = new Random();
                    falsoUsuario = falsoUsuario + "-" + ((new Date()).getTime() + random.nextInt(1000));
                }
                falsoUsuario = falsoUsuario + "/" + empresaId;
                String password = Utilidades.encriptarPassword(falsoUsuario);
                request.login(falsoUsuario, password);
            } catch (Exception ex) {
                logger.error("Error al inicializar usuario", ex);
                addErrorMessage(sesionMBean.getTextos().get("no_se_pudo_registrar_un_usuario_anonimo"));
                errorInit = true;
                return;
            }

            // Recargar textos para el esquema correcto
            sesionMBean.cargarTextos();

            // Cargar empresa
            try {
                Empresa empresa = agendarReservasEJB.obtenerEmpresaPorId(empresaId);
                if (empresa == null || empresa.getFechaBaja() != null) {
                    addErrorMessage(sesionMBean.getTextos().get("la_combinacion_de_parametros_especificada_no_es_valida"));
                    sesionMBean.setEmpresaActual(null);
                    errorInit = true;
                    return;
                } else {
                    sesionMBean.setEmpresaActual(empresa);
                }
            } catch (Exception e) {
                addErrorMessage(sesionMBean.getTextos().get("la_combinacion_de_parametros_especificada_no_es_valida"));
                errorInit = true;
                return;
            }

            // Cargar agenda
            String paginaDeRetorno = request.getParameter("pagina_retorno");
            try {
                sesionMBean.seleccionarAgenda(agendaId);
                sesionMBean.setPaginaDeRetorno(paginaDeRetorno);
            } catch (Exception ae) {
                addErrorMessage(sesionMBean.getTextos().get("la_combinacion_de_parametros_especificada_no_es_valida"));
                errorInit = true;
                return;
            }

            // Construir URL para redirects de IdUruguay
            String url = "/agendarReserva/Paso1.xhtml?e=" + empresaId + "&a=" + agendaId;
            if (recursoId != null) url = url + "&r=" + recursoId;
            if (sIdioma != null) url = url + "&i=" + sIdioma;
            if (sUrl != null) url = url + "&u=" + sUrl;
            if (sParms != null) url = url + "&p=" + sParms;
            if (sTraza != null) url = url + "&t=" + sTraza;
            if (sTramite != null) url = url + "&q=" + sTramite;
            sesionMBean.setUrlPaso1Reserva(url);

            // Verificar si requiere IdUruguay
            if(sesionMBean.getAgenda().getConCda()!=null && sesionMBean.getAgenda().getConCda().equals(Boolean.TRUE)){
                try {
                    Context initContext = new InitialContext();
                    String typeHandler = (String)initContext.lookup("java:global/iduruguay/typeHandlerPub");
                    if(typeHandler.equals(Constantes.OPENID_HANLDER) && !sessionAttrs.containsKey(Constantes.IDURUGUAY_OPENID_SESION_ATTRIBUTE)){
                        httpSession.setAttribute(Constantes.SSO_HOME_PAGE,request.getContextPath() + url);
                        request.logout();
                        FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + SesionMBean.URL_BASE_TO_FORWARD_IDURUGUAY);
                        return;
                    }
                    else if(typeHandler.equals(Constantes.SAML_HANLDER) && !sessionAttrs.containsKey(Constantes.IDURUGUAY_SAML_SESION_ATTRIBUTE)){
                        httpSession.setAttribute(Constantes.SSO_HOME_PAGE,request.getContextPath() + url);
                        request.logout();
                        FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + SesionMBean.URL_BASE_TO_FORWARD_IDURUGUAY);
                        return;
                    }
                } catch (Exception ex) {
                    logger.error("Error verificando IdUruguay", ex);
                }
            }

            Agenda agenda = sesionMBean.getAgenda();
            if (agenda == null) {
                addErrorMessage(sesionMBean.getTextos().get("debe_haber_una_agenda_seleccionada"));
                errorInit = true; return;
            }

            // Recursos
            recursos = agendarReservasEJB.consultarRecursos(agenda);
            if (recursos == null || recursos.isEmpty()) {
                addErrorMessage(sesionMBean.getTextos().get("no_hay_recursos_disponibles_para_la_agenda_seleccionada"));
                errorInit = true; return;
            }

            // Ordenar recursos: primero los que tienen disponibilidad
            Map<Integer, Boolean> recursoConDisponibilidad = new HashMap<>();
            for (Recurso r : recursos) {
                if (!r.getVisibleInternet()) continue;
                try {
                    VentanaDeTiempo ventanaTemp = agendarReservasEJB.obtenerVentanaCalendarioIntranet(r);
                    List<Integer> cupos = agendarReservasEJB.obtenerCuposPorDia(r, ventanaTemp, sesionMBean.getTimeZone());
                    boolean tieneDisp = cupos.stream().anyMatch(c -> c != null && c > 0);
                    recursoConDisponibilidad.put(r.getId(), tieneDisp);
                } catch (Exception e) {
                    recursoConDisponibilidad.put(r.getId(), false);
                }
            }

            recursos.sort((r1, r2) -> {
                boolean disp1 = recursoConDisponibilidad.getOrDefault(r1.getId(), false);
                boolean disp2 = recursoConDisponibilidad.getOrDefault(r2.getId(), false);
                if (disp1 != disp2) return disp2 ? 1 : -1;
                return 0;
            });

            // Buscar recurso por defecto (si viene en URL)
            Recurso recursoDefecto = null;
            if (recursoId != null) {
                for (Recurso r : recursos) {
                    if (r.getVisibleInternet() && r.getId().equals(recursoId)) {
                        recursoDefecto = r;
                        break;
                    }
                }
                if (recursoDefecto == null) {
                    addAdvertenciaMessage(sesionMBean.getTextos().get("el_recurso_especificado_no_es_valido"));
                }
            }

            recursosItems = recursos.stream()
                    .filter(r -> r.getVisibleInternet())
                    .map(r -> {
                        // Construir descripción: Nombre - Dirección
                        String descripcion;
                        boolean tieneNombre = r.getNombre() != null && !r.getNombre().trim().isEmpty();
                        boolean tieneDireccion = r.getDireccion() != null && !r.getDireccion().trim().isEmpty();

                        if (tieneNombre && tieneDireccion) {
                            descripcion = r.getNombre() + " - " + r.getDireccion();
                        } else if (tieneNombre) {
                            descripcion = r.getNombre();
                        } else if (tieneDireccion) {
                            descripcion = r.getDireccion();
                        } else {
                            descripcion = "(Sin información)";
                        }

                        String localidadDepto = direccionCompleta(r);

                        String html = "<span class='fw-semibold'>" + escapeHtml(descripcion) + "</span>"
                                + ((localidadDepto != null && !localidadDepto.isEmpty())
                                ? "<br/><span class='text-muted small d-block radio-sub'>" + escapeHtml(localidadDepto) + "</span>"
                                : "");

                        SelectItem si = new SelectItem(r.getId().toString(), html);
                        si.setEscape(false);
                        return si;
                    })
                    .collect(Collectors.toList());

            // Seleccionar recurso por defecto
            if (!recursos.isEmpty()) {
                if (recursoDefecto != null) {
                    // Recurso vino en URL y es válido
                    sesionMBean.setRecurso(recursoDefecto);
                } else if (sesionMBean.getRecurso() == null) {
                    // No hay recurso seleccionado, usar el primero visible
                    sesionMBean.setRecurso(recursos.stream().filter(r -> r.getVisibleInternet()).findFirst().orElse(recursos.get(0)));
                }
            }

            // Configurar calendario y primer día con cupos
            configurarCalendario(sesionMBean.getRecurso());
            fechaSeleccionada = buscarPrimerDiaConDisponibilidad(sesionMBean.getRecurso());

            // Cargar disponibilidades del día seleccionado
            cargarDisponibilidadesDelDia(sesionMBean.getRecurso(), fechaSeleccionada);

            cargarDisponibilidadesParaPrimerosTurnos(sesionMBean.getRecurso(), fechaSeleccionada);

            // Calcular primeras 3 y combo
            recomputarFusionadasYDerivadas();

        } catch (Exception e) {
            logger.error("Error init Paso1MBean", e);
            errorInit = true;
        }
    }

    public boolean getErrorInit() { return errorInit; }

    public String getAgendaNombre() {
        return sesionMBean.getAgenda() != null ? sesionMBean.getAgenda().getNombre() : null;
    }

    /* ==================== Recursos (radio) ==================== */

    public List<SelectItem> getRecursosItems() { return recursosItems; }

    public String getRecursoId() {
        return sesionMBean.getRecurso() != null ? sesionMBean.getRecurso().getId().toString() : null;
    }
    
    public void setRecursoId(String sRecursoId) {
        if (sRecursoId == null) return;
        Integer id = Integer.valueOf(sRecursoId);
        if (sesionMBean.getRecurso() == null || !sesionMBean.getRecurso().getId().equals(id)) {
            recursos.stream().filter(r -> r.getId().equals(id)).findFirst().ifPresent(sesionMBean::setRecurso);
        }
    }

    public void cambioRecursoAjax(AjaxBehaviorEvent e) {
        try {
            configurarCalendario(sesionMBean.getRecurso());
            fechaSeleccionada = buscarPrimerDiaConDisponibilidad(sesionMBean.getRecurso());
            cargarDisponibilidadesDelDia(sesionMBean.getRecurso(), fechaSeleccionada);
            cargarDisponibilidadesParaPrimerosTurnos(sesionMBean.getRecurso(), fechaSeleccionada);
            disponibilidadSeleccionada = null;
            disponibilidadSeleccionadaId = null;
            mostrarCalendarioCompleto = false;
            recomputarFusionadasYDerivadas();
        } catch (Exception ex) {
            addErrorMessage(ex, MSG_ID);
        }
    }

    /* ==================== Calendario ==================== */

    public boolean isMostrarCalendarioCompleto() { return mostrarCalendarioCompleto; }
    public boolean isRecursoTieneDisponibilidad() { return recursoTieneDisponibilidad; }
    
    public void toggleMostrarCalendario() {
        mostrarCalendarioCompleto = !mostrarCalendarioCompleto;
        if (!mostrarCalendarioCompleto) {
            todasDisponibilidadesItems.clear();
        } else {
            recomputarFusionadasYDerivadas();
        }
    }

    public Date getFechaSeleccionada() { return fechaSeleccionada; }
    public void setFechaSeleccionada(Date fechaSeleccionada) { this.fechaSeleccionada = fechaSeleccionada; }

    public Date getMinDateTime() { return minDateTime; }
    public Date getMaxDateTime() { return maxDateTime; }
    public List<Date> getInvalidDates() { return invalidDates; }

    public void onDateSelect(SelectEvent<Date> event) {
        Date d = event.getObject();
        this.fechaSeleccionada = d;
        sesionMBean.setDiaSeleccionado(d);
        try {
            cargarDisponibilidadesDelDia(sesionMBean.getRecurso(), d);
            disponibilidadSeleccionada = null;
            disponibilidadSeleccionadaId = null;
            // Solo actualizar el combo de horarios, NO los primeros 3 turnos
            actualizarSoloComboHorarios();
        } catch (Exception ex) {
            addErrorMessage(ex, MSG_ID);
        }
    }

    /* ==================== Botones (3 primeros) ==================== */

    public List<TurnoDTO> getPrimerasDisponibilidades() { return primerasDisponibilidades; }

    public void seleccionarDisponibilidad() {
        if (disponibilidadSeleccionada != null) {
            disponibilidadSeleccionadaId = disponibilidadSeleccionada.getId();
            mostrarCalendarioCompleto = false;
        }
    }

    public Disponibilidad getDisponibilidadSeleccionada() { return disponibilidadSeleccionada; }
    public void setDisponibilidadSeleccionada(Disponibilidad d) { this.disponibilidadSeleccionada = d; }

    /* ==================== Combo (todas) ==================== */

    public List<SelectItem> getTodasDisponibilidadesItems() { return todasDisponibilidadesItems; }

    public Integer getDisponibilidadSeleccionadaId() { return disponibilidadSeleccionadaId; }
    public void setDisponibilidadSeleccionadaId(Integer id) {
        this.disponibilidadSeleccionadaId = id;
        if (id == null) { disponibilidadSeleccionada = null; return; }
        Disponibilidad d = buscarDisponibilidadPorIdEnDia(id);
        this.disponibilidadSeleccionada = d;
    }

    /* ==================== Botón siguiente ==================== */

    public String siguientePaso() {
        if (disponibilidadSeleccionada == null || disponibilidadSeleccionada.getCupo() == null || disponibilidadSeleccionada.getCupo() < 1) {
            recursoTieneDisponibilidad = true;
            addErrorMessage(sesionMBean.getTextos().get("debe_seleccionar_un_horario_con_disponibilidades"), MSG_ID);
            return null;
        }
        try {
            String ipOrigen = SofisJSFUtils.obtenerDireccionIPCliente(FacesContext.getCurrentInstance());
            Reserva r = agendarReservasEJB.marcarReserva(disponibilidadSeleccionada, OrigenReserva.WEB, null, ipOrigen);
            sesionMBean.setReserva(r);
            sesionMBean.setDisponibilidad(disponibilidadSeleccionada);
            return "siguientePaso";
        } catch (Exception ex) {
            addErrorMessage(ex, MSG_ID);
            return null;
        }
    }

    /* ==================== Internos ==================== */

    private void configurarCalendario(Recurso recurso) throws Exception {
        VentanaDeTiempo ventana = agendarReservasEJB.obtenerVentanaCalendarioInternet(recurso);
        sesionMBean.setVentanaCalendario(ventana);
        minDateTime = ventana.getFechaInicial();
        maxDateTime = disponibilidadEJB.ultFechaGenerada(recurso);

        if (minDateTime != null && maxDateTime != null && minDateTime.equals(maxDateTime)) {
            Calendar calMin = Calendar.getInstance();
            calMin.setTime(minDateTime);
            calMin.set(Calendar.HOUR_OF_DAY, 0);
            calMin.set(Calendar.MINUTE, 0);
            calMin.set(Calendar.SECOND, 0);
            calMin.set(Calendar.MILLISECOND, 0);
            minDateTime = calMin.getTime();

            Calendar calMax = Calendar.getInstance();
            calMax.setTime(maxDateTime);
            calMax.set(Calendar.HOUR_OF_DAY, 23);
            calMax.set(Calendar.MINUTE, 59);
            calMax.set(Calendar.SECOND, 59);
            calMax.set(Calendar.MILLISECOND, 999);
            maxDateTime = calMax.getTime();
        }

        // Cargar fechas inválidas usando el rango completo del datepicker (minDateTime a maxDateTime)
        VentanaDeTiempo ventanaDatepicker = new VentanaDeTiempo();
        ventanaDatepicker.setFechaInicial(minDateTime);
        ventanaDatepicker.setFechaFinal(maxDateTime != null ? maxDateTime : ventana.getFechaFinal());
        cargarInvalidDates(recurso, ventanaDatepicker);
    }

    private void cargarInvalidDates(Recurso recurso, VentanaDeTiempo ventana) {
        try {
            invalidDates.clear();
            List<Integer> listaCupos = agendarReservasEJB.obtenerCuposPorDia(recurso, ventana, sesionMBean.getTimeZone());
            Calendar cont = Calendar.getInstance();
            cont.setTime(Utiles.time2InicioDelDia(ventana.getFechaInicial()));

            Integer i = 0;
            while (!cont.getTime().after(ventana.getFechaFinal()) && i < listaCupos.size()) {
                if (listaCupos.get(i) == null || listaCupos.get(i) <= 0) {
                    invalidDates.add(cont.getTime());
                }
                cont.add(Calendar.DAY_OF_MONTH, 1);
                i++;
            }
        } catch (Exception e) {
            logger.error("Error al cargar invalidDates", e);
        }
    }

    private Date buscarPrimerDiaConDisponibilidad(Recurso recurso) throws Exception {
        VentanaDeTiempo ventana = sesionMBean.getVentanaCalendario();
        if (ventana == null) ventana = agendarReservasEJB.obtenerVentanaCalendarioInternet(recurso);

        List<Integer> cupos = agendarReservasEJB.obtenerCuposPorDia(recurso, ventana, sesionMBean.getTimeZone());
        Calendar cont = Calendar.getInstance();
        cont.setTime(Utiles.time2InicioDelDia(ventana.getFechaInicial()));
        Date fin = ventana.getFechaFinal();

        int i = 0;
        while (!cont.getTime().after(fin) && i < cupos.size()) {
            Integer c = cupos.get(i);
            if (c != null && c > 0) return cont.getTime();
            cont.add(Calendar.DAY_OF_MONTH, 1);
            i++;
        }
        return ventana.getFechaInicial();
    }

    private void cargarDisponibilidadesDelDia(Recurso recurso, Date dia) throws Exception {
        if (recurso == null || dia == null) {
            sesionMBean.setDisponibilidadesDelDiaMatutina(new uy.gub.imm.sae.web.common.RowList<>(new ArrayList<>()));
            sesionMBean.setDisponibilidadesDelDiaVespertina(new uy.gub.imm.sae.web.common.RowList<>(new ArrayList<>()));
            return;
        }
        VentanaDeTiempo ventDia = new VentanaDeTiempo();
        ventDia.setFechaInicial(Utiles.time2InicioDelDia(dia));
        ventDia.setFechaFinal(Utiles.time2FinDelDia(dia));

        List<Disponibilidad> lista = agendarReservasEJB.obtenerDisponibilidades(recurso, ventDia, sesionMBean.getTimeZone());
        List<Disponibilidad> mat = new ArrayList<>();
        List<Disponibilidad> ves = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        for (Disponibilidad d : lista) {
            if (d == null) continue;
            cal.setTime(d.getHoraInicio());
            if (d.getCupo() != null && d.getCupo() < 0) d.setCupo(0);
            if (cal.get(Calendar.AM_PM) == Calendar.AM) mat.add(d);
            else ves.add(d);
        }
        sesionMBean.setDisponibilidadesDelDiaMatutina(new uy.gub.imm.sae.web.common.RowList<>(mat));
        sesionMBean.setDisponibilidadesDelDiaVespertina(new uy.gub.imm.sae.web.common.RowList<>(ves));
    }

    private void cargarDisponibilidadesParaPrimerosTurnos(Recurso recurso, Date diaInicio) throws Exception {
        disponibilidadesParaPrimerosTurnos.clear();
        if (recurso == null || diaInicio == null) {
            return;
        }

        Calendar calFin = Calendar.getInstance();
        calFin.setTime(diaInicio);
        calFin.add(Calendar.DAY_OF_MONTH, 7);
        Date fechaFinBusqueda = calFin.getTime();

        if (maxDateTime != null && fechaFinBusqueda.after(maxDateTime)) {
            fechaFinBusqueda = maxDateTime;
        }

        VentanaDeTiempo ventAmpliada = new VentanaDeTiempo();
        ventAmpliada.setFechaInicial(Utiles.time2InicioDelDia(diaInicio));
        ventAmpliada.setFechaFinal(Utiles.time2FinDelDia(fechaFinBusqueda));

        List<Disponibilidad> lista = agendarReservasEJB.obtenerDisponibilidades(recurso, ventAmpliada, sesionMBean.getTimeZone());

        for (Disponibilidad d : lista) {
            if (d == null) continue;
            if (d.getCupo() != null && d.getCupo() < 0) d.setCupo(0);
            disponibilidadesParaPrimerosTurnos.add(d);
        }

        disponibilidadesParaPrimerosTurnos.sort(Comparator.comparing(Disponibilidad::getHoraInicio));
    }

    private void recomputarFusionadasYDerivadas() {
        List<Disponibilidad> sinDupAmpliadas = new ArrayList<>();
        Set<Long> seenAmpliadas = new HashSet<>();
        for (Disponibilidad d : disponibilidadesParaPrimerosTurnos) {
            if (d == null || d.getHoraInicio() == null) continue;
            long ts = d.getHoraInicio().getTime();
            if (seenAmpliadas.add(ts)) sinDupAmpliadas.add(d);
        }

        primerasDisponibilidades = sinDupAmpliadas.stream()
                .filter(d -> d.getCupo() != null && d.getCupo() > 0)
                .limit(3)
                .map(this::toTurnoDTO)
                .collect(Collectors.toList());

        List<Disponibilidad> fusionadasDia = new ArrayList<>();
        if (sesionMBean.getDisponibilidadesDelDiaMatutina() != null) {
            for (uy.gub.imm.sae.web.common.Row<Disponibilidad> r : sesionMBean.getDisponibilidadesDelDiaMatutina())
                if (r != null && r.getData() != null) fusionadasDia.add(r.getData());
        }
        if (sesionMBean.getDisponibilidadesDelDiaVespertina() != null) {
            for (uy.gub.imm.sae.web.common.Row<Disponibilidad> r : sesionMBean.getDisponibilidadesDelDiaVespertina())
                if (r != null && r.getData() != null) fusionadasDia.add(r.getData());
        }

        fusionadasDia.removeIf(Objects::isNull);
        fusionadasDia.sort(Comparator.comparing(Disponibilidad::getHoraInicio));
        List<Disponibilidad> sinDupDia = new ArrayList<>();
        Set<Long> seenDia = new HashSet<>();
        for (Disponibilidad d : fusionadasDia) {
            if (d.getHoraInicio() == null) continue;
            long ts = d.getHoraInicio().getTime();
            if (seenDia.add(ts)) sinDupDia.add(d);
        }

        List<Disponibilidad> conCupoDia = sinDupDia.stream()
                .filter(d -> d.getCupo() != null && d.getCupo() > 0)
                .collect(Collectors.toList());

        todasDisponibilidadesItems = conCupoDia.stream()
                .map(this::toSelectItem)
                .collect(Collectors.toList());

        List<Disponibilidad> conCupoAmpliado = sinDupAmpliadas.stream()
                .filter(d -> d.getCupo() != null && d.getCupo() > 0)
                .collect(Collectors.toList());
        recursoTieneDisponibilidad = !conCupoAmpliado.isEmpty();

        if (!conCupoDia.isEmpty() && disponibilidadSeleccionadaId == null) {
            Disponibilidad primera = conCupoDia.get(0);
            disponibilidadSeleccionadaId = primera.getId();
            disponibilidadSeleccionada = primera;
        }
    }

    private void actualizarSoloComboHorarios() {
        List<Disponibilidad> fusionadas = new ArrayList<>();
        if (sesionMBean.getDisponibilidadesDelDiaMatutina() != null) {
            for (uy.gub.imm.sae.web.common.Row<Disponibilidad> r : sesionMBean.getDisponibilidadesDelDiaMatutina())
                if (r != null && r.getData() != null) fusionadas.add(r.getData());
        }
        if (sesionMBean.getDisponibilidadesDelDiaVespertina() != null) {
            for (uy.gub.imm.sae.web.common.Row<Disponibilidad> r : sesionMBean.getDisponibilidadesDelDiaVespertina())
                if (r != null && r.getData() != null) fusionadas.add(r.getData());
        }

        fusionadas.removeIf(Objects::isNull);
        fusionadas.sort(Comparator.comparing(Disponibilidad::getHoraInicio));
        List<Disponibilidad> sinDup = new ArrayList<>();
        Set<Long> seen = new HashSet<>();
        for (Disponibilidad d : fusionadas) {
            if (d.getHoraInicio() == null) continue;
            long ts = d.getHoraInicio().getTime();
            if (seen.add(ts)) sinDup.add(d);
        }

        // Solo actualizar items para combo, NO tocar primerasDisponibilidades
        List<Disponibilidad> conCupo = sinDup.stream()
                .filter(d -> d.getCupo() != null && d.getCupo() > 0)
                .collect(Collectors.toList());

        todasDisponibilidadesItems = conCupo.stream()
                .map(this::toSelectItem)
                .collect(Collectors.toList());

        if (!conCupo.isEmpty() && disponibilidadSeleccionadaId == null) {
            Disponibilidad primera = conCupo.get(0);
            disponibilidadSeleccionadaId = primera.getId();
            disponibilidadSeleccionada = primera;
        }
    }

    private TurnoDTO toTurnoDTO(Disponibilidad d) {
        String label = formatFechaHoraES(d.getHoraInicio());
        return new TurnoDTO(d, label);
    }

    private SelectItem toSelectItem(Disponibilidad d) {
        String label = formatHoraES(d.getHoraInicio());
        return new SelectItem(d.getId(), label);
    }

    private Disponibilidad buscarDisponibilidadPorIdEnDia(Integer id) {
        if (id == null) return null;
        if (sesionMBean.getDisponibilidadesDelDiaMatutina() != null) {
            for (uy.gub.imm.sae.web.common.Row<Disponibilidad> r : sesionMBean.getDisponibilidadesDelDiaMatutina())
                if (r != null && r.getData() != null && id.equals(r.getData().getId())) return r.getData();
        }
        if (sesionMBean.getDisponibilidadesDelDiaVespertina() != null) {
            for (uy.gub.imm.sae.web.common.Row<Disponibilidad> r : sesionMBean.getDisponibilidadesDelDiaVespertina())
                if (r != null && r.getData() != null && id.equals(r.getData().getId())) return r.getData();
        }
        for (Disponibilidad d : disponibilidadesParaPrimerosTurnos) {
            if (d != null && id.equals(d.getId())) return d;
        }
        return null;
    }

    public void beforePhase(PhaseEvent phaseEvent) {
        disableBrowserCache(phaseEvent);
    }

    private String formatFechaHoraES(Date dt) {
        if (dt == null) return "";
        Locale loc = new Locale("es", "UY");
        SimpleDateFormat df = new SimpleDateFormat("EEEE d 'de' MMMM", loc);
        SimpleDateFormat hf = new SimpleDateFormat(sesionMBean.getFormatoHora(), loc);
        if (sesionMBean.getTimeZone() != null) {
            df.setTimeZone(sesionMBean.getTimeZone());
            hf.setTimeZone(sesionMBean.getTimeZone());
        }
        String fecha = df.format(dt);
        if (!fecha.isEmpty()) {
            fecha = Character.toUpperCase(fecha.charAt(0)) + fecha.substring(1);
        }
        return fecha + " - " + hf.format(dt) + " h";
    }

    private String formatHoraES(Date dt) {
        if (dt == null) return "";
        Locale loc = new Locale("es", "UY");
        SimpleDateFormat hf = new SimpleDateFormat(sesionMBean.getFormatoHora(), loc);
        if (sesionMBean.getTimeZone() != null) {
            hf.setTimeZone(sesionMBean.getTimeZone());
        }
        return hf.format(dt) + " h";
    }

    private static String escapeHtml(String s) {
        if (s == null) return "";
        return s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;");
    }

    private String direccionCompleta(Recurso r) {
        if (r == null) return "";
        StringBuilder sb = new StringBuilder();
        if (r.getLocalidad() != null && !r.getLocalidad().isEmpty()) {
            if (sb.length() > 0) sb.append(" - ");
            sb.append(r.getLocalidad());
        }
        if (r.getDepartamento() != null && !r.getDepartamento().isEmpty()) {
            if (sb.length() > 0) sb.append(" - ");
            sb.append(r.getDepartamento());
        }
        return sb.toString();
    }
}
