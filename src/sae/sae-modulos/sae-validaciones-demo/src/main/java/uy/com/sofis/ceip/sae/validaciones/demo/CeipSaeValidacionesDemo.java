/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.sofis.ceip.sae.validaciones.demo;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import uy.gub.imm.sae.business.dto.ReservaDTO;
import uy.gub.imm.sae.validaciones.business.ejb.ResultadoValidacion;
import uy.gub.imm.sae.validaciones.business.ejb.ValidadorReservaRemote;
import uy.gub.imm.sae.validaciones.business.ejb.exception.InvalidParametersException;
import uy.gub.imm.sae.validaciones.business.ejb.exception.UnexpectedValidationException;


/**
 *
 * @author martin
 */
@Stateless
@LocalBean
public class CeipSaeValidacionesDemo implements ValidadorReservaRemote {

    private static final Logger LOGGER = Logger.getLogger(CeipSaeValidacionesDemo.class.getName());
    
    @PostConstruct
    public void init() {
        LOGGER.log(Level.INFO, "Inicializando una instancia de la validación personalizada CeipSaeValidacionesDemo.");
    }

    @Override
    public ResultadoValidacion validarDatosReserva(String nombreValidacion, Map<String, Object> params)  throws UnexpectedValidationException, InvalidParametersException {
        LOGGER.log(Level.INFO, "Ejecutando una validación personalizada CeipSaeAccionesDemo");
        LOGGER.log(Level.INFO, "\tValidación: {0}", nombreValidacion);
        LOGGER.log(Level.INFO, "\tParámetros: ");
        for(String clave : params.keySet()) {
            LOGGER.log(Level.INFO, "\t\t: {0}= {1}", new Object[]{clave, params.get(clave)});
            if("RESERVA".equals(clave)) {
                ReservaDTO reserva = (ReservaDTO)params.get(clave);
                LOGGER.log(Level.INFO, "\t\t\t: {0}= {1}", new Object[]{"ID", reserva.getId()});
                LOGGER.log(Level.INFO, "\t\t\t: {0}= {1}", new Object[]{"Fecha", reserva.getFecha()});
                LOGGER.log(Level.INFO, "\t\t\t: {0}= {1}", new Object[]{"Hora", reserva.getHoraInicio()});
                LOGGER.log(Level.INFO, "\t\t\t: {0}= {1}", new Object[]{"Documento", reserva.getNumeroDocumento()});
                LOGGER.log(Level.INFO, "\t\t\t: {0}= {1}", new Object[]{"Estado", reserva.getEstado()});
                LOGGER.log(Level.INFO, "\t\t\t: {0}= {1}", new Object[]{"Origen", reserva.getOrigen()});
                LOGGER.log(Level.INFO, "\t\t\t: {0}= {1}", new Object[]{"Presencial", reserva.getPresencial()});                
            }
        }
        ResultadoValidacion res = new ResultadoValidacion();
        //Añadir errores o warnings si fuese necesario
        //Se añade un error si se incluye el parámetro error y el valor es no vacío
        if(params.get("error")!=null && !params.get("error").toString().trim().isEmpty()) {
            res.addError("error", params.get("error").toString());
        }
        return res;
    }

}
