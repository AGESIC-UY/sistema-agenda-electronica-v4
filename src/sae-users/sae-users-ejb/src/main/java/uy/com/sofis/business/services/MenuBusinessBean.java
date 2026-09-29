/*
 * Sofis Solutions
 */
package uy.com.sofis.business.services;

import java.util.List;
import javax.ejb.LocalBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import uy.com.sofis.entities.Menu;
import uy.com.sofis.exceptions.GeneralException;
import uy.com.sofis.exceptions.TechnicalException;
import uy.com.sofis.filtros.FiltroCodiguera;
import uy.com.sofis.persistencia.daos.CodigueraDAO;


@Stateless
@LocalBean
public class MenuBusinessBean  {
    
    
    @PersistenceContext
    private EntityManager em;
    
    
    public List<Menu> obtenerPorFiltro(FiltroCodiguera filtro) throws GeneralException {
        try {
            CodigueraDAO<Menu> codDAO = new CodigueraDAO<>(em, Menu.class);
            List<Menu> list = codDAO.obtenerPorFiltro(filtro);
            return list;
        } catch (Exception ex) {
            throw new TechnicalException(ex);
        }
    }

}