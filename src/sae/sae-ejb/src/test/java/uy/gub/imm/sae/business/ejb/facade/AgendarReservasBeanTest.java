package uy.gub.imm.sae.business.ejb.facade;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import uy.gub.imm.sae.entity.Agenda;
import uy.gub.imm.sae.entity.Recurso;
import uy.gub.imm.sae.exception.ApplicationException;
import uy.gub.imm.sae.exception.BusinessException;


class AgendarReservasBeanTest {

  @Test
  void givenValidRecursoWhenIsRecursoVisibleInternetThenReturnTrue()
      throws BusinessException, ApplicationException {
    AgendarReservasBean reservasBean = new AgendarReservasBean();
    Recurso recurso = new Recurso();
    recurso.setId(1);
    Agenda agenda = new Agenda();
    agenda.setId(1);
    recurso.setAgenda(agenda);
    recurso.setVisibleInternet(true);
    EntityManager entityManager = mock(EntityManager.class, RETURNS_DEEP_STUBS);
    reservasBean.setEntityManager(entityManager);
    when(entityManager.createQuery(contains("Agenda")).setParameter(anyString(), any())
        .getSingleResult()).thenReturn(agenda);
    when(entityManager.createQuery(contains("Recurso")).setParameter(anyString(), any())
        .setParameter(anyString(), any())
        .getSingleResult()).thenReturn(recurso);

    boolean result = reservasBean.isRecursoVisibleInternet(recurso);

    assertTrue(result);
  }

  @Test
  void givenInvalidRecursoWhenIsRecursoVisibleInternetThenReturnFalse()
      throws BusinessException, ApplicationException {
    AgendarReservasBean reservasBean = new AgendarReservasBean();
    Recurso recurso = new Recurso();
    recurso.setId(1);
    Agenda agenda = new Agenda();
    agenda.setId(1);
    recurso.setAgenda(agenda);
    recurso.setVisibleInternet(false);
    EntityManager entityManager = mock(EntityManager.class, RETURNS_DEEP_STUBS);
    reservasBean.setEntityManager(entityManager);
    when(entityManager.createQuery(contains("Agenda")).setParameter(anyString(), any())
        .getSingleResult()).thenReturn(agenda);
    when(entityManager.createQuery(contains("Recurso")).setParameter(anyString(), any())
        .setParameter(anyString(), any())
        .getSingleResult()).thenReturn(recurso);

    boolean result = reservasBean.isRecursoVisibleInternet(recurso);

    assertFalse(result);
  }

 }