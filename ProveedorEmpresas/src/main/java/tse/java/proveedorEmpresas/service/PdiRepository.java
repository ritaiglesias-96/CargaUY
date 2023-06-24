package tse.java.proveedorEmpresas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import pdi.Ciudadano;
import pdi.Empresa;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Component
public class PdiRepository {
    private static Empresa[] empresaList = new Empresa[]{};
    private static Ciudadano[] ciudadanoList = new Ciudadano[]{};

    ObjectMapper objectMapper = new ObjectMapper();

    public PdiRepository() throws IOException {
    }

    @PostConstruct
    public void initData() throws IOException {
        File empresasData = new ClassPathResource("empresas.json").getFile();
        File ciudadanosData = new ClassPathResource("ciudadanos.json").getFile();
        empresaList = objectMapper.readValue(empresasData, Empresa[].class);
        ciudadanoList = objectMapper.readValue(ciudadanosData, Ciudadano[].class);
        for (Empresa e:empresaList
             ) {
            System.out.println(e.getRut());

        }
        for (Ciudadano e:ciudadanoList
        ) {
            System.out.println(e.getCedula());

        }
    }

    public Empresa findEmpresa(String rut) {
        for (Empresa i : empresaList) {
            if(Objects.equals(i.getRut(), rut)){
                return i;
            }
        }
        return null;
    }

    public Ciudadano findCiudadano(String cedula) {
        for (Ciudadano i : ciudadanoList) {
            if(Objects.equals(i.getCedula(), cedula)){
                return i;
            }
        }
        return null;
    }
}
