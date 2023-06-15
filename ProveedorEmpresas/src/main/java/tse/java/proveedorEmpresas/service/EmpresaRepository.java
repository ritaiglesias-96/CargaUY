package tse.java.proveedorEmpresas.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import empresa.Empresa;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Component
public class EmpresaRepository {
    private static Empresa[] empresaList = new Empresa[]{};

    ObjectMapper objectMapper = new ObjectMapper();

    public EmpresaRepository() throws IOException {
    }

    @PostConstruct
    public void initData() throws IOException {
        File jsonFile = new ClassPathResource("data.json").getFile();

        empresaList = objectMapper.readValue(jsonFile, Empresa[].class);

        for (Empresa e:empresaList
             ) {
            System.out.println(e.getCedula());

        }
    }

    public Empresa findEmpresa(String cedula) {
        for (Empresa i : empresaList) {
            if(Objects.equals(i.getCedula(), cedula)){
                return i;
            }
        }
        return null;
    }
}
