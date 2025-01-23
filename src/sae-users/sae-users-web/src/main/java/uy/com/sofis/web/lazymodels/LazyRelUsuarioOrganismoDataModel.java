/*
 *  SAE-USERS-ADMIN
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
import uy.com.sofis.business.services.UsuarioBusinessBean;
import uy.com.sofis.constantes.ConstantesComponentesId;
import uy.com.sofis.entities.Usuario;
import uy.com.sofis.filtros.FiltroUsuario;
import uy.com.sofis.web.presentacion.mensajes.Mensajes;
import uy.com.sofis.web.utils.JSFUtils;

/**
 *
 * @author Sofis Solutions
 */
public class LazyRelUsuarioOrganismoDataModel extends LazyDataModel<Usuario> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(LazyRelUsuarioOrganismoDataModel.class.getName());
    private static final long serialVersionUID = 1L;
    private UsuarioBusinessBean businessBean;
    private FiltroUsuario filtro;

    public LazyRelUsuarioOrganismoDataModel(UsuarioBusinessBean businessBean, FiltroUsuario filtro, Long rowCount, Integer pageSize) {
        this.businessBean = businessBean;
        this.filtro = filtro;
        this.setRowCount(rowCount.intValue());
        this.setPageSize(pageSize);
    }

    @Override
    public Usuario getRowData(String rowKey) {
        try {
            return businessBean.obtenerPorId(Long.valueOf(rowKey));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarError(ConstantesComponentesId.ID_MSG_TEMPLATE, Mensajes.obtenerMensaje(Mensajes.ERROR_GENERAL), "");
            return null;
        }
    }

    @Override
    public Object getRowKey(Usuario c) {
        return c.getId();
    }

    @Override
    public List<Usuario> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        try {
            filtro.setFirst(new Long(first));
            filtro.setMaxResults(new Long(pageSize));
            if (sortField != null) {
                filtro.setOrderBy(new String[]{sortField});
                filtro.setAscending(new boolean[]{sortOrder.equals(SortOrder.ASCENDING)});
            }
            return businessBean.obtenerPorFiltro(filtro);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarError(ConstantesComponentesId.ID_MSG_TEMPLATE, Mensajes.obtenerMensaje(Mensajes.ERROR_GENERAL), "");
            return new ArrayList<>();
        }
    }

    public void setFiltro(FiltroUsuario filtro) {
        this.filtro = filtro;
    }

}
