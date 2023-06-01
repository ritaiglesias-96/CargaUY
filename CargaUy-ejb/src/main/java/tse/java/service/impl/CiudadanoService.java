package tse.java.service.impl;

import tse.java.dto.*;
import tse.java.entity.*;
import tse.java.model.Ciudadanos;
import tse.java.persistance.*;
import tse.java.persistance.impl.CiudadanoDAO;
import tse.java.persistance.impl.FuncionarioDAO;
import tse.java.service.ICiudadanosService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.text.SimpleDateFormat;
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
    IGuiaDeViajeDAO guiaDAO;

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

    @Override
    public boolean contieneGuiaViajeChofer(String cedula_chofer, int numero_viaje) {
        ChoferDTO c = choferDAO.buscarChoferPorCedula(cedula_chofer);
        for(GuiaDeViajeDTO g : c.getGuiasDeViaje())
            if(g.getNumero() == numero_viaje)
                return true;
        return false;
    }

    @Override
    public boolean contieneGuiaViajeResponsable(String cedula_responsable, int numero_viaje) {
        ResponsableDTO r = responsableDAO.buscarResponsablePorCedula(cedula_responsable);
        for(GuiaDeViajeDTO g : r.getGuiasDeViaje())
            if(g.getNumero() == numero_viaje)
                return true;
        return false;
    }

    @Override
    public void borrarGuia(int numero_guia) {
        GuiaDeViajeDTO g = guiaDAO.buscarGuiaViajePorNumero(numero_guia);
        GuiaDeViaje gnew = guiaDAO.buscarGuiaDeViaje(g.getId());
        for(CiudadanoDTO c:ciudadanoDAO.listarCiudadanos()){
            if(responsableDAO.buscarResponsablePorCedula(c.getCedula())!=null){
                if(contieneGuiaViajeResponsable(c.getCedula(),numero_guia)){
                    Responsable r = (Responsable) ciudadanoDAO.buscarCiudadanoPorId(c.getIdCiudadano());
                    List<GuiaDeViaje> guias = r.getGuiasDeViaje();
                    guias.remove(gnew);
                    r.setGuiasDeViaje(guias);
                    responsableDAO.modificarResponsable(r);
                }
            } else {
                if(contieneGuiaViajeChofer(c.getCedula(),numero_guia)){
                    Chofer ch = (Chofer) ciudadanoDAO.buscarCiudadanoPorId(c.getIdCiudadano());
                    List<GuiaDeViaje> guias = ch.getGuiasDeViaje();
                    guias.remove(gnew);
                    ch.setGuiasDeViaje(guias);
                    choferDAO.modificarChofer(ch);
                }
            }
        }
    }
}
