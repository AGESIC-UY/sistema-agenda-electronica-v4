package uy.gub.imm.sae.entity.global;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfiguracionTest {

    @Test
    void givenObjectsWithSameId_whenEqual_thenReturnTrue() {
        Configuracion config1 = new Configuracion("clave");
        Configuracion config2 = new Configuracion("clave");

        assertTrue(config1.equals(config2));
    }

    @Test
    void givenObjectsWithDifferentId_whenEqual_thenReturnFalse() {
        Configuracion config1 = new Configuracion("clave");
        Configuracion config2 = new Configuracion("clave1");

        assertFalse(config1.equals(config2));
    }

    @Test
    void givenObjectsWithSameClave_whenHashcode_thenReturnSameNumber() {
        int hashCode1 = new Configuracion("clave").hashCode();
        int hashCode2 = new Configuracion("clave").hashCode();

        assertEquals(hashCode1, hashCode2);
    }

    @Test
    void givenObjectsWithDifferentClave_whenHashcode_thenReturnDifferentNumber() {
        int hashCode1 = new Configuracion("clave").hashCode();
        int hashCode2 = new Configuracion().hashCode();

        assertNotEquals(hashCode1, hashCode2);
    }
}