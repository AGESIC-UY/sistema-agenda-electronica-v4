/*
 * Nombre del cliente
 * Sistema de Gestión
 * Desarrollado por Sofis Solutions
 */
package uy.com.sofis.web.presentacion.tipos;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class FormatoArchivo implements Serializable {

    private String formato;
    private String valor;
    private String imagen = "";

    public FormatoArchivo() {

    }

    public FormatoArchivo(String nombre, String valor) {
        this.formato = nombre;
        this.valor = valor;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
