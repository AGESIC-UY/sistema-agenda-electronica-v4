
package uy.gub.agesic.itramites.bruto.web.ws.cabezal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for datosProcesoTramiteDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="datosProcesoTramiteDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService}datosProcesoDTO">
 *       &lt;sequence>
 *         &lt;element name="canalDeInicio" type="{http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService}datosProcesoTramiteCanalDeInicioEnum" minOccurs="0"/>
 *         &lt;element name="inicioAsistidoProceso" type="{http://ws.web.captura.trazabilidad.agesic.gub.uy/api/v2/cabezalService}datosProcesoTramiteInicioAsistidoProcesoEnum" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datosProcesoTramiteDTO", propOrder = {
    "canalDeInicio",
    "inicioAsistidoProceso"
})
public class DatosProcesoTramiteDTO
    extends DatosProcesoDTO
{

    protected DatosProcesoTramiteCanalDeInicioEnum canalDeInicio;
    protected DatosProcesoTramiteInicioAsistidoProcesoEnum inicioAsistidoProceso;

    /**
     * Gets the value of the canalDeInicio property.
     * 
     * @return
     *     possible object is
     *     {@link DatosProcesoTramiteCanalDeInicioEnum }
     *     
     */
    public DatosProcesoTramiteCanalDeInicioEnum getCanalDeInicio() {
        return canalDeInicio;
    }

    /**
     * Sets the value of the canalDeInicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosProcesoTramiteCanalDeInicioEnum }
     *     
     */
    public void setCanalDeInicio(DatosProcesoTramiteCanalDeInicioEnum value) {
        this.canalDeInicio = value;
    }

    /**
     * Gets the value of the inicioAsistidoProceso property.
     * 
     * @return
     *     possible object is
     *     {@link DatosProcesoTramiteInicioAsistidoProcesoEnum }
     *     
     */
    public DatosProcesoTramiteInicioAsistidoProcesoEnum getInicioAsistidoProceso() {
        return inicioAsistidoProceso;
    }

    /**
     * Sets the value of the inicioAsistidoProceso property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosProcesoTramiteInicioAsistidoProcesoEnum }
     *     
     */
    public void setInicioAsistidoProceso(DatosProcesoTramiteInicioAsistidoProcesoEnum value) {
        this.inicioAsistidoProceso = value;
    }

}
