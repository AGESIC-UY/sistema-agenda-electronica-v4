package uy.gub.imm.sae.web.mbean.administracion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import uy.gub.imm.sae.business.ejb.facade.AgendaGeneralLocal;
import uy.gub.imm.sae.business.ejb.facade.RecursosLocal;
import uy.gub.imm.sae.entity.Agenda;
import uy.gub.imm.sae.entity.Recurso;
import uy.gub.imm.sae.exception.ApplicationException;
import uy.gub.imm.sae.exception.BusinessException;
import uy.gub.imm.sae.exception.UserException;
import uy.gub.imm.sae.web.common.Row;
import uy.gub.imm.sae.web.common.RowList;

class AgendaMBeanTest {

  @Test
  void whenMoverSeleccionados_thenRecursosMarcadosActualizados() {
    try (MockedStatic<Logger> logger = mockStatic(Logger.class, RETURNS_DEEP_STUBS);
        MockedStatic<FacesContext> context = mockStatic(FacesContext.class,
            Mockito.RETURNS_DEEP_STUBS)) {
      AgendaMBean bean = new AgendaMBean();
      RowList<Recurso> recursos = new RowList<>();
      Recurso recurso = new Recurso();
      recurso.setSeleccionado(true);
      recurso.setId(0);
      Row<Recurso> row = new Row<>(recurso, recursos);
      recursos.add(row);
      bean.setRecursosAgenda(recursos);

      bean.moverSeleccionados(null);

      assertThat(bean.getRecursosMarcados()).isNotNull().hasSize(1).contains(recurso);
    }
  }

  @Test
  void whenActualizarRecursos_thenModificarRecurso()
      throws BusinessException, ApplicationException, UserException {
    try (MockedStatic<Logger> logger = mockStatic(Logger.class, RETURNS_DEEP_STUBS);
        MockedStatic<FacesContext> context = mockStatic(FacesContext.class,
            Mockito.RETURNS_DEEP_STUBS);
        MockedStatic<PrimeFaces> primefaces = mockStatic(PrimeFaces.class, RETURNS_DEEP_STUBS)) {
      AgendaMBean bean = new AgendaMBean();
      bean.setReporte(new DefaultStreamedContent());
      RecursoSessionMBean recursoMBean = new RecursoSessionMBean();
      recursoMBean.setDiasInicioVentanaIntranet(10);
      recursoMBean.setDiasVentanaIntranet(10);
      recursoMBean.setDiasInicioVentanaInternet(10);
      recursoMBean.setDiasVentanaInternet(10);
      Recurso recurso = new Recurso();
      recurso.setId(1);
      Set<Recurso> recursosMarcados = new TreeSet<>();
      recursosMarcados.add(recurso);
      RecursosLocal recursosEjb = mock(RecursosLocal.class);
      bean.setRecursosMarcados(recursosMarcados);
      bean.setRecursoSessionMBean(recursoMBean);
      SessionMBean sessionMBean = mock(SessionMBean.class, Answers.RETURNS_DEEP_STUBS);
      when(sessionMBean.getTextos().get(anyString())).thenReturn("");
      bean.setSessionMBean(sessionMBean);
      bean.setGeneralEJB(mock(AgendaGeneralLocal.class));
      bean.setRecursosEJB(recursosEjb);

      bean.actualizarRecursos();

      verify(recursosEjb, times(1)).modificarRecurso(any(), any());
      StreamedContent reporte = bean.getReporte();
      assertThat(reporte).isNull();
    }
  }
/*
  @Test
  void givenRecursoWithError_whenActualizarRecursos_thenGetReporte() throws IOException, BusinessException, ApplicationException, UserException {
    try (MockedStatic<Logger> logger = mockStatic(Logger.class, RETURNS_DEEP_STUBS);
        MockedStatic<FacesContext> context = mockStatic(FacesContext.class,
            Mockito.RETURNS_DEEP_STUBS);
        MockedStatic<PrimeFaces> primefaces = mockStatic(PrimeFaces.class, RETURNS_DEEP_STUBS)) {
      AgendaMBean bean = new AgendaMBean();
      RecursoSessionMBean recursoMBean = new RecursoSessionMBean();
      recursoMBean.setDiasInicioVentanaIntranet(10);
      recursoMBean.setDiasVentanaIntranet(10);
      recursoMBean.setDiasInicioVentanaInternet(10);
      recursoMBean.setDiasVentanaInternet(10);
      Recurso recurso = new Recurso();
      recurso.setId(1);
      recurso.setNombre("Recurso de prueba");
      Agenda agenda = new Agenda();
      agenda.setId(1);
      recurso.setAgenda(agenda);
      Set<Recurso> recursosMarcados = new TreeSet<>();
      recursosMarcados.add(recurso);
      RecursosLocal recursosEjb = mock(RecursosLocal.class);
      doThrow(new BusinessException("Error al actualizar")).when(recursosEjb)
          .modificarRecurso(any(), any());
      bean.setRecursosMarcados(recursosMarcados);
      bean.setRecursoSessionMBean(recursoMBean);
      SessionMBean sessionMBean = mock(SessionMBean.class, RETURNS_DEEP_STUBS);
      when(sessionMBean.getTextos().get(anyString())).thenReturn("");
      bean.setSessionMBean(sessionMBean);
      bean.setGeneralEJB(mock(AgendaGeneralLocal.class));
      bean.setRecursosEJB(recursosEjb);

      bean.actualizarRecursos();
      StreamedContent reporte = bean.getReporte();

      verify(recursosEjb, times(1)).modificarRecurso(any(), any());
      assertThat(reporte).isNotNull();
      assertThat(reporte.getName()).isEqualTo("reporte.csv");
      assertThat(reporte.getContentType()).isEqualTo("text/csv");
      String content = IOUtils.toString(reporte.getStream(), StandardCharsets.UTF_8);
      assertThat(content).contains(
          "1,El recurso 'Recurso de prueba' de la agenda 1 no ha sido actualizado. Mensaje de error:");
    }
  }*/

  @Test
  void whenActualizarRecursosWithoutRecursosMarcados_thenGetReportNull()
      throws BusinessException, ApplicationException, UserException {
    try (MockedStatic<Logger> logger = mockStatic(Logger.class, RETURNS_DEEP_STUBS);
        MockedStatic<FacesContext> context = mockStatic(FacesContext.class,
            Mockito.RETURNS_DEEP_STUBS)) {
      AgendaMBean bean = new AgendaMBean();
      RecursoSessionMBean recursoMBean = new RecursoSessionMBean();
      recursoMBean.setDiasInicioVentanaIntranet(10);
      recursoMBean.setDiasVentanaIntranet(10);
      recursoMBean.setDiasInicioVentanaInternet(10);
      recursoMBean.setDiasVentanaInternet(10);
      Set<Recurso> recursosMarcados = new TreeSet<>();
      RecursosLocal recursosEjb = mock(RecursosLocal.class);
      bean.setRecursosMarcados(recursosMarcados);
      bean.setRecursoSessionMBean(recursoMBean);
      SessionMBean sessionMBean = mock(SessionMBean.class, Answers.RETURNS_DEEP_STUBS);
      when(sessionMBean.getTextos().get(anyString())).thenReturn("");
      bean.setSessionMBean(sessionMBean);
      bean.setGeneralEJB(mock(AgendaGeneralLocal.class));
      bean.setRecursosEJB(recursosEjb);

      bean.actualizarRecursos();
      StreamedContent reporte = bean.getReporte();

      verify(recursosEjb, times(0)).modificarRecurso(any(), anyString());
      assertThat(reporte).isNull();
    }
  }

  @Test
  void whenSeleccionarTodosRecursos_thenSeleccionadoIsChecked() {
    try (MockedStatic<Logger> logger = mockStatic(Logger.class, RETURNS_DEEP_STUBS);
        MockedStatic<FacesContext> context = mockStatic(FacesContext.class,
            Mockito.RETURNS_DEEP_STUBS)) {
      AgendaMBean bean = new AgendaMBean();
      AjaxBehaviorEvent event = mock(AjaxBehaviorEvent.class);
      HtmlSelectBooleanCheckbox checkBox = mock(HtmlSelectBooleanCheckbox.class);
      when(checkBox.getValue()).thenReturn(true);
      when(event.getSource()).thenReturn(checkBox);
      RowList<Recurso> recursos = new RowList<>();
      recursos.addAll(Collections.singletonList(new Row<>(new Recurso(), recursos)));
      bean.setRecursosAgenda(recursos);

      bean.seleccionarTodosRecursos(event);
      RowList<Recurso> recursosAgenda = bean.getRecursosAgenda();

      assertThat(recursosAgenda.get(0).getData().isSeleccionado()).isTrue();
    }
  }

  @Test
  void whenSeleccionarRecursosDisponibles_thenGetRecursosMarcadosWithData()
      throws UserException {
    try (MockedStatic<Logger> logger = mockStatic(Logger.class, RETURNS_DEEP_STUBS);
        MockedStatic<FacesContext> context = mockStatic(FacesContext.class,
            Mockito.RETURNS_DEEP_STUBS)) {
      AgendaMBean bean = new AgendaMBean();
      bean.setTodosLosRecursos(true);
      List<SelectItem> agendasDisponibles = new ArrayList<>();
      agendasDisponibles.add(new SelectItem("1", "Agenda 1"));
      bean.setAgendasDisponibles(agendasDisponibles);
      RecursosLocal recursosEjb = mock(RecursosLocal.class);
      Recurso recurso = new Recurso();
      recurso.setId(1);
      when(recursosEjb.consultarRecursoByAgendaId(anyInt())).thenReturn(
          Collections.singletonList(recurso));
      bean.setRecursosEJB(recursosEjb);

      bean.seleccionarRecursosDisponibles();
      Set<Recurso> recursosMarcados = bean.getRecursosMarcados();

      verify(recursosEjb, times(1)).consultarRecursoByAgendaId(1);
      assertThat(recursosMarcados).isNotNull().isNotEmpty().hasSize(1);
    }
  }

  @Test
  void givenValidAgendaId_whenCambioSeleccionAgenda_thenUpdateRecursosAgenda()
      throws UserException {
    try (MockedStatic<Logger> logger = mockStatic(Logger.class, RETURNS_DEEP_STUBS);
        MockedStatic<FacesContext> context = mockStatic(FacesContext.class,
            Mockito.RETURNS_DEEP_STUBS)) {
      AgendaMBean bean = new AgendaMBean();
      bean.setAgendaActualId("1");
      List<SelectItem> agendasDisponibles = new ArrayList<>();
      agendasDisponibles.add(new SelectItem("1", "Agenda 1"));
      bean.setAgendasDisponibles(agendasDisponibles);
      RecursosLocal recursosEjb = mock(RecursosLocal.class);
      Recurso recurso = new Recurso();
      recurso.setId(1);
      when(recursosEjb.consultarRecursoByAgendaId(anyInt())).thenReturn(
          Collections.singletonList(recurso));
      bean.setRecursosEJB(recursosEjb);

      bean.cambioSeleccionAgenda(null);
      RowList<Recurso> recursosAgenda = bean.getRecursosAgenda();

      verify(recursosEjb, times(1)).consultarRecursoByAgendaId(1);
      assertThat(recursosAgenda).isNotNull().isNotEmpty().hasSize(1);
      assertThat(bean.getSeleccionarTodos()).isFalse();
    }
  }

  @Test
  void givenInvalidAgendaId_whenCambioSeleccionAgenda_thenGetEmptyRecursosAgenda()
      throws UserException {
    try (MockedStatic<Logger> logger = mockStatic(Logger.class, RETURNS_DEEP_STUBS);
        MockedStatic<FacesContext> context = mockStatic(FacesContext.class,
            Mockito.RETURNS_DEEP_STUBS)) {
      AgendaMBean bean = new AgendaMBean();
      bean.setAgendaActualId("X");
      RecursosLocal recursosEjb = mock(RecursosLocal.class);
      bean.setRecursosEJB(recursosEjb);

      bean.cambioSeleccionAgenda(null);
      RowList<Recurso> recursosAgenda = bean.getRecursosAgenda();

      verify(recursosEjb, times(0)).consultarRecursoByAgendaId(1);
      assertThat(recursosAgenda).isNotNull().isEmpty();
      assertThat(bean.getSeleccionarTodos()).isFalse();
    }
  }

    @Test
    void givenAgendaSeleccionadaWithIdiomasNotNull_whenIdiomasSeleccionados_thenReturnIdiomasAgenda() {
      try (MockedStatic<FacesContext> context = mockStatic(FacesContext.class,
                   Mockito.RETURNS_DEEP_STUBS)) {
        Agenda agenda = new Agenda();
        agenda.setIdiomas("es,en");
        AgendaMBean agendaMBean = new AgendaMBean();
        SessionMBean sessionMBean = new SessionMBean();
        sessionMBean.setAgendaSeleccionada(agenda);
        agendaMBean.setSessionMBean(sessionMBean);

        assertThat(agendaMBean.getIdiomasSeleccionados()).hasSize(2).contains("es", "en");
      }
    }

  @Test
  void givenAgendaSeleccionadaWithIdiomasNull_whenIdiomasSeleccionados_thenReturnIdiomaDefault() {
    try (MockedStatic<FacesContext> context = mockStatic(FacesContext.class,
            Mockito.RETURNS_DEEP_STUBS)) {
      Agenda agenda = new Agenda();
      AgendaMBean agendaMBean = new AgendaMBean();
      SessionMBean sessionMBean = new SessionMBean();
      sessionMBean.setAgendaSeleccionada(agenda);
      AgendaSessionMBean agendaSessionMBean = new AgendaSessionMBean();
      agendaSessionMBean.setIdiomasSeleccionados(Collections.singletonList("es"));
      agendaMBean.setSessionMBean(sessionMBean);
      agendaMBean.setAgendaSessionMBean(agendaSessionMBean);

      assertThat(agendaMBean.getIdiomasSeleccionados()).hasSize(1).contains("es");
    }
  }

  @Test
  void givenAgendaWith1Idioma_whenGetIdiomasAgenda_thenReturnList() {
    Agenda agenda = new Agenda();
    agenda.setIdiomas("es");
    AgendaMBean agendaMBean = new AgendaMBean();

    List<String> idiomas = agendaMBean.getIdiomasAgenda(agenda);

    assertThat(idiomas).hasSize(1).contains("es");
  }

  @Test
  void givenAgendaWith2Idioma_whenGetIdiomasAgenda_thenReturnList() {
    Agenda agenda = new Agenda();
    agenda.setIdiomas("es,en");
    AgendaMBean agendaMBean = new AgendaMBean();

    List<String> idiomas = agendaMBean.getIdiomasAgenda(agenda);

    assertThat(idiomas).hasSize(2).contains("es", "en");
  }

  @Test
  void givenAgendaWithoutIdiomas_whenSetIdiomasAgenda_thenSetValues() {
    Agenda agenda = new Agenda();
    AgendaMBean agendaMBean = new AgendaMBean();
    List<String> idiomas = Arrays.asList("en", "es");

    agendaMBean.setIdiomasAgenda(agenda, idiomas);

    assertThat(agenda.getIdiomas()).isEqualTo("en,es");
  }
}