package tse.java.model;

import java.io.Serializable;
import java.util.ArrayList;

import tse.java.dto.EmpresaDTO;

public class Empresas implements Serializable {

    private static final long serialVersionUID = 1L;

    private ArrayList<EmpresaDTO> listaEmpresas;
    private int totalRows;

    public ArrayList<EmpresaDTO> getListaEmpresas() {
        return listaEmpresas;
    }
    public void setListaEmpresas(ArrayList<EmpresaDTO> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }
    public int getTotalRows() {
        return totalRows;
    }
    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }
}