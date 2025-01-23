package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParametroAccionTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        ParametroAccion parametroAccion1 = new ParametroAccion(1);
        ParametroAccion parametroAccion2 = new ParametroAccion(1);

        assertTrue(parametroAccion1.equals(parametroAccion2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        ParametroAccion parametroAccion1 = new ParametroAccion(1);
        ParametroAccion parametroAccion2 = new ParametroAccion();
        parametroAccion2.setId(null);

        assertFalse(parametroAccion1.equals(parametroAccion2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new ParametroAccion(1).hashCode();
        int hashCode2 = new ParametroAccion(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}