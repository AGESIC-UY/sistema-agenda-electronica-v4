package uy.gub.imm.sae.entity.global;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsuarioTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        Usuario usuario1 = new Usuario(1);
        Usuario usuario2 = new Usuario(1);

        assertTrue(usuario1.equals(usuario2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        Usuario usuario1 = new Usuario(1);
        Usuario usuario2 = new Usuario();
        usuario2.setId(null);

        assertFalse(usuario1.equals(usuario2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new Usuario(1).hashCode();
        int hashCode2 = new Usuario(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}