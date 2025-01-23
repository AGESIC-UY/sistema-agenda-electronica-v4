package uy.com.sofis.web.seguridad;

import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.security.SecurityContext;
import org.jboss.security.SecurityContextAssociation;
import org.jboss.security.SecurityContextFactory;

import uy.com.sofis.business.services.ConfiguracionBusinessBean;
import uy.com.sofis.business.services.UsuarioBusinessBean;
import uy.com.sofis.constantes.ConstantesConfiguracion;
import uy.com.sofis.entities.Usuario;
import uy.com.sofis.web.mb.InicioManagedBean;
import uy.com.sofis.web.utils.JSFUtils;

/**
 *
 * @author Sofis Solutions
 */
@Named
@SessionScoped
public class GestionSessionMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(GestionSessionMB.class.getName());

	@Inject
	private ConfiguracionBusinessBean csb;
	@Inject
	UsuarioBusinessBean usuarioBusinessBean;
	@Inject
	InicioManagedBean inicioManagedBean;

	private Locale locale;

	private String piePagina = "";
	private String nombreAplicacion;
	private String ssoBaseUrl = null;
	private Usuario usuarioActual;

	private enum TipoLogin {
		LOCAL, CDA, CAS, IDURUGUAY, ARCHIVO
	};

	private TipoLogin tipoLogin;

	/**
	 * Creates a new instance of GestionSessionMB
	 */
	public GestionSessionMB() {
		// Do Nothing
	}

	@PostConstruct
	public void init() {
		locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		piePagina = ConstantesConfiguracion.CODIGO_PIE_PAGINA;
		nombreAplicacion = ConstantesConfiguracion.NOMBRE_SISTEMA;
	}

	public String logout() {
		if (TipoLogin.ARCHIVO.equals(tipoLogin)) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			session.invalidate();
			JSFUtils.redirectToIndex();
		} else {
			cerrarSesion();
		}
		return null;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getLanguage() {
		return locale.getLanguage();
	}

	public void setLanguage(String language) {
		locale = new Locale(language);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}

	public String getPiePagina() {
		return piePagina;
	}

	public String getNombreAplicacion() {
		return nombreAplicacion;
	}

	public String irAInicio() {
		return "IR_A_INICIO";
	}

	public HttpServletRequest sessionFixationPrevention() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession session = request.getSession();
		// Esto es para prevenir el ataque de Session Fixation: cada vez que se pide
		// recargar los datos del usuario se
		// genera un id de sesión nuevo, y se copian todos los datos que estaban de la
		// anterior
		// 1-Almacenar en un map temporal todas las propiedades de la sesión actual
		Map<String, Object> map0 = new HashMap<>();
		Enumeration<String> en = session.getAttributeNames();
		while (en.hasMoreElements()) {
			String attNombre = en.nextElement();
			map0.put(attNombre, session.getAttribute(attNombre));
		}
		// 2-Invalidar la sesión actual
		session.invalidate();
		// 3-Crear una nueva sesión
		session = request.getSession(true);
		// 4-Poner en la sesión nueva los datos de la sesión anterior
		for (String nombre : map0.keySet()) {
			session.setAttribute(nombre, map0.get(nombre));
		}
		return request;
	}

	public void cerrarSesion() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		if (tipoLogin == null) {
			cerrarSesionLocal();
		} else {
			switch (tipoLogin) {
			case CAS:
				cerrarSesionCAS();
				break;
			case LOCAL:
				cerrarSesionLocal();
				break;
			case IDURUGUAY:
				cerrarSesionIDURUGUAY();
				break;
			case CDA:
			default:
				ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
				try {
					ectx.redirect(request.getContextPath() + "/logoutcda");
				} catch (IOException e) {
					LOGGER.log(Level.SEVERE, "Error al  intentar la sesion en CDA", e);
				}
				break;
			}
		}
	}

	private void cerrarSesionLocal() {
		// Si la autenticación fue con la válvula de CDA se invoca al logout de CDA,
		// sino se hace logout local
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
		try {
			request.logout();
		} catch (ServletException ex) {
			LOGGER.log(Level.SEVERE, "Error al cerrar la sesión del usuario, pero de todas maneras se invalida", ex);
		}
		fc.getExternalContext().invalidateSession();
		JSFUtils.redirectToIndex();
	}

	private void cerrarSesionCAS() {
		if (!TipoLogin.CAS.equals(tipoLogin)) {
			return;
		}
		try {
			String casLogoutURL = System.getProperty("cas.server.url");
			if (casLogoutURL != null) {
				casLogoutURL = casLogoutURL + "/logout";
				ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
				ectx.invalidateSession();
				ectx.redirect(casLogoutURL);
			}
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "Error al cerrar la sesión de CAS", ex);
		}
	}

	private void cerrarSesionIDURUGUAY() {
		if (!TipoLogin.IDURUGUAY.equals(tipoLogin)) {
			return;
		}
		try {
			Context initContext = new InitialContext();
			String urlLogout = (String) initContext.lookup("java:global/iduruguayhttphandler/ssoRedirectUrl");
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
			HttpSession sesion = (HttpSession) ctx.getSession(false);
			String idToken = (String) sesion.getAttribute("IDURUGUAY_ID_TOKEN");
			String ssoBseUrl = getSsoBaseUrl();
			String logoutUrl = ssoBseUrl + "logout?id_token_hint=" + idToken + "&post_logout_redirect_uri="
					+ URLEncoder.encode(urlLogout, "UTF-8");
			ctx.redirect(logoutUrl);
			sesion.invalidate();
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "Error al cerrar la sesión de IDURUGUAY", ex);
		}
	}

	public String getSsoBaseUrl() {
		if (ssoBaseUrl == null) {
			Context initContext = null;
			try {
				initContext = new InitialContext();
				ssoBaseUrl = (String) initContext.lookup("java:global/iduruguayhttphandler/ssoBaseUrl");
			} catch (NamingException ex) {
				LOGGER.log(Level.SEVERE, null, ex);
			} finally {
				try {
					initContext.close();
				} catch (NamingException ex) {
					LOGGER.log(Level.SEVERE, null, ex);
				}
			}
		}
		return ssoBaseUrl;
	}

	// Genera un password de uso único
	private String hacerLoginAutomatico(HttpServletRequest request, String codigo, Integer empresaId)
			throws NamingException, ServletException {
		// Cerrar la sesión del usuario
		request.logout();
		// Cargar los datos en el contexto JNDI para que el AutoLoginModule tome la
		// contraseña de uso único para el usuario
		InitialContext ctx = new InitialContext();
		String password = UUID.randomUUID().toString();
		String sessionId = request.getSession().getId();
		try {
			ctx.bind("{LOGIN}{" + sessionId + "}", password);
		} catch (NamingException nabEx) {
			ctx.rebind("{LOGIN}{" + sessionId + "}", password);
		}

		// Hacer el login
		if (empresaId != null) {
			request.login(sessionId + "/" + codigo + "/" + empresaId, password);
		} else {
			request.login(sessionId + "/" + codigo, password);
		}
		return password;
	}

	public void autenticarUsuarioActual(HttpServletRequest request, Map<String, Object> sessionAttrs) {
		// Si está en la sesión el atributo "_const_cas_assertion_" es porque viene de
		// CAS
		// Se utiliza como código de usuario el atributo remoteUser y hay que
		// autenticarlo programáticamente (se genera una contraseña encriptado el
		// código de usuario con la clave privada del certificado configurado para CAS)
		if (sessionAttrs.containsKey("IDURUGUAY_ID_TOKEN")) {
			autenticarUsuarioActualDeIdUruguay(request);
		} else if (sessionAttrs.containsKey("_const_cas_assertion_")) {
			autenticarUsuarioDeCAS(request);
		} else if (sessionAttrs.containsKey("IDURUGUAY_SAML")) {

			// Si está en la sesión el atributo "codigocda" se utiliza como código de
			// usuario el atributo "documentocda",
			// sino se utiliza el atributo remoteUser; además, si viene de CDA hay que
			// autenticarlo programáticamente (la
			// contraseña usada es la encriptacion con RSA y la clave privada del
			// certificado usado por la válvula, se debe
			// verificar con la clave pública del mismo certificado)
			// Nota: la válvula pone valores como los siguientes: codigocda=uy-ci-88888889,
			// documentocda=88888889
			autenticarUsuarioDeIDUruguaySAML(request);
		}

		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		// Cargar organismos del usuario
		if (codigo == null) {
			LOGGER.log(Level.SEVERE, "tipoLogin", "TipoDeLogin ARCHIVO");
			codigo = inicioManagedBean.getNombreUsuario();
			tipoLogin = TipoLogin.ARCHIVO;
		}
		if (codigo != null) {
			usuarioActual = usuarioBusinessBean.obtenerUsuarioPorCodigo(codigo);
		}
	}

	private void autenticarUsuarioDeCAS(HttpServletRequest request) {
		String codigoCas = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		if (codigoCas != null) {
			tipoLogin = TipoLogin.CAS;
			try {
				if (request.getUserPrincipal() != null) {
					// Hay que hacer un logout del usuario de CAS para poder hacer el login de SAE
					// (cambia la clase de Principal)
					request.logout();
				}
				SecurityContext secContext = SecurityContextFactory.createSecurityContext("SDSAE");
				SecurityContextAssociation.setSecurityContext(secContext);
				hacerLoginAutomatico(request, codigoCas, null);
				Principal principal = request.getUserPrincipal();
				SecurityContextAssociation.setPrincipal(principal);
			} catch (Exception ex) {
				LOGGER.log(Level.SEVERE, "NO se pudo loguear al usuario desde CAS", ex);
				// No se pudo loguear al usuario
				// Se crea un usuario artificial solo para poder ofrecer el link "logout" si es
				// de CDA
				usuarioActual = new Usuario();
				usuarioActual.setCodigo(codigoCas);
				usuarioActual.setNombre(codigoCas);
				FacesContext fCtx = FacesContext.getCurrentInstance();
				fCtx.getApplication().getNavigationHandler().handleNavigation(fCtx, "", "noAutorizado");
				return;
			}
		} else {
			// Viene de cas pero no hay un usuario autenticado
			LOGGER.log(Level.INFO, "[cargarDatosUsuario] 5a ");
			cerrarSesion();
		}
	}

	private void autenticarUsuarioActualDeIdUruguay(HttpServletRequest request) {
		String codigoIdUruguay = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		if (codigoIdUruguay != null) {
			tipoLogin = TipoLogin.IDURUGUAY;
			try {
				if (request.getUserPrincipal() != null) {
					// Hay que hacer un logout del usuario de CAS para poder hacer el login de SAE
					// (cambia la clase de Principal)
					request.logout();
				}
				// hacerLoginAutomatico(request, codigoIdUruguay, null);
				Principal principal = request.getUserPrincipal();
				SecurityContextAssociation.setPrincipal(principal);
			} catch (Exception ex) {
				LOGGER.log(Level.SEVERE, "NO se pudo loguear al usuario desde IDURUGUAY", ex);
				// No se pudo loguear al usuario
				// Se crea un usuario artificial solo para poder ofrecer el link "logout" si es
				// de IDURUGUAY
				usuarioActual = new Usuario();
				usuarioActual.setCodigo(codigoIdUruguay);
				usuarioActual.setNombre(codigoIdUruguay);
				FacesContext fCtx = FacesContext.getCurrentInstance();
				fCtx.getApplication().getNavigationHandler().handleNavigation(fCtx, "", "noAutorizado");
				return;
			}
		} else {
			// Viene de cas pero no hay un usuario autenticado
			LOGGER.log(Level.INFO, "[cargarDatosUsuario] 5a ");
			cerrarSesion();
		}
	}

	private void autenticarUsuarioDeIDUruguaySAML(HttpServletRequest request) {
		String codigoCDA = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		if (codigoCDA != null) {
			// Viene de la válvula de CDA
			tipoLogin = TipoLogin.CDA;
			try {
				if (request.getUserPrincipal() != null) {
					// Hay que hacer un logout del usuario de IDURUGUAY para poder hacer el login de
					// SAE (cambia la clase de Principal)
					request.logout();
				}
				hacerLoginAutomatico(request, codigoCDA, null);
				Principal principal = request.getUserPrincipal();
				SecurityContextAssociation.setPrincipal(principal);

			} catch (Exception ex) {
				// No se pudo loguear al usuario
				// Se crea un usuario artificial solo para poder ofrecer el link "logout" si es
				// de CDA
				usuarioActual = new Usuario();
				usuarioActual.setCodigo(codigoCDA);
				usuarioActual.setNombre(codigoCDA);
				FacesContext ctx = FacesContext.getCurrentInstance();
				ctx.getApplication().getNavigationHandler().handleNavigation(ctx, "", "noAutorizado");
				return;
			}
		} else {
			// Viene de cas pero no hay un usuario autenticado
			LOGGER.log(Level.INFO, "[cargarDatosUsuario] 5a ");
			cerrarSesion();
		}
	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(Usuario usuarioActual) {
		this.usuarioActual = usuarioActual;
	}

}
