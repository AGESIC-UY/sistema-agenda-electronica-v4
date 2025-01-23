package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReservaTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        Reserva reserva1 = new Reserva(1);
        Reserva reserva2 = new Reserva(1);

        assertTrue(reserva1.equals(reserva2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        Reserva reserva1 = new Reserva(1);
        Reserva reserva2 = new Reserva();
        reserva2.setId(null);

        assertFalse(reserva1.equals(reserva2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new Reserva(1).hashCode();
        int hashCode2 = new Reserva(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}