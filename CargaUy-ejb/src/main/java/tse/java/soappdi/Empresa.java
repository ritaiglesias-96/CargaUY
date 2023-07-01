
package tse.java.soappdi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para empresa complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="empresa"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="rut" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="nombrePublico" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dirPrincipal" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="razonSocial" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="nroEmpresa" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="responsable" type="{/pdi}responsable" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "empresa", propOrder = {
    "rut",
    "nombrePublico",
    "dirPrincipal",
    "razonSocial",
    "nroEmpresa",
    "responsable"
})
public class Empresa {

    @XmlElement(required = true)
    protected String rut;
    @XmlElement(required = true)
    protected String nombrePublico;
    @XmlElement(required = true)
    protected String dirPrincipal;
    @XmlElement(required = true)
    protected String razonSocial;
    protected int nroEmpresa;
    protected Responsable responsable;

    /**
     * Obtiene el valor de la propiedad rut.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRut() {
        return rut;
    }

    /**
     * Define el valor de la propiedad rut.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRut(String value) {
        this.rut = value;
    }

    /**
     * Obtiene el valor de la propiedad nombrePublico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombrePublico() {
        return nombrePublico;
    }

    /**
     * Define el valor de la propiedad nombrePublico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombrePublico(String value) {
        this.nombrePublico = value;
    }

    /**
     * Obtiene el valor de la propiedad dirPrincipal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirPrincipal() {
        return dirPrincipal;
    }

    /**
     * Define el valor de la propiedad dirPrincipal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirPrincipal(String value) {
        this.dirPrincipal = value;
    }

    /**
     * Obtiene el valor de la propiedad razonSocial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * Define el valor de la propiedad razonSocial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazonSocial(String value) {
        this.razonSocial = value;
    }

    /**
     * Obtiene el valor de la propiedad nroEmpresa.
     * 
     */
    public int getNroEmpresa() {
        return nroEmpresa;
    }

    /**
     * Define el valor de la propiedad nroEmpresa.
     * 
     */
    public void setNroEmpresa(int value) {
        this.nroEmpresa = value;
    }

    /**
     * Obtiene el valor de la propiedad responsable.
     * 
     * @return
     *     possible object is
     *     {@link Responsable }
     *     
     */
    public Responsable getResponsable() {
        return responsable;
    }

    /**
     * Define el valor de la propiedad responsable.
     * 
     * @param value
     *     allowed object is
     *     {@link Responsable }
     *     
     */
    public void setResponsable(Responsable value) {
        this.responsable = value;
    }

}
