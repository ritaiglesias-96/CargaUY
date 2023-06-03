package tse.java.model;

import tse.java.dto.CiudadanoDTO;
import tse.java.dto.EmpresaDTO;

import java.io.Serializable;
import java.util.ArrayList;

public class Ciudadanos implements Serializable {

    private static final long serialVersionUID = 1L;

    private ArrayList<CiudadanoDTO> listaCiudadanos;

    public ArrayList<CiudadanoDTO> getListaCiudadanos() {
        return listaCiudadanos;
    }

    public void setListaCiudadanos(ArrayList<CiudadanoDTO> listaCiudadanos) {
        this.listaCiudadanos = listaCiudadanos;
    }
}
