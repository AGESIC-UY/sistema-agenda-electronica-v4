package uy.gub.imm.sae.web.mbean.administracion;

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
import uy.gub.imm.sae.web.common.SofisJSFUtils;

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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@ManagedBean(name = "paso1AdminMBean")
@ViewScoped
public class Paso1AdminMBean extends BaseMBean {

    private static final Logger logger = Logger.getLogger(Paso1AdminMBean.class);
    public static final String MSG_ID = "pantalla";

    @EJB private AgendarReservasLocal agendarReservasEJB;
    @EJB private DisponibilidadesLocal disponibilidadEJB;
    @EJB private RecursosLocal recursosEJB;

    @ManagedProperty(value = "#{sessionMBean}")
    private SessionMBean sessionMBean;

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
    private Integer disponibilidadSeleccionadaId; // id elegido en el combo
    private Disponibilidad disponibilidadSeleccionada; // se setea por botón o por combo
    private boolean recursoTieneDisponibilidad = true; // para template

    private List<Disponibilidad> disponibilidadesParaPrimerosTurnos = new ArrayList<>();

    public SessionMBean getSessionMBean() { return sessionMBean; }
    public void setSessionMBean(SessionMBean sessionMBean) { this.sessionMBean = sessionMBean; }

    @PostConstruct
    public void init() {
        try {
            errorInit = false;

            Agenda agenda = sessionMBean.getAgendaMarcada();
            if (agenda == null) {
                addErrorMessage(sessionMBean.getTextos().get("debe_haber_una_agenda_seleccionada"));
                errorInit = true; return;
            }
            sessionMBean.setAgenda(agenda);

            // Recursos
            recursos = agendarReservasEJB.consultarRecursos(agenda);
            if (recursos == null || recursos.isEmpty()) {
                addErrorMessage(sessionMBean.getTextos().get("no_hay_recursos_disponibles_para_la_agenda_seleccionada"));
                errorInit = true; return;
            }

            // Ordenar recursos: primero los que tienen disponibilidad
            Map<Integer, Boolean> recursoConDisponibilidad = new HashMap<>();
            for (Recurso r : recursos) {
                try {
                    VentanaDeTiempo ventanaTemp = agendarReservasEJB.obtenerVentanaCalendarioIntranet(r);
                    List<Integer> cupos = agendarReservasEJB.obtenerCuposPorDia(r, ventanaTemp, sessionMBean.getTimeZone());
                    boolean tieneDisp = cupos.stream().anyMatch(c -> c != null && c > 0);
                    recursoConDisponibilidad.put(r.getId(), tieneDisp);
                } catch (Exception e) {
                    recursoConDisponibilidad.put(r.getId(), false);
                }
            }

            recursos.sort((r1, r2) -> {
                boolean disp1 = recursoConDisponibilidad.getOrDefault(r1.getId(), false);
                boolean disp2 = recursoConDisponibilidad.getOrDefault(r2.getId(), false);
                if (disp1 != disp2) return disp2 ? 1 : -1; // los con disponibilidad primero
                return 0; // mantener orden original entre iguales
            });

            recursosItems = recursos.stream()
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

                        String html = "<span class='fw-semibold'>" + esc(descripcion) + "</span>"
                                + ((localidadDepto != null && !localidadDepto.isEmpty())
                                ? "<br/><span class='text-muted small d-block radio-sub'>" + esc(localidadDepto) + "</span>"
                                : "");

                        SelectItem si = new SelectItem(r.getId().toString(), html);
                        si.setEscape(false);
                        return si;
                    })
                    .collect(Collectors.toList());




            // Recurso por defecto (o marcado en URL)
            Recurso recursoDefecto = sessionMBean.getRecursoMarcado();
            if (recursoDefecto != null) sessionMBean.setRecurso(recursoDefecto);
            else if (sessionMBean.getRecurso() == null) sessionMBean.setRecurso(recursos.get(0));

            // Configurar calendario y primer día con cupos
            configurarCalendario(sessionMBean.getRecurso());
            fechaSeleccionada = buscarPrimerDiaConDisponibilidad(sessionMBean.getRecurso());

            // Cargar disponibilidades del día seleccionado (y guardarlas en SessionMBean)
            cargarDisponibilidadesDelDia(sessionMBean.getRecurso(), fechaSeleccionada);

            cargarDisponibilidadesParaPrimerosTurnos(sessionMBean.getRecurso(), fechaSeleccionada);

            // Calcular primeras 3 y combo
            recomputarFusionadasYDerivadas();

        } catch (Exception e) {
            logger.error("Error init Paso1AdminMBean", e);
            redirect(ERROR_PAGE_OUTCOME);
        }
    }
    /* ==================== Render / texto ==================== */
    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;");
    }

    public boolean getErrorInit() { return errorInit; }

    public String getAgendaNombre() {
        return sessionMBean.getAgenda() != null ? sessionMBean.getAgenda().getNombre() : null;
    }

    public String getEtiquetaSeleccionDelRecurso() {
        TextoAgenda ta = getTextoAgenda(sessionMBean.getAgenda(), sessionMBean.getIdiomaActual());
        return (ta != null && ta.getTextoSelecRecurso() != null) ? ta.getTextoSelecRecurso() : "";
    }

    /* ==================== Recursos (radio) ==================== */

    public List<SelectItem> getRecursosItems() { return recursosItems; }



    public String getRecursoId() {
        return sessionMBean.getRecurso() != null ? sessionMBean.getRecurso().getId().toString() : null;
    }
    public void setRecursoId(String sRecursoId) {
        if (sRecursoId == null) return;
        Integer id = Integer.valueOf(sRecursoId);
        if (sessionMBean.getRecurso() == null || !sessionMBean.getRecurso().getId().equals(id)) {
            recursos.stream().filter(r -> r.getId().equals(id)).findFirst().ifPresent(sessionMBean::setRecurso);
        }
    }

    public void cambioRecursoAjax(AjaxBehaviorEvent e) {
        try {
            configurarCalendario(sessionMBean.getRecurso());
            fechaSeleccionada = buscarPrimerDiaConDisponibilidad(sessionMBean.getRecurso());
            cargarDisponibilidadesDelDia(sessionMBean.getRecurso(), fechaSeleccionada);
            cargarDisponibilidadesParaPrimerosTurnos(sessionMBean.getRecurso(), fechaSeleccionada);
            disponibilidadSeleccionada = null;
            disponibilidadSeleccionadaId = null;
            mostrarCalendarioCompleto = false; // evitar validaciones del combo/calendario
            recomputarFusionadasYDerivadas();
        } catch (Exception ex) {
            addErrorMessage(ex, MSG_ID);
        }
    }

    public boolean isRecursoTieneDisponibilidad() { return recursoTieneDisponibilidad; }

    /* ==================== Calendario ==================== */

    public boolean isMostrarCalendarioCompleto() { return mostrarCalendarioCompleto; }
    public void toggleMostrarCalendario() {
        mostrarCalendarioCompleto = !mostrarCalendarioCompleto;
        if (!mostrarCalendarioCompleto) {
            // si lo oculto, no dejo seleccionado un valor "huérfano" del combo
            todasDisponibilidadesItems.clear();
        } else {
            // si lo muestro, aseguro tener ítems vigentes del día actual
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
        sessionMBean.setDiaSeleccionado(d);
        try {
            cargarDisponibilidadesDelDia(sessionMBean.getRecurso(), d);
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
        // <f:setPropertyActionListener> setea disponibilidadSeleccionada desde la vista
        if (disponibilidadSeleccionada != null) {
            disponibilidadSeleccionadaId = disponibilidadSeleccionada.getId();
            // si el usuario eligió uno de los 3 primeros, oculto calendario/combobox
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
        if (!recursoTieneDisponibilidad) {
            // El recurso no tiene cupos: el warning ya está visible, no agregar error
            return null;
        }
        if (disponibilidadSeleccionada == null || disponibilidadSeleccionada.getCupo() == null || disponibilidadSeleccionada.getCupo() < 1) {
            addErrorMessage(sessionMBean.getTextos().get("debe_seleccionar_un_horario_con_disponibilidades"), MSG_ID);
            return null;
        }
        try {
            String ipOrigen = SofisJSFUtils.obtenerDireccionIPCliente(FacesContext.getCurrentInstance());
            Reserva r = agendarReservasEJB.marcarReserva(disponibilidadSeleccionada, OrigenReserva.CALLCENTER, null, ipOrigen);
            sessionMBean.setReserva(r);
            sessionMBean.setDisponibilidad(disponibilidadSeleccionada);
            return "siguientePaso";
        } catch (Exception ex) {
            addErrorMessage(ex, MSG_ID);
            return null;
        }
    }

    /* ==================== Internos ==================== */

    private void configurarCalendario(Recurso recurso) throws Exception {
        VentanaDeTiempo ventana = agendarReservasEJB.obtenerVentanaCalendarioIntranet(recurso);
        sessionMBean.setVentanaCalendario(ventana);
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
            List<Integer> listaCupos = agendarReservasEJB.obtenerCuposPorDia(recurso, ventana, sessionMBean.getTimeZone());
            Calendar cont = Calendar.getInstance();
            cont.setTime(Utiles.time2InicioDelDia(ventana.getFechaInicial()));

            Integer i = 0;
            int totalDias = 0;
            int diasInvalidos = 0;
            while (!cont.getTime().after(ventana.getFechaFinal()) && i < listaCupos.size()) {
                totalDias++;
                if (listaCupos.get(i) == null || listaCupos.get(i) <= 0) {
                    invalidDates.add(cont.getTime());
                    diasInvalidos++;
                }
                cont.add(Calendar.DAY_OF_MONTH, 1);
                i++;
            }
            logger.info("Recurso " + recurso.getId() + ": Total días=" + totalDias + ", Días inválidos=" + diasInvalidos +
                       ", Rango=" + ventana.getFechaInicial() + " a " + ventana.getFechaFinal());
        } catch (Exception e) {
            logger.error("Error al cargar invalidDates", e);
        }
    }

    private Date buscarPrimerDiaConDisponibilidad(Recurso recurso) throws Exception {
        VentanaDeTiempo ventana = sessionMBean.getVentanaCalendario();
        if (ventana == null) ventana = agendarReservasEJB.obtenerVentanaCalendarioIntranet(recurso);

        List<Integer> cupos = agendarReservasEJB.obtenerCuposPorDia(recurso, ventana, sessionMBean.getTimeZone());
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
            sessionMBean.setDisponibilidadesDelDiaMatutina(new uy.gub.imm.sae.web.common.RowList<>(new ArrayList<>()));
            sessionMBean.setDisponibilidadesDelDiaVespertina(new uy.gub.imm.sae.web.common.RowList<>(new ArrayList<>()));
            return;
        }
        VentanaDeTiempo ventDia = new VentanaDeTiempo();
        ventDia.setFechaInicial(Utiles.time2InicioDelDia(dia));
        ventDia.setFechaFinal(Utiles.time2FinDelDia(dia));

        List<Disponibilidad> lista = agendarReservasEJB.obtenerDisponibilidades(recurso, ventDia, sessionMBean.getTimeZone());
        List<Disponibilidad> mat = new ArrayList<>();
        List<Disponibilidad> ves = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        for (Disponibilidad d : lista) {
            if (d == null) continue;
            cal.setTime(d.getHoraInicio());
            if (d.getCupo() != null && d.getCupo() < 0) d.setCupo(0); // normalizo por si viene -1
            if (cal.get(Calendar.AM_PM) == Calendar.AM) mat.add(d);
            else ves.add(d);
        }
        sessionMBean.setDisponibilidadesDelDiaMatutina(new uy.gub.imm.sae.web.common.RowList<>(mat));
        sessionMBean.setDisponibilidadesDelDiaVespertina(new uy.gub.imm.sae.web.common.RowList<>(ves));
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

        List<Disponibilidad> lista = agendarReservasEJB.obtenerDisponibilidades(recurso, ventAmpliada, sessionMBean.getTimeZone());

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
        if (sessionMBean.getDisponibilidadesDelDiaMatutina() != null) {
            for (uy.gub.imm.sae.web.common.Row<Disponibilidad> r : sessionMBean.getDisponibilidadesDelDiaMatutina())
                if (r != null && r.getData() != null) fusionadasDia.add(r.getData());
        }
        if (sessionMBean.getDisponibilidadesDelDiaVespertina() != null) {
            for (uy.gub.imm.sae.web.common.Row<Disponibilidad> r : sessionMBean.getDisponibilidadesDelDiaVespertina())
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
        // fusionar matutina + vespertina
        List<Disponibilidad> fusionadas = new ArrayList<>();
        if (sessionMBean.getDisponibilidadesDelDiaMatutina() != null) {
            for (uy.gub.imm.sae.web.common.Row<Disponibilidad> r : sessionMBean.getDisponibilidadesDelDiaMatutina())
                if (r != null && r.getData() != null) fusionadas.add(r.getData());
        }
        if (sessionMBean.getDisponibilidadesDelDiaVespertina() != null) {
            for (uy.gub.imm.sae.web.common.Row<Disponibilidad> r : sessionMBean.getDisponibilidadesDelDiaVespertina())
                if (r != null && r.getData() != null) fusionadas.add(r.getData());
        }

        // ordenar y deduplicar por timestamp de inicio
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

        // Actualizar recursoTieneDisponibilidad
        recursoTieneDisponibilidad = !conCupo.isEmpty();

        // Seleccionar automáticamente la primera disponibilidad si hay alguna
        if (!conCupo.isEmpty() && disponibilidadSeleccionadaId == null) {
            Disponibilidad primera = conCupo.get(0);
            disponibilidadSeleccionadaId = primera.getId();
            disponibilidadSeleccionada = primera;
        }
    }

    private TurnoDTO toTurnoDTO(Disponibilidad d) {
        String label = formatFechaHoraES(d.getHoraInicio()); // sólo fecha + hora
        return new TurnoDTO(d, label);
    }

    private SelectItem toSelectItem(Disponibilidad d) {
        String label = formatHoraES(d.getHoraInicio()); // idem para el combo
        return new SelectItem(d.getId(), label);
    }

    private Disponibilidad buscarDisponibilidadPorIdEnDia(Integer id) {
        if (id == null) return null;
        if (sessionMBean.getDisponibilidadesDelDiaMatutina() != null) {
            for (uy.gub.imm.sae.web.common.Row<Disponibilidad> r : sessionMBean.getDisponibilidadesDelDiaMatutina())
                if (r != null && r.getData() != null && id.equals(r.getData().getId())) return r.getData();
        }
        if (sessionMBean.getDisponibilidadesDelDiaVespertina() != null) {
            for (uy.gub.imm.sae.web.common.Row<Disponibilidad> r : sessionMBean.getDisponibilidadesDelDiaVespertina())
                if (r != null && r.getData() != null && id.equals(r.getData().getId())) return r.getData();
        }
        for (Disponibilidad d : disponibilidadesParaPrimerosTurnos) {
            if (d != null && id.equals(d.getId())) return d;
        }
        return null;
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

    public void beforePhase(PhaseEvent phaseEvent) {
        disableBrowserCache(phaseEvent);
        if (phaseEvent.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            sessionMBean.setPantallaTitulo(sessionMBean.getTextos().get("realizar_reserva"));
            sessionMBean.limpiarPaso2();
        }
    }

    private String formatFechaHoraES(Date dt) {
        if (dt == null) return "";
        Locale loc = new Locale("es", "UY");
        SimpleDateFormat df = new SimpleDateFormat("EEEE d 'de' MMMM", loc);
        SimpleDateFormat hf = new SimpleDateFormat(sessionMBean.getFormatoHora(), loc);
        if (sessionMBean.getTimeZone() != null) {
            df.setTimeZone(sessionMBean.getTimeZone());
            hf.setTimeZone(sessionMBean.getTimeZone());
        }
        String fecha = df.format(dt);
        // capitalizar el primer carácter (Java suele dar "lunes", "martes"...)
        if (!fecha.isEmpty()) {
            fecha = Character.toUpperCase(fecha.charAt(0)) + fecha.substring(1);
        }
        return fecha + " - " + hf.format(dt) + " h";
    }

    private String formatHoraES(Date dt) {
        if (dt == null) return "";
        Locale loc = new Locale("es", "UY");
        SimpleDateFormat hf = new SimpleDateFormat(sessionMBean.getFormatoHora(), loc); // ej. "HH:mm"
        if (sessionMBean.getTimeZone() != null) {
            hf.setTimeZone(sessionMBean.getTimeZone());
        }
        return hf.format(dt) + " h";
    }
}
