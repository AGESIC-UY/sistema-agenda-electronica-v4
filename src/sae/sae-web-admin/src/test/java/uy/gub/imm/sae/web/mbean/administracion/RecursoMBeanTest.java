package uy.gub.imm.sae.web.mbean.administracion;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.primefaces.PrimeFaces;
import uy.gub.imm.opencsv.ext.entity.TableCellValue;
import uy.gub.imm.sae.business.ejb.facade.RecursosLocal;
import uy.gub.imm.sae.common.enumerados.FormaCancelacion;
import uy.gub.imm.sae.entity.Recurso;
import uy.gub.imm.sae.exception.ApplicationException;
import uy.gub.imm.sae.exception.BusinessException;
import uy.gub.imm.sae.exception.UserException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RecursoMBeanTest {

    @Test
    void givenRecursoConLatitudYLongitudAsNulls_whenExtraerContenido_thenFinishWithoutNPE() {
        RecursoMBean recursoMBean = new RecursoMBean();

        List<List<TableCellValue>> contenido = recursoMBean.extraerContenido(Collections.singletonList(new Recurso(1, new Date(), new Date(), new Date(), null, null)));

        assertThat(contenido).isNotEmpty().hasSize(1);
        assertThat(contenido.get(0).get(9).getValue()).isEqualTo(""); // Latitud
        assertThat(contenido.get(0).get(10).getValue()).isEqualTo(""); // Longitud
    }

    @Test
    void givenRecursoConLatitudYLongitudAsNotNulls_whenExtraerContenido_thenExtractValues() {
        RecursoMBean recursoMBean = new RecursoMBean();

        List<List<TableCellValue>> contenido = recursoMBean.extraerContenido(Collections.singletonList(new Recurso(1, new Date(), new Date(), new Date(), BigDecimal.ONE, BigDecimal.TEN)));

        assertThat(contenido).isNotEmpty().hasSize(1);
        assertThat(contenido.get(0).get(9).getValue()).isEqualTo("1"); // Latitud
        assertThat(contenido.get(0).get(10).getValue()).isEqualTo("10"); // Longitud
    }

    @SuppressWarnings("unchecked")
    @Test
    void givenRecursoWithoutLargoLista_whenCrear_thenAddErrorMessage() {
        try (MockedStatic<Logger> logger = mockStatic(Logger.class, RETURNS_DEEP_STUBS); MockedStatic<FacesContext> context = mockStatic(FacesContext.class, Mockito.RETURNS_DEEP_STUBS)) {
            RecursoMBean recursoMBean = new RecursoMBean();
            SessionMBean sessionMBean = mock(SessionMBean.class);
            Recurso recurso = recursoFake();
            when(sessionMBean.getRecursoSeleccionado()).thenReturn(recurso);
            recursoMBean.setSessionMBean(sessionMBean);
            recursoMBean.setRecursoNuevo(recurso);
            recursoMBean.setRecursosEJB(mock(RecursosLocal.class));
            FacesContext facesContext = mock(FacesContext.class, RETURNS_DEEP_STUBS);
            when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
            ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
            ArgumentCaptor<String> componentIdCaptor = ArgumentCaptor.forClass(String.class);
            recursoMBean.crear();

            verify(facesContext, times(1)).addMessage(componentIdCaptor.capture(), messageCaptor.capture());
            assertThat(messageCaptor.getValue().getSeverity()).isEqualTo(FacesMessage.SEVERITY_ERROR);
            assertThat(componentIdCaptor.getValue()).isEqualTo("form:largoLista");
        }
    }

    @Test
    void givenRecursoWithLargoListaAndRequiredProperties_whenCrear_thenCrearRecurso() throws BusinessException, ApplicationException, UserException {
        try (MockedStatic<Logger> logger = mockStatic(Logger.class, RETURNS_DEEP_STUBS); MockedStatic<FacesContext> context = mockStatic(FacesContext.class, Mockito.RETURNS_DEEP_STUBS); MockedStatic<PrimeFaces> primefaces = mockStatic(PrimeFaces.class, Mockito.RETURNS_DEEP_STUBS)) {
            RecursoMBean recursoMBean = new RecursoMBean();
            SessionMBean sessionMBean = mock(SessionMBean.class, RETURNS_DEEP_STUBS);
            Recurso recurso = recursoFake();
            recurso.setLargoListaEspera(1);
            when(sessionMBean.getRecursoSeleccionado()).thenReturn(recurso);
            recursoMBean.setSessionMBean(sessionMBean);
            recursoMBean.setRecursoNuevo(recurso);
            RecursosLocal recursosEJB = mock(RecursosLocal.class);
            recursoMBean.setRecursosEJB(recursosEJB);
            recursoMBean.crear();

            verify(recursosEJB, times(1)).crearRecurso(sessionMBean.getAgendaMarcada(), recurso, null);
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    void givenRecursoWithoutLargoLista_whenGuardar_thenAddErrorMessage() {
        try (MockedStatic<Logger> logger = mockStatic(Logger.class, RETURNS_DEEP_STUBS); MockedStatic<FacesContext> context = mockStatic(FacesContext.class, Mockito.RETURNS_DEEP_STUBS)) {
            RecursoMBean recursoMBean = new RecursoMBean();
            SessionMBean sessionMBean = mock(SessionMBean.class);
            Recurso recurso = recursoFake();
            when(sessionMBean.getRecursoSeleccionado()).thenReturn(recurso);
            recursoMBean.setSessionMBean(sessionMBean);
            recursoMBean.setRecursoNuevo(recurso);
            recursoMBean.setRecursosEJB(mock(RecursosLocal.class));
            FacesContext facesContext = mock(FacesContext.class, RETURNS_DEEP_STUBS);
            when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
            ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
            ArgumentCaptor<String> componentIdCaptor = ArgumentCaptor.forClass(String.class);
            String resultado = recursoMBean.guardar();

            verify(facesContext, times(1)).addMessage(componentIdCaptor.capture(), messageCaptor.capture());
            assertThat(messageCaptor.getValue().getSeverity()).isEqualTo(FacesMessage.SEVERITY_ERROR);
            assertThat(componentIdCaptor.getValue()).isEqualTo("form:largoListaEspera");
            assertThat(resultado).isNull();
        }
    }

    @Test
    void givenRecursoWithLargoListaAndRequiredProperties_whenGuardar_thenReturnGuardar() {
        try (MockedStatic<Logger> logger = mockStatic(Logger.class, RETURNS_DEEP_STUBS); MockedStatic<FacesContext> context = mockStatic(FacesContext.class, Mockito.RETURNS_DEEP_STUBS); MockedStatic<PrimeFaces> primefaces = mockStatic(PrimeFaces.class, Mockito.RETURNS_DEEP_STUBS)) {
            RecursoMBean recursoMBean = new RecursoMBean();
            SessionMBean sessionMBean = mock(SessionMBean.class, RETURNS_DEEP_STUBS);
            Recurso recurso = recursoFake();
            recurso.setLargoListaEspera(1);
            when(sessionMBean.getRecursoSeleccionado()).thenReturn(recurso);
            recursoMBean.setSessionMBean(sessionMBean);
            recursoMBean.setRecursoNuevo(recurso);
            RecursosLocal recursosEJB = mock(RecursosLocal.class);
            recursoMBean.setRecursosEJB(recursosEJB);

            String resultado = recursoMBean.guardar();

            assertThat(resultado).isEqualTo("guardar");
        }
    }

    private Recurso recursoFake() {
        Recurso recurso = new Recurso();
        recurso.setId(10);
        recurso.setNombre("RECURSO DE TEST");
        recurso.setDescripcion("PRUEBAS");
        recurso.setFechaInicio(Date.from(Instant.parse("2021-01-01T00:00:00.00Z")));
        recurso.setFechaFin(Date.from(Instant.parse("2021-12-31T23:59:59.00Z")));
        recurso.setFechaInicioDisp(Date.from(Instant.parse("2021-01-02T00:00:00.00Z")));
        recurso.setFechaFinDisp(Date.from(Instant.parse("2021-12-30T00:00:00.00Z")));
        recurso.setDiasInicioVentanaIntranet(0);
        recurso.setDiasVentanaIntranet(100);
        recurso.setDiasInicioVentanaInternet(0);
        recurso.setDiasVentanaInternet(100);
        recurso.setCantDiasAGenerar(250);
        recurso.setMostrarNumeroEnLlamador(false);
        recurso.setVisibleInternet(true);
        recurso.setUsarLlamador(true);
        recurso.setSerie("");
        recurso.setSabadoEsHabil(true);
        recurso.setDomingoEsHabil(false);
        recurso.setMostrarNumeroEnTicket(false);
        recurso.setMostrarIdEnTicket(false);
        recurso.setFuenteTicket("helvetica");
        recurso.setTamanioFuenteGrande(12);
        recurso.setTamanioFuenteNormal(10);
        recurso.setTamanioFuenteChica(6);
        recurso.setOficinaId("0");
        recurso.setDireccion("Dirección de prueba");
        recurso.setLocalidad("Ciudad de prueba");
        recurso.setDepartamento("Departamento de prueba");
        recurso.setTelefonos("");
        recurso.setHorarios("");
        recurso.setLatitud(BigDecimal.TEN);
        recurso.setLongitud(BigDecimal.TEN);
        recurso.setPresencialAdmite(true);
        recurso.setPresencialCupos(6);
        recurso.setPresencialLunes(true);
        recurso.setPresencialMartes(true);
        recurso.setPresencialMiercoles(true);
        recurso.setPresencialJueves(true);
        recurso.setPresencialViernes(true);
        recurso.setPresencialSabado(true);
        recurso.setPresencialDomingo(false);
        recurso.setMultipleAdmite(true);
        recurso.setCambiosAdmite(true);
        recurso.setCambiosTiempo(2);
        recurso.setCambiosUnidad(5);
        recurso.setPeriodoValidacion(250);
        recurso.setValidarPorIP(false);
        recurso.setCancelacionTiempo(2);
        recurso.setCambiosUnidad(5);
        recurso.setCancelacionTipo(FormaCancelacion.I);
        recurso.setMiPerfilConHab(false);
        recurso.setMiPerfilConTitulo("");
        recurso.setMiPerfilConCorto("");
        recurso.setMiPerfilCanLargo("");
        recurso.setMiPerfilCanVencim(0);
        recurso.setMiPerfilRecHab(false);
        recurso.setMiPerfilRecTitulo("");
        recurso.setMiPerfilRecCorto("");
        recurso.setMiPerfilRecLargo("");
        recurso.setMiPerfilRecVencim(0);
        recurso.setMiPerfilRecHora(0);
        recurso.setReservaPendienteTiempoMax(10);
        recurso.setReservaMultiplePendienteTiempoMax(10);
        recurso.setSeleccionado(false);
        return recurso;
    }

    private Recurso recursoFakeFechas(String nombre, String fechaInicio, String fechaFin, String fechaInicioDisp, String fechaFinDisp) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Recurso recurso = new Recurso();
        recurso.setId(10);
        recurso.setNombre(nombre);
        recurso.setDescripcion("PRUEBAS");
        recurso.setFechaInicio(dateFormat.parse(fechaInicio));
        recurso.setFechaFin(dateFormat.parse(fechaFin));
        recurso.setFechaInicioDisp(dateFormat.parse(fechaInicioDisp));
        recurso.setFechaFinDisp(dateFormat.parse(fechaFinDisp));
        recurso.setDiasInicioVentanaIntranet(0);
        recurso.setDiasVentanaIntranet(100);
        recurso.setDiasInicioVentanaInternet(0);
        recurso.setDiasVentanaInternet(100);
        recurso.setCantDiasAGenerar(250);
        recurso.setMostrarNumeroEnLlamador(false);
        recurso.setVisibleInternet(true);
        recurso.setUsarLlamador(true);
        recurso.setSerie("");
        recurso.setSabadoEsHabil(true);
        recurso.setDomingoEsHabil(false);
        recurso.setMostrarNumeroEnTicket(false);
        recurso.setMostrarIdEnTicket(false);
        recurso.setFuenteTicket("helvetica");
        recurso.setTamanioFuenteGrande(12);
        recurso.setTamanioFuenteNormal(10);
        recurso.setTamanioFuenteChica(6);
        recurso.setOficinaId("0");
        recurso.setDireccion("Dirección de prueba");
        recurso.setLocalidad("Ciudad de prueba");
        recurso.setDepartamento("Departamento de prueba");
        recurso.setTelefonos("");
        recurso.setHorarios("");
        recurso.setLatitud(BigDecimal.TEN);
        recurso.setLongitud(BigDecimal.TEN);
        recurso.setPresencialAdmite(true);
        recurso.setPresencialCupos(6);
        recurso.setPresencialLunes(true);
        recurso.setPresencialMartes(true);
        recurso.setPresencialMiercoles(true);
        recurso.setPresencialJueves(true);
        recurso.setPresencialViernes(true);
        recurso.setPresencialSabado(true);
        recurso.setPresencialDomingo(false);
        recurso.setMultipleAdmite(true);
        recurso.setCambiosAdmite(true);
        recurso.setCambiosTiempo(2);
        recurso.setCambiosUnidad(5);
        recurso.setPeriodoValidacion(250);
        recurso.setValidarPorIP(false);
        recurso.setCancelacionTiempo(2);
        recurso.setCambiosUnidad(5);
        recurso.setCancelacionTipo(FormaCancelacion.I);
        recurso.setMiPerfilConHab(false);
        recurso.setMiPerfilConTitulo("");
        recurso.setMiPerfilConCorto("");
        recurso.setMiPerfilCanLargo("");
        recurso.setMiPerfilCanVencim(0);
        recurso.setMiPerfilRecHab(false);
        recurso.setMiPerfilRecTitulo("");
        recurso.setMiPerfilRecCorto("");
        recurso.setMiPerfilRecLargo("");
        recurso.setMiPerfilRecVencim(0);
        recurso.setMiPerfilRecHora(0);
        recurso.setReservaPendienteTiempoMax(10);
        recurso.setReservaMultiplePendienteTiempoMax(10);
        recurso.setSeleccionado(false);
        return recurso;
    }
    @Test
    void dateValidationTest() throws ParseException {
        try (MockedStatic<Logger> logger = mockStatic(Logger.class, RETURNS_DEEP_STUBS); MockedStatic<FacesContext> context = mockStatic(FacesContext.class, Mockito.RETURNS_DEEP_STUBS); MockedStatic<PrimeFaces> primefaces = mockStatic(PrimeFaces.class, Mockito.RETURNS_DEEP_STUBS)) {
        RecursoMBean recursoMBean = new RecursoMBean();
        recursoMBean.setSessionMBean(mock(SessionMBean.class, RETURNS_DEEP_STUBS));
        //Casos validos
        Recurso rec1 = recursoFakeFechas("Caso1","31/05/2022", "31/03/2026", "30/06/2022", "30/05/2024");
        Recurso rec2 = recursoFakeFechas("Caso2","01/01/2024", "31/12/2024", "01/01/2024", "31/12/2024");
        Recurso rec3 = recursoFakeFechas("Caso3","01/01/2024", "31/12/2024", "01/01/2024", "01/01/2024");
        Recurso rec4 = recursoFakeFechas("Caso4","01/01/2023", "31/12/2025", "01/01/2024", "01/01/2025");
        //Casos invalidos
        Recurso rec5 = recursoFakeFechas("Caso5","01/01/2025","31/12/2024","01/01/2024","31/12/2024");
        Recurso rec6 = recursoFakeFechas("Caso6","01/01/2024","31/12/2024","01/01/2024","01/01/2025");
        Recurso rec7 = recursoFakeFechas("Caso7","31/05/2022","31/03/2026","31/12/2021","31/12/2021");
        Recurso rec8 = recursoFakeFechas("Caso8","31/05/2022","31/03/2026","31/03/2028","31/12/2028");
        Recurso rec9 = recursoFakeFechas("Caso9","31/05/2025","31/03/2024","31/03/2023","31/12/2022");
        Recurso rec10 = recursoFakeFechas("Caso10","30/09/2017","30/09/2022","30/09/2016","30/09/2023");
        Recurso rec11 = recursoFakeFechas("Caso11","28/05/2024","31/12/2024","01/05/2024","31/12/2023");
        Recurso rec12 = recursoFakeFechas("Caso12","17/08/2024","30/12/2023","17/08/2021","31/12/2024");
        Recurso rec13 = recursoFakeFechas("Caso13","01/01/2024","31/12/2024","31/12/2023", "31/12/2024");
        Recurso rec14 = recursoFakeFechas("Caso14","01/01/2024", "31/12/2024","01/01/2024","31/12/2023");
        //Casos validos
        assertThat(recursoMBean.validarFechas(rec1)).isFalse();
        assertThat(recursoMBean.validarFechas(rec2)).isFalse();
        assertThat(recursoMBean.validarFechas(rec3)).isFalse();
        assertThat(recursoMBean.validarFechas(rec4)).isFalse();
        //Casos Invalidos
        assertThat(recursoMBean.validarFechas(rec5)).isTrue();
        assertThat(recursoMBean.validarFechas(rec6)).isTrue();
        assertThat(recursoMBean.validarFechas(rec7)).isTrue();
        assertThat(recursoMBean.validarFechas(rec8)).isTrue();
        assertThat(recursoMBean.validarFechas(rec9)).isTrue();
        assertThat(recursoMBean.validarFechas(rec10)).isTrue();
        assertThat(recursoMBean.validarFechas(rec11)).isTrue();
        assertThat(recursoMBean.validarFechas(rec12)).isTrue();
        assertThat(recursoMBean.validarFechas(rec13)).isTrue();
        assertThat(recursoMBean.validarFechas(rec14)).isTrue();

        }

    }

}