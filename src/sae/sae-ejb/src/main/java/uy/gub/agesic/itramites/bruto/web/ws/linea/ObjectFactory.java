
package uy.gub.agesic.itramites.bruto.web.ws.linea;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the uy.gub.agesic.itramites.bruto.web.ws.linea package. 
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

    private final static QName _DatosProcesoLineaDTO_QNAME = new QName("http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService", "datosProcesoLineaDTO");
    private final static QName _IdTrazaDTO_QNAME = new QName("http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService", "idTrazaDTO");
    private final static QName _Linea_QNAME = new QName("http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService", "linea");
    private final static QName _Persist_QNAME = new QName("http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService", "persist");
    private final static QName _PersistResponse_QNAME = new QName("http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService", "persistResponse");
    private final static QName _Ping_QNAME = new QName("http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService", "ping");
    private final static QName _PingResponse_QNAME = new QName("http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService", "pingResponse");
    private final static QName _ResponseDTO_QNAME = new QName("http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService", "responseDTO");
    private final static QName _LineaDTOEtapa_QNAME = new QName("", "etapa");
    private final static QName _LineaDTODescripcionDeLaEtapa_QNAME = new QName("", "descripcionDeLaEtapa");
    private final static QName _LineaDTOAclaraciones_QNAME = new QName("", "aclaraciones");
    private final static QName _LineaDTOPasoDelProceso_QNAME = new QName("", "pasoDelProceso");
    private final static QName _LineaDTOOidOficinaDestino_QNAME = new QName("", "oidOficinaDestino");
    private final static QName _LineaDTOOficinaDestino_QNAME = new QName("", "oficinaDestino");
    private final static QName _LineaDTOVisibilidad_QNAME = new QName("", "visibilidad");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uy.gub.agesic.itramites.bruto.web.ws.linea
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LineaDTO }
     * 
     */
    public LineaDTO createLineaDTO() {
        return new LineaDTO();
    }

    /**
     * Create an instance of {@link IdTrazaDTO }
     * 
     */
    public IdTrazaDTO createIdTrazaDTO() {
        return new IdTrazaDTO();
    }

    /**
     * Create an instance of {@link Persist }
     * 
     */
    public Persist createPersist() {
        return new Persist();
    }

    /**
     * Create an instance of {@link PersistResponse }
     * 
     */
    public PersistResponse createPersistResponse() {
        return new PersistResponse();
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
     * Create an instance of {@link ResponseDTO }
     * 
     */
    public ResponseDTO createResponseDTO() {
        return new ResponseDTO();
    }

    /**
     * Create an instance of {@link InvolucradoDTO }
     * 
     */
    public InvolucradoDTO createInvolucradoDTO() {
        return new InvolucradoDTO();
    }

    /**
     * Create an instance of {@link DatosProcesoTramiteLineaDTO }
     * 
     */
    public DatosProcesoTramiteLineaDTO createDatosProcesoTramiteLineaDTO() {
        return new DatosProcesoTramiteLineaDTO();
    }

    /**
     * Create an instance of {@link DatosProcesoExpedienteLineaDTO }
     * 
     */
    public DatosProcesoExpedienteLineaDTO createDatosProcesoExpedienteLineaDTO() {
        return new DatosProcesoExpedienteLineaDTO();
    }

    /**
     * Create an instance of {@link LineaDTO.Involucrados }
     * 
     */
    public LineaDTO.Involucrados createLineaDTOInvolucrados() {
        return new LineaDTO.Involucrados();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DatosProcesoDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService", name = "datosProcesoLineaDTO")
    public JAXBElement<DatosProcesoDTO> createDatosProcesoLineaDTO(DatosProcesoDTO value) {
        return new JAXBElement<DatosProcesoDTO>(_DatosProcesoLineaDTO_QNAME, DatosProcesoDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdTrazaDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService", name = "idTrazaDTO")
    public JAXBElement<IdTrazaDTO> createIdTrazaDTO(IdTrazaDTO value) {
        return new JAXBElement<IdTrazaDTO>(_IdTrazaDTO_QNAME, IdTrazaDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LineaDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService", name = "linea")
    public JAXBElement<LineaDTO> createLinea(LineaDTO value) {
        return new JAXBElement<LineaDTO>(_Linea_QNAME, LineaDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Persist }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService", name = "persist")
    public JAXBElement<Persist> createPersist(Persist value) {
        return new JAXBElement<Persist>(_Persist_QNAME, Persist.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersistResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService", name = "persistResponse")
    public JAXBElement<PersistResponse> createPersistResponse(PersistResponse value) {
        return new JAXBElement<PersistResponse>(_PersistResponse_QNAME, PersistResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Ping }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService", name = "ping")
    public JAXBElement<Ping> createPing(Ping value) {
        return new JAXBElement<Ping>(_Ping_QNAME, Ping.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService", name = "pingResponse")
    public JAXBElement<PingResponse> createPingResponse(PingResponse value) {
        return new JAXBElement<PingResponse>(_PingResponse_QNAME, PingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/lineaService", name = "responseDTO")
    public JAXBElement<ResponseDTO> createResponseDTO(ResponseDTO value) {
        return new JAXBElement<ResponseDTO>(_ResponseDTO_QNAME, ResponseDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "etapa", scope = LineaDTO.class)
    public JAXBElement<Long> createLineaDTOEtapa(Long value) {
        return new JAXBElement<Long>(_LineaDTOEtapa_QNAME, Long.class, LineaDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "descripcionDeLaEtapa", scope = LineaDTO.class)
    public JAXBElement<String> createLineaDTODescripcionDeLaEtapa(String value) {
        return new JAXBElement<String>(_LineaDTODescripcionDeLaEtapa_QNAME, String.class, LineaDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "aclaraciones", scope = LineaDTO.class)
    public JAXBElement<String> createLineaDTOAclaraciones(String value) {
        return new JAXBElement<String>(_LineaDTOAclaraciones_QNAME, String.class, LineaDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "pasoDelProceso", scope = LineaDTO.class)
    public JAXBElement<Long> createLineaDTOPasoDelProceso(Long value) {
        return new JAXBElement<Long>(_LineaDTOPasoDelProceso_QNAME, Long.class, LineaDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "oidOficinaDestino", scope = LineaDTO.class)
    public JAXBElement<String> createLineaDTOOidOficinaDestino(String value) {
        return new JAXBElement<String>(_LineaDTOOidOficinaDestino_QNAME, String.class, LineaDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "oficinaDestino", scope = LineaDTO.class)
    public JAXBElement<String> createLineaDTOOficinaDestino(String value) {
        return new JAXBElement<String>(_LineaDTOOficinaDestino_QNAME, String.class, LineaDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VisibilidadEnum }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "visibilidad", scope = LineaDTO.class)
    public JAXBElement<VisibilidadEnum> createLineaDTOVisibilidad(VisibilidadEnum value) {
        return new JAXBElement<VisibilidadEnum>(_LineaDTOVisibilidad_QNAME, VisibilidadEnum.class, LineaDTO.class, value);
    }

}
