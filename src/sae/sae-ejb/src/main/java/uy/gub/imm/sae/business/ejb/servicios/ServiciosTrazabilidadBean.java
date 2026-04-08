package uy.gub.imm.sae.business.ejb.servicios;

import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import javax.ejb.*;
import javax.persistence.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.jboss.ejb3.annotation.TransactionTimeout;

import uy.gub.agesic.itramites.bruto.web.ws.cabezal.CabezalDTO;
import uy.gub.agesic.itramites.bruto.web.ws.cabezal.CabezalResponseDTO;
import uy.gub.agesic.itramites.bruto.web.ws.cabezal.CabezalService;
import uy.gub.agesic.itramites.bruto.web.ws.cabezal.CapturaCabezalSOAP;
import uy.gub.agesic.itramites.bruto.web.ws.cabezal.DatosProcesoTramiteCanalDeInicioEnum;
import uy.gub.agesic.itramites.bruto.web.ws.cabezal.DatosProcesoTramiteDTO;
import uy.gub.agesic.itramites.bruto.web.ws.cabezal.DatosProcesoTramiteInicioAsistidoProcesoEnum;
import uy.gub.agesic.itramites.bruto.web.ws.cabezal.IdTrazaDTO;
import uy.gub.agesic.itramites.bruto.web.ws.cabezal.InvolucradoDTO;
import uy.gub.agesic.itramites.bruto.web.ws.cabezal.RoleEnum;
import uy.gub.agesic.itramites.bruto.web.ws.cabezal.TipoProcesoEnum;
import uy.gub.agesic.itramites.bruto.web.ws.linea.CapturaLineaSOAP;
import uy.gub.agesic.itramites.bruto.web.ws.linea.DatosProcesoTramiteLineaDTO;
import uy.gub.agesic.itramites.bruto.web.ws.linea.EstadoProcesoEnum;
import uy.gub.agesic.itramites.bruto.web.ws.linea.LineaDTO;
import uy.gub.agesic.itramites.bruto.web.ws.linea.LineaDTO.Involucrados;
import uy.gub.agesic.itramites.bruto.web.ws.linea.LineaService;
import uy.gub.agesic.itramites.bruto.web.ws.linea.ResponseDTO;
import uy.gub.agesic.itramites.bruto.web.ws.linea.TipoRegistroTrazabilidadEnum;
import uy.gub.imm.sae.business.dto.InformacionRequestDTO;
import uy.gub.imm.sae.business.ejb.facade.ConfiguracionLocal;
import uy.gub.imm.sae.business.ejb.facade.RecursosLocal;
import uy.gub.imm.sae.business.em.TenantContext;
import uy.gub.imm.sae.business.ws.SoapHandler;
import uy.gub.imm.sae.entity.Agenda;
import uy.gub.imm.sae.entity.DatoASolicitar;
import uy.gub.imm.sae.entity.Reserva;
import uy.gub.imm.sae.entity.ValorPosible;
import uy.gub.imm.sae.entity.global.Empresa;
import uy.gub.imm.sae.entity.global.Trazabilidad;

//@Stateless
@Singleton
@Lock(LockType.READ)
@SuppressWarnings("UseSpecificCatch")
public class ServiciosTrazabilidadBean {

	private static final String WS_TRAZABILIDAD_LOCATION_LINEA = "WS_TRAZABILIDAD_LOCATION_LINEA";
	private static final String WS_TRAZABILIDAD_LOCATION_CABEZAL = "WS_TRAZABILIDAD_LOCATION_CABEZAL";
	private static final String WS_TRAZABILIDAD_TIMEOUT = "WS_TRAZABILIDAD_TIMEOUT";

	@PersistenceContext(unitName = "AGENDA-GLOBAL")
	private EntityManager globalEntityManager;

	private static final Logger logger = Logger.getLogger(ServiciosTrazabilidadBean.class);

	@EJB
	private ConfiguracionLocal confBean;
	@EJB
	private RecursosLocal recursosBean;

	@PersistenceContext(unitName = "SAE-EJB")
	private EntityManager entityManager;

	private ReentrantLock lock = new ReentrantLock();

	/*
	 * Por documentación del campo estado ver el documento
	 * Metadato_Trazas_Edicion_01_SinIntro.pdf Estados: 1=Inicio, 2=En ejecución,
	 * 3=Finalizado, 4=Cancelado
	 */
	public static enum Paso {
		RESERVA(1L, 2, "Reserva"), ASISTENCIA(2L, 2, "Atención"), INASISTENCIA(3L, 2, "No asistencia"),
		CANCELACION(4L, 4, "Cancelación"), FINALIZACION(5L, 3, "Finalización");

		long lPaso;
		int estado;
		String asunto;

		Paso(long lPaso, int estado, String asunto) {
			this.lPaso = lPaso;
			this.estado = estado;
			this.asunto = asunto;
		}

		public long getLPaso() {
			return lPaso;
		}

		public int getEstado() {
			return estado;
		}

		public String getAsunto() {
			return asunto;
		}
	}

	public static enum Tipo {
		CABEZAL, LINEA
	}

	public String registrarCabezal(Empresa empresa, Reserva reserva, String transaccionId, String procesoId,
			boolean inicioAsistido, String transaccionPadreId, Long pasoPadre,
			InformacionRequestDTO informacionRequestDTO) {

//        logger.warn("No se ejecuta el método registrarCabezal porque no fue migrado");
//        return null;

		logger.debug("Comenzando a registrar un cabezal de traza con id de transacción " + transaccionId);

		boolean habilitado = false;
		// Primero ver si la agenda soporta trazabilidad
		logger.info("Determinando si la agenda se integra con trazabilidad...");
		Agenda agenda = reserva.getDisponibilidades().get(0).getRecurso().getAgenda();
		if (agenda.getConTrazabilidad() != null) {
			habilitado = agenda.getConTrazabilidad();
		}
		if (!habilitado) {
			logger.debug("Se ignora la solicitud porque trazabilidad no está habilitado para la agenda.");
			return null;
		}
		// Después ver si la instalación soporta trazabilidad
		logger.info("Determinando si SAE se integra con trazabilidad...");
		try {
			habilitado = confBean.getBoolean("WS_TRAZABILIDAD_HABILITADO", empresa.getOrganismoId());
		} catch (Exception nfEx) {
			habilitado = false;
		}
		if (!habilitado) {
			logger.debug("Se ignora la solicitud porque trazabilidad no está habilitado para la instalación.");
			return null;
		}
		CabezalDTO traza = null;
		try {
			/*URL urlWsdl = CabezalService.class.getResource("CabezalService.wsdl");
			urlWsdl = CabezalService.class.getResource("wsdl/CabezalServiceV2.wsdl");*/

			// Consultar el servicio web
			CabezalService cabezalService = new CabezalService();

			// CabezalWS cabezalPort = cabezalService.getCabezalWSPort();

			CapturaCabezalSOAP cabezalPort = cabezalService.getCapturaCabezalSOAPPort();

			traza = new CabezalDTO();

			DatosProcesoTramiteDTO datosProceso = new DatosProcesoTramiteDTO();

			IdTrazaDTO idTrazaDTO = new IdTrazaDTO();

			IdTrazaDTO idTrazaPadreDTO = new IdTrazaDTO();

			traza.setOidOficina(empresa.getOid());

			traza.setOficina(empresa.getOrganismoNombre());

			traza.setFechaHoraOrganismo(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));

			traza.setAppOrigen("SAE v4");

			// I.Friedmann: El modelo de trazabilidad está pensado para que aplique a
			// cualquier tipo de proceso:
			// trámites, expedientes, compras, reclamos, etc... en el caso de Agenda se
			// trata de Trámites, y el valor
			// que corresponde a ese tipo de proceso es el 1

			// traza.setTipoProceso(1);			
			idTrazaDTO.setOidOrganismo(empresa.getOid());

			idTrazaDTO.setTipoProceso(TipoProcesoEnum.TRAMITE);

			// En caso de que Tipo de proceso sea trámite utilizar la codiguera de la guía
			// de trámites
			try {
				String[] partes = procesoId.split("-");
				if (partes.length > 1) {
					procesoId = partes[1];
				}
				// traza.setIdProceso(Integer.valueOf(procesoId));

				idTrazaDTO.setIdProceso(Integer.valueOf(procesoId));
			} catch (Exception ex) {
				// traza.setIdProceso(0);

				idTrazaDTO.setIdProceso(0);
			}
			// OID organismo + ":" + Identificador del Proceso + ":" + Código interno del
			// trámite

			// traza.setIdTransaccion(transaccionId);
			String idInstancia = transaccionId;
			String [] instancia = transaccionId.split(":");
			if(instancia.length > 2) {
				idInstancia = instancia[2];
			}

			idTrazaDTO.setIdInstancia(idInstancia);

			// Versión (01.00) del Modelo de Metadatos de Referencia de Trazabilidad
			long version;
			try {
				version = confBean.getLong("WS_TRAZABILIDAD_VERSION", empresa.getOrganismoId());
			} catch (NumberFormatException nfEx) {
				version = 100;
			}

			// traza.setEdicionModelo(version);

			// Es obligatorio, si no hay, es el mismo que transaccionId
			/*if (transaccionPadreId != null && !transaccionPadreId.trim().isEmpty()) {
				// traza.setIdTransaccionPadre(transaccionPadreId);

				idTrazaPadreDTO.setIdInstancia(transaccionPadreId);
			} else {
				idTrazaPadreDTO.setIdInstancia(transaccionId);
			}*/
			//
			if (pasoPadre != null) {
				// traza.setPasoPadre(pasoPadre);

				traza.setPasoPadre(new JAXBElement<Long>(QName.valueOf("pasoPadre"), Long.class, pasoPadre));
			}
			
			
			if (transaccionPadreId != null && !transaccionPadreId.trim().isEmpty()) {
				// traza.setIdTransaccionPadre(transaccionPadreId);
				idTrazaPadreDTO.setOidOrganismo("");				
				idTrazaPadreDTO.setIdInstancia(transaccionPadreId);
				idTrazaPadreDTO.setTipoProceso(TipoProcesoEnum.TRAMITE);
				String [] trazaPadreDTOarr = transaccionPadreId.split(":");
				if(trazaPadreDTOarr.length > 2) {
					idTrazaPadreDTO.setOidOrganismo(trazaPadreDTOarr[0]);
					idTrazaPadreDTO.setIdProceso(Integer.valueOf(trazaPadreDTOarr[1]));
					idTrazaPadreDTO.setIdInstancia(trazaPadreDTOarr[2]);
					
				}
				traza.setIdTrazaPadre(new JAXBElement<IdTrazaDTO>(QName.valueOf("idTrazaPadre"), IdTrazaDTO.class, idTrazaPadreDTO));
			} 
			/*else {				
				idTrazaPadreDTO.setIdInstancia(transaccionId);
			}*/
			//
			/*if (pasoPadre == null) {
				// traza.setPasoPadre(pasoPadre);
				pasoPadre = 0L;
			}
			traza.setPasoPadre(new JAXBElement<Long>(QName.valueOf("pasoPadre"), Long.class, pasoPadre));*/
			// Hay 3 pasos: 1=Reserva, 2=Atención (asiste o no), 3=Finalización (al final
			// del día)

			// traza.setCantidadPasosProceso(3L);

			traza.setCantidadPasosProceso(new JAXBElement<Long>(QName.valueOf("cantidadPasosProceso"), Long.class, 3L));

			// Si se hace desde la web pública es 0 en otro caso es 1
			if (inicioAsistido) {
				// traza.setInicioAsistidoProceso(1);

				datosProceso.setInicioAsistidoProceso(DatosProcesoTramiteInicioAsistidoProcesoEnum.SI);

			} else {
				// traza.setInicioAsistidoProceso(0);

				datosProceso.setInicioAsistidoProceso(DatosProcesoTramiteInicioAsistidoProcesoEnum.NO);
			}
			datosProceso.setCanalDeInicio(DatosProcesoTramiteCanalDeInicioEnum.WEB_PC);

			// 1=Web PC, 2=Web Móvil, 3=Presencial, 4=Redes de Cobranza, 5=PAC,
			// 6=Telefónico, 7=Correo electrónico
			// ToDo: si es una reserva pública, habría que ver si es 1 o 2; si es privada
			// habría que ver si es 3, 5 o 6
			// traza.setCanalDeInicio(1);
			// Ejemplo: 2015-11-16, 17:26:32 +03:00

			traza.setIdTraza(idTrazaDTO);
			datosProceso.setDatosExtra(this.getDatosExtraCabezal(version, empresa, agenda, reserva, informacionRequestDTO));
			traza.setDatosProceso(datosProceso);

			/**
			 * TODO: resolver mapeo de codigo
			 */
			if (StringUtils.isNotEmpty(reserva.getTipoDocumento()) && StringUtils.isNotEmpty(reserva.getDocumento())) {
				InvolucradoDTO involucradoDTO = generarDTOInvolucrado(empresa, reserva);
				traza.setInvolucrados(new uy.gub.agesic.itramites.bruto.web.ws.cabezal.CabezalDTO.Involucrados());
				traza.getInvolucrados().getInvolucrado().add(involucradoDTO);
			}

			String wsAddressLocation = confBean.getString(WS_TRAZABILIDAD_LOCATION_CABEZAL, empresa.getOrganismoId());
			logger.info("Organismo cod: " + empresa.getOrganismoCodigo() + "URL WS CABEZAL " + wsAddressLocation);
			int timeout;
			try {
				timeout = confBean.getLong(WS_TRAZABILIDAD_TIMEOUT, empresa.getOrganismoId()).intValue();
			} catch (Exception nfEx) {
				timeout = 5000;
			}

			configurarWSPort(cabezalPort, wsAddressLocation, timeout);

			logger.debug("Realizando la invocación del servicio web...");
			
			logger.info("Cabezal Request: " + cabezalToXml(traza));

			CabezalResponseDTO cabezalResp = cabezalPort.persist(traza);
			if (cabezalResp.getEstado().equals(uy.gub.agesic.itramites.bruto.web.ws.cabezal.EstadoRespuestaEnum.OK)) {
				registrarTraza(empresa, reserva.getId(), transaccionId, cabezalToXml(traza), true, false, true);

				logger.debug("El cabezal de la traza fue registrado correctamente");

				return cabezalResp.getGuid();
			} else {
				// No se pudo registrar la traza pero no se puede cancelar la reserva
				logger.warn("No se pudo registrar el cabezal de la traza para la reserva " + transaccionId + ": "
						+ cabezalResp.getMensaje());
				registrarTraza(empresa, reserva.getId(), transaccionId, cabezalToXml(traza), true, false, false);
			}
		} catch (Exception ex) {
			logger.warn("No se pudo registrar el cabezal de la traza para la reserva " + transaccionId + ": "
					+ ex.getMessage(), ex);
			registrarTraza(empresa, reserva.getId(), transaccionId, cabezalToXml(traza), true, false, false);
		}
		return null;
	}

	private InvolucradoDTO generarDTOInvolucrado(Empresa empresa, Reserva reserva) {
		InvolucradoDTO involucradoDTO = new InvolucradoDTO();
		String codigoPais = "uy";
		if (!StringUtils.isEmpty(reserva.getPaisDocumento())) {
			codigoPais = reserva.getPaisDocumento();
		}
		String tipoDocumento = reserva.getTipoDocumento();
		DatoASolicitar datoSolicitar = reserva.getDatoSolicitarDeTipoDocumento();
		if (datoSolicitar != null) {
			TimeZone timezone = determinarTimeZoneDeReserva(empresa, reserva);
			ValorPosible valorPosible = recursosBean.obtenerValorPosiblePorValor(tipoDocumento, datoSolicitar.getId(),
					timezone);
			if (valorPosible != null && !StringUtils.isEmpty(valorPosible.getValorEnTraza())) {
				tipoDocumento = valorPosible.getValorEnTraza();
			}
		}
		String documento = reserva.getDocumento();
		String[] arrOfStr = documento.split(" ");
		try {
			int documentoAux = Integer.parseInt(arrOfStr[1]);
			if (documentoAux > 0)
				documento = arrOfStr[1];

		}catch (Exception e) {
			logger.warn("Sin Documento");
		}

		involucradoDTO.setOid(codigoPais + "-" + tipoDocumento + "-" + documento);
		involucradoDTO.setRole(RoleEnum.SOLICITANTE);
		return involucradoDTO;
	}

	private uy.gub.agesic.itramites.bruto.web.ws.linea.InvolucradoDTO generarDTOLineaInvolucrado(Empresa empresa,
			Reserva reserva) {
		uy.gub.agesic.itramites.bruto.web.ws.linea.InvolucradoDTO involucradoDTO = new uy.gub.agesic.itramites.bruto.web.ws.linea.InvolucradoDTO();
		String codigoPais = "uy";
		if (!StringUtils.isEmpty(reserva.getPaisDocumento())) {
			codigoPais = reserva.getPaisDocumento();
		}
		String tipoDocumento = reserva.getTipoDocumento();
		DatoASolicitar datoSolicitar = reserva.getDatoSolicitarDeTipoDocumento();
		if (datoSolicitar != null) {
			TimeZone timezone = determinarTimeZoneDeReserva(empresa, reserva);
			ValorPosible valorPosible = recursosBean.obtenerValorPosiblePorValor(tipoDocumento, datoSolicitar.getId(),
					timezone);
			if (valorPosible != null && !StringUtils.isEmpty(valorPosible.getValorEnTraza())) {
				tipoDocumento = valorPosible.getValorEnTraza();
			}
		}
		String documento = reserva.getDocumento();
		String[] arrOfStr = documento.split(" ");
		try {
			int documentoAux = Integer.parseInt(arrOfStr[1]);
			if (documentoAux > 0)
				documento = arrOfStr[1];

		}catch (Exception e) {
			logger.warn("Sin Documento");
		}
		involucradoDTO.setOid(codigoPais + "-" + tipoDocumento + "-" + documento);
		involucradoDTO.setRole(uy.gub.agesic.itramites.bruto.web.ws.linea.RoleEnum.SOLICITANTE);
		return involucradoDTO;
	}

	private TimeZone determinarTimeZoneDeReserva(Empresa empresa, Reserva reserva) {
		// Determinar el timezone según la agenda o la empresa
		TimeZone timezone = TimeZone.getDefault();
		Agenda agenda = reserva.getDisponibilidades().get(0).getRecurso().getAgenda();
		if (agenda.getTimezone() != null && !agenda.getTimezone().isEmpty()) {
			timezone = TimeZone.getTimeZone(agenda.getTimezone());
		} else if (empresa.getTimezone() != null && !empresa.getTimezone().isEmpty()) {
			timezone = TimeZone.getTimeZone(empresa.getTimezone());
		}
		return timezone;
	}

	/**
	 * El registro de las líneas siempre se realiza posteriormente, para no trancar
	 * al usuario (solo es necesario hacerlo sincrónico en el cabezal para obtener
	 * el código de trazabilidad)
	 *
	 *
	 * @param empresa
	 * @param reserva
	 * @param transaccionId
	 * @param oficina
	 * @param paso
	 */
	public void registrarLinea(Empresa empresa, Reserva reserva, String transaccionId, String oficina, Paso paso) {

//        logger.warn("No se ejecuta el método registrarLinea porque no fue migrado");

		logger.debug("Comenzando a registrar una línea de traza con id de transacción " + transaccionId);

		boolean habilitado = false;

		// Primero ver si la agenda soporta trazabilidad
		logger.info("Determinando si la agenda se integra con trazabilidad...");
		Agenda agenda = reserva.getDisponibilidades().get(0).getRecurso().getAgenda();
		if (agenda.getConTrazabilidad() != null) {
			habilitado = agenda.getConTrazabilidad();
		}
		if (!habilitado) {
			return;
		}
		// Después ver si la instalación soporta trazabilidad
		logger.info("Determinando si SAE se integra con trazabilidad...");
		try {
			habilitado = confBean.getBoolean("WS_TRAZABILIDAD_HABILITADO", empresa.getOrganismoId());
		} catch (Exception nfEx) {
			habilitado = false;
		}
		if (!habilitado) {
			logger.debug("Se ignora la solicitud porque trazabilidad no está habilitado para la agenda.");
			return;
		}

		// Determinar el número de pasoProceso que le corresponde
		String eql = "SELECT count(t.id) FROM Trazabilidad t WHERE t.transaccionId=:transaccionId AND t.esCabezal=FALSE";
		Query query = globalEntityManager.createQuery(eql);
		query.setParameter("transaccionId", transaccionId);
		long pasoProceso = (Long) query.getSingleResult();
		pasoProceso++; // Este pasoProceso es uno más que la cantidad de lineas de la transacción
		LineaDTO traza = new LineaDTO();
		// traza.setIdTransaccion(transaccionId);

		DatosProcesoTramiteLineaDTO datosProceso = new DatosProcesoTramiteLineaDTO();
		uy.gub.agesic.itramites.bruto.web.ws.linea.IdTrazaDTO idTraza = new uy.gub.agesic.itramites.bruto.web.ws.linea.IdTrazaDTO();

		idTraza.setOidOrganismo(empresa.getOid());
		idTraza.setTipoProceso(uy.gub.agesic.itramites.bruto.web.ws.linea.TipoProcesoEnum.TRAMITE);
		String idInstancia = transaccionId;
		String [] instancia = transaccionId.split(":");
		if(instancia.length > 2) {
			idInstancia = instancia[2];
		}

		idTraza.setIdInstancia(idInstancia);

		String procesoId = reserva.getTramiteCodigo();

		try {
			String[] partes = procesoId.split("-");
			if (partes.length > 1) {
				procesoId = partes[1];
			}
			// traza.setIdProceso(Integer.valueOf(procesoId));

			idTraza.setIdProceso(Integer.valueOf(procesoId));
		} catch (Exception ex) {
			// traza.setIdProceso(0);

			idTraza.setIdProceso(0);
		}

		long version;
		try {
			version = confBean.getLong("WS_TRAZABILIDAD_VERSION", empresa.getOrganismoId());
		} catch (NumberFormatException nfEx) {
			version = 100;
		}
		// traza.setEdicionModelo(version);

		//String datosExtra = "edicionModelo: " + version;
		String datosExtra = this.getDatosExtraLinea(version, empresa, agenda, reserva);
		datosProceso.setDatosExtra(datosExtra);
		traza.setDatosProceso(datosProceso);

		traza.setIdTraza(idTraza);
		/*traza.setOidOficina(reserva.getDisponibilidades().get(0).getRecurso().getOficinaId());
		traza.setOficina(oficina);
		 */
		traza.setOidOficina(empresa.getOid());
		traza.setOficina(empresa.getOrganismoNombre());
		traza.setAppOrigen("SAE v4");

		try {
			traza.setFechaHoraOrganismo(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
		} catch (Exception ex) {
			traza.setFechaHoraOrganismo(null);
		}
		// traza.setPaso(paso.getLPaso());
		// traza.setDescripcionDelPaso(paso.getAsunto());
		// traza.setEstadoProceso(paso.estado);
		// traza.setTipoRegistroTrazabilidad(3); //Comun
		// traza.setPasoDelProceso(pasoProceso);

		traza.setEtapa(new JAXBElement<Long>(QName.valueOf("etapa"), Long.class, paso.getLPaso()));
		traza.setDescripcionDeLaEtapa(
				new JAXBElement<String>(QName.valueOf("descripcionDeLaEtapa"), String.class, paso.getAsunto()));
		traza.setEstadoProceso(this.getEstadoProceso(paso.getEstado()));
		traza.setTipoRegistroTrazabilidad(TipoRegistroTrazabilidadEnum.COMUN);
		traza.setPasoDelProceso(new JAXBElement<Long>(QName.valueOf("pasoDelProceso"), Long.class, pasoProceso));

		if (StringUtils.isNotEmpty(reserva.getTipoDocumento()) && StringUtils.isNotEmpty(reserva.getDocumento())) {
			uy.gub.agesic.itramites.bruto.web.ws.linea.InvolucradoDTO involucradoDTO = generarDTOLineaInvolucrado(
					empresa, reserva);
			traza.setInvolucrados(new Involucrados());
			traza.getInvolucrados().getInvolucrado().add(involucradoDTO);
		}

		registrarTraza(empresa, reserva.getId(), transaccionId, lineaToXml(traza), false, false, false);

		logger.debug("La línea de la traza fue registrada correctamente");
	}
	private String getDatosExtraLinea(long version, Empresa empresa, Agenda agenda, Reserva reserva){
		String datosExtra = "";
		datosExtra += "involucradoDocumento: " + reserva.getDocumento() + ", ";
		datosExtra += "edicionModelo: " + version + ", ";
		datosExtra += "agendaId: " + agenda.getId() + ", ";
		datosExtra += "agendaNombre: " + agenda.getNombre() + ", ";
		datosExtra += "agendaDescripcion: " + agenda.getDescripcion() + ", ";
		if (reserva.getDisponibilidades().size() > 0) {
			datosExtra += "recursoId: " + reserva.getDisponibilidades().get(0).getRecurso().getId() + ", ";
			datosExtra += "recursoNombre: " + reserva.getDisponibilidades().get(0).getRecurso().getNombre() + ", ";
			datosExtra += "recursoDescripcion: " + reserva.getDisponibilidades().get(0).getRecurso().getDescripcion()
					+ ", ";
		}
		datosExtra += "fechaHoraReserva: " + reserva.getFechaHora().toString() + ", ";
		datosExtra += "codigoCancelacion: " + reserva.getCodigoSeguridad();
		return datosExtra;
	}
	private String getDatosExtraCabezal(long version, Empresa empresa, Agenda agenda, Reserva reserva,
			InformacionRequestDTO informacionRequestDTO) {
		String datosExtra = "";
		datosExtra += "involucradoDocumento: " + reserva.getDocumento() + ", ";
		datosExtra += "edicionModelo: " + version + ", ";
		datosExtra += "agendaId: " + agenda.getId() + ", ";
		datosExtra += "agendaNombre: " + agenda.getNombre() + ", ";
		datosExtra += "agendaDescripcion: " + agenda.getDescripcion() + ", ";
		if (reserva.getDisponibilidades().size() > 0) {
			datosExtra += "recursoId: " + reserva.getDisponibilidades().get(0).getRecurso().getId() + ", ";
			datosExtra += "recursoNombre: " + reserva.getDisponibilidades().get(0).getRecurso().getNombre() + ", ";
			datosExtra += "recursoDescripcion: " + reserva.getDisponibilidades().get(0).getRecurso().getDescripcion()
					+ ", ";
		}
		datosExtra += "fechaHoraReserva: " + reserva.getFechaHora().toString() + "";
		datosExtra += "codigoCancelacion: " + reserva.getCodigoSeguridad();

		/**
		 * TODO: crear metodo utilitario para armar los links de cancelacion
		 */

		if (informacionRequestDTO != null) {
			datosExtra += ", ";
			String linkBase = informacionRequestDTO.getScheme() + "://" + informacionRequestDTO.getServerName();
			if ("http".equals(informacionRequestDTO.getScheme()) && informacionRequestDTO.getServerPort() != 80
					|| "https".equals(informacionRequestDTO.getScheme())
							&& informacionRequestDTO.getServerPort() != 443) {
				linkBase = linkBase + ":" + informacionRequestDTO.getServerPort();
			}
			String linkCancelacion = linkBase + informacionRequestDTO.getContextPath()
					+ "/cancelarReserva/Paso1.xhtml?e=" + empresa.getId() + "&amp;a=" + agenda.getId() + "&amp;ri="
					+ reserva.getId();

			datosExtra += "urlCancelacion: " + linkCancelacion;
		}

		datosExtra += ".";

		return datosExtra;
	}

	private EstadoProcesoEnum getEstadoProceso(int estado) {
		EstadoProcesoEnum estadoProceso = null;
		switch (estado) {
		case 1:
			estadoProceso = EstadoProcesoEnum.INICIO;
			break;
		case 2:
			estadoProceso = EstadoProcesoEnum.EN_EJECUCION;
			break;
		case 3:
			estadoProceso = EstadoProcesoEnum.FINALIZADO;
			break;
		case 4:
			estadoProceso = EstadoProcesoEnum.CANCELADO;
			break;
		default:
			estadoProceso = EstadoProcesoEnum.INICIO;
			break;
		}
		return estadoProceso;
	}

	private String cabezalToXml(CabezalDTO cabezal) {
		if (cabezal == null) {
			return null;
		}
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(CabezalDTO.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			StringWriter sw = new StringWriter();
			QName qName = new QName("PP", cabezal.getClass().getName());
            JAXBElement<Object> root = new JAXBElement<Object>(qName, Object.class, cabezal);
			jaxbMarshaller.marshal(root, sw);
			String xmlString = sw.toString();
			return xmlString;
		} catch (Exception ex) {
			if (cabezal.getIdTraza() != null) {
				logger.warn("No se pudo convertir el cabezal de la traza para la reserva "
						+ cabezal.getIdTraza().getIdInstancia() + " a XML: " + ex.getMessage(), ex);
			} else {
				logger.warn("No se pudo convertir el cabezal de la traza a XML: " + ex.getMessage(), ex);
			}
		}
		return null;
	}

	private String lineaToXml(LineaDTO linea) {
		if (linea == null) {
			return null;
		}
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(LineaDTO.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			StringWriter sw = new StringWriter();
			QName qName = new QName("PP", linea.getClass().getName());
            JAXBElement<Object> root = new JAXBElement<Object>(qName, Object.class, linea);
			jaxbMarshaller.marshal(root, sw);
			String xmlString = sw.toString();
			return xmlString;
		} catch (Exception ex) {
			logger.warn("No se pudo convertir la linea de la traza para la reserva "
					+ linea.getIdTraza().getIdInstancia() + " a XML: " + ex.getMessage(), ex);
		}
		return null;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void registrarTraza(Empresa empresa, Integer reservaId, String transaccionId, String datos,
			boolean esCabezal, boolean esFinal, boolean enviado) {

		if (datos == null) {
			return;
		}
		Trazabilidad traza = new Trazabilidad();
		traza.setDatos(datos);
		traza.setEnviado(enviado);
		traza.setFechaCreacion(new Date());
		traza.setFechaUltIntento(new Date());
		traza.setIntentos(0);
		traza.setTransaccionId(transaccionId);
		traza.setEsCabezal(esCabezal);
		traza.setEmpresa(empresa);
		traza.setReservaId(reservaId);
		traza.setEsFinal(esFinal);
		globalEntityManager.persist(traza);
	}

	private CabezalDTO xmlToCabezal(String xml) {
		if (xml == null) {
			return null;
		}
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(CabezalDTO.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xml);
			JAXBElement<Object> root = (JAXBElement<Object>) jaxbUnmarshaller.unmarshal(reader);
			CabezalDTO cabezal = (CabezalDTO) root.getValue();
			return cabezal;
		} catch (Exception ex) {
			logger.warn("No se pudo convertir el XML al cabezal de la traza: " + ex.getMessage(), ex);
		}
		return null;
	}

	private LineaDTO xmlToLinea(String xml) {
		if (xml == null) {
			return null;
		}
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(LineaDTO.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();			
			StringReader reader = new StringReader(xml);
			JAXBElement<Object> root = (JAXBElement<Object>) jaxbUnmarshaller.unmarshal(reader);
			LineaDTO linea = (LineaDTO) root.getValue();
			return linea;
		} catch (Exception ex) {
			logger.warn("No se pudo convertir el XML a la linea de la traza: " + ex.getMessage(), ex);
		}
		return null;
	}

	private static final long LOCK_ID_REINTENTAR = 1414141414;

	@SuppressWarnings("unchecked")
	@TransactionTimeout(value = 30, unit = TimeUnit.MINUTES)
	@Schedule(second = "0", minute = "*/5", hour = "*", persistent = false)
	// @Schedule(second = "0", minute = "*/1", hour = "*", persistent = false)
	public void reintentarTrazas() {

		logger.info("Reintentando el envío de trazas pendientes...");

		try {
			// Intentar liberar el lock por si lo tiene esta instancia
			globalEntityManager.createNativeQuery("SELECT pg_advisory_unlock(" + LOCK_ID_REINTENTAR + ")")
					.getSingleResult();
			// Intentar obtener el lock
			boolean lockOk = (boolean) globalEntityManager
					.createNativeQuery("SELECT pg_try_advisory_lock(" + LOCK_ID_REINTENTAR + ")").getSingleResult();
			if (!lockOk) {
				// Otra instancia tiene el lock
				logger.info("No se ejecuta el reintento de trazas porque hay otra instancia haciéndolo.");
				return;
			}
			// No hay otra instancia con el lock, se continúa

			// Obtener los identificadores de los organismos que tienen habilitada el envío
			// de trazas
			String eql = "SELECT c.organismoId FROM Configuracion c WHERE c.clave='WS_TRAZABILIDAD_HABILITADO' AND UPPER(c.valor)='TRUE'";
			Query query = globalEntityManager.createQuery(eql);
			List<Integer> organismosId = query.getResultList();

			// Si no hay ninguna, solo retornar
			if (organismosId == null || organismosId.isEmpty()) {
				logger.info(
						"No se ejecuta el reintento de trazas porque no hay ningún organismo que lo tenga habilitado.");
				return;
			}

			//URL urlWsdl = CabezalService.class.getResource("CabezalService.wsdl");
			//CabezalService cabezalService = new CabezalService(urlWsdl);
			// CabezalWS cabezalPort = cabezalService.getCabezalWSPort();
			CabezalService cabezalService = new CabezalService();

			CapturaCabezalSOAP cabezalPort = cabezalService.getCapturaCabezalSOAPPort();

			LineaService lineaService = new LineaService();
			//LineaService lineaService = new LineaService(LineaService.class.getResource("LineaService.wsdl"));
			// LineaWS lineaPort = lineaService.getLineaWSPort();

			CapturaLineaSOAP lineaPort = lineaService.getCapturaLineaSOAPPort();

			// Para cada organismo que admita trazabiliad, obtener sus trazas y enviarlas
			for (Integer organismoId : organismosId) {

				int maxIntentos;
				try {
					maxIntentos = confBean.getLong("WS_TRAZABILIDAD_MAXINTENTOS", organismoId).intValue();
				} catch (Exception nfEx) {
					maxIntentos = 15;
				}

				eql = "SELECT t FROM Trazabilidad t WHERE t.empresa.organismoId=:organismoId AND t.enviado=FALSE AND t.intentos<:maxIntentos ORDER BY t.id";

				logger.debug("Consulta para determinar las trazas pendientes: " + eql);

				query = globalEntityManager.createQuery(eql);
				query.setParameter("maxIntentos", maxIntentos);
				query.setParameter("organismoId", organismoId);
				List<Trazabilidad> trazas = query.setMaxResults(100).getResultList();

				logger.debug("Se encontraron " + trazas.size() + " trazas pendientes de envío para el organismo "
						+ organismoId);

				if (!trazas.isEmpty()) {

					int timeout;
					try {
						timeout = confBean.getLong(WS_TRAZABILIDAD_TIMEOUT, organismoId).intValue();
					} catch (Exception nfEx) {
						timeout = 5000;
					}

					// Determinar el nombre de la tabla de reservas
					Table tabla = Reserva.class.getAnnotation(Table.class);
					String nombreTablaReservas = "reservas";
					if (tabla != null) {
						nombreTablaReservas = tabla.name();
					}

					String wsAddressLocationCabezal = confBean.getString(WS_TRAZABILIDAD_LOCATION_CABEZAL, organismoId);
					String wsAddressLocationLinea = confBean.getString(WS_TRAZABILIDAD_LOCATION_LINEA, organismoId);

					configurarWSPort(cabezalPort, wsAddressLocationCabezal, timeout);
					configurarWSPort(lineaPort, wsAddressLocationLinea, timeout);

					for (Trazabilidad traza : trazas) {

						traza.setFechaUltIntento(new Date());
						traza.setIntentos(traza.getIntentos() + 1);

						logger.debug("Procesando la traza [" + traza.getId() + "] ("
								+ (traza.getEsCabezal() ? "cabezal" : "línea") + ")");
						try {
							if (traza.getEsCabezal()) {
								CabezalDTO cabezal = xmlToCabezal(traza.getDatos());
								CabezalResponseDTO cabezalResp = cabezalPort.persist(cabezal);
								if (cabezalResp.getEstado()
										.equals(uy.gub.agesic.itramites.bruto.web.ws.cabezal.EstadoRespuestaEnum.OK)) {
									// Actualizar el codigo de trazabilidad en la tabla de reservas
									// Y enviar el mail al usuario
									if (traza.getEmpresa() != null && traza.getReservaId() != null) {
										try {
											//
											String esquema = traza.getEmpresa().getDatasource();
											// Actualizar el codigo de trazabilidad en la tabla de reservas
											String sql = "UPDATE " + esquema + "." + nombreTablaReservas
													+ " SET trazabilidad_guid=:trazabilidadGuid WHERE id=:reservaId";
											Query update = globalEntityManager.createNativeQuery(sql);
											update.setParameter("trazabilidadGuid", cabezalResp.getGuid());
											update.setParameter("reservaId", traza.getReservaId());
											update.executeUpdate();
										} catch (Exception ex) {
											logger.warn(
													"No se pudo actualizar el código de trazabilidad para la reserva "
															+ traza.getReservaId() + ", traza " + traza.getId(),
													ex);
										}
									}
									traza.setEnviado(true);
								} else {
									logger.warn("El servicio se invocó correctamente pero no respondió OK: "
											+ cabezalResp.getEstado() + "/" + cabezalResp.getMensaje());
									traza.setEnviado(false);
								}
							} else {
								LineaDTO linea = xmlToLinea(traza.getDatos());
								ResponseDTO lineaResp = lineaPort.persist(linea);
								if (lineaResp.getEstado()
										.equals(uy.gub.agesic.itramites.bruto.web.ws.linea.EstadoRespuestaEnum.OK)) {
									traza.setEnviado(true);
								} else {
									traza.setEnviado(false);
								}
							}
							logger.debug("La traza [" + traza.getId() + "] fue procesada correctamente.");
						} catch (Exception ex) {
							logger.debug("La traza [" + traza.getId() + "] no pudo ser procesada!", ex);
						} finally {
							try {
								globalEntityManager.merge(traza);
							} catch (Exception ex) {
								//
							}
						}
					}
				}
			}
		} finally {
			// Intentar liberar el lock (si lo tiene esta instancia)
			globalEntityManager.createNativeQuery("SELECT pg_advisory_unlock(" + LOCK_ID_REINTENTAR + ")")
					.getSingleResult();
			logger.info("Ejecución del reintento de trazas finalizada.");
		}

	}

	/**
	 * Aplica la política de seguridad requerida para invocar un servicio web en la
	 * plataforma. Obtiene el token STS, añade los handlers necesarios y configura
	 * los timeouts.
	 *
	 * @return La fecha en la cual vence el token para que el cliente pida otro si
	 *         está vencido.
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private void configurarWSPort(Object port, String wsAddressLocation, int timeout) {

		logger.debug("Propiedades del servicio web: ");
		logger.debug("--- wsAddressLocation: " + wsAddressLocation);
		logger.debug("--- timeout: " + timeout);

		BindingProvider bindingProvider = (BindingProvider) port;

		Map<String, Object> reqContext = bindingProvider.getRequestContext();
		reqContext.put("javax.xml.ws.client.connectionTimeout", timeout);
		reqContext.put("javax.xml.ws.client.receiveTimeout", timeout);
		if (wsAddressLocation != null) {
			// Si no se define se deja lo que tenga el WSDL
			reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, wsAddressLocation);
		}

		List<Handler> customHandlerChain = new ArrayList<>();
		customHandlerChain.add(new SoapHandler());
		bindingProvider.getBinding().setHandlerChain(customHandlerChain);

	}

	/**
	 *
	 * @param organismoOid: OID del organismo
	 * @param tramiteId:    id del trámite asociado a la agenda. El formato debe ser
	 *                      empresa-idtramite
	 * @param idInterno:    id de la reserva
	 * @return
	 */
	public String armarTransaccionId(String organismoOid, String tramiteId, Integer idInterno) {
		if (organismoOid == null || tramiteId == null || idInterno == null) {
			return null;
		}
		// El id del trámite incluye al id de la empresa (ej: 13-1855 -> tramite = 1855)
		String[] partes = tramiteId.split("-");
		if (partes.length > 1) {
			tramiteId = partes[1];
		}
		return organismoOid + ":" + tramiteId + ":" + idInterno;
	}

	// El id puede ser cualquier número que compartan todos los módulos y que no
	// entre en conflicto con
	// otro lock
	private static final long LOCK_ID_FINALIZAR = 1313131313;

	@SuppressWarnings("unchecked")
	@Schedule(second = "0", minute = "0", hour = "1", timezone = "America/Montevideo", persistent = false)
	//@Schedule(second = "0", minute = "*/5", hour = "*", persistent = false)
	@TransactionTimeout(value = 30, unit = TimeUnit.MINUTES)
	public void finalizarTrazas() {

		long inicio = System.currentTimeMillis();
		try {

			logger.info("Iniciando ejecución de cierre de trazas...");
			boolean isLockAcquired = lock.tryLock();
			if (!isLockAcquired) {
				// Otra instancia tiene el lock
				logger.info("No se ejecuta el cierre de trazas porque hay otra instancia haciéndolo.");
				return;
			}


			// No hay otra instancia con el lock, se continúa

			// Template para la consulta de reservas vencidas con trazabilidad habilitada
			// Se buscan todas las reservas correspondientes a disponibilidades anteriores
			// al día de hoy
			// que tengan alguna traza registrada y no tengan una traza marcada como final
			String sql = "SELECT r.id, t.transaccion_id, s.nombre, count(*) AS lineas "
					+ "FROM {empresa}.ae_disponibilidades d "
					+ "JOIN {empresa}.ae_reservas_disponibilidades rd ON rd.aedi_id=d.id "
					+ "JOIN {empresa}.ae_reservas r ON r.id=rd.aers_id "
					+ "JOIN {empresa}.ae_recursos s ON s.id=d.aere_id "
					+ "JOIN global.ae_trazabilidad t ON t.reserva_id=r.id AND t.empresa_id={empresaId} "
					+ "WHERE NOT EXISTS( " + "  SELECT 1 FROM global.ae_trazabilidad t1 "
					+ "  WHERE t1.empresa_id=t.empresa_id " + " AND t1.reserva_id=t.reserva_id "
					+ " AND t1.es_final=true) " + "AND d.fecha < :ahora " + "GROUP BY r.id, t.transaccion_id, s.nombre";

			// Obtener los identificadores de los organismos que tienen habilitada el envío
			// de trazas
			String eql = "SELECT c.organismoId FROM Configuracion c WHERE c.clave='WS_TRAZABILIDAD_HABILITADO' AND UPPER(c.valor)='TRUE'";
			Query query = globalEntityManager.createQuery(eql);
			List<Integer> organismosId = query.getResultList();

			// Si no hay ninguna, solo retornar
			if (organismosId == null || organismosId.isEmpty()) {
				logger.info(
						"No se ejecuta el reintento de trazas porque no hay ningún organismo que lo tenga habilitado.");
				return;
			}

			//LineaService lineaService = new LineaService(LineaService.class.getResource("LineaService.wsdl"));
			LineaService lineaService = new LineaService();
			// LineaWS lineaPort = lineaService.getLineaWSPort();

			CapturaLineaSOAP lineaPort = lineaService.getCapturaLineaSOAPPort();

			// Para cada organismo que admita trazabiliad, obtener sus trazas y enviarlas
			for (Integer organismoId : organismosId) {

				int timeout;
				try {
					timeout = confBean.getLong(WS_TRAZABILIDAD_TIMEOUT, organismoId).intValue();
				} catch (Exception nfEx) {
					timeout = 5000;
				}
				long version;
				try {
					version = confBean.getLong("WS_TRAZABILIDAD_VERSION", organismoId);
				} catch (NumberFormatException nfEx) {
					version = 100;
				}
				String wsAddressLocationLinea = confBean.getString(WS_TRAZABILIDAD_LOCATION_LINEA, organismoId);

				configurarWSPort(lineaPort, wsAddressLocationLinea, timeout);

				// Cargar todas las empresas del organismo
				query = globalEntityManager.createQuery(
						"SELECT e FROM Empresa e WHERE e.organismoId=:organismoId AND e.fechaBaja IS NULL");
				List<Empresa> empresas = query.setParameter("organismoId", organismoId).getResultList();
				// Para cada empresa determinar sus trazas vencidas y abiertas y tratar de
				// cerrarlas
				Integer reservaId;
				String transaccionId = null;
				String oficina;
				BigInteger lineas;

				for (Empresa empresa : empresas) {
					try {
						String sql1 = sql.replace("{empresa}", empresa.getDatasource()).replace("{empresaId}",
								empresa.getId().toString());

						logger.debug("Consulta para determinar las trazas a cerrar en la empresa ["
								+ empresa.getNombre() + "]: " + sql1);

						query = globalEntityManager.createNativeQuery(sql1);
						query.setParameter("ahora", new Date(), TemporalType.DATE);
						List<Object[]> aTrazas = query.getResultList();

						logger.debug("Se encontraron " + aTrazas.size() + " trazas para la empresa ["
								+ empresa.getNombre() + "].");

						int iTraza = 0;
						for (Object[] aTraza : aTrazas) {
							iTraza++;
							try {
								reservaId = (Integer) aTraza[0];
								transaccionId = (String) aTraza[1];
								oficina = (String) aTraza[2];
								lineas = (BigInteger) aTraza[3];

								LineaDTO linea = new LineaDTO();
								// linea.setIdTransaccion(transaccionId);

								uy.gub.agesic.itramites.bruto.web.ws.linea.IdTrazaDTO idTrazaLinea = new uy.gub.agesic.itramites.bruto.web.ws.linea.IdTrazaDTO();
								idTrazaLinea.setOidOrganismo(empresa.getOid());
								idTrazaLinea.setTipoProceso(uy.gub.agesic.itramites.bruto.web.ws.linea.TipoProcesoEnum.TRAMITE);
								String idInstancia = transaccionId;
								String procesoId = "0";
								String [] instancia = transaccionId.split(":");
								if(instancia.length > 2) {
									idInstancia = instancia[2];
									procesoId = instancia[1];
								}
								idTrazaLinea.setIdInstancia(idInstancia);
								idTrazaLinea.setIdProceso(Integer.valueOf(procesoId));
								linea.setIdTraza(idTrazaLinea);

								// linea.setEdicionModelo(version);
								TenantContext.setCurrentTenant(empresa.getDatasource());
								Query queryRs = entityManager.createQuery(
										"SELECT r FROM Reserva r WHERE r.id=:reservaId");
								Reserva reserva = (Reserva) queryRs.setParameter("reservaId", reservaId).getSingleResult();
								//Reserva reserva = entityManager.find(Reserva.class, reservaId);
								Agenda agenda = reserva.getDisponibilidades().get(0).getRecurso().getAgenda();
								DatosProcesoTramiteLineaDTO datosProceso = new DatosProcesoTramiteLineaDTO();
								//String datosExtra = "edicionModelo: " + version;
								String datosExtra = this.getDatosExtraLinea(version, empresa, agenda, reserva);
								datosProceso.setDatosExtra(datosExtra);
								linea.setDatosProceso(datosProceso);
								linea.setOidOficina(empresa.getOid());
								linea.setOficina(empresa.getOrganismoNombre());
								//linea.setOficina(oficina);
								linea.setAppOrigen("SAE v4");
								try {
									linea.setFechaHoraOrganismo(DatatypeFactory.newInstance()
											.newXMLGregorianCalendar(new GregorianCalendar()));
								} catch (Exception ex) {
									linea.setFechaHoraOrganismo(null);
								}

								// linea.setPaso(Paso.FINALIZACION.getLPaso());
								// linea.setDescripcionDelPaso(Paso.FINALIZACION.getAsunto());
								// linea.setEstadoProceso(Paso.FINALIZACION.getEstado());
								// linea.setTipoRegistroTrazabilidad(3); //Comun
								// linea.setPasoDelProceso(lineas.longValue() + 1);

								linea.setEtapa(new JAXBElement<Long>(QName.valueOf("etapa"), Long.class,
										Paso.FINALIZACION.getLPaso()));
								linea.setDescripcionDeLaEtapa(
										new JAXBElement<String>(QName.valueOf("descripcionDeLaEtapa"), String.class,
												Paso.FINALIZACION.getAsunto()));
								linea.setEstadoProceso(this.getEstadoProceso(Paso.FINALIZACION.getEstado()));
								linea.setTipoRegistroTrazabilidad(TipoRegistroTrazabilidadEnum.COMUN);
								linea.setPasoDelProceso(new JAXBElement<Long>(QName.valueOf("pasoDelProceso"),
										Long.class, lineas.longValue()));
								if (StringUtils.isNotEmpty(reserva.getTipoDocumento()) && StringUtils.isNotEmpty(reserva.getDocumento())) {
									uy.gub.agesic.itramites.bruto.web.ws.linea.InvolucradoDTO involucradoDTO = generarDTOLineaInvolucrado(
											empresa, reserva);
									linea.setInvolucrados(new Involucrados());
									linea.getInvolucrados().getInvolucrado().add(involucradoDTO);
								}

								ResponseDTO lineaResp = lineaPort.persist(linea);
								// Solo se registra la traza final si se pudo enviar correctamente, ya que en
								// otro caso se requiere
								// volver a generar una traza con la fecha del momento
								lineaResp.setEstado(uy.gub.agesic.itramites.bruto.web.ws.linea.EstadoRespuestaEnum.OK);
								if (lineaResp.getEstado()
										.equals(uy.gub.agesic.itramites.bruto.web.ws.linea.EstadoRespuestaEnum.OK)) {
									registrarTraza(empresa, reservaId, transaccionId, lineaToXml(linea), false, true,
											true);
									logger.debug("La traza " + transaccionId + " de la empresa " + empresa.getId()
											+ " (" + empresa.getNombre() + ") fue cerrada (" + iTraza + "/"
											+ aTrazas.size() + ").");
								} else {
									logger.debug("La traza " + transaccionId + " de la empresa " + empresa.getId()
											+ " (" + empresa.getNombre() + ") no fue cerrada: " + lineaResp.getMensaje()
											+ " (" + iTraza + "/" + aTrazas.size() + ").");
								}
							} catch (Exception ex) {
								logger.error("No se pudo cerrar la traza " + transaccionId + " de la empresa "
										+ empresa.getId() + " (" + empresa.getNombre() + ")", ex);
							} finally {
								TenantContext.clear();
							}
						}
					} catch (Exception ex) {
						logger.error("No se pudo cerrar las trazas de la empresa " + empresa.getId() + " ("
								+ empresa.getNombre() + ")", ex);
					}
				}
			}
		} finally {
			// Intentar liberar el lock (si lo tiene esta instancia)
			lock.unlock();
			long finalizacion = System.currentTimeMillis();
			long tiempoejecucion = finalizacion - inicio;
			logger.info("Se finaliza la finalizacion de las trazas : "+finalizacion);
			logger.info("Tiempo de ejecucion la finalizacion de las trazas : "+tiempoejecucion);
			logger.info("Ejecución de cierre de trazas finalizada.");
		}
	}

}
