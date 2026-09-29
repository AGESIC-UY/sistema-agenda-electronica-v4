/*
 *  Nombre del cliente
 *  Sistema de Gestión
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.business.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Sofis Solutions
 */
public class DateHelper {

    /**
     * Devuelve el string correspondiente a una fecha Date
     * @param d
     * @return
     */
    public static String toStrignDate(Date d) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(d);
    }

    /**
     * Devuelve el string correspondiente a una fecha XMLGregorianCalendar
     * @param d
     * @return
     */
    public static String toStrignXMLGregorianCalendar(XMLGregorianCalendar d) {
        String resp = "" + d.getDay() + "/" + d.getMonth() + "/" + d.getYear();
        return resp;
    }

    /**
     * Convierte de Date a XMLGregorianCalendar
     * @param cal
     * @return
     */
    public static Date toDate(XMLGregorianCalendar cal) {
        Date respuesta = null;
        /* if ((cal != null ) && (cal.toGregorianCalendar()!=null)) {
        respuesta = cal.toGregorianCalendar().getTime();
        }*/
        if (cal != null) {
            Calendar gc = new GregorianCalendar();
            gc.set(cal.getYear(), cal.getMonth() - 1, cal.getDay(), cal.getHour(), cal.getMinute());
            respuesta = gc.getTime();
        }
        return respuesta;
    }


}
