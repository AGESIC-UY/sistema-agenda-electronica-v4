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
public enum EstadoProyecto {

    BORRADOR("Borrador"),
    CORREGIR("Corregir"),
    ESTUDIO("Estudio"),
    APROBADO("Aprobado"),
    RECHAZADO("Rechazado"),
    SUGERIDO_CORREGIR("Sugerido corregir"),
    SUGERIDO_APROBAR("Sugerido aprobar"),
    SUGERIDO_RECHAZAR("Sugerido rechazar"),
    DESESTIMADO("Desestimado"),
    NO_VIGENTE("No vigente"),
    FINALIZADO("Finalizado"),
    NO_VIGENTE_CON_SALDO("No vigente con saldo")
    ;

    private final String text;

    private EstadoProyecto(final String text) {
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
