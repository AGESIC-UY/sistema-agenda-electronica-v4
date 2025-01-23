/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.sofis.web.iduruguay.saml;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.servlet.handlers.ServletRequestContext;
import io.undertow.util.Headers;
import io.undertow.util.StatusCodes;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.X509Certificate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.opensaml.Configuration;
import org.opensaml.DefaultBootstrap;
import org.opensaml.common.SAMLObjectBuilder;
import org.opensaml.common.SAMLVersion;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.common.impl.SecureRandomIdentifierGenerator;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml1.core.NameIdentifier;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.AttributeStatement;
import org.opensaml.saml2.core.Audience;
import org.opensaml.saml2.core.AudienceRestriction;
import org.opensaml.saml2.core.AuthnContextClassRef;
import org.opensaml.saml2.core.AuthnStatement;
import org.opensaml.saml2.core.Conditions;
import org.opensaml.saml2.core.LogoutRequest;
import org.opensaml.saml2.core.LogoutResponse;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.RequestAbstractType;
import org.opensaml.saml2.core.RequestedAuthnContext;
import org.opensaml.saml2.core.SessionIndex;
import org.opensaml.saml2.core.Status;
import org.opensaml.saml2.core.StatusCode;
import org.opensaml.saml2.core.Subject;
import org.opensaml.saml2.core.impl.AudienceBuilder;
import org.opensaml.saml2.core.impl.AudienceRestrictionBuilder;
import org.opensaml.saml2.core.impl.AuthnContextClassRefBuilder;
import org.opensaml.saml2.core.impl.ConditionsBuilder;
import org.opensaml.saml2.core.impl.RequestedAuthnContextBuilder;
import org.opensaml.saml2.core.impl.SessionIndexBuilder;
import org.opensaml.security.SAMLSignatureProfileValidator;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.XMLObjectBuilderFactory;
import org.opensaml.xml.io.Marshaller;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.io.Unmarshaller;
import org.opensaml.xml.io.UnmarshallerFactory;
import org.opensaml.xml.io.UnmarshallingException;
import org.opensaml.xml.schema.XSAny;
import org.opensaml.xml.schema.XSString;
import org.opensaml.xml.security.SecurityConfiguration;
import org.opensaml.xml.security.SecurityHelper;
import org.opensaml.xml.security.x509.BasicX509Credential;
import org.opensaml.xml.security.x509.KeyStoreX509CredentialAdapter;
import org.opensaml.xml.signature.Signature;
import org.opensaml.xml.signature.SignatureValidator;
import org.opensaml.xml.signature.Signer;
import org.opensaml.xml.signature.impl.SignatureBuilder;
import org.opensaml.xml.util.Base64;
import org.opensaml.xml.util.XMLHelper;
import org.opensaml.xml.validation.ValidationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


/**
 * * Este handler es especificamente para la integración con IDURUGUAY con protocolo SAML, antes llamado CDA
 * @author smena
 */
@SuppressWarnings({"rawtypes", "unused", "unchecked"})
public class IDUruguaySAMLHttpHandler implements HttpHandler {

    private static final Logger logger = Logger.getLogger(IDUruguaySAMLHttpHandler.class.getName());

    private static final String SAML_REQUEST = "SAMLRequest";
    private static final String SAML_RESPONSE = "SAMLResponse";


    private static final String USER_DATA_CACHE = "UserDataCache";

    private SecureRandomIdentifierGenerator idGenerator;

    // Estos son atributos de configuracion por lo que no hay problema de
    // sincronizacion (solo se modifican al levantar el Wildfly)
    private String idpUrlLogin = "";
    private String idpUrlLogout = "";
    private String providerId = "";
    private String spReturnUrl = "";
    private String returnPath = "";
    private String logoutPath = "";
    private String keystorePath = "";
    private String keystorePass = "";
    private String certAlias = "";
    private String truststorePath = "";
    private String truststorePass = "";
    private boolean validarFirma = false;
    private String attributeMappings = "";
    private String privisioningConfigs = "";
    private String usernameTransformations = "";
    private boolean debug = false;
    private boolean relaxValidityPeriod = false;
    private String ssoState;
    

    CDAServiceProviderData programData = null;

    // Esta variable se modifica solo en el constructor por lo que tampoco hay
    // problema de sinc.
    private XMLObjectBuilderFactory builderFactory;
     
    private String typeHandler;
    
    
    private final HttpHandler next;
    
    private UserData userData;

    public IDUruguaySAMLHttpHandler(HttpHandler next) {

        Context initContext = null;
        try {
            initContext = new InitialContext();
            typeHandler = (String)initContext.lookup("java:global/iduruguay/typeHandlerPub");
            if(typeHandler!=null &&  typeHandler.equals("SAML")){
                logger.info("=== SAE-USERS-WEB ==================================");
                logger.info("=== SAMLCONNECT CLIENT-VALVE v.1.0 ===");
                
                idpUrlLogin = (String)initContext.lookup("java:global/iduruguaysamlhttphandler/idpUrlLogin");
                idpUrlLogout = (String)initContext.lookup("java:global/iduruguaysamlhttphandler/idpUrlLogout");
                providerId = (String)initContext.lookup("java:global/iduruguaysamlhttphandler/providerIdPub");
                spReturnUrl = (String)initContext.lookup("java:global/iduruguaysamlhttphandler/spReturnUrlPub");
                returnPath = (String)initContext.lookup("java:global/iduruguaysamlhttphandler/returnPath");
                logoutPath = (String)initContext.lookup("java:global/iduruguaysamlhttphandler/logoutPath");
                keystorePath = (String)initContext.lookup("java:global/iduruguaysamlhttphandler/keystorePath");
                keystorePass = (String)initContext.lookup("java:global/iduruguaysamlhttphandler/keystorePass");
                certAlias = (String)initContext.lookup("java:global/iduruguaysamlhttphandler/certAlias");
                truststorePath = (String)initContext.lookup("java:global/iduruguaysamlhttphandler/truststorePath");
                truststorePass = (String)initContext.lookup("java:global/iduruguaysamlhttphandler/truststorePass");
                attributeMappings = (String)initContext.lookup("java:global/iduruguaysamlhttphandler/attributeMappings");
                validarFirma = Boolean.getBoolean((String) initContext.lookup("java:global/iduruguaysamlhttphandler/validarFirma"));
                relaxValidityPeriod = Boolean.getBoolean((String) initContext.lookup("java:global/iduruguaysamlhttphandler/relaxValidityPeriod"));
                ssoState = (String)initContext.lookup("java:global/iduruguayhttphandler/ssoState");

                logger.log(Level.INFO, "    returnPath: {0}", returnPath);
                logger.log(Level.INFO, "    idpUrlLogout: {0}", idpUrlLogout);
                logger.log(Level.INFO, "    providerId: {0}", providerId);
                logger.log(Level.INFO, "    spReturnUrl: {0}", spReturnUrl);
                logger.log(Level.INFO, "    returnPath: {0}", returnPath);
                logger.log(Level.INFO, "    logoutPath: {0}", logoutPath);
                logger.log(Level.INFO, "    keystorePath: {0}", keystorePath);
                logger.log(Level.INFO, "    keystorePass: {0}", keystorePass);
                logger.log(Level.INFO, "    certAlias: {0}", certAlias);
                logger.log(Level.INFO, "    truststorePath: {0}", truststorePath);
                logger.log(Level.INFO, "    truststorePass: {0}", truststorePass);
                logger.log(Level.INFO, "    attributeMappings: {0}", attributeMappings);

                logger.info("=== FIN INIT SAE-USERS-WEB ===============================");

                DefaultBootstrap.bootstrap();
                builderFactory = Configuration.getBuilderFactory();
                idGenerator = new SecureRandomIdentifierGenerator();
            }
            
        } catch (NamingException ex) {
            Logger.getLogger(IDUruguaySAMLHttpHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Throwable ex) {
            Logger.getLogger(IDUruguaySAMLHttpHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                initContext.close();
            } catch (NamingException ex) {
                Logger.getLogger(IDUruguaySAMLHttpHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.next = next;
    }

    @Override
    @SuppressWarnings("UnnecessaryReturnStatement")
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        //Se pregunta si el typeHandler es distinto de nulo y si es de tipo SAML
        if(typeHandler!=null &&  typeHandler.equals("SAML")){
            
            logger.log(Level.FINE, "[{0}] ================================", new Object[]{exchange.getRelativePath()});
            logger.log(Level.FINE, "[{0}] Comenzando a procesar request...", new Object[]{exchange.getRelativePath()});
            logger.log(Level.FINE, "[{0}] ================================", new Object[]{exchange.getRelativePath()});
            
            logger.log(Level.FINE, "[{0}] RequestURL: {1}", new Object[]{exchange.getRelativePath(), exchange.getRelativePath()});
            logger.log(Level.FINE, "[{0}] QueryString: {1}", new Object[]{exchange.getRelativePath(), exchange.getQueryString()});
            logger.log(Level.FINE, "[{0}] SecurityContext: {1}", new Object[]{exchange.getRelativePath(), exchange.getSecurityContext()});
            logger.log(Level.FINE, "[{0}] AuthenticationRequired: {1}", new Object[]{exchange.getRelativePath(), (exchange.getSecurityContext() != null && exchange.getSecurityContext().isAuthenticationRequired())});
            logger.log(Level.FINE, "[{0}] Authenticated: {1}", new Object[]{exchange.getRelativePath(), (exchange.getSecurityContext() != null && exchange.getSecurityContext().isAuthenticated())});

            if (programData == null) {
                    synchronized (this) {
                            programData = new CDAServiceProviderData(keystorePath, keystorePass, truststorePath, truststorePass,
                                 attributeMappings, privisioningConfigs, usernameTransformations);
                    }
            }
            if (!programData.isConfigured()) {

                logger.log(Level.SEVERE, "[{0}] No se pudo inicializar la configuracion de la valvula. Puede obtener mas informacion en el log del servidor ({1}).", 
                            new Object[]{exchange.getRequestPath(), /*ssoState*/});
                exchange.getResponseSender().send("No se pudo inicializar la configuracion de la valvula.");
                exchange.endExchange();
                return;
            }


            // Si el servletPath es la URL de retorno registrada en Agesic (en desarrollo, se registro
            // como URL de retorno "https://agenda2.sofis.com.uy:13002/sae-admin/cda", por lo que el servletPath debe ser "/cda")
            // hay que ver si viene un SAMLResponse y ademas es valido y esta firmado.
            // Incluso si la firma es válida hay que ver si el registro del usuario en CDA es "Certificado" (usó la
            //cédula electrónica) o "Presencial" (demostró ser el dueño del número de cédula)
            String servletPath = exchange.getRelativePath();
            if (returnPath.equals(servletPath)) {

                // =================================================================
                // Es un acceso a la URL de retorno luego de un login exitoso
                // =================================================================
                logger.log(Level.FINE, "[{0}] Es un acceso a la URL de retorno.", new Object[]{exchange.getRelativePath()});


                String samlResponse = getParameter(exchange, SAML_RESPONSE);
                if (samlResponse == null) {

                        //Es posible que contenga un LogoutRequest del CDA
                        String samlRequest = getParameter(exchange,SAML_REQUEST);
                        
                        if(samlRequest != null) {
                        logger.log(Level.FINE, "[{0}] Se recibe una invocación de logout desde CDA.", new Object[]{exchange.getRelativePath()});
                            try {
                                processSAMLRequest(samlRequest,exchange);
                                return;
                            }catch(CDAServiceProviderException ex) {
                                exchange.getResponseSender().send("Error de autenticación ante ID Uruguay SAML.");
                                exchange.endExchange();
                                logger.log(Level.FINE, "Error en el proceso de SAML Request", ex);
                            }
                            return;
                        }
                        logger.log(Level.FINE, "[{0}] El acceso a la URL de retorno no contiene una respuesta SAML. Se muestra error y termina la ejecucion.", new Object[]{exchange.getRelativePath()});

                        
                        exchange.getResponseSender().send("Esta URL solo debe ser invocada por el Proveedor de Identidades de su Organización");
                        exchange.endExchange();
                        return;
                }
                
                logger.log(Level.FINE, "[{0}] El acceso a la URL de retorno contiene una respuesta SAML. Se continúa", new Object[]{exchange.getRelativePath()});
                // Tomar la respuesta y validarla
                String numeroDoc;
                try {
                    logger.log(Level.FINE, "[{0}] Procesando la respuesta...", new Object[]{exchange.getRelativePath()});
                    numeroDoc = processSAMLResponse(samlResponse, programData, exchange);
                    logger.log(Level.FINE, "[{0}] El código del usuario  es: ({1})", new Object[]{exchange.getRelativePath(),numeroDoc});
                    if (numeroDoc == null) {
                        logger.log(Level.FINE, "[{0}] No se encontro el  usuario", new Object[]{exchange.getRelativePath()});
                        throw new CDAServiceProviderException("No se encontro el  usuario");
                    }
                } catch (CDAServiceProviderException ex) {
                    logger.log(Level.SEVERE, "[{0}] Error procesando la respuesta: ({1}).", new Object[]{exchange.getRequestPath(), ex.getMessage()});
                    throw new CDAServiceProviderException("Error procesando la respuesta SAML");
                }

               

                // hacer login con el numero de documento que entrega cda
                //Hacer login con el módulo IDURUGUAYLoginModule
                boolean loginOk = exchange.getSecurityContext().login(numeroDoc, ssoState);
                if(!loginOk) {
                    exchange.getResponseSender().send("Error de autenticación ante ID Uruguay.");
                    exchange.endExchange();
                    logger.log(Level.SEVERE, "No se pudo realizar la autenticación del usuario en la aplicación");
                    return;
                }
                
                //Se establece en session un atributo identificador que el login se ha realizado con IDURUGUAY con protocolo SAML
                //Esto con la finalidad que el sistema conozca con que protocolo se esta trabajando, es útil cuando se hace un logout
                ServletRequestContext src = exchange.getAttachment(ServletRequestContext.ATTACHMENT_KEY);
                src.getSession().setAttribute("IDURUGUAY_SAML", numeroDoc);
                src.getSession().setAttribute(USER_DATA_CACHE, userData);
            
                logger.log(Level.FINE, "[{0}] Redirigiendo a la página de solicitada...", new Object[]{exchange.getRequestPath()});
                String redirect = src.getSession().getAttribute("ssoHomePage").toString();
                //Reenviar al usuario a la pagina que solicito originalmente
                exchange.setStatusCode(302);
                exchange.getResponseHeaders().put(Headers.LOCATION, redirect);
                exchange.endExchange();
                return;
                    

            } else if (logoutPath.equals(servletPath)) {

                // =================================================================
                // Es un acceso a la URL de logout
                // =================================================================
                logger.log(Level.FINE, "[{0}] Se detecta un acceso a la URL de logout ({1}).", new Object[]{exchange.getRequestPath(), logoutPath});
                // Armar el SAML Request de logout y enviarlo
                initLogoutProcess(programData, exchange);
                // Destruir la sesión (no importa el resultado de la solicitud de logout)
                ServletRequestContext src = exchange.getAttachment(ServletRequestContext.ATTACHMENT_KEY);

                src.getSession().invalidate();
                return;
            } else {

                // =================================================================
                // Es un acceso a una URL comun
                // =================================================================
                logger.log(Level.FINE, "[{0}] No es un acceso a la URL de retorno ni de logout.", new Object[]{exchange.getRelativePath()});
                
                //Si el recurso no requiere autenticación no hay nada para hacer
                if(!exchange.getSecurityContext().isAuthenticationRequired()) {
                    logger.log(Level.FINE, "[{0}] NO se requiere autenticación.", new Object[]{exchange.getRequestPath()});
                    next.handleRequest(exchange);
                    return;
                }
                
                //Si ya está autenticado no hay nada para hacer
                if(exchange.getSecurityContext().isAuthenticated()) {
                    logger.log(Level.FINE, "[{0}] Ya está autenticado.", new Object[]{exchange.getRequestPath()});
                    next.handleRequest(exchange);
                    return;
                }

                //Ver si se puede autenticar con los datos ya existentes
                if(exchange.getSecurityContext().authenticate()) {
                    logger.log(Level.FINE, "[{0}] Ya estaba autenticado previamente.", new Object[]{exchange.getRequestPath()});
                    next.handleRequest(exchange);
                    return;
                }
                
                //Hay que hacer la autenticación
                logger.log(Level.FINE, "[{0}] Hay que iniciar el proceso de autenticación.", new Object[]{exchange.getRequestPath()});
                
                //Se construye el authnRequetes necesario para hacer el login con portal.gub.uy
                try {   
                        AuthnRequest authnRequest = buildSAMLAuthnRequest(programData, providerId);

                        printXMLObject("AUTHNREQUEST", authnRequest);

                        Marshaller marshaller = org.opensaml.Configuration.getMarshallerFactory().getMarshaller(authnRequest);
                        org.w3c.dom.Element authDOM = marshaller.marshall(authnRequest);
                        StringWriter rspWrt = new StringWriter();
                        XMLHelper.writeNode(authDOM, rspWrt);

                        String samlRequest = Base64.encodeBytes(rspWrt.toString().getBytes(), Base64.DONT_BREAK_LINES);

                        // Nota: tal vez sea necesario URLEncode a samlRequest
                        // (URLEncoder.encode(samlRequest, "UTF-8"))
                        String html = "<html><form id='form1' action='" + idpUrlLogin + "' method='POST'>" + "<input type='hidden' name='"
                            + SAML_REQUEST + "' value='" + samlRequest + "' />";
                        if (debug) {
                                html += "<input type='submit' value='Login'/></form></html>";
                        } else {
                                html += "</form><script>document.getElementById('form1').submit();</script></html>";
                        }
                        
                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
                        exchange.setStatusCode(StatusCodes.OK);
                        exchange.getResponseSender().send(html);
                        exchange.endExchange();
                        
                        
                } catch (MarshallingException ex) {
                	logger.log(Level.WARNING, ExceptionUtils.getStackTrace(ex));
                }
                
                
                return;
               
            }

        }
        else{
            //Si no está especificado que use SAML se hace un next para que el próximo handler tome la petición
            //en este caso sería el handler de OPENID IDUruguayHttpHanlder el cual es el encargado de hacer la integración
            //con IDURUGUAY con protocolo OPENID.
            next.handleRequest(exchange);
            return;
        }

    }
    
    
    private String processSAMLResponse(String sSamlResponse, CDAServiceProviderData programData, HttpServerExchange exchange) throws CDAServiceProviderException, NamingException, SQLException {
        
        try {
            org.opensaml.saml2.core.Response samlResponse;
            Element element;
            try {
                    byte[] bSamlResponse = Base64.decode(sSamlResponse);
                    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                    documentBuilderFactory.setNamespaceAware(true);
                    DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();
                    Document document = null;
                    //Intentar parsear la respuesta tal cual, si no se puede es probable que esté comprimida
                    //Cuando es una respuesta POST (request response) viene sin comprimir, 
                    //cuando es una respuesta GET (logout response) viene comprimida
                    try {
                        document = docBuilder.parse(new ByteArrayInputStream(bSamlResponse));
                    }catch(SAXParseException saxEx) {
                        try {
                            document = docBuilder.parse(new ByteArrayInputStream(inflate(bSamlResponse, true)));
                        }catch(Exception ex) {
                                //No se comprende la respuesta
                                throw new CDAServiceProviderException("No se comprende la respuesta del Proveedor de Identidades", ex);
                        }
                    }

                    element = document.getDocumentElement();
                    UnmarshallerFactory unmarshallerFactory = Configuration.getUnmarshallerFactory();
                    Unmarshaller unmarshaller = unmarshallerFactory.getUnmarshaller(element);
                    XMLObject responseXmlObj = unmarshaller.unmarshall(element);
                    String responseType = element.getLocalName();
                    if("LogoutResponse".equals(responseType)) {
                            return null;
                    }else if("Response".equals(responseType)) {
                            samlResponse = (org.opensaml.saml2.core.Response) responseXmlObj;
                    }else {
                            throw new CDAServiceProviderException("No se acepta el tipo de respuesta del Proveedor de Identidades: "+responseType+" (solo se acepta Response y LogoutResponse)");
                    }
            } catch (IOException | ParserConfigurationException | UnmarshallingException | SAXException ex) {
            	logger.log(Level.WARNING, ExceptionUtils.getStackTrace(ex));
                throw new CDAServiceProviderException("Error obteniendo la respuesta del Proveedor de Identidades", ex);
            }

            printXMLObject("AUTHRESPONSE", samlResponse);

            // Validar la firma
            if (samlResponse.getAssertions() == null || samlResponse.getAssertions().isEmpty()) {
                    throw new CDAServiceProviderException("La respuesta del Proveedor de Identidades no contiene informaciÃ³n sobre el usuario");
            }
            // Obtener la informacion del usuario (solo deberia venir una assertion, no mas)
            Assertion assertion = samlResponse.getAssertions().get(0);
            if (!assertion.isSigned()) {
                    throw new CDAServiceProviderException("La respuesta del Proveedor de Identidades no estÃ¡ firmada digitalmente, no se acepta");
            }
            // Esto es necesario porque el atributo "ID" (con mayusculas) no es igual al atributo "Id"
            // y por lo tanto al validar la firma no encuentra ningun elemento con el Id buscado
            // Lo que hace es registrar el atributo "ID" como un atributo id valido
            NodeList assertionElements = element.getElementsByTagNameNS(SAMLConstants.SAML20_NS, Assertion.DEFAULT_ELEMENT_LOCAL_NAME);
            for (int i = 0; i < assertionElements.getLength(); i++) {
                    Element assertionElement = (Element) assertionElements.item(i);
                    assertionElement.setIdAttributeNS(null, "ID", true);
            }
            // Ver si la firma cumple el estandar SAML
            Signature signature = assertion.getSignature();
            try {
                SAMLSignatureProfileValidator profileValidator = new SAMLSignatureProfileValidator();
                profileValidator.validate(signature);
            } catch (ValidationException ex) {
                logger.log(Level.SEVERE, "firma del token saml no cumple con el estandar SAML", ex);
                throw new CDAServiceProviderException("La firma del mensaje enviado por el Proveedor de Identidades no cumple el estÃ¡ndar SAML 2.0, no se acepta");
            }
            
            // La firma cumple el estandar, hay que validarla, validarla contra todos los certificados del truststore
            Enumeration<String> aliases = programData.getTrustStore().aliases();
            KeyStoreX509CredentialAdapter credential;
            SignatureValidator signatureValidator;
            String alias;
            boolean firmaValida = false;
            while (aliases.hasMoreElements()) {
                    alias = aliases.nextElement();
                    credential = new KeyStoreX509CredentialAdapter(programData.getTrustStore(), alias, truststorePass.toCharArray());
                    signatureValidator = new SignatureValidator(credential);
                    try {
                        signatureValidator.validate(signature);
                        firmaValida = true;
                        logger.log(Level.FINEST, "firma del token saml ok");
                        break;
                    } catch (ValidationException ex) {
                        logger.log(Level.FINEST, "firma del token saml invalida", ex);
                        // La firma no es criptograficamente valida con este
                        // certificado (probar con otro)
                    }
            }

            if (!firmaValida) {
                if (validarFirma) {
                        throw new CDAServiceProviderException("La firma del mensaje enviado por el Proveedor de Identidades no pudo ser validada con ninguno de los certificados del TrustStore, no se acepta");
                } else {
                        logger.warning("La firma del mensaje enviado por el Proveedor de Identidades no pudo ser validada con ninguno de los certificados del TrustStore");
                }
            }

            //La firma es válida, ver si tiene un nivel de seguridad aceptable
            //(Debe tener uno de los atributos "Certificado" o "Presencial" en true)
            assertion.getAttributeStatements();
            boolean certificado = false;
            boolean presencial = false;
            String uid = "";
            for (AttributeStatement attSt : assertion.getAttributeStatements()) {
                for (Attribute att : attSt.getAttributes()) {
                    if ("Certificado".equalsIgnoreCase(att.getName())) {
                            XSString attVal = (XSString) att.getAttributeValues().get(0);
                            String sCertificado = attVal.getValue();
                            if("true".equalsIgnoreCase(sCertificado)) {
                                    certificado = true;
                            }
                    }
                    if ("Presencial".equalsIgnoreCase(att.getName())) {
                            XSString attVal = (XSString) att.getAttributeValues().get(0);
                            String sPresencial = attVal.getValue();
                            if("true".equalsIgnoreCase(sPresencial)) {
                                    presencial = true;
                            }
                    }
                    if ("Uid".equalsIgnoreCase(att.getName())) {
                      XSString attVal = (XSString) att.getAttributeValues().get(0);
                      uid = attVal.getValue();
                    }
                }
            }
            if (!certificado && !presencial) {
                //throw new CDAServiceProviderException("La cuenta del usuario en CDA no está autenticada, no se acepta");
                logger.log(Level.FINE, "[{0}] La cuenta del usuariot ({1}) en CDA no está autenticada..", new Object[]{exchange.getRequestPath(),uid});
            } 

            // Verificar el status
            Status status = samlResponse.getStatus();
            String statusCode = status.getStatusCode().getValue();

            if (!StatusCode.SUCCESS_URI.equals(statusCode)) {
                    throw new CDAServiceProviderException("La respuesta enviada por el Proveedor de Identidades no es afirmativa (" + statusCode + "), no se acepta");
            }

            // Verificar la vigencia del token
            Conditions conditions = assertion.getConditions();
            if (!relaxValidityPeriod) {
                if (conditions == null || conditions.getNotBefore() == null || conditions.getNotOnOrAfter() == null) {
                        throw new CDAServiceProviderException("La respuesta enviada por el Proveedor de Identidades no especifica su periodo de vigencia, no se acepta");
                }
                DateTime ahora = new DateTime();
                if (conditions.getNotBefore().isAfter(ahora) || conditions.getNotOnOrAfter().isBefore(ahora)) {
                        throw new CDAServiceProviderException("La respuesta enviada por el Proveedor de Identidades esta fuera de su periodo de vigencia, no se acepta");
                }
            }

            // Verificar que el token este dirigido a este SP
            boolean isAudienceOk = false;
            if (conditions.getAudienceRestrictions() != null) {
                for (AudienceRestriction audienceRestriction : conditions.getAudienceRestrictions()) {
                        List<Audience> audiences = audienceRestriction.getAudiences();
                        if (audiences != null) {
                                for (Audience audience : audiences) {
                                        if (audience.getAudienceURI() != null && audience.getAudienceURI().equals(providerId)) {
                                                isAudienceOk = true;
                                        }
                                }
                        }
                }
            }
            if (!isAudienceOk) {
                    throw new CDAServiceProviderException("La respuesta enviada por el Proveedor de Identidades no está dirigida a este servidor, no se acepta");
            }

            // Si llego hasta aca, el token es valido
            // ToDo: asegurar el identificador para el usuario
            // Nota: se usa el remoteHost ya que todas las invocaciones a este
            // servidor provenientes del
            // mismo host se consideran que estan incluidas en el SSO
            String userDataId = "SSO_" + idGenerator.generateIdentifier() + "_" + ((new Date()).getTime());

            Subject subject = assertion.getSubject();

            if (subject == null || subject.getNameID() == null || subject.getNameID().getValue() == null) {
                    throw new CDAServiceProviderException(
                        "El mensaje enviado por el Proveedor de Identidades no contiene información sobre el usuario autenticado");
            }

            String userId = null;
            String firstName = null;
            String lastName1 = null;
            String lastname2 = null;
            for (AttributeStatement attSt : assertion.getAttributeStatements()) {
                for (Attribute att : attSt.getAttributes()) {
                    if ("uid".equalsIgnoreCase(att.getName())) {
                            XSString attVal = (XSString) att.getAttributeValues().get(0);
                            userId = attVal.getValue();
                    }
                    if ("PrimerNombre".equalsIgnoreCase(att.getName())) {
                            XSString attVal = (XSString) att.getAttributeValues().get(0);
                            firstName = attVal.getValue();
                    }
                    if ("PrimerApellido".equalsIgnoreCase(att.getName())) {
                            XSString attVal = (XSString) att.getAttributeValues().get(0);
                            lastName1 = attVal.getValue();
                    }
                    if ("SegundoApellido".equalsIgnoreCase(att.getName())) {
                            XSString attVal = (XSString) att.getAttributeValues().get(0);
                            lastname2 = attVal.getValue();
                    }
                }
            }

            String userName = " ";
            if (firstName != null) {
                    userName = userName + firstName + " ";
            }
            if (lastName1 != null) {
                    userName = userName + lastName1 + " ";
            }
            if (lastname2 != null) {
                    userName = userName + lastname2 + " ";
            }
            if (userId != null) {
                    userName = userName + "(" + userId + ")";
            }
            userName = userName.trim();
            if (userName.isEmpty()) {
                    userName = applyTransformations(userName, programData);
            }

            List<String> sessionIndexes = new ArrayList<String>();
            if (assertion.getAuthnStatements() != null && !assertion.getAuthnStatements().isEmpty()) {
                    AuthnStatement authStatement = assertion.getAuthnStatements().get(0);
                    if (authStatement.getSessionIndex() != null) {
                            sessionIndexes.add(authStatement.getSessionIndex());
                    }
            }

            userData = new UserData(userDataId, userName, exchange.getHostName(), sessionIndexes);

            if (assertion.getAttributeStatements() != null) {
                    for (AttributeStatement attStatement : assertion.getAttributeStatements()) {
                            if (attStatement.getAttributes() != null) {
                                    for (Attribute attribute : attStatement.getAttributes()) {
                                            if (attribute.getNameFormat() != null && attribute.getNameFormat().equals(Attribute.URI_REFERENCE)) {
                                                    for (XMLObject xmlo : attribute.getAttributeValues()) {
                                                            userData.getAttributes().put(attribute.getName(), getStringValueFromXMLObject(xmlo));
                                                    }
                                            }
                                    }
                            }
                    }
            }

            
            return userId;

        } catch (CDAServiceProviderException sspEx) {
                throw sspEx;
        }catch (IllegalArgumentException | IllegalStateException | KeyStoreException | DOMException ex) {
                throw new CDAServiceProviderException("Error grave procesando la solicitud: " + ex.getMessage(), ex);
        }
    }
    
    /**
    * Este método solo debería invocarse para procesar los LogoutRequest enviados por CDA cuando otra
    * aplicación desea hacer logout; 
    * @param sSamlResponse
    * @param exchange
    * @return
    * @throws CDAServiceProviderException
    */
   private void processSAMLRequest(String sSamlRequest, HttpServerExchange exchange) throws CDAServiceProviderException {
        Element element;
        try {
            byte[] bSamlRequest = Base64.decode(sSamlRequest);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(true);
            DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = null;
            //Intentar parsear la solicitud tal cual, si no se puede es probable que esté comprimida
            try {
                document = docBuilder.parse(new ByteArrayInputStream(bSamlRequest));
            }catch(SAXParseException saxEx) {
                try {
                    document = docBuilder.parse(new ByteArrayInputStream(inflate(bSamlRequest, true)));
                }catch(Exception ex) {
                    //No se comprende la respuesta
                    throw new CDAServiceProviderException("No se comprende la solicitud del Proveedor de Identidades", ex);
                }
            }
            element = document.getDocumentElement();
            UnmarshallerFactory unmarshallerFactory = Configuration.getUnmarshallerFactory();
            Unmarshaller unmarshaller = unmarshallerFactory.getUnmarshaller(element);
            XMLObject requestXmlObj = unmarshaller.unmarshall(element);
            String responseType = element.getLocalName();
            if("LogoutRequest".equals(responseType)) {

                LogoutRequest logoutRequest = (LogoutRequest)requestXmlObj;
                XMLObjectBuilderFactory builderFactory = Configuration.getBuilderFactory();
                SAMLObjectBuilder<LogoutResponse> logoutResponseBuilder = (SAMLObjectBuilder<LogoutResponse>) builderFactory.getBuilder(LogoutResponse.DEFAULT_ELEMENT_NAME);
                LogoutResponse logoutResponse = logoutResponseBuilder.buildObject();
                //Destination
                logoutResponse.setDestination(idpUrlLogout);
                //ID
                String sAssertionId = idGenerator.generateIdentifier();
                if (!sAssertionId.startsWith("_")) {
                        sAssertionId = "_" + sAssertionId;
                }
                logoutResponse.setID("AuthnRequest" + sAssertionId+ "-" + (new Date()).getTime());
                //Version
                logoutResponse.setVersion(SAMLVersion.VERSION_20);
                //IssueInstant
                logoutResponse.setIssueInstant(new DateTime());
                //InResponseTo
                logoutResponse.setInResponseTo(logoutRequest.getID());
                //Issuer
                SAMLObjectBuilder issuerBuilder = (SAMLObjectBuilder) builderFactory.getBuilder(Issuer.DEFAULT_ELEMENT_NAME);
                Issuer issuer = (Issuer) issuerBuilder.buildObject();
                issuer.setValue(providerId);
                logoutResponse.setIssuer(issuer);
                //Status
                SAMLObjectBuilder<Status> statusBuilder = (SAMLObjectBuilder<Status>) builderFactory.getBuilder(Status.DEFAULT_ELEMENT_NAME);
                Status status = statusBuilder.buildObject();
                SAMLObjectBuilder<StatusCode> statusCodeBuilder = (SAMLObjectBuilder<StatusCode>) builderFactory.getBuilder(StatusCode.DEFAULT_ELEMENT_NAME);
                StatusCode statusCode = statusCodeBuilder.buildObject();
                statusCode.setValue(StatusCode.SUCCESS_URI);
                status.setStatusCode(statusCode);
                logoutResponse.setStatus(status);

                printXMLObject("LOGOUT RESPONSE OK", logoutResponse);

                Marshaller marshaller = org.opensaml.Configuration.getMarshallerFactory().getMarshaller(logoutResponse);
                org.w3c.dom.Element node = marshaller.marshall(logoutResponse);
                StringWriter rspWrt = new StringWriter();
                XMLHelper.writeNode(node, rspWrt);
                Deflater deflater = new Deflater(9, true);
                deflater.setInput(rspWrt.toString().getBytes());
                deflater.finish();
                byte[] buf = new byte[rspWrt.toString().getBytes().length];
                int size = deflater.deflate(buf);
                deflater.end();
                String samlRequest = Base64.encodeBytes(buf, 0, size, Base64.DONT_BREAK_LINES);
                // Determinar la firma de los parámetros
                String signatureAlg = SignatureMethod.RSA_SHA1; // "http://www.w3.org/2000/09/xmldsig#rsa-sha1";
                String queryParametersToSign = "SAMLResponse=" + URLEncoder.encode(samlRequest, "UTF-8");
                queryParametersToSign = queryParametersToSign + "&SigAlg=" + URLEncoder.encode(signatureAlg, "UTF-8");
                PrivateKeyEntry pkEntry = (PrivateKeyEntry) programData.getKeyStore().getEntry(certAlias, new PasswordProtection(keystorePass.toCharArray()));
                PrivateKey pk = pkEntry.getPrivateKey();
                java.security.Signature rsa = java.security.Signature.getInstance("SHA1withRSA");
                rsa.initSign(pk);
                rsa.update(queryParametersToSign.getBytes("UTF-8"));
                String sigStringVal = Base64.encodeBytes(rsa.sign(), Base64.DONT_BREAK_LINES);
                // Armar la URL para la redirección
                String redirectUrl = idpUrlLogout;
                redirectUrl = redirectUrl + (redirectUrl.indexOf("?") < 0 ? "?" : "&");
                redirectUrl = redirectUrl + queryParametersToSign;
                redirectUrl = redirectUrl + "&Signature=" + URLEncoder.encode(sigStringVal, "UTF-8");
                
                //Invalidar la sesión
                ServletRequestContext src = exchange.getAttachment(ServletRequestContext.ATTACHMENT_KEY);
                src.getSession().invalidate();
                
                // Enviar la orden de redicción que incluye la respuesta y la firma
                exchange.setStatusCode(302);
                exchange.getResponseHeaders().put(Headers.LOCATION, redirectUrl);
                exchange.endExchange();
                
            }else {
                throw new CDAServiceProviderException("No se acepta el tipo de respuesta del Proveedor de Identidades: "+responseType+" (solo se acepta Response y LogoutResponse)");
            }
        } catch (Exception ex) {
        	logger.log(Level.WARNING, ExceptionUtils.getStackTrace(ex));
            throw new CDAServiceProviderException("Error obteniendo la respuesta del Proveedor de Identidades", ex);
        }
   }

   
    private class UserData {

        private final String userDataId;
        private final String userName;
        private final String remoteHost;
        private final Map<String, String> attributes = new HashMap<>();
        private final Map<String, Principal> principals = new HashMap<>(); // Un
        // Principal por cada aplicacion

        // Para el CDA (esto se obtiene de la response inicial)
        private final List<String> sessionIndexes;


        public UserData(String userDataId, String userName, String remoteHost, List<String> sessionIndexes) {
                this.userDataId = userDataId;
                this.userName = userName;
                this.remoteHost = remoteHost;
                this.sessionIndexes = sessionIndexes;
        }

        public String getUserDataId() {
                return userDataId;
        }

        public Map<String, String> getAttributes() {
                return attributes;
        }

        public String getRemoteHost() {
                return remoteHost;
        }

        public String getUserName() {
                return userName;
        }

        public Map<String, Principal> getPrincipals() {
                return principals;
        }

        public List<String> getSessionIndexes() {
                return sessionIndexes;
        }

    }
      
 
    private AuthnRequest buildSAMLAuthnRequest(CDAServiceProviderData programData, String sIssuer){
        try {
                DateTime ahora = new DateTime();

                SAMLObjectBuilder authnRequestBuilder = (SAMLObjectBuilder) builderFactory.getBuilder(AuthnRequest.DEFAULT_ELEMENT_NAME);

                SAMLObjectBuilder issuerBuilder = (SAMLObjectBuilder) builderFactory.getBuilder(Issuer.DEFAULT_ELEMENT_NAME);
                Issuer issuer = (Issuer) issuerBuilder.buildObject();
                issuer.setValue(sIssuer);

                String sAssertionId = idGenerator.generateIdentifier();
                if (!sAssertionId.startsWith("_")) {
                        sAssertionId = "_" + sAssertionId;
                }

                AuthnRequest authnRequest = (AuthnRequest) authnRequestBuilder.buildObject();
                authnRequest.setID("AuthnRequest" + sAssertionId + "-" + (new Date()).getTime());
                authnRequest.setVersion(SAMLVersion.VERSION_20);
                authnRequest.setIssueInstant(ahora);
                authnRequest.setIssuer(issuer);
                authnRequest.setAssertionConsumerServiceURL(spReturnUrl);
                authnRequest.setAttributeConsumingServiceIndex(0);
                authnRequest.setConsent(RequestAbstractType.OBTAINED_CONSENT);
                authnRequest.setDestination(idpUrlLogin);
                authnRequest.setForceAuthn(Boolean.FALSE);
                authnRequest.setProviderName(sIssuer);

                AudienceRestriction audienceRestriction = new AudienceRestrictionBuilder().buildObject();
                Audience issuerAudience = new AudienceBuilder().buildObject();
                issuerAudience.setAudienceURI(sIssuer);
                audienceRestriction.getAudiences().add(issuerAudience);
                Conditions conditions = new ConditionsBuilder().buildObject();
                conditions.getAudienceRestrictions().add(audienceRestriction);
                authnRequest.setConditions(conditions);

                AuthnContextClassRef authnContextClassRef = new AuthnContextClassRefBuilder().buildObject(SAMLConstants.SAML20_NS,
                    AuthnContextClassRef.DEFAULT_ELEMENT_LOCAL_NAME, "saml2");
                RequestedAuthnContext requestedAuthnContext = new RequestedAuthnContextBuilder().buildObject();
                requestedAuthnContext.getAuthnContextClassRefs().add(authnContextClassRef);
                authnRequest.setRequestedAuthnContext(requestedAuthnContext);

                // Firma
                PrivateKeyEntry pkEntry = (PrivateKeyEntry) programData.getKeyStore().getEntry(certAlias, new PasswordProtection(keystorePass.toCharArray()));
                PrivateKey pk = pkEntry.getPrivateKey();
                X509Certificate certificate = (X509Certificate) pkEntry.getCertificate();
                BasicX509Credential credential = new BasicX509Credential();
                credential.setEntityCertificate(certificate);
                credential.setPrivateKey(pk);

                SignatureBuilder signatureBuilder = (SignatureBuilder) builderFactory.getBuilder(Signature.DEFAULT_ELEMENT_NAME);
                Signature signature = (Signature) signatureBuilder.buildObject(Signature.DEFAULT_ELEMENT_NAME);
                signature.setSigningCredential(credential);

                SecurityConfiguration secConfig = Configuration.getGlobalSecurityConfiguration();
                SecurityHelper.prepareSignatureParams(signature, credential, secConfig, null);
                authnRequest.setSignature(signature);
                Marshaller marshaller = Configuration.getMarshallerFactory().getMarshaller(authnRequest);
                marshaller.marshall(authnRequest);
                Signer.signObject(signature);

                return authnRequest;
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableEntryException | MarshallingException
            | org.opensaml.xml.security.SecurityException | org.opensaml.xml.signature.SignatureException e) {
        	logger.log(Level.WARNING, ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    private void printXMLObject(String titulo, XMLObject obj) {
        try {
                Marshaller marshaller = org.opensaml.Configuration.getMarshallerFactory().getMarshaller(obj);
                org.w3c.dom.Element authDOM = marshaller.marshall(obj);
                StringWriter rspWrt = new StringWriter();
                XMLHelper.writeNode(authDOM, rspWrt);
                logger.log(Level.FINE, "================== {0} ========================", titulo);
                logger.fine(rspWrt.toString());
                logger.fine("======================================================");
        } catch (MarshallingException ex) {
        	logger.log(Level.WARNING, ExceptionUtils.getStackTrace(ex));
        }
    }


    private static byte[] inflate(byte[] bytes, boolean nowrap) throws Exception {

        Inflater decompressor = null;
        InflaterInputStream decompressorStream = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            decompressor = new Inflater(nowrap);
            decompressorStream = new InflaterInputStream(new ByteArrayInputStream(bytes), 
              decompressor);
            byte[] buf = new byte[1024];
            int count;
            while ((count = decompressorStream.read(buf)) != -1) {
                out.write(buf, 0, count);
            }
            return out.toByteArray();
        } finally {
            if (decompressor != null) {
                decompressor.end();
            }
            try {
                if (decompressorStream != null) {
                    decompressorStream.close();
                }
             } catch (IOException ioe) {
                 /*ignore*/
             }
             try {
                 if (out != null) {
                     out.close();
                 }
             } catch (IOException ioe) {
                 /*ignore*/
             }
          }
    }	
    
    /**
	 * Transforma el nombre de usuario dado segÃ±n las transformaciones definidas
	 * (esto es porque el TFIM de agesic devuelve los nombres de los usuarios en
	 * formato estandar pais-tipodocumento-numerodocumento, donde tipodocumento es
	 * un codigo estandar numerico en lugar del nombre (ci, pasaporte, etc).
	 *
	 * @param userName
	 * @return
	 */
    private String applyTransformations(String userName, CDAServiceProviderData programData) {
        if (userName == null || programData == null || programData.getUsernameTransformationsMap() == null) {
                return "";
        }

        logger.log(Level.FINE, "Nombre de usuario antes: {0}", userName);

        String repl;
        for (String orig : programData.getUsernameTransformationsMap().keySet()) {
                repl = programData.getUsernameTransformationsMap().get(orig);
                userName = userName.replace(orig, repl);
        }

        logger.log(Level.FINE, "Nombre de usuario despues: {0}", userName);

        return userName;
    }
    
    
    private String getParameter(HttpServerExchange exchange, String paramName) throws IOException {
        logger.log(Level.FINE, "getParameter " + paramName);    
        try {   
                InputStream input = exchange.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String line = new String();
                while ((line = reader.readLine()) != null) {
                    if(line.startsWith(paramName)){
                        return URLDecoder.decode(line.replace(paramName+"=",""), StandardCharsets.UTF_8.toString());  
                    }
                }
                reader.close();
                
                
        } catch (IOException ex) {
            logger.log(Level.WARNING, ex.getMessage());
        }
            
        return null;
    }

    private void initLogoutProcess(CDAServiceProviderData programData, HttpServerExchange exchange) {
        ServletRequestContext src = exchange.getAttachment(ServletRequestContext.ATTACHMENT_KEY);
        UserData userData = (UserData) src.getSession().getAttribute(USER_DATA_CACHE);
        if (userData == null) {
                logger.fine("No se encontraron datos para el usuario.");
                return;
        }

        sendRedirectPostFormLogout(programData, exchange, userData);

    }

    /**
	 * En el nuevo CDA el logout se hace mediante un REDIRECT (302 FOUND) y no
	 * mediante POST. Según el documento
	 * "Bindings for the OASIS Security Assertion Markup Language (SAML)" la
	 * aplicación debe enviar al usuario una instrucción redirect a la URL de
	 * logout del CDA (por ejemplo,
	 * https://test-eid.portal.gub.uy/idp/profile/SAML2/Redirect/SLO) con los
	 * siguientes parámetros: - SAMLRequest: solicitud de logout; debe ser de tipo
	 * LogoutRequest y NO debe estar firmada. Al momento de ponerlo como parámetro
	 * debe ser comprimido (deflated) y codificado en base64. - SigAlg: URI del
	 * algoritmo de firma (http://www.w3.org/2000/09/xmldsig#rsa-sha1) -
	 * Signature: firma de los dos parámetros anteriores; para esto deben
	 * concatenarse ambos parámetros de la siguiente manera:
	 * SAMLRequest=<valor>&SigAlg=<valor> y luego firmarse usando SHA1 y RSA o
	 * DSA.
	 * 
	 * @param programData
	 * @param httpRequest
	 * @param httpResponse
	 * @param userData
	 * @return
	 */
    private void sendRedirectPostFormLogout(CDAServiceProviderData programData, HttpServerExchange exchange,UserData userData) {
        try {

                // Armar la solicitud de logout
                LogoutRequest logoutRequest = buildSAMLLogoutRequest(programData, providerId, userData);

                printXMLObject("LOGOUTREQUEST", logoutRequest);

                Marshaller marshaller = org.opensaml.Configuration.getMarshallerFactory().getMarshaller(logoutRequest);
                org.w3c.dom.Element authDOM = marshaller.marshall(logoutRequest);

                // Comprimir el token SAML usando DEFLATE y codificarlo en base 64
                StringWriter rspWrt = new StringWriter();
                XMLHelper.writeNode(authDOM, rspWrt);
                Deflater deflater = new Deflater(9, true);
                deflater.setInput(rspWrt.toString().getBytes());
                deflater.finish();
                byte[] buf = new byte[rspWrt.toString().getBytes().length];
                int size = deflater.deflate(buf);
                deflater.end();
                String samlRequest = Base64.encodeBytes(buf, 0, size, Base64.DONT_BREAK_LINES);

                // Determinar la firma de los parámetros
                String signatureAlg = SignatureMethod.RSA_SHA1; // "http://www.w3.org/2000/09/xmldsig#rsa-sha1";
                String queryParametersToSign = "SAMLRequest=" + URLEncoder.encode(samlRequest, "UTF-8");
                queryParametersToSign = queryParametersToSign + "&SigAlg=" + URLEncoder.encode(signatureAlg, "UTF-8");
                PrivateKeyEntry pkEntry = (PrivateKeyEntry) programData.getKeyStore().getEntry(certAlias,
                    new PasswordProtection(keystorePass.toCharArray()));
                PrivateKey pk = pkEntry.getPrivateKey();
                java.security.Signature rsa = java.security.Signature.getInstance("SHA1withRSA");
                rsa.initSign(pk);
                rsa.update(queryParametersToSign.getBytes("UTF-8"));
                String sigStringVal = Base64.encodeBytes(rsa.sign(), Base64.DONT_BREAK_LINES);

                // Armar la URL para la redirección
                String redirectUrl = idpUrlLogout;
                redirectUrl = redirectUrl + (redirectUrl.indexOf("?") < 0 ? "?" : "&");
                redirectUrl = redirectUrl + queryParametersToSign;
                redirectUrl = redirectUrl + "&Signature=" + URLEncoder.encode(sigStringVal, "UTF-8");

                logger.log(Level.FINE, "URL de logout: {0}", redirectUrl);

                // Enviar la orden de redicción
                exchange.setStatusCode(302);
                exchange.getResponseHeaders().put(Headers.LOCATION, redirectUrl);
                exchange.endExchange();

        } catch (IOException | InvalidKeyException | KeyStoreException | NoSuchAlgorithmException | SignatureException
            | UnrecoverableEntryException | MarshallingException ex) {
        	logger.log(Level.WARNING, ExceptionUtils.getStackTrace(ex));
        }
    }
        
        
    private LogoutRequest buildSAMLLogoutRequest(CDAServiceProviderData programData, String sIssuer, UserData userData) {
            try {

                String sNameId = userData.getUserName();

                DateTime ahora = new DateTime();

                SAMLObjectBuilder authnRequestBuilder = (SAMLObjectBuilder) builderFactory.getBuilder(LogoutRequest.DEFAULT_ELEMENT_NAME);

                SAMLObjectBuilder issuerBuilder = (SAMLObjectBuilder) builderFactory.getBuilder(Issuer.DEFAULT_ELEMENT_NAME);
                Issuer issuer = (Issuer) issuerBuilder.buildObject();
                issuer.setValue(sIssuer);

                String sAssertionId = idGenerator.generateIdentifier();
                if (!sAssertionId.startsWith("_")) {
                        sAssertionId = "_" + sAssertionId;
                }

                SAMLObjectBuilder nameIdBuilder = (SAMLObjectBuilder) builderFactory.getBuilder(NameID.DEFAULT_ELEMENT_NAME);
                NameID nameId = (NameID) nameIdBuilder.buildObject();
                nameId.setFormat(NameIdentifier.EMAIL);
                nameId.setValue(sNameId);

                LogoutRequest logoutRequest = (LogoutRequest) authnRequestBuilder.buildObject();
                logoutRequest.setDestination(idpUrlLogout);
                logoutRequest
                    .setID("LogoutRequest" + sAssertionId + "-" + (new Date()).getTime() /* "Assertion-uuid7989a210-0141-1ae2-b4c4-c02d0ee4a0a3" */);
                logoutRequest.setVersion(SAMLVersion.VERSION_20);
                logoutRequest.setIssueInstant(ahora);
                logoutRequest.setIssuer(issuer);
                logoutRequest.setNameID(nameId);

                if (userData.getSessionIndexes() != null) {
                    for (String sSessionIndex : userData.getSessionIndexes()) {
                            SessionIndex sessionIndex = new SessionIndexBuilder().buildObject();
                            sessionIndex.setSessionIndex(sSessionIndex);
                            logoutRequest.getSessionIndexes().add(sessionIndex);
                    }
                }

                return logoutRequest;
            } catch (Exception e) {
            	logger.log(Level.WARNING, ExceptionUtils.getStackTrace(e));
            }
            return null;
	}


    public static String getStringValueFromXMLObject(XMLObject xmlObj) {
            if (xmlObj instanceof XSString) {
                    return ((XSString) xmlObj).getValue();
            } else if (xmlObj instanceof XSAny) {
                    return ((XSAny) xmlObj).getTextContent();
            }
            return null;
    }



}
