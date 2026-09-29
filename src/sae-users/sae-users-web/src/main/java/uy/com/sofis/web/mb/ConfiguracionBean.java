package uy.com.sofis.web.mb;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.SerializationUtils;
import org.primefaces.PrimeFaces;
import uy.com.sofis.business.services.ConfiguracionBusinessBean;
import uy.com.sofis.business.services.ConsultaHistorico;
import uy.com.sofis.constantes.ConstantesComponentesId;
import uy.com.sofis.entities.Configuracion;
import uy.com.sofis.exceptions.BusinessException;
import uy.com.sofis.filtros.FiltroCodiguera;
import uy.com.sofis.web.lazymodels.LazyConfiguracionDataModel;
import uy.com.sofis.web.presentacion.mensajes.Mensajes;
import uy.com.sofis.web.utils.JSFUtils;


/**
 *
 * @author Sofis Solutions
 */
@Named
@ViewScoped
public class ConfiguracionBean implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(ConfiguracionBean.class.getName());

    @Inject
    private ConfiguracionBusinessBean restClient;
    
    @Inject
    private ConsultaHistorico consHistorico;

    private FiltroCodiguera filtro = new FiltroCodiguera();
    private Configuracion entidadEnEdicion = new Configuracion();
    private List<Configuracion> historialConfiguracion = new ArrayList();
    private Integer paginado = 10;
    private Long totalResultados;
    private LazyConfiguracionDataModel configuracionLazyModel;

    public ConfiguracionBean() {
    }

    @PostConstruct
    public void init() {
        try {
            cargarCombos();
            buscar();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void buscar() {
        try {
            totalResultados = restClient.obtenerTotalPorFiltro(filtro);
            configuracionLazyModel = new LazyConfiguracionDataModel(restClient, filtro, totalResultados, paginado);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarError(ConstantesComponentesId.ID_MSG_TEMPLATE, Mensajes.obtenerMensaje(Mensajes.ERROR_GENERAL), "");
        }
    }

    public void cargarCombos() {

    }

    private void limpiarCombos() {

    }

    public void limpiar() {
        filtro = new FiltroCodiguera();
        buscar();
    }

    public void agregar() {
        JSFUtils.limpiarMensajesError();
        limpiarCombos();
        entidadEnEdicion = new Configuracion();
    }

    public void actualizar(Configuracion var) {
        JSFUtils.limpiarMensajesError();
        limpiarCombos();
        entidadEnEdicion = (Configuracion) SerializationUtils.clone(var);
    }

    public void guardar() {
        try {
            entidadEnEdicion = restClient.guardar(entidadEnEdicion);
            PrimeFaces.current().executeScript("PF('itemDialog').hide()");
            JSFUtils.agregarInfo(ConstantesComponentesId.ID_MSG_TEMPLATE, Mensajes.obtenerMensaje(Mensajes.GUARDADO_CORRECTO), "");
            buscar();
        } catch (BusinessException be) {
            JSFUtils.agregarMensajes(ConstantesComponentesId.ID_MSG_POPUP, FacesMessage.SEVERITY_ERROR, be.getErrores());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarError(ConstantesComponentesId.ID_MSG_POPUP, Mensajes.obtenerMensaje(Mensajes.ERROR_GENERAL), "");
        }
    }

    public void eliminar() {
        try {
            restClient.eliminar(entidadEnEdicion.getCnfId());
            JSFUtils.agregarInfo(ConstantesComponentesId.ID_MSG_TEMPLATE, Mensajes.obtenerMensaje(Mensajes.ELIMINADO_CORRECTO), "");
            buscar();
            entidadEnEdicion = null;
        } catch (BusinessException be) {
            JSFUtils.agregarMensajes(ConstantesComponentesId.ID_MSG_TEMPLATE, FacesMessage.SEVERITY_ERROR, be.getErrores());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarError(ConstantesComponentesId.ID_MSG_TEMPLATE, Mensajes.obtenerMensaje(Mensajes.ERROR_GENERAL), "");
        }
    }

    public void historial(Long id) {
        try {
            historialConfiguracion  = consHistorico.obtenerHistorialPorId(Configuracion.class, id);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarError(ConstantesComponentesId.ID_MSG_TEMPLATE, Mensajes.obtenerMensaje(Mensajes.ERROR_GENERAL), "");
        }
    }

    public FiltroCodiguera getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroCodiguera filtro) {
        this.filtro = filtro;
    }

    public Configuracion getEntidadEnEdicion() {
        return entidadEnEdicion;
    }

    public void setEntidadEnEdicion(Configuracion entidadEnEdicion) {
        this.entidadEnEdicion = entidadEnEdicion;
    }

    public List<Configuracion> getHistorialConfiguracion() {
        return historialConfiguracion;
    }

    public void setHistorialConfiguracion(List<Configuracion> historialConfiguracion) {
        this.historialConfiguracion = historialConfiguracion;
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

    public LazyConfiguracionDataModel getConfiguracionLazyModel() {
        return configuracionLazyModel;
    }

    public void setTiposCalendarioLazyModel(LazyConfiguracionDataModel configuracionLazyModel) {
        this.configuracionLazyModel = configuracionLazyModel;
    }

}
