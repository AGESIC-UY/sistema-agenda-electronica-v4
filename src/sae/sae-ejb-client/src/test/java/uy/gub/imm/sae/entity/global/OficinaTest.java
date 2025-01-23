package uy.gub.imm.sae.entity.global;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OficinaTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        Oficina oficina1 = new Oficina("1");
        Oficina oficina2 = new Oficina("1");

        assertTrue(oficina1.equals(oficina2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        Oficina oficina1 = new Oficina("1");
        Oficina oficina2 = new Oficina();
        oficina2.setId(null);

        assertFalse(oficina1.equals(oficina2));
    }

    @Test
    void givenObjectsWithSameClave_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new Oficina("1").hashCode();
        int hashCode2 = new Oficina("1").hashCode();

        assertEquals(hashCode1, hashCode2);
    }

    @Test
    void givenObjectsWithDifferentClave_whenHashcode_thenReturnDifferentNumber() {
        int hashCode1 = new Oficina("1").hashCode();
        int hashCode2 = new Oficina().hashCode();

        assertNotEquals(hashCode1, hashCode2);
    }
}