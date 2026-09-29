/*
 *
 *
 */
package uy.com.sofis.persistencia.daos;

import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import uy.com.sofis.filtros.FiltroUsuario;
import com.sofis.utils.CriteriaTOUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.apache.commons.lang3.StringUtils;
import uy.com.sofis.entities.Usuario;

/**
 *
 * @author Usuario
 */
public class UsuarioDAO extends HibernateJpaDAOImp<Usuario, Integer> implements Serializable {

    private EntityManager em;

    public UsuarioDAO(EntityManager em) {
        super(em);
        this.em = em;
    }


    private List<CriteriaTO> generarCriteria(FiltroUsuario filtro) {

            List<CriteriaTO> criterios = new ArrayList<>();

            if (!StringUtils.isBlank(filtro.getCodigo())) {
                MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.EQUALS, "codigo", filtro.getCodigo().trim());
                criterios.add(criterio);
            }
            
            if (!StringUtils.isBlank(filtro.getNombreYApellido())) {
                MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.CONTAINS_ILIKE, "nombre", filtro.getNombreYApellido().trim());
                criterios.add(criterio);
            }
            

            if (!StringUtils.isBlank(filtro.getCorreo())) {
                MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.CONTAINS_ILIKE, "correoe", filtro.getCorreo().trim());
                criterios.add(criterio);
            }
            
            if (filtro.getOrganismoId()!=null) {
                MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.EQUALS, "empresas.organismoId", filtro.getOrganismoId());
                criterios.add(criterio);
            }

            return criterios;
    }

     public List<Usuario> obtenerPorFiltro(FiltroUsuario filtro) throws DAOGeneralException {
        try {
            List<CriteriaTO> criterios = generarCriteria(filtro);

            String[] orderBy = filtro.getOrderBy();
            boolean[] asc = filtro.getAscending();

            CriteriaTO criterio = null;
            if (criterios.size() > 1) {
                criterio = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[criterios.size()]));
            } else if (criterios.size() == 1) {
                criterio = criterios.get(0);
            }
            return this.findEntityByCriteria(Usuario.class, criterio, orderBy, asc, filtro.getFirst(), filtro.getMaxResults(), filtro.getSeNecesitaDistinct(), null, filtro.getIncluirCampos());
        } catch (DAOGeneralException e) {
            throw e;
        } catch (Exception ex) {
            throw new DAOGeneralException(ex);
        }
    }


    public Long obtenerTotalPorFiltro(FiltroUsuario filtro) throws DAOGeneralException {
        try {
            List<CriteriaTO> criterios = generarCriteria(filtro);
            CriteriaTO criterio = null;
            if (criterios.size() > 1) {
                criterio = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[criterios.size()]));
            } else if (criterios.size() == 1) {
                criterio = criterios.get(0);
            }
            return this.countByCriteria(Usuario.class, criterio, filtro.getSeNecesitaDistinct(), null);
        } catch (Exception ex) {
            throw new DAOGeneralException(ex);
        }
    }
    
    
    

    
}
