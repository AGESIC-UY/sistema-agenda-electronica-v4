
package uy.gub.agesic.itramites.bruto.web.ws.linea;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tipoProcesoEnum.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="tipoProcesoEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="TRAMITE"/&gt;
 *     &lt;enumeration value="COMPRA"/&gt;
 *     &lt;enumeration value="LICITACION"/&gt;
 *     &lt;enumeration value="RECLAMO"/&gt;
 *     &lt;enumeration value="SERVICIO"/&gt;
 *     &lt;enumeration value="EXPEDIENTE"/&gt;
 *     &lt;enumeration value="PROCESO_GENERICO"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
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
