package uy.gub.imm.sae.web.mbean.administracion;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.primefaces.PrimeFaces;
import uy.gub.imm.sae.business.ejb.facade.AgendarReservasLocal;
import uy.gub.imm.sae.entity.Agenda;
import uy.gub.imm.sae.entity.TramiteAgenda;
import uy.gub.imm.sae.exception.ApplicationException;
import uy.gub.imm.sae.exception.BusinessException;

import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

class AtencionPresencialMBeanTest {

    @Test
    void givenCambioAgendaSeleccionadaInit() throws ApplicationException {

        try (MockedStatic<FacesContext> context = mockStatic(FacesContext.class,
                 Mockito.RETURNS_DEEP_STUBS);) {
            AtencionPresencialMBean atencionBean = new AtencionPresencialMBean();
            AgendarReservasLocal agendarReservasEJB = mock(AgendarReservasLocal.class, RETURNS_DEEP_STUBS);
            SessionMBean sessionMBean = mock(SessionMBean.class, RETURNS_DEEP_STUBS);
            Agenda agenda = new Agenda();
            agenda.setId(32);
            when(sessionMBean.getAgendaMarcada()).thenReturn(agenda);
            List<TramiteAgenda> listaTramites = new ArrayList<>();
            TramiteAgenda tramite1 = new TramiteAgenda();
            tramite1.setId(33);
            tramite1.setAgenda(new Agenda());
            tramite1.setTramiteId("2-4052");
            tramite1.setTramiteCodigo("4052");
            tramite1.setTramiteNombre("Acreditaciones a eventos");
            TramiteAgenda tramite2 = new TramiteAgenda();
            tramite2.setId(34);
            tramite2.setAgenda(new Agenda());
            tramite2.setTramiteId("2-2608");
            tramite2.setTramiteCodigo("2608");
            tramite2.setTramiteNombre("Acreditaciones para la Prensa");
            listaTramites.add(tramite1);
            listaTramites.add(tramite2);
            when(agendarReservasEJB.consultarTramites(any())).thenReturn(listaTramites);
            atencionBean.setSessionMBean(sessionMBean);
            atencionBean.setAgendarReservasEJB(agendarReservasEJB);
            atencionBean.cambioAgendaSeleccionadaInit();
            assertThat(atencionBean.getTramites().size()).isEqualTo(3);
            verify(agendarReservasEJB, times(1)).consultarTramites(any());
            verify(sessionMBean, times(1)).getAgendaMarcada();
        }
    }

}