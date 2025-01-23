package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccionTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        Accion accion1 = new Accion(1);
        Accion accion2 = new Accion(1);

        assertTrue(accion1.equals(accion2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        Accion accion1 = new Accion(1);
        Accion accion2 = new Accion();
        accion2.setId(null);

        assertFalse(accion1.equals(accion2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new Accion(1).hashCode();
        int hashCode2 = new Accion(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}