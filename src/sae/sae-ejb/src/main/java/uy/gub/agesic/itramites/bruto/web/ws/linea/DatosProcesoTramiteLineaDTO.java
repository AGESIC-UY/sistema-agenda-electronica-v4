
package uy.gub.agesic.itramites.bruto.web.ws.linea;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para datosProcesoTramiteLineaDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="datosProcesoTramiteLineaDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService}datosProcesoDTO"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="etiqueta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datosProcesoTramiteLineaDTO", propOrder = {
    "etiqueta"
})
public class DatosProcesoTramiteLineaDTO
    extends DatosProcesoDTO
{

    protected String etiqueta;

    /**
     * Obtiene el valor de la propiedad etiqueta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEtiqueta() {
        return etiqueta;
    }

    /**
     * Define el valor de la propiedad etiqueta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEtiqueta(String value) {
        this.etiqueta = value;
    }

}
