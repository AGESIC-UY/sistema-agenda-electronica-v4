package uy.com.sofis.business.validaciones;

import org.junit.jupiter.api.Test;
import uy.com.sofis.entities.Usuario;
import uy.com.sofis.exceptions.BusinessException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsuarioBusinessValidationTest {

    @Test
    void whenValidar_thenPasswordIsBank() {
        Usuario usuario = usuarioBuilder();
        UsuarioBusinessValidation.validar(usuario);
        assertTrue(UsuarioBusinessValidation.validar(usuario));
    }

    @Test
    void whenValidar_thenUserIsNull() {

        Usuario usuario = null;
        Exception exception = assertThrows(BusinessException.class, () -> {
            UsuarioBusinessValidation.validar(usuario);
        });
        String expectedMessage = "Excepción de negocio";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.equals(expectedMessage));

    }

    @Test
    void whenValidar_thenCodigoIsBlank() {
        Usuario usuario = usuarioBuilder();
        usuario.setCodigo("");
        Exception exception = assertThrows(BusinessException.class, () -> {
            UsuarioBusinessValidation.validar(usuario);
        });
        String expectedMessage = "Excepción de negocio";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    void whenValidar_thenCodigoGreaterThan25() {
        Usuario usuario = usuarioBuilder();
        usuario.setCodigo("12345678901234567890123456");
        Exception exception = assertThrows(BusinessException.class, () -> {
            UsuarioBusinessValidation.validar(usuario);
        });
        String expectedMessage = "Excepción de negocio";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    void whenValidar_thenNombreIsBank() {
        Usuario usuario = usuarioBuilder();
        usuario.setNombre("");
        Exception exception = assertThrows(BusinessException.class, () -> {
            UsuarioBusinessValidation.validar(usuario);
        });
        String expectedMessage = "Excepción de negocio";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    void whenValidar_thenNombreIsGreaterThan100() {
        Usuario usuario = usuarioBuilder();
        usuario.setNombre("12345678901234567890123456789012345678901234567890"
        + "123456789012345678901234567890123456789012345678901");
        Exception exception = assertThrows(BusinessException.class, () -> {
            UsuarioBusinessValidation.validar(usuario);
        });
        String expectedMessage = "Excepción de negocio";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    void whenValidar_thenCorreoIsBlank() {
        Usuario usuario = usuarioBuilder();
        usuario.setCorreoe("");
        Exception exception = assertThrows(BusinessException.class, () -> {
            UsuarioBusinessValidation.validar(usuario);
        });
        String expectedMessage = "Excepción de negocio";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    void whenValidar_thenCorreoIsInvalid() {
        Usuario usuario = usuarioBuilder();
        usuario.setCorreoe("correoinvalido");
        Exception exception = assertThrows(BusinessException.class, () -> {
            UsuarioBusinessValidation.validar(usuario);
        });
        String expectedMessage = "Excepción de negocio";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    void whenValidar_thenCorreoIsGreaterThan100() {
        Usuario usuario = usuarioBuilder();
        usuario.setCorreoe("a123456789a123456789a123456789a123456789a123456789"
        + "a123456789a123456789a123456789a123456789a123456789@email.com");
        Exception exception = assertThrows(BusinessException.class, () -> {
            UsuarioBusinessValidation.validar(usuario);
        });
        String expectedMessage = "Excepción de negocio";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.equals(expectedMessage));
    }


    private Usuario usuarioBuilder(){
        Usuario usuario = new Usuario();
        usuario.setCodigo("1111");
        usuario.setNombre("Nombre");
        usuario.setCorreoe("test@email.com");
        return usuario;
    }


}