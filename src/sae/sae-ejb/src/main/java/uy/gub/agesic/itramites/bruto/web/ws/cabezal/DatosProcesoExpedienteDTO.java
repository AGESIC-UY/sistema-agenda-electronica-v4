
package uy.gub.agesic.itramites.bruto.web.ws.cabezal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for datosProcesoExpedienteDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="datosProcesoExpedienteDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService}datosProcesoDTO">
 *       &lt;sequence>
 *         &lt;element name="formaDeIncio" type="{http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService}datosProcesoExpedienteFormaDeInicioEnum"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datosProcesoExpedienteDTO", propOrder = {
    "formaDeIncio"
})
public class DatosProcesoExpedienteDTO
    extends DatosProcesoDTO
{

    @XmlElement(required = true)
    protected DatosProcesoExpedienteFormaDeInicioEnum formaDeIncio;

    /**
     * Gets the value of the formaDeIncio property.
     * 
     * @return
     *     possible object is
     *     {@link DatosProcesoExpedienteFormaDeInicioEnum }
     *     
     */
    public DatosProcesoExpedienteFormaDeInicioEnum getFormaDeIncio() {
        return formaDeIncio;
    }

    /**
     * Sets the value of the formaDeIncio property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosProcesoExpedienteFormaDeInicioEnum }
     *     
     */
    public void setFormaDeIncio(DatosProcesoExpedienteFormaDeInicioEnum value) {
        this.formaDeIncio = value;
    }

}
