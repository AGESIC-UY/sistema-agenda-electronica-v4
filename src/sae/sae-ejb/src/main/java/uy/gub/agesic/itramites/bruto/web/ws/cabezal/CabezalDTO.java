
package uy.gub.agesic.itramites.bruto.web.ws.cabezal;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for cabezalDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cabezalDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idTraza" type="{http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService}IdTrazaDTO"/>
 *         &lt;element name="oidOficina" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oficina" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaHoraOrganismo" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="appOrigen" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="involucrados" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="involucrado" type="{http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService}involucradoDTO" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="datosProceso" type="{http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService}datosProcesoDTO"/>
 *         &lt;element name="cantidadPasosProceso" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idTrazaPadre" type="{http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService}IdTrazaDTO" minOccurs="0"/>
 *         &lt;element name="pasoPadre" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cabezalDTO", propOrder = {
    "idTraza",
    "oidOficina",
    "oficina",
    "fechaHoraOrganismo",
    "appOrigen",
    "involucrados",
    "datosProceso",
    "cantidadPasosProceso",
    "idTrazaPadre",
    "pasoPadre"
})
public class CabezalDTO {

    @XmlElement(required = true)
    protected IdTrazaDTO idTraza;
    @XmlElement(required = true)
    protected String oidOficina;
    @XmlElement(required = true)
    protected String oficina;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaHoraOrganismo;
    @XmlElement(required = true)
    protected String appOrigen;
    protected CabezalDTO.Involucrados involucrados;
    @XmlElement(required = true)
    protected DatosProcesoDTO datosProceso;
    @XmlElementRef(name = "cantidadPasosProceso", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> cantidadPasosProceso;
    @XmlElementRef(name = "idTrazaPadre", type = JAXBElement.class, required = false)
    protected JAXBElement<IdTrazaDTO> idTrazaPadre;
    @XmlElementRef(name = "pasoPadre", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> pasoPadre;

    /**
     * Gets the value of the idTraza property.
     * 
     * @return
     *     possible object is
     *     {@link IdTrazaDTO }
     *     
     */
    public IdTrazaDTO getIdTraza() {
        return idTraza;
    }

    /**
     * Sets the value of the idTraza property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdTrazaDTO }
     *     
     */
    public void setIdTraza(IdTrazaDTO value) {
        this.idTraza = value;
    }

    /**
     * Gets the value of the oidOficina property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOidOficina() {
        return oidOficina;
    }

    /**
     * Sets the value of the oidOficina property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOidOficina(String value) {
        this.oidOficina = value;
    }

    /**
     * Gets the value of the oficina property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOficina() {
        return oficina;
    }

    /**
     * Sets the value of the oficina property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOficina(String value) {
        this.oficina = value;
    }

    /**
     * Gets the value of the fechaHoraOrganismo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaHoraOrganismo() {
        return fechaHoraOrganismo;
    }

    /**
     * Sets the value of the fechaHoraOrganismo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaHoraOrganismo(XMLGregorianCalendar value) {
        this.fechaHoraOrganismo = value;
    }

    /**
     * Gets the value of the appOrigen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppOrigen() {
        return appOrigen;
    }

    /**
     * Sets the value of the appOrigen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppOrigen(String value) {
        this.appOrigen = value;
    }

    /**
     * Gets the value of the involucrados property.
     * 
     * @return
     *     possible object is
     *     {@link CabezalDTO.Involucrados }
     *     
     */
    public CabezalDTO.Involucrados getInvolucrados() {
        return involucrados;
    }

    /**
     * Sets the value of the involucrados property.
     * 
     * @param value
     *     allowed object is
     *     {@link CabezalDTO.Involucrados }
     *     
     */
    public void setInvolucrados(CabezalDTO.Involucrados value) {
        this.involucrados = value;
    }

    /**
     * Gets the value of the datosProceso property.
     * 
     * @return
     *     possible object is
     *     {@link DatosProcesoDTO }
     *     
     */
    public DatosProcesoDTO getDatosProceso() {
        return datosProceso;
    }

    /**
     * Sets the value of the datosProceso property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosProcesoDTO }
     *     
     */
    public void setDatosProceso(DatosProcesoDTO value) {
        this.datosProceso = value;
    }

    /**
     * Gets the value of the cantidadPasosProceso property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getCantidadPasosProceso() {
        return cantidadPasosProceso;
    }

    /**
     * Sets the value of the cantidadPasosProceso property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setCantidadPasosProceso(JAXBElement<Long> value) {
        this.cantidadPasosProceso = value;
    }

    /**
     * Gets the value of the idTrazaPadre property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link IdTrazaDTO }{@code >}
     *     
     */
    public JAXBElement<IdTrazaDTO> getIdTrazaPadre() {
        return idTrazaPadre;
    }

    /**
     * Sets the value of the idTrazaPadre property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link IdTrazaDTO }{@code >}
     *     
     */
    public void setIdTrazaPadre(JAXBElement<IdTrazaDTO> value) {
        this.idTrazaPadre = value;
    }

    /**
     * Gets the value of the pasoPadre property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getPasoPadre() {
        return pasoPadre;
    }

    /**
     * Sets the value of the pasoPadre property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setPasoPadre(JAXBElement<Long> value) {
        this.pasoPadre = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="involucrado" type="{http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService}involucradoDTO" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "involucrado"
    })
    public static class Involucrados {

        @XmlElement(nillable = true)
        protected List<InvolucradoDTO> involucrado;

        /**
         * Gets the value of the involucrado property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the involucrado property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInvolucrado().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link InvolucradoDTO }
         * 
         * 
         */
        public List<InvolucradoDTO> getInvolucrado() {
            if (involucrado == null) {
                involucrado = new ArrayList<InvolucradoDTO>();
            }
            return this.involucrado;
        }

    }

}
