package uy.gub.imm.sae.entity.global;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TextoGlobalTest {

    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        TextoGlobal textoGlobal1 = new TextoGlobal("codigo");
        TextoGlobal textoGlobal2 = new TextoGlobal("codigo");

        assertTrue(textoGlobal1.equals(textoGlobal2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        TextoGlobal textoGlobal1 = new TextoGlobal("codigo");
        TextoGlobal textoGlobal2 = new TextoGlobal("codigo1");

        assertFalse(textoGlobal1.equals(textoGlobal2));
    }

    @Test
    void givenObjectsWithSameClave_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new TextoGlobal("codigo").hashCode();
        int hashCode2 = new TextoGlobal("codigo").hashCode();

        assertEquals(hashCode1, hashCode2);
    }

    @Test
    void givenObjectsWithDifferentClave_whenHashcode_thenReturnDifferentNumber() {
        int hashCode1 = new TextoGlobal("codigo").hashCode();
        int hashCode2 = new TextoGlobal().hashCode();

        assertNotEquals(hashCode1, hashCode2);
    }
}