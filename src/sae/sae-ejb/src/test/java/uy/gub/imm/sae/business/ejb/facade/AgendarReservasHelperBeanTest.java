package uy.gub.imm.sae.business.ejb.facade;

import org.junit.jupiter.api.Test;
import uy.gub.imm.sae.business.utils.TimeZoneUtils;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AgendarReservasHelperBeanTest {

    @Test
    void givenAhoraWithSameTimeZoneWhenAhoraAjustadoGetSameValue() {
        var ahoraUruguay = GregorianCalendar.from(ZonedDateTime.parse("2022-08-17T09:15:45.929419600-03:00[America/Montevideo]"));
        var ahoraAjustado = TimeZoneUtils.applyTimeZone(TimeZone.getTimeZone("America/Montevideo"), ahoraUruguay);

        assertThat(ahoraAjustado).isEqualTo(ahoraUruguay.getTime());
    }

    @Test
    void givenAhoraWithDifferentTimeZoneWhenAhoraAjustadoGetAhoraAjustado() {
        var ahoraChicago = GregorianCalendar.from(ZonedDateTime.parse("2022-08-17T07:36:11.685560600-05:00[America/Chicago]"));
        var ahoraAjustado = TimeZoneUtils.applyTimeZone(TimeZone.getTimeZone("America/Montevideo"), ahoraChicago);

        assertThat(ahoraAjustado).isEqualTo(Date.from(ZonedDateTime.parse("2022-08-17T05:36:11.685560600-04:00[America/Montevideo]").toInstant()));

    }
}
