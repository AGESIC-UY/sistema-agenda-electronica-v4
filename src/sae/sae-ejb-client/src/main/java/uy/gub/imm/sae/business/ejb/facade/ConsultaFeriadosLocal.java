package uy.gub.imm.sae.business.ejb.facade;

import java.util.Collection;
import java.util.Date;
import javax.ejb.Local;

@Local
public interface ConsultaFeriadosLocal {

    public Collection<Date> obtenerDiasFeriados();
}
