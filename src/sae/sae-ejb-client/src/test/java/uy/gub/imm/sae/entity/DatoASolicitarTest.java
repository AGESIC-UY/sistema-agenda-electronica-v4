package uy.gub.imm.sae.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatoASolicitarTest {
    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        DatoASolicitar datoASolicitar1 = new DatoASolicitar(1);
        DatoASolicitar datoASolicitar2 = new DatoASolicitar(1);

        assertTrue(datoASolicitar1.equals(datoASolicitar2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        DatoASolicitar datoASolicitar1 = new DatoASolicitar(1);
        DatoASolicitar datoASolicitar2 = new DatoASolicitar();
        datoASolicitar2.setId(null);

        assertFalse(datoASolicitar1.equals(datoASolicitar2));
    }

    @Test
    void givenObjectsFromSameClass_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new DatoASolicitar(1).hashCode();
        int hashCode2 = new DatoASolicitar(2).hashCode();

        assertEquals(hashCode1, hashCode2);
    }
}