/*
 *  Nombre del cliente
 *  Sistema de Gestión
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.web.graficos;

import java.util.ArrayList;
import java.util.List;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Usuario
 */
public class SofisGraficoMapa extends SofisGrafico {

    private Integer ancho;
    private Integer alto;
    private List<DataMapa> listaValores = new ArrayList();
    private String etiquetaRotulo = "País";
    private String etiquetaCantidad = "Cantidad";
    private String etiquetaPorcentaje = "Porcentaje";
    private String region;

    public SofisGraficoMapa() {
    }

    public Integer getAncho() {
        return ancho;
    }

    public void setAncho(Integer ancho) {
        this.ancho = ancho;
    }

    public Integer getAlto() {
        return alto;
    }

    public void setAlto(Integer alto) {
        this.alto = alto;
    }

    public List<DataMapa> getListaValores() {
        return listaValores;
    }

    public void setListaValores(List<DataMapa> listaValores) {
        this.listaValores = listaValores;
    }

    public String getEtiquetaRotulo() {
        return etiquetaRotulo;
    }

    public void setEtiquetaRotulo(String etiquetaRotulo) {
        this.etiquetaRotulo = etiquetaRotulo;
    }

    public String getEtiquetaCantidad() {
        return etiquetaCantidad;
    }

    public void setEtiquetaCantidad(String etiquetaCantidad) {
        this.etiquetaCantidad = etiquetaCantidad;
    }

    public String getEtiquetaPorcentaje() {
        return etiquetaPorcentaje;
    }

    public void setEtiquetaPorcentaje(String etiquetaPorcentaje) {
        this.etiquetaPorcentaje = etiquetaPorcentaje;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void ejecutarJS() {
        String data = "var data = google.visualization.arrayToDataTable([";
        data += "['" + etiquetaRotulo + "','" + etiquetaPorcentaje + "','" + etiquetaCantidad + "'],";
        for (DataMapa s : listaValores) {
            data += "['" + s.getRotulo() + "'," + s.getPorcentaje() + "," + s.getCantidad() + "],";
        }
        data += "]);";

        String opciones = "var options = {";
        if (getAlto() != null) {
            opciones += " height: " + getAlto() + ",";
        }
        if (getAncho() != null) {
            opciones += " width: " + getAncho() + ",";
        }
        if (getRegion() != null) {
            opciones += " region: " + getRegion() + ",";
        }
        opciones += "};";
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute(data);
        context.execute(opciones);
        context.execute("var chart = new google.visualization.GeoChart(document.getElementById('" + getNombreDiv() + "')) ");
        context.execute("chart.draw(data, options)");
    }

}
