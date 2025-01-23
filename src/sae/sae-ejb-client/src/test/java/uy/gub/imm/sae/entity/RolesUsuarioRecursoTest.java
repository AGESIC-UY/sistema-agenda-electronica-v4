package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RolesUsuarioRecursoTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        RolesUsuarioRecurso rolesUsuarioRecurso1 = new RolesUsuarioRecurso(new RolesUsuarioRecursoId(1, 1));
        RolesUsuarioRecurso rolesUsuarioRecurso2 = new RolesUsuarioRecurso(new RolesUsuarioRecursoId(1, 1));

        assertTrue(rolesUsuarioRecurso1.equals(rolesUsuarioRecurso2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        RolesUsuarioRecurso rolesUsuarioRecurso1 = new RolesUsuarioRecurso(new RolesUsuarioRecursoId(1, 1));
        RolesUsuarioRecurso rolesUsuarioRecurso2 = new RolesUsuarioRecurso(null);

        assertFalse(rolesUsuarioRecurso1.equals(rolesUsuarioRecurso2));
    }

    @Test
    void givenObjectsWithSameClave_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new RolesUsuarioRecurso(new RolesUsuarioRecursoId(1, 1)).hashCode();
        int hashCode2 = new RolesUsuarioRecurso(new RolesUsuarioRecursoId(1, 1)).hashCode();

        assertEquals(hashCode1, hashCode2);
    }

    @Test
    void givenObjectsWithDifferentClave_whenHashcode_thenReturnDifferentNumber() {
        int hashCode1 = new RolesUsuarioRecurso(new RolesUsuarioRecursoId(1, 1)).hashCode();
        int hashCode2 = new RolesUsuarioRecurso().hashCode();

        assertNotEquals(hashCode1, hashCode2);
    }
}