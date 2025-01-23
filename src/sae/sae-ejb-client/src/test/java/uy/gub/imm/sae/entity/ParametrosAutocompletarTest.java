package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParametrosAutocompletarTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        ParametrosAutocompletar parametrosAutocompletar1 = new ParametrosAutocompletar(1);
        ParametrosAutocompletar parametrosAutocompletar2 = new ParametrosAutocompletar(1);

        assertTrue(parametrosAutocompletar1.equals(parametrosAutocompletar2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        ParametrosAutocompletar parametrosAutocompletar1 = new ParametrosAutocompletar(1);
        ParametrosAutocompletar parametrosAutocompletar2 = new ParametrosAutocompletar();
        parametrosAutocompletar2.setId(null);

        assertFalse(parametrosAutocompletar1.equals(parametrosAutocompletar2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new ParametrosAutocompletar(1).hashCode();
        int hashCode2 = new ParametrosAutocompletar(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}