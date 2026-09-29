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
public enum EstadoCompromiso {
     
    COMPROMETIDO("Comprometido"),
    DEPOSITADO("Depositado"),
    DEPOSITO_RECHAZADO_SIN_OT("Depósito rechazado sin OT"),
    DEPOSITO_RECHAZADO_CON_OT("Depósito rechazado con OT"),
    DEPOSITO_RETORNADO("Depósito retornado"),
    APROBADO("Depósito aprobado"),
    PAGADO("Pagado"),
    SOLICITADO("Solicitado"),
    CANJEADO("Canjeado"),
    ANULADO("Anulado"),
    ;
    
    private final String text;


    private EstadoCompromiso(final String text) {
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
