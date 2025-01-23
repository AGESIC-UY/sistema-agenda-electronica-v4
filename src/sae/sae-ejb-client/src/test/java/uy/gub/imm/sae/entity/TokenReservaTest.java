package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TokenReservaTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        TokenReserva tokenTokenReserva1 = new TokenReserva(1);
        TokenReserva tokenTokenReserva2 = new TokenReserva(1);

        assertTrue(tokenTokenReserva1.equals(tokenTokenReserva2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        TokenReserva tokenTokenReserva1 = new TokenReserva(1);
        TokenReserva tokenTokenReserva2 = new TokenReserva();
        tokenTokenReserva2.setId(null);

        assertFalse(tokenTokenReserva1.equals(tokenTokenReserva2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new TokenReserva(1).hashCode();
        int hashCode2 = new TokenReserva(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }

}