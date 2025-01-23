package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TextoTenantTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        TextoTenant textoTenant1 = new TextoTenant(new TextoGlobalId("1", "1"));
        TextoTenant textoTenant2 = new TextoTenant(new TextoGlobalId("1", "1"));

        assertTrue(textoTenant1.equals(textoTenant2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        TextoTenant textoTenant1 = new TextoTenant(new TextoGlobalId("1", "1"));
        TextoTenant textoTenant2 = new TextoTenant(null);

        assertFalse(textoTenant1.equals(textoTenant2));
    }

    @Test
    void givenObjectsWithSameClave_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new TextoTenant(new TextoGlobalId("1", "1")).hashCode();
        int hashCode2 = new TextoTenant(new TextoGlobalId("1", "1")).hashCode();

        assertEquals(hashCode1, hashCode2);
    }

    @Test
    void givenObjectsWithDifferentClave_whenHashcode_thenReturnDifferentNumber() {
        int hashCode1 = new TextoTenant(new TextoGlobalId("1", "1")).hashCode();
        int hashCode2 = new TextoTenant().hashCode();

        assertNotEquals(hashCode1, hashCode2);
    }
}