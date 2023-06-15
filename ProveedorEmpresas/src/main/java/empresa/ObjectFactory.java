
package empresa;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the empresa package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: empresa
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetEmpresaRequest }
     * 
     */
    public GetEmpresaRequest createGetEmpresaRequest() {
        return new GetEmpresaRequest();
    }

    /**
     * Create an instance of {@link GetEmpresaResponse }
     * 
     */
    public GetEmpresaResponse createGetEmpresaResponse() {
        return new GetEmpresaResponse();
    }

    /**
     * Create an instance of {@link Empresa }
     * 
     */
    public Empresa createEmpresa() {
        return new Empresa();
    }

    /**
     * Create an instance of {@link Responsable }
     * 
     */
    public Responsable createResponsable() {
        return new Responsable();
    }

}
