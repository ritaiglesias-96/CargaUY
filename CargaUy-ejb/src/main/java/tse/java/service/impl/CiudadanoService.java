package tse.java.service.impl;

import tse.java.dto.ChoferDTO;
import tse.java.dto.FuncionarioDTO;
import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.ResponsableDTO;
import tse.java.entity.*;
import tse.java.model.Ciudadanos;
import tse.java.persistance.IChoferDAO;
import tse.java.persistance.ICiudadanoDAO;
import tse.java.persistance.IFuncionarioDAO;
import tse.java.persistance.IResponsableDAO;
import tse.java.persistance.impl.CiudadanoDAO;
import tse.java.persistance.impl.FuncionarioDAO;
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

    @Override
    public Ciudadanos obtenerCiudadanos() {
        Ciudadanos c = new Ciudadanos();
        c.setListaCiudadanos(ciudadanoDAO.listarCiudadanos());
        return c;
    }

    @Override
    public void agregarCiudadano(Ciudadano ciudadano) {
        if (ciudadano instanceof Funcionario) {
            funcionarioDAO.agregarFuncionario((Funcionario) ciudadano);
        } else if (ciudadano instanceof Responsable) {
            ciudadano = new Responsable(ciudadano.getEmail(), ciudadano.getCedula());
            responsableDAO.agregarResponsable((Responsable) ciudadano);
        } else {
            ciudadano = new Chofer(ciudadano.getEmail(), ciudadano.getCedula());
            choferDAO.agregarChofer((Chofer) ciudadano);
        }
    }

    @Override
    public void modificarCiudadano(Ciudadano ciudadano) {
        if (ciudadano instanceof Funcionario) {
            funcionarioDAO.modificarFuncionario((Funcionario) ciudadano);
        } else if (ciudadano instanceof Responsable) {
            responsableDAO.modificarResponsable((Responsable) ciudadano);
        } else {
            choferDAO.modificarChofer((Chofer) ciudadano);
        }
    }

    @Override
    public void eliminarCiudadano(Ciudadano ciudadano) {
        if (ciudadano instanceof Funcionario) {
            funcionarioDAO.eliminiarFuncionario((Funcionario) ciudadano);
        } else if (ciudadano instanceof Responsable) {
            responsableDAO.eliminiarResponsable((Responsable) ciudadano);
        } else {
            choferDAO.eliminiarChofer((Chofer) ciudadano);
        }
    }

    @Override
    public void asingarViajeChofer(int chofer_id, GuiaDeViajeDTO g) {
        Chofer chofer = (Chofer) ciudadanoDAO.buscarCiudadanoPorId(chofer_id);
        List<GuiaDeViaje> guias = chofer.getGuiasDeViaje();
        GuiaDeViaje gnew = new GuiaDeViaje(g);
        guias.add(gnew);
        chofer.setGuiasDeViaje(guias);
        choferDAO.modificarChofer(chofer);
    }

    @Override
    public void asingarViajeResponsable(int responsable_id, GuiaDeViajeDTO g) {
        Responsable responsable = (Responsable) ciudadanoDAO.buscarCiudadanoPorId(responsable_id);
        List<GuiaDeViaje> guias = responsable.getGuiasDeViaje();
        GuiaDeViaje gnew = new GuiaDeViaje(g);
        guias.add(gnew);
        responsable.setGuiasDeViaje(guias);
        responsableDAO.modificarResponsable(responsable);
    }
}
