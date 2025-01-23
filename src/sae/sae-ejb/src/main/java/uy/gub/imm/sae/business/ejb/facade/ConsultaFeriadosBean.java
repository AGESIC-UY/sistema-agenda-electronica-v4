package uy.gub.imm.sae.business.ejb.facade;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ConsultaFeriadosBean implements ConsultaFeriadosLocal {

    @PersistenceContext(unitName = "SAE-EJB")
    private EntityManager entityManager;

    public ConsultaFeriadosBean() {

    }

    @SuppressWarnings(value = "unchecked")
    public Collection<Date> obtenerDiasFeriados() {

        return (List<Date>) entityManager.createQuery("SELECT distinct f.dia FROM Feriados f").getResultList();
    }
}
