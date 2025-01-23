package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccionPorDatoTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        AccionPorDato accionPorDato1 = new AccionPorDato(1);
        AccionPorDato accionPorDato2 = new AccionPorDato(1);

        assertTrue(accionPorDato1.equals(accionPorDato2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        AccionPorDato accionPorDato1 = new AccionPorDato(1);
        AccionPorDato accionPorDato2 = new AccionPorDato();
        accionPorDato2.setId(null);

        assertFalse(accionPorDato1.equals(accionPorDato2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new AccionPorDato(1).hashCode();
        int hashCode2 = new AccionPorDato(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}