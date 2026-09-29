/*
 *  Nombre del cliente
 *  Sistema de Gestión
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.web.utils;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import uy.com.sofis.business.services.ConfiguracionBusinessBean;

/**
 *
 * @author Usuario
 */
public class EjbUtils implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(EjbUtils.class.getName());

    private static Object lookup(String beanName, String viewClassFullName) throws NamingException {
        Context context = null;
        try {
            final Hashtable jndiProperties = new Hashtable();
            jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            context = new InitialContext(jndiProperties);
            context.addToEnvironment("sistema", "SAE-USERS-ADMIN");
            // The app name is the application name of the deployed EJBs. This is typically the ear name
            // without the .ear suffix. However, the application name could be overridden in the application.xml of the
            // EJB deployment on the server.
            // Since we haven't deployed the application as a .ear, the app name for us will be an empty string
            final String appName = "global";
            // This is the module name of the deployed EJBs on the server. This is typically the jar name of the
            // EJB deployment, without the .jar suffix, but can be overridden via the ejb-jar.xml
            // In this example, we have deployed the EJBs in a jboss-as-ejb-remote-app.jar, so the module name is
            // jboss-as-ejb-remote-app
                       
            
            final String moduleName = "sae-users-ear-0.2.1/sae-users-ejb-0.2.1";
            // The EJB name which by default is the simple class name of the bean implementation class
            // let's do the lookup
            String nombre = "java:" + appName + "/" + moduleName + "/" + beanName + "!" + viewClassFullName;
            // AS7 allows each deployment to have an (optional) distinct name. We haven't specified a distinct name for
            // our EJB deployment, so this is an empty string
            LOGGER.log(Level.FINE, "lookup: " + nombre);
            return (Object) context.lookup(nombre);
        } finally {
            if (context != null){
                context.close();
            }
        }
    }



    public static ConfiguracionBusinessBean configuracionLookup() throws Exception {
        try {
            return (ConfiguracionBusinessBean) lookup("ConfiguracionBusinessBean", ConfiguracionBusinessBean.class.getName());
        } catch (NamingException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
    }
}
