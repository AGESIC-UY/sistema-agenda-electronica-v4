package uy.gub.imm.sae.business.ejb.servicios;

import javax.ejb.Stateless;
import uy.gub.imm.sae.entity.Reserva;
import uy.gub.imm.sae.entity.global.Empresa;

import org.apache.log4j.Logger;
import uy.gub.agesic.novedades.Acciones;
import uy.gub.imm.sae.business.ejb.facade.ConfiguracionLocal;

import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import org.jboss.ejb3.annotation.TransactionTimeout;
import uy.gub.agesic.novedades.NuevaNovedadService;
import uy.gub.agesic.novedades.NuevaNovedadService_Service;
import uy.gub.agesic.novedades.ObjectFactory;
import uy.gub.agesic.novedades.Publicar;
import uy.gub.imm.sae.business.ws.PublishSubscribeHeadersHandler;
import uy.gub.imm.sae.business.ws.SoapHandler;
import uy.gub.imm.sae.entity.Agenda;
import uy.gub.imm.sae.entity.DatoReserva;
import uy.gub.imm.sae.entity.Disponibilidad;
import uy.gub.imm.sae.entity.Recurso;
import uy.gub.imm.sae.entity.global.Novedad;

/**
 * Clase encargada del envío de notificaciones al Sistema de Novedades de
 * AGESIC.
 *
 * El envío se realiza en dos pasos: 1 - Cuando otra clase invoca el método
 * publicarNovedad(...) la novedad se registra en la base de datos pero no se
 * envía en el momento (para liberar rápidamente la thread y no bloquear al
 * usuario). 2 - Cada x minutos (2 actualmente) el método
 * enviarNovedadesPendientes(...) se encarga de enviar las novedades pendientes.
 *
 * @author spio
 *
 */
@Stateless
public class ServiciosNovedadesBean {

    @PersistenceContext(unitName = "AGENDA-GLOBAL")
    private EntityManager globalEntityManager;

    @EJB
    private ConfiguracionLocal confBean;

    private static Logger logger = Logger.getLogger(ServiciosNovedadesBean.class);

    private static final DateFormat FULL_TIME_DF = new SimpleDateFormat("yyyyMMdd HHmm ZZZ");

    /**
     * @param empresa
     * @param reserva
     * @param accion
     */
    public void publicarNovedad(Empresa empresa, Reserva reserva, Acciones accion) {

//        logger.warn("No se ejecuta el método publicarNovedad porque no fue migrado");

        logger.debug("Novedad recibida para publicar: reserva " + reserva.getId());

        Disponibilidad dispon = reserva.getDisponibilidades().get(0);
        Recurso recurso = dispon.getRecurso();
        Agenda agenda = recurso.getAgenda();

        boolean habilitado = false;
        logger.info("Determinando si la agenda se integra con novedades...");
        if (agenda.getPublicarNovedades() != null) {
            habilitado = agenda.getPublicarNovedades();
        }
        if (!habilitado) {
            logger.debug("La publicación de novedades no está habilitada en la agenda indicada, se ignora la solicitud.");
            return;
        }
        
        try {
            logger.info("Determinando si el Organismo de la empresa se integra con novedades...");
            habilitado = confBean.getBoolean("WS_NOVEDADES_HABILITADO", empresa.getOrganismoId());
        } catch (Exception nfEx) {
            habilitado = false;
        }
        if (!habilitado) {
            logger.debug("La publicación de novedades no está habilitada en la instalación, se ignora la solicitud.");
            return;
        }

        ObjectFactory objFactory = new ObjectFactory();
        Publicar novedad = objFactory.createPublicar();

        try {
            novedad.setTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
        } catch (Exception ex) {
            novedad.setTimestamp(null);
        }
        novedad.setTipoDocumento(reserva.getTipoDocumento());
        novedad.setPaisDocumento("");
        novedad.setNumeroDocumento(reserva.getNumeroDocumento());
        novedad.setFechaHoraReserva(FULL_TIME_DF.format(dispon.getHoraInicio()));
        novedad.setNumeroReserva(reserva.getNumero() == null ? "" : reserva.getNumero().toString());
        novedad.setOidOrganismo(empresa.getOid());
        novedad.setNombreOrganismo(empresa.getNombre());
        novedad.setCodigoAgenda(reserva.getTramiteCodigo());
        novedad.setNombreAgenda(agenda.getNombre());
        novedad.setCodigoRecurso(recurso.getOficinaId());
        novedad.setNombreRecurso(recurso.getNombre());
        novedad.setAccion(accion);
        
        Publicar.Datosextras datos = objFactory.createPublicarDatosextras();
        for (DatoReserva valor : reserva.getDatosReserva()) {
            if (valor != null && valor.getId() != null && valor.getDatoASolicitar() != null) {
                if (valor.getDatoASolicitar().getIncluirEnNovedades() != null 
                        && valor.getDatoASolicitar().getIncluirEnNovedades().equals(Boolean.TRUE)) {
                    
                    Publicar.Datosextras.Datoextra dato = objFactory.createPublicarDatosextrasDatoextra();
                    dato.setNombre(valor.getDatoASolicitar().getNombre());
                    dato.setTipo(valor.getDatoASolicitar().getTipo().getDescripcion());
                    dato.setValor(valor.getValor());
                    datos.getDatoextra().add(dato);
                }

            }
        }
        
        if(datos!=null && datos.getDatoextra()!=null && !datos.getDatoextra().isEmpty()){
                novedad.setDatosextras(datos);
        }
        
        
        logger.debug("Registrando la novedad en la base de datos...");

        Integer novedadId = registrarNovedad(empresa, reserva, novedadToXml(novedad));

        if (novedadId != null) {
            logger.debug("La novedad fue registrada en la base de datos (id=" + novedadId + ", no enviada aún).");
        } else {
            logger.debug("La novedad no fue registrada en la base de datos.");
        }
    }

    private String novedadToXml(Publicar novedad) {
        if (novedad == null) {
            return null;
        }
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Publicar.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(novedad, sw);
            String xmlString = sw.toString();
            return xmlString;
        } catch (Exception ex) {
            logger.warn("No se pudo convertir la novedad a XML: " + ex.getMessage(), ex);
        }
        return null;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private Integer registrarNovedad(Empresa empresa, Reserva reserva, String datos) {
        if (datos == null) {
            return null;
        }
        
        boolean novedad_xml_log = false;
        try {
                novedad_xml_log = confBean.getBoolean("WS_NOVEDADES_XML_LOG", empresa.getOrganismoId());
        } catch (Exception ex) {
                novedad_xml_log = false;
        }

        if (novedad_xml_log) {
                logger.info("XML DE NOVEDAD V2: " + datos);
        }
        
        Novedad novedad = new Novedad();
        novedad.setDatos(datos);
        novedad.setEnviado(false);
        novedad.setFechaCreacion(new Date());
        novedad.setFechaUltIntento(new Date());
        novedad.setIntentos(0);
        novedad.setEmpresa(empresa);
        novedad.setReservaId(reserva.getId());
        globalEntityManager.persist(novedad);
        return novedad.getId();
    }

    private Publicar xmlToNovedad(String xml) {
        if (xml == null) {
            return null;
        }
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Publicar.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            Publicar novedad = (Publicar) jaxbUnmarshaller.unmarshal(reader);
            return novedad;
        } catch (Exception ex) {
            logger.warn("No se pudo convertir el XML a la novedad: " + ex.getMessage(), ex);
        }
        return null;
    }

    //El id puede ser cualquier número que compartan todos los módulos y que no entre en conflicto con
    //otro lock
    private static final long LOCK_ID = 1515151515;

    @SuppressWarnings("unchecked")
    @TransactionTimeout(value = 30, unit = TimeUnit.MINUTES)
    @Schedule(second = "30", minute = "*/5", hour = "*", persistent = false)
    //@Schedule(second = "30", minute = "*/1", hour = "*", persistent = false)
    public void enviarNovedadesPendientes() {

        logger.info("Enviando novedades pendientes...");

        //Intentar liberar el lock por si lo tiene esta instancia
        globalEntityManager.createNativeQuery("SELECT pg_advisory_unlock(" + LOCK_ID + ")").getSingleResult();
        //Intentar obtener el lock
        boolean lockOk = (boolean) globalEntityManager.createNativeQuery("SELECT pg_try_advisory_lock(" + LOCK_ID + ")").getSingleResult();
        if (!lockOk) {
            //Otra instancia tiene el lock
            logger.info("No se ejecuta el envío de novedades porque hay otra instancia haciéndolo.");
            return;
        }
        //No hay otra instancia con el lock, se continúa

        try {
            //Obtener los identificadores de los organismos que tienen habilitada el envío de novedades
            String eql = "SELECT c.organismoId FROM Configuracion c WHERE c.clave='WS_NOVEDADES_HABILITADO' AND UPPER(c.valor)='TRUE'";
            Query query = globalEntityManager.createQuery(eql);
            List<Integer> organismosId = (List<Integer>) query.getResultList();
            
            //Si no hay ninguna, solo retornar
            if(organismosId==null || organismosId.isEmpty()) {
                logger.info("No se ejecuta el reintento de trazas porque no hay ningún organismo que lo tenga habilitado.");
                return;
            }
 
            URL urlWsdl = NuevaNovedadService_Service.class.getResource("PublicacionTopico-SAENovedades_v2.wsdl");
            NuevaNovedadService_Service novedadService = new NuevaNovedadService_Service(urlWsdl);
            NuevaNovedadService novedadPort = novedadService.getNuevaNovedadPort();
            
            //Para cada organismo que admita novedades, obtener las pendientes y enviarlas
            for(Integer organismoId : organismosId) {
                boolean habilitado = false;
                try {
                    habilitado = confBean.getBoolean("WS_NOVEDADES_HABILITADO", organismoId);
                } catch (Exception nfEx) {
                    habilitado = false;
                }
                if (!habilitado) {
                    logger.debug("La publicación de novedades no está habilitada en la instalación.");
                    return;
                }

                int maxIntentos = 15;
                try {
                    maxIntentos = confBean.getLong("WS_NOVEDADES_MAXINTENTOS", organismoId).intValue();
                } catch (Exception nfEx) {
                    maxIntentos = 15;
                }
                
                String wsAddressLocation = confBean.getString("WS_NOVEDADES_LOCATION", organismoId);
                logger.info("ORGANISMO COD: " + organismoId + " WS URL NOVEDADES: " + wsAddressLocation);
                int timeout;
                try {
                    timeout = confBean.getLong("WS_NOVEDADES_TIMEOUT", organismoId).intValue();
                } catch (Exception nfEx) {
                    timeout = 5000;
                }
                
                String wsProductor = confBean.getString("WS_NOVEDADES_PRODUCTOR", organismoId);

                String wsTopico = confBean.getString("WS_NOVEDADES_TOPICO", organismoId);

                configurarWSPort(novedadPort, wsAddressLocation, timeout, wsProductor, wsTopico);
                
                logger.debug("Determinando novedades pendientes de envío...");

                eql = "SELECT n FROM Novedad n WHERE n.enviado=FALSE AND n.intentos<:maxIntentos ORDER BY id";

                logger.debug("Consulta para determinar las novedades pendientes: " + eql);

                query = globalEntityManager.createQuery(eql);
                query.setParameter("maxIntentos", maxIntentos);
                List<Novedad> novedades = (List<Novedad>) query.setMaxResults(100).getResultList();

                logger.info("Se encontraron " + novedades.size() + " novedades pendientes de envío.");

                if (!novedades.isEmpty()) {
                    try {
                        for (Novedad novedad0 : novedades) {
                            
                            Publicar novedad = xmlToNovedad(novedad0.getDatos());
                            novedad0.setFechaUltIntento(new Date());
                            novedad0.setIntentos(novedad0.getIntentos() + 1);
                            try {
                                novedadPort.nuevaNovedad(novedad);;
                                novedad0.setEnviado(true);
                            } catch (Exception ex) {
                                logger.warn("No se pudo enviar una novedad!", ex);
                            } finally {
                                try {
                                    globalEntityManager.merge(novedad0);
                                } catch (Exception ex) {
                                    //
                                    ex.printStackTrace();
                                }
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    logger.debug("" + novedades.size() + " novedades procesadas.");
                }
            }
        } finally {
            //Intentar liberar el lock (si lo tiene esta instancia)
            globalEntityManager.createNativeQuery("SELECT pg_advisory_unlock(" + LOCK_ID + ")").getSingleResult();
            logger.info("Procesamiento de novedades pendientes finalizado.");
        }
    }

    
    //ToDo: el código en SAE viejo añadía además un handler exclusivo para novedades para indicar el tópico destino, que no se como se pone en el conector
    @SuppressWarnings("rawtypes")
    private void configurarWSPort(Object port, String wsAddressLocation, int timeout, String productor, String topico) {

        logger.debug("Propiedades del servicio web: ");
        logger.debug("--- wsAddressLocation: " + wsAddressLocation);
        logger.debug("--- timeout: " + timeout);

        BindingProvider bindingProvider = (BindingProvider) port;

        Map<String, Object> reqContext = bindingProvider.getRequestContext();
        reqContext.put("javax.xml.ws.client.connectionTimeout", timeout);
        reqContext.put("javax.xml.ws.client.receiveTimeout", timeout);
        if (wsAddressLocation != null) {
            //Si no se define se deja lo que tenga el WSDL
            reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, wsAddressLocation);
        }

        List<Handler> customHandlerChain = new ArrayList<>();
        customHandlerChain.add(new SoapHandler());
        customHandlerChain.add(new PublishSubscribeHeadersHandler(productor, topico));
        bindingProvider.getBinding().setHandlerChain(customHandlerChain);
    }
    
}
