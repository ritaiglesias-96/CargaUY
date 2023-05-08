package tse.java.model;


import tse.java.dto.VehiculoDTO;

import java.io.Serializable;
import java.util.ArrayList;

public class Vehiculos implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<VehiculoDTO> listaVehiculos;
    public void setListaVehiculos(ArrayList<VehiculoDTO> listaVehiculos) { this.listaVehiculos = listaVehiculos; }
    public ArrayList<VehiculoDTO> getListaVehiculos() { return listaVehiculos; }

}
