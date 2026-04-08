package uy.gub.imm.sae.web.mbean.reserva;

import org.apache.log4j.Logger;
import uy.gub.imm.sae.business.dto.InformacionRequestDTO;
import uy.gub.imm.sae.business.ejb.facade.AgendarReservasLocal;
import uy.gub.imm.sae.business.ejb.facade.ComunicacionesLocal;
import uy.gub.imm.sae.business.ejb.facade.RecursosLocal;
import uy.gub.imm.sae.common.Utiles;
import uy.gub.imm.sae.common.enumerados.ModoAutocompletado;
import uy.gub.imm.sae.common.enumerados.Tipo;
import uy.gub.imm.sae.entity.*;
import uy.gub.imm.sae.entity.global.Empresa;
import uy.gub.imm.sae.exception.*;
import uy.gub.imm.sae.web.common.FormularioDinamicoReserva;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@ManagedBean
@ViewScoped
public class Paso2MBean extends BaseMBean {

    private static final Logger LOGGER = Logger.getLogger(Paso2MBean.class);

    public static final String FORMULARIO_ID = "datosReserva";
    public static final String DATOS_RESERVA_MBEAN = "datosReservaMBean";

    @EJB
    private AgendarReservasLocal agendarReservasEJB;

    @EJB
    private RecursosLocal recursosEJB;

    @EJB
    private ComunicacionesLocal comunicacionesEJB;

    @ManagedProperty(value = "#{sesionMBean}")
    private SesionMBean sesionMBean;

    @ManagedProperty(value = "#{datosReservaMBean}")
    private Map<String, Object> datosReservaMBean;

    private UIComponent campos;
    private FormularioDinamicoReserva formularioDin;

    private Map<String, TramiteAgenda> tramitesAgenda;
    private List<SelectItem> tramites;
    private String tramiteCodigo;
    private String aceptaCondiciones;

    private boolean yaExisteReservaCamposClave = false;
    private boolean errorInit = false;

    // Captcha
    private String textoIndicativoCaptcha;
    private String textoCaptchaUsuario;

    public void beforePhase(PhaseEvent event) {
        disableBrowserCache(event);
        if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            if (sesionMBean.getReserva() == null) {
                // Se ha apretado el boton de back o algun acceso directo
                FacesContext ctx = FacesContext.getCurrentInstance();
                ctx.getApplication().getNavigationHandler().handleNavigation(ctx, "", "pasoAnterior");
            }
        }
    }

    @PostConstruct
    public void init() {
        errorInit = false;
        if (sesionMBean.getAgenda() == null || sesionMBean.getRecurso() == null) {
            addErrorMessage(sesionMBean.getTextos().get("debe_haber_una_agenda_seleccionada"));
            errorInit = true;
            return;
        }
        try {
            this.tramiteCodigo = null;
            tramitesAgenda = new HashMap<>();
            tramites = new ArrayList<>();
            Reserva reserva = sesionMBean.getReserva();
            if (reserva == null || reserva.getDisponibilidades() == null || reserva.getDisponibilidades().isEmpty()) {
                addErrorMessage(sesionMBean.getTextos().get("debe_seleccionar_un_horario"));
                errorInit = true;
                return;
            }
            Agenda agenda = reserva.getDisponibilidades().get(0).getRecurso().getAgenda();
            List<TramiteAgenda> tramites0 = agendarReservasEJB.consultarTramites(agenda);
            if (tramites0.size() == 1) {
                TramiteAgenda tramite = tramites0.get(0);
                tramiteCodigo = tramite.getTramiteCodigo();
                tramitesAgenda.put(tramiteCodigo, tramite);
            } else if (tramites0.size() > 1) {
                tramites.add(new SelectItem("", "Sin especificar"));
                for (TramiteAgenda tramite : tramites0) {
                    tramitesAgenda.put(tramite.getTramiteCodigo(), tramite);
                    tramites.add(new SelectItem(tramite.getTramiteCodigo(), tramite.getTramiteNombre()));
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Error al inicializar Paso2MBean", ex);
            addErrorMessage(sesionMBean.getTextos().get("ha_ocurrido_un_error"));
            errorInit = true;
        }
    }

    public SesionMBean getSesionMBean() {
        return sesionMBean;
    }

    public void setSesionMBean(SesionMBean sesionMBean) {
        this.sesionMBean = sesionMBean;
    }

    public boolean getErrorInit() {
        return errorInit;
    }

    public String getAgendaNombre() {
        if (sesionMBean.getAgenda() != null) {
            return sesionMBean.getAgenda().getNombre();
        } else {
            return null;
        }
    }

    public String getRecursoDescripcion() {
        Recurso recurso = sesionMBean.getRecurso();
        if (recurso != null) {
            String descripcion = recurso.getNombre();
            if (descripcion != null && !descripcion.equals(recurso.getDireccion())) {
                descripcion = descripcion + " - " + recurso.getDireccion();
            }
            return descripcion;
        } else {
            return null;
        }
    }

    public String getDescripcion() {
        TextoAgenda textoAgenda = getTextoAgenda(sesionMBean.getAgenda(), sesionMBean.getIdiomaActual());
        if (textoAgenda != null) {
            return textoAgenda.getTextoPaso3();
        } else {
            return null;
        }
    }

    public String getDescripcionRecurso() {
        TextoRecurso textoRecurso = getTextoRecurso(sesionMBean.getRecurso(), sesionMBean.getIdiomaActual());
        if (textoRecurso != null) {
            return textoRecurso.getTextoPaso3();
        } else {
            return null;
        }
    }

    public Date getDiaSeleccionado() {
        if (sesionMBean.getDisponibilidad() != null) {
            return sesionMBean.getDisponibilidad().getFecha();
        }
        return sesionMBean.getDiaSeleccionado();
    }

    public Date getHoraSeleccionada() {
        if (sesionMBean.getDisponibilidad() != null) {
            return sesionMBean.getDisponibilidad().getHoraInicio();
        } else {
            return null;
        }
    }

    public String getClausulaConsentimiento() {
        Empresa empresa = sesionMBean.getEmpresaActual();
        String texto = sesionMBean.getTextos().get("clausula_de_consentimiento_informado_texto");
        texto = texto.replace("{finalidad}", empresa.getCcFinalidad());
        texto = texto.replace("{responsable}", empresa.getCcResponsable());
        texto = texto.replace("{direccion}", empresa.getCcDireccion());
        return texto;
    }

    public UIComponent getCampos() {
        return campos;
    }

    public void setCampos(UIComponent campos) {
        this.campos = campos;
        try {
            Recurso recurso = sesionMBean.getRecurso();
            if (campos.getChildCount() == 0 && recurso != null) {
                if (formularioDin == null) {
                    List<AgrupacionDato> agrupaciones = recursosEJB.consultarDefinicionDeCampos(recurso, sesionMBean.getTimeZone());
                    sesionMBean.setDatosASolicitar(obtenerCampos(agrupaciones));
                    formularioDin = new FormularioDinamicoReserva(DATOS_RESERVA_MBEAN, FORMULARIO_ID, FormularioDinamicoReserva.TipoFormulario.EDICION,
                            null, sesionMBean.getFormatoFecha(), new Locale(sesionMBean.getIdiomaActual()));
                    HashMap<Integer, HashMap<Integer, ServicioPorRecurso>> serviciosAutocompletar = new HashMap<>();
                    List<ServicioPorRecurso> lstServiciosPorRecurso = recursosEJB.consultarServicioAutocompletar(recurso);
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
                                        } else if (sDato.getDatoASolicitar().getAgrupacionDato().getOrden().intValue() > ultimo.getAgrupacionDato().getOrden().intValue()) {
                                            HashMap<Integer, ServicioPorRecurso> auxServiciosRecurso = serviciosAutocompletar.get(ultimo.getId());
                                            if (auxServiciosRecurso.size() > 1) {
                                                auxServiciosRecurso.remove(sRec.getId());
                                            } else {
                                                serviciosAutocompletar.remove(ultimo.getId());
                                            }
                                            ultimo = sDato.getDatoASolicitar();
                                        } else if (sDato.getDatoASolicitar().getAgrupacionDato().getOrden().intValue() == ultimo.getAgrupacionDato().getOrden().intValue()) {
                                            if (sDato.getDatoASolicitar().getFila().intValue() > ultimo.getFila().intValue()) {
                                                HashMap<Integer, ServicioPorRecurso> auxServiciosRecurso = serviciosAutocompletar.get(ultimo.getId());
                                                if (auxServiciosRecurso.size() > 1) {
                                                    auxServiciosRecurso.remove(sRec.getId());
                                                } else {
                                                    serviciosAutocompletar.remove(ultimo.getId());
                                                }
                                                ultimo = sDato.getDatoASolicitar();
                                            } else if (sDato.getDatoASolicitar().getFila().intValue() == ultimo.getFila().intValue()) {
                                                if (sDato.getDatoASolicitar().getColumna().intValue() > ultimo.getColumna().intValue()) {
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
                                            HashMap<Integer, ServicioPorRecurso> auxServiciosRecurso = new HashMap<Integer, ServicioPorRecurso>();
                                            auxServiciosRecurso.put(sRec.getId(), sRec);
                                            serviciosAutocompletar.put(ultimo.getId(), auxServiciosRecurso);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    formularioDin.armarFormulario(agrupaciones, serviciosAutocompletar);
                }
                UIComponent formulario = formularioDin.getComponenteFormulario();
                campos.getChildren().add(formulario);
            }
        } catch (Exception e) {
            addErrorMessage(e, FORMULARIO_ID);
        }
    }

    public Map<String, Object> getDatosReservaMBean() {
        return datosReservaMBean;
    }

    public void setDatosReservaMBean(Map<String, Object> datosReservaMBean) {
        this.datosReservaMBean = datosReservaMBean;
    }

    public String confirmarReserva() {
        LOGGER.debug("=== INICIO confirmarReserva ===");
        limpiarMensajesError();
        yaExisteReservaCamposClave = false;
        sesionMBean.setReservaConfirmada(null);
        sesionMBean.setEnvioCorreoReserva(Boolean.TRUE);
        try {
            boolean hayError = false;
            HtmlPanelGroup tramiteGroup = (HtmlPanelGroup) FacesContext.getCurrentInstance().getViewRoot().findComponent("form:formDin:tramiteGroup");
            String tramiteStyleClass = tramiteGroup != null ? (tramiteGroup.getStyleClass() != null ? tramiteGroup.getStyleClass() : "") : "";
            if ((tramites != null && !tramites.isEmpty()) && (this.tramiteCodigo == null || this.tramiteCodigo.isEmpty())) {
                hayError = true;
                addErrorMessage(sesionMBean.getTextos().get("debe_seleccionar_el_tramite"), FORM_ID + ":tramite");
                if (tramiteGroup != null && !tramiteStyleClass.contains(FormularioDinamicoReserva.STYLE_CLASS_DATO_CON_ERROR)) {
                    tramiteStyleClass = tramiteStyleClass + " " + FormularioDinamicoReserva.STYLE_CLASS_DATO_CON_ERROR;
                    tramiteGroup.setStyleClass(tramiteStyleClass);
                }
            } else if (tramiteGroup != null && tramiteStyleClass.contains(FormularioDinamicoReserva.STYLE_CLASS_DATO_CON_ERROR)) {
                tramiteStyleClass = tramiteStyleClass.replace(FormularioDinamicoReserva.STYLE_CLASS_DATO_CON_ERROR, "");
                tramiteGroup.setStyleClass(tramiteStyleClass);
            }
            HtmlPanelGroup clausulaGroup = (HtmlPanelGroup) FacesContext.getCurrentInstance().getViewRoot().findComponent("form:formDin:clausula");
            String clausulaStyleClass = clausulaGroup != null ? clausulaGroup.getStyleClass() : "";
            if (this.aceptaCondiciones == null || !this.aceptaCondiciones.equals("SI")) {
                hayError = true;
                addErrorMessage(sesionMBean.getTextos().get("debe_aceptar_los_terminos_de_la_clausula_de_consentimiento_informado"), FORM_ID + ":condiciones");
                if (clausulaGroup != null && !clausulaStyleClass.contains(FormularioDinamicoReserva.STYLE_CLASS_DATO_CON_ERROR)) {
                    clausulaStyleClass = clausulaStyleClass + " " + FormularioDinamicoReserva.STYLE_CLASS_DATO_CON_ERROR;
                    clausulaGroup.setStyleClass(clausulaStyleClass);
                }
            } else if (clausulaGroup != null && clausulaStyleClass.contains(FormularioDinamicoReserva.STYLE_CLASS_DATO_CON_ERROR)) {
                clausulaStyleClass = clausulaStyleClass.replace(FormularioDinamicoReserva.STYLE_CLASS_DATO_CON_ERROR, "");
                clausulaGroup.setStyleClass(clausulaStyleClass);
            }
            // Validación del captcha
            HtmlPanelGroup captchaGroup = (HtmlPanelGroup) FacesContext.getCurrentInstance().getViewRoot().findComponent("form:formDin:captcha");
            String captchaStyleClass = captchaGroup == null ? "" : (captchaGroup.getStyleClass() == null ? "" : captchaGroup.getStyleClass());
            if (textoCaptchaUsuario == null || textoCaptchaUsuario.trim().isEmpty()) {
                hayError = true;
                addErrorMessage(sesionMBean.getTextos().get("debe_responder_la_pregunta_de_seguridad"), FORM_ID + ":secureText");
                if (captchaGroup != null && !captchaStyleClass.contains(FormularioDinamicoReserva.STYLE_CLASS_DATO_CON_ERROR)) {
                    captchaStyleClass = captchaStyleClass + " " + FormularioDinamicoReserva.STYLE_CLASS_DATO_CON_ERROR;
                    captchaGroup.setStyleClass(captchaStyleClass);
                }
            } else if (!textoCaptchaUsuario.equalsIgnoreCase(sesionMBean.getPaso3Captcha())) {
                hayError = true;
                addErrorMessage(sesionMBean.getTextos().get("la_respuesta_a_la_pregunta_de_seguridad_no_es_correcta"), FORM_ID + ":secureText");
                if (captchaGroup != null && !captchaStyleClass.contains(FormularioDinamicoReserva.STYLE_CLASS_DATO_CON_ERROR)) {
                    captchaStyleClass = captchaStyleClass + " " + FormularioDinamicoReserva.STYLE_CLASS_DATO_CON_ERROR;
                    captchaGroup.setStyleClass(captchaStyleClass);
                }
            } else if (captchaGroup != null && captchaStyleClass.contains(FormularioDinamicoReserva.STYLE_CLASS_DATO_CON_ERROR)) {
                captchaStyleClass = captchaStyleClass.replace(FormularioDinamicoReserva.STYLE_CLASS_DATO_CON_ERROR, "");
                captchaGroup.setStyleClass(captchaStyleClass);
            }
            List<String> idComponentes = new ArrayList<>();
            Set<DatoReserva> datos = new HashSet<>();
            for (String nombre : datosReservaMBean.keySet()) {
                Object valor = datosReservaMBean.get(nombre);
                idComponentes.add(nombre);
                if (valor != null && !valor.toString().trim().equals("")) {
                    DatoReserva dato = new DatoReserva();
                    dato.setDatoASolicitar(sesionMBean.getDatosASolicitar().get(nombre));
                    dato.setValor(valor.toString().trim());
                    datos.add(dato);
                }
            }
            FormularioDinamicoReserva.desmarcarCampos(idComponentes, campos);
            Reserva reserva = sesionMBean.getReserva();
            reserva.setDatosReserva(datos);
            reserva.setOrigen("W");
            agendarReservasEJB.validarDatosReserva(sesionMBean.getEmpresaActual(), reserva);
            if (hayError) {
                return null;
            }
            if (this.tramiteCodigo != null && !this.tramiteCodigo.isEmpty()) {
                reserva.setTramiteCodigo(this.tramiteCodigo);
                reserva.setTramiteNombre(tramitesAgenda.get(this.tramiteCodigo).getTramiteNombre());
            }
            boolean confirmada = false;
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            while (!confirmada) {
                try {
                    Reserva rConfirmada = agendarReservasEJB.confirmarReserva(sesionMBean.getEmpresaActual(), reserva, null, null, true, new InformacionRequestDTO(request.getScheme(), request.getServerName(), request.getContextPath(), request.getServerPort()));
                    reserva.setSerie(rConfirmada.getSerie());
                    reserva.setNumero(rConfirmada.getNumero());
                    reserva.setCodigoSeguridad(rConfirmada.getCodigoSeguridad());
                    reserva.setTrazabilidadGuid(rConfirmada.getTrazabilidadGuid());
                    confirmada = true;
                } catch (AccesoMultipleException e) {
                    // Reintento hasta tener exito
                }
            }

            sesionMBean.setReservaConfirmada(reserva);
            sesionMBean.setReserva(null);
            LOGGER.debug("=== Reserva confirmada ID: " + reserva.getId() + " ===");

            // Enviar el mail de confirmacion
            String linkBase = request.getScheme() + "://" + request.getServerName();
            if ("http".equals(request.getScheme()) && request.getServerPort() != 80 || "https".equals(request.getScheme()) && request.getServerPort() != 443) {
                linkBase = linkBase + ":" + request.getServerPort();
            }
            Recurso recurso = reserva.getDisponibilidades().get(0).getRecurso();
            Agenda agenda = recurso.getAgenda();
            String linkCancelacion = linkBase + "/sae/cancelarReserva/Paso1.xhtml?e=" + sesionMBean.getEmpresaActual().getId() + "&a=" + agenda.getId() + "&ri=" + reserva.getId();
            String linkModificacion = linkBase + "/sae/modificarReserva/Paso1.xhtml?e=" + sesionMBean.getEmpresaActual().getId() + "&a=" + agenda.getId() + "&r=" + recurso.getId() + "&ri=" + reserva.getId();
            comunicacionesEJB.enviarComunicacionesConfirmacion(sesionMBean.getEmpresaActual(), linkCancelacion, linkModificacion, reserva, sesionMBean.getIdiomaActual(),
                    sesionMBean.getFormatoFecha(), sesionMBean.getFormatoHora());
        } catch (ValidacionPorCampoException e) {
            List<String> idComponentesError = new ArrayList<>();
            for (int i = 0; i < e.getCantCampos(); i++) {
                if (e.getMensaje(i) != null) {
                    DatoASolicitar dato = sesionMBean.getDatosASolicitar().get(e.getNombreCampo(i));
                    String mensaje = sesionMBean.getTextos().get(e.getMensaje(i));
                    if (mensaje == null) {
                        mensaje = sesionMBean.getTextos().get("el_valor_ingresado_no_es_aceptable");
                    }
                    mensaje = mensaje.replace("{campo}", dato.getEtiqueta());
                    addErrorMessage(mensaje, "form:" + dato.getNombre());
                    idComponentesError.add(e.getNombreCampo(i));
                }
            }
            FormularioDinamicoReserva.marcarCamposError(idComponentesError, campos);
            return null;
        } catch (ErrorValidacionException e) {
            List<String> idComponentesError = new ArrayList<String>();
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
            List<String> idComponentesError = new ArrayList<String>();
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
            yaExisteReservaCamposClave = true;
            String mensajeReservaPrevia = sesionMBean.getTextos().get("ya_tiene_una_reserva_para_el_dia_seleccionado");
            Recurso recurso = sesionMBean.getRecurso();
            if (recurso.getPeriodoValidacion() != null && recurso.getPeriodoValidacion() > 0) {
                Reserva reserva = sesionMBean.getReserva();
                Date fecha = reserva.getDisponibilidades().get(0).getFecha();
                Date fechaDesde = fecha;
                Date fechaHasta = fecha;
                Integer periodoValidacion = recurso.getPeriodoValidacion();
                if (periodoValidacion == null) {
                    periodoValidacion = 0;
                }
                Calendar cal = new GregorianCalendar();
                cal.setTime(fecha);
                cal.add(Calendar.DATE, -1 * periodoValidacion);
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                fechaDesde = cal.getTime();
                cal.setTime(fecha);
                cal.add(Calendar.DATE, 1 * periodoValidacion);
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.SECOND, 59);
                cal.set(Calendar.MILLISECOND, 999);
                fechaHasta = cal.getTime();
                DateFormat df = new SimpleDateFormat(sesionMBean.getFormatoFecha());
                String periodo = df.format(fechaDesde) + " - " + df.format(fechaHasta);
                mensajeReservaPrevia = sesionMBean.getTextos().get("ya_tiene_una_reserva_para_el_periodo").replace("{periodo}", periodo);
            }
            List<String> idComponentesError = new ArrayList<String>();
            for (int i = 0; i < vcuEx.getCantCampos(); i++) {
                idComponentesError.add(vcuEx.getNombreCampo(i));
            }
            if (tramites != null && tramites.size() > 1) {
                idComponentesError.add("tramite");
            }
            addErrorMessage(mensajeReservaPrevia);
            FormularioDinamicoReserva.marcarCamposError(idComponentesError, campos);
            return null;
        } catch (ValidacionIPException ipEx) {
            String mensaje = sesionMBean.getTextos().get(ipEx.getCodigoError());
            Recurso recurso = sesionMBean.getRecurso();
            if (mensaje != null && recurso != null && recurso.getCantidadPorIP() != null) {
                mensaje = mensaje.replace("{cantidad}", recurso.getCantidadPorIP().toString());
            }
            addErrorMessage(mensaje);
            return null;
        } catch (ValidacionException e) {
            List<String> idComponentesError = new ArrayList<String>();
            for (int i = 0; i < e.getCantCampos(); i++) {
                DatoASolicitar dato = sesionMBean.getDatosASolicitar().get(e.getNombreCampo(i));
                String mensaje = sesionMBean.getTextos().get("debe_completar_el_campo_campo").replace("{campo}", dato.getEtiqueta());
                addErrorMessage(mensaje, "form", "form:" + dato.getNombre());
                idComponentesError.add(e.getNombreCampo(i));
            }
            FormularioDinamicoReserva.marcarCamposError(idComponentesError, campos);
            return null;
        } catch (BusinessException bEx) {
            addErrorMessage(bEx.getMessage());
            bEx.printStackTrace();
            return null;
        } catch (UserException uEx) {
            sesionMBean.setEnvioCorreoReserva(Boolean.FALSE);
            datosReservaMBean.clear();
            return "reservaConfirmada";
        } catch (Exception ex) {
            addErrorMessage(sesionMBean.getTextos().get("sistema_en_mantenimiento"));
            ex.printStackTrace();
            return null;
        } finally {
            if (sesionMBean.getReservaConfirmada() == null) {
                for (String nombre : datosReservaMBean.keySet()) {
                    Object valor = datosReservaMBean.get(nombre);
                    DatoASolicitar datoSol = sesionMBean.getDatosASolicitar().get(nombre);
                }
            }
        }
        datosReservaMBean.clear();
        LOGGER.debug("=== FIN confirmarReserva - retornando reservaConfirmada ===");
        return "reservaConfirmada";
    }

    private Map<String, DatoASolicitar> obtenerCampos(List<AgrupacionDato> agrupaciones) {
        Map<String, DatoASolicitar> camposXnombre = new HashMap<String, DatoASolicitar>();
        for (AgrupacionDato agrupacion : agrupaciones) {
            for (DatoASolicitar dato : agrupacion.getDatosASolicitar()) {
                camposXnombre.put(dato.getNombre(), dato);
            }
        }
        return camposXnombre;
    }

    public String autocompletarCampo() {
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String claves = (String) requestParameterMap.get("paramIdsServicio");
        try {
            List<String> idComponentes = new ArrayList<String>();
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
            List<String> idComponentesError = new ArrayList<String>();
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
            List<String> idComponentesError = new ArrayList<String>();
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
            addErrorMessage(e.getMessage(), FORMULARIO_ID);
            List<String> idComponentesError = new ArrayList<String>();
            for (int i = 0; i < e.getCantCampos(); i++) {
                idComponentesError.add(e.getNombreCampo(i));
            }
            FormularioDinamicoReserva.marcarCamposError(idComponentesError, campos);
            return null;
        } catch (Exception e) {
            addErrorMessage(e, FORMULARIO_ID);
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

    public boolean isYaExisteReservaCamposClave() {
        return yaExisteReservaCamposClave;
    }

    // =========================================================
    // Métodos del Captcha
    public String getTextoIndicativoCaptcha() {
        Random rand = new Random();

        //Elegir una frase de todas las disponibles, si no hay ninguna solo se usa la palabra "test"
        String pregunta = "test";
        String respuesta = "test";
        Map<String, String> preguntasCaptcha = sesionMBean.getPreguntasCaptcha();
        if (preguntasCaptcha != null && !preguntasCaptcha.isEmpty()) {
            String[] preguntas = preguntasCaptcha.keySet().toArray(new String[preguntasCaptcha.size()]);
            int ind = rand.nextInt(preguntas.length);
            pregunta = preguntas[ind];
            respuesta = preguntasCaptcha.get(pregunta);
        }
        textoIndicativoCaptcha = pregunta;
        sesionMBean.setPaso3Captcha(respuesta);
        return textoIndicativoCaptcha;
    }

    public String getTextoCaptchaUsuario() {
        return textoCaptchaUsuario;
    }

    public void setTextoCaptchaUsuario(String textoCaptchaUsuario) {
        this.textoCaptchaUsuario = textoCaptchaUsuario;
    }

    public void recargarCaptcha() {
        sesionMBean.setPaso3Captcha(null);
        HtmlPanelGroup captchaGroup = (HtmlPanelGroup) FacesContext.getCurrentInstance().getViewRoot().findComponent("form:formDin:captcha");
        String captchaStyleClass = captchaGroup.getStyleClass();
        if (captchaStyleClass != null && captchaStyleClass.contains(FormularioDinamicoReserva.STYLE_CLASS_DATO_CON_ERROR)) {
            captchaStyleClass = captchaStyleClass.replace(FormularioDinamicoReserva.STYLE_CLASS_DATO_CON_ERROR, "");
            captchaGroup.setStyleClass(captchaStyleClass);
        }
    }

    /**
     * Método para volver a Paso1 preservando los parámetros URL
     */
    public String pasoAnterior() {
        return sesionMBean.getUrlPaso1Reserva() + "&faces-redirect=true";
    }

}
