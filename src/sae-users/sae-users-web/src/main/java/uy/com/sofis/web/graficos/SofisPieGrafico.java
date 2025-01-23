/*
 * Nombre del cliente
 * Sistema de Gestión
 * Desarrollado por Sofis Solutions
 */
package uy.com.sofis.web.graficos;

import uy.com.sofis.utils.FloatUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

import org.primefaces.context.RequestContext;

/**
 *
 * @author Usuario
 */
public class SofisPieGrafico extends SofisGrafico {

    private Boolean en3D;
    private Integer ancho;
    private Integer alto;
    private float pieHole;
    private List<DataPie> listaValores = new ArrayList();
    private String etiquetaRotulo;
    private String etiquetaValor;

    public SofisPieGrafico() {
    }

    public Boolean getEn3D() {
        return en3D;
    }

    public void setEn3D(Boolean en3D) {
        this.en3D = en3D;
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

    public float getPieHole() {
        return pieHole;
    }

    public void setPieHole(float pieHole) {
        this.pieHole = pieHole;
    }

    public List<DataPie> getListaValores() {
        return listaValores;
    }

    public void setListaValores(List<DataPie> listaValores) {
        this.listaValores = listaValores;
    }

    public String getEtiquetaRotulo() {
        return etiquetaRotulo;
    }

    public void setEtiquetaRotulo(String etiquetaRotulo) {
        this.etiquetaRotulo = etiquetaRotulo;
    }

    public String getEtiquetaValor() {
        return etiquetaValor;
    }

    public void setEtiquetaValor(String etiquetaValor) {
        this.etiquetaValor = etiquetaValor;
    }

    public void ejecutarJS() {
        String x = "var data = google.visualization.arrayToDataTable([";
        x += "['" + etiquetaRotulo + "', '" + etiquetaValor + "'],";
        for (DataPie s : listaValores) {
            x += "['" + s.getRotulo() + "',   " + s.getValor() + "],\n";
        }
        x += "]);";

        String opciones = "var options = {";
        if (!FloatUtils.esNulo(pieHole)) {
            opciones += " pieHole: " + pieHole + ",";
        }
        if (!StringUtils.isBlank(getTitulo())) {
            opciones += " title: '" + getTitulo() + "',";
        }
        if (ancho != null) {
            opciones += " width: " + ancho + ",";
        }
        if (alto != null) {
            opciones += " height: " + alto + ",";
        }
        opciones += "};";
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute(x);
        context.execute(opciones);
        context.execute("var chart = new google.visualization.PieChart(document.getElementById('" + getNombreDiv() + "')) ");
        context.execute("chart.draw(data, options)");
    }

}
