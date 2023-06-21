package tse.java.service.impl;

import tse.java.dto.*;
import tse.java.entity.*;
import tse.java.enumerated.RolCiudadano;
import tse.java.model.Ciudadanos;
import tse.java.persistance.*;
import tse.java.persistance.impl.CiudadanoDAO;
import tse.java.persistance.impl.FuncionarioDAO;
import tse.java.persistance.impl.ResponsableDAO;
import tse.java.service.IAsignacionesService;
import tse.java.service.ICiudadanosService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @EJB
    IAsignacionesService asignacionesService;

    @Override
    public Ciudadanos obtenerCiudadanos() {
        Ciudadanos c = new Ciudadanos();
        c.setListaCiudadanos(ciudadanoDAO.listarCiudadanos());
        return c;
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
    public void eliminarCiudadano(Ciudadano ciudadano) {
        ciudadanoDAO.eliminiarCiudadano(ciudadano);
    }

    @Override
    public void agregarHijoCiudadano(Ciudadano ciudadano) {
        if (ciudadano.getRol() == RolCiudadano.FUNCIONARIO) {
            funcionarioDAO.agregarFuncionario((Funcionario) ciudadano);
        } else if (ciudadano.getRol() == RolCiudadano.RESPONSABLE) {
            ciudadano = new Responsable(ciudadano.getEmail(), ciudadano.getCedula());
            responsableDAO.agregarResponsable((Responsable) ciudadano);
        } else {
            ciudadano = new Chofer(ciudadano.getEmail(), ciudadano.getCedula());
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
    public void eliminarHijoCiudadano(Ciudadano ciudadano) {
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
        List<Asignacion> asignaciones = chofer.getAsignaciones();
        asignaciones.add(a);
        chofer.setAsignaciones(asignaciones);
        choferDAO.modificarChofer(chofer);
    }

    @Override
    public boolean contieneGuiaViajeChofer(String cedula_chofer, int numero_viaje) {
        ChoferDTO c = choferDAO.buscarChoferPorCedula(cedula_chofer);
        for(AsignacionDTO a : c.getAsignaciones())
            if(a.getGuia().getNumero() == numero_viaje)
                return true;
        return false;
    }

    @Override
    public void borrarGuia(int numero_guia) {
        GuiaDeViajeDTO g = guiaDAO.buscarGuiaViajePorNumero(numero_guia);
        GuiaDeViaje gnew = guiaDAO.buscarGuiaDeViaje(g.getId());
        for(CiudadanoDTO c:ciudadanoDAO.listarCiudadanos()){
            if(c.getRol()==RolCiudadano.CHOFER){
                Chofer ch = (Chofer) ciudadanoDAO.buscarCiudadanoPorId(c.getIdCiudadano());
                List<Asignacion> asignaciones = ch.getAsignaciones();
                asignaciones.removeAll(listaAsignacionesConGuia(ch,numero_guia));
                ch.setAsignaciones(asignaciones);
                choferDAO.modificarChofer(ch);
            }
        }
    }

    // Auxiliar
    private List<Asignacion> listaAsignacionesConGuia(Chofer c, int numeroGuia){
        List<Asignacion> result = new ArrayList<Asignacion>();
        for(Asignacion a:c.getAsignaciones()){
            if(a.getGuia().getNumero()==numeroGuia)
                result.add(a);
        }
        return result;
    }

    private Asignacion buscarGuiaenChofer(Chofer c, GuiaDeViajeDTO g) {
        for(Asignacion a:c.getAsignaciones())
            if(a.getGuia().getNumero()==g.getNumero())
                return a;
        return null;
    }

}
