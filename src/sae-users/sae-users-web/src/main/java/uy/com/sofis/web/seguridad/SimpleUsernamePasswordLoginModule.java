/*
 * 
 * 
 */
package uy.com.sofis.web.seguridad;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import org.apache.commons.lang3.StringUtils;
import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.auth.spi.UsernamePasswordLoginModule;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;
import uy.com.sofis.web.utils.Utilidades;

/**
 *
 * @author Usuario
 */
public class SimpleUsernamePasswordLoginModule extends UsernamePasswordLoginModule {

    private static final Logger LOGGER = Logger.getLogger(SimpleUsernamePasswordLoginModule.class.getName());

    private Set<String> operaciones = new HashSet<>();

    String WEB_REQUEST_KEY = "javax.servlet.http.HttpServletRequest";
    private String codigo = null;

    PasswordEncryptor encryptor = new BasicPasswordEncryptor();
    private String salt = "SOFIS_ENC";

    @SuppressWarnings("rawtypes")
    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map sharedState, Map options) {
        LOGGER.log(Level.FINE, "Initialize SimpleUsernamePasswordLoginModule");
        // We could read options passed via <module-option> in standalone.xml if there were any here
        // For an example see http://docs.redhat.com/docs/en-US/JBoss_Enterprise_Application_Platform/5/html/Security_Guide/sect-Custom_LoginModule_Example.html
        // We could also f.ex. lookup a data source in JNDI
        // For an example see http://www.docjar.com/html/api/org/jboss/security/auth/spi/DatabaseServerLoginModule.java.html
        super.initialize(subject, callbackHandler, sharedState, options);

    }

    /**
     * (required) The UsernamePasswordLoginModule modules compares the result of
     * this method with the actual password.
     *
     * @return
     * @throws javax.security.auth.login.LoginException
     */
    @Override
    protected String getUsersPassword() throws LoginException {
        LOGGER.log(Level.FINE, "getUsersPassword");
        // Lets pretend we got the password from somewhere and that it's, by a chance, same as the username
        String password = super.getUsername();
        return password;
    }

    /**
     * (optional) Override if you want to change how the password are compared
     * or if you need to perform some conversion on them.
     *
     * @param inputPassword
     * @param expectedPassword
     * @return
     */
    @Override
    protected boolean validatePassword(String inputPassword, String inputUser){
        LOGGER.log(Level.FINE, "validatePassword");
        try {
            LOGGER.log(Level.INFO, "SimpleUsernamePasswordLoginModule.validatePassword(): {0}", new Object[]{inputUser});
            
            String usu = User.getValue("user");
            String password = StringUtils.EMPTY;
            if (usu == null) {
                LOGGER.log(Level.SEVERE, "Usuario ''{0}'' no se encuentra.", inputUser);
            } else {
                LOGGER.log(Level.INFO, "Validando usuario ''{0}''.", inputUser);
                if(StringUtils.equals(usu, inputUser)){
                    password = User.getValue("password");
                }
            }
            

            if (usu != null && StringUtils.isNoneBlank(password) && StringUtils.isNoneBlank(inputPassword)) {
                Boolean pass = password.equals(Utilidades.encriptarPassword(inputPassword));
                if (pass) {
                    LOGGER.log(Level.INFO, "Autenticación de usuario ''{0}'' exitosa.", inputUser);
                    operaciones.clear();
                    return true;
                } else {
                    LOGGER.log(Level.INFO, "Autenticación de usuario ''{0}'' fallida.", inputUser);
                }
            }

        } catch (Exception ex) {
            LOGGER.log(Level.INFO, "Error inesperado al autenticar usuario ''" + inputUser + "''.", ex);
        }
        return false;

    }

    /**
     * (required) The groups of the user, there must be at least one group
     * called "Roles" (though it likely can be empty) containing the roles the
     * user has.
     *
     * @return
     * @throws javax.security.auth.login.LoginException
     */
    @Override
    protected SimpleGroup[] getRoleSets() throws LoginException {
        SimpleGroup group = new SimpleGroup("Roles");
        try {
            group.addMember(new SimplePrincipal("usuario_autenticado"));
            for (String s : operaciones) {
                group.addMember(new SimplePrincipal(s));
            }
        } catch (Exception e) {
            throw new LoginException("Failed to create group member for " + group);
        }
        return new SimpleGroup[]{group};
    }

}
