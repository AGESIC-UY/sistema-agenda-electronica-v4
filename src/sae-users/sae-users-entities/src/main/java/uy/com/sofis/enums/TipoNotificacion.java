/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.sofis.enums;

/**
 *
 * @author USUARIO
 */
public enum TipoNotificacion {
     
    TEXTO_LIBRE("Texto Libre"), //Manual
    INSTITUCION_PROYECTOS_A_RENDIR("Proyectos a rendir"), //Manual
    PROYECTO_CAMBIO_ESTADO("Proyecto cambio estado"),
    RENDICION_PROYECTO_CAMBIO_ESTADO("Rendición proyecto cambio estado"),
    COMPROMISO_CAMBIO_ESTADO("Compromiso cambio estado"),
    SOLICITUD_TOPE_CAMBIO_ESTADO("Solicitud tope cambio estado"),
    TOPE_ASIGNADO_INSTITUCION("Institución tope asignado"),
    INSTITUCION_ASIGNADA_EVALUADORA("Institución asignada como evaluadora"),
    INSTITUCION_ASIGNADA_BENEFICIARIA("Institución asignada como beneficiaria"),
    CORRECCION_ESTADO_COMPROMISO("Corrección estado de compromiso"),
    PROYECTO_MODIFICADO_BENEFICIARIO("Modificación de proyecto por beneficiario");
    

     private final String text;


    private TipoNotificacion(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public String getText() {
        return text;
    }


}
