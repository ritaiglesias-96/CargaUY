
package empresa;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for empresa complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="empresa"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cedula" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="nombrePublico" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dirPrincipal" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="razonSocial" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="nroEmpresa" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="responsable" type="{/empresa}responsable" minOccurs="0"/&gt;
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
    "cedula",
    "nombrePublico",
    "dirPrincipal",
    "razonSocial",
    "nroEmpresa",
    "responsable"
})
public class Empresa {

    @XmlElement(required = true)
    protected String cedula;
    @XmlElement(required = true)
    protected String nombrePublico;
    @XmlElement(required = true)
    protected String dirPrincipal;
    @XmlElement(required = true)
    protected String razonSocial;
    protected int nroEmpresa;
    protected Responsable responsable;

    /**
     * Gets the value of the cedula property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Sets the value of the cedula property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCedula(String value) {
        this.cedula = value;
    }

    /**
     * Gets the value of the nombrePublico property.
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
     * Sets the value of the nombrePublico property.
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
     * Gets the value of the dirPrincipal property.
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
     * Sets the value of the dirPrincipal property.
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
     * Gets the value of the razonSocial property.
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
     * Sets the value of the razonSocial property.
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
     * Gets the value of the nroEmpresa property.
     * 
     */
    public int getNroEmpresa() {
        return nroEmpresa;
    }

    /**
     * Sets the value of the nroEmpresa property.
     * 
     */
    public void setNroEmpresa(int value) {
        this.nroEmpresa = value;
    }

    /**
     * Gets the value of the responsable property.
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
     * Sets the value of the responsable property.
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
