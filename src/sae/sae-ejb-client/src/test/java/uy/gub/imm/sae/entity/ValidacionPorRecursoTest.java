package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidacionPorRecursoTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        ValidacionPorRecurso validacionPorRecurso1 = new ValidacionPorRecurso(1);
        ValidacionPorRecurso validacionPorRecurso2 = new ValidacionPorRecurso(1);

        assertTrue(validacionPorRecurso1.equals(validacionPorRecurso2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        ValidacionPorRecurso validacionPorRecurso1 = new ValidacionPorRecurso(1);
        ValidacionPorRecurso validacionPorRecurso2 = new ValidacionPorRecurso();
        validacionPorRecurso2.setId(null);

        assertFalse(validacionPorRecurso1.equals(validacionPorRecurso2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new ValidacionPorRecurso(1).hashCode();
        int hashCode2 = new ValidacionPorRecurso(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }

}