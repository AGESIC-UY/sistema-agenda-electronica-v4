package uy.com.sofis.business.services;

import uy.com.sofis.business.validaciones.AyudaBusinessValidation;
import uy.com.sofis.exceptions.BusinessException;
import uy.com.sofis.exceptions.GeneralException;
import uy.com.sofis.exceptions.TechnicalException;
import uy.com.sofis.entities.Ayuda;
import uy.com.sofis.persistencia.daos.CodigueraDAO;
import java.util.List;
import java.util.logging.Logger;
import javax.cache.annotation.CacheResult;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import uy.com.sofis.constantes.ConstantesCache;
import uy.com.sofis.constantes.ConstantesErrores;
import uy.com.sofis.filtros.FiltroCodiguera;
import uy.com.sofis.persistence.helpers.PersistenceHelper;

/**
 *
 * @author Usuario
 */
@Stateless
@LocalBean
public class AyudaBusinessBean {

    private static final Logger LOGGER = Logger.getLogger(AyudaBusinessBean.class.getName());

    @PersistenceContext
    private EntityManager em;

    @Inject
    private ConsultaHistorico ch;

    /**
     * Guarda un elemento del tipo. Si el elemento no tiene id hace un
     * persistence, en caso contrario hace un merge.
     *
     * @param ayu
     * @return
     * @throws GeneralException Devuelve los códigos de error de la validación
     */
    public Ayuda guardar(Ayuda ayu) throws GeneralException {
        try {
            if (ayu != null) {
                //Validar el elemento a guardar. Si no valida, se lanza una excepcion
                if (AyudaBusinessValidation.validar(ayu)) {
                    CodigueraDAO<Ayuda> codDAO = new CodigueraDAO<>(em, Ayuda.class);
                    boolean guardar = true;
                    if (ayu.getAyuId() != null) {
                        //Si el dato ya fue guardado, se determina que haya cambiado alguno de los valores.
                        //En caso contrario no se guarda
                        Object valorAnt = ch.obtenerEnVersion(ayu.getClass(), ayu.getAyuId(), ayu.getAyuVersion());
                        Ayuda valorAnterior = (Ayuda) valorAnt;
                        guardar = !AyudaBusinessValidation.compararParaGrabar(valorAnterior, ayu);
                    }
                    if (guardar) {
                        return codDAO.guardar(ayu);
                    }
                }
            }

        } catch (BusinessException be) {
            throw be;
        } catch (Exception ex) {
            if (PersistenceHelper.isConstraintViolation(ex)) {
                BusinessException b = new BusinessException();
                b.addError(ConstantesErrores.ERROR_CONSTRAINT_VIOLATION);
                throw b;
            }
            throw new TechnicalException(ex);
        }

        return ayu;

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
                CodigueraDAO<Ayuda> codDAO = new CodigueraDAO<>(em, Ayuda.class);
                codDAO.eliminarPorId(id);
            } catch (Exception ex) {
                if (PersistenceHelper.isConstraintViolation(ex)) {
                    BusinessException b = new BusinessException();
                    b.addError(ConstantesErrores.ERROR_CONSTRAINT_VIOLATION);
                    throw b;
                }
                throw new TechnicalException(ex);
            }

        }
    }

    /**
     * Devuelve el objeto del tipo que tiene el id indicado
     *
     * @param id
     * @return
     * @throws GeneralException
     */
    public Ayuda obtenerPorId(Long id) throws GeneralException {
        if (id != null) {
            try {
                CodigueraDAO<Ayuda> codDAO = new CodigueraDAO<>(em, Ayuda.class);
                return codDAO.obtenerPorId(id);
            } catch (Exception ex) {
                throw new TechnicalException(ex);
            }
        }
        return null;
    }
    
    public List<Ayuda> obtenerPorFiltro(FiltroCodiguera filtro) throws GeneralException {
        try {
            CodigueraDAO<Ayuda> codDAO = new CodigueraDAO<>(em, Ayuda.class);
            return codDAO.obtenerPorFiltro(filtro);
        } catch (Exception ex) {
            throw new TechnicalException(ex);
        }
    }

    @CacheResult(cacheName = ConstantesCache.AYUDA_CACHE)
    public List<Ayuda> obtenerPorFiltroCache(FiltroCodiguera filtro) throws GeneralException {
        try {
            CodigueraDAO<Ayuda> codDAO = new CodigueraDAO<>(em, Ayuda.class);
            return codDAO.obtenerPorFiltro(filtro);
        } catch (Exception ex) {
            throw new TechnicalException(ex);
        }
    }
    
    public Long obtenerTotalPorFiltro(FiltroCodiguera filtro) throws GeneralException {
        try {
            CodigueraDAO<Ayuda> codDAO = new CodigueraDAO<>(em, Ayuda.class);
            return codDAO.obtenerTotalPorFiltro(filtro);
        } catch (Exception ex) {
            throw new TechnicalException(ex);
        }
    }

}
