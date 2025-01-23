
package uy.gub.agesic.itramites.bruto.web.ws.cabezal;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tipoProcesoEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="tipoProcesoEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="TRAMITE"/>
 *     &lt;enumeration value="COMPRA"/>
 *     &lt;enumeration value="LICITACION"/>
 *     &lt;enumeration value="RECLAMO"/>
 *     &lt;enumeration value="SERVICIO"/>
 *     &lt;enumeration value="EXPEDIENTE"/>
 *     &lt;enumeration value="PROCESO_GENERICO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tipoProcesoEnum")
@XmlEnum
public enum TipoProcesoEnum {

    TRAMITE,
    COMPRA,
    LICITACION,
    RECLAMO,
    SERVICIO,
    EXPEDIENTE,
    PROCESO_GENERICO;

    public String value() {
        return name();
    }

    public static TipoProcesoEnum fromValue(String v) {
        return valueOf(v);
    }

}
