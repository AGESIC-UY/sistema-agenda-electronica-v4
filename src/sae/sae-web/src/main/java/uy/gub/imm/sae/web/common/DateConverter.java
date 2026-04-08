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
package uy.gub.imm.sae.web.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import uy.gub.imm.sae.common.Utiles;

/**
 * Convierte de String a Date y viceversa. En ambos casos se puede especificar
 * el patrón, siguiendo la misma nomenclatura que java.text.DateFormat. Para
 * eso, si se utiliza un tag <f:inputText> o similar se debe utilizar lo
 * siguiente:
 * <f:attribute name="pattern" value="..."/>
 * En el caso de la conversión de String a Date también permite especificar qué
 * se debe hacer en caso de que no se pueda realizar la conversión mediante el
 * atributo onError; los valores aceptados y el comportamiento correspondiente
 * son los siguientes: - clear: convierte el valor a null y vacía el campo de
 * texto. - zero: convierte el valor al comienzo de los tiempos "01/01/0001"
 * (ver uy.gub.imm.sae.web.common.Utiles.FECHA_INVALIDA) que debe ser reconocido
 * como una fecha inválida.
 *
 * @author spio
 *
 */
@FacesConverter("dateConverter")
public class DateConverter implements Converter {

    private static final String PATRON_DEFAULT = "yyyyMMdd HH:mm:ss Z";

    public DateConverter() {
    }

    @Override
    public Date getAsObject(FacesContext facesContext, UIComponent comp, String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return null;
        }
        String patron = (String) comp.getAttributes().get("pattern");
        if (patron == null) {
            patron = PATRON_DEFAULT;
        }
        try {
            DateFormat dateFormat = new SimpleDateFormat(patron);
            dateFormat.setLenient(false);
            Date fecha = dateFormat.parse(valor);
            return fecha;
        } catch (Exception ex) {
            //Actuar según lo solicitado por el atributo onError
            String onError = (String) comp.getAttributes().get("onError");
            if (onError != null) {
                if ("clear".equals(onError)) {
                    return null;
                } else if ("zero".equals(onError)) {
                    Date fecha = Utiles.FECHA_INVALIDA.getTime();
                    return fecha;
                }
            }
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent comp, Object valor) {
        if (valor == null || !(valor instanceof Date)) {
            return null;
        }
        String patron = (String) comp.getAttributes().get("pattern");
        if (patron == null) {
            patron = PATRON_DEFAULT;
        }
        try {
            DateFormat dateFormat = new SimpleDateFormat(patron);
            String fecha = dateFormat.format(valor);
            return fecha;
        } catch (Exception ex) {
            return null;
        }
    }

}
