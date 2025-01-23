/*
 *  Nombre del cliente
 *  Sistema de Gestión
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.web.graficos;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Usuario
 */
public class SofisGraficoColumnas extends SofisGrafico {

    private Integer ancho;
    private Integer alto;
    private List<DataColumna> listaValores = new ArrayList();
    private String etiquetaRotulo;
    private String[] etiquetasValor;

    public SofisGraficoColumnas() {
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

    public List<DataColumna> getListaValores() {
        return listaValores;
    }

    public void setListaValores(List<DataColumna> listaValores) {
        this.listaValores = listaValores;
    }

    public String getEtiquetaRotulo() {
        return etiquetaRotulo;
    }

    public void setEtiquetaRotulo(String etiquetaRotulo) {
        this.etiquetaRotulo = etiquetaRotulo;
    }

    public String[] getEtiquetasValor() {
        return etiquetasValor;
    }

    public void setEtiquetasValor(String[] etiquetasValor) {
        this.etiquetasValor = etiquetasValor;
    }

    public void ejecutarJS() {
        String data = "var data = google.visualization.arrayToDataTable([";
        data += "['" + etiquetaRotulo + "',";
        for (String s : etiquetasValor) {
            data += "'" + s + "',";
        }
        data += "],";

        for (DataColumna c : listaValores) {
            data += "['" + c.getRotulo() + "',";
            for (Float f : c.getValor()) {
                data += f + ", ";
            }
            data += "],";
        }
        data += "]);";

        String opciones = "var options = {";
        if (!StringUtils.isBlank(getTitulo())) {
            opciones += " title: '" + getTitulo() + "',";
        }
        opciones += "};";
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "data:{0} options:{1}", new Object[]{data, opciones});
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute(data);
        context.execute(opciones);
        context.execute("var chart = new google.charts.Bar(document.getElementById('" + getNombreDiv() + "')) ");
        context.execute("chart.draw(data, options)");
    }

    public void ejecutarPruebaJS() {
        String data = "var data = google.visualization.arrayToDataTable([";
        data += "['Países/Organismos','Primeros Oferentes','Segundos Oferentes','Receptores',],";
        data += "['Chile',44.2,0,0],";
        data += "['México',31.2,0,0],";
        data += "['Colombia',11.7,0,0],";
        data += "['Brasil',7.8,0,0],";
        data += "['Otros',6.2,33.8,41.8],";
        data += "['Alemania',0,27.3,0],";
        data += "['Japón',0,14.3,0],";
        data += "['EEUU',0,14.3,0],";
        data += "['Australia',0,10.4,0],";
        data += "['Paraguay',0,0,18.2],";
        data += "['El Salvador',0,0,16.8],";
        data += "['Guatemala',0,0,13],";
        data += "['Honduras',0,0,11.7],";
        data += "])\n";

        String opciones = "var options = {"
                + "title: 'CSST',"
                + "width: 900,"
                + "chart: { subtitle: 'Percentaje' },"
                + "bar: { groupWidth: '90%'}}\n";
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute(data);
        context.execute(opciones);
        context.execute("var chart = new google.charts.Bar(document.getElementById('" + getNombreDiv() + "')) ");
        context.execute("chart.draw(data, google.charts.Bar.convertOptions(options))");
    }
}
