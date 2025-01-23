/*
 * Sofis Solutions
 */
package uy.com.sofis.utils;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author USUARIO
 */
public class UruguayDateFormatter {

    private static String patternFechaHora = "dd/MM/yyyy HH:mm";
    private static String patternFecha = "dd/MM/yyyy";

    private static ZoneId getZoneId() {
        return ZoneId.ofOffset("GMT", ZoneOffset.ofHours(0)); //Horario de servidor ya se encuentra en gmt-3
    }

    public static DateTimeFormatter getDateTimeFormatterWithTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternFechaHora);
        ZoneId zone = getZoneId();
        return (zone != null) ? formatter.withZone(zone) : formatter;
    }

    public static DateTimeFormatter getDateTimeFormatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternFecha);
        ZoneId zone = getZoneId();
        return (zone != null) ? formatter.withZone(zone) : formatter;
    }
    


}
