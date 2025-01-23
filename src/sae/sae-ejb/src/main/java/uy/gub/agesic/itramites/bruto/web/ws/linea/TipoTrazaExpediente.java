
package uy.gub.agesic.itramites.bruto.web.ws.linea;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tipoTrazaExpediente.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="tipoTrazaExpediente"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="PASE_INTERNO"/&gt;
 *     &lt;enumeration value="PASE_EXTERNO"/&gt;
 *     &lt;enumeration value="CAMBIO_DE_ESTADO"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "tipoTrazaExpediente")
@XmlEnum
public enum TipoTrazaExpediente {

    PASE_INTERNO,
    PASE_EXTERNO,
    CAMBIO_DE_ESTADO;

    public String value() {
        return name();
    }

    public static TipoTrazaExpediente fromValue(String v) {
        return valueOf(v);
    }

}
