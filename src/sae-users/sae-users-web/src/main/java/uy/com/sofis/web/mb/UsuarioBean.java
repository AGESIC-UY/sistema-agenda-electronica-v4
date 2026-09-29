/*
 *  SAE-USERS-ADMIN
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.web.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.SerializationUtils;
import org.primefaces.PrimeFaces;

import uy.com.sofis.business.services.OrganismoBusinessBean;
import uy.com.sofis.business.services.RelUsuarioOrganismoBusinessBean;
import uy.com.sofis.business.services.UsuarioBusinessBean;
import uy.com.sofis.constantes.ConstantesComponentesId;
import uy.com.sofis.entities.Organismo;
import uy.com.sofis.entities.Usuario;
import uy.com.sofis.entities.UsuarioOrganismoDTO;
import uy.com.sofis.exceptions.BusinessException;
import uy.com.sofis.exceptions.GeneralException;
import uy.com.sofis.filtros.FiltroCodiguera;
import uy.com.sofis.filtros.FiltroUsuario;
import uy.com.sofis.web.lazymodels.LazyRelUsuarioOrganismoDataModel;
import uy.com.sofis.web.lazymodels.LazyUsuarioDataModel;
import uy.com.sofis.web.presentacion.mensajes.Etiquetas;
import uy.com.sofis.web.presentacion.mensajes.Mensajes;
import uy.com.sofis.web.seguridad.SessionMBean;
import uy.com.sofis.web.utils.JSFUtils;
import uy.com.sofis.web.utils.SofisComboG;

/**
 *
 * @author Sofis Solutions
 */
@Named
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(UsuarioBean.class.getName());

	@Inject
	private UsuarioBusinessBean businessBean;
	@Inject
	private RelUsuarioOrganismoBusinessBean relUsuOrgbusinessBean;
	@Inject
	private OrganismoBusinessBean organismoBusinessBean;
	@Inject
	private SessionMBean sessionMBean;

	private FiltroCodiguera filtro = new FiltroCodiguera();
	private FiltroUsuario filtroUsuario = new FiltroUsuario();
	private Usuario entidadEnEdicion = new Usuario();
	private UsuarioOrganismoDTO usuarioOrgEnEdicion = new UsuarioOrganismoDTO();
	private List<Usuario> historialuario = new ArrayList();
	private List<Organismo> organismos = new ArrayList();
	private List<Organismo> organismosPorUsuario = new ArrayList();
	private Integer paginado = 10;
	private Long totalResultados = 0L;
	private LazyUsuarioDataModel uarioLazyModel;
	private LazyRelUsuarioOrganismoDataModel lazyRelUsuOrgLazyModel;

	private SofisComboG<Organismo> comboOrganismo = new SofisComboG<>();
	private SofisComboG<Organismo> comboOrganismoBus = new SofisComboG<>();
	private Organismo organismoActual;

	private List<UsuarioOrganismoDTO> listUsuarios = new ArrayList();

	private String tituloDialog = new String();

	public UsuarioBean() {
	}

	@PostConstruct
	public void init() {
		try {
			if (!sessionMBean.tieneRoles(new String[] { "RA_AE_ADMINISTRADOR" })) {
				FacesContext ctx = FacesContext.getCurrentInstance();
				FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(ctx, "",
						"noAutorizado");
			}
			organismos = obtenerOrganismos();
			cargarCombos();
			buscar();
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}

	public void buscar() {
		try {
			if (comboOrganismoBus.getSelectedT() != null) {
				filtroUsuario.setOrganismoId(comboOrganismoBus.getSelectedT().getId());
			}
			listUsuarios = relUsuOrgbusinessBean.buscarUsuariosOrganismos(filtroUsuario);
			totalResultados = new Long(listUsuarios.size());

		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
			JSFUtils.agregarError(ConstantesComponentesId.ID_MSG_TEMPLATE,
					Mensajes.obtenerMensaje(Mensajes.ERROR_GENERAL), "");
		}
	}

	public List<Organismo> obtenerOrganismos() {
		try {
			FiltroCodiguera filtroC = new FiltroCodiguera();
			filtroC.setOrderBy(new String[] { "codigo", "nombre" });
			filtroC.setAscending(new boolean[] { true });

			return organismoBusinessBean.obtenerPorFiltro(filtroC);

		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}

		return new ArrayList();
	}

	public void cargarCombos() {
		try {
			comboOrganismoBus = new SofisComboG<>(obtenerOrganismos(), "codigoNombre");
			comboOrganismoBus.addEmptyItem(Etiquetas.getValue("comboEmptyItem"));

		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}

	public void cargarCombosAgregar() {
		try {
			comboOrganismo = new SofisComboG<>();
			comboOrganismo = new SofisComboG<>(obtenerOrganismos(), "codigoNombre");
			comboOrganismo.addEmptyItem(Etiquetas.getValue("comboEmptyItem"));
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}

	private void limpiarCombos() {
		comboOrganismoBus.setSelected(-1);
	}

	public void limpiar() {
		filtroUsuario = new FiltroUsuario();
		limpiarCombos();
		listUsuarios = new ArrayList();
		totalResultados = 0L;
		buscar();
	}

	public void agregar() {
		JSFUtils.limpiarMensajesError();
		entidadEnEdicion = new Usuario();
		organismoActual = null;
		entidadEnEdicion.setIsAdministrador(Boolean.TRUE);
		cargarCombosAgregar();
		tituloDialog = Etiquetas.getValue("nuevousuario");
	}

	public void actualizar(UsuarioOrganismoDTO var) {
		JSFUtils.limpiarMensajesError();
		organismoActual = null;
		usuarioOrgEnEdicion = SerializationUtils.clone(var);

		entidadEnEdicion.setId(usuarioOrgEnEdicion.getId());
		entidadEnEdicion.setCodigo(usuarioOrgEnEdicion.getCodigo());
		entidadEnEdicion.setNombre(usuarioOrgEnEdicion.getNombre());
		entidadEnEdicion.setCorreoe(usuarioOrgEnEdicion.getCorreoe());
		entidadEnEdicion.setPassword(usuarioOrgEnEdicion.getPassword());
		entidadEnEdicion.setIsAdministrador(usuarioOrgEnEdicion.getAdministrador());
		if (usuarioOrgEnEdicion.getOrgId() != null) {
			organismoActual = new Organismo(usuarioOrgEnEdicion.getOrgId(), usuarioOrgEnEdicion.getCodOrganismo(),
					usuarioOrgEnEdicion.getNombreOrganismo());
			entidadEnEdicion.setOrganismoOld(organismoActual);
		}
		cargarCombosAgregar();
		if (organismoActual != null) {
			comboOrganismo.setSelectedT(organismoActual);
			entidadEnEdicion.setOrganismo(organismoActual);
		}
		tituloDialog = Etiquetas.getValue("edicionusuario");
	}

	public void guardar() {
		try {
			if (comboOrganismo.getSelectedT() == null && comboOrganismo.getSelected().equals(-1)) {
				JSFUtils.agregarError(ConstantesComponentesId.ID_MSG_POPUP,
						Mensajes.obtenerMensaje(Mensajes.ERROR_ORGANISMO_VACIO), "");
				return;
			} else {
				if (entidadEnEdicion.getId() == null && entidadEnEdicion.getIsAdministrador().equals(Boolean.FALSE)) {
					JSFUtils.agregarError(ConstantesComponentesId.ID_MSG_POPUP,
							Mensajes.obtenerMensaje(Mensajes.ERROR_NOCHECK_ADMIN), "");
					return;
				}

				LOGGER.log(Level.SEVERE, "admin " + entidadEnEdicion.getIsAdministrador());
				if (entidadEnEdicion.getId() != null && usuarioOrgEnEdicion.getAdministrador() != null
						&& usuarioOrgEnEdicion.getAdministrador().equals(Boolean.FALSE)
						&& entidadEnEdicion.getIsAdministrador().equals(Boolean.FALSE)) {
					JSFUtils.agregarError(ConstantesComponentesId.ID_MSG_POPUP,
							Mensajes.obtenerMensaje(Mensajes.ERROR_NOCHECK_ADMIN), "");
					return;
				}

				if (entidadEnEdicion.getIsAdministrador().equals(Boolean.TRUE)) {
					entidadEnEdicion.setOrganismo(comboOrganismo.getSelectedT());
				}

				entidadEnEdicion = businessBean.guardar(entidadEnEdicion);
				PrimeFaces.current().executeScript("PF('itemDialog').hide()");
				JSFUtils.agregarInfo(ConstantesComponentesId.ID_MSG_TEMPLATE,
						Mensajes.obtenerMensaje(Mensajes.GUARDADO_CORRECTO), "");
				buscar();
			}
		} catch (BusinessException be) {
			LOGGER.log(Level.SEVERE, be.getMessage(), be);
			JSFUtils.agregarMensajes(ConstantesComponentesId.ID_MSG_POPUP, FacesMessage.SEVERITY_ERROR,
					be.getErrores());
		} catch (GeneralException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			if (e.getErrores().size() > 0) {
				JSFUtils.agregarMensajes(ConstantesComponentesId.ID_MSG_POPUP, FacesMessage.SEVERITY_ERROR,
						e.getErrores());
			} else {
				JSFUtils.agregarError(ConstantesComponentesId.ID_MSG_POPUP,
						Mensajes.obtenerMensaje(Mensajes.ERROR_GENERAL), "");
			}
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
			JSFUtils.agregarError(ConstantesComponentesId.ID_MSG_POPUP, Mensajes.obtenerMensaje(Mensajes.ERROR_GENERAL),
					"");
		}
	}

	public void cambioCodigoUsuario() {

		try {
			if (entidadEnEdicion.getCodigo() != null) {
				Usuario usuario = businessBean.obtenerUsuarioPorCodigo(entidadEnEdicion.getCodigo());
				if (usuario != null) {
					setEntidadEnEdicion(usuario);
				}
			}
		} catch (GeneralException e) {
			setEntidadEnEdicion(null); // El metodo setUsuarioEditar se encarga de manejar el caso de null
		}
	}

	public FiltroCodiguera getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroCodiguera filtro) {
		this.filtro = filtro;
	}

	public Usuario getEntidadEnEdicion() {
		return entidadEnEdicion;
	}

	public void setEntidadEnEdicion(Usuario entidadEnEdicion) {
		this.entidadEnEdicion = entidadEnEdicion;
	}

	public List<Usuario> getHistorialuario() {
		return historialuario;
	}

	public void setHistorialuario(List<Usuario> historialuario) {
		this.historialuario = historialuario;
	}

	public Integer getPaginado() {
		return paginado;
	}

	public void setPaginado(Integer paginado) {
		this.paginado = paginado;
	}

	public Long getTotalResultados() {
		return totalResultados;
	}

	public void setTotalResultados(Long totalResultados) {
		this.totalResultados = totalResultados;
	}

	public LazyUsuarioDataModel getuarioLazyModel() {
		return uarioLazyModel;
	}

	public void setTiposCalendarioLazyModel(LazyUsuarioDataModel uarioLazyModel) {
		this.uarioLazyModel = uarioLazyModel;
	}

	public SofisComboG<Organismo> getComboOrganismo() {
		return comboOrganismo;
	}

	public void setComboOrganismo(SofisComboG<Organismo> comboOrganismo) {
		this.comboOrganismo = comboOrganismo;
	}

	public SofisComboG<Organismo> getComboOrganismoBus() {
		return comboOrganismoBus;
	}

	public void setComboOrganismoBus(SofisComboG<Organismo> comboOrganismoBus) {
		this.comboOrganismoBus = comboOrganismoBus;
	}

	public FiltroUsuario getFiltroUsuario() {
		return filtroUsuario;
	}

	public void setFiltroUsuario(FiltroUsuario filtroUsuario) {
		this.filtroUsuario = filtroUsuario;
	}

	public String getTituloDialog() {
		return tituloDialog;
	}

	public void setTituloDialog(String tituloDialog) {
		this.tituloDialog = tituloDialog;
	}

	public Organismo getOrganismoActual() {
		return organismoActual;
	}

	public void setOrganismoActual(Organismo organismoActual) {
		this.organismoActual = organismoActual;
	}

	public List<UsuarioOrganismoDTO> getListUsuarios() {
		return listUsuarios;
	}

	public void setListUsuarios(List<UsuarioOrganismoDTO> listUsuarios) {
		this.listUsuarios = listUsuarios;
	}

	public List<Organismo> getOrganismosPorUsuario() {
		return organismosPorUsuario;
	}

	public void setOrganismosPorUsuario(List<Organismo> organismosPorUsuario) {
		this.organismosPorUsuario = organismosPorUsuario;
	}

}
