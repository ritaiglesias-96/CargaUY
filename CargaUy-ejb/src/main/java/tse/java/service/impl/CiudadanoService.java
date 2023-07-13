package tse.java.service.impl;

import tse.java.dto.*;
import tse.java.entity.*;
import tse.java.enumerated.RolCiudadano;
import tse.java.model.Ciudadanos;
import tse.java.persistance.*;
import tse.java.service.ICiudadanosService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.List;

@Stateless
@Named("ciudadanosService")
public class CiudadanoService implements ICiudadanosService {
    @EJB
    ICiudadanoDAO ciudadanoDAO;
    @EJB
    IFuncionarioDAO funcionarioDAO;
    @EJB
    IChoferDAO choferDAO;
    @EJB
    IResponsableDAO responsableDAO;

    @EJB
    IEmpresasDAO empresasDAO;

    @Override
    public Ciudadano obtenerCiudadano(int id) {
        return ciudadanoDAO.buscarCiudadanoPorId(id);
    }

    @Override
    public Ciudadano obtenerCiudadanoPorCedula(String cedula) {
        return ciudadanoDAO.buscarCiudadanoPorCedula(cedula);
    }

    @Override
    public Ciudadanos obtenerCiudadanos() {
        Ciudadanos c = new Ciudadanos();
        c.setListaCiudadanos(ciudadanoDAO.listarCiudadanos());
        return c;
    }

    @Override
    public ChoferDTO obtenerChofer(String cedulaChofer) {
        Ciudadano c = ciudadanoDAO.buscarCiudadanoPorCedula(cedulaChofer);
        if (c instanceof Chofer) {
            return ((Chofer) c).darDTO();
        } else {
            return null;
        }
    }

    @Override
    public void agregarCiudadano(Ciudadano ciudadano) {
        ciudadanoDAO.agregarCiudadano(ciudadano);
    }

    @Override
    public void modificarCiudadano(Ciudadano ciudadano) {
        ciudadanoDAO.modificarCiudadano(ciudadano);
    }

    @Override
    public void eliminarCiudadano(int id) {
        ciudadanoDAO.eliminiarCiudadano(id);
    }

    @Override
    public void agregarHijoCiudadano(Ciudadano ciudadano) {
        if (ciudadano.getRol() == RolCiudadano.FUNCIONARIO) {
            funcionarioDAO.agregarFuncionario((Funcionario) ciudadano);
        } else if (ciudadano.getRol() == RolCiudadano.RESPONSABLE) {
            ciudadano = new Responsable(ciudadano.getEmail(), ciudadano.getCedula(), ciudadano.getNombre(), ciudadano.getApellido());
            responsableDAO.agregarResponsable((Responsable) ciudadano);
        } else {
            ciudadano = new Chofer(ciudadano.getEmail(), ciudadano.getCedula(), ciudadano.getNombre(), ciudadano.getApellido());
            choferDAO.agregarChofer((Chofer) ciudadano);
        }
    }

    @Override
    public void modificarHijoCiudadano(Ciudadano ciudadano) {
        if (ciudadano instanceof Funcionario) {
            funcionarioDAO.modificarFuncionario((Funcionario) ciudadano);
        } else if (ciudadano instanceof Responsable) {
            responsableDAO.modificarResponsable((Responsable) ciudadano);
        } else {
            choferDAO.modificarChofer((Chofer) ciudadano);
        }
    }

    @Override
    public void eliminarHijoCiudadano(int id) {
        Ciudadano ciudadano = ciudadanoDAO.buscarCiudadanoPorId(id);
        if (ciudadano instanceof Funcionario) {
            funcionarioDAO.eliminiarFuncionario((Funcionario) ciudadano);
        } else if (ciudadano instanceof Responsable) {
            responsableDAO.eliminiarResponsable((Responsable) ciudadano);
        } else {
            choferDAO.eliminiarChofer((Chofer) ciudadano);
        }
    }

    @Override
    public void asignarEmpresa(int responsableId, Empresa empresa) {
        responsableDAO.asignarEmpresa(responsableId, empresa);
    }

    @Override
    public void eliminarEmpresa(int responsableId, Empresa empresa) {
        responsableDAO.eliminarEmpresa(responsableId, empresa);
    }

    @Override
    public void asignarEmpresaChofer(int choferId, Empresa empresa) {
        choferDAO.asignarEmpresaChofer(choferId, empresa);
    }

    @Override
    public void eliminarEmpresaChofer(int choferId, Empresa empresa) {
        choferDAO.eliminarEmpresaChofer(choferId, empresa);
    }

    @Override
    public void asingarViajeChofer(int chofer_id, Asignacion a) {
        Chofer chofer = (Chofer) ciudadanoDAO.buscarCiudadanoPorId(chofer_id);
        chofer.getAsignaciones().add(a);
        choferDAO.modificarChofer(chofer);
    }

    @Override
    public boolean contieneGuiaViajeChofer(String cedula_chofer, int numero_viaje) {
        ChoferDTO c = choferDAO.buscarChoferPorCedula(cedula_chofer);
        for (AsignacionDTO a : c.getAsignaciones()) {
            if (a.getGuia().getNumero() == numero_viaje)
                return true;
        }
        return false;
    }

    @Override
    public EmpresaDTO obtenerEmpresaPorResponsable(String cedulaResponsable){
        return ciudadanoDAO.obtenerEmpresaPorResponsable(cedulaResponsable);
    }

}
