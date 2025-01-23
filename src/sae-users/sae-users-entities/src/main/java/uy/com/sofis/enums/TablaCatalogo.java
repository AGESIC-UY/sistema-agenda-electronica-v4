/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.sofis.enums;

/**
 *
 * @author sofis-iquezada
 */
public enum TablaCatalogo {
    
    INCISO(1,"Inciso"),
    UJ(2,"Unidad Ejecutoras"),
    PROGRAMA(3,"Programa"),
    PROYECTO(4,"Proyectos"),
    OBJGASTOAUX(5,"Objetos del Gasto y Auxiliares"),
    FUENTEFINANC(6,"Fuentes de Financiamiento"),
    MONEDA(7,"Monedas"),
    TIPOCREDITO(8,"Tipos de Crédito"),
    AGRUPADOR(9,"Agrupadores"),
    RELACION(10,"Relación Inciso, Programa, Proyecto");
    
    private final Integer cod;
    private final String nombre;

    
    private TablaCatalogo(final Integer cod,final String nombre) {
        this.cod = cod;
        this.nombre = nombre;
    }

    /**
     * @return the cod
     */
    public Integer getCod() {
        return cod;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    

    
}
