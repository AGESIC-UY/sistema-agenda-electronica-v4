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
public enum TipoSolicitudTope {
     
    DISTRIBUCION("Distribución entre proyectos"), 
    INICIAL("Asignación de tope inicial"),
    VARIACION("Variación de tope");
 

    private final String text;

    private TipoSolicitudTope(final String text) {
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
