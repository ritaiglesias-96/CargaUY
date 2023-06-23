
package pdi;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 3.0.0
 * Generated source version: 3.0
 * 
 */
@WebService(name = "PdiPort", targetNamespace = "/pdi")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PdiPort {


    /**
     * 
     * @param getEmpresaRequest
     * @return
     *     returns pdi.GetEmpresaResponse
     */
    @WebMethod
    @WebResult(name = "getEmpresaResponse", targetNamespace = "/pdi", partName = "getEmpresaResponse")
    public GetEmpresaResponse getEmpresa(
        @WebParam(name = "getEmpresaRequest", targetNamespace = "/pdi", partName = "getEmpresaRequest")
        GetEmpresaRequest getEmpresaRequest);

    /**
     * 
     * @param getCiudadanoRequest
     * @return
     *     returns pdi.GetCiudadanoResponse
     */
    @WebMethod
    @WebResult(name = "getCiudadanoResponse", targetNamespace = "/pdi", partName = "getCiudadanoResponse")
    public GetCiudadanoResponse getCiudadano(
        @WebParam(name = "getCiudadanoRequest", targetNamespace = "/pdi", partName = "getCiudadanoRequest")
        GetCiudadanoRequest getCiudadanoRequest);

}
