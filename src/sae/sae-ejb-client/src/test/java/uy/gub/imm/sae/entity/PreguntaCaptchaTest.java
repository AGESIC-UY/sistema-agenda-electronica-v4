package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PreguntaCaptchaTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        PreguntaCaptcha preguntaCaptcha1 = new PreguntaCaptcha("1");
        PreguntaCaptcha preguntaCaptcha2 = new PreguntaCaptcha("1");

        assertTrue(preguntaCaptcha1.equals(preguntaCaptcha2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        PreguntaCaptcha preguntaCaptcha1 = new PreguntaCaptcha("1");
        PreguntaCaptcha preguntaCaptcha2 = new PreguntaCaptcha(null);

        assertFalse(preguntaCaptcha1.equals(preguntaCaptcha2));
    }

    @Test
    void givenObjectsWithSameClave_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new PreguntaCaptcha("1").hashCode();
        int hashCode2 = new PreguntaCaptcha("1").hashCode();

        assertEquals(hashCode1, hashCode2);
    }

    @Test
    void givenObjectsWithDifferentClave_whenHashcode_thenReturnDifferentNumber() {
        int hashCode1 = new PreguntaCaptcha("1").hashCode();
        int hashCode2 = new PreguntaCaptcha().hashCode();

        assertNotEquals(hashCode1, hashCode2);
    }
}