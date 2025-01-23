package uy.gub.imm.sae.entity.global;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmpresaTest {

    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        Empresa empresa1 = new Empresa(1);
        Empresa empresa2 = new Empresa(1);

        assertTrue(empresa1.equals(empresa2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        Empresa empresa1 = new Empresa(1);
        Empresa empresa2 = new Empresa();
        empresa2.setId(null);

        assertFalse(empresa1.equals(empresa2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new Empresa(1).hashCode();
        int hashCode2 = new Empresa().hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}