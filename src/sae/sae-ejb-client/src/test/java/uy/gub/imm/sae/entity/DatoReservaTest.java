package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatoReservaTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        DatoReserva datoReserva1 = new DatoReserva(1);
        DatoReserva datoReserva2 = new DatoReserva(1);

        assertTrue(datoReserva1.equals(datoReserva2));
    }

    @Test
    void givenObjectsWithSameIdAndValor_whenEqual_thenReturnTrue() {
        DatoReserva datoReserva1 = new DatoReserva(1, "valor", null);
        DatoReserva datoReserva2 = new DatoReserva(1, "valor", null);

        assertTrue(datoReserva1.equals(datoReserva2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        DatoReserva datoReserva1 = new DatoReserva(1);
        DatoReserva datoReserva2 = new DatoReserva();
        datoReserva2.setId(null);

        assertFalse(datoReserva1.equals(datoReserva2));
    }

    @Test
    void givenObjectsWithSameIdAndDifferentValor_whenEqual_thenReturnFalse() {
        DatoReserva datoReserva1 = new DatoReserva(1, "valor", null);
        DatoReserva datoReserva2 = new DatoReserva(1, "valor1", null);

        assertFalse(datoReserva1.equals(datoReserva2));
    }

    @Test
    void givenObjectsWithSameIdAndDifferentDatoASolicitar_whenEqual_thenReturnFalse() {
        DatoReserva datoReserva1 = new DatoReserva(1, "valor", new DatoASolicitar(1));
        DatoReserva datoReserva2 = new DatoReserva(1, "valor", new DatoASolicitar(2));

        assertFalse(datoReserva1.equals(datoReserva2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new DatoReserva(1).hashCode();
        int hashCode2 = new DatoReserva(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}