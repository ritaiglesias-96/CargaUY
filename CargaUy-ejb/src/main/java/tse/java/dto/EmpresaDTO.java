package tse.java.dto;

import tse.java.entity.Empresa;

public class EmpresaDTO {
    private Integer id;
    private String nombrePublico;
    private String razonSocial;
    private int nroEmpresa;
    private String dirPrincipal;

    public EmpresaDTO(){
    }

    public EmpresaDTO(Empresa e){
        this.id = e.getId();
        this.nombrePublico = e.getNombrePublico();
        this.razonSocial = e.getRazonSocial();
        this.nroEmpresa = e.getNroEmpresa();
        this.dirPrincipal = e.getDirPrincipal();
    }

    public EmpresaDTO(Integer id,String nombrePublico, String razonSocial, int nroEmpresa, String dirPrincipal) {
        this.id = id;
        this.nombrePublico = nombrePublico;
        this.razonSocial = razonSocial;
        this.nroEmpresa = nroEmpresa;
        this.dirPrincipal = dirPrincipal;
    }

// Getters y Setters

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombrePublico() {
        return nombrePublico;
    }

    public void setNombrePublico(String nombrePublico) {
        this.nombrePublico = nombrePublico;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public int getNroEmpresa() {
        return nroEmpresa;
    }

    public void setNroEmpresa(int nroEmpresa) {
        this.nroEmpresa = nroEmpresa;
    }

    public String getDirPrincipal() {
        return dirPrincipal;
    }

    public void setDirPrincipal(String dirPrincipal) {
        this.dirPrincipal = dirPrincipal;
    }
}
