/*
 *  SIGES
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.web.presentacion.converters;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;
import uy.com.sofis.web.mb.ApplicationBean;

@Named
@ApplicationScoped
public class LocalTimeConverter implements javax.faces.convert.Converter {

    private DateTimeFormatter formatter;

    @Inject
    private ApplicationBean aplicacionBean;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        try {
            checkFormatter();
            return LocalTime.parse(value, formatter);
        } catch (Exception e) {
            throw new ConverterException("Hora inválida");
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        checkFormatter();
        LocalTime timeValue = (LocalTime) value;
        return timeValue.format(formatter);
    }

    private void checkFormatter() {
        if (formatter == null) {
            formatter = DateTimeFormatter.ofPattern(aplicacionBean.getPatternHora());
        }
    }

}
