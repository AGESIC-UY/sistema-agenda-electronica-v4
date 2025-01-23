/*
 *  Nombre del cliente
 *  Sistema de Gestión
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.web.mb;

import uy.com.sofis.exceptions.BusinessException;
import uy.com.sofis.entities.Ayuda;
import uy.com.sofis.web.presentacion.mensajes.Mensajes;
import uy.com.sofis.web.utils.JSFUtils;
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
import uy.com.sofis.business.services.AyudaBusinessBean;
import uy.com.sofis.business.services.ConsultaHistorico;
import uy.com.sofis.constantes.ConstantesComponentesId;
import uy.com.sofis.filtros.FiltroCodiguera;
import uy.com.sofis.web.lazymodels.LazyAyudaDataModel;

/**
 *
 * @author Usuario
 */
@Named
@ViewScoped
public class AyudaGestionBean implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(AyudaGestionBean.class.getName());

    @Inject
    private AyudaBusinessBean lmb;
    
    @Inject
    private ConsultaHistorico consHistorico;

     private FiltroCodiguera filtro = new FiltroCodiguera();
    private Ayuda entidadEnEdicion = new Ayuda();
    private List<Ayuda> historialAyuda = new ArrayList();
    private Integer paginado = 10;
    private Long totalResultados;
    private LazyAyudaDataModel ayudaLazyModel;

    /**
     * Creates a new instance of AyudaManagedBean
     */
    public AyudaGestionBean (){
    
    }
            
                
    @PostConstruct
    public void init() {
        try {
            buscar();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

    }

    public void buscar() {
        try {
            totalResultados = lmb.obtenerTotalPorFiltro(filtro);
            ayudaLazyModel = new LazyAyudaDataModel(lmb, filtro, totalResultados, paginado);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarError(ConstantesComponentesId.ID_MSG_TEMPLATE, Mensajes.obtenerMensaje(Mensajes.ERROR_GENERAL), "");
        }
    }

      public void limpiar() {
        filtro = new FiltroCodiguera();
        buscar();
    }

    public void agregar() {
        JSFUtils.limpiarMensajesError();
        entidadEnEdicion = new Ayuda();
    }

    public void actualizar(Ayuda var) {
        JSFUtils.limpiarMensajesError();
        entidadEnEdicion = (Ayuda) SerializationUtils.clone(var);
    }



    public void eliminar() {
        try {
            lmb.eliminar(entidadEnEdicion.getAyuId());
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
            historialAyuda = consHistorico.obtenerHistorialPorId(Ayuda.class, id);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarError(ConstantesComponentesId.ID_MSG_TEMPLATE, Mensajes.obtenerMensaje(Mensajes.ERROR_GENERAL), "");
        }
    }

    

    // <editor-fold defaultstate="collapsed" desc=" Getters y Setters">
    
    public Integer getPaginado() {
        return paginado;
    }

    public void setPaginado(Integer paginado) {
        this.paginado = paginado;
    }

    public List<Ayuda> getHistorialAyuda() {
        return historialAyuda;
    }

    public void setHistorialAyuda(List<Ayuda> historialAyuda) {
        this.historialAyuda = historialAyuda;
    }

    public LazyAyudaDataModel getAyudaLazyModel() {
        return ayudaLazyModel;
    }

    public void setAyudaLazyModel(LazyAyudaDataModel ayudaLazyModel) {
        this.ayudaLazyModel = ayudaLazyModel;
    }

    public AyudaBusinessBean getLmb() {
        return lmb;
    }

    public void setLmb(AyudaBusinessBean lmb) {
        this.lmb = lmb;
    }

    public FiltroCodiguera getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroCodiguera filtro) {
        this.filtro = filtro;
    }

    public Ayuda getEntidadEnEdicion() {
        return entidadEnEdicion;
    }

    public void setEntidadEnEdicion(Ayuda entidadEnEdicion) {
        this.entidadEnEdicion = entidadEnEdicion;
    }


    public Long getTotalResultados() {
        return totalResultados;
    }

    public void setTotalResultados(Long totalResultados) {
        this.totalResultados = totalResultados;
    }


    
        // </editor-fold>
}
