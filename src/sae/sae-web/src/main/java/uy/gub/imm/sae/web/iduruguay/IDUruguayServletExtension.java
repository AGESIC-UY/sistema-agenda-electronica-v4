package uy.gub.imm.sae.web.iduruguay;

import io.undertow.servlet.ServletExtension;
import io.undertow.servlet.api.DeploymentInfo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import uy.gub.imm.sae.web.iduruguay.saml.IDUruguaySAMLHttpHandler;
import uy.gub.imm.sae.web.iduruguay.openid.IDUruguayHttpHandler;

/**
 * @author Sofis Solutions
 */
public class IDUruguayServletExtension implements ServletExtension {

     private static final Logger logger = Logger.getLogger(IDUruguayServletExtension.class.getName());

    @Override
    public void handleDeployment(final DeploymentInfo deploymentInfo, final ServletContext servletContext) {
        logger.log(Level.INFO, "Registrando una instancia de IDUruguayHttpHandler para la aplicacion...");
        deploymentInfo.addSecurityWrapper(handler -> new IDUruguayHttpHandler(handler));
        logger.log(Level.INFO, "Registrada una instancia de IDUruguayHttpHandler para la aplicacion.");
        
        logger.log(Level.INFO, "Registrando una instancia de IDUruguaySAMLHttpHandler para la aplicacion...");
        deploymentInfo.addSecurityWrapper(handler -> new IDUruguaySAMLHttpHandler(handler));
        logger.log(Level.INFO, "Registrada una instancia de IDUruguaySAMLHttpHandler para la aplicacion.");
    }

}
