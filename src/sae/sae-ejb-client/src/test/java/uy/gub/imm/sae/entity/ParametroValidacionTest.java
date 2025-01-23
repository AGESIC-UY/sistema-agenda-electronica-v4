package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParametroValidacionTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        ParametroValidacion parametroValidacion1 = new ParametroValidacion(1);
        ParametroValidacion parametroValidacion2 = new ParametroValidacion(1);

        assertTrue(parametroValidacion1.equals(parametroValidacion2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        ParametroValidacion parametroValidacion1 = new ParametroValidacion(1);
        ParametroValidacion parametroValidacion2 = new ParametroValidacion();
        parametroValidacion2.setId(null);

        assertFalse(parametroValidacion1.equals(parametroValidacion2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new ParametroValidacion(1).hashCode();
        int hashCode2 = new ParametroValidacion(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}