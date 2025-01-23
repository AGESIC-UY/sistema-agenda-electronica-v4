/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.omnifaces.cdi.Param;
import org.primefaces.PrimeFaces;
import uy.com.sofis.business.services.AyudaBusinessBean;
import uy.com.sofis.constantes.ConstantesComponentesId;
import uy.com.sofis.entities.Ayuda;
import uy.com.sofis.exceptions.BusinessException;
import uy.com.sofis.filtros.FiltroCodiguera;
import uy.com.sofis.web.lazymodels.LazyAyudaDataModel;
import uy.com.sofis.web.presentacion.mensajes.Mensajes;
import uy.com.sofis.web.utils.JSFUtils;
import uy.com.sofis.web.presentacion.mensajes.Etiquetas;

@Named
@ViewScoped
public class AyudaBean implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(AyudaBean.class.getName());

    @Inject
    private AyudaBusinessBean lmb;

    private FiltroCodiguera filtro = new FiltroCodiguera();
    private Ayuda entidadEnEdicion = new Ayuda();
    private List<Ayuda> historialAyuda = new ArrayList();
    private Integer paginado = 10;
    private Long totalResultados;
    private LazyAyudaDataModel ayudaLazyModel;
    private Boolean soloLectura = Boolean.FALSE;
    
    @Inject
    @Param(name = "id")
    private Long ayudaId;

    @Inject
    @Param(name = "edit")
    private Boolean editable;

    public AyudaBean() {
    }
    
      @PostConstruct
    public void init() {
        try {
            cargarCombos();
            if (ayudaId != null && ayudaId > 0) {
                    this.actualizar(lmb.obtenerPorId(ayudaId));
                    soloLectura = editable != null ? !editable : soloLectura;              
            }      
            else{
                this.agregar();
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }



    public void cargarCombos() {

    }

    private void limpiarCombos() {

    }

    public void agregar() {
        JSFUtils.limpiarMensajesError();
        limpiarCombos();
        entidadEnEdicion = new Ayuda();
    }

    public void actualizar(Ayuda var) {
        JSFUtils.limpiarMensajesError();
        limpiarCombos();
        entidadEnEdicion = (Ayuda) SerializationUtils.clone(var);
    }

    public void guardar() {
        try {
            entidadEnEdicion = lmb.guardar(entidadEnEdicion);
            PrimeFaces.current().executeScript("PF('itemDialog').hide()");
            JSFUtils.agregarInfo(ConstantesComponentesId.ID_MSG_TEMPLATE, Mensajes.obtenerMensaje(Mensajes.GUARDADO_CORRECTO), "");
           
        } catch (BusinessException be) {
            JSFUtils.agregarMensajes(ConstantesComponentesId.ID_MSG_TEMPLATE, FacesMessage.SEVERITY_ERROR, be.getErrores());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarError(ConstantesComponentesId.ID_MSG_TEMPLATE, Mensajes.obtenerMensaje(Mensajes.ERROR_GENERAL), "");
        }
    }

    public String getTituloPagina(){
        if(ayudaId==null){
            return Etiquetas.getValue("nuevoAyuda");
        }else{
            return Etiquetas.getValue("edicionAyuda")+" "+ entidadEnEdicion.getAyuCodigo();
        }
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

    public List<Ayuda> getHistorialAyuda() {
        return historialAyuda;
    }

    public void setHistorialAyuda(List<Ayuda> historialAyuda) {
        this.historialAyuda = historialAyuda;
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

    public LazyAyudaDataModel getAyudaLazyModel() {
        return ayudaLazyModel;
    }

    public void setTiposCalendarioLazyModel(LazyAyudaDataModel ayudaLazyModel) {
        this.ayudaLazyModel = ayudaLazyModel;
    }
}
