
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
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="nombrePublico" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="numeroEmpresa" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="dirPrincipal" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="razonSocial" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "id",
    "nombrePublico",
    "numeroEmpresa",
    "dirPrincipal",
    "razonSocial"
})
public class Empresa {

    protected int id;
    @XmlElement(required = true)
    protected String nombrePublico;
    protected int numeroEmpresa;
    @XmlElement(required = true)
    protected String dirPrincipal;
    @XmlElement(required = true)
    protected String razonSocial;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
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
     * Gets the value of the numeroEmpresa property.
     * 
     */
    public int getNumeroEmpresa() {
        return numeroEmpresa;
    }

    /**
     * Sets the value of the numeroEmpresa property.
     * 
     */
    public void setNumeroEmpresa(int value) {
        this.numeroEmpresa = value;
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

}
