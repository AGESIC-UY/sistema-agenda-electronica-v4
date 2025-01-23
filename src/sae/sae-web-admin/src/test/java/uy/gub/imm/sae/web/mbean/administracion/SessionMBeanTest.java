package uy.gub.imm.sae.web.mbean.administracion;

import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import uy.gub.imm.sae.business.ejb.facade.UsuariosEmpresasLocal;
import uy.gub.imm.sae.entity.global.Organismo;

import javax.faces.context.FacesContext;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

class SessionMBeanTest {

    @Test
    void whenLanguageComboShown_thenItHasMoreThanOneLanguage() {
        try (MockedStatic<FacesContext> context = mockStatic(FacesContext.class, Mockito.RETURNS_DEEP_STUBS);) {
            SessionMBean sessionMBean = new SessionMBean();
            UsuariosEmpresasLocal usuariosEmpresasEJB = mock(UsuariosEmpresasLocal.class, Answers.RETURNS_DEEP_STUBS);
            List<String> idiomas = Arrays.asList("español", "inglés", "ruso");
            when(usuariosEmpresasEJB.obtenerIdiomasSoportados(anyInt())).thenReturn(idiomas);
            sessionMBean.setUsuariosEmpresasEJB(usuariosEmpresasEJB);
            Organismo organismo = new Organismo();
            organismo.setId(1);
            organismo.setCodigo("123");
            organismo.setNombre("Nombre Organismo");
            sessionMBean.setOrganismoActual(organismo);
            assertThat(sessionMBean.isLanguageComboShown()).isTrue();
            verify(usuariosEmpresasEJB, atLeastOnce()).obtenerIdiomasSoportados(anyInt());
        }
    }

    @Test
    void whenLanguageComboShown_thenItJustHasOneLanguage() {
        try (MockedStatic<FacesContext> context = mockStatic(FacesContext.class, Mockito.RETURNS_DEEP_STUBS);) {
            SessionMBean sessionMBean = new SessionMBean();
            UsuariosEmpresasLocal usuariosEmpresasEJB = mock(UsuariosEmpresasLocal.class, Answers.RETURNS_DEEP_STUBS);
            List<String> idiomas = Arrays.asList("español");
            when(usuariosEmpresasEJB.obtenerIdiomasSoportados(anyInt())).thenReturn(idiomas);
            sessionMBean.setUsuariosEmpresasEJB(usuariosEmpresasEJB);
            Organismo organismo = new Organismo();
            organismo.setId(1);
            organismo.setCodigo("123");
            organismo.setNombre("Nombre Organismo");
            sessionMBean.setOrganismoActual(organismo);
            assertThat(sessionMBean.isLanguageComboShown()).isFalse();
            verify(usuariosEmpresasEJB, atLeastOnce()).obtenerIdiomasSoportados(anyInt());
        }
    }

    @Test
    void givenValidFecha_whenGetFechaActual_thenParseSuccessfully() {
        try (var context = mockStatic(FacesContext.class, Mockito.RETURNS_DEEP_STUBS)) {
            var fechaActual = new FakeSessionMBean().getFechaActual();
            assertThat(fechaActual).isEqualTo("04/08/2022 01:25");
        }
    }

    class FakeSessionMBean extends SessionMBean {
        @Override
        LocalDateTime getCurrentLocalDateTime(ZoneId zoneId) {
            return Instant.now(Clock.fixed(Instant.parse("2022-08-04T04:25:09.697540200Z"), ZoneId.of("UTC"))).atZone(ZoneId.of("America/Montevideo")).toLocalDateTime();
        }
    }
}