
package uy.gub.agesic.itramites.bruto.web.ws.cabezal;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the uy.gub.bpsejb.clientes.pavel package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Cabezal_QNAME = new QName("http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService", "cabezal");
    private final static QName _DatosProcesoDTO_QNAME = new QName("http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService", "datosProcesoDTO");
    private final static QName _IdTrazaDTO_QNAME = new QName("http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService", "idTrazaDTO");
    private final static QName _Ping_QNAME = new QName("http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService", "ping");
    private final static QName _ResponseDTO_QNAME = new QName("http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService", "responseDTO");
    private final static QName _PingResponse_QNAME = new QName("http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService", "pingResponse");
    private final static QName _Persist_QNAME = new QName("http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService", "persist");
    private final static QName _PersistResponse_QNAME = new QName("http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService", "persistResponse");
    private final static QName _CabezalDTOIdTrazaPadre_QNAME = new QName("", "idTrazaPadre");
    private final static QName _CabezalDTOCantidadPasosProceso_QNAME = new QName("", "cantidadPasosProceso");
    private final static QName _CabezalDTOPasoPadre_QNAME = new QName("", "pasoPadre");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uy.gub.bpsejb.clientes.pavel
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CabezalDTO }
     * 
     */
    public CabezalDTO createCabezalDTO() {
        return new CabezalDTO();
    }

    /**
     * Create an instance of {@link IdTrazaDTO }
     * 
     */
    public IdTrazaDTO createIdTrazaDTO() {
        return new IdTrazaDTO();
    }

    /**
     * Create an instance of {@link PersistResponse }
     * 
     */
    public PersistResponse createPersistResponse() {
        return new PersistResponse();
    }

    /**
     * Create an instance of {@link Persist }
     * 
     */
    public Persist createPersist() {
        return new Persist();
    }

    /**
     * Create an instance of {@link ResponseDTO }
     * 
     */
    public ResponseDTO createResponseDTO() {
        return new ResponseDTO();
    }

    /**
     * Create an instance of {@link Ping }
     * 
     */
    public Ping createPing() {
        return new Ping();
    }

    /**
     * Create an instance of {@link PingResponse }
     * 
     */
    public PingResponse createPingResponse() {
        return new PingResponse();
    }

    /**
     * Create an instance of {@link DatosProcesoTramiteDTO }
     * 
     */
    public DatosProcesoTramiteDTO createDatosProcesoTramiteDTO() {
        return new DatosProcesoTramiteDTO();
    }

    /**
     * Create an instance of {@link CabezalResponseDTO }
     * 
     */
    public CabezalResponseDTO createCabezalResponseDTO() {
        return new CabezalResponseDTO();
    }

    /**
     * Create an instance of {@link DatosProcesoExpedienteDTO }
     * 
     */
    public DatosProcesoExpedienteDTO createDatosProcesoExpedienteDTO() {
        return new DatosProcesoExpedienteDTO();
    }

    /**
     * Create an instance of {@link InvolucradoDTO }
     * 
     */
    public InvolucradoDTO createInvolucradoDTO() {
        return new InvolucradoDTO();
    }

    /**
     * Create an instance of {@link CabezalDTO.Involucrados }
     * 
     */
    public CabezalDTO.Involucrados createCabezalDTOInvolucrados() {
        return new CabezalDTO.Involucrados();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CabezalDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService", name = "cabezal")
    public JAXBElement<CabezalDTO> createCabezal(CabezalDTO value) {
        return new JAXBElement<CabezalDTO>(_Cabezal_QNAME, CabezalDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DatosProcesoDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService", name = "datosProcesoDTO")
    public JAXBElement<DatosProcesoDTO> createDatosProcesoDTO(DatosProcesoDTO value) {
        return new JAXBElement<DatosProcesoDTO>(_DatosProcesoDTO_QNAME, DatosProcesoDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdTrazaDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService", name = "idTrazaDTO")
    public JAXBElement<IdTrazaDTO> createIdTrazaDTO(IdTrazaDTO value) {
        return new JAXBElement<IdTrazaDTO>(_IdTrazaDTO_QNAME, IdTrazaDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Ping }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService", name = "ping")
    public JAXBElement<Ping> createPing(Ping value) {
        return new JAXBElement<Ping>(_Ping_QNAME, Ping.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService", name = "responseDTO")
    public JAXBElement<ResponseDTO> createResponseDTO(ResponseDTO value) {
        return new JAXBElement<ResponseDTO>(_ResponseDTO_QNAME, ResponseDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService", name = "pingResponse")
    public JAXBElement<PingResponse> createPingResponse(PingResponse value) {
        return new JAXBElement<PingResponse>(_PingResponse_QNAME, PingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Persist }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService", name = "persist")
    public JAXBElement<Persist> createPersist(Persist value) {
        return new JAXBElement<Persist>(_Persist_QNAME, Persist.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersistResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService", name = "persistResponse")
    public JAXBElement<PersistResponse> createPersistResponse(PersistResponse value) {
        return new JAXBElement<PersistResponse>(_PersistResponse_QNAME, PersistResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdTrazaDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "idTrazaPadre", scope = CabezalDTO.class)
    public JAXBElement<IdTrazaDTO> createCabezalDTOIdTrazaPadre(IdTrazaDTO value) {
        return new JAXBElement<IdTrazaDTO>(_CabezalDTOIdTrazaPadre_QNAME, IdTrazaDTO.class, CabezalDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "cantidadPasosProceso", scope = CabezalDTO.class)
    public JAXBElement<Long> createCabezalDTOCantidadPasosProceso(Long value) {
        return new JAXBElement<Long>(_CabezalDTOCantidadPasosProceso_QNAME, Long.class, CabezalDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "pasoPadre", scope = CabezalDTO.class)
    public JAXBElement<Long> createCabezalDTOPasoPadre(Long value) {
        return new JAXBElement<Long>(_CabezalDTOPasoPadre_QNAME, Long.class, CabezalDTO.class, value);
    }

}
