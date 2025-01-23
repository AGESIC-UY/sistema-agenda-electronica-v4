/*
 *  Nombre del cliente
 *  Sistema de Gestión
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.business.services;

import uy.com.sofis.exceptions.GeneralException;
import uy.com.sofis.exceptions.TechnicalException;
import uy.com.sofis.entities.Configuracion;
import uy.com.sofis.persistencia.daos.CodigueraDAO;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import uy.com.sofis.business.validaciones.ConfiguracionBusinessValidation;
import uy.com.sofis.exceptions.BusinessException;
import uy.com.sofis.filtros.FiltroCodiguera;

/**
 *
 * @author Usuario
 */
@Stateless
@LocalBean
public class ConfiguracionBusinessBean {
    
    private static final Logger LOGGER = Logger.getLogger(ConfiguracionBusinessBean.class.getName());
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    private ConsultaHistorico ch;

     /**
     * Devuelve el objeto del tipo que tiene el id indicado
     *
     * @param id Long
     * @return SgConfiguracion
     * @throws GeneralException
     */
    public Configuracion obtenerPorId(Long id) throws GeneralException {
        if (id != null) {
            try {
                CodigueraDAO<Configuracion> codDAO = new CodigueraDAO<>(em, Configuracion.class);
                return codDAO.obtenerPorId(id);
            } catch (Exception ex) {
                throw new TechnicalException(ex);
            }
        }
        return null;
    }

    /**
     * Devuelve si el objeto existe
     *
     * @param id Long
     * @return Boolean
     * @throws GeneralException
     */
    public Boolean objetoExistePorId(Long id) throws GeneralException {
        if (id != null) {
            try {
                CodigueraDAO<Configuracion> codDAO = new CodigueraDAO<>(em, Configuracion.class);
                return codDAO.objetoExistePorId(id);
            } catch (Exception ex) {
                throw new TechnicalException(ex);
            }
        }
        return Boolean.FALSE;
    }


    /**
     * Guarda el objeto indicado
     *
     * @param con SgConfiguracion
     * @return SgConfiguracion
     * @throws GeneralException
     */
    public Configuracion guardar(Configuracion con) throws GeneralException {
        try {
            if (con != null) {
                if (ConfiguracionBusinessValidation.validar(con)) {
                    CodigueraDAO<Configuracion> codDAO = new CodigueraDAO<>(em, Configuracion.class);
                    boolean guardar = true;
                    if (con.getCnfId() != null) {
                        //Si el dato ya fue guardado, se determina que haya cambiado alguno de los valores. En caso contrario no se guarda
                        Object valorAnt = ch.obtenerEnVersion(con.getClass(), con.getCnfId() , con.getCnfVersion());
                        Configuracion valorAnterior = (Configuracion) valorAnt;
                        guardar = !ConfiguracionBusinessValidation.compararParaGrabar(valorAnterior, con);
                    }
                    if (guardar) {
                        return codDAO.guardar(con);
                    }
                }
            }
        } catch (BusinessException be) {
            throw be;
        } catch (Exception ex) {
            throw new TechnicalException(ex);
        }

        return con;
    }

    /**
     * Devuelve la cantidad total de elementos que satisfacen el criterio
     *
     * @param filtro FiltroCodiguera
     * @return Long
     * @throws GeneralException
     */
    public Long obtenerTotalPorFiltro(FiltroCodiguera filtro) throws GeneralException {
        try {
            CodigueraDAO<Configuracion> codDAO = new CodigueraDAO<>(em, Configuracion.class);
            return codDAO.obtenerTotalPorFiltro(filtro);
        } catch (Exception ex) {
            throw new TechnicalException(ex);
        }
    }

    /**
     * Devuelve los elementos que satisfacen el criterio
     *
     * @param filtro FiltroCodiguera
     * @return Lista de <SgConfiguracion>
     * @throws GeneralException
     */
    public List<Configuracion> obtenerPorFiltro(FiltroCodiguera filtro) throws GeneralException {
        try {
            CodigueraDAO<Configuracion> codDAO = new CodigueraDAO<>(em, Configuracion.class);
            return codDAO.obtenerPorFiltro(filtro);
        } catch (Exception ex) {
            throw new TechnicalException(ex);
        }
    }

    /**
     * Elimina el objeto con la id indicada
     *
     * @param id Long
     * @throws GeneralException
     */
    public void eliminar(Long id) throws GeneralException {
        if (id != null) {
            try {
                CodigueraDAO<Configuracion> codDAO = new CodigueraDAO<>(em, Configuracion.class);
                codDAO.eliminarPorId(id);
            } catch (Exception ex) {
                throw new TechnicalException(ex);
            }
        }
    }

    /**
     * Realiza la búsqueda de los elementos que satisfacen el criterio. La
     * búsqueda es por códig y descripción
     *
     * @param codigo
     * @param descripcion
     * @return
     * @throws GeneralException
     */
    public List<Configuracion> buscarSimple(String codigo, String descripcion) throws GeneralException {
        try {
            CodigueraDAO<Configuracion> codDAO = new CodigueraDAO<>(em, Configuracion.class);
            return codDAO.busquedaSimple(codigo, descripcion);
        } catch (Exception ex) {
            throw new TechnicalException(ex);
        }
    }
    
    /**
     * Devuelve el objeto del tipo que tiene el código indicado.
     *
     * @param codigo
     * @return
     * @throws GeneralException
     */
    public Configuracion obtenerPorCodigo(String codigo) throws GeneralException {
        if (codigo != null) {
            try {
                CodigueraDAO<Configuracion> codDAO = new CodigueraDAO<>(em, Configuracion.class);
                Collection<Configuracion> col = codDAO.busquedaSimple(codigo, "");
                if (!col.isEmpty()) {
                    return col.iterator().next();
                }
            } catch (Exception ex) {
                throw new TechnicalException(ex);
            }
        }
        return null;
    }
    

}
