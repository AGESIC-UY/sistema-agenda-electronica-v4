package uy.gub.imm.sae.entity.global;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UnidadEjecutoraTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        UnidadEjecutora unidadEjecutora1 = new UnidadEjecutora(1);
        UnidadEjecutora unidadEjecutora2 = new UnidadEjecutora(1);

        assertTrue(unidadEjecutora1.equals(unidadEjecutora2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        UnidadEjecutora unidadEjecutora1 = new UnidadEjecutora(1);
        UnidadEjecutora unidadEjecutora2 = new UnidadEjecutora();
        unidadEjecutora2.setId(null);

        assertFalse(unidadEjecutora1.equals(unidadEjecutora2));
    }

    @Test
    void givenObjectsWithSameClave_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new UnidadEjecutora(1).hashCode();
        int hashCode2 = new UnidadEjecutora(1).hashCode();

        assertEquals(hashCode1, hashCode2);
    }

    @Test
    void givenObjectsWithDifferentClave_whenHashcode_thenReturnDifferentNumber() {
        int hashCode1 = new UnidadEjecutora(1).hashCode();
        int hashCode2 = new UnidadEjecutora().hashCode();

        assertNotEquals(hashCode1, hashCode2);
    }
}