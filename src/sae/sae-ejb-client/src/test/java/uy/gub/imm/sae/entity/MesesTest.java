package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MesesTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        Meses meses1 = new Meses(1);
        Meses meses2 = new Meses(1);

        assertTrue(meses1.equals(meses2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        Meses meses1 = new Meses(1);
        Meses meses2 = new Meses();
        meses2.setId(null);

        assertFalse(meses1.equals(meses2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new Meses(1).hashCode();
        int hashCode2 = new Meses(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}