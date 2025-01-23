package uy.gub.imm.sae.business.ejb.facade;

import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;


import uy.gub.imm.sae.entity.global.Empresa;

@Local
public interface DepuracionReservasLocal {
	
	public void eliminarReservas(Empresa empresa,Calendar fechaLimiteIndividual,Calendar fechaLimiteMultiple,Integer idRecurso, List<Integer> idsReservaIndividuales, List<Integer>idsReservaMultiples);

}