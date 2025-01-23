package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AtencionTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        Atencion atencion1 = new Atencion(1);
        Atencion atencion2 = new Atencion(1);

        assertTrue(atencion1.equals(atencion2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        Atencion atencion1 = new Atencion(1);
        Atencion atencion2 = new Atencion();
        atencion2.setId(null);

        assertFalse(atencion1.equals(atencion2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new Atencion(1).hashCode();
        int hashCode2 = new Atencion(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}