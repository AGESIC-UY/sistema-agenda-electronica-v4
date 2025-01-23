
package uy.gub.agesic.itramites.bruto.web.ws.linea;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para IdTrazaDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="IdTrazaDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="oidOrganismo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="idProceso" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="idInstancia" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="tipoProceso" type="{http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService}tipoProcesoEnum"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdTrazaDTO", propOrder = {
    "oidOrganismo",
    "idProceso",
    "idInstancia",
    "tipoProceso"
})
public class IdTrazaDTO {

    @XmlElement(required = true)
    protected String oidOrganismo;
    protected long idProceso;
    @XmlElement(required = true)
    protected String idInstancia;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected TipoProcesoEnum tipoProceso;

    /**
     * Obtiene el valor de la propiedad oidOrganismo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOidOrganismo() {
        return oidOrganismo;
    }

    /**
     * Define el valor de la propiedad oidOrganismo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOidOrganismo(String value) {
        this.oidOrganismo = value;
    }

    /**
     * Obtiene el valor de la propiedad idProceso.
     * 
     */
    public long getIdProceso() {
        return idProceso;
    }

    /**
     * Define el valor de la propiedad idProceso.
     * 
     */
    public void setIdProceso(long value) {
        this.idProceso = value;
    }

    /**
     * Obtiene el valor de la propiedad idInstancia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdInstancia() {
        return idInstancia;
    }

    /**
     * Define el valor de la propiedad idInstancia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdInstancia(String value) {
        this.idInstancia = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoProceso.
     * 
     * @return
     *     possible object is
     *     {@link TipoProcesoEnum }
     *     
     */
    public TipoProcesoEnum getTipoProceso() {
        return tipoProceso;
    }

    /**
     * Define el valor de la propiedad tipoProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoProcesoEnum }
     *     
     */
    public void setTipoProceso(TipoProcesoEnum value) {
        this.tipoProceso = value;
    }

}
