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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;


import uy.gub.imm.sae.business.ejb.facade.RecursosLocal;
import uy.gub.imm.sae.business.ejb.facade.ValidacionesLocal;
import uy.gub.imm.sae.entity.DatoASolicitar;
import uy.gub.imm.sae.entity.ParametroValidacion;
import uy.gub.imm.sae.entity.Validacion;
import uy.gub.imm.sae.entity.ValidacionPorDato;
import uy.gub.imm.sae.entity.ValidacionPorRecurso;
import uy.gub.imm.sae.exception.ApplicationException;
import uy.gub.imm.sae.web.common.RowList;

@ManagedBean
@ViewScoped
public class ValidacionAsignacionMBean extends BaseMBean {

    public static final String MSG_ID = "pantalla";

    @EJB
    private ValidacionesLocal validacionesEJB;

    @EJB
    private RecursosLocal recursosEJB;

    @ManagedProperty(value = "#{sessionMBean}")
    private SessionMBean sessionMBean;

    private RowList<ValidacionPorRecurso> validacionesDelRecurso;

    private ValidacionPorRecurso validacionDelRecurso;
    private ValidacionPorRecurso validacionDelRecursoEliminar;

    private Map<Integer, DatoASolicitar> datosASolicitarDelRecurso;
    private List<SelectItem> datosASolicitarDelRecursoItems;

    private List<ParametroValidacion> parametrosValidacion;

    private Map<Integer, Validacion> validaciones;
    private List<SelectItem> validacionesItems;

    public SessionMBean getSessionMBean() {
        return sessionMBean;
    }

    public void setSessionMBean(SessionMBean sessionMBean) {
        this.sessionMBean = sessionMBean;
    }

    public void beforePhase(PhaseEvent event) {
        if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            sessionMBean.setPantallaTitulo(sessionMBean.getTextos().get("asociar_validaciones_a_recurso"));
            if (sessionMBean.getAgendaMarcada() == null) {
                addErrorMessage(sessionMBean.getTextos().get("debe_haber_una_agenda_seleccionada"), MSG_ID);
            }
            if (sessionMBean.getRecursoMarcado() == null) {
                addErrorMessage(sessionMBean.getTextos().get("debe_haber_un_recurso_seleccionado"), MSG_ID);
            }
        }
    }

    private void cargarValidacionesDelRecurso() {
        try {
            List<ValidacionPorRecurso> entidades = validacionesEJB.obtenerValidacionesDelRecurso(sessionMBean.getRecursoMarcado());
            validacionesDelRecurso = new RowList<>(entidades);
        } catch (Exception e) {
            addErrorMessage(e, MSG_ID);
        }
    }

    public RowList<ValidacionPorRecurso> getValidacionesDelRecurso() {
        if (validacionesDelRecurso == null) {
            cargarValidacionesDelRecurso();
        }
        return validacionesDelRecurso;
    }

    public ValidacionPorRecurso getValidacionDelRecurso() {
        return validacionDelRecurso;
    }

    public void setValidacionDelRecurso(ValidacionPorRecurso validacionDelRecurso) {
        this.validacionDelRecurso = validacionDelRecurso;
    }

    @SuppressWarnings("unchecked")
    public void selecValidacionEliminar(Integer rowIndex) {
        validacionDelRecurso = null;
        this.validacionDelRecursoEliminar = validacionesDelRecurso.get(rowIndex).getData();
    }

    public void eliminarValidacion(ActionEvent event) {
        if (validacionDelRecursoEliminar != null) {
            try {
                validacionesEJB.eliminarValidacionPorRecurso(validacionDelRecursoEliminar);
                validacionDelRecursoEliminar = null;
                cargarValidacionesDelRecurso();
                addInfoMessage(sessionMBean.getTextos().get("validacion_eliminada"), MSG_ID);
            } catch (Exception ex) {
                addErrorMessage(ex, MSG_ID);
            }
        }
    }

    private void cargarValidaciones() throws ApplicationException {
        validaciones = new HashMap<>();
        validacionesItems = new ArrayList<>();
        validacionesItems.add(new SelectItem(0, "Sin especificar"));
        List<Validacion> validaciones0 = validacionesEJB.consultarValidaciones();
        for (Validacion validacion : validaciones0) {
            validacionesItems.add(new SelectItem(validacion.getId(), validacion.getNombre()));
            validaciones.put(validacion.getId(), validacion);
        }
    }

    public void agregarValidacion(ActionEvent event) {
        try {
            this.validacionDelRecurso = new ValidacionPorRecurso();
            this.validacionDelRecurso.setValidacion(new Validacion());
            this.validacionDelRecurso.setRecurso(sessionMBean.getRecursoMarcado());
            this.validacionDelRecurso.setValidacionesPorDato(new ArrayList<>());
            cargarValidaciones();
        } catch (Exception e) {
            addErrorMessage(e, MSG_ID);
        }
        cargarDatosASolicitar();
        cargarParametrosDeLaValidacion();
    }

    @SuppressWarnings("unchecked")
    public void editarValidacion(Integer rowIndex) {
        try {
            this.validacionDelRecurso = validacionesDelRecurso.get(rowIndex).getData();
            this.validacionDelRecurso.setValidacionesPorDato(validacionesEJB.obtenerAsociacionesValidacionPorDato(this.validacionDelRecurso));
            cargarValidaciones();
        } catch (Exception e) {
            addErrorMessage(e, MSG_ID);
        }
        cargarDatosASolicitar();
        cargarParametrosDeLaValidacion();
    }

    public List<SelectItem> getValidacionesItems() {
        return validacionesItems;
    }

    private void cargarDatosASolicitar() {
        this.datosASolicitarDelRecurso = new HashMap<>();
        this.datosASolicitarDelRecursoItems = new ArrayList<>();
        try {
            List<DatoASolicitar> datosASol = recursosEJB.consultarDatosSolicitar(sessionMBean.getRecursoMarcado());
            for (DatoASolicitar das : datosASol) {
                this.datosASolicitarDelRecursoItems.add(new SelectItem(das.getId(), das.getNombre()));
                this.datosASolicitarDelRecurso.put(das.getId(), das);
            }
        } catch (Exception e) {
            addErrorMessage(e, MSG_ID);
        }
    }

    public List<SelectItem> getDatosASolicitarItems() {
        return datosASolicitarDelRecursoItems;
    }

    public List<SelectItem> getParametrosDeLaValidacionItems() {
        List<SelectItem> parametrosDeLaValidacionItems = new ArrayList<>();
        for (ParametroValidacion param : this.parametrosValidacion) {
            parametrosDeLaValidacionItems.add(new SelectItem(param.getNombre(), param.getNombre()));
        }
        return parametrosDeLaValidacionItems;
    }

    public void cambioValidacionDelRecurso(ValueChangeEvent event) {

        limpiarMensajesError();

        Integer validacionId = (Integer) event.getNewValue();
        if (validacionId != null && validacionId > 0) {
            Validacion validacion = validaciones.get(validacionId);
            this.validacionDelRecurso.setValidacion(validacion);
        } else {
            this.validacionDelRecurso.setValidacion(new Validacion());
        }
        cargarParametrosDeLaValidacion();
    }

    private void cargarParametrosDeLaValidacion() {
        try {
            Validacion validacion = validacionDelRecurso.getValidacion();
            if (validacion.getId() != null && validacion.getId() > 0) {
                this.parametrosValidacion = validacionesEJB.consultarParametrosDeLaValidacion(validacion);
            } else {
                this.parametrosValidacion = new ArrayList<>();
            }
        } catch (Exception e) {
            addErrorMessage(e, MSG_ID);
        }
    }

    public void guardarAsociacion(ActionEvent event) {
        try {
            boolean hayErrores = false;

            if (this.validacionDelRecurso.getValidacion().getId() == null || this.validacionDelRecurso.getValidacion().getId() == 0) {
                addErrorMessage(sessionMBean.getTextos().get("la_validacion_es_obligatoria"), "form:accionAsociacion");
                hayErrores = true;
            }
            if (this.validacionDelRecurso.getOrdenEjecucion() == null) {
                addErrorMessage(sessionMBean.getTextos().get("el_orden_de_ejecucion_es_obligatorio"), "form:ordenAsociacion");
                hayErrores = true;
            } else if (this.validacionDelRecurso.getOrdenEjecucion() < 1) {
                addErrorMessage(sessionMBean.getTextos().get("el_orden_de_ejecucion_debe_ser_mayor_a_cero"), "form:ordenAsociacion");
                hayErrores = true;
            }
            int ind = 0;
            for (ValidacionPorDato param : this.validacionDelRecurso.getValidacionesPorDato()) {
                if (param.getDatoASolicitar().getId() == null || param.getDatoASolicitar().getId() == 0) {
                    addErrorMessage(sessionMBean.getTextos().get("el_dato_a_solicitar_es_obligatorio"), "form:tablaAccionesPorDato:" + ind + ":parametroDatoASolicitar");
                    hayErrores = true;
                }
                if (param.getNombreParametro() == null || param.getNombreParametro().trim().isEmpty()) {
                    addErrorMessage(sessionMBean.getTextos().get("el_parametro_es_obligatorio"), "form:tablaAccionesPorDato:" + ind + ":parametroParametroDato");
                    hayErrores = true;
                }
                ind++;
            }
            if (hayErrores) {
                return;
            }
            if (this.validacionDelRecurso.getId() == null) {
                validacionesEJB.crearValidacionPorRecurso(this.validacionDelRecurso);
                addInfoMessage(sessionMBean.getTextos().get("validacion_creada"), MSG_ID);
            } else {
                validacionesEJB.modificarValidacionPorRecurso(this.validacionDelRecurso);
                addInfoMessage(sessionMBean.getTextos().get("validacion_modificada"), MSG_ID);
            }
            this.validacionDelRecurso = null;
            cargarValidacionesDelRecurso();
        } catch (Exception e) {
            addErrorMessage(e, MSG_ID);
        }
    }

    public void cancelarAsociacion(ActionEvent e) {
        this.validacionDelRecurso = null;
        cargarValidacionesDelRecurso();
    }

    public void crearValidacionPorDato(ActionEvent event) {
        ValidacionPorDato validacionPorDato = new ValidacionPorDato();
        validacionPorDato.setDatoASolicitar(new DatoASolicitar());
        this.validacionDelRecurso.getValidacionesPorDato().add(validacionPorDato);
    }

    public void eliminarValidacionPorDato(Integer ordinal) {
        this.validacionDelRecurso.getValidacionesPorDato().remove(ordinal.intValue());
    }

}
