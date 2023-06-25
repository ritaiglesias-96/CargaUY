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
    public Ciudadano obtenerCiudadano(int id) {
        return ciudadanoDAO.buscarCiudadanoPorId(id);
    }

    @Override
    public Ciudadano obtenerCiudadanoPorCedula(Integer cedula) {
        return ciudadanoDAO.buscarCiudadanoPorCedula(cedula);
    }

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
    public void eliminarCiudadano(int id) {
        ciudadanoDAO.eliminiarCiudadano(id);
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
        List<Asignacion> asignaciones = chofer.getAsignaciones();
        asignaciones.add(a);
        chofer.setAsignaciones(asignaciones);
        choferDAO.modificarChofer(chofer);
    }

    /*  @Override
    public void asingarViajeResponsable(int responsable_id, Asignacion a) {
        Responsable responsable = (Responsable) ciudadanoDAO.buscarCiudadanoPorId(responsable_id);
        List<Asignacion> asignaciones = responsable.getAsignaciones();
        asignaciones.add(a);
        responsable.setAsignaciones(asignaciones);
        responsableDAO.modificarResponsable(responsable);
    }*/

    @Override
    public boolean contieneGuiaViajeChofer(Integer cedula_chofer, int numero_viaje) {
        Long id = asignacionesService.ultimaAsignacionViaje(numero_viaje);
        ChoferDTO c = choferDAO.buscarChoferPorCedula(cedula_chofer);
        for(AsignacionDTO a : c.getAsignaciones())
            if(a.getId() == id)
                return true;
        return false;
    }

  /*  @Override
    public boolean contieneGuiaViajeResponsable(String cedula_responsable, int numero_viaje) {
        Long id = asignacionesService.ultimaAsignacionViaje(numero_viaje);
        ResponsableDTO r = responsableDAO.buscarResponsablePorCedula(cedula_responsable);
        for(AsignacionDTO a : r.getAsignaciones())
            if(a.getId() == id)
                return true;
        return false;
    }
*/
    @Override
    public void borrarGuia(int numero_guia) {
        GuiaDeViajeDTO g = guiaDAO.buscarGuiaViajePorNumero(numero_guia);
        GuiaDeViaje gnew = guiaDAO.buscarGuiaDeViaje(g.getId());
        for(CiudadanoDTO c:ciudadanoDAO.listarCiudadanos()){
            /*if(responsableDAO.buscarResponsablePorCedula(c.getCedula())!=null){
                if(contieneGuiaViajeResponsable(c.getCedula(),numero_guia)){
                    Responsable r = (Responsable) ciudadanoDAO.buscarCiudadanoPorId(c.getIdCiudadano());
                    List<Asignacion> asignaciones = r.getAsignaciones();
                    asignaciones.remove(buscarGuiaenResponsable(r,g));
                    r.setAsignaciones(asignaciones);
                    responsableDAO.modificarResponsable(r);
                }
            } else {*/
                if(contieneGuiaViajeChofer(c.getCedula(),numero_guia)){
                    Chofer ch = (Chofer) ciudadanoDAO.buscarCiudadanoPorId(c.getIdCiudadano());
                    List<Asignacion> asignaciones = ch.getAsignaciones();
                    asignaciones.remove(buscarGuiaenChofer(ch,g));
                    ch.setAsignaciones(asignaciones);
                    choferDAO.modificarChofer(ch);
                }
            //}
        }
    }

    private Asignacion buscarGuiaenChofer(Chofer c, GuiaDeViajeDTO g) {
        for(Asignacion a:c.getAsignaciones())
            if(a.getGuia().getNumero()==g.getNumero())
                return a;
        return null;
    }

   /* private Asignacion buscarGuiaenResponsable(Responsable r, GuiaDeViajeDTO g) {
        for(Asignacion a:r.getAsignaciones())
            if(a.getGuia().getNumero()==g.getNumero())
                return a;
        return null;
    }*/
}
