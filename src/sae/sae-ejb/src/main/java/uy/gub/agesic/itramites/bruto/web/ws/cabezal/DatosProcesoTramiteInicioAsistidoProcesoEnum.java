
package uy.gub.agesic.itramites.bruto.web.ws.cabezal;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for datosProcesoTramiteInicioAsistidoProcesoEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="datosProcesoTramiteInicioAsistidoProcesoEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NO"/>
 *     &lt;enumeration value="SI"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "datosProcesoTramiteInicioAsistidoProcesoEnum")
@XmlEnum
public enum DatosProcesoTramiteInicioAsistidoProcesoEnum {

    NO,
    SI;

    public String value() {
        return name();
    }

    public static DatosProcesoTramiteInicioAsistidoProcesoEnum fromValue(String v) {
        return valueOf(v);
    }

}
