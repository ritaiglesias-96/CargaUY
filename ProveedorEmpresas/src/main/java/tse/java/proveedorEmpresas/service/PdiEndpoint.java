package tse.java.proveedorEmpresas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pdi.GetCiudadanoRequest;
import pdi.GetCiudadanoResponse;
import pdi.GetEmpresaRequest;
import pdi.GetEmpresaResponse;

@Endpoint
public class PdiEndpoint {
    private PdiRepository pdiRepository;

    @Autowired
    public PdiEndpoint(PdiRepository pdiRepository) {
        this.pdiRepository = pdiRepository;
    }

    @PayloadRoot(localPart = "getEmpresaRequest", namespace = "/pdi")
    @ResponsePayload
    public GetEmpresaResponse getEmpresaRequest(@RequestPayload GetEmpresaRequest request) {
        GetEmpresaResponse response = new GetEmpresaResponse();
        System.out.println(request.getRut());
        response.setEmpresa(pdiRepository.findEmpresa(request.getRut()));
        return response;
    }

    @PayloadRoot(localPart = "getCiudadanoRequest", namespace = "/pdi")
    @ResponsePayload
    public GetCiudadanoResponse getCiudadanoRequest(@RequestPayload GetCiudadanoRequest request) {
        GetCiudadanoResponse response = new GetCiudadanoResponse();
        System.out.println(request.getCedula());
        response.setCiudadano(pdiRepository.findCiudadano(request.getCedula()));
        return response;
    }
}
