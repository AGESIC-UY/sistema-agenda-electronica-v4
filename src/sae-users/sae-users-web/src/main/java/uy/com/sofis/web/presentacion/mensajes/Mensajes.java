/*
 *  Nombre del cliente
 *  Sistema de Gestión
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.web.presentacion.mensajes;

import java.util.HashMap;
import java.util.ResourceBundle;

/**
 *
 * @author Usuario
 */
public class Mensajes {

    private static HashMap<String, String> MENSAJES = new HashMap();
    private static ResourceBundle bundle = ResourceBundle.getBundle("com.sofis.presentacion.mensajes.mensajes");

    public static String obtenerMensaje(String key) {
        if (bundle.containsKey(key)) {
            return bundle.getString(key);
        } else {
            return "?" + key + "?";
        }
    }

    public static boolean containsKey(String key) {
        return bundle.containsKey(key);
    }

    public static final String GUARDADO_CORRECTO = "GUARDADO_CORRECTO";
    public static final String GUARDADO_CORRECTO_PROYECTO = "GUARDADO_CORRECTO_PROYECTO";
    public static final String ELIMINADO_CORRECTO = "ELIMINADO_CORRECTO";
    public static final String ENVIADO_CORRECTO = "ENVIADO_CORRECTO";
    public static final String CAMBIO_CONTRASENIA_CORRECTO = "CAMBIO_PASSWORD_CORRECTO";
    public static final String ERROR_GENERAL = "ERROR_GENERAL";
    public static final String ACCESO_DENEGADO = "ACCESO_DENEGADO";
    public static final String AGREGADO_CORRECTO = "AGREGADO_CORRECTO";

    public static final String CORREO_ENVIADO = "CORREO_ENVIADO";
    public static final String EMAIL_NO_COINCIDE = "EMAIL_NO_COINCIDE";
    public static final String USUARIO_NO_BLOQUEADO = "USUARIO_NO_BLOQUEADO";
    public static final String CODIGO_USUARIO_INEXISTENTE = "CODIGO_USUARIO_INEXISTENTE";
    public static final String USUARIO_CREDENCIALES_INVALIDAS = "USUARIO_CREDENCIALES_INVALIDAS";

    public static final String OPERACION_EXISTENTE_EN_ROL = "OPERACION_EXISTENTE_EN_ROL";
    public static final String OPERACION_VACIA = "OPERACION_VACIA";

    public static final String RESPUESTA_CAPTCHA_INCORRECTA = "RESPUESTA_CAPTCHA_INCORRECTA";
    public static final String RESPUESTA_CAPTCHA_INEXISTENTE = "RESPUESTA_CAPTCHA_INEXISTENTE";
    public static final String CODIGO_USUARIO_VAC = "CODIGO_USUARIO_VAC";

    public static final String ARCHIVO_VACIO = "ARCHIVO_VACIO";
    public static final String CONFIGURACION_NO_CARGADA = "CONFIGURACION_NO_CARGADA";
    public static final String ERROR_ORGANISMO_VACIO = "ERROR_ORGANISMO_VACIO";
    public static final String ERROR_NOCHECK_ADMIN="ERROR_NOCHECK_ADMIN";

}
