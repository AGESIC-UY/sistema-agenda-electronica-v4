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
package uy.gub.imm.sae.web.mbean.administracion;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
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

    private static final Map<String, String[]> PAGINAS_ROLES = new HashMap<>();

    @PostConstruct
    public void init() {
        try {
            InputStream is = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/META-INF/MANIFEST.MF");
            Manifest manifest = new Manifest();
            manifest.read(is);
            Attributes atts = manifest.getMainAttributes();
            version = atts.getValue("Implementation-Version");
            is.close();

            mapearPaginasRoles();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error al inicializar la aplicación", ex);
        }
    }

    public String getVersion() {
        return version;
    }

    public String[] getPermisosRequeridos(String path) {
        if (!PAGINAS_ROLES.containsKey(path)) {
            //return new String[]{"RA_AE_ADMINISTRADOR"};
            return new String[]{};
        }
        return PAGINAS_ROLES.get(path);
    }

    /*
        Asocia a cada página la lista de roles que permiten el acceso a ella (basta con tener uno para poder acceder).
        Casos especiales:
            vacío -> Todos pueden acceder
     */
    private void mapearPaginasRoles() {
        PAGINAS_ROLES.put("/administracion/inicio.xhtml", new String[]{});

        PAGINAS_ROLES.put("/administracion/seleccionAgendaRecurso.xhtml", new String[]{});

        PAGINAS_ROLES.put("/administracion/configuracion/configuracion.xhtml", new String[]{"RA_AE_ADMINISTRADOR"});

        PAGINAS_ROLES.put("/administracion/empresa/consultar.xhtml", new String[]{"RA_AE_ADMINISTRADOR"});
        PAGINAS_ROLES.put("/administracion/empresa/editar.xhtml", new String[]{"RA_AE_ADMINISTRADOR"});

        PAGINAS_ROLES.put("/administracion/usuario/consultar.xhtml", new String[]{"RA_AE_ADMINISTRADOR"});
        PAGINAS_ROLES.put("/administracion/usuario/editar.xhtml", new String[]{"RA_AE_ADMINISTRADOR"});
        PAGINAS_ROLES.put("/administracion/usuario/tokens.xhtml", new String[]{"RA_AE_ADMINISTRADOR"});
        PAGINAS_ROLES.put("/administracion/usuario/consultar_por_empresa.xhtml", new String[]{"RA_AE_ADMINISTRADOR"});

        PAGINAS_ROLES.put("/administracion/agenda/crear.xhtml", new String[]{"RA_AE_ADMINISTRADOR"});
        PAGINAS_ROLES.put("/administracion/agenda/modificar.xhtml", new String[]{"RA_AE_ADMINISTRADOR"});
        PAGINAS_ROLES.put("/administracion/agenda/modificarConsultar.xhtml", new String[]{"RA_AE_ADMINISTRADOR"});
        PAGINAS_ROLES.put("/administracion/agenda/modificarTextos.xhtml", new String[]{"RA_AE_ADMINISTRADOR"});

        PAGINAS_ROLES.put("/administracion/recurso/crear.xhtml", new String[]{"RA_AE_ADMINISTRADOR"});
        PAGINAS_ROLES.put("/administracion/recurso/importar.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
        PAGINAS_ROLES.put("/administracion/recurso/modificar.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
        PAGINAS_ROLES.put("/administracion/recurso/modificarConsultar.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
        PAGINAS_ROLES.put("/administracion/recurso/modificarTextos.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});

        PAGINAS_ROLES.put("/administracion/datoASolicitar/crear.xhtml", new String[]{"RA_AE_ADMINISTRADOR"});
        PAGINAS_ROLES.put("/administracion/datoASolicitar/disenioFormulario.xhtml", new String[]{"RA_AE_ADMINISTRADOR"});
        PAGINAS_ROLES.put("/administracion/datoASolicitar/modificar.xhtml", new String[]{"RA_AE_ADMINISTRADOR"});
        PAGINAS_ROLES.put("/administracion/datoASolicitar/modificarAgrupaciones.xhtml", new String[]{"RA_AE_ADMINISTRADOR"});
        PAGINAS_ROLES.put("/administracion/datoASolicitar/modificarConsultar.xhtml", new String[]{"RA_AE_ADMINISTRADOR"});
        PAGINAS_ROLES.put("/administracion/datoASolicitar/verDisenioFormulario.xhtml", new String[]{"RA_AE_ADMINISTRADOR"});

        PAGINAS_ROLES.put("/administracion/disponibilidad/consultarDia.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_PLANIFICADOR", "RA_AE_PLANIFICADOR_X_RECURSO", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
        PAGINAS_ROLES.put("/administracion/disponibilidad/consultarDisponibilidades.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_PLANIFICADOR", "RA_AE_PLANIFICADOR_X_RECURSO", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
        PAGINAS_ROLES.put("/administracion/disponibilidad/crearDisponibilidades.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_PLANIFICADOR", "RA_AE_PLANIFICADOR_X_RECURSO", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
        PAGINAS_ROLES.put("/administracion/disponibilidad/eliminarDisponibilidadesPeriodo.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_PLANIFICADOR", "RA_AE_PLANIFICADOR_X_RECURSO", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
        PAGINAS_ROLES.put("/administracion/disponibilidad/eliminarDisponibilidadesSemana.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_PLANIFICADOR", "RA_AE_PLANIFICADOR_X_RECURSO", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
        PAGINAS_ROLES.put("/administracion/disponibilidad/generarPatronDia.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_PLANIFICADOR", "RA_AE_PLANIFICADOR_X_RECURSO", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
        PAGINAS_ROLES.put("/administracion/disponibilidad/modificarCupo.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_PLANIFICADOR", "RA_AE_PLANIFICADOR_X_RECURSO", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});

        PAGINAS_ROLES.put("/administracion/acciones/asociacion.xhtml", new String[]{"RA_AE_ADMINISTRADOR"});
        PAGINAS_ROLES.put("/administracion/acciones/mantenimiento.xhtml", new String[]{"RA_AE_ADMINISTRADOR"});

        PAGINAS_ROLES.put("/administracion/validaciones/asociacion.xhtml", new String[]{"RA_AE_ADMINISTRADOR"});
        PAGINAS_ROLES.put("/administracion/validaciones/mantenimiento.xhtml", new String[]{"RA_AE_ADMINISTRADOR"});

        PAGINAS_ROLES.put("/administracion/reserva/Paso.template.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_FCALL_CENTER", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
        PAGINAS_ROLES.put("/administracion/reserva/Paso1.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_FCALL_CENTER", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
        PAGINAS_ROLES.put("/administracion/reserva/Paso2.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_FCALL_CENTER", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
        PAGINAS_ROLES.put("/administracion/reserva/Paso3.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_FCALL_CENTER", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
        PAGINAS_ROLES.put("/administracion/reserva/PasoFinal.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_FCALL_CENTER", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
        PAGINAS_ROLES.put("/administracion/reserva/atencionPresencial.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_FCALL_CENTER", "RA_AE_FATENCION", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
        PAGINAS_ROLES.put("/administracion/reserva/cancelarReserva.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_FCALL_CENTER", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
        PAGINAS_ROLES.put("/administracion/reserva/cancelarReservasPeriodo.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_PLANIFICADOR", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});

        PAGINAS_ROLES.put("/administracion/llamador/configurarLlamador.xhtml", new String[]{"RA_AE_LLAMADOR", "RA_AE_ADMINISTRADOR_DE_RECURSOS","RA_AE_ADMINISTRADOR"});
        PAGINAS_ROLES.put("/administracion/llamador/listaDeEspera.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_FATENCION", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
        PAGINAS_ROLES.put("/administracion/llamador/listaDeLlamadasExterno.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_LLAMADOR", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
        PAGINAS_ROLES.put("/administracion/llamador/listaDeLlamadasInterno.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_LLAMADOR", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});

        PAGINAS_ROLES.put("/administracion/consulta/consultarAsistenciaPeriodo.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_FCALL_CENTER", "AE_R_GENERADORREPORTES_X_RECURSO", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
        PAGINAS_ROLES.put("/administracion/consulta/consultarAtencionFuncPeriodo.xhtml", new String[]{"RA_AE_ADMINISTRADOR"});
        PAGINAS_ROLES.put("/administracion/consulta/consultarAtencionPresencialPeriodo.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "AE_R_GENERADORREPORTES_X_RECURSO"});
        PAGINAS_ROLES.put("/administracion/consulta/consultarCancelaciones.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_FCALL_CENTER", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
        PAGINAS_ROLES.put("/administracion/consulta/consultarReservaDatos.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_FCALL_CENTER", "AE_R_GENERADORREPORTES_X_RECURSO", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
        PAGINAS_ROLES.put("/administracion/consulta/consultarReservaId.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_FCALL_CENTER", "AE_R_GENERADORREPORTES_X_RECURSO", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
        PAGINAS_ROLES.put("/administracion/consulta/consultarReservaNumero.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_FCALL_CENTER", "AE_R_GENERADORREPORTES_X_RECURSO", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
        PAGINAS_ROLES.put("/administracion/consulta/consultarReservaPeriodo.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_FCALL_CENTER", "AE_R_GENERADORREPORTES_X_RECURSO", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
        PAGINAS_ROLES.put("/administracion/consulta/consultarTiempoAtencionFun.xhtml", new String[]{"RA_AE_ADMINISTRADOR"});
        PAGINAS_ROLES.put("/administracion/consulta/detalleReserva.xhtml", new String[]{"RA_AE_ADMINISTRADOR", "RA_AE_FCALL_CENTER", "AE_R_GENERADORREPORTES_X_RECURSO", "RA_AE_ADMINISTRADOR_DE_RECURSOS"});
    }

}
