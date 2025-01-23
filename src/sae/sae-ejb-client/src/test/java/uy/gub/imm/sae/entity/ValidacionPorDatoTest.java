package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidacionPorDatoTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        ValidacionPorDato validacionPorDato1 = new ValidacionPorDato(1);
        ValidacionPorDato validacionPorDato2 = new ValidacionPorDato(1);

        assertTrue(validacionPorDato1.equals(validacionPorDato2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        ValidacionPorDato validacionPorDato1 = new ValidacionPorDato(1);
        ValidacionPorDato validacionPorDato2 = new ValidacionPorDato();
        validacionPorDato2.setId(null);

        assertFalse(validacionPorDato1.equals(validacionPorDato2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new ValidacionPorDato(1).hashCode();
        int hashCode2 = new ValidacionPorDato(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }

}