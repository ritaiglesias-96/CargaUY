
package tse.java.soappdi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ciudadano" type="{/pdi}ciudadano"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ciudadano"
})
@XmlRootElement(name = "getCiudadanoResponse")
public class GetCiudadanoResponse {

    @XmlElement(required = true)
    protected Ciudadano ciudadano;

    /**
     * Obtiene el valor de la propiedad ciudadano.
     * 
     * @return
     *     possible object is
     *     {@link Ciudadano }
     *     
     */
    public Ciudadano getCiudadano() {
        return ciudadano;
    }

    /**
     * Define el valor de la propiedad ciudadano.
     * 
     * @param value
     *     allowed object is
     *     {@link Ciudadano }
     *     
     */
    public void setCiudadano(Ciudadano value) {
        this.ciudadano = value;
    }

}
