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

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import uy.gub.imm.sae.business.ejb.facade.AgendaGeneralLocal;
import uy.gub.imm.sae.business.ejb.facade.AgendasLocal;
import uy.gub.imm.sae.business.ejb.facade.RecursosLocal;
import uy.gub.imm.sae.business.ejb.facade.UsuariosEmpresasLocal;
import uy.gub.imm.sae.entity.Agenda;
import uy.gub.imm.sae.entity.Recurso;
import uy.gub.imm.sae.entity.TramiteAgenda;
import uy.gub.imm.sae.entity.global.Tramite;
import uy.gub.imm.sae.exception.ApplicationException;
import uy.gub.imm.sae.exception.BusinessException;
import uy.gub.imm.sae.exception.UserException;
import uy.gub.imm.sae.web.common.CsvReport;
import uy.gub.imm.sae.web.common.CsvRow;
import uy.gub.imm.sae.web.common.RecursoComparatorNombre;
import uy.gub.imm.sae.web.common.Row;
import uy.gub.imm.sae.web.common.RowList;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean
@ViewScoped
public class AgendaMBean extends BaseMBean {

    private static final Logger LOGGER = Logger.getLogger(AgendaMBean.class.getName());

    public static final String MSG_ID = "pantalla";

    private AgendaGeneralLocal generalEJB;

    @EJB
    private AgendasLocal agendasEJB;

    @EJB
    private UsuariosEmpresasLocal empresasEJB;

    private RecursosLocal recursosEJB;

    @ManagedProperty(value = "#{sessionMBean}")
    private SessionMBean sessionMBean;

    @ManagedProperty(value = "#{agendaSessionMBean}")
    private AgendaSessionMBean agendaSessionMBean;

    @ManagedProperty(value = "#{recursoSessionMBean}")
    private RecursoSessionMBean recursoSessionMBean;

    private Agenda agendaNueva;
    private RowList<Agenda> agendasSeleccion;
    private RowList<Recurso> recursosAgenda;
    private Set<Recurso> recursosMarcados;
    private List<SelectItem> agendasDisponibles;
    private Boolean seleccionarTodos=Boolean.FALSE;
    private DefaultStreamedContent reporte;
    private boolean todosLosRecursos = false;
    private String agendaActualId;

    public SessionMBean getSessionMBean() {
        return sessionMBean;
    }

    public void setSessionMBean(SessionMBean sessionMBean) {
        this.sessionMBean = sessionMBean;
    }

    //Lista de agendas para seleccionar en la eliminacion/modificacion.
    public RowList<Agenda> getAgendasSeleccion() {
        try {
            List<Agenda> entidades = generalEJB.consultarAgendas();
            agendasSeleccion = new RowList<>(entidades);
        } catch (Exception e) {
            addErrorMessage(e, MSG_ID);
        }
        return agendasSeleccion;
    }

    public Agenda getAgendaNueva() {
        if (agendaNueva == null) {
            agendaNueva = new Agenda();
        }
        return agendaNueva;
    }

    //Agenda seleccionada para eliminacion/modificacion
    public Agenda getAgendaSeleccionada() {
        return sessionMBean.getAgendaSeleccionada();
    }

    @PostConstruct
    public void postConstruct() {
        if (sessionMBean.getEmpresaActual() == null) {
            addErrorMessage(sessionMBean.getTextos().get("debe_especificar_la_empresa"));
        }
    }

    @SuppressWarnings("UseSpecificCatch")
    public void crear(ActionEvent e) {
        limpiarMensajesError();
        boolean hayErrores = false;
        Agenda agendaCrear = getAgendaNueva();
        if (agendaCrear.getNombre() == null || agendaCrear.getNombre().trim().equals("")) {
            addErrorMessage(sessionMBean.getTextos().get("el_nombre_de_la_agenda_es_obligatorio"), "form:nombreAgenda");
            hayErrores = true;
        }
        if (agendaCrear.getIdiomas().isEmpty()) {
            addErrorMessage(sessionMBean.getTextos().get("debe_seleccionar_al_menos_un_idioma"), "form:idiomasAgenda");
            hayErrores = true;
        }
        if (agendaCrear.getTramites().isEmpty()) {
            addErrorMessage(sessionMBean.getTextos().get("debe_haber_al_menos_un_tramite"));
            hayErrores = true;
        } else {
            int ind = 0;
            List<String> tramitesUsados = new ArrayList<>(agendaCrear.getTramites().size());
            boolean hayTramitesRepetidos = false;
            for (TramiteAgenda tramite : agendaCrear.getTramites()) {
                if (tramite.getTramiteCodigo() == null || tramite.getTramiteCodigo().trim().isEmpty()) {
                    addErrorMessage(sessionMBean.getTextos().get("el_codigo_y_el_nombre_del_tramite_son_obligatorios"), "form:tramites:" + ind + ":codigoTramite");
                    addErrorMessage("", "form:tramites:codigoTramite");
                    hayErrores = true;
                } else if (tramite.getTramiteNombre() == null || tramite.getTramiteNombre().trim().isEmpty()) {
                    addErrorMessage(sessionMBean.getTextos().get("el_codigo_y_el_nombre_del_tramite_son_obligatorios"), "form:tramites:" + ind + ":codigoTramite");
                    addErrorMessage("", "form:tramites:codigoTramite");
                    hayErrores = true;
                } else {
                    //Esto es necesario porque al utilizar el método addErrorMessage para colorear en rojo el fondo de los campos con error, dado que todas las instancias
                    //del bloque comparten el mismo componente JSF, todas ellas quedan con color rojo, incluso si no tienen error (JSF utiliza el mismo componente para todos los divs).
                    //Esto lo que hace es agendar un código JavaScript que le quita la clase 'form-group-con-error' al componente.
                    String compDomId = "form:tramites:" + ind + ":fgCodigoTramite";
                    RequestContext.getCurrentInstance().execute("document.getElementById('" + compDomId + "').className=document.getElementById('" + compDomId + "').className.replace('form-group-con-error','')");
                    String claveTramite = "[" + tramite.getTramiteCodigo().trim() + "][" + tramite.getTramiteNombre().trim() + "]";
                    claveTramite = claveTramite.toLowerCase();
                    if (tramitesUsados.contains(claveTramite)) {
                        hayTramitesRepetidos = true;
                    } else {
                        tramitesUsados.add(claveTramite);
                    }
                }
                ind++;
            }
            if (hayTramitesRepetidos) {
                addErrorMessage(sessionMBean.getTextos().get("hay_tramites_repetidos"));
                hayErrores = true;
            }
        }
        if (hayErrores) {
            return;
        }
        if (agendaCrear.getDescripcion() == null) {
            agendaCrear.setDescripcion("");
        }
        //Guardar la agenda en la base de datos
        try {
            if (agendasEJB.existeAgendaPorNombre(agendaCrear)) {
                addErrorMessage(sessionMBean.getTextos().get("ya_existe_una_agenda_con_el_nombre_especificado"), "form:nombreAgenda");
                return;
            }
            agendasEJB.crearAgenda(agendaCrear);
            sessionMBean.cargarAgendas();
            agendasSeleccion = null;
            agendaNueva = null;
            agendaSessionMBean.setIdiomasSeleccionados(null);
            addInfoMessage(sessionMBean.getTextos().get("agenda_creada"), MSG_ID);
        } catch (Exception ex) {
            addErrorMessage(ex, MSG_ID);
        }
    }

    @SuppressWarnings("unchecked")
    public void selecAgendaEliminar(Integer agendaId) {
        Agenda agenda = agendasEJB.obtenerAgendaPorId(agendaId);
        sessionMBean.setAgendaSeleccionada(agenda);
    }

    @SuppressWarnings("UseSpecificCatch")
    public void eliminar(ActionEvent event) {
        Agenda agenda = sessionMBean.getAgendaSeleccionada();
        if (agenda != null) {
            try {
                if (sessionMBean.getAgendaMarcada() != null && sessionMBean.getAgendaMarcada().getId().equals(agenda.getId())) {
                    sessionMBean.desseleccionarAgenda();
                }
                agendasEJB.eliminarAgenda(agenda, sessionMBean.getTimeZone(), sessionMBean.getUsuarioActual().getCodigo());
                sessionMBean.cargarAgendas();
                agendasSeleccion = null;
                addInfoMessage(sessionMBean.getTextos().get("agenda_eliminada"), MSG_ID);
            } catch (Exception e) {
                addErrorMessage(e, MSG_ID);
            }
        } else {
            addErrorMessage(sessionMBean.getTextos().get("debe_haber_una_agenda_seleccionada"), MSG_ID);
        }
    }

    @SuppressWarnings("unchecked")
    public String modificar(Integer agendaId) {
        try {
            Agenda agenda = agendasEJB.obtenerAgendaPorId(agendaId);
            if (agenda != null) {
                //Cargar los trámites de la agenda
                List<TramiteAgenda> tramites1 = generalEJB.consultarTramites(agenda);
                agenda.setTramites(tramites1);
                //Cargar los idiomas seleccionados
                sessionMBean.setAgendaSeleccionada(agenda);
                List<String> idiomasSeleccionados = new ArrayList<>();
                if (agenda.getIdiomas() != null) {
                    String[] idiomas = agenda.getIdiomas().split(",");
                    idiomasSeleccionados.addAll(Arrays.asList(idiomas));
                }
                agendaSessionMBean.setIdiomasSeleccionados(idiomasSeleccionados);
                return "modificar";
            } else {
                sessionMBean.setAgendaSeleccionada(null);
                addErrorMessage(sessionMBean.getTextos().get("debe_haber_una_agenda_seleccionada"), MSG_ID);
                return null;
            }
        } catch (ApplicationException aEx) {
            addErrorMessage(aEx.getMessage(), MSG_ID);
            return null;
        }
    }

    @SuppressWarnings("UseSpecificCatch")
    public String guardar() {
        limpiarMensajesError();
        Agenda agendaSeleccionada = sessionMBean.getAgendaSeleccionada();
        if (agendaSeleccionada != null) {
            boolean hayErrores = false;
            if (agendaSeleccionada.getNombre() == null || agendaSeleccionada.getNombre().trim().equals("")) {
                addErrorMessage(sessionMBean.getTextos().get("el_nombre_de_la_agenda_es_obligatorio"), "form:nombreAgenda");
                hayErrores = true;
            }
            if (agendaSeleccionada.getIdiomas().isEmpty()) {
                addErrorMessage(sessionMBean.getTextos().get("debe_seleccionar_al_menos_un_idioma"), "form:idiomasAgenda");
                hayErrores = true;
            }
            if (agendaSeleccionada.getTramites().isEmpty()) {
                addErrorMessage(sessionMBean.getTextos().get("debe_haber_al_menos_un_tramite"));
                hayErrores = true;
            } else {
                int ind = 0;
                List<String> tramitesUsados = new ArrayList<>(agendaSeleccionada.getTramites().size());
                boolean hayTramitesRepetidos = false;
                for (TramiteAgenda tramite : agendaSeleccionada.getTramites()) {
                    if (tramite.getTramiteCodigo() == null || tramite.getTramiteCodigo().trim().isEmpty()) {
                        addErrorMessage(sessionMBean.getTextos().get("el_codigo_y_el_nombre_del_tramite_son_obligatorios"), "form:tramites:" + ind + ":codigoTramite");
                        addErrorMessage("", "form:tramites:codigoTramite");
                        hayErrores = true;
                    } else if (tramite.getTramiteNombre() == null || tramite.getTramiteNombre().trim().isEmpty()) {
                        addErrorMessage(sessionMBean.getTextos().get("el_codigo_y_el_nombre_del_tramite_son_obligatorios"), "form:tramites:" + ind + ":codigoTramite");
                        addErrorMessage("", "form:tramites:codigoTramite");
                        hayErrores = true;
                    } else {
                        //Esto es necesario porque al utilizar el método addErrorMessage para colorear en rojo el fondo de los campos con error, dado que todas las instancias
                        //del bloque comparten el mismo componente JSF, todas ellas quedan con color rojo, incluso si no tienen error (JSF utiliza el mismo componente para todos los divs).
                        //Esto lo que hace es agendar un código JavaScript que le quita la clase 'form-group-con-error' al componente.
                        String compDomId = "form:tramites:" + ind + ":fgCodigoTramite";
                        RequestContext.getCurrentInstance().execute("document.getElementById('" + compDomId + "').className=document.getElementById('" + compDomId + "').className.replace('form-group-con-error','')");
                        String claveTramite = "[" + tramite.getTramiteCodigo().trim() + "][" + tramite.getTramiteNombre().trim() + "]";
                        claveTramite = claveTramite.toLowerCase();
                        if (tramitesUsados.contains(claveTramite)) {
                            hayTramitesRepetidos = true;
                        } else {
                            tramitesUsados.add(claveTramite);
                        }
                    }
                    ind++;
                }
                if (hayTramitesRepetidos) {
                    addErrorMessage(sessionMBean.getTextos().get("hay_tramites_repetidos"));
                    hayErrores = true;
                }
            }
            if (hayErrores) {
                return null;
            }
            if (agendaSeleccionada.getDescripcion() == null) {
                agendaSeleccionada.setDescripcion("");
            }
            try {
                if (agendasEJB.existeAgendaPorNombre(agendaSeleccionada)) {
                    addErrorMessage(sessionMBean.getTextos().get("ya_existe_una_agenda_con_el_nombre_especificado"), "form:nombreAgenda");
                    return null;
                }
                Integer idAgendaSelected = 0;
                Integer idRecursoSelected = 0;
                if (sessionMBean.getAgendaMarcada() != null){
                    idAgendaSelected = sessionMBean.getAgendaMarcada().getId();
                    if (sessionMBean.getRecursoMarcado() != null){
                        idRecursoSelected = sessionMBean.getRecursoMarcado().getId();
                    }
                }


                agendasEJB.modificarAgenda(agendaSeleccionada);
                sessionMBean.cargarAgendas();
                agendasSeleccion = null;
                sessionMBean.setAgendaSeleccionada(null);
                if (idAgendaSelected != 0){
                    for (Row<Agenda> row : sessionMBean.getAgendas()){
                        if(row.getData().getId().equals(idAgendaSelected)){
                            sessionMBean.getAgendas().setSelectedRow(row);
                            break;
                        }
                    }
                }

            	if (sessionMBean.getRowSelectRecurso() != null) {
            		sessionMBean.cargarRecursos();
            	}
                if (idRecursoSelected != 0){
                    for (Row<Recurso> row : sessionMBean.getRecursos()){
                        if(row.getData().getId().equals(idRecursoSelected)){
                            sessionMBean.getRecursos().setSelectedRow(row);
                            break;
                        }
                    }
                }
                addInfoMessage(sessionMBean.getTextos().get("agenda_modificada"), MSG_ID);
                return "guardar";
            } catch (Exception e) {
                addErrorMessage(e, MSG_ID);
            }
        } else {
            addErrorMessage(sessionMBean.getTextos().get("debe_haber_una_agenda_seleccionada"), MSG_ID);
        }
        return null;
    }

    public AgendaSessionMBean getAgendaSessionMBean() {
        return agendaSessionMBean;
    }

    public void setAgendaSessionMBean(AgendaSessionMBean agendaSessionMBean) {
        this.agendaSessionMBean = agendaSessionMBean;
    }

    public void beforePhaseCrear(PhaseEvent event) {
        if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            sessionMBean.setPantallaTitulo(sessionMBean.getTextos().get("crear_agenda"));
        }
    }

    public void beforePhaseModificarConsultar(PhaseEvent event) {
        if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            sessionMBean.setPantallaTitulo(sessionMBean.getTextos().get("consultar_agendas"));
        }
    }

    public void beforePhaseModificar(PhaseEvent event) {
        if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            sessionMBean.setPantallaTitulo(sessionMBean.getTextos().get("modificar_agenda"));
        }
    }

    //=============================================================================
    //Datos de los tramites 
    private Map<String, Tramite> mapTramites = new HashMap<>();
    private List<SelectItem> tramites = new ArrayList<>(0);

    public Map<String, Tramite> getMapTramites() {
        return this.mapTramites;
    }

    public void setTramites(List<Tramite> trams) {
        mapTramites = new HashMap<>();
        tramites = new ArrayList<>(0);
        tramites.add(new SelectItem(0, "Sin especificar"));
        if (trams == null) {
            return;
        }
        tramites = new ArrayList<>(trams.size());
        tramites.add(new SelectItem(0, "Sin especificar"));
        for (Tramite tram : trams) {
            tramites.add(new SelectItem(tram.getId(), tram.getNombre()));
            mapTramites.put(tram.getId(), tram);
        }
    }

    public List<SelectItem> getTramites() {
        if (tramites == null || tramites.isEmpty()) {
            recargarTramites(false);
        }
        return tramites;
    }

    public String recargarTramites(ActionEvent event) {
        recargarTramites(true);
        return null;
    }

    @SuppressWarnings("UseSpecificCatch")
    private void recargarTramites(boolean actualizar) {
        try {
            if (sessionMBean.getEmpresaActual() == null) {
                setTramites(null);
                return;
            }
            Integer empresaId = sessionMBean.getEmpresaActual().getId();
            List<Tramite> trams = empresasEJB.obtenerTramitesEmpresa(empresaId, actualizar);
            setTramites(trams);
            String msg = sessionMBean.getTextos().get("se_cargaron_n_tramites");
            if (msg != null) {
                addInfoMessage(msg.replace("{cant}", "" + (trams == null ? "0" : "" + trams.size())));
            }
        } catch (UserException ex) {
            LOGGER.log(Level.SEVERE, "Error al recargar los trámites", ex);
            addErrorMessage(ex);
            setTramites(null);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error al recargar los trámites", ex);
            addErrorMessage(sessionMBean.getTextos().get("no_se_pudo_cargar_tramites"));
            setTramites(null);
        }
    }

    public List<SelectItem> getTimezones() {
        String[] ids = TimeZone.getAvailableIDs();
        List<SelectItem> timezones = new ArrayList<>();
        timezones.add(new SelectItem("", sessionMBean.getTextos().get("misma_que_la_de_la_empresa")));
        for (String id : ids) {
            TimeZone timezone = TimeZone.getTimeZone(id);
            long hours = TimeUnit.MILLISECONDS.toHours(timezone.getRawOffset());
            long minutes = TimeUnit.MILLISECONDS.toMinutes(timezone.getRawOffset()) - TimeUnit.HOURS.toMinutes(hours);
            timezones.add(new SelectItem(id, id + " (GMT " + (hours > 0 ? "+" : "") + hours + ":" + (minutes < 10 ? "0" : "") + minutes + ")"));
        }
        return timezones;
    }

    @SuppressWarnings({"unchecked", "UseSpecificCatch"})
    public void copiar(Integer agendaId) {
        Agenda agenda = agendasEJB.obtenerAgendaPorId(agendaId);
        if (agenda != null) {
            try {
                agendasEJB.copiarAgenda(agenda);
                sessionMBean.cargarAgendas();
                addInfoMessage(sessionMBean.getTextos().get("agenda_copiada"), MSG_ID);
            } catch (Exception e) {
                addErrorMessage(e, MSG_ID);
            }
        }
    }

    public List<SelectItem> getIdiomasDisponibles() {
        return sessionMBean.getIdiomasSoportados();
    }

    public List<String> getIdiomasSeleccionados() {
        if (getAgendaSeleccionada() != null && getAgendaSeleccionada().getIdiomas() != null) {
            return Arrays.asList(getAgendaSeleccionada().getIdiomas().split(","));
        }
        return agendaSessionMBean.getIdiomasSeleccionados();
    }

    public void setIdiomasSeleccionados(List<String> idiomasSeleccionados) {
        agendaSessionMBean.setIdiomasSeleccionados(idiomasSeleccionados);
    }

    public void agregarTramite() {
        limpiarMensajesError();
        TramiteAgenda tramite = new TramiteAgenda();
        tramite.setAgenda(this.agendaNueva);
        this.agendaNueva.getTramites().add(tramite);
    }

    public void cambioTramite(ValueChangeEvent event) {
        limpiarMensajesError();
        UIInput input = (UIInput) event.getComponent();
        TramiteAgenda tramiteAgenda = (TramiteAgenda) input.getAttributes().get("tramiteAgenda");
        String tramIdViejo = (String) event.getOldValue();
        String tramId = (String) event.getNewValue();
        //Si el valor nuevo es "0" y el viejo no está en la lista de trámites el cambio es porque
        //cambió la lista de trámites y no puede seleccionar el trámite correspondiente. En este
        //caso se cambia el id del trámite pero no el código y nombre
        if ("0".equals(tramId) && !mapTramites.containsKey(tramIdViejo)) {
            tramiteAgenda.setTramiteId(null);
        } else if (tramId == null || !mapTramites.containsKey(tramId)) {
            tramiteAgenda.setTramiteId(null);
            tramiteAgenda.setTramiteCodigo("");
            tramiteAgenda.setTramiteNombre("");
        } else {
            Tramite tramite = mapTramites.get(tramId);
            tramiteAgenda.setTramiteId(tramId);
            tramiteAgenda.setTramiteNombre(tramite.getNombre());
            String[] partes = tramId.split("-");
            if (partes.length > 1) {
                tramiteAgenda.setTramiteCodigo(partes[1]);
            }
        }
    }

    public void quitarTramite(Integer ordinal) {
        limpiarMensajesError();
        TramiteAgenda ta = this.agendaNueva.getTramites().remove(ordinal.intValue());
        ta.setAgenda(null);
    }

    public void agregarTramiteMod() {
        limpiarMensajesError();
        TramiteAgenda tramite = new TramiteAgenda();
        tramite.setAgenda(this.agendaNueva);
        this.getAgendaSeleccionada().getTramites().add(tramite);
    }

    public void quitarTramiteMod(Integer ordinal) {
        limpiarMensajesError();
        TramiteAgenda ta = this.getAgendaSeleccionada().getTramites().remove(ordinal.intValue());
        ta.setAgenda(null);
    }

    public void beforePhaseActualizar(PhaseEvent event) {

        if (!FacesContext.getCurrentInstance().isPostback()) {
            // Verificar que el usuario tiene permisos para acceder a esta página
            if(!BooleanUtils.isTrue(sessionMBean.getAdminOrganismoActual())) {
                FacesContext ctx = FacesContext.getCurrentInstance();
                ctx.getApplication().getNavigationHandler().handleNavigation(ctx, "", "noAutorizado");
            }
            if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
                sessionMBean.setPantallaTitulo(sessionMBean.getTextos().get("actualizacion_masiva"));
            }

            initDatosActualizacionMasiva();
        }


    }

    private void initDatosActualizacionMasiva(){
        try {
            //Cargar la lista de agendas destino
            agendasDisponibles = new ArrayList<>();

            agendasDisponibles.add(new SelectItem("", sessionMBean.getTextos().get("seleccionar")));

            for(Agenda ag : generalEJB.consultarAgendas()) {
                agendasDisponibles.add(new SelectItem(ag.getId().toString(), ag.getNombre()));
            }

            Agenda agenda = sessionMBean.getAgendaMarcada();


            List<Recurso> recursosList = generalEJB.consultarRecursos(agenda);
            recursosList.sort(new RecursoComparatorNombre());
            if(!recursosList.isEmpty()){
                recursoSessionMBean.setRecursos(new RowList<>(recursosList));
            }
            limpiarCampos();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void moverSeleccionados(ActionEvent e) {
        limpiarMensajesError();
        if (recursosMarcados == null) {
            recursosMarcados = new TreeSet<>();
        }
        if (recursosAgenda != null && !recursosAgenda.isEmpty()) {
            for (Row<Recurso> row : recursosAgenda) {
                if (row.getData().isSeleccionado()) {
                    recursosMarcados.add(row.getData());
                }
            }
        }
    }

    public void actualizarRecursos() {
        limpiarMensajesError();
        int count = 0;
        boolean hayErrores = false;
        Integer diasInicioVentanaIntranet = recursoSessionMBean.getDiasInicioVentanaIntranet();
        Integer diasVentanaIntranet = recursoSessionMBean.getDiasVentanaIntranet();
        Integer diasInicioVentanaInternet = recursoSessionMBean.getDiasInicioVentanaInternet();
        Integer diasVentanaInternet = recursoSessionMBean.getDiasVentanaInternet();
        if (diasInicioVentanaIntranet == null){
            addErrorMessage(sessionMBean.getTextos().get("los_dias_de_inicio_de_la_ventana_de_intranet_es_obligatorio"), FORM_ID+":diasIVIntranet");
            hayErrores = true;
        } else if (diasInicioVentanaIntranet < 0){
            addErrorMessage(sessionMBean.getTextos().get("los_dias_de_inicio_de_la_ventana_de_intranet_debe_ser_mayor_o_igual_a_cero"), FORM_ID+":diasIVIntranet");
            hayErrores = true;
        }
        if (diasVentanaIntranet == null){
            addErrorMessage(sessionMBean.getTextos().get("los_dias_de_la_ventana_de_intranet_es_obligatorio"), FORM_ID+":DiasVIntranet");
            hayErrores = true;
        } else if (diasVentanaIntranet <= 0){
            addErrorMessage(sessionMBean.getTextos().get("los_dias_de_la_ventana_de_intranet_debe_ser_mayor_a_cero"), FORM_ID+":DiasVIntranet");
            hayErrores = true;
        }
        if (diasInicioVentanaInternet == null){
            addErrorMessage(sessionMBean.getTextos().get("los_dias_de_inicio_de_la_ventana_de_internet_es_obligatorio"), FORM_ID+":DiasInicioVInternet");
            hayErrores = true;
        } else if (diasInicioVentanaInternet < 0){
            addErrorMessage(sessionMBean.getTextos().get("los_dias_de_inicio_de_la_ventana_de_internet_debe_ser_mayor_o_igual_a_cero"), FORM_ID+":DiasInicioVInternet");
            hayErrores = true;
        }
        if (diasVentanaInternet == null){
            addErrorMessage(sessionMBean.getTextos().get("los_dias_de_la_ventana_de_internet_es_obligatorio"), FORM_ID+":DiasVInternet");
            hayErrores = true;
        } else if (diasVentanaInternet <= 0){
            addErrorMessage(sessionMBean.getTextos().get("los_dias_de_la_ventana_de_internet_debe_ser_mayor_a_cero"), FORM_ID+":DiasVInternet");
            hayErrores = true;
        }
        List<CsvRow> csvRows = new ArrayList<>();
        if(recursosMarcados!=null && !recursosMarcados.isEmpty()) {
            for (Recurso recursoSeleccionado : recursosMarcados) {
                try {
                    count++;
                    recursoSeleccionado.setDiasInicioVentanaIntranet(diasInicioVentanaIntranet);
                    recursoSeleccionado.setDiasVentanaIntranet(diasVentanaIntranet);
                    recursoSeleccionado.setDiasInicioVentanaInternet(diasInicioVentanaInternet);
                    recursoSeleccionado.setDiasVentanaInternet(diasVentanaInternet);
                    recursosEJB.modificarRecurso(recursoSeleccionado, sessionMBean.getUsuarioActual().getCodigo());
                } catch (UserException | BusinessException | ApplicationException ue) {
                    hayErrores = true;
                    String mensajeError = sessionMBean.getTextos().get(ue.getCodigoError());
                    csvRows.add(new CsvRow(recursoSeleccionado.getId(), String.format(
                        "El recurso '%s' de la agenda %d no ha sido actualizado. Mensaje de error: %s.",
                        recursoSeleccionado.getNombre(), recursoSeleccionado.getAgenda().getId(),
                        mensajeError)));
                }
            }
        }
        if (count == 0) {
            addErrorMessage("Debe seleccionar al menos un recurso", MSG_ID);
            csvRows.add(new CsvRow(0, "Ningún recurso ha sido actualizado. Debe seleccionar al menos un recurso"));
        } else if (count > 0) {
            sessionMBean.cargarRecursos();
            initDatosActualizacionMasiva();
            if (hayErrores && !csvRows.isEmpty()) {
                addAdvertenciaMessage("Descargue reporte de errores para ver detalles", MSG_ID);
                byte[] bytes = new CsvReport().convertToCSV(csvRows);
                InputStream inputStream = new ByteArrayInputStream(bytes);
                reporte = new DefaultStreamedContent(inputStream, "text/csv", "reporte.csv",
                    StandardCharsets.UTF_8.toString());
            } else {
                addInfoMessage(sessionMBean.getTextos().get("actualizacion_recursos")
                    .replaceAll("x", String.valueOf(count)), MSG_ID);
                reporte = null;
            }
            seleccionarTodos = false;
        }
    }

    public void seleccionarTodosRecursos(AjaxBehaviorEvent event) {

        limpiarMensajesError();
        HtmlSelectBooleanCheckbox check = (HtmlSelectBooleanCheckbox)event.getSource();
        Boolean isChecked = (Boolean)check.getValue();
        if (recursosAgenda != null && !recursosAgenda.isEmpty()) {
            for (Row<Recurso> row : recursosAgenda) {
                row.getData().setSeleccionado(isChecked);
            }
        }
    }

    public void seleccionarRecursosDisponibles() throws UserException {
        limpiarMensajesError();
        recursosMarcados = new TreeSet<>();
        if (todosLosRecursos) {
            for (SelectItem agenda : agendasDisponibles) {
                String agendaId = agenda.getValue().toString();
                if(StringUtils.isNumeric(agendaId)) {
                    recursosMarcados.addAll(recursosEJB.consultarRecursoByAgendaId(NumberUtils.toInt(agendaId)));
                }
            }
        }
    }

    private void limpiarCampos(){
        recursoSessionMBean.setDiasInicioVentanaInternet(null);
        recursoSessionMBean.setDiasVentanaInternet(null);
        recursoSessionMBean.setDiasInicioVentanaIntranet(null);
        recursoSessionMBean.setDiasVentanaIntranet(null);
        recursosMarcados = new TreeSet<>();
        recursosAgenda = new RowList<>();
        seleccionarTodos = false;
        todosLosRecursos = false;
        agendaActualId = "";
    }

    public void seleccionarUno() {
        limpiarMensajesError();
    }

    public RecursoSessionMBean getRecursoSessionMBean() {
        return recursoSessionMBean;
    }

    public void setRecursoSessionMBean(RecursoSessionMBean recursoSessionMBean) {
        this.recursoSessionMBean = recursoSessionMBean;
    }

    public List<SelectItem> getAgendasDisponibles() {
        return agendasDisponibles;
    }

    public void setAgendasDisponibles(List<SelectItem> agendasDisponibles) {
        this.agendasDisponibles = agendasDisponibles;
    }

    public Boolean getSeleccionarTodos() {
        return seleccionarTodos;
    }

    public void setSeleccionarTodos(Boolean seleccionarTodos) {
        this.seleccionarTodos = seleccionarTodos;
    }

    public void cambioSeleccionAgenda(AjaxBehaviorEvent event) throws UserException {
        if(StringUtils.isNumeric(agendaActualId)) {
            int agendaId = Integer.parseInt(agendaActualId);
            List<Recurso> recursos = recursosEJB.consultarRecursoByAgendaId(agendaId);
            recursosAgenda = new RowList<>(recursos);
        } else {
            recursosAgenda = new RowList<>();
        }
        seleccionarTodos = false;
    }

    public RowList<Recurso> getRecursosAgenda() {
        return recursosAgenda;
    }

    public void setRecursosAgenda(RowList<Recurso> recursosAgenda) {
        this.recursosAgenda = recursosAgenda;
    }

    public String getAgendaActualId() {
        return agendaActualId;
    }

    public void setAgendaActualId(String agendaActualId) {
        this.agendaActualId = agendaActualId;
    }

    public boolean isTodosLosRecursos() {
        return todosLosRecursos;
    }

    public void setTodosLosRecursos(boolean todosLosRecursos) {
        this.todosLosRecursos = todosLosRecursos;
    }

    public Set<Recurso> getRecursosMarcados() {
        return recursosMarcados;
    }

    public void setRecursosMarcados(Set<Recurso> recursosMarcados) {
        this.recursosMarcados = recursosMarcados;
    }

    public DefaultStreamedContent getReporte() {
        return reporte;
    }

    void setReporte(DefaultStreamedContent reporte) {
        this.reporte = reporte;
    }

    @EJB
    public void setGeneralEJB(AgendaGeneralLocal generalEJB) {
        this.generalEJB = generalEJB;
    }

    @EJB
    public void setRecursosEJB(RecursosLocal recursosEJB) {
        this.recursosEJB = recursosEJB;
    }

    public List<String> getIdiomasParaNuevaAgenda() {
        return getIdiomasAgenda(getAgendaNueva());
    }

    public void setIdiomasParaNuevaAgenda(List<String> idiomasParaNuevaAgenda) {
        setIdiomasAgenda(getAgendaNueva(), idiomasParaNuevaAgenda);
    }

    public List<String> getIdiomasAgendaSeleccionada() {
        return getIdiomasAgenda(getAgendaSeleccionada());
    }

    public void setIdiomasAgendaSeleccionada(List<String> idiomasParaAgendaSeleccionada) {
        setIdiomasAgenda(getAgendaSeleccionada(), idiomasParaAgendaSeleccionada);
    }

    List<String> getIdiomasAgenda(Agenda agenda) {
        if (agenda != null && agenda.getIdiomas() != null) {
            return new ArrayList<>(Arrays.asList(agenda.getIdiomas().split(",")));
        }
        return new ArrayList<>();
    }

    void setIdiomasAgenda(Agenda agenda, List<String> idiomas) {
        agenda.setIdiomas(String.join(",", idiomas));
    }
}
