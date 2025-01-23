
package uy.gub.agesic.itramites.bruto.web.ws.linea;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tipoRegistroTrazabilidadEnum.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="tipoRegistroTrazabilidadEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="TRANSFERENCIA"/&gt;
 *     &lt;enumeration value="SUBPROCESO"/&gt;
 *     &lt;enumeration value="COMUN"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "tipoRegistroTrazabilidadEnum")
@XmlEnum
public enum TipoRegistroTrazabilidadEnum {

    TRANSFERENCIA,
    SUBPROCESO,
    COMUN;

    public String value() {
        return name();
    }

    public static TipoRegistroTrazabilidadEnum fromValue(String v) {
        return valueOf(v);
    }

}
