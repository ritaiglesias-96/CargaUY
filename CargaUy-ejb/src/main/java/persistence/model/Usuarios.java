package persistence.model;

import dto.UsuarioDTO;
import java.io.Serializable;
import java.util.ArrayList;


public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;

    private ArrayList<UsuarioDTO> listaUsuarios;
    private int totalRows;

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public ArrayList<UsuarioDTO> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<UsuarioDTO> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
}
