/*
 *  Nombre del cliente
 *  Sistema de Gestión
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.web.graficos;


/**
 *
 * @author Usuario
 */
public class DataMapa {

    private Object rotulo;
    private Long cantidad;
    private Float porcentaje;

    public DataMapa() {
    }

    public Object getRotulo() {
        return rotulo;
    }

    public void setRotulo(Object rotulo) {
        this.rotulo = rotulo;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Float porcentaje) {
        this.porcentaje = porcentaje;
    }

}
