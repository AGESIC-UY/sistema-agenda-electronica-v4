
package uy.gub.agesic.itramites.bruto.web.ws.linea;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para persist complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="persist"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="traza" type="{http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService}lineaDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "persist", propOrder = {
    "traza"
})
public class Persist {

    protected LineaDTO traza;

    /**
     * Obtiene el valor de la propiedad traza.
     * 
     * @return
     *     possible object is
     *     {@link LineaDTO }
     *     
     */
    public LineaDTO getTraza() {
        return traza;
    }

    /**
     * Define el valor de la propiedad traza.
     * 
     * @param value
     *     allowed object is
     *     {@link LineaDTO }
     *     
     */
    public void setTraza(LineaDTO value) {
        this.traza = value;
    }

}
