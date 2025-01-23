/*
 * SAE - Sistema de Agenda Electrónica
 * Copyright (C) 2009  IMM - Intendencia Municipal de Montevideo
 *
 * This file is part of SAE.

 * SAE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package uy.com.sofis.web.seguridad;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import uy.com.sofis.business.services.RelUsuarioOrganismoBusinessBean;
import uy.com.sofis.entities.UsuarioOrganismoDTO;
import uy.com.sofis.exceptions.GeneralException;
import uy.com.sofis.filtros.FiltroUsuario;

@Named
@SessionScoped
public class SessionMBean implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String MSG_ID = "pantalla";

	private static final Logger LOGGER = Logger.getLogger(SessionMBean.class.getName());

	// Booleana para saber si el usuario es administrador del Organismo Actual
	private Boolean adminAlgunOrganismo = Boolean.FALSE;
	private List<String> rolesPorEmpresa;

	@Inject
	GestionSessionMB gestionSessionMB;

	@Inject
	RelUsuarioOrganismoBusinessBean relUsuarioOrganismo;

	@PostConstruct
	public void postConstruct() {
		// Se cargan los datos del usuario
		cargarDatosUsuario();
	}

	public void cargarDatosUsuario() {
		LOGGER.log(Level.INFO, "cargar datos ");
		try {
			HttpServletRequest request = gestionSessionMB.sessionFixationPrevention();
			if (gestionSessionMB.getUsuarioActual() == null) {
				// No esta definido el usuario actual, se carga ahora
				Map<String, Object> sessionAttrs = FacesContext.getCurrentInstance().getExternalContext()
						.getSessionMap();
				gestionSessionMB.autenticarUsuarioActual(request, sessionAttrs);

				String codigo = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
				// Cargar organismos del usuario
				if (codigo != null) {
					cargarOrganismosDelUsuarioYChequearSiEsAdmin(codigo);
					cargarRoles();
				} else {
					gestionSessionMB.cerrarSesion();
				}
			}
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "Error al  cargar datos del usuario", ex);
		}
	}

	private void cargarOrganismosDelUsuarioYChequearSiEsAdmin(String codigo) {
		try {
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.setCodigo(codigo);
			List<UsuarioOrganismoDTO> usuarioOrganismos = relUsuarioOrganismo.buscarUsuariosOrganismos(filtroUsuario);
			for (UsuarioOrganismoDTO usuarioOrganismo : usuarioOrganismos) {
				if (usuarioOrganismo.getAdministrador().booleanValue()) {
					adminAlgunOrganismo = Boolean.TRUE;
					return;
				}
			}
		} catch (GeneralException aEx) {
			LOGGER.log(Level.SEVERE, "Error al  cargar datos del usuario", aEx);
		}
	}

	private void cargarRoles() {
		try {
			if (gestionSessionMB.getUsuarioActual() != null) {
				rolesPorEmpresa = relUsuarioOrganismo
						.obtenerRolesUsuarioTodasLasEmpresas(gestionSessionMB.getUsuarioActual().getId());
			}
		} catch (GeneralException ex) {
			LOGGER.log(Level.SEVERE, "No se pudo cargar los roles del usuario en las empresas", ex);
		}
	}

	public boolean tieneRol(String rol) {
		return tieneRoles(new String[] { rol });
	}

	public boolean tieneRoles(String[] roles) {
		if (gestionSessionMB.getUsuarioActual() == null) {
			return false;
		}

		if (adminAlgunOrganismo != null && adminAlgunOrganismo.booleanValue()) {
			return true;
		}

		// Si la lista es vacía (no nula) es porque no se requiere ningún rol
		if (roles != null && roles.length == 0) {
			return true;
		}

		// Verificar si tiene el rol a nivel global
		if ((rolesPorEmpresa != null && !rolesPorEmpresa.isEmpty()) && (roles != null && roles.length > 0)) {
			for (String rol : roles) {
				if (rolesPorEmpresa.contains(rol)) {
					return true;
				}
			}
		}

		return false;
	}

}
