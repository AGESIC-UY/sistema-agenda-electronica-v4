/*
 *  SIGES
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.persistencia.daos;

import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import com.sofis.security.DataSecurityException;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.LockModeType;
import org.apache.commons.lang3.StringUtils;
import uy.com.sofis.filtros.FiltroCodiguera;
import uy.com.sofis.persistence.annotations.AtributoCodigo;
import uy.com.sofis.persistence.annotations.AtributoDescripcion;
import uy.com.sofis.persistence.annotations.AtributoHabilitado;
import uy.com.sofis.persistence.annotations.AtributoNombre;
import uy.com.sofis.utils.ReflectionUtils;
import uy.com.sofis.utils.SofisStringUtils;


public class CodigueraDAO<T> extends HibernateJpaDAOImp<T, Long> implements Serializable {

    private EntityManager em;
    private final Class<T> clazz;

    public CodigueraDAO(EntityManager em, Class clase) throws Exception {
        super(em);
        this.em = em;
        this.clazz = clase;
    }

    public void lock(int id) {
        Object lock = em.find(clazz, id);
        em.lock(lock, LockModeType.PESSIMISTIC_WRITE);
    }

    private List<CriteriaTO> generarCriteria(FiltroCodiguera filtro) {

        String campoCodigo = ReflectionUtils.obtenerNombreCampoAnotado(clazz, AtributoCodigo.class);
        String campoNombre = ReflectionUtils.obtenerNombreCampoAnotado(clazz, AtributoNombre.class);
        String campoDescripcion = ReflectionUtils.obtenerNombreCampoAnotado(clazz, AtributoDescripcion.class);
        String campoHabilitado = ReflectionUtils.obtenerNombreCampoAnotado(clazz, AtributoHabilitado.class);

        List<CriteriaTO> criterios = new ArrayList();

        if (!StringUtils.isBlank(filtro.getCodigo())) {
            MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.CONTAINS, campoCodigo, SofisStringUtils.normalizarParaBusqueda(filtro.getCodigo()));
            criterios.add(criterio);
        }

        if (!StringUtils.isBlank(filtro.getCodigoExacto())) {
            MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, campoCodigo, SofisStringUtils.normalizarParaBusqueda(filtro.getCodigoExacto()));
            criterios.add(criterio);
        }

        if (!StringUtils.isBlank(filtro.getNombre())) {
            MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.CONTAINS, campoNombre, SofisStringUtils.normalizarParaBusqueda(filtro.getNombre()));
            criterios.add(criterio);
        }

        if (!StringUtils.isBlank(filtro.getDescripcion())) {
            MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.CONTAINS, campoDescripcion, SofisStringUtils.normalizarParaBusqueda(filtro.getDescripcion()));
            criterios.add(criterio);
        }

        if (filtro.getHabilitado() != null) {
            MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, campoHabilitado, filtro.getHabilitado());
            criterios.add(criterio);
        }
        

        return criterios;
    }
    
     public List<T> busquedaSimple(String codigo, String nombre) throws DAOGeneralException {
        FiltroCodiguera filtro = new FiltroCodiguera();
        filtro.setCodigoExacto(codigo);
        filtro.setNombre(nombre);
        return this.obtenerPorFiltro(filtro);
    }

    public List<T> obtenerPorFiltro(FiltroCodiguera filtro) throws DAOGeneralException {
        try {

            List<CriteriaTO> criterios = this.generarCriteria(filtro);

            String[] orderBy = filtro.getOrderBy();
            boolean[] asc = filtro.getAscending();

            CriteriaTO criterio = null;
            if (criterios.size() > 1) {
                criterio = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[criterios.size()]));
            } else if (criterios.size() == 1) {
                criterio = criterios.get(0);
            }
            return this.findEntityByCriteria(clazz, criterio, orderBy, asc, filtro.getFirst(), filtro.getMaxResults(), false, null, filtro.getIncluirCampos());
        } catch (DAOGeneralException e) {
            throw e;
        } catch (Exception ex) {
            throw new DAOGeneralException(ex);
        }
    }

    public Long obtenerTotalPorFiltro(FiltroCodiguera filtro) throws DAOGeneralException {
        try {
            List<CriteriaTO> criterios = generarCriteria(filtro);
            CriteriaTO criterio = null;
            if (criterios.size() > 1) {
                criterio = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[criterios.size()]));
            } else if (criterios.size() == 1) {
                criterio = criterios.get(0);
            }
            return super.countByCriteria(clazz, criterio, false, null);
        } catch (Exception ex) {
            throw new DAOGeneralException(ex);
        }
    }

    public T guardar(T elemento) throws DAOGeneralException, DataSecurityException {
        try {
            Field campoId = ReflectionUtils.obtenerCampoAnotado(clazz, Id.class);
            Object value = campoId.get(elemento);
            if (value == null) {
                elemento = super.create(elemento, null);
            } else {
                elemento = super.update(elemento, null);
            }
        } catch (DataSecurityException se) {
            throw se;
        } catch (DAOGeneralException e) {
            throw e;
        } catch (Exception ex) {
            throw new DAOGeneralException(ex);
        }
        return elemento;
    }

    public void eliminar(T elemento) throws DAOGeneralException, DataSecurityException {
        try {
            super.delete(elemento, null);
        } catch (DataSecurityException se) {
            throw se;
        } catch (Exception ex) {
            throw new DAOGeneralException(ex);
        }
    }

    public Boolean objetoExistePorId(Long id) throws DAOGeneralException {
        try {
            String campoId = ReflectionUtils.obtenerCampoAnotado(clazz, Id.class).getName();
            MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, campoId, id);
            Long count = this.countByCriteria(clazz, criterio, false,  null);
            if (count > 0L) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        } catch (Exception ex) {
            throw new DAOGeneralException(ex);
        }
    }

    public T obtenerPorId(Long id) throws DAOGeneralException {
        try {
            return super.findById(clazz, id, null);
        } catch (Exception ex) {
            throw new DAOGeneralException(ex);
        }
    }

    public void eliminarPorId(Long id) throws DAOGeneralException, DataSecurityException {
        try {
            this.eliminar(this.obtenerPorId(id));
        } catch (DataSecurityException se) {
            throw se;
        } catch (Exception ex) {
            throw new DAOGeneralException(ex);
        }
    }

}
