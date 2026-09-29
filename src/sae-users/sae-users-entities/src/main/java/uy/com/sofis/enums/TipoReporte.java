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
public enum TipoReporte {
    GENERAL("General"),
    OBJETIVO_INDICADOR("Objetivos e Indicadores"),
    SALDOS_EJECUTADO("Saldos y Ejecutado"),
    VIGENTES_POR_INSTITUCION("Vigentes por institución"),
    CATEGORIA_GASTO("Categoría gasto");

    private final String text;

    private TipoReporte(final String text) {
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
