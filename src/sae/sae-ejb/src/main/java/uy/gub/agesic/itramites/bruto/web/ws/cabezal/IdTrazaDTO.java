
package uy.gub.agesic.itramites.bruto.web.ws.cabezal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdTrazaDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdTrazaDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="oidOrganismo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idProceso" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idInstancia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoProceso" type="{http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService}tipoProcesoEnum"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
    protected TipoProcesoEnum tipoProceso;

    /**
     * Gets the value of the oidOrganismo property.
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
     * Sets the value of the oidOrganismo property.
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
     * Gets the value of the idProceso property.
     * 
     */
    public long getIdProceso() {
        return idProceso;
    }

    /**
     * Sets the value of the idProceso property.
     * 
     */
    public void setIdProceso(long value) {
        this.idProceso = value;
    }

    /**
     * Gets the value of the idInstancia property.
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
     * Sets the value of the idInstancia property.
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
     * Gets the value of the tipoProceso property.
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
     * Sets the value of the tipoProceso property.
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
