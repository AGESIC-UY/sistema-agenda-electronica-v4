/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.gub.imm.sae.web.iduruguay.openid;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.servlet.handlers.ServletRequestContext;
import io.undertow.util.Headers;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.net.ssl.HttpsURLConnection;
import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;

import org.json.JSONObject;

/**
 *
 * @author spio
 */
@SuppressWarnings({"rawtypes", "unused", "unchecked"})
public class IDUruguayHttpHandler implements HttpHandler {

     private static final Logger logger = Logger.getLogger(IDUruguayHttpHandler.class.getName());

    private static final ResourceBundle bundle = ResourceBundle.getBundle("uy.com.sofis.web.iduruguay.tiposdocumentos");
    private static final String[] NID_HABILITADOS_DEFAULT = {":nid:2", ":nid:3"};

    // Estos atributos de configuracion se deben configurar en el archivo standalone.xml, dentro del subsistema urn:jboss:domain:naming:2.0
    // <subsystem xmlns="urn:jboss:domain:naming:2.0">
    //   <remote-naming/>
    //   <bindings>
//            <simple name="java:global/iduruguayhttphandler/ssoState" value="AGESIC_SAE" type="java.lang.String"/>
//            <simple name="java:global/iduruguayhttphandler/returnPath" value="/iduruguay" type="java.lang.String"/>
//            <simple name="java:global/iduruguayhttphandler/ssoBaseUrl" value="https://auth-testing.iduruguay.gub.uy/oidc/v1/" type="java.lang.String"/>
//            <simple name="java:global/iduruguayhttphandler/ssoClientId" value="953314" type="java.lang.String"/>
//            <simple name="java:global/iduruguayhttphandler/ssoSecret" value="46927b26f162876369966963118e98f69de3b8c57d7459ef62deb880" type="java.lang.String"/>
//            <simple name="java:global/iduruguayhttphandler/ssoReturnUrl" value="https://sae.desarrollo.sofis:8443/sae-web-admin/iduruguay" type="java.lang.String"/>
//            <simple name="java:global/iduruguayhttphandler/ssoReturnUrlSaePublico" value="https://sae.desarrollo.sofis:8443/sae-web/iduruguay" type="java.lang.String"/>
//            <simple name="java:global/iduruguayhttphandler/ssoHomePage" value="https://sae.desarrollo.sofis:8443/sae-web-admin/administracion/inicio.xhtml" type="java.lang.String"/>
//            <simple name="java:global/iduruguayhttphandler/ssoLogoutUrl" value="https://auth-testing.iduruguay.gub.uy/auth/logout" type="java.lang.String"/>
//            <simple name="java:global/iduruguayhttphandler/ssoRedirectUrl" value="https://sae.desarrollo.sofis:8443/sae-web-admin/" type="java.lang.String"/>
//            <simple name="java:global/iduruguayhttphandler/ssoRedirectUrlSaePublico" value="https://sae.desarrollo.sofis:8443/sae-web/" type="java.lang.String"/>
//            <simple name="java:global/iduruguayhttphandler/dsJndiName" value="java:/postgres-sae-ds" type="java.lang.String"/>
    //   </bindings>
    // </subsystem>
    // Además se requiere también configurar un módulo de login utilizando el IDURUGUAYLoginModule y un security domain que lo use
    //  1 - Configurar el módulo en Wildfly (rear la carpeta <wildfly>/system/layers/base/iduruguay y copiar los archivos JAR y XML)
    //  2 - Crear el security-domain llamado "iduruguay":
    //      <security-domain name="iduruguay" cache-type="default">
    //          <authentication>
    //              <login-module code="uy.com.sofis.iduruguay.IDURUGUAYLoginModule" flag="sufficient" module="iduruguay"/>
    //          </authentication>
    //      </security-domain>
    private String typeHandler;
    private String returnPath;
    private String ssoBaseUrl;
    private String ssoClientId;
    private String ssoSecret;
    private String ssoReturnUrl;
    private String ssoHomePage;
    private String ssoState;
    private String dsJndiName;
    private String ssoLogoutUrl;
    private String[] nidsHabilitados;
    
    private final HttpHandler next;

    public IDUruguayHttpHandler(HttpHandler next) {

        Context initContext = null;
        try {
            initContext = new InitialContext();
            typeHandler = (String)initContext.lookup("java:global/iduruguay/typeHandlerAdmin");
            
            if(typeHandler.equals("OPENID")){
                
                logger.info("==============================================");
                logger.info("=== SOFIS-OPENIDCONNECT CLIENT-VALVE v.1.0 ===");
                logger.info("==============================================");
                
                returnPath = (String)initContext.lookup("java:global/iduruguayhttphandler/returnPath");
                ssoLogoutUrl = (String)initContext.lookup("java:global/iduruguayhttphandler/ssoLogoutUrl");
                ssoBaseUrl = (String)initContext.lookup("java:global/iduruguayhttphandler/ssoBaseUrl");
                ssoClientId = (String)initContext.lookup("java:global/iduruguayhttphandler/ssoClientId");
                ssoSecret = (String)initContext.lookup("java:global/iduruguayhttphandler/ssoSecret");
                ssoState = (String)initContext.lookup("java:global/iduruguayhttphandler/ssoState");
                ssoReturnUrl = (String)initContext.lookup("java:global/iduruguayhttphandler/ssoReturnUrl");
                ssoHomePage = (String)initContext.lookup("java:global/iduruguayhttphandler/ssoHomePage");
                dsJndiName = (String)initContext.lookup("java:global/iduruguayhttphandler/dsJndiName");
                final String nidsHabilitadosString = (String) initContext.lookup("java:global/iduruguayhttphandler/nidsHabilitados");
                if (StringUtils.isNotBlank(nidsHabilitadosString)) {
                    nidsHabilitados = StringUtils.split(nidsHabilitadosString, ",");
                } else {
                    nidsHabilitados = NID_HABILITADOS_DEFAULT;
                }

                logger.log(Level.INFO, "    returnPath: {0}", returnPath);
                logger.log(Level.INFO, "    ssoLogoutUrl: {0}", ssoLogoutUrl);
                logger.log(Level.INFO, "    ssoBaseUrl: {0}", ssoBaseUrl);
                logger.log(Level.INFO, "    ssoClientId: {0}", ssoClientId);
                logger.log(Level.INFO, "    ssoSecret: {0}", ssoSecret);
                logger.log(Level.INFO, "    ssoState: {0}", ssoState);
                logger.log(Level.INFO, "    ssoReturnUrl: {0}", ssoReturnUrl);
                logger.log(Level.INFO, "    ssoHomePage: {0}", ssoHomePage);
                logger.log(Level.INFO, "    dsJndiName: {0}", dsJndiName);
                logger.info("==============================================");
            }
            
        } catch (NamingException ex) {
            Logger.getLogger(IDUruguayHttpHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                initContext.close();
            } catch (NamingException ex) {
                Logger.getLogger(IDUruguayHttpHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.next = next;
    }

    @Override
    @SuppressWarnings("UnnecessaryReturnStatement")
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        
    	if (typeHandler == null) {
    		next.handleRequest(exchange);
            return;
    	}
        if(typeHandler.equals("OPENID")){
            if (StringUtils.isBlank(returnPath) || StringUtils.isBlank(ssoLogoutUrl) || StringUtils.isBlank(ssoBaseUrl) 
                    || StringUtils.isBlank(ssoClientId) || StringUtils.isBlank(ssoSecret) || StringUtils.isBlank(ssoState) 
                    || StringUtils.isBlank(ssoReturnUrl) ||StringUtils.isBlank(ssoHomePage) || StringUtils.isBlank(dsJndiName)){
                logger.log(Level.INFO, "[{0}] No hay parámetros de config de Id Uruguay definidos", new Object[]{exchange.getRelativePath()});
                next.handleRequest(exchange);
                return;
            }

            logger.log(Level.INFO, "[{0}] ================================", new Object[]{exchange.getRelativePath()});
            logger.log(Level.INFO, "[{0}] Comenzando a procesar request...", new Object[]{exchange.getRelativePath()});
            logger.log(Level.FINE, "[{0}] ================================", new Object[]{exchange.getRelativePath()});
            
            logger.log(Level.INFO, "[{0}] RequestURL: {1}", new Object[]{exchange.getRelativePath(), exchange.getRelativePath()});
            logger.log(Level.FINE, "[{0}] QueryString: {1}", new Object[]{exchange.getRelativePath(), exchange.getQueryString()});
            logger.log(Level.FINE, "[{0}] SecurityContext: {1}", new Object[]{exchange.getRelativePath(), exchange.getSecurityContext()});
            logger.log(Level.FINE, "[{0}] AuthenticationRequired: {1}", new Object[]{exchange.getRelativePath(), (exchange.getSecurityContext() != null && exchange.getSecurityContext().isAuthenticationRequired())});
            logger.log(Level.FINE, "[{0}] Authenticated: {1}", new Object[]{exchange.getRelativePath(), (exchange.getSecurityContext() != null && exchange.getSecurityContext().isAuthenticated())});

            String servletPath = exchange.getRelativePath();
            if (returnPath.equals(servletPath)) {
                // =================================================================
                // Es un acceso a la URL de retorno luego de un login exitoso
                // =================================================================
                logger.log(Level.INFO, "[{0}] Es un acceso a la URL de retorno.", new Object[]{exchange.getRelativePath()});

                String state = exchange.getQueryParameters().get("state").getFirst();
                if (ssoState.equals(state)) {
                    //Es la respuesta del POST inicial a la página de login, hay que enviar un POST para pedir el token

                    //Verificar que ID Uruguay no haya devuelto un error
                    if(exchange.getQueryParameters().containsKey("error")) {
                        //ID Uruguay devolvió un error
                        if(exchange.getQueryParameters().containsKey("error_description")) {
                            logger.log(Level.FINE, "[{0}] La respuesta enviada por ID Uruguay es de error: {1}/{2}", new Object[]{exchange.getRelativePath(),
                            exchange.getQueryParameters().get("error_description").getFirst()});
                        }else {
                            logger.log(Level.INFO, "[{0}] La respuesta enviada por ID Uruguay es de error: {1}", new Object[]{exchange.getRelativePath(), 
                            exchange.getQueryParameters().get("error").getFirst()});
                        }
                        exchange.setStatusCode(302);
                        exchange.getResponseHeaders().put(Headers.LOCATION, ssoHomePage.replace("/privado/", "/publico/"));
                        exchange.endExchange();

                        return;
                    }

                    //ID Uruguay no devolvió un error, debe haber un code
                    try {
                        //Obtener el código de autenticación
                        String code = exchange.getQueryParameters().get("code").getFirst();

                        String authKey = ssoClientId + ":" + ssoSecret;
                        String authKeyB64 = new String(Base64.getEncoder().encode(authKey.getBytes()));

                        //No validar el certificado SSL
                        IDUruguayTrustManager.allowAllSSL();

                        //Solicitar un token con el código obtenido de la respuesta de ID Uruguay
                        String url = ssoBaseUrl + "token";
                        URL urlObj = new URL(url);
                        HttpsURLConnection con = (HttpsURLConnection) urlObj.openConnection();
                        con.setRequestMethod("POST");
                        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        con.setRequestProperty("User-Agent", ssoState);
                        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                        con.setRequestProperty("Authorization", "Basic " + authKeyB64);
                        String tokenDatos = "grant_type=authorization_code&code=" + code + "&redirect_uri=" + ssoReturnUrl;
                        logger.log(Level.FINE, "[{0}] Haciendo POST para obtener el token... {1}/{2}", new Object[]{exchange.getRelativePath(), url, tokenDatos});
                        con.setDoOutput(true);
                        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                        wr.writeBytes(tokenDatos);
                        wr.flush();
                        wr.close();

                        //Validar el código de respuesta
                        if(con.getResponseCode() != 200) {
                            logger.log(Level.SEVERE, "[{0}] No se pudo obtener el token: {1}/{2}", new Object[]{exchange.getRelativePath(), 
                            con.getResponseCode(), con.getResponseMessage()});
                            exchange.getResponseSender().send("Error de autenticación ante ID Uruguay.");
                            exchange.setStatusCode(401);
                            exchange.endExchange();
                            return;
                        }

                        //Procesar la respuesta
                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
                        String inputLine;
                        StringBuilder response = new StringBuilder();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();

                        logger.log(Level.INFO, "[{0}] Respuesta al POST para obtener el token: {1}", new Object[]{exchange.getRelativePath(), response.toString()});

                        JSONObject jsonObj = new JSONObject(response.toString());
                        String accessToken = jsonObj.getString("access_token");
                        String refreshToken = jsonObj.getString("refresh_token");
                        String tokenType = jsonObj.getString("token_type");
                        Long expiresIn = jsonObj.getLong("expires_in");
                        String idToken = jsonObj.getString("id_token");

                        ServletRequestContext src = exchange.getAttachment(ServletRequestContext.ATTACHMENT_KEY);

                        //Solicitar con el token recibido los datos del usuario
                        url = ssoBaseUrl + "userinfo";
                        urlObj = new URL(url);
                        con = (HttpsURLConnection) urlObj.openConnection();
                        con.setRequestMethod("GET");
                        con.setRequestProperty("User-Agent", ssoState);
                        con.setRequestProperty("Authorization", "Bearer " + accessToken);

                        logger.log(Level.FINE, "[{0}] Haciendo GET para obtener datos del usuario... {1}", new Object[]{exchange.getRelativePath(), url});

                        //Validar el código de respuesta
                        if(con.getResponseCode() != 200) {
                            logger.log(Level.INFO, "[{0}] No se pudo obtener los datos del usuario: {1}/{2}", new Object[]{exchange.getRelativePath(), 
                            con.getResponseCode(), con.getResponseMessage()});
                            exchange.getResponseSender().send("Error de autenticación ante ID Uruguay.");
                            exchange.setStatusCode(401);
                            exchange.endExchange();
                            return;
                        }

                        //Procesar la respuesta
                        in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
                        response = new StringBuilder();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();

                        logger.log(Level.INFO, "[{0}] Respuesta al GET para obtener datos del usuario: {1}", new Object[]{exchange.getRelativePath(), response.toString()});

                        jsonObj = new JSONObject(response.toString());

                        //ToDo: entre los datos que devuelve el servidor debería estar el código de usuario (uy-ci-123456789), pero por ahora no está.
                        //De hecho, el documento viene en tres partes con el nombre completo del país (Uruguay), y un tipo de documento no tabulado (C.I.).

                        String paisDocumento = obtenerValor(jsonObj, "pais_documento", "codigo");
                        String tipoDocumento = obtenerValor(jsonObj, "tipo_documento", "codigo");
                        String numeroDocumento = obtenerValor(jsonObj, "numero_documento", null);

                        if(StringUtils.isBlank(paisDocumento) || StringUtils.isBlank(tipoDocumento) || StringUtils.isBlank(numeroDocumento)) {
                            exchange.getResponseSender().send("Error de autenticación ante ID Uruguay.");
                            exchange.setStatusCode(401);
                            exchange.endExchange();
                            logger.log(Level.SEVERE, "ID Uruguay no ha indicado el documento del ciudadano");
                            return;
                        }

                        //paisDocumento = convertirPaisDocumento(paisDocumento);
                        tipoDocumento = convertirTipoDocumento(tipoDocumento);

                        String codigoUsuario = paisDocumento+"-"+tipoDocumento+"-"+numeroDocumento;

                        //Validar el nivel de seguridad (acr)
                        //Los valores posibles son urn:iduruguay:nid:0, urn:iduruguay:nid:1, urn:iduruguay:nid:2, urn:iduruguay:nid:3
                        //Se requiere nid:2 o nid:3 para el backend
                        String acr = null;
                        if (jsonObj.has("acr")) {
                            acr = jsonObj.getString("acr");
                        } else if (jsonObj.has("nid")) {
                            acr = jsonObj.getString("nid");
                        }
                        if (acr == null && idToken != null) {
                            //Si no está en userinfo, buscarlo en el ID Token (JWT)
                            try {
                                String[] parts = idToken.split("\\.");
                                if (parts.length >= 2) {
                                    String payload = new String(Base64.getDecoder().decode(parts[1]));
                                    JSONObject idTokenPayload = new JSONObject(payload);
                                    if (idTokenPayload.has("acr")) {
                                        acr = idTokenPayload.getString("acr");
                                    } else if (idTokenPayload.has("nid")) {
                                        acr = idTokenPayload.getString("nid");
                                    }
                                }
                            } catch (Exception ex) {
                                logger.log(Level.INFO, "No se pudo decodificar el ID Token para verificar el acr", ex);
                            }
                        }
                        
                        logger.log(Level.INFO, "[{0}] Nivel de seguridad (acr/nid) recibido: {1}", new Object[]{exchange.getRelativePath(), acr});
                        
                        String contextPath = src.getDeployment().getServletContext().getContextPath();
                        logger.log(Level.INFO, "[{0}] ContextPath: {1}", new Object[]{exchange.getRelativePath(), contextPath});
                        if (StringUtils.contains(contextPath, "sae-web-admin") &&
                                !StringUtils.endsWithAny(acr, NID_HABILITADOS_DEFAULT)) {
                            logger.log(Level.INFO, "[{0}] El usuario {1} no tiene el nivel de seguridad requerido (nid2 o nid3). acr: {2}", new Object[]{exchange.getRelativePath(), numeroDocumento, acr});
                            exchange.setStatusCode(302);
                            exchange.getResponseHeaders().put(Headers.LOCATION, contextPath + "/error/accesoNoAutorizadoIdUruguay.xhtml");
                            exchange.endExchange();
                            return;
                        }

                        //Determinar si existe un usuario con el código de usuario indicado
                        Context initContext = new InitialContext();
                        DataSource ds = (DataSource)initContext.lookup(dsJndiName);
                        Connection conn = ds.getConnection();
                        PreparedStatement st = conn.prepareStatement("SELECT 1 FROM global.ae_usuarios WHERE codigo=?");
                        st.setString(1, numeroDocumento);
                        st.executeQuery();
                        ResultSet rs = st.getResultSet();
                        boolean existeUsuario = rs.next();
                        rs.close();
                        st.close();

                        logger.log(Level.INFO, "[{0}] Existe un usuario con el código {1}? {2}", new Object[]{exchange.getRelativePath(), codigoUsuario, existeUsuario});

                        //Si existe, actualizar su nombre, si no existe crearlo
                        String nombre1 = obtenerValor(jsonObj, "primer_nombre", null);
                        String nombre2 = obtenerValor(jsonObj, "segundo_nombre", null);
                        String apellido1 = obtenerValor(jsonObj, "primer_apellido", null);
                        String apellido2 = obtenerValor(jsonObj, "segundo_apellido", null);
                        String nombres = ((StringUtils.isBlank(nombre1)?"":nombre1)+" "+(StringUtils.isBlank(nombre2)?"":nombre2)).trim();
                        String apellidos = ((StringUtils.isBlank(apellido1)?"":apellido1)+" "+(StringUtils.isBlank(apellido2)?"":apellido2)).trim();
                        if(existeUsuario) {
                            String sql = "UPDATE global.ae_usuarios SET nombre=? WHERE codigo=?";
                            logger.log(Level.FINE, "[{0}] Actualizando un usuario: {1}", new Object[]{exchange.getRelativePath(), sql});
                            st = conn.prepareStatement(sql);
                            st.setString(1, nombres.concat(" ").concat(apellidos));
                            st.setString(2, numeroDocumento);
                            st.executeUpdate();
                            st.close();
                        }else {


                        }

                        conn.close();

                        //Hacer login con el módulo PFEALoginModule
                        boolean loginOk = exchange.getSecurityContext().login(numeroDocumento, ssoState);
                        if(!loginOk) {
                            exchange.getResponseSender().send("Error de autenticación ante ID Uruguay.");
                            exchange.setStatusCode(401);
                            exchange.endExchange();
                            logger.log(Level.SEVERE, "No se pudo realizar la autenticación del usuario en la aplicación");
                            return;
                        }

                        src.getSession().putValue("IDURUGUAY_ID_TOKEN", idToken);
                        //Redirigir a la página de inicio de la aplicación
                        logger.log(Level.INFO, "[{0}] Redirigiendo a la página de inicio...", new Object[]{exchange.getRequestPath()});
                        exchange.setStatusCode(302);
                        exchange.getResponseHeaders().put(Headers.LOCATION, ssoHomePage);
                        exchange.endExchange();
                        return;

                    } catch (IOException | JSONException | NamingException | SQLException ex) {
                        exchange.getResponseSender().send("Error de autenticación ante ID Uruguay.");
                        exchange.setStatusCode(401);
                        exchange.endExchange();
                        logger.log(Level.SEVERE, "No se pudo procesar la respuesta del servidor de autenticación", ex);
                        return;
                    }

                } else {
                    logger.log(Level.SEVERE, "[{0}] Intento de acceso a /iduruguay sin incluir la variable state correcta ({1}).", 
                            new Object[]{exchange.getRequestPath(), ssoState});
                    exchange.getResponseSender().send("Acceso denegado; solo se permite el acceso a este recurso bajo ciertas circunstancias.");
                    exchange.endExchange();
                    return;
                }
            } else {
                // =================================================================
                // Es un acceso a una URL comun
                // =================================================================
                logger.log(Level.INFO, "[{0}] No es un acceso a la URL de retorno ni de logout.", new Object[]{exchange.getRelativePath()});

                //Si el recurso no requiere autenticación no hay nada para hacer
                if(!exchange.getSecurityContext().isAuthenticationRequired()) {
                    logger.log(Level.INFO, "[{0}] NO se requiere autenticación.", new Object[]{exchange.getRequestPath()});
                    next.handleRequest(exchange);
                    return;
                }

                //Si ya está autenticado no hay nada para hacer
                if(exchange.getSecurityContext().isAuthenticated()) {
                    logger.log(Level.INFO, "[{0}] Ya está autenticado.", new Object[]{exchange.getRequestPath()});
                    next.handleRequest(exchange);
                    return;
                }

    //            Ver si se puede autenticar con los datos ya existentes
                if(exchange.getSecurityContext().authenticate()) {
                    logger.log(Level.INFO, "[{0}] Ya estaba autenticado previamente.", new Object[]{exchange.getRequestPath()});
                    next.handleRequest(exchange);
                    return;
                }

                //Hay que hacer la autenticación
                logger.log(Level.INFO, "[{0}] Hay que iniciar el proceso de autenticación.", new Object[]{exchange.getRequestPath()});
                
                String scope = "openid%20personal%20email%20document%20personal_info";
                String acrValues = "";
                ServletRequestContext srcAuth = exchange.getAttachment(ServletRequestContext.ATTACHMENT_KEY);
                if (srcAuth != null) {
                    String contextPath = srcAuth.getDeployment().getServletContext().getContextPath();
                    if (contextPath != null && contextPath.contains("sae-web-admin")) {
                        scope += "%20auth_info";
                        acrValues = "&acr_values=urn:iduruguay:nid:0%20urn:iduruguay:nid:1%20urn:iduruguay:nid:2%20urn:iduruguay:nid:3";
                    }
                }

                String urlRedirect = ssoBaseUrl + "authorize?response_type=code&scope=" + scope + "&"
                        + "client_id="+ssoClientId+"&state="+ssoState+"&redirect_uri=" + ssoReturnUrl
                        + acrValues;
                logger.log(Level.FINE, "[{0}] URL de redirección: {1}.", new Object[]{exchange.getRequestPath(), urlRedirect});
                exchange.setStatusCode(302);
                exchange.getResponseHeaders().put(Headers.LOCATION, urlRedirect);
                exchange.endExchange();
                return;

            }
        }
        else{
            next.handleRequest(exchange);
            return;
        }

    }

    // ===============================================================

    private String obtenerValor(JSONObject jObj, String nombre1, String nombre2) {
        try {
            if(nombre2==null) {
                return jObj.get(nombre1).toString();
            }
            return jObj.getJSONObject(nombre1).get(nombre2).toString(); 
        }catch(Exception ex) {
            return null;
        }
    }

//    public String convertirPaisDocumento(String paisDocumento) {
//        for(DocPaisEmisor pais : DocPaisEmisor.values()) {
//            if(paisDocumento.equals(pais.getNombre()) || paisDocumento.equals(pais.name())) {
//                return pais.name();
//            }
//        }
//        return DocPaisEmisor.otro.name();
//    }
    

    public String convertirTipoDocumento(String tipoDocumento) {
        if (bundle.containsKey(tipoDocumento)) {
            return bundle.getString(tipoDocumento);
        }
        return tipoDocumento;
    }
    
    

}
