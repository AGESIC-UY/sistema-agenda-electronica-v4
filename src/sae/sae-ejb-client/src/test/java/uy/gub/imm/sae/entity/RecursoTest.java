package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RecursoTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        Recurso recurso1 = new Recurso(1);
        Recurso recurso2 = new Recurso(1);

        assertTrue(recurso1.equals(recurso2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        Recurso recurso1 = new Recurso(1);
        Recurso recurso2 = new Recurso();
        recurso2.setId(null);

        assertFalse(recurso1.equals(recurso2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new Recurso(1).hashCode();
        int hashCode2 = new Recurso(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}