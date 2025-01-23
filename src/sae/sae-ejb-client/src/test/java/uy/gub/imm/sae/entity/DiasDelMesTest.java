package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DiasDelMesTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        DiasDelMes DiasDelMes1 = new DiasDelMes(1);
        DiasDelMes DiasDelMes2 = new DiasDelMes(1);

        assertTrue(DiasDelMes1.equals(DiasDelMes2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        DiasDelMes DiasDelMes1 = new DiasDelMes(1);
        DiasDelMes DiasDelMes2 = new DiasDelMes();
        DiasDelMes2.setId(null);

        assertFalse(DiasDelMes1.equals(DiasDelMes2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new DiasDelMes(1).hashCode();
        int hashCode2 = new DiasDelMes(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}