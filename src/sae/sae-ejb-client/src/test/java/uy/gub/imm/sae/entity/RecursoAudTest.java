package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RecursoAudTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        RecursoAud recursoAud1 = new RecursoAud(1);
        RecursoAud recursoAud2 = new RecursoAud(1);

        assertTrue(recursoAud1.equals(recursoAud2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        RecursoAud recursoAud1 = new RecursoAud(1);
        RecursoAud recursoAud2 = new RecursoAud();
        recursoAud2.setId(null);

        assertFalse(recursoAud1.equals(recursoAud2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new RecursoAud(1).hashCode();
        int hashCode2 = new RecursoAud(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}