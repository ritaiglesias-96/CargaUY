package tse.java.proveedorEmpresas.service;

import empresa.Empresa;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EmpresaRepository {
    private static final Map<Integer, Empresa> empresas = new HashMap<>();

    @PostConstruct
    public void initData() {
        Empresa e1 = new Empresa();
        e1.setId(1);
        e1.setNombrePublico("Tata");
        e1.setRazonSocial("TataSA");
        e1.setDirPrincipal("18 de Julio 4444");
        e1.setNumeroEmpresa(1);

        empresas.put(e1.getId(), e1);

        Empresa e2 = new Empresa();
        e2.setId(2);
        e2.setNombrePublico("Tata");
        e2.setRazonSocial("TataSA");
        e2.setDirPrincipal("18 de Julio 4444");
        e2.setNumeroEmpresa(2);

        empresas.put(e2.getId(), e2);

        Empresa e3 = new Empresa();
        e3.setId(3);
        e3.setNombrePublico("Tata");
        e3.setRazonSocial("TataSA");
        e3.setDirPrincipal("18 de Julio 4444");
        e3.setNumeroEmpresa(3);

        empresas.put(e3.getId(), e3);

        Empresa e4 = new Empresa();
        e4.setId(4);
        e4.setNombrePublico("Tata");
        e4.setRazonSocial("TataSA");
        e4.setDirPrincipal("18 de Julio 4444");
        e4.setNumeroEmpresa(4);

        empresas.put(e4.getId(), e4);

        Empresa e5 = new Empresa();
        e5.setId(5);
        e5.setNombrePublico("Tata");
        e5.setRazonSocial("TataSA");
        e5.setDirPrincipal("18 de Julio 4444");
        e5.setNumeroEmpresa(5);

        empresas.put(e5.getId(), e5);

        Empresa e6 = new Empresa();
        e6.setId(6);
        e6.setNombrePublico("Tata");
        e6.setRazonSocial("TataSA");
        e6.setDirPrincipal("18 de Julio 4444");
        e6.setNumeroEmpresa(6);

        empresas.put(e6.getId(), e6);

    }

    public Empresa findEmpresa(int id) {
        return empresas.get(id);
    }
}
