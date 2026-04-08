package uy.gub.imm.sae.web.mbean.reserva;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import uy.gub.imm.sae.business.ejb.facade.AgendarReservasLocal;
import uy.gub.imm.sae.business.ejb.facade.ConsultasLocal;
import uy.gub.imm.sae.business.ejb.facade.DisponibilidadesLocal;
import uy.gub.imm.sae.business.ejb.facade.RecursosLocal;
import uy.gub.imm.sae.common.Utiles;
import uy.gub.imm.sae.common.VentanaDeTiempo;
import uy.gub.imm.sae.common.enumerados.Estado;
import uy.gub.imm.sae.entity.AgrupacionDato;
import uy.gub.imm.sae.entity.DatoASolicitar;
import uy.gub.imm.sae.entity.DatoReserva;
import uy.gub.imm.sae.entity.Disponibilidad;
import uy.gub.imm.sae.entity.Recurso;
import uy.gub.imm.sae.entity.Reserva;
import uy.gub.imm.sae.entity.TextoAgenda;
import uy.gub.imm.sae.entity.ValorPosible;
import uy.gub.imm.sae.entity.constantes.Constantes;
import uy.gub.imm.sae.entity.global.Empresa;
import uy.gub.imm.sae.exception.ApplicationException;
import uy.gub.imm.sae.exception.BusinessException;
import uy.gub.imm.sae.login.Utilidades;
import uy.gub.imm.sae.web.common.FormularioDinReservaClient;

@ManagedBean @ViewScoped
public class ModificarPaso1MBean extends BaseMBean {

    private static final Logger LOGGER = Logger.getLogger(ModificarPaso1MBean.class.getName());

    @EJB
    private AgendarReservasLocal agendarReservasEJB;

    @EJB
    private RecursosLocal recursosEJB;

    @EJB
    private ConsultasLocal consultaEJB;

    @EJB
    private DisponibilidadesLocal disponibilidadEJB;

    @ManagedProperty(value="#{sesionMBean}")
    private SesionMBean sesionMBean;
    
    @ManagedProperty(value="#{datosFiltroReservaMBean}")
    private Map<String, Object> datosFiltroReservaMBean;
    
    private UIComponent filtroConsulta;
    private UIComponent campos;
    private Map<String, DatoASolicitar> datosASolicitar;
    
    private boolean errorInit;

    //VALIDAR_DATOS, BUSCAR_RESERVAS, LISTAR_RESERVAS
    private String mostrar = "";

    private List<SelectItem> tiposDocumento = new ArrayList<>();
    private String tipoDocumento;
    private String numeroDocumento;
    private String codigoSeguridad;
    private boolean codigoVerificado = false;

    // Propiedades para calendario y selección de fecha/hora
    private List<Recurso> recursos;
    private List<SelectItem> recursosItems;
    private Integer recursoId;

    private boolean mostrarCalendarioCompleto = false;
    private Date fechaSeleccionada;
    private Date minDateTime;
    private Date maxDateTime;
    private List<Date> invalidDates = new ArrayList<>();

    // Turnos (botones + combo)
    public static class TurnoDTO {
        private final Disponibilidad disponibilidad;
        private final String label;
        public TurnoDTO(Disponibilidad d, String label) {
            this.disponibilidad = d;
            this.label = label;
        }
        public Disponibilidad getDisponibilidad() { return disponibilidad; }
        public String getLabel() { return label; }
    }

    private List<TurnoDTO> primerasDisponibilidades = new ArrayList<>();
    private List<SelectItem> todasDisponibilidadesItems = new ArrayList<>();
    private Integer disponibilidadSeleccionadaId;
    private Disponibilidad disponibilidadSeleccionada;

    @PostConstruct
    @SuppressWarnings("UseSpecificCatch")
    public void init() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            //Estos dos son obligatorios
            String sEmpresaId = request.getParameter("e");
            String sAgendaId = request.getParameter("a");
            //De estos dos, uno debe estar necesariamente
            String sRecursoId = request.getParameter("r");
            String sReservaId = request.getParameter("ri");

            Integer empresaId;
            Integer agendaId;
            Integer recursoId;
            Integer reservaId;

            if (sEmpresaId != null && sAgendaId != null && (sRecursoId != null || sReservaId != null)) {
                try {
                    empresaId = Integer.valueOf(sEmpresaId);
                    agendaId = Integer.valueOf(sAgendaId);
                    if (sReservaId != null) {
                        //Se especificó una reserva por id
                        reservaId = Integer.valueOf(sReservaId);
                        recursoId = null;
                    } else {
                        //No se especificó una reserva pero sí un recurso
                        reservaId = null;
                        recursoId = Integer.valueOf(sRecursoId);
                    }

                    limpiarSession();

                    sesionMBean.setEmpresaId(empresaId);
                    sesionMBean.setAgendaId(agendaId);
                    sesionMBean.setRecursoId(recursoId);
                    sesionMBean.setReservaId(reservaId);

                } catch (Exception ex) {
                    addErrorMessage(sesionMBean.getTextos().get("la_combinacion_de_parametros_especificada_no_es_valida"));
                    limpiarSession();
                    errorInit = true;
                    return;
                }
            } else {
                //No hay parámetros, pueden estar ya en la sesion
                empresaId = sesionMBean.getEmpresaId();
                agendaId = sesionMBean.getAgendaId();
                reservaId = sesionMBean.getReservaId();
                recursoId = sesionMBean.getRecursoId();

                if (empresaId == null || agendaId == null || (reservaId == null && recursoId == null)) {
                    //Tampoco están en sesión
                    addErrorMessage(sesionMBean.getTextos().get("la_combinacion_de_parametros_especificada_no_es_valida"));
                    limpiarSession();
                    errorInit = true;
                    return;
                }
            }

            //Poner en sesion los datos de la empresa  y la agenda para la válvula de CDA 
            //(necesita estos datos para determinar si la agenda particular requiere o no CDA)
            HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            httpSession.setAttribute("e", empresaId.toString());
            httpSession.setAttribute("a", agendaId.toString());
            Map<String, Object> sessionAttrs = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            String remoteUser = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

            try {
                // Crear un usuario falso temporal
                String falsoUsuario;
                if (remoteUser == null) {
                    //No hay usuario, se crea uno
                    sesionMBean.setUsuarioCda(null);
                    falsoUsuario = "sae" + empresaId;
                } else {
                    //Hay usuario, tres alternativas: es de iduruguay, cda o es local de otra empresa
                    if(sessionAttrs.containsKey(Constantes.IDURUGUAY_OPENID_SESION_ATTRIBUTE)){
                        //hacer otro logeo fake
                        sesionMBean.setUsuarioIduruguay(remoteUser);
                        falsoUsuario = remoteUser;
                    }
                    else if (sessionAttrs.containsKey(Constantes.IDURUGUAY_SAML_SESION_ATTRIBUTE)) {
                        //Es un usuario de CDA
                        sesionMBean.setUsuarioCda(remoteUser);
                        falsoUsuario = remoteUser;
                    } 
                    else {
                        //Es un usuario de otra empresa
                        falsoUsuario = "sae" + empresaId;
                        sesionMBean.setUsuarioCda(null);
                    }
                    //Desloguear al usuario actual (inválido)
                    try {
                        request.logout();
                    } catch (Exception ex) {
                        LOGGER.log(Level.WARNING, "No se pudo hacer logout del usuario para volver a hacer login", ex);
                    }
                }
                
                if (falsoUsuario.startsWith("sae")) {
                    Random random = new Random();
                    falsoUsuario = falsoUsuario + "-" + ((new Date()).getTime() + random.nextInt(1000));
                }
                
                falsoUsuario = falsoUsuario + "/" + empresaId;
                // Autenticarlo
                String password = Utilidades.encriptarPassword(falsoUsuario);
                request.login(falsoUsuario, password);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Error al inicializar", ex);
                throw new ApplicationException(sesionMBean.getTextos().get("no_se_pudo_registrar_un_usuario_anonimo"));
            }

            // Guardar la empresa en la sesion
            try {
                Empresa empresa = agendarReservasEJB.obtenerEmpresaPorId(empresaId);
                sesionMBean.setEmpresaActual(empresa);
                if (empresa == null) {
                    addErrorMessage(sesionMBean.getTextos().get("la_empresa_especificada_no_es_valida"));
                    limpiarSession();
                    errorInit = true;
                    return;
                }
            } catch (Exception e) {
                addErrorMessage(sesionMBean.getTextos().get("la_empresa_especificada_no_es_valida"));
                limpiarSession();
                errorInit = true;
                return;
            }

            //Guardar la agenda
            try {
                sesionMBean.seleccionarAgenda(agendaId);
            } catch (Exception ex) {
                addErrorMessage(sesionMBean.getTextos().get("la_agenda_especificada_no_es_valida"));
                limpiarSession();
                errorInit = true;
            }
            
            //Construir url
            String url = "/modificarReserva/Paso1.xhtml?e=" + empresaId + "&a=" + agendaId;
            if (recursoId != null) {
                url = url + "&r=" + recursoId;
            }
            if (reservaId != null) {
                url = url + "&ri=" + reservaId;
            }
            
            //Si la agenda es con iduruguay, redireccionar para hacer login con IDURUGUAY
            if(sesionMBean.getAgenda().getConCda()!=null){
                if(sesionMBean.getAgenda().getConCda().equals(Boolean.TRUE)){
                    try {
                        Context initContext = new InitialContext();
                        String typeHanlder = (String)initContext.lookup("java:global/iduruguay/typeHandlerPub");
                        if(typeHanlder.equals(Constantes.OPENID_HANLDER) && !sessionAttrs.containsKey(Constantes.IDURUGUAY_OPENID_SESION_ATTRIBUTE)){
                            //guardo en session la homePage
                            httpSession.setAttribute(Constantes.SSO_HOME_PAGE,request.getContextPath() + url);
                            //redirecciono a la página que tiene idUruguay
                            request.logout();
                            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + sesionMBean.URL_BASE_TO_FORWARD_IDURUGUAY);
                            return;
                        }
                        else if(typeHanlder.equals(Constantes.SAML_HANLDER) && !sessionAttrs.containsKey(Constantes.IDURUGUAY_SAML_SESION_ATTRIBUTE)){
                            //guardo en session la homePage
                            httpSession.setAttribute(Constantes.SSO_HOME_PAGE,request.getContextPath() + url);
                            //redirecciono a la página que tiene idUruguay
                            request.logout();
                            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + sesionMBean.URL_BASE_TO_FORWARD_IDURUGUAY);
                            return;
                        }

                    } catch (NamingException | ServletException | IOException ex) {
                        java.util.logging.Logger.getLogger(Paso1MBean.class.getName()).log(Level.SEVERE, null, ex);
                    }    
                }
                
            }

            //Si hay recurso obtenerlo
            if (reservaId != null) {
                //Primero obtener el recurso de la reserva
                Recurso recurso;
                try {
                    recurso = agendarReservasEJB.consultarRecursoPorReservaId(reservaId);
                } catch (Exception ex) {
                    LOGGER.log(Level.WARNING, "Error al obtener el recurso a partir de la reserva", ex);
                    recurso = null;
                }
                if (recurso == null || !sesionMBean.getAgenda().getId().equals(recurso.getAgenda().getId())) {
                    addErrorMessage(sesionMBean.getTextos().get("no_se_encuentra_la_reserva_especificada"));
                    limpiarSession();
                    errorInit = true;
                    return;
                }
                sesionMBean.setRecurso(recurso);

                if (recurso.getCambiosAdmite() == null || !recurso.getCambiosAdmite()) {
                    addErrorMessage(sesionMBean.getTextos().get("el_recurso_no_admite_cambios_de_reservas"));
                    limpiarSession();
                    errorInit = true;
                    return;
                }

                //Obtener la reserva
                Reserva reserva = consultaEJB.consultarReservaId(reservaId, recurso.getId());

                if (reserva == null) {
                    addErrorMessage(sesionMBean.getTextos().get("no_se_encuentra_la_reserva_especificada"));
                    limpiarSession();
                    errorInit = true;
                    return;
                } else if (!reserva.getEstado().toString().equals("R")) {
                    //Determinar si la reserva está en estado Reservada
                    addErrorMessage(sesionMBean.getTextos().get("no_se_encuentra_la_reserva_especificada"));
                    limpiarSession();
                    errorInit = true;
                    return;
                } else {
                    //Determinar si la reserva está vigente
                    Calendar calAhora = new GregorianCalendar();
                    calAhora.add(Calendar.MILLISECOND, sesionMBean.getTimeZone().getOffset(calAhora.getTimeInMillis()));
                    Calendar calReserva = new GregorianCalendar();
                    calReserva.setTime(reserva.getDisponibilidades().get(0).getHoraInicio());
                    if (calReserva.before(calAhora)) {
                        addErrorMessage(sesionMBean.getTextos().get("no_se_encuentra_la_reserva_especificada"));
                        limpiarSession();
                        errorInit = true;
                        return;
                    }

                    if (recurso.getCambiosTiempo() != null && recurso.getCambiosUnidad() != null) {
                        calReserva.add(recurso.getCambiosUnidad(), recurso.getCambiosTiempo() * -1);
                    }
                    if (calReserva.before(calAhora)) {
                        addErrorMessage(sesionMBean.getTextos().get("la_reserva_especificada_ya_no_admite_cambios"));
                        limpiarSession();
                        errorInit = true;
                        return;
                    }
                }

                this.sesionMBean.setReservaModificar1(reserva);

                mostrar = "VALIDAR_DATOS";
            } else if (recursoId != null) {
                List<Recurso> recursos = agendarReservasEJB.consultarRecursos(sesionMBean.getAgenda());
                for (Recurso recurso0 : recursos) {
                    if (recurso0.getId().equals(recursoId)) {
                        sesionMBean.setRecurso(recurso0);
                        break;
                    }
                }
                if (sesionMBean.getRecurso() == null) {
                    addErrorMessage(sesionMBean.getTextos().get("el_recurso_especificado_no_es_valido"));
                    limpiarSession();
                    errorInit = true;
                    return;
                }
                this.sesionMBean.setRenderedVolverBotom(true);

                mostrar = "BUSCAR_RESERVAS";
            }

            try {
                // guardo en session los datos a solicitar del recurso
                List<DatoASolicitar> listaDatoSolicitar = recursosEJB.consultarDatosSolicitar(sesionMBean.getRecurso());
                Map<String, DatoASolicitar> datoSolicMap = new HashMap<>();
                for (DatoASolicitar dato : listaDatoSolicitar) {
                    datoSolicMap.put(dato.getNombre(), dato);
                }
                setDatosASolicitar(datoSolicMap);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Error al inicializar", ex);
                addErrorMessage(sesionMBean.getTextos().get("el_recurso_especificado_no_es_valido"));
                limpiarSession();
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error al inicializar", ex);
            redirect(ERROR_PAGE_OUTCOME);
        }
    }

    public SesionMBean getSesionMBean() {
        return sesionMBean;
    }

    public void setSesionMBean(SesionMBean sesionMBean) {
        this.sesionMBean = sesionMBean;
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
            if (descripcion != null
                    && !descripcion.equals(recurso.getDireccion())) {
                descripcion = descripcion + " - " + recurso.getDireccion();
            }
            return descripcion;
        } else {
            return null;
        }
    }

    public UIComponent getFiltroConsulta() {
        return filtroConsulta;
    }

    public void setFiltroConsulta(UIComponent filtroConsulta) {
        if (errorInit || (this.sesionMBean.getReserva() == null && this.sesionMBean.getRecurso() == null)) {
            return;
        }
        this.filtroConsulta = filtroConsulta;
        try {
            if (this.sesionMBean.getReservaId() != null && this.sesionMBean.getEmpresaId() != null) {
                if (this.sesionMBean.getReservaDatos() == null) {
                    Reserva reserva = consultaEJB.consultarReservaId(this.sesionMBean.getReservaId(), sesionMBean.getRecurso().getId());
                    sesionMBean.setReservaDatos(reserva);
                }
                tiposDocumento = new ArrayList<>();
                List<DatoASolicitar> datos = recursosEJB.consultarDatosSolicitar(sesionMBean.getRecurso());
                if (datos != null) {
                    for (DatoASolicitar dato : datos) {
                        if (!dato.getAgrupacionDato().getBorrarFlag() && dato.getNombre().equals(DatoASolicitar.TIPO_DOCUMENTO)) {
                            for (ValorPosible valor : dato.getValoresPosibles()) {
                                tiposDocumento.add(new SelectItem(valor.getValor(), valor.getEtiqueta()));
                            }
                        }
                    }
                }
                tipoDocumento = (String) tiposDocumento.get(0).getValue();
                numeroDocumento = "";
            } else {
                AgrupacionDato agrupacion = null;
                List<AgrupacionDato> agrupaciones = recursosEJB.consultarDefCamposTodos(this.sesionMBean.getRecurso());
                for (AgrupacionDato agrupacionDato : agrupaciones) {
                    if (!agrupacionDato.getBorrarFlag()) {
                        agrupacion = agrupacionDato;
                        break;
                    }
                }
                agrupaciones.clear();
                agrupaciones.add(agrupacion);
                FormularioDinReservaClient.armarFormularioEdicionDinamico(this.sesionMBean.getRecurso(), filtroConsulta, agrupaciones, 
                        sesionMBean.getFormatoFecha(), new Locale(sesionMBean.getIdiomaActual()));
            }
        } catch (Exception e) {
            addErrorMessage(e);
        }
    }

    public void buscarReservaDatos(ActionEvent e) {

        limpiarMensajesError();

        boolean huboError = false;

        if (sesionMBean.getAgenda() == null) {
            huboError = true;
            addErrorMessage(sesionMBean.getTextos().get("debe_especificar_la_agenda."));
        }
        if (sesionMBean.getRecurso() == null) {
            huboError = true;
            addErrorMessage(sesionMBean.getTextos().get("debe_especificar_el_recurso"));
        }

        if (huboError) {
            return;
        }

        try {
            List<DatoReserva> datos;
            if (this.sesionMBean.getReservaId() != null && this.sesionMBean.getEmpresaId() != null) {
                datos = new ArrayList<>();
                datos.addAll(this.sesionMBean.getReservaDatos().getDatosReserva());
            } else {
                datos = FormularioDinReservaClient.obtenerDatosReserva(datosFiltroReservaMBean, datosASolicitar);
            }
            if (datos.size() <= 1) {
                huboError = true;
                addErrorMessage(sesionMBean.getTextos().get("debe_ingresar_al_menos_dos_de_los_datos_solicitados"));
            }
            if (sesionMBean.getCodigoSeguridadReserva().trim().isEmpty()) {
                huboError = true;
                addErrorMessage(sesionMBean.getTextos().get("debe_ingresar_codigo_de_seguridad"));
            }
            if (huboError) {
                return;
            }
            List<Reserva> reservas = (ArrayList<Reserva>) consultaEJB.consultarReservasParaModificarCancelar(datos, sesionMBean.getRecurso(), sesionMBean.getCodigoSeguridadReserva(), sesionMBean.getTimeZone());
            if (reservas.isEmpty()) {
                this.sesionMBean.setListaReservas(new ArrayList<>());
                addErrorMessage(sesionMBean.getTextos().get("los_datos_ingresados_no_son_correctos"));
            } else {
                this.sesionMBean.setListaReservas(reservas);
                mostrar = "LISTAR_RESERVAS";
            }
        } catch (Exception ex) {
            addErrorMessage(ex);
        }

    }

    public Map<String, DatoASolicitar> getDatosASolicitar() {
        return datosASolicitar;
    }

    public void setDatosASolicitar(Map<String, DatoASolicitar> datosASolicitar) {
        this.datosASolicitar = datosASolicitar;
    }

    public String selecReservaEliminar(int rowIndex) {
        Reserva reserva = this.sesionMBean.getListaReservas().get(rowIndex);
        this.sesionMBean.setReservaModificar1(reserva);
        this.sesionMBean.setReservaDatos(reserva);
        mostrar = "VALIDAR_DATOS";
        return null; // Permanecer en la misma página, cambiar solo el estado
    }

    /**
     * Método para seleccionar una reserva y redirigir a Paso2 (calendario)
     */
    public String seleccionarReserva(int rowIndex) {
        Reserva reserva = this.sesionMBean.getListaReservas().get(rowIndex);
        this.sesionMBean.setReservaModificar1(reserva);
        this.sesionMBean.setReservaDatos(reserva);
        return "siguientePaso"; // Redirige a Paso2 (calendario)
    }

    public Map<String, Object> getDatosFiltroReservaMBean() {
        return datosFiltroReservaMBean;
    }

    public void setDatosFiltroReservaMBean(
            Map<String, Object> datosFiltroReservaMBean) {
        this.datosFiltroReservaMBean = datosFiltroReservaMBean;
    }

    public UIComponent getCampos() {
        return campos;
    }

    public void setCampos(UIComponent campos) {
        this.campos = campos;
        try {
            List<AgrupacionDato> agrupaciones = recursosEJB.consultarDefinicionDeCampos(sesionMBean.getRecurso(), sesionMBean.getTimeZone());
            FormularioDinReservaClient.armarFormularioLecturaDinamico(sesionMBean.getRecurso(), this.sesionMBean.getReservaDatos(), this.campos, 
                    agrupaciones, sesionMBean.getFormatoFecha(), new Locale(sesionMBean.getIdiomaActual()));
        } catch (BusinessException be) {
            addErrorMessage(be);
        } catch (Exception e) {
            addErrorMessage(e);
        }
    }

    public void beforePhase(PhaseEvent phaseEvent) {
        disableBrowserCache(phaseEvent);
    }

    public Boolean getConfirmarDeshabilitado() {
        return sesionMBean.getReservaDatos() == null
                || sesionMBean.getReservaDatos().getEstado() == Estado.C
                || sesionMBean.getReservaDatos().getEstado() == Estado.U;
    }

    public String getUrlBuscarReservas() {
        return sesionMBean.getUrlCancelarReserva();
    }

    private void limpiarSession() {
        sesionMBean.setAgenda(null);
        sesionMBean.setListaReservas(null);
        sesionMBean.setReservaDatos(null);
        sesionMBean.setDisponibilidadCancelarReserva(null);
        sesionMBean.setUrlCancelarReserva(null);
        sesionMBean.setEmpresaId(null);
        sesionMBean.setAgendaId(null);
        sesionMBean.setRecursoId(null);
        sesionMBean.setReservaId(null);
        sesionMBean.setCodigoSeguridadReserva(null);
        sesionMBean.setEmpresaActual(null);
    }

    public boolean isErrorInit() {
        return errorInit;
    }

    public String getMostrar() {
        return mostrar;
    }

    public String getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(String codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public List<SelectItem> getTiposDocumento() {
        return tiposDocumento;
    }

    public void setTiposDocumento(List<SelectItem> tiposDocumento) {
        this.tiposDocumento = tiposDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String verificarDatos() {
        limpiarMensajesError();
        if (codigoSeguridad == null || codigoSeguridad.trim().isEmpty()) {
            addErrorMessage(sesionMBean.getTextos().get("el_codigo_de_seguridad_es_obligatorio"));
            return null;
        }
        Reserva reserva = this.sesionMBean.getReservaDatos();
        if (reserva == null || !reserva.getCodigoSeguridad().equals(codigoSeguridad)) {
            addErrorMessage(sesionMBean.getTextos().get("los_datos_ingresados_no_son_correctos"));
            return null;
        }

        // Código verificado correctamente
        codigoVerificado = true;

        // Cargar recursos y disponibilidades
        try {
            cargarRecursos();
            if (sesionMBean.getRecurso() != null) {
                recursoId = sesionMBean.getRecurso().getId();
                cargarDisponibilidades();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al cargar disponibilidades", e);
            addErrorMessage(sesionMBean.getTextos().get("ha_ocurrido_un_error_grave"));
        }

        return null; // Permanecer en el mismo paso para seleccionar fecha/hora
    }

    // Métodos para manejo de recursos
    private void cargarRecursos() {
        try {
            recursos = agendarReservasEJB.consultarRecursos(sesionMBean.getAgenda());
            recursosItems = new ArrayList<>();

            for (Recurso r : recursos) {
                String label = buildRecursoLabel(r);
                recursosItems.add(new SelectItem(r.getId(), label, "", false, false, true));
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al cargar recursos", e);
        }
    }

    private String buildRecursoLabel(Recurso r) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class='recurso-item'>");
        sb.append("<strong>").append(r.getNombre() != null ? r.getNombre() : "").append("</strong>");
        if (r.getDireccion() != null && !r.getDireccion().isEmpty()) {
            sb.append("<br/><small>").append(r.getDireccion()).append("</small>");
        }
        sb.append("</div>");
        return sb.toString();
    }

    public void cambioRecursoAjax() {
        if (recursoId != null) {
            for (Recurso r : recursos) {
                if (r.getId().equals(recursoId)) {
                    sesionMBean.setRecurso(r);
                    break;
                }
            }
            cargarDisponibilidades();
        }
    }

    // Métodos para manejo de disponibilidades
    private void cargarDisponibilidades() {
        try {
            Recurso recurso = sesionMBean.getRecurso();
            if (recurso == null) return;

            configurarCalendario(recurso);
            fechaSeleccionada = buscarPrimerDiaConDisponibilidad(recurso);
            cargarDisponibilidadesDelDia(recurso, fechaSeleccionada);
            recomputarFusionadasYDerivadas();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al cargar disponibilidades", e);
        }
    }

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

        cargarInvalidDates(recurso, ventana);
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
            LOGGER.log(Level.SEVERE, "Error al cargar invalidDates", e);
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

    private void recomputarFusionadasYDerivadas() {
        List<Disponibilidad> fusionadas = new ArrayList<>();
        if (sesionMBean.getDisponibilidadesDelDiaMatutina() != null) {
            for (uy.gub.imm.sae.web.common.Row<Disponibilidad> r : sesionMBean.getDisponibilidadesDelDiaMatutina())
                if (r != null && r.getData() != null) fusionadas.add(r.getData());
        }
        if (sesionMBean.getDisponibilidadesDelDiaVespertina() != null) {
            for (uy.gub.imm.sae.web.common.Row<Disponibilidad> r : sesionMBean.getDisponibilidadesDelDiaVespertina())
                if (r != null && r.getData() != null) fusionadas.add(r.getData());
        }

        // Cargar primeros 3 turnos
        primerasDisponibilidades = new ArrayList<>();
        todasDisponibilidadesItems = new ArrayList<>();

        if (!fusionadas.isEmpty()) {
            SimpleDateFormat sdfFecha = new SimpleDateFormat("EEEE dd 'de' MMMM", new Locale(sesionMBean.getIdiomaActual()));
            SimpleDateFormat sdfHora = new SimpleDateFormat(sesionMBean.getFormatoHora());
            sdfFecha.setTimeZone(sesionMBean.getTimeZone());
            sdfHora.setTimeZone(sesionMBean.getTimeZone());

            int count = 0;
            for (Disponibilidad d : fusionadas) {
                String labelFecha = sdfFecha.format(d.getFecha());
                String labelHora = sdfHora.format(d.getHoraInicio());
                String label = labelFecha + " - " + labelHora;

                if (count < 3) {
                    primerasDisponibilidades.add(new TurnoDTO(d, label));
                }

                todasDisponibilidadesItems.add(new SelectItem(d.getId(), label));
                count++;
            }

            // Seleccionar primer turno por defecto
            if (!primerasDisponibilidades.isEmpty()) {
                disponibilidadSeleccionada = primerasDisponibilidades.get(0).getDisponibilidad();
                disponibilidadSeleccionadaId = disponibilidadSeleccionada.getId();
            }
        }
    }

    public void toggleMostrarCalendario() {
        mostrarCalendarioCompleto = !mostrarCalendarioCompleto;
        if (!mostrarCalendarioCompleto) {
            todasDisponibilidadesItems.clear();
        } else {
            recomputarFusionadasYDerivadas();
        }
    }

    public void onDateSelect(org.primefaces.event.SelectEvent event) {
        Date d = (Date) event.getObject();
        this.fechaSeleccionada = d;
        sesionMBean.setDiaSeleccionado(d);
        try {
            cargarDisponibilidadesDelDia(sesionMBean.getRecurso(), d);
            disponibilidadSeleccionada = null;
            disponibilidadSeleccionadaId = null;
            recomputarFusionadasYDerivadas();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error al actualizar disponibilidades por fecha", ex);
            addErrorMessage(sesionMBean.getTextos().get("ha_ocurrido_un_error_grave"));
        }
    }

    public void seleccionarDisponibilidad() {
        // El actionListener ya setea las propiedades vía f:setPropertyActionListener para los botones
        // Para el combo, el setter de disponibilidadSeleccionadaId busca la disponibilidad
        if (disponibilidadSeleccionada != null) {
            disponibilidadSeleccionadaId = disponibilidadSeleccionada.getId();
            mostrarCalendarioCompleto = false;
        }
    }

    public String siguientePaso() {
        limpiarMensajesError();

        // Validar que se haya seleccionado una disponibilidad
        if (disponibilidadSeleccionada == null || disponibilidadSeleccionada.getCupo() == null || disponibilidadSeleccionada.getCupo() < 1) {
            addErrorMessage(sesionMBean.getTextos().get("debe_seleccionar_un_horario_con_disponibilidades"));
            return null;
        }

        try {
            sesionMBean.setDisponibilidad(disponibilidadSeleccionada);
            sesionMBean.setDiaSeleccionado(disponibilidadSeleccionada.getFecha());
            return "siguientePaso";
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al procesar disponibilidad seleccionada", e);
            addErrorMessage(sesionMBean.getTextos().get("ha_ocurrido_un_error_grave"));
            return null;
        }
    }

    // Getters y setters
    public List<SelectItem> getRecursosItems() {
        return recursosItems;
    }

    public Integer getRecursoId() {
        return recursoId;
    }

    public void setRecursoId(Integer recursoId) {
        this.recursoId = recursoId;
    }

    public boolean isMostrarCalendarioCompleto() {
        return mostrarCalendarioCompleto;
    }

    public void setMostrarCalendarioCompleto(boolean mostrarCalendarioCompleto) {
        this.mostrarCalendarioCompleto = mostrarCalendarioCompleto;
    }

    public Date getFechaSeleccionada() {
        return fechaSeleccionada;
    }

    public void setFechaSeleccionada(Date fechaSeleccionada) {
        this.fechaSeleccionada = fechaSeleccionada;
    }

    public Date getMinDateTime() {
        return minDateTime;
    }

    public Date getMaxDateTime() {
        return maxDateTime;
    }

    public List<Date> getInvalidDates() {
        return invalidDates;
    }

    public List<TurnoDTO> getPrimerasDisponibilidades() {
        return primerasDisponibilidades;
    }

    public List<SelectItem> getTodasDisponibilidadesItems() {
        return todasDisponibilidadesItems;
    }

    public Integer getDisponibilidadSeleccionadaId() {
        return disponibilidadSeleccionadaId;
    }

    public void setDisponibilidadSeleccionadaId(Integer id) {
        this.disponibilidadSeleccionadaId = id;
        if (id == null) {
            disponibilidadSeleccionada = null;
            return;
        }
        Disponibilidad d = buscarDisponibilidadPorIdEnDia(id);
        this.disponibilidadSeleccionada = d;
    }

    public Disponibilidad getDisponibilidadSeleccionada() {
        return disponibilidadSeleccionada;
    }

    public void setDisponibilidadSeleccionada(Disponibilidad disponibilidadSeleccionada) {
        this.disponibilidadSeleccionada = disponibilidadSeleccionada;
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
        return null;
    }

    public boolean isCodigoVerificado() {
        return codigoVerificado;
    }

    @PreDestroy
    public void preDestroy() {
        try {
            this.agendarReservasEJB = null;
            this.campos = null;
            this.consultaEJB = null;
            if (this.datosASolicitar != null) {
                this.datosASolicitar.clear();
            }
            this.datosASolicitar = null;
            if (this.datosFiltroReservaMBean != null) {
                this.datosFiltroReservaMBean.clear();
            }
            this.datosFiltroReservaMBean = null;
            this.filtroConsulta = null;
            this.recursosEJB = null;
            this.sesionMBean = null;
            if (this.tiposDocumento != null) {
                this.tiposDocumento.clear();
            }
            this.tiposDocumento = null;
        } catch (Exception ex) {
            //Nada para hacer
        }
    }

    public Date getFechaOriginal() {
        if (sesionMBean.getReservaModificar1() == null) {
            return null;
        }
        return sesionMBean.getReservaModificar1().getDisponibilidades().get(0).getFecha();
    }

    public Date getHoraOriginal() {
        if (sesionMBean.getReservaModificar1() == null) {
            return null;
        }
        return sesionMBean.getReservaModificar1().getDisponibilidades().get(0).getHoraInicio();
    }

}
