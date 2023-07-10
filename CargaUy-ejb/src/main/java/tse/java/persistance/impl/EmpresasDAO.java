package tse.java.persistance.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import tse.java.dto.EmpresaDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.entity.Empresa;
import tse.java.entity.Pesaje;
import tse.java.entity.Vehiculo;
import tse.java.persistance.IEmpresasDAO;
import tse.java.util.qualifier.TSE2023DB;

@Stateless
public class EmpresasDAO implements IEmpresasDAO {

    @TSE2023DB
    @Inject
    public EntityManager em;

    @Override
    public EmpresaDTO obtenerEmpresaPorId(int id) {
        Empresa e = em.find(Empresa.class, id);
        if(e != null){
            return new EmpresaDTO(e);
        }else {
            return null;
        }
    }

    @Override
    public ArrayList<EmpresaDTO> obtenerEmpresas() {

        Query q = em.createNativeQuery("select * from public.\"Empresa\"", Empresa.class);

        List<Empresa> retorno = q.getResultList();

        ArrayList<EmpresaDTO> ret = new ArrayList<>();

        retorno.forEach(e -> ret.add(new EmpresaDTO(e)));
        return ret;
    }

    @Override
    public void guardarEmpresa(String nombrePublico, String razonSocial, int nroEmpresa, String dirPrincipal) {
        Empresa e = new Empresa(nombrePublico, razonSocial, nroEmpresa, dirPrincipal);
        em.persist(e);

    }

    @Override
    public Empresa modificarEmpresa(EmpresaDTO empresaDTO) {
        Empresa e = new Empresa(empresaDTO);
        em.getTransaction().begin();
        em.merge(e);
        em.getTransaction().commit();
        return e;
    }

    @Override
    public void eliminarEmpresa(int id) {
        Empresa e = em.find(Empresa.class,id);
        if(e!=null) {
            em.remove(e);
        }

    }

    @Override
    public EmpresaDTO obtenerEmpresaPorNumero(int numero_empresa) {
        Query q = em.createQuery("select e from Empresa e where e.nroEmpresa=" + numero_empresa);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            Empresa e = (Empresa) q.getResultList().get(0);
            return new EmpresaDTO(e);
        }
    }

    @Override
    public void eliminarVehiculo(int idEmpresa, int idVehiculo) {
        Empresa e = em.find(Empresa.class, idEmpresa);
        if(!e.getVehiculos().isEmpty()){
            Vehiculo v = em.find(Vehiculo.class, idVehiculo);
            if (v != null) {
                e.getVehiculos().remove(v);
                em.merge(e);
            }

        }
    }

    @Override
    public void agregarVehiculo(int idEmpresa, VehiculoDTO vehiculoDTO) {
        Empresa e = em.find(Empresa.class, idEmpresa);
        Vehiculo v = em.find(Vehiculo.class, vehiculoDTO.getId());
        if(v != null) {
            e.getVehiculos().add(v);
            em.merge(e);
        } else {
            Vehiculo vnuevo = new Vehiculo(vehiculoDTO);
            em.persist(vnuevo);
            e.getVehiculos().add(vnuevo);
            em.merge(e);
        }

    }

}