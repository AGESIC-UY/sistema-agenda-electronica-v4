/*
 * Nombre del cliente
 * Sistema de Gestión
 * Desarrollado por Sofis Solutions
 */
package uy.com.sofis.web.graficos;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class SofisGrafico implements Serializable {

    private String titulo = "";
    private String nombreDiv = "";
    private String inicializacion = "";

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombreDiv() {
        return nombreDiv;
    }

    public void setNombreDiv(String nombreDiv) {
        this.nombreDiv = nombreDiv;
    }

    public String getInicializacion() {
        return inicializacion;
    }

    public void setInicializacion(String inicializacion) {
        this.inicializacion = inicializacion;
    }

}
