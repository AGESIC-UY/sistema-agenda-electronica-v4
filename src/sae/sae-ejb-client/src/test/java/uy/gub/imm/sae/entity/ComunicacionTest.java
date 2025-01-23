package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ComunicacionTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        Comunicacion comunicacion1 = new Comunicacion(1);
        Comunicacion comunicacion2 = new Comunicacion(1);

        assertTrue(comunicacion1.equals(comunicacion2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        Comunicacion comunicacion1 = new Comunicacion(1);
        Comunicacion comunicacion2 = new Comunicacion();
        comunicacion2.setId(null);

        assertFalse(comunicacion1.equals(comunicacion2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new Comunicacion(1).hashCode();
        int hashCode2 = new Comunicacion(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}