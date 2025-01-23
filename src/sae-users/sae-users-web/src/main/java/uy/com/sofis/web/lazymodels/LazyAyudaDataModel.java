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
import uy.com.sofis.business.services.AyudaBusinessBean;
import uy.com.sofis.constantes.ConstantesComponentesId;
import uy.com.sofis.entities.Ayuda;
import uy.com.sofis.filtros.FiltroCodiguera;
import uy.com.sofis.web.presentacion.mensajes.Mensajes;
import uy.com.sofis.web.utils.JSFUtils;

/**
 *
 * @author Sofis Solutions
 */
public class LazyAyudaDataModel extends LazyDataModel<Ayuda> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(LazyAyudaDataModel.class.getName());
    private static final long serialVersionUID = 1L;
    private AyudaBusinessBean ayudaBean;
    private FiltroCodiguera filtro;

    public LazyAyudaDataModel(AyudaBusinessBean ayudaBean, FiltroCodiguera filtro, Long rowCount, Integer pageSize) {
        this.ayudaBean = ayudaBean;
        this.filtro = filtro;
        this.setRowCount(rowCount.intValue());
        this.setPageSize(pageSize);
    }

    @Override
    public Ayuda getRowData(String rowKey) {
        try {
            return ayudaBean.obtenerPorId(Long.valueOf(rowKey));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarError(ConstantesComponentesId.ID_MSG_TEMPLATE, Mensajes.obtenerMensaje(Mensajes.ERROR_GENERAL), "");
            return null;
        }
    }

    @Override
    public Object getRowKey(Ayuda c) {
        return c.getAyuId();
    }

    @Override
    public List<Ayuda> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        try {
            filtro.setFirst(new Long(first));
            filtro.setMaxResults(new Long(pageSize));
            if (sortField != null) {
                filtro.setOrderBy(new String[]{sortField});
                filtro.setAscending(new boolean[]{sortOrder.equals(SortOrder.ASCENDING)});
            }
            return ayudaBean.obtenerPorFiltro(filtro);
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
