package uy.gub.imm.sae.entity.global;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NovedadTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        Novedad novedad1 = new Novedad(1);
        Novedad novedad2 = new Novedad(1);

        assertTrue(novedad1.equals(novedad2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        Novedad novedad1 = new Novedad(1);
        Novedad novedad2 = new Novedad();
        novedad2.setId(null);

        assertFalse(novedad1.equals(novedad2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new Novedad(1).hashCode();
        int hashCode2 = new Novedad().hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}