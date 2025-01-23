package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValorConstanteValidacionRecursoTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        ValorConstanteValidacionRecurso valorConstanteValidacionRecurso1 = new ValorConstanteValidacionRecurso(1);
        ValorConstanteValidacionRecurso valorConstanteValidacionRecurso2 = new ValorConstanteValidacionRecurso(1);

        assertTrue(valorConstanteValidacionRecurso1.equals(valorConstanteValidacionRecurso2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        ValorConstanteValidacionRecurso valorConstanteValidacionRecurso1 = new ValorConstanteValidacionRecurso(1);
        ValorConstanteValidacionRecurso valorConstanteValidacionRecurso2 = new ValorConstanteValidacionRecurso();
        valorConstanteValidacionRecurso2.setId(null);

        assertFalse(valorConstanteValidacionRecurso1.equals(valorConstanteValidacionRecurso2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new ValorConstanteValidacionRecurso(1).hashCode();
        int hashCode2 = new ValorConstanteValidacionRecurso(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }

}