/*
 * SAE - Sistema de Agenda Electronica
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
package uy.gub.imm.sae.web.mbean.reserva;

import java.io.InputStream;
import java.util.Iterator;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
@ApplicationScoped
public class ApplicationMBean {

    private static final Logger LOGGER = Logger.getLogger(ApplicationMBean.class.getName());
    
    private static String version = "x.x.x";

    @PostConstruct
    public void init() {
        try {
            InputStream is = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/META-INF/MANIFEST.MF");
            Manifest manifest = new Manifest();
            manifest.read(is);
            Attributes atts = manifest.getMainAttributes();
            version = atts.getValue("Implementation-Version");
            is.close();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error al inicializar la aplicación", ex);
        } 
    }

    public String getVersion() {
        return version;
    }

}
