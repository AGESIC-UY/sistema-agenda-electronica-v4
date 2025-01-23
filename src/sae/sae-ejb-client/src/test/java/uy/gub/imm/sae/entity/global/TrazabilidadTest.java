package uy.gub.imm.sae.entity.global;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrazabilidadTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        Trazabilidad trazabilidad1 = new Trazabilidad(1);
        Trazabilidad trazabilidad2 = new Trazabilidad(1);

        assertTrue(trazabilidad1.equals(trazabilidad2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        Trazabilidad trazabilidad1 = new Trazabilidad(1);
        Trazabilidad trazabilidad2 = new Trazabilidad();
        trazabilidad2.setId(null);

        assertFalse(trazabilidad1.equals(trazabilidad2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new Trazabilidad(1).hashCode();
        int hashCode2 = new Trazabilidad(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}