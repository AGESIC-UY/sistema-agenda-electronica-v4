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

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

import uy.gub.imm.sae.business.ejb.facade.RecursosLocal;
import uy.gub.imm.sae.entity.AgrupacionDato;
import uy.gub.imm.sae.entity.DatoASolicitar;
import uy.gub.imm.sae.entity.Recurso;
import uy.gub.imm.sae.web.common.FormularioDinamicoReserva;

/**
 * Maneja la lógica de generación dinámica de los componentes gráficos que constituyen los datos a solicitarse para realizar la reserva.
 *
 * @author im2716295
 *
 */
@ManagedBean
@ViewScoped
public class DisenioFormularioMBean extends BaseMBean {

    public static final String FORMULARIO_ID = "datosReserva";
    public static final String DATOS_RESERVA_MBEAN = "datosReservaMBean";

    @EJB
    private RecursosLocal recursosEJB;

    @ManagedProperty(value = "#{sessionMBean}")
    private SessionMBean sessionMBean;

    @ManagedProperty(value = "#{datosReservaMBean}")
    private Map<String, Object> datosReservaMBean;

    private UIComponent campos;
    private FormularioDinamicoReserva formularioDin;

    @PostConstruct
    public void init() {
    }

    public SessionMBean getSessionMBean() {
        return sessionMBean;
    }

    public void setSessionMBean(SessionMBean sessionMBean) {
        this.sessionMBean = sessionMBean;
    }

    public UIComponent getCampos() {
        return campos;
    }

    public void setCampos(UIComponent campos) {

        this.campos = campos;

        try {
            Recurso recurso = sessionMBean.getRecursoMarcado();

            //El chequeo de recurso != null es en caso de un acceso directo a la pagina, es solo
            //para que no salte la excepcion en el log, pues de todas formas sera redirigido a una pagina de error.
            if (campos.getChildCount() == 0 && recurso != null) {

                if (formularioDin == null) {
                    List<AgrupacionDato> agrupaciones = recursosEJB.consultarDefinicionDeCampos(recurso, sessionMBean.getTimeZone());
                    sessionMBean.setDatosASolicitar(obtenerCampos(agrupaciones));
                    formularioDin = new FormularioDinamicoReserva(DATOS_RESERVA_MBEAN, FORMULARIO_ID, FormularioDinamicoReserva.TipoFormulario.EDICION,
                            null, sessionMBean.getFormatoFecha(), new Locale(sessionMBean.getIdiomaActual()));

                    //TODO: VER ESTOOO!!!!!!
                    formularioDin.armarFormulario(agrupaciones, null);
                }
                UIComponent formulario = formularioDin.getComponenteFormulario();
                campos.getChildren().add(formulario);
            }
        } catch (Exception e) {
            addErrorMessage(e);
        }
    }

    public Map<String, Object> getDatosReservaMBean() {
        return datosReservaMBean;
    }

    public void setDatosReservaMBean(Map<String, Object> datosReservaMBean) {
        this.datosReservaMBean = datosReservaMBean;
    }

    public void refrescar(ActionEvent event) {

        getCampos().getChildren().clear();
        try {
            Recurso recurso = sessionMBean.getRecursoMarcado();

            //El chequeo de recurso != null es en caso de un acceso directo a la pagina, es solo
            //para que no salte la excepcion en el log, pues de todas formas sera redirigido a una pagina de error.
            if (recurso != null) {
                List<AgrupacionDato> agrupaciones = recursosEJB.consultarDefinicionDeCampos(recurso, sessionMBean.getTimeZone());
                sessionMBean.setDatosASolicitar(obtenerCampos(agrupaciones));
                FormularioDinamicoReserva formularioDin1 = new FormularioDinamicoReserva(DATOS_RESERVA_MBEAN, FORMULARIO_ID,
                        FormularioDinamicoReserva.TipoFormulario.EDICION, null, sessionMBean.getFormatoFecha(), new Locale(sessionMBean.getIdiomaActual()));
                formularioDin1.armarFormulario(agrupaciones, null);
                UIComponent formulario = formularioDin1.getComponenteFormulario();
                campos.getChildren().add(formulario);
            }
        } catch (Exception e) {
            addErrorMessage(e);
        }
    }

    /**
     * @param agrupaciones de algun recurso
     * @return retorna todos los datos a solicitar definidos en la lista de agrupaciones en un mapa cuya clave es el nombre del campo
     */
    private Map<String, DatoASolicitar> obtenerCampos(List<AgrupacionDato> agrupaciones) {

        Map<String, DatoASolicitar> camposXnombre = new HashMap<>();

        for (AgrupacionDato agrupacion : agrupaciones) {
            for (DatoASolicitar dato : agrupacion.getDatosASolicitar()) {
                camposXnombre.put(dato.getNombre(), dato);
            }
        }

        return camposXnombre;
    }

}
