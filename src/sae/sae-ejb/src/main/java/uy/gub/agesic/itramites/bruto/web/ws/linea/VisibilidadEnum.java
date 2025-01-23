
package uy.gub.agesic.itramites.bruto.web.ws.linea;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para visibilidadEnum.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="visibilidadEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="VISIBLE"/&gt;
 *     &lt;enumeration value="USO_INTERNO"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "visibilidadEnum")
@XmlEnum
public enum VisibilidadEnum {

    VISIBLE,
    USO_INTERNO;

    public String value() {
        return name();
    }

    public static VisibilidadEnum fromValue(String v) {
        return valueOf(v);
    }

}
