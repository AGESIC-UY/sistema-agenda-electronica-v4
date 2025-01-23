package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AgendaTest {

    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        Agenda agenda1 = new Agenda(1);
        Agenda agenda2 = new Agenda(1);

        assertTrue(agenda1.equals(agenda2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        Agenda agenda1 = new Agenda(1);
        Agenda agenda2 = new Agenda();
        agenda2.setId(null);

        assertFalse(agenda1.equals(agenda2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new Agenda(1).hashCode();
        int hashCode2 = new Agenda(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}