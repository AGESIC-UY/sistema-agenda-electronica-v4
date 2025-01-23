
package uy.gub.agesic.itramites.bruto.web.ws.cabezal;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for datosProcesoExpedienteFormaDeInicioEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="datosProcesoExpedienteFormaDeInicioEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="APP_EE"/>
 *     &lt;enumeration value="APP_EXTERNA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "datosProcesoExpedienteFormaDeInicioEnum")
@XmlEnum
public enum DatosProcesoExpedienteFormaDeInicioEnum {

    APP_EE,
    APP_EXTERNA;

    public String value() {
        return name();
    }

    public static DatosProcesoExpedienteFormaDeInicioEnum fromValue(String v) {
        return valueOf(v);
    }

}
