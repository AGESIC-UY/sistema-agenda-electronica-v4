package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValorPosibleTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        ValorPosible valorPosible1 = new ValorPosible(1);
        ValorPosible valorPosible2 = new ValorPosible(1);

        assertTrue(valorPosible1.equals(valorPosible2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        ValorPosible valorPosible1 = new ValorPosible(1);
        ValorPosible valorPosible2 = new ValorPosible();
        valorPosible2.setId(null);

        assertFalse(valorPosible1.equals(valorPosible2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new ValorPosible(1).hashCode();
        int hashCode2 = new ValorPosible(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}