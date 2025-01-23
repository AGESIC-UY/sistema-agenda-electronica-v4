
package uy.gub.agesic.itramites.bruto.web.ws.linea;

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
 * <p>Clase Java para lineaDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="lineaDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idTraza" type="{http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService}IdTrazaDTO"/&gt;
 *         &lt;element name="oidOficina" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="oficina" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="fechaHoraOrganismo" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="appOrigen" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="involucrados" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="involucrado" type="{http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService}involucradoDTO" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="datosProceso" type="{http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService}datosProcesoDTO"/&gt;
 *         &lt;element name="tipoRegistroTrazabilidad" type="{http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService}tipoRegistroTrazabilidadEnum"/&gt;
 *         &lt;element name="etapa" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="descripcionDeLaEtapa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="aclaraciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pasoDelProceso" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="oidOficinaDestino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="oficinaDestino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="visibilidad" type="{http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService}visibilidadEnum" minOccurs="0"/&gt;
 *         &lt;element name="estadoProceso" type="{http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService}estadoProcesoEnum"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lineaDTO", propOrder = {
    "idTraza",
    "oidOficina",
    "oficina",
    "fechaHoraOrganismo",
    "appOrigen",
    "involucrados",
    "datosProceso",
    "tipoRegistroTrazabilidad",
    "etapa",
    "descripcionDeLaEtapa",
    "aclaraciones",
    "pasoDelProceso",
    "oidOficinaDestino",
    "oficinaDestino",
    "visibilidad",
    "estadoProceso"
})
public class LineaDTO {

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
    protected LineaDTO.Involucrados involucrados;
    @XmlElement(required = true)
    protected DatosProcesoDTO datosProceso;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected TipoRegistroTrazabilidadEnum tipoRegistroTrazabilidad;
    @XmlElementRef(name = "etapa", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> etapa;
    @XmlElementRef(name = "descripcionDeLaEtapa", type = JAXBElement.class, required = false)
    protected JAXBElement<String> descripcionDeLaEtapa;
    @XmlElementRef(name = "aclaraciones", type = JAXBElement.class, required = false)
    protected JAXBElement<String> aclaraciones;
    @XmlElementRef(name = "pasoDelProceso", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> pasoDelProceso;
    @XmlElementRef(name = "oidOficinaDestino", type = JAXBElement.class, required = false)
    protected JAXBElement<String> oidOficinaDestino;
    @XmlElementRef(name = "oficinaDestino", type = JAXBElement.class, required = false)
    protected JAXBElement<String> oficinaDestino;
    @XmlElementRef(name = "visibilidad", type = JAXBElement.class, required = false)
    protected JAXBElement<VisibilidadEnum> visibilidad;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EstadoProcesoEnum estadoProceso;

    /**
     * Obtiene el valor de la propiedad idTraza.
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
     * Define el valor de la propiedad idTraza.
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
     * Obtiene el valor de la propiedad oidOficina.
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
     * Define el valor de la propiedad oidOficina.
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
     * Obtiene el valor de la propiedad oficina.
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
     * Define el valor de la propiedad oficina.
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
     * Obtiene el valor de la propiedad fechaHoraOrganismo.
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
     * Define el valor de la propiedad fechaHoraOrganismo.
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
     * Obtiene el valor de la propiedad appOrigen.
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
     * Define el valor de la propiedad appOrigen.
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
     * Obtiene el valor de la propiedad involucrados.
     * 
     * @return
     *     possible object is
     *     {@link LineaDTO.Involucrados }
     *     
     */
    public LineaDTO.Involucrados getInvolucrados() {
        return involucrados;
    }

    /**
     * Define el valor de la propiedad involucrados.
     * 
     * @param value
     *     allowed object is
     *     {@link LineaDTO.Involucrados }
     *     
     */
    public void setInvolucrados(LineaDTO.Involucrados value) {
        this.involucrados = value;
    }

    /**
     * Obtiene el valor de la propiedad datosProceso.
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
     * Define el valor de la propiedad datosProceso.
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
     * Obtiene el valor de la propiedad tipoRegistroTrazabilidad.
     * 
     * @return
     *     possible object is
     *     {@link TipoRegistroTrazabilidadEnum }
     *     
     */
    public TipoRegistroTrazabilidadEnum getTipoRegistroTrazabilidad() {
        return tipoRegistroTrazabilidad;
    }

    /**
     * Define el valor de la propiedad tipoRegistroTrazabilidad.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoRegistroTrazabilidadEnum }
     *     
     */
    public void setTipoRegistroTrazabilidad(TipoRegistroTrazabilidadEnum value) {
        this.tipoRegistroTrazabilidad = value;
    }

    /**
     * Obtiene el valor de la propiedad etapa.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getEtapa() {
        return etapa;
    }

    /**
     * Define el valor de la propiedad etapa.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setEtapa(JAXBElement<Long> value) {
        this.etapa = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcionDeLaEtapa.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescripcionDeLaEtapa() {
        return descripcionDeLaEtapa;
    }

    /**
     * Define el valor de la propiedad descripcionDeLaEtapa.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescripcionDeLaEtapa(JAXBElement<String> value) {
        this.descripcionDeLaEtapa = value;
    }

    /**
     * Obtiene el valor de la propiedad aclaraciones.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAclaraciones() {
        return aclaraciones;
    }

    /**
     * Define el valor de la propiedad aclaraciones.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAclaraciones(JAXBElement<String> value) {
        this.aclaraciones = value;
    }

    /**
     * Obtiene el valor de la propiedad pasoDelProceso.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getPasoDelProceso() {
        return pasoDelProceso;
    }

    /**
     * Define el valor de la propiedad pasoDelProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setPasoDelProceso(JAXBElement<Long> value) {
        this.pasoDelProceso = value;
    }

    /**
     * Obtiene el valor de la propiedad oidOficinaDestino.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOidOficinaDestino() {
        return oidOficinaDestino;
    }

    /**
     * Define el valor de la propiedad oidOficinaDestino.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOidOficinaDestino(JAXBElement<String> value) {
        this.oidOficinaDestino = value;
    }

    /**
     * Obtiene el valor de la propiedad oficinaDestino.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOficinaDestino() {
        return oficinaDestino;
    }

    /**
     * Define el valor de la propiedad oficinaDestino.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOficinaDestino(JAXBElement<String> value) {
        this.oficinaDestino = value;
    }

    /**
     * Obtiene el valor de la propiedad visibilidad.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link VisibilidadEnum }{@code >}
     *     
     */
    public JAXBElement<VisibilidadEnum> getVisibilidad() {
        return visibilidad;
    }

    /**
     * Define el valor de la propiedad visibilidad.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link VisibilidadEnum }{@code >}
     *     
     */
    public void setVisibilidad(JAXBElement<VisibilidadEnum> value) {
        this.visibilidad = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoProceso.
     * 
     * @return
     *     possible object is
     *     {@link EstadoProcesoEnum }
     *     
     */
    public EstadoProcesoEnum getEstadoProceso() {
        return estadoProceso;
    }

    /**
     * Define el valor de la propiedad estadoProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoProcesoEnum }
     *     
     */
    public void setEstadoProceso(EstadoProcesoEnum value) {
        this.estadoProceso = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="involucrado" type="{http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService}involucradoDTO" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
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
