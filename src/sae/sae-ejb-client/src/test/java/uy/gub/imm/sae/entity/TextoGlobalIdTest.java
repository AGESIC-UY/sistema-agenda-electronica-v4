package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TextoGlobalIdTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        TextoGlobalId textoGlobalId1 = new TextoGlobalId("1", "1");
        TextoGlobalId textoGlobalId2 = new TextoGlobalId("1", "1");

        assertTrue(textoGlobalId1.equals(textoGlobalId2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        TextoGlobalId textoGlobalId1 = new TextoGlobalId("1", "1");
        TextoGlobalId textoGlobalId2 = new TextoGlobalId();

        assertFalse(textoGlobalId1.equals(textoGlobalId2));
    }

    @Test
    void givenObjectsWithSameClave_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new TextoGlobalId("1", "en").hashCode();
        int hashCode2 = new TextoGlobalId("1", "en").hashCode();

        assertEquals(hashCode1, hashCode2);
    }

    @Test
    void givenObjectsWithDifferentClave_whenHashcode_thenReturnDifferentNumber() {
        int hashCode1 = new TextoGlobalId("1", "en").hashCode();
        int hashCode2 = new TextoGlobalId().hashCode();

        assertNotEquals(hashCode1, hashCode2);
    }
}