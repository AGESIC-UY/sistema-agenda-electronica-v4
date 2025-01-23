package uy.gub.imm.sae.login;

import java.io.IOException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.sql.DataSource;

import org.jboss.security.SimpleGroup;
import org.jboss.security.auth.spi.AbstractServerLoginModule;

/**
 * Esta clase es utilizada para permitir la autenticación de usuarios en la aplicación que se autenticaron en un identity provider externo (CAS, CDA, etc).
 *
 * El código de usuario debe estar compuesto por un nombre y el identificador de la empresa separados por una barra. Por ejemplo "usuario12345/3". Si no se
 * incluye el identificador de la empresa, y el usuario no es superadministrador, no se cargará ningún rol. Si es superadministrador se cargan todos los roles.
 *
 * La contraseña debe ser un UUID aleatorio de uso único que se coloca en el directorio JNDI (este módulo lo borra después de usarlo). Ejemplo: InitialContext
 * ctx = new InitialContext(); String password = UUID.randomUUID().toString(); try { ctx.bind("{LOGIN}{id-de-sesion}", password); } catch (NamingException nabEx) {
 * ctx.rebind("{LOGIN}{id-de-sesion}", password); }
 *
 * @author spio
 *
 */
public class SAEAutoLoginModule extends AbstractServerLoginModule {

    private static final Logger LOGGER = Logger.getLogger(SAEAutoLoginModule.class.getName());

    private String sessionId = null;
    private String codigo = null;
    private Integer empresa = null;
    private Principal identity = null;

    public SAEAutoLoginModule() {
    }
    
    @Override
    public boolean login() throws LoginException {
        
        loginOk = false;
        //Obtener las credenciales
        if (callbackHandler == null) {
            throw new LoginException("No hay registrado un callback handler");
        }
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("name:");
        callbacks[1] = new PasswordCallback("password:", false);
        try {
            callbackHandler.handle(callbacks);
        } catch (IOException | UnsupportedCallbackException ex) {
            throw new LoginException("Error en la invocación al callback handler");
        }
        NameCallback nameCallback = (NameCallback) callbacks[0];
        PasswordCallback passwordCallback = (PasswordCallback) callbacks[1];
        String username = nameCallback.getName();
        String password = new String(passwordCallback.getPassword());
        //Separar el codigo de usuario en sus tres partes: id de sesión, código de usuario y empresa (opcional)
        String partes[] = username.split("/", 3);
        if(partes.length<2) {
            sessionId = null;
            codigo = null;
            empresa = null;
            password = null;
        }else if (partes.length == 2) {
            sessionId = partes[0];
            codigo = partes[1];
            empresa = null;
        } else {
            sessionId = partes[0];
            codigo = partes[1];
            empresa = Integer.valueOf(partes[2]);
        }
        //Si no se especificó usuario o contraseña no se puede continuar
        if (sessionId == null || sessionId.trim().isEmpty()  || codigo == null || codigo.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            loginOk = false;
        } else {
            InitialContext ctx = null;
            try {
                //Obtener la contraseña de uso único
                ctx = new InitialContext();
                String password1 = (String) ctx.lookup("{LOGIN}{"+sessionId+"}");
                //Quitar la contraseña de uso único del directorio JNDI
                ctx.unbind("{LOGIN}{"+sessionId+"}");
                //Comparar la contraseñas
                if (!password.equals(password1)) {
                    throw new LoginException("La contraseña no coincide con lo esperado");
                }
                //Determinar si es superadmin, el esquema según la empresa y crear el identity
                //Esta información la usan los EJBs
                Connection conn = null;
                PreparedStatement st = null;
                ResultSet rs = null;
                try {
                    String dsJndiName = (String) options.get("dsJndiName");
                    Context initContext = new InitialContext();
                    DataSource ds = (DataSource) initContext.lookup(dsJndiName);
                    conn = ds.getConnection();
                    
                    String tenant = "default";
                    if (empresa != null) {
                        st = conn.prepareStatement("select datasource from global.ae_empresas where id=?");
                        st.setInt(1, empresa);
                        st.executeQuery();
                        rs = st.getResultSet();
                        if (rs.next()) {
                            tenant = rs.getString(1);
                        }
                    }
                    identity = new SAEPrincipal(codigo, tenant);
                    loginOk = true;
                } catch (NamingException | SQLException ex) {
                    LOGGER.log(Level.SEVERE, "Error al verificar datos del usuario", ex);
                    loginOk = false;
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                    } catch (Exception ex) {
                        //Nada para hacer
                    }
                    try {
                        if (st != null) {
                            st.close();
                        }
                    } catch (Exception ex) {
                        //Nada para hacer
                    }
                    try {
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (Exception ex) {
                        //Nada para hacer
                    }
                }
            } catch (NamingException ex) {
                //No están los datos en el contexto jndi, no es un login automático
                loginOk = false;
            } finally {
                if (ctx != null) {
                    try {
                        ctx.close();
                    } catch (NamingException ex) {
                        //Nada para hacer
                    }
                }
            }
        }
        return loginOk;
    }

    @Override
    protected Principal getIdentity() {
        return identity;
    }

    @Override
    protected SimpleGroup[] getRoleSets() throws LoginException {
        //Es necesario crar un grupo llamado "Roles" donde se colocan todos los roles del usuario
        //Se pone un solo rol porque los verdaderos roles del usuario los tiene que cargar la aplicación
        SimpleGroup rolesGroup = new SimpleGroup("Roles");
        rolesGroup.addMember(new SimpleGroup("autenticados"));
        SimpleGroup[] roles = new SimpleGroup[1];
        roles[0] = rolesGroup;
        return roles;
    }
}
