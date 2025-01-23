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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.context.RequestContext;

import uy.gub.imm.opencsv.ext.entity.CommonLabelValueImpl;
import uy.gub.imm.opencsv.ext.entity.LabelValue;
import uy.gub.imm.opencsv.ext.entity.TableCellValue;
import uy.gub.imm.opencsv.ext.file.StandardCSVFile;
import uy.gub.imm.opencsv.ext.printer.CSVWebFilePrinter;
import uy.gub.imm.sae.business.dto.UsuarioEmpresaDTO;
import uy.gub.imm.sae.business.dto.UsuarioEmpresaRoles;
import uy.gub.imm.sae.business.dto.UsuarioRolesRecursoDTO;
import uy.gub.imm.sae.business.ejb.facade.RecursosLocal;
import uy.gub.imm.sae.business.ejb.facade.UsuariosEmpresasLocal;
import uy.gub.imm.sae.common.Utiles;
import uy.gub.imm.sae.entity.Agenda;
import uy.gub.imm.sae.entity.AgrupacionDato;
import uy.gub.imm.sae.entity.Recurso;
import uy.gub.imm.sae.entity.RolesUsuarioRecurso;
import uy.gub.imm.sae.entity.global.Empresa;
import uy.gub.imm.sae.entity.global.UnidadEjecutora;
import uy.gub.imm.sae.entity.global.Usuario;
import uy.gub.imm.sae.exception.ApplicationException;
import uy.gub.imm.sae.exception.UserException;
import uy.gub.imm.sae.web.common.Row;
import uy.gub.imm.sae.web.common.RowList;

@ManagedBean
@ViewScoped
public class EmpresaMBean extends BaseMBean {

    private static final Logger LOGGER = Logger.getLogger(EmpresaMBean.class.getName());

    public static final String MSG_ID = "pantalla";

    @EJB
    private UsuariosEmpresasLocal empresasEJB;

    @EJB
    private RecursosLocal recursosEJB;

    @ManagedProperty(value = "#{sessionMBean}")
    private SessionMBean sessionMBean;

    @ManagedProperty(value = "#{empresaSessionMBean}")
    private EmpresaSessionMBean empresaSessionMBean;

    private RowList<Empresa> empresasSeleccion;

    private Empresa empresaActual = new Empresa();

    private RowList<UsuarioEmpresaDTO> usuariosEmpresaActual = new RowList<UsuarioEmpresaDTO>();

    private RowList<UsuarioRolesRecursoDTO> rolesUsuarioRecursosActual = new RowList<UsuarioRolesRecursoDTO>();

    @PostConstruct
    public void postConstruct() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        if (request.getParameter("n") != null) {
            setEmpresaEditar(new Empresa());
        }
        // Cargar los organismos
        // try {
        // List<Organismo> orgs = empresasEJB.obtenerOrganismos(false);
        // empresaSessionMBean.setOrganismos(orgs);
        // } catch (ApplicationException ex) {
        // empresaSessionMBean.setOrganismos(null);
        // LOGGER.log(Level.WARNING, "No se pudo cargar los organismos", ex);
        // }

        // Cargar las unidades ejecutoras
        try {
            List<UnidadEjecutora> ues = empresasEJB.obtenerUnidadesEjecutoras(false,
                    sessionMBean.getOrganismoActual().getId());
            empresaSessionMBean.setUnidadesEjecutoras(ues);
        } catch (ApplicationException ex) {
            empresaSessionMBean.setUnidadesEjecutoras(null);
            LOGGER.log(Level.WARNING, "No se pudo cargar las unidades ejecutoras", ex);
        }
    }

    public SessionMBean getSessionMBean() {
        return sessionMBean;
    }

    public void setSessionMBean(SessionMBean sessionMBean) {
        this.sessionMBean = sessionMBean;
    }

    public Empresa getEmpresaEditar() {
        if (empresaSessionMBean.getEmpresaEditar() == null) {
            empresaSessionMBean.setEmpresaEditar(new Empresa());
        }

        Empresa empresaEdicion = empresaSessionMBean.getEmpresaEditar();
        empresaEdicion.setOrganismoId(sessionMBean.getOrganismoActual().getId());
        empresaEdicion.setOrganismoCodigo(sessionMBean.getOrganismoActual().getCodigo());
        empresaEdicion.setOrganismoNombre(sessionMBean.getOrganismoActual().getNombre());

        return empresaEdicion;
    }

    public void setEmpresaEditar(Empresa empresa) {
        empresaSessionMBean.setEmpresaEditar(empresa);
    }

    public StreamedContent getEmpresaLogo() {
        return empresaSessionMBean.getEmpresaLogo();
    }

    public boolean isUltimaEmpresaEliminar() {
        return this.empresaSessionMBean.isUltimaEmpresaEliminar();
    }

    // Lista de empresas para seleccionar en la eliminacion/modificacion.
    public RowList<Empresa> getEmpresasSeleccion() {
        try {
            List<Empresa> empresas = new ArrayList();
            // preguntar si es admin de un organismo en vez de super admin
            if (sessionMBean.tieneRoles(new String[] { "RA_AE_ADMINISTRADOR" })) {
                empresas = empresasEJB.consultarEmpresasPorOrganismo(sessionMBean.getOrganismoActual());
            }
            empresasSeleccion = new RowList<>(empresas);
        } catch (Exception ex) {
            addErrorMessage(ex, MSG_ID);
        }
        return empresasSeleccion;
    }

    public Empresa getEmpresaActual() {
        return this.empresaActual;
    }

    public void setEmpresaActual(Empresa empresaActual) {
        this.empresaActual = empresaActual;
    }

    public RowList<UsuarioEmpresaDTO> getUsuariosEmpresaActual() {
        return this.usuariosEmpresaActual;
    }

    public RowList<UsuarioRolesRecursoDTO> getRolesUsuarioRecursosActual() {
        return this.rolesUsuarioRecursosActual;
    }

    public String guardar() {
        limpiarMensajesError();
        if (getEmpresaEditar() != null) {
            try {
                Empresa empresa = getEmpresaEditar();
                boolean hayErrores = false;
                if (empresa.getUnidadEjecutoraCodigo() == null || empresa.getUnidadEjecutoraCodigo().trim().isEmpty()) {
                    hayErrores = true;
                    addErrorMessage(sessionMBean.getTextos().get("el_codigo_de_la_unidad_ejecutora_es_obligatorio"),
                            "form:codigoUnidadEjecutora");
                }
                if (empresa.getNombre() == null || empresa.getNombre().trim().isEmpty()) {
                    hayErrores = true;
                    addErrorMessage(sessionMBean.getTextos().get("el_nombre_de_la_empresa_es_obligatorio"),
                            "form:nombreEmpresa");
                }
                if (empresa.getOid() == null || empresa.getOid().trim().isEmpty()) {
                    hayErrores = true;
                    addErrorMessage(sessionMBean.getTextos().get("el_oid_de_la_empresa_es_obligatorio"),
                            "form:oidEmpresa");
                }
                if (empresa.getDatasource() == null || empresa.getDatasource().trim().isEmpty()) {
                    hayErrores = true;
                    addErrorMessage(sessionMBean.getTextos().get("el_origen_de_datos_de_la_empresa_es_obligatorio"),
                            "form:datasourceEmpresa");
                } else {
                    // Ver si existe el datasource
                    boolean existeDatasource = empresasEJB.existeEsquema(empresa.getDatasource());
                    if (!existeDatasource) {
                        hayErrores = true;
                        addErrorMessage(sessionMBean.getTextos().get("no_existe_el_origen_de_datos"),
                                "form:datasourceEmpresa");
                    } else {
                        List<Empresa> otrasEmpresas = empresasEJB.obtenerEmpresasPorDatasource(empresa.getDatasource());
                        for (Empresa otraEmpresa : otrasEmpresas) {
                            // Si esta eliminada se ignora
                            if (otraEmpresa.getFechaBaja() == null) {
                                // No esta eliminada
                                if (empresa.getId() == null || !empresa.getId().equals(otraEmpresa.getId())) {
                                    hayErrores = true;
                                    addErrorMessage(
                                            sessionMBean.getTextos().get(
                                                    "ya_existe_una_empresa_con_el_mismo_valor_para_origen_de_datos"),
                                            "form:datasourceEmpresa");
                                }
                            }
                        }
                    }
                }
                if (empresa.getCcFinalidad() == null || empresa.getCcFinalidad().trim().isEmpty()) {
                    hayErrores = true;
                    addErrorMessage(
                            sessionMBean.getTextos()
                                    .get("la_finalidad_para_la_clausula_de_consentimiento_informado_es_obligatoria"),
                            "form:ccFinalidad");
                }
                if (empresa.getCcResponsable() == null || empresa.getCcResponsable().trim().isEmpty()) {
                    hayErrores = true;
                    addErrorMessage(
                            sessionMBean.getTextos()
                                    .get("el_responsable_para_la_clausula_de_consentimiento_informado_es_obligatorio"),
                            "form:ccResponsable");
                }
                if (empresa.getCcDireccion() == null || empresa.getCcDireccion().trim().isEmpty()) {
                    hayErrores = true;
                    addErrorMessage(
                            sessionMBean.getTextos()
                                    .get("la_direccion_para_la_clausula_de_consentimiento_informado_es_obligatoria"),
                            "form:ccDireccion");
                }
                if (empresa.getLogoTexto() == null || empresa.getLogoTexto().trim().isEmpty()) {
                    hayErrores = true;
                    addErrorMessage(sessionMBean.getTextos().get("el_texto_alternativo_del_logo_no_puede_estar_vacio"),
                            "form:textoLogoEmpresa");
                }
                if (hayErrores) {
                    return null;
                }
                boolean nueva = (empresa.getId() == null);
                // Verificar que no existe otra empresa con el mismo nombre
                if (nueva) {
                    try {
                        Empresa empresa0 = empresasEJB.obtenerEmpresaPorNombre(empresa.getNombre());
                        if (empresa0 != null) {
                            addErrorMessage(
                                    sessionMBean.getTextos().get("ya_existe_una_empresa_con_el_nombre_especificado"),
                                    "nombreEmpresa");
                            return null;
                        }
                    } catch (ApplicationException aEx) {
                        // Nada para hacer
                    }
                }
                empresa = empresasEJB.guardarEmpresa(empresa);
                if (nueva && sessionMBean.tieneRoles(new String[] { "RA_AE_ADMINISTRADOR" })) {
                    Usuario usuario = sessionMBean.getUsuarioActual();
                    usuario.getEmpresas().add(empresa);
                    empresasEJB.guardarUsuario(usuario);
                    UsuarioEmpresaRoles ueRoles = new UsuarioEmpresaRoles(usuario.getId(), empresa.getId());
                    ueRoles.setAdministrador(Boolean.TRUE);
                    empresasEJB.guardarRolesUsuarioEmpresa(ueRoles);

                }

                setEmpresaEditar(empresa);
                // List<Empresa> entidades = empresasEJB.consultarEmpresas();
                empresasSeleccion = getEmpresasSeleccion();
                if (nueva) {
                    addInfoMessage(sessionMBean.getTextos().get("empresa_creada"), MSG_ID);
                } else {
                    addInfoMessage(sessionMBean.getTextos().get("empresa_modificada"), MSG_ID);
                }
                // se carga los datos del usuario para que cargue la nueva empresa creada
                sessionMBean.cargarEmpresasUsuario();
                // si es la primer empresa y es nueva, loguear al usuario en esa empresa
                // nota: habría que hacer lo mismo si es una edición, es la empresa actualmente
                // seleccionada y cambió el datasource
                if (nueva) {
                    List<SelectItem> emps = sessionMBean.getEmpresasUsuario();
                    if (emps != null && emps.size() == 1) {
                        SelectItem emp = emps.get(0);
                        sessionMBean.cambioEmpresa((Integer) emp.getValue());
                    }
                }
            } catch (ApplicationException | UserException ex) {
                addErrorMessage(ex, MSG_ID);
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public void selecEmpresaEliminar(Integer empresaId) {
        try {
            empresaSessionMBean.setEmpresaEliminar(empresasEJB.obtenerEmpresaPorId(empresaId));
            empresaSessionMBean.setUltimaEmpresaEliminar(empresasSeleccion != null && empresasSeleccion.size() == 1);
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, "No se pudo seleccionar la empresa param eliminar", ex);
        }
    }

    @SuppressWarnings("unchecked")
    public String modificar(Integer empresaId) {
        try {
            setEmpresaEditar(empresasEJB.obtenerEmpresaPorId(empresaId));
            return "modificar";
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, "No se pudo seleccionar la empresa param modificar", ex);
        }
        return null;
    }

    public String eliminar() {

        Empresa empresaEliminar = empresaSessionMBean.getEmpresaEliminar();
        if (empresaEliminar != null) {
            Empresa empresaActual = sessionMBean.getEmpresaActual();
            try {
                if (!empresaEliminar.getId().equals(empresaActual.getId())
                        && empresasEJB.empresaEsquemaValido(empresaEliminar.getId())) {
                    sessionMBean.seleccionarEmpresa(empresaEliminar.getId());
                }
                empresasEJB.eliminarEmpresa(empresaEliminar, sessionMBean.getTimeZone(),
                        sessionMBean.getUsuarioActual().getCodigo());
                // Recargar la lista de empresas
                List<Empresa> empresas = empresasEJB.consultarEmpresas();
                empresasSeleccion = new RowList<>(empresas);
                empresaSessionMBean.setEmpresaEliminar(null);
                addInfoMessage(sessionMBean.getTextos().get("empresa_eliminada"), MSG_ID);
            } catch (ApplicationException | UserException ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
                addErrorMessage(ex, MSG_ID);
            } finally {
                if (empresaEliminar.getId().equals(empresaActual.getId())) {
                    // Se eliminó la empresa seleccionada
                    sessionMBean.setEmpresaActual(null);
                    sessionMBean.cargarEmpresasUsuario();
                    sessionMBean.seleccionarEmpresa(sessionMBean.getEmpresaActual().getId());
                } else {
                    sessionMBean.cargarEmpresasUsuario();
                }
            }
        }
        return null;
    }

    public EmpresaSessionMBean getEmpresaSessionMBean() {
        return empresaSessionMBean;
    }

    public void setEmpresaSessionMBean(EmpresaSessionMBean empresaSessionMBean) {
        this.empresaSessionMBean = empresaSessionMBean;
    }

    public void beforePhaseCrearModificar(PhaseEvent event) {
        if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                    .getRequest();
            if (request.getParameter("n") != null) {
                // Solicitud para crear una empresa nueva
                sessionMBean.setPantallaTitulo(sessionMBean.getTextos().get("crear_empresa"));
            } else {
                // Puede ser que sea editar una empresa existente o se está creando una nueva
                // (se cambió el valor en un combo y no llega el parámetro n)
                String tituloActual = sessionMBean.getPantallaTitulo();
                // Si el título actual es el de crear empresa no se cambia
                if (tituloActual != null && !tituloActual.equals(sessionMBean.getTextos().get("crear_empresa"))) {
                    sessionMBean.setPantallaTitulo(sessionMBean.getTextos().get("modificar_empresa"));
                }
            }
        }
    }

    public void beforePhaseConsultar(PhaseEvent event) {
        if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            sessionMBean.setPantallaTitulo(sessionMBean.getTextos().get("consultar_empresas"));
        }
    }

    // public void cambioOrganismo(ValueChangeEvent event) {
    // Integer orgId = (Integer) event.getNewValue();
    // empresaSessionMBean.actualizarOrganismoEmpresa(orgId);
    // }

    // public String recargarOrganismos(ActionEvent event) {
    // //Cargar los organismos
    // try {
    // List<Organismo> orgs = empresasEJB.obtenerOrganismos(true);
    // if (orgs == null) {
    // addAdvertenciaMessage(sessionMBean.getTextos().get("no_se_pudo_actualizar_lista_de_organismos"));
    // orgs = empresasEJB.obtenerOrganismos(false);
    // } else {
    // addInfoMessage(sessionMBean.getTextos().get("lista_de_organismos_actualizada"));
    // }
    // empresaSessionMBean.setOrganismos(orgs);
    // } catch (ApplicationException ex) {
    // LOGGER.log(Level.WARNING, "No se pudo recargar los organismos", ex);
    // empresaSessionMBean.setOrganismos(null);
    // }
    // return null;
    // }

    public void cambioUnidadEjecutora(ValueChangeEvent event) {
        Integer ueId = (Integer) event.getNewValue();
        empresaSessionMBean.actualizarUnidadEjecutoraEmpresa(ueId);
    }

    public String recargarUnidadesEjecutoras(ActionEvent event) {
        // Cargar las unidades ejecutoras
        try {
            List<UnidadEjecutora> ues = empresasEJB.obtenerUnidadesEjecutoras(true,
                    sessionMBean.getOrganismoActual().getId());
            if (ues == null) {
                addAdvertenciaMessage(
                        sessionMBean.getTextos().get("no_se_pudo_actualizar_lista_de_unidades_ejecutoras"));
                ues = empresasEJB.obtenerUnidadesEjecutoras(false, sessionMBean.getOrganismoActual().getId());
            } else {
                addInfoMessage(sessionMBean.getTextos().get("lista_de_unidades_ejecutas_actualizada"));
            }
            empresaSessionMBean.setUnidadesEjecutoras(ues);
        } catch (ApplicationException ex) {
            LOGGER.log(Level.WARNING, "No se pudo recargar las unidades ejecutoras", ex);
            empresaSessionMBean.setUnidadesEjecutoras(null);
        }
        return null;
    }

    public void cambioLogo(FileUploadEvent event) {
        UploadedFile archivo = event.getFile();
        try {
            byte[] bytes = IOUtils.toByteArray(archivo.getInputstream());
            empresaSessionMBean.getEmpresaEditar().setLogo(bytes);
            boolean actualizarLogoCabezal = false;
            if (sessionMBean.getEmpresaActualId() != null && empresaSessionMBean.getEmpresaEditar() != null
                    && empresaSessionMBean.getEmpresaEditar().getId() != null) {
                try {
                    Integer empresaActualId = Integer.valueOf(sessionMBean.getEmpresaActualId());
                    Integer empresaEditarId = empresaSessionMBean.getEmpresaEditar().getId();
                    if (empresaActualId.intValue() == empresaEditarId.intValue()) {
                        actualizarLogoCabezal = true;
                    }
                } catch (Exception ex) {
                    //
                }
            }
            if (actualizarLogoCabezal) {
                // Si la empresa que se edita es la actual hay que modificar el logo en el
                // cabezal
                sessionMBean.setEmpresaActualLogoBytes(bytes);
            }
        } catch (IOException ex) {
            LOGGER.log(Level.WARNING, "No se pudo cambiar el logo", ex);
        }
    }

    public void quitarLogo() {
        empresaSessionMBean.getEmpresaEditar().setLogo(null);
        if (sessionMBean.getEmpresaActualId() != null && empresaSessionMBean.getEmpresaEditar() != null
                && empresaSessionMBean.getEmpresaEditar().getId() != null) {
            Integer empresaActualId = Integer.valueOf(sessionMBean.getEmpresaActualId());
            Integer empresaEditarId = empresaSessionMBean.getEmpresaEditar().getId();
            if (empresaActualId.intValue() == empresaEditarId.intValue()) {
                // Si la empresa que se edita es la actual hay que modificar el logo en el
                // cabezal
                sessionMBean.setEmpresaActualLogoBytes(null);
            }
        }
    }

    public void cambioEmpresaActual(ValueChangeEvent event) {
        try {
            this.usuariosEmpresaActual = new RowList<UsuarioEmpresaDTO>();
            if ((Integer) event.getNewValue() > 0) {
                List<Usuario> usuarios = this.empresasEJB.consultarUsuariosEmpresa((Integer) event.getNewValue());
                List<UsuarioEmpresaDTO> listadoUsuarios = new ArrayList<UsuarioEmpresaDTO>();
                usuarios.forEach(usuario -> {
                    List<String> listadoRolesUsuarioEmpresa = new ArrayList<String>();
                    try {
                        listadoRolesUsuarioEmpresa = this.empresasEJB.obtenerRolesUsuarioEmpresa(usuario.getId(),
                                (Integer) event.getNewValue());
                    } catch (ApplicationException e) {
                        e.printStackTrace();
                    } finally {
                        UsuarioEmpresaDTO usuarioListado = new UsuarioEmpresaDTO(usuario.getId(), usuario.getCodigo(),
                                usuario.getNombre(), usuario.getCorreoe(),
                                this.getRolesUsuarioEmpresa(listadoRolesUsuarioEmpresa));
                        listadoUsuarios.add(usuarioListado);
                    }
                });
                this.usuariosEmpresaActual = new RowList<>(listadoUsuarios);
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "No se pudo obtener los usuarios por empresa", e);
        }
    }

    private String getRolesUsuarioEmpresa(List<String> rolesUsuarioEmpresa) {
        String roles = "";
        int i = 0;
        for (String rolUsuarioEmpresa : rolesUsuarioEmpresa) {
            roles = (i == 0) ? rolUsuarioEmpresa.trim() : roles + ", " + rolUsuarioEmpresa.trim();
            i++;
        }
        return roles;
    }

    public void obtenerRolesUsuarioRecurso(Integer usuarioId) {
        this.rolesUsuarioRecursosActual = new RowList<UsuarioRolesRecursoDTO>();
        List<UsuarioRolesRecursoDTO> rolesRecurso = new ArrayList<UsuarioRolesRecursoDTO>();
        try {
            Usuario usuario = this.empresasEJB.obtenerUsuarioPorId(usuarioId);
            for (RolesUsuarioRecurso rolesUsuarioRecurso : this.recursosEJB.getRolesUsuarioRecurso(usuarioId)) {
                String usuarioData = usuario.getCodigo().trim() + " - " + usuario.getNombre().trim();
                String nombreRecurso = this.recursosEJB.obtenerRecursoPorId(rolesUsuarioRecurso.getId().getRecursoId())
                        .getNombre().trim();
                UsuarioRolesRecursoDTO usuarioRolesRecursoDTO = new UsuarioRolesRecursoDTO(usuarioData, nombreRecurso,
                        rolesUsuarioRecurso.getRoles());
                rolesRecurso.add(usuarioRolesRecursoDTO);
            }
            this.rolesUsuarioRecursosActual = new RowList<UsuarioRolesRecursoDTO>(rolesRecurso);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('listarRolesUsuarioRecurso').show();");
        } catch (ApplicationException e) {
            LOGGER.log(Level.WARNING, "No se pudo obtener los roles del usuario por recurso", e);
        }
    }
    
    public String reporteUsuarioXEmpresa() {
    	limpiarMensajesError();
        boolean hayErrores = false;        
        if (this.empresaActual.getId() <= 0) {
        	addErrorMessage("Debe seleccionar una empresa.", "form:empresa");
        	hayErrores = true;
        }
        if (hayErrores) {
            return null;
        }
        try {
        	List<List<TableCellValue>> contenido = new ArrayList<>();        	
        	List<UsuarioEmpresaDTO> listusuariosEmpresaActual = listUsuarioXEmpresa(this.empresaActual.getId());        	
        	List<List<TableCellValue>> contenido1 = armarContenidoExel(listusuariosEmpresaActual);        	
        	contenido.addAll(contenido1);
        	String[] defColPlanilla = {};
        	String nombreEmpresa = "";
        	for (Row<Empresa> empresa: this.getEmpresasSeleccion()) {
        		if (empresa.getData().getId().equals(this.empresaActual.getId())) {
        			nombreEmpresa = empresa.getData().getNombre();
        		}
        	}
        	
        	LabelValue[] filtros = {
                    new CommonLabelValueImpl(sessionMBean.getTextos().get("listado_de_empresas") + ": ", nombreEmpresa)
                };
        	StandardCSVFile fileCSV = new StandardCSVFile(filtros, defColPlanilla, contenido);
        	String nombre = "reporte Usuario por Empresa";
        	CSVWebFilePrinter printer = new CSVWebFilePrinter(fileCSV, nombre);
            printer.print();
        }
        catch (Exception e) {
        	addErrorMessage(e);
		}
    	return null;
    }
 // Arma el contenido de la planilla excel
    private List<List<TableCellValue>> armarContenidoExel(List<UsuarioEmpresaDTO> usuariosEmpresaActual){
    	List<List<TableCellValue>> resultado = new ArrayList<>();
    	//Una linea en blanco
        List<TableCellValue> filaCabezal = new ArrayList<>();
        resultado.add(filaCabezal);        
        String[] cabezales = armarCabezales();
        filaCabezal = new ArrayList<>();
        for (String cabezal : cabezales) {
            filaCabezal.add(new TableCellValue(cabezal));
        }
        resultado.add(filaCabezal);        
        for (UsuarioEmpresaDTO usuario:usuariosEmpresaActual) {
        	List<TableCellValue> filaDatos = new ArrayList<>();
        	filaCabezal = new ArrayList<>();
        	cabezales = armarCabezalesRol();
        	for (String cabezal : cabezales) {
                filaCabezal.add(new TableCellValue(cabezal));
            }
            resultado.add(filaCabezal);
            
        	boolean firstFlag = true;
        	//bandera para saber si tiene roles por recurso
        	boolean secondFlag = false;
        	filaDatos.add(new TableCellValue(usuario.getCodigo().trim()));
        	filaDatos.add(new TableCellValue(usuario.getNombre().trim()));
        	filaDatos.add(new TableCellValue(usuario.getCorreo().trim()));
        	filaDatos.add(new TableCellValue(usuario.getRolesEmpresa().trim()));
            List<UsuarioRolesRecursoDTO> listrolesUsuarioRecursosActual = listObtenerRolesUsuarioRecurso(usuario.getId());            
            for (UsuarioRolesRecursoDTO usuarioXroles: listrolesUsuarioRecursosActual) {
            	secondFlag = true;
	            if (!firstFlag) {
	            	filaDatos.add(new TableCellValue(""));
	            	filaDatos.add(new TableCellValue(""));
	            	filaDatos.add(new TableCellValue(""));
	            	filaDatos.add(new TableCellValue(""));
            	}
            	if (usuarioXroles.getUsuario() == null) {
                    filaDatos.add(new TableCellValue(""));
                } else {
                    filaDatos.add(new TableCellValue(usuarioXroles.getUsuario().trim()));
                }
            	if (usuarioXroles.getNombreRecurso() == null) {
                    filaDatos.add(new TableCellValue(""));
                } else {
                    filaDatos.add(new TableCellValue(usuarioXroles.getNombreRecurso().trim()));
                }
            	if (usuarioXroles.getRoles() == null) {
                    filaDatos.add(new TableCellValue(""));
                } else {
                    filaDatos.add(new TableCellValue(usuarioXroles.getRoles().trim()));
                }
            	resultado.add(filaDatos);
            	if (firstFlag) { firstFlag = false;}
            }
            if (!secondFlag) {resultado.add(filaDatos);}
        }
    	return resultado;
    }
    
 // Arma la lista de etiquetas para encabezar la planilla excel
    private String[] armarCabezales() {
    	String[] cabezales = {sessionMBean.getTextos().get("cedula_de_identidad"), sessionMBean.getTextos().get("nombre_y_apellido"),
                sessionMBean.getTextos().get("correo_electronico"), sessionMBean.getTextos().get("roles_del_usuario_en_la_empresa"), sessionMBean.getTextos().get("roles_del_usuario_por_recurso")};
    	return cabezales;
    }
    // Arma la lista de etiquetas  de roles para encabezar la planilla excel
    private String[] armarCabezalesRol() {
    	String[] cabezales = {"", "", "", "", sessionMBean.getTextos().get("usuario"),
    			sessionMBean.getTextos().get("recurso"), sessionMBean.getTextos().get("roles")};
    	return cabezales;
    }
    
    public List<UsuarioEmpresaDTO> listUsuarioXEmpresa(Integer empresaActualID) {    	
    	List<UsuarioEmpresaDTO> listUsuariosEmpresaActual = new ArrayList<>();
    	try {        	
            if (empresaActualID > 0) {
                List<Usuario> usuarios = this.empresasEJB.consultarUsuariosEmpresa(empresaActualID);
                Integer cantidad = usuarios.size();
                List<UsuarioEmpresaDTO> listadoUsuarios = new ArrayList<>();
                usuarios.forEach(usuario -> {
                    List<String> listadoRolesUsuarioEmpresa = new ArrayList<>();
                    try {
                        listadoRolesUsuarioEmpresa = this.empresasEJB.obtenerRolesUsuarioEmpresa(usuario.getId(),
                        		empresaActualID);
                    } catch (ApplicationException e) {
                        e.printStackTrace();
                    } finally {
                        UsuarioEmpresaDTO usuarioListado = new UsuarioEmpresaDTO(usuario.getId(), usuario.getCodigo(),
                                usuario.getNombre(), usuario.getCorreoe(),
                                this.getRolesUsuarioEmpresa(listadoRolesUsuarioEmpresa));
                        listadoUsuarios.add(usuarioListado);
                    }
                });
                listUsuariosEmpresaActual = listadoUsuarios;                
            }           
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "No se pudo obtener los usuarios por empresa", e);
        }
		return listUsuariosEmpresaActual;
    }
    
    public List<UsuarioRolesRecursoDTO> listObtenerRolesUsuarioRecurso(Integer usuarioId) {
    	
        List<UsuarioRolesRecursoDTO> rolesRecurso = new ArrayList<>();
        try {
            Usuario usuario = this.empresasEJB.obtenerUsuarioPorId(usuarioId);
            for (RolesUsuarioRecurso rolesUsuarioRecurso : this.recursosEJB.getRolesUsuarioRecurso(usuarioId)) {
                String usuarioData = usuario.getCodigo().trim() + " - " + usuario.getNombre().trim();
                String nombreRecurso = this.recursosEJB.obtenerRecursoPorId(rolesUsuarioRecurso.getId().getRecursoId())
                        .getNombre().trim();
                UsuarioRolesRecursoDTO usuarioRolesRecursoDTO = new UsuarioRolesRecursoDTO(usuarioData, nombreRecurso,
                        rolesUsuarioRecurso.getRoles());
                rolesRecurso.add(usuarioRolesRecursoDTO);
            }                       
        } catch (ApplicationException e) {
            LOGGER.log(Level.WARNING, "No se pudo obtener los roles del usuario por recurso", e);
        }
		return rolesRecurso;
    }

}
