package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TextoRecursoTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        TextoRecurso textoRecurso1 = new TextoRecurso(1);
        TextoRecurso textoRecurso2 = new TextoRecurso(1);

        assertTrue(textoRecurso1.equals(textoRecurso2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        TextoRecurso textoRecurso1 = new TextoRecurso(1);
        TextoRecurso textoRecurso2 = new TextoRecurso();
        textoRecurso2.setId(null);

        assertFalse(textoRecurso1.equals(textoRecurso2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new TextoRecurso(1).hashCode();
        int hashCode2 = new TextoRecurso(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}