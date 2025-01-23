/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.sofis.ceip.sae.acciones.demo;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import uy.gub.sae.acciones.business.ejb.EjecutorAccionRemote;
import uy.gub.sae.acciones.business.ejb.ResultadoAccion;
import uy.gub.sae.acciones.business.ejb.exception.InvalidParametersException;
import uy.gub.sae.acciones.business.ejb.exception.UnexpectedAccionException;

/**
 *
 * @author martin
 */
@Stateless
@LocalBean
public class CeipSaeAccionesDemo implements EjecutorAccionRemote {

    private static final Logger LOGGER = Logger.getLogger(CeipSaeAccionesDemo.class.getName());
    
    @PostConstruct
    public void init() {
        LOGGER.log(Level.INFO, "Inicializando una instancia de la acción personalizada CeipSaeAccionesDemo.");
    }

    @Override
    public ResultadoAccion ejecutar(String nombreAccion, Map<String, Object> params) throws UnexpectedAccionException, InvalidParametersException {
        LOGGER.log(Level.INFO, "Ejecutando una acción personalizada CeipSaeAccionesDemo");
        LOGGER.log(Level.INFO, "\tAcción: {0}", nombreAccion);
        LOGGER.log(Level.INFO, "\tParámetros: ");
        for(String clave : params.keySet()) {
            LOGGER.log(Level.INFO, "\t\t: {0}= {1}", new Object[]{clave, params.get(clave)});
        }
        ResultadoAccion res = new ResultadoAccion();
        //Añadir errores o warnings si fuese necesario
        return res;
    }

}
