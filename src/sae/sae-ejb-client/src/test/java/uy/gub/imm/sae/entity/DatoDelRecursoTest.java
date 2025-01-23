package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatoDelRecursoTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        DatoDelRecurso datoDelRecurso1 = new DatoDelRecurso(1);
        DatoDelRecurso datoDelRecurso2 = new DatoDelRecurso(1);

        assertTrue(datoDelRecurso1.equals(datoDelRecurso2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        DatoDelRecurso datoDelRecurso1 = new DatoDelRecurso(1);
        DatoDelRecurso datoDelRecurso2 = new DatoDelRecurso();
        datoDelRecurso2.setId(null);

        assertFalse(datoDelRecurso1.equals(datoDelRecurso2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new DatoDelRecurso(1).hashCode();
        int hashCode2 = new DatoDelRecurso(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}