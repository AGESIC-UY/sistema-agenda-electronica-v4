package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccionMiPerfilTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        AccionMiPerfil accionMiPerfil1 = new AccionMiPerfil(1);
        AccionMiPerfil accionMiPerfil2 = new AccionMiPerfil(1);

        assertTrue(accionMiPerfil1.equals(accionMiPerfil2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        AccionMiPerfil accionMiPerfil1 = new AccionMiPerfil(1);
        AccionMiPerfil accionMiPerfil2 = new AccionMiPerfil();
        accionMiPerfil2.setId(null);

        assertFalse(accionMiPerfil1.equals(accionMiPerfil2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new AccionMiPerfil(1).hashCode();
        int hashCode2 = new AccionMiPerfil(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}