package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccionPorRecursoTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        AccionPorRecurso accionPorRecurso1 = new AccionPorRecurso(1);
        AccionPorRecurso accionPorRecurso2 = new AccionPorRecurso(1);

        assertTrue(accionPorRecurso1.equals(accionPorRecurso2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        AccionPorRecurso accionPorRecurso1 = new AccionPorRecurso(1);
        AccionPorRecurso accionPorRecurso2 = new AccionPorRecurso();
        accionPorRecurso2.setId(null);

        assertFalse(accionPorRecurso1.equals(accionPorRecurso2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new AccionPorRecurso(1).hashCode();
        int hashCode2 = new AccionPorRecurso(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}