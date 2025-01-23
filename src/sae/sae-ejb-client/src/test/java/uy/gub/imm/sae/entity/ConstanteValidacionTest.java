package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConstanteValidacionTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        ConstanteValidacion constanteValidacion1 = new ConstanteValidacion(1);
        ConstanteValidacion constanteValidacion2 = new ConstanteValidacion(1);

        assertTrue(constanteValidacion1.equals(constanteValidacion2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        ConstanteValidacion constanteValidacion1 = new ConstanteValidacion(1);
        ConstanteValidacion constanteValidacion2 = new ConstanteValidacion();
        constanteValidacion2.setId(null);

        assertFalse(constanteValidacion1.equals(constanteValidacion2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new ConstanteValidacion(1).hashCode();
        int hashCode2 = new ConstanteValidacion(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}