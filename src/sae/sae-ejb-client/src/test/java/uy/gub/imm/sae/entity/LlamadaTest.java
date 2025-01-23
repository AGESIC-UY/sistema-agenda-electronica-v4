package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LlamadaTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        Llamada llamada1 = new Llamada(1);
        Llamada llamada2 = new Llamada(1);

        assertTrue(llamada1.equals(llamada2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        Llamada llamada1 = new Llamada(1);
        Llamada llamada2 = new Llamada();
        llamada2.setId(null);

        assertFalse(llamada1.equals(llamada2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new Llamada(1).hashCode();
        int hashCode2 = new Llamada(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}