package uy.gub.imm.sae.entity.global;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrganismoTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        Organismo organismo1 = new Organismo(1);
        Organismo organismo2 = new Organismo(1);

        assertTrue(organismo1.equals(organismo2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        Organismo organismo1 = new Organismo(1);
        Organismo organismo2 = new Organismo();
        organismo2.setId(null);

        assertFalse(organismo1.equals(organismo2));
    }

    @Test
    void givenObjectsWithSameClave_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new Organismo(1).hashCode();
        int hashCode2 = new Organismo(1).hashCode();

        assertEquals(hashCode1, hashCode2);
    }

    @Test
    void givenObjectsWithDifferentClave_whenHashcode_thenReturnDifferentNumber() {
        int hashCode1 = new Organismo(1).hashCode();
        int hashCode2 = new Organismo().hashCode();

        assertNotEquals(hashCode1, hashCode2);
    }
}