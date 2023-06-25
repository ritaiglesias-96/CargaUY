
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
@WebService(name = "EmpresaPort", targetNamespace = "/pdi")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface EmpresaPort {


    /**
     * 
     * @param getEmpresaRequest
     * @return
     *     returns empresa.GetEmpresaResponse
     */
    @WebMethod
    @WebResult(name = "getEmpresaResponse", targetNamespace = "/pdi", partName = "getEmpresaResponse")
    public GetEmpresaResponse getEmpresa(
        @WebParam(name = "getEmpresaRequest", targetNamespace = "/pdi", partName = "getEmpresaRequest")
        GetEmpresaRequest getEmpresaRequest);

}