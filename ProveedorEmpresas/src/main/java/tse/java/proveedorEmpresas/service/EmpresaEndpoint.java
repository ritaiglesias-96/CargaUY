package tse.java.proveedorEmpresas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import empresa.GetEmpresaRequest;
import empresa.GetEmpresaResponse;

@Endpoint
public class EmpresaEndpoint {
    private static final String NAMESPACE = "/empresa";

    private EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaEndpoint(EmpresaRepository eRepository) {
        this.empresaRepository = eRepository;
    }

    @PayloadRoot(localPart = "getEmpresaRequest", namespace = "/empresa")
    @ResponsePayload
    public GetEmpresaResponse getEmpresaRequest(@RequestPayload GetEmpresaRequest request) {
        GetEmpresaResponse response = new GetEmpresaResponse();
        response.setEmpresa(empresaRepository.findEmpresa(request.getCedula()));
        return response;
    }
}
