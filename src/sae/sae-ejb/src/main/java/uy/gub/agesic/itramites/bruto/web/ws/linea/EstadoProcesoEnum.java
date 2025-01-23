
package uy.gub.agesic.itramites.bruto.web.ws.linea;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para estadoProcesoEnum.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="estadoProcesoEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="INICIO"/&gt;
 *     &lt;enumeration value="EN_EJECUCION"/&gt;
 *     &lt;enumeration value="FINALIZADO"/&gt;
 *     &lt;enumeration value="CERRADO"/&gt;
 *     &lt;enumeration value="ARCHIVADO"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "estadoProcesoEnum")
@XmlEnum
public enum EstadoProcesoEnum {

    INICIO,
    EN_EJECUCION,
    FINALIZADO,
    CERRADO,
    ARCHIVADO;

    public String value() {
        return name();
    }

    public static EstadoProcesoEnum fromValue(String v) {
        return valueOf(v);
    }

}
