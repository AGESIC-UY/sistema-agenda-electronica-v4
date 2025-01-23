package uy.gub.imm.sae.business.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public abstract class TimeZoneUtils {

    public static Date applyTimeZone(TimeZone timezone, GregorianCalendar cal) {

        if (!timezone.equals(cal.getTimeZone())) {
            // Ajustar la hora segun el timezone solo si el timezone es diferente al de la agenda
            cal.add(Calendar.MILLISECOND, timezone.getOffset(cal.getTime().getTime()));
        }
        return cal.getTime();
    }

}
