package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ServicioAutocompletarPorDatoTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        ServicioAutocompletar servicioAutocompletar1 = new ServicioAutocompletar(1);
        ServicioAutocompletar servicioAutocompletar2 = new ServicioAutocompletar(1);

        assertTrue(servicioAutocompletar1.equals(servicioAutocompletar2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        ServicioAutocompletar servicioAutocompletar1 = new ServicioAutocompletar(1);
        ServicioAutocompletar servicioAutocompletar2 = new ServicioAutocompletar();
        servicioAutocompletar2.setId(null);

        assertFalse(servicioAutocompletar1.equals(servicioAutocompletar2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new ServicioAutocompletar(1).hashCode();
        int hashCode2 = new ServicioAutocompletar(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}