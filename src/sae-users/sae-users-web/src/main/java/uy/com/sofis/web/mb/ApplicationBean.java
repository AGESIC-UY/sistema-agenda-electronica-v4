/*
 *  Nombre del cliente
 *  Sistema de Gestión
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.web.mb;

import java.io.InputStream;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 *
 * @author Usuario
 */
@Named
@ApplicationScoped
public class ApplicationBean implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(ApplicationBean.class.getName());

    private String version;
    private String build;
    private String timestamp;
    private final String paginatorTemplate = "{RowsPerPageDropdown} {CurrentPageReport} {anterior} {PageLinks} {siguiente}";
    private final String rowsPerPageTemplate = "1,5,10,25,50,100";
    private final String patternFecha = "dd/MM/yyyy";
    private final String patternHora = "HH:mm";
    private final String patternFechaHora = "dd/MM/yyyy HH:mm";
    private final String encodingDataExport = "iso-8859-1";
    private final String patternMail = "(^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$)?";
    private String formatValoresDecimales = "#,##0.00";
    private String formatPorcentaje = "###,###,##0.00";

    /**
     * Creates a new instance of AplicacionManagedBean
     */
    public ApplicationBean() {

    }

    @PostConstruct
    private void init() {
        Manifest manifest;
        try {
            InputStream is = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/META-INF/MANIFEST.MF");
            manifest = new Manifest();
            manifest.read(is);

            Attributes atts = manifest.getMainAttributes();
            for (Iterator it = atts.keySet().iterator(); it.hasNext();) {
                Attributes.Name attrName = (Attributes.Name) it.next();
                String attrValue = atts.getValue(attrName);
                LOGGER.log(Level.INFO, "*attr: " + attrValue);
            }
            version = atts.getValue("version");
            build = atts.getValue("Implementation-Build");
            timestamp = atts.getValue("build-time");

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

    }

    public SelectItem[] getHabilitadoValues() {
        return new SelectItem[]{
            new SelectItem(Boolean.TRUE, "Sí"),
            new SelectItem(Boolean.FALSE, "No")
        };
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getPaginatorTemplate() {
        return paginatorTemplate;
    }

    public String getRowsPerPageTemplate() {
        return rowsPerPageTemplate;
    }

    public String getPatternFecha() {
        return patternFecha;
    }

    public String getEncodingDataExport() {
        return encodingDataExport;
    }

    public String getPatternMail() {
        return patternMail;
    }

    public DateTimeFormatter getFormatterFechaHora() {
        return DateTimeFormatter.ofPattern(patternFechaHora);
    }

    public DateTimeFormatter getFormatterFecha() {
        return DateTimeFormatter.ofPattern(patternFecha);
    }

    public String getFormatValoresDecimales() {
        return formatValoresDecimales;
    }

    public void setFormatValoresDecimales(String formatValoresDecimales) {
        this.formatValoresDecimales = formatValoresDecimales;
    }

    public String getPatternFechaHora() {
        return patternFechaHora;
    }

    public String getFormatPorcentaje() {
        return formatPorcentaje;
    }

    public String getPatternHora() {
        return patternHora;
    }
    
    
}
