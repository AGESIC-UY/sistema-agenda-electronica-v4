package uy.gub.imm.sae.business.ejb.facade;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import uy.gub.imm.sae.entity.Recurso;
import uy.gub.imm.sae.exception.UserException;

class RecursosBeanTest {

  @Test
  @SuppressWarnings("unchecked")
  void givenValidAgendaIdWhenConsultarRecursoByAgendaIdThenReturnRecursos() throws UserException {
    RecursosBean recursosBean = new RecursosBean();
    EntityManager entityManager = mock(EntityManager.class, RETURNS_DEEP_STUBS);
    recursosBean.setEntityManager(entityManager);
    when(
        entityManager.createQuery(anyString(), any(Class.class))
            .setParameter(anyString(), anyInt())
            .getResultList()).thenReturn(Collections.singletonList(new Recurso()));

    List<Recurso> recursos = recursosBean.consultarRecursoByAgendaId(1);

    assertThat(recursos).isNotNull().isNotEmpty();
  }

  @Test
  @SuppressWarnings("unchecked")
  void givenInvalidAgendaIdWhenConsultarRecursoByAgendaIdThenThrowException() {
    RecursosBean recursosBean = new RecursosBean();
    EntityManager entityManager = mock(EntityManager.class, RETURNS_DEEP_STUBS);
    recursosBean.setEntityManager(entityManager);
    when(
        entityManager.createQuery(anyString(), any(Class.class)).setParameter(anyString(), anyInt())
            .getResultList()).thenReturn(null);

    assertThatThrownBy(() -> recursosBean.consultarRecursoByAgendaId(0)).
        isInstanceOf(UserException.class)
        .hasFieldOrPropertyWithValue("codigoError", "no_se_encuentra_el_recurso_especificado");
  }
}
