package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidacionTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        Validacion validacion1 = new Validacion(1);
        Validacion validacion2 = new Validacion(1);

        assertTrue(validacion1.equals(validacion2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        Validacion validacion1 = new Validacion(1);
        Validacion validacion2 = new Validacion();
        validacion2.setId(null);

        assertFalse(validacion1.equals(validacion2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new Validacion(1).hashCode();
        int hashCode2 = new Validacion(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }

}