
package uy.gub.agesic.itramites.bruto.web.ws.linea;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para datosProcesoDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="datosProcesoDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="datosExtra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datosProcesoDTO", propOrder = {
    "datosExtra"
})
@XmlSeeAlso({
    DatosProcesoTramiteLineaDTO.class,
    DatosProcesoExpedienteLineaDTO.class
})
public abstract class DatosProcesoDTO {

    protected String datosExtra;

    /**
     * Obtiene el valor de la propiedad datosExtra.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatosExtra() {
        return datosExtra;
    }

    /**
     * Define el valor de la propiedad datosExtra.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatosExtra(String value) {
        this.datosExtra = value;
    }

}
