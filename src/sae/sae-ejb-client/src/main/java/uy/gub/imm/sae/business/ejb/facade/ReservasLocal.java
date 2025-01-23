package uy.gub.imm.sae.business.ejb.facade;

import javax.ejb.Local;
import uy.gub.imm.sae.business.dto.ReservaDTO;
import uy.gub.imm.sae.exception.AccesoMultipleException;

@Local
public interface ReservasLocal {

    public void modificarEstadoReserva(ReservaDTO reserva) throws AccesoMultipleException;

}
