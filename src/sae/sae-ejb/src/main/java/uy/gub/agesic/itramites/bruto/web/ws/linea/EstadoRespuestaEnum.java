
package uy.gub.agesic.itramites.bruto.web.ws.linea;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para estadoRespuestaEnum.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="estadoRespuestaEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="OK"/&gt;
 *     &lt;enumeration value="ERROR"/&gt;
 *     &lt;enumeration value="EXCEPTION"/&gt;
 *     &lt;enumeration value="EXCEPTION_MENSAJE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "estadoRespuestaEnum")
@XmlEnum
public enum EstadoRespuestaEnum {

    OK,
    ERROR,
    EXCEPTION,
    EXCEPTION_MENSAJE;

    public String value() {
        return name();
    }

    public static EstadoRespuestaEnum fromValue(String v) {
        return valueOf(v);
    }

}
