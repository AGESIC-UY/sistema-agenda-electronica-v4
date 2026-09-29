/*
 *  SAE-USERS-ADMIN
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.business.services;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import uy.com.sofis.entities.Organismo;
import java.util.List;
import uy.com.sofis.business.validaciones.OrganismoBusinessValidation;
import uy.com.sofis.persistencia.daos.CodigueraDAO;
import uy.com.sofis.exceptions.BusinessException;
import uy.com.sofis.exceptions.TechnicalException;
import javax.inject.Inject;
import uy.com.sofis.exceptions.GeneralException;
import uy.com.sofis.constantes.ConstantesErrores;
import java.util.logging.Logger;
import uy.com.sofis.filtros.FiltroCodiguera;
import uy.com.sofis.persistence.helpers.PersistenceHelper;


/**
 *
 * @author Sofis Solutions
 */
@Stateless
@LocalBean
public class OrganismoBusinessBean  {

    private static final Logger LOGGER = Logger.getLogger(OrganismoBusinessBean.class.getName());

    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private ConsultaHistorico ch;
    
    
	 /**
     * Devuelve el objeto del tipo que tiene el id indicado
     * @param id
     * @return
     * @throws GeneralException 
     */
    public Organismo obtenerPorId(Long id) throws GeneralException {
        if (id != null) {
            try {
                CodigueraDAO<Organismo> codDAO = new CodigueraDAO<>(em, Organismo.class);
                return codDAO.obtenerPorId(id);
            } catch (Exception ex) {
                throw new TechnicalException(ex);
            }
        }
        return null;
    }
	
	
	 
	 /**
     * Guarda el objeto indicado
     *
     * @param usu Organismo
     * @return Organismo
     * @throws GeneralException
     */
    public Organismo guardar(Organismo usu) throws GeneralException {
        try {
            if (usu != null) {
                if (OrganismoBusinessValidation.validar(usu)) {
                    CodigueraDAO<Organismo> codDAO = new CodigueraDAO<>(em, Organismo.class);
                    return codDAO.guardar(usu);
                }
            }
        } catch (BusinessException be) {
            throw be;
        } catch (Exception ex) {
            throw new TechnicalException(ex);
        }

        return usu;
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
            CodigueraDAO<Organismo> codDAO = new CodigueraDAO<>(em, Organismo.class);
            return codDAO.obtenerTotalPorFiltro(filtro);
        } catch (Exception ex) {
            throw new TechnicalException(ex);
        }
    }

    /**
     * Devuelve los elementos que satisfacen el criterio
     *
     * @param filtro FiltroCodiguera
     * @return Lista de <Organismo>
     * @throws GeneralException
     */
    public List<Organismo> obtenerPorFiltro(FiltroCodiguera filtro) throws GeneralException {
        try {
            CodigueraDAO<Organismo> codDAO = new CodigueraDAO<>(em, Organismo.class);
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
                CodigueraDAO<Organismo> codDAO = new CodigueraDAO<>(em, Organismo.class);
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
    
    
    
}
