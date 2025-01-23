
package uy.gub.agesic.itramites.bruto.web.ws.cabezal;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for datosProcesoTramiteCanalDeInicioEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="datosProcesoTramiteCanalDeInicioEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="WEB_PC"/>
 *     &lt;enumeration value="WEB_MOVIL"/>
 *     &lt;enumeration value="PRESENCIAL"/>
 *     &lt;enumeration value="REDES_DE_COBRANZAS"/>
 *     &lt;enumeration value="PAC"/>
 *     &lt;enumeration value="TELEFONICO"/>
 *     &lt;enumeration value="CORREO_ELECTRONICO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "datosProcesoTramiteCanalDeInicioEnum")
@XmlEnum
public enum DatosProcesoTramiteCanalDeInicioEnum {

    WEB_PC,
    WEB_MOVIL,
    PRESENCIAL,
    REDES_DE_COBRANZAS,
    PAC,
    TELEFONICO,
    CORREO_ELECTRONICO;

    public String value() {
        return name();
    }

    public static DatosProcesoTramiteCanalDeInicioEnum fromValue(String v) {
        return valueOf(v);
    }

}
