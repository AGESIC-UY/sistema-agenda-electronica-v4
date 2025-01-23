/*
 *  SIGES
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.web.lazymodels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import uy.com.sofis.web.utils.JSFUtils;
import uy.com.sofis.entities.Configuracion;
import uy.com.sofis.business.services.ConfiguracionBusinessBean;
import uy.com.sofis.constantes.ConstantesComponentesId;
import uy.com.sofis.filtros.FiltroCodiguera;
import uy.com.sofis.web.presentacion.mensajes.Mensajes;

/**
 *
 * @author Sofis Solutions
 */
public class LazyConfiguracionDataModel extends LazyDataModel<Configuracion> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(LazyConfiguracionDataModel.class.getName());
    private static final long serialVersionUID = 1L;
    private ConfiguracionBusinessBean configBean;
    private FiltroCodiguera filtro;

    public LazyConfiguracionDataModel(ConfiguracionBusinessBean configBean, FiltroCodiguera filtro, Long rowCount, Integer pageSize) {
        this.configBean = configBean;
        this.filtro = filtro;
        this.setRowCount(rowCount.intValue());
        this.setPageSize(pageSize);
    }

    @Override
    public Configuracion getRowData(String rowKey) {
        try {
            return configBean.obtenerPorId(Long.valueOf(rowKey));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarError(ConstantesComponentesId.ID_MSG_TEMPLATE, Mensajes.obtenerMensaje(Mensajes.ERROR_GENERAL), "");
            return null;
        }
    }

    @Override
    public Object getRowKey(Configuracion c) {
        return c.getCnfId();
    }

    @Override
    public List<Configuracion> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        try {
            filtro.setFirst(new Long(first));
            filtro.setMaxResults(new Long(pageSize));
            if (sortField != null) {
                filtro.setOrderBy(new String[]{sortField});
                filtro.setAscending(new boolean[]{sortOrder.equals(SortOrder.ASCENDING)});
            }
            return configBean.obtenerPorFiltro(filtro);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarError(ConstantesComponentesId.ID_MSG_TEMPLATE, Mensajes.obtenerMensaje(Mensajes.ERROR_GENERAL), "");
            return new ArrayList<>();
        }
    }

    public void setFiltro(FiltroCodiguera filtro) {
        this.filtro = filtro;
    }

}
