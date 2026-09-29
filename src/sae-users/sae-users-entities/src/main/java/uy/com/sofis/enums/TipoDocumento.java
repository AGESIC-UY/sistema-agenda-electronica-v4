/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.sofis.enums;

/**
 *
 * @author juan
 */
public enum TipoDocumento {
    NACIONAL("Nacional"),
    EXTRANJERO("Extranjero");

    private final String text;

    private TipoDocumento(final String text) {
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
