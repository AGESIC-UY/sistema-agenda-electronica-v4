package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ServicioPorRecursoTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        ServicioPorRecurso servicioPorRecurso1 = new ServicioPorRecurso(1);
        ServicioPorRecurso servicioPorRecurso2 = new ServicioPorRecurso(1);

        assertTrue(servicioPorRecurso1.equals(servicioPorRecurso2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        ServicioPorRecurso servicioPorRecurso1 = new ServicioPorRecurso(1);
        ServicioPorRecurso servicioPorRecurso2 = new ServicioPorRecurso();
        servicioPorRecurso2.setId(null);

        assertFalse(servicioPorRecurso1.equals(servicioPorRecurso2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new ServicioPorRecurso(1).hashCode();
        int hashCode2 = new ServicioPorRecurso(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}