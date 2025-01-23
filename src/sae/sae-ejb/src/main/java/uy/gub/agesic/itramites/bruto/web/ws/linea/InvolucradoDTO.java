
package uy.gub.agesic.itramites.bruto.web.ws.linea;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para involucradoDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="involucradoDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="oid" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="role" type="{http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService}roleEnum"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "involucradoDTO", propOrder = {
    "oid",
    "role"
})
public class InvolucradoDTO {

    @XmlElement(required = true)
    protected String oid;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected RoleEnum role;

    /**
     * Obtiene el valor de la propiedad oid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOid() {
        return oid;
    }

    /**
     * Define el valor de la propiedad oid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOid(String value) {
        this.oid = value;
    }

    /**
     * Obtiene el valor de la propiedad role.
     * 
     * @return
     *     possible object is
     *     {@link RoleEnum }
     *     
     */
    public RoleEnum getRole() {
        return role;
    }

    /**
     * Define el valor de la propiedad role.
     * 
     * @param value
     *     allowed object is
     *     {@link RoleEnum }
     *     
     */
    public void setRole(RoleEnum value) {
        this.role = value;
    }

}
