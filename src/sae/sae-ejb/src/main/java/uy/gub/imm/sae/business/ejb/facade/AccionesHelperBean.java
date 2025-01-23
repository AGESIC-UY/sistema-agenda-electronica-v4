package uy.gub.imm.sae.business.ejb.facade;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.management.MBeanServer;
import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

import uy.gub.imm.sae.business.dto.ReservaDTO;
import uy.gub.imm.sae.common.Utiles;
import uy.gub.imm.sae.common.enumerados.Evento;
import uy.gub.imm.sae.common.enumerados.Tipo;
import uy.gub.imm.sae.entity.Accion;
import uy.gub.imm.sae.entity.AccionPorDato;
import uy.gub.imm.sae.entity.AccionPorRecurso;
import uy.gub.imm.sae.entity.DatoASolicitar;
import uy.gub.imm.sae.entity.DatoReserva;
import uy.gub.imm.sae.entity.Recurso;
import uy.gub.imm.sae.exception.ApplicationException;
import uy.gub.imm.sae.exception.BusinessException;
import uy.gub.imm.sae.exception.ErrorAccionException;
import uy.gub.sae.acciones.business.dto.RecursoDTO;
import uy.gub.sae.acciones.business.ejb.EjecutorAccion;
import uy.gub.sae.acciones.business.ejb.ErrorAccion;
import uy.gub.sae.acciones.business.ejb.ResultadoAccion;
import uy.gub.sae.acciones.business.ejb.exception.InvalidParametersException;
import uy.gub.sae.acciones.business.ejb.exception.UnexpectedAccionException;

/**
 * Session Bean implementation class AccionesHelperBean
 */
@Stateless
public class AccionesHelperBean implements AccionesHelperLocal {

    private static final Logger LOGGER = Logger.getLogger(AccionesHelperBean.class);
    
    //Parametro fijo que se pasa en todas las invocaciones a validaciones.
    private final String PARAMETRO_RECURSO = "RECURSO";
    private final String PARAMETRO_RESERVA = "RESERVA";

    @PersistenceContext(unitName = "SAE-EJB")
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public List<AccionPorRecurso> obtenerAccionesPorRecurso(Recurso r, Evento e) {
        List<AccionPorRecurso> acciones = (List<AccionPorRecurso>) em.createQuery(
                "SELECT axr "
                + "FROM  AccionPorRecurso axr "
                + "WHERE axr.recurso = :r "
                + "	 AND axr.evento = :e "
                + "  AND axr.fechaBaja = null "
                + "ORDER BY axr.ordenEjecucion ASC "
        )
                .setParameter("r", r)
                .setParameter("e", e)
                .getResultList();
        return acciones;
    }

    @Override
    public void ejecutarAccionesPorEvento(Map<String, DatoReserva> valores, ReservaDTO reserva, Recurso recurso, Evento evento) throws ApplicationException, BusinessException, ErrorAccionException {
        List<AccionPorRecurso> acciones = this.obtenerAccionesPorRecurso(recurso, evento);
        for (AccionPorRecurso aXr : acciones) {
            Accion a = aXr.getAccion();
            if (a.getFechaBaja() == null) {
                List<AccionPorDato> camposDeLaAccion = aXr.getAccionesPorDato();
                Map<String, Object> parametros = new HashMap<>();
                for (AccionPorDato accionPorDato : camposDeLaAccion) {
                    if (accionPorDato.getFechaDesasociacion() == null) {
                        String nombreParametro = accionPorDato.getNombreParametro();
                        DatoASolicitar campo = accionPorDato.getDatoASolicitar();
                        DatoReserva dato = valores.get(campo.getNombre());
                        if (dato != null) {
                            if (campo.getTipo() != null) {
                                switch (campo.getTipo()) {
                                    case NUMBER:
                                        parametros.put(nombreParametro, Integer.valueOf(dato.getValor()));
                                        break;
                                    case BOOLEAN:
                                        parametros.put(nombreParametro, Boolean.valueOf(dato.getValor()));
                                        break;
                                    case DATE:
                                        try {
                                            parametros.put(nombreParametro, Utiles.stringToDate(dato.getValor()));
                                        } catch (Exception ex) {
                                            parametros.put(nombreParametro, dato.getValor());
                                        }
                                        break;
                                    default:
                                        parametros.put(nombreParametro, dato.getValor());
                                        break;
                                }
                            }else {
                                parametros.put(nombreParametro, dato.getValor());
                            }
                        } else {
                            parametros.put(nombreParametro, null);
                        }
                    }
                }
                parametros.put(PARAMETRO_RECURSO, copiarRecurso(aXr.getRecurso()));
                parametros.put(PARAMETRO_RESERVA, reserva);
                EjecutorAccion ejecutor = getEjecutor(a.getHost(), a.getServicio());
                try {
                    //Ejecuto la accion
                    ResultadoAccion resultado = ejecutor.ejecutar(a.getNombre(), parametros);
                    //Hay errores
                    if (resultado.getErrores().size() > 0) {
                        List<String> mensajes = new ArrayList<>();
                        List<String> codigosErrorMensajes = new ArrayList<>();
                        for (ErrorAccion error : resultado.getErrores()) {
                            mensajes.add(error.getMensaje());
                            codigosErrorMensajes.add(error.getCodigo());
                        }
                        throw new ErrorAccionException("-1", mensajes, codigosErrorMensajes, a.getNombre());
                    }
                } catch (UnexpectedAccionException e) {
                    throw new ApplicationException(e);
                } catch (InvalidParametersException e) {
                    List<String> mensajes = new ArrayList<>();
                    mensajes.add(e.getMessage());
                    throw new ErrorAccionException("-1", mensajes);
                }
            }
        }
    }

    private EjecutorAccion getEjecutor(String host, String jndiName) throws ApplicationException {
        Object ejb = null;
        try {
            //A partir de Wildfly 11 el factory debe ser org.wildfly.naming.client.WildFlyInitialContextFactory y
            //el host debe ser http-remoting://localhost:8080 (cambiar el puerto si es necesario)            
            @SuppressWarnings("UseOfObsoleteCollectionType")
            final Hashtable jndiProperties = new Hashtable();
            jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            jndiProperties.put(Context.PROVIDER_URL, host); 
            final Context context = new InitialContext(jndiProperties);
            ejb = context.lookup(jndiName);
        } catch (NamingException nEx) {
            LOGGER.error("No se pudo acceder a un EJB de tipo EjecutorAccion (jndiName: " + jndiName + ")", nEx);
            throw new ApplicationException("No se pudo acceder a un EJB de tipo EjecutorAccion (jndiName: " + jndiName + ")", nEx);
        }
        if (ejb instanceof EjecutorAccion) {
            return (EjecutorAccion) ejb;
        } else {
            LOGGER.error("Se esperaba un EJB de tipo EjecutorAccion y se encontró uno del tipo " + ejb.getClass());
            throw new ApplicationException("Se esperaba un EJB de tipo EjecutorAccion y se encontró uno del tipo " + ejb.getClass());
        }
    }

    private RecursoDTO copiarRecurso(Recurso recurso) {
        RecursoDTO recursoDTO = new RecursoDTO();
        recursoDTO.setId(recurso.getId());
        recursoDTO.setNombre(recurso.getNombre());
        recursoDTO.setDescripcion(recurso.getDescripcion());
        recursoDTO.setCantDiasAGenerar(recurso.getCantDiasAGenerar());
        recursoDTO.setFechaBaja(recurso.getFechaBaja());
        recursoDTO.setFechaFin(recurso.getFechaFin());
        recursoDTO.setFechaFinDisp(recurso.getFechaFinDisp());
        recursoDTO.setFechaInicio(recurso.getFechaInicio());
        recursoDTO.setFechaInicioDisp(recurso.getFechaInicioDisp());
        recursoDTO.setMostrarNumeroEnLlamador(recurso.getMostrarNumeroEnLlamador());
        recursoDTO.setVentanaCuposMinimos(recurso.getVentanaCuposMinimos());
        recursoDTO.setDiasInicioVentanaIntranet(recurso.getDiasInicioVentanaIntranet());
        recursoDTO.setDiasVentanaIntranet(recurso.getDiasVentanaIntranet());
        recursoDTO.setDiasInicioVentanaInternet(recurso.getDiasInicioVentanaInternet());
        recursoDTO.setDiasVentanaInternet(recurso.getDiasVentanaInternet());
        recursoDTO.setSerie(recurso.getSerie());
        recursoDTO.setAgendaDescripcion(recurso.getAgenda().getDescripcion());
        return recursoDTO;
    }


}
