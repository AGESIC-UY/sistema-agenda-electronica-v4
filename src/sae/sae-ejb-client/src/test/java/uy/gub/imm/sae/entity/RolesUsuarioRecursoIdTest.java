package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RolesUsuarioRecursoIdTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        RolesUsuarioRecursoId rolesUsuarioRecursoId1 = new RolesUsuarioRecursoId(1, 1);
        RolesUsuarioRecursoId rolesUsuarioRecursoId2 = new RolesUsuarioRecursoId(1, 1);

        assertTrue(rolesUsuarioRecursoId1.equals(rolesUsuarioRecursoId2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        RolesUsuarioRecursoId rolesUsuarioRecursoId1 = new RolesUsuarioRecursoId(1, 1);
        RolesUsuarioRecursoId rolesUsuarioRecursoId2 = new RolesUsuarioRecursoId();

        assertFalse(rolesUsuarioRecursoId1.equals(rolesUsuarioRecursoId2));
    }

    @Test
    void givenObjectsWithSameClave_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new RolesUsuarioRecursoId(1, 1).hashCode();
        int hashCode2 = new RolesUsuarioRecursoId(1, 1).hashCode();

        assertEquals(hashCode1, hashCode2);
    }

    @Test
    void givenObjectsWithDifferentClave_whenHashcode_thenReturnDifferentNumber() {
        int hashCode1 = new RolesUsuarioRecursoId(1, 1).hashCode();
        int hashCode2 = new RolesUsuarioRecursoId().hashCode();

        assertNotEquals(hashCode1, hashCode2);
    }
}