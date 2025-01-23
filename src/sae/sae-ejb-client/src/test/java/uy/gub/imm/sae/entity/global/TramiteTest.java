package uy.gub.imm.sae.entity.global;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TramiteTest {

    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        Tramite tramite1 = new Tramite("1");
        Tramite tramite2 = new Tramite("1");

        assertTrue(tramite1.equals(tramite2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        Tramite tramite1 = new Tramite("1");
        Tramite tramite2 = new Tramite();
        tramite2.setId(null);

        assertFalse(tramite1.equals(tramite2));
    }

    @Test
    void givenObjectsWithSameClave_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new Tramite("1").hashCode();
        int hashCode2 = new Tramite("1").hashCode();

        assertEquals(hashCode1, hashCode2);
    }

    @Test
    void givenObjectsWithDifferentClave_whenHashcode_thenReturnDifferentNumber() {
        int hashCode1 = new Tramite("1").hashCode();
        int hashCode2 = new Tramite().hashCode();

        assertNotEquals(hashCode1, hashCode2);
    }
}