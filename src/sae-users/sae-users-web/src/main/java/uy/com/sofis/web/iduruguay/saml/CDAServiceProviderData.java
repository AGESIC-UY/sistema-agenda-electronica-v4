/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.sofis.web.iduruguay.saml;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.exception.ExceptionUtils;

/**
 *
 * @author spio
 */
public class CDAServiceProviderData implements Serializable {

	private static final long serialVersionUID = 1L;

	//private static CDAServiceProviderData instance = null;
	private final Logger logger = Logger.getLogger("com.sofis.agesic.sae.cda");
	private boolean configured = false;
	private KeyStore keyStore = null;
	private KeyStore trustStore = null;
	private List<String> defaultRolesList;
	private Map<String, String> attributeMappingsMap; //<aplicacion.parametroidp, parametroapp>
	private Map<String, Map<String, String>> provisioningConfigsMap; //<aplicacion, <parametro, valor>>
	private Map<String, String> usernameTransformationsMap; //<original, remplazo>

	public CDAServiceProviderData(String keystorePath, String keystorePass,
		  String truststorePath, String truststorePass,
		  String attributeMappings, String provisioningConfigs,
		  String usernameTransformations) {
		try {

			logger.fine("Inicializando configuracion...");
			logger.log(Level.FINE, "keystorePath={0}", keystorePath);
			logger.fine("keystorePass=" + "***");
			logger.log(Level.FINE, "truststorePath={0}", truststorePath);
			logger.fine("truststorePass=" + "***");
			logger.log(Level.FINE, "attributeMappings={0}", attributeMappings);
			logger.log(Level.FINE, "provisioningConfigs={0}", provisioningConfigs);

			logger.fine("Cargando keystore...");
			keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			FileInputStream fis = new FileInputStream(keystorePath);
			keyStore.load(fis, keystorePass.toCharArray());
			fis.close();
			logger.fine("Keystore cargado correctamente.");

			logger.fine("Cargando truststore...");
			trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			fis = new FileInputStream(truststorePath);
			trustStore.load(fis, truststorePass.toCharArray());
			fis.close();
			logger.fine("Truststore cargado correctamente.");

			

			logger.fine("Cargando mapeos de atributos...");
			attributeMappingsMap = new HashMap<>();
			if (attributeMappings != null) {
				String[] ams = attributeMappings.split(",");
				for (String am : ams) {
					am = am.trim();
					if (!am.isEmpty()) {
						String[] am2 = am.split("=");
						if (am2 != null && am2.length == 2) {
							attributeMappingsMap.put(am2[0].trim(), am2[1].trim());
						}
					}
				}
			}
			logger.fine("Mapeos de atributos cargados correctamente.");

			logger.fine("Cargando datos de aprovisionamiento...");
			provisioningConfigsMap = new HashMap<>();
			String appName;
			String filePath;
			if (provisioningConfigs != null) {
				String[] pcs = provisioningConfigs.split(",");
				for (String pc : pcs) {
					String[] pc1 = pc.split("=");
					if (pc1.length == 2) {
						appName = pc1[0].trim();
						filePath = pc1[1].trim();
						provisioningConfigsMap.put(appName, loadProvisioningConfig(filePath));
					}
				}
			}
			logger.fine("Datos de aprovisionamiento cargados correctamente.");

			

			logger.fine("Cargando transformaciones de nombre de usuario...");
			usernameTransformationsMap = new HashMap<>();
			if (usernameTransformations != null) {
				String[] unTransfs = usernameTransformations.split(",");
				String orig;
				String repl;
				for (String unTransf : unTransfs) {
					String[] unt = unTransf.split("=");
					if (unt.length == 2) {
						orig = unt[0].trim();
						repl = unt[1].trim();
						usernameTransformationsMap.put(orig, repl);
					}
				}
			}
			logger.fine("Transformaciones de nombre de usuario cargadas correctamente.");

			configured = true;
		} catch (Exception ex) {
			logger.log(Level.WARNING, ExceptionUtils.getStackTrace(ex));
			configured = false;
		}
	}

	public void initRoles(String defaultRoles) {
		defaultRolesList = new ArrayList<>();
		if (defaultRoles != null) {
			String[] defRoles = defaultRoles.split(",");
			for (String defRole : defRoles) {
				if (!defRole.trim().isEmpty()) {
					defaultRolesList.add(defRole.trim());
				}
			}
		}
	}

//	public static CDAServiceProviderData getInstance(String keystorePath, String keystorePass,
//		  String truststorePath, String truststorePass, String restrictedUrls, String excludedUrls,
//		  String attributeMappings, String provisioningConfigs, String defaultRoles,
//		  String usernameTransformations) {
//		if (instance == null) {
//			instance = new CDAServiceProviderData(keystorePath, keystorePass, truststorePath,
//				  truststorePass, restrictedUrls, excludedUrls,
//				  attributeMappings, provisioningConfigs, defaultRoles, usernameTransformations);
//		}
//		return instance;
//	}

	public boolean isConfigured() {
		return configured;
	}

	public KeyStore getKeyStore() {
		return keyStore;
	}

	public KeyStore getTrustStore() {
		return trustStore;
	}


	public Map<String, String> getAttributeMappingsMap() {
		return attributeMappingsMap;
	}

	public Map<String, Map<String, String>> getProvisioningConfigsMap() {
		return provisioningConfigsMap;
	}

	public void setProvisioningConfigsMap(Map<String, Map<String, String>> provisioningConfigsMap) {
		this.provisioningConfigsMap = provisioningConfigsMap;
	}

	public List<String> getDefaultRolesList() {
		return defaultRolesList;
	}


	public Map<String, String> getUsernameTransformationsMap() {
		return usernameTransformationsMap;
	}

	private Map<String, String> loadProvisioningConfig(String filePath) throws Exception {

		logger.log(Level.FINE, "Obteniendo configuració3n de aprovisionamiento a partir del archivo {0}", filePath);

		Map<String, String> map = new HashMap<>();

		String folderPath = filePath.substring(0, filePath.lastIndexOf(File.separator));
		String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1, filePath.lastIndexOf("."));

		File file = new File(folderPath);

		if (!(file.exists() && file.canRead())) {
			throw new Exception("No se puede acceder al archivo " + filePath + ".");
		}

		URL[] urls = {file.toURI().toURL()};
		ClassLoader loader = new URLClassLoader(urls);
		ResourceBundle conf = ResourceBundle.getBundle(fileName, Locale.getDefault(), loader);

		Enumeration<String> keys = conf.getKeys();
		String key;
		while (keys.hasMoreElements()) {
			key = keys.nextElement();
			logger.log(Level.FINE, "Leida propiedad: {0} => {1}", new Object[]{key, conf.getString(key)});
			map.put(key, conf.getString(key));
		}

		return map;
	}
}
