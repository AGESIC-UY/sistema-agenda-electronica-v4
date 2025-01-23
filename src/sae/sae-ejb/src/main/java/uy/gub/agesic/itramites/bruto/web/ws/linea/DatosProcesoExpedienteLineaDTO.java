
package uy.gub.agesic.itramites.bruto.web.ws.linea;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para datosProcesoExpedienteLineaDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="datosProcesoExpedienteLineaDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService}datosProcesoDTO"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idExpedienteActual" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="tipoTrazaExpediente" type="{http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService}tipoTrazaExpediente"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datosProcesoExpedienteLineaDTO", propOrder = {
    "idExpedienteActual",
    "tipoTrazaExpediente"
})
public class DatosProcesoExpedienteLineaDTO
    extends DatosProcesoDTO
{

    @XmlElement(required = true)
    protected String idExpedienteActual;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected TipoTrazaExpediente tipoTrazaExpediente;

    /**
     * Obtiene el valor de la propiedad idExpedienteActual.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdExpedienteActual() {
        return idExpedienteActual;
    }

    /**
     * Define el valor de la propiedad idExpedienteActual.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdExpedienteActual(String value) {
        this.idExpedienteActual = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoTrazaExpediente.
     * 
     * @return
     *     possible object is
     *     {@link TipoTrazaExpediente }
     *     
     */
    public TipoTrazaExpediente getTipoTrazaExpediente() {
        return tipoTrazaExpediente;
    }

    /**
     * Define el valor de la propiedad tipoTrazaExpediente.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoTrazaExpediente }
     *     
     */
    public void setTipoTrazaExpediente(TipoTrazaExpediente value) {
        this.tipoTrazaExpediente = value;
    }

}
