package tse.java.persistance.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

        //return em.find(Empresa.class, id);

        Query q = em.createNativeQuery("select * from public.\"Empresa\" where id = " + id, Empresa.class);

        try {

            Empresa result = (Empresa) q.getSingleResult();
            result.setId(id);
            return new EmpresaDTO(result);

        } catch (NoResultException e) {
            throw e;
        }
    }

    @Override
    public ArrayList<EmpresaDTO> obtenerEmpresas() {

        Query q  = em.createNativeQuery("select * from public.\"Empresa\"",Empresa.class);

        List<Empresa> retorno = q.getResultList();

        ArrayList<EmpresaDTO> ret = new ArrayList<>();

        retorno.forEach(e -> ret.add(new EmpresaDTO(e)));
        return ret;
    }

    @Override
    public void guardarEmpresa(String nombrePublico, String razonSocial, int nroEmpresa, String dirPrincipal) {
        Empresa e = new Empresa(nombrePublico,razonSocial,nroEmpresa,dirPrincipal);
        em.persist(e);

    }

    @Override
    public Empresa modificarEmpresa(EmpresaDTO empresaDTO) {
        Empresa e = em.find(Empresa.class,empresaDTO.getId());
        if (e.getVehiculos().size() != empresaDTO.getVehiculos().size()) {
            List<VehiculoDTO> vehiculos = empresaDTO.getVehiculos();
            List<Vehiculo> vehiculosADevolver = new ArrayList<Vehiculo>();
            for(VehiculoDTO v:vehiculos){
                Vehiculo insertar = new Vehiculo(v);
                insertar.setId(v.getId());
                vehiculosADevolver.add(insertar);
            }
            e.setVehiculos(vehiculosADevolver);
        }
        em.merge(e);
        return e;
    }

    @Override
    public void eliminarEmpresa(EmpresaDTO empresaDTO) {
        Empresa e = em.find(Empresa.class,empresaDTO.getId());
        if(e!=null) {
            em.remove(e);
        }

    }

    @Override
    public EmpresaDTO obtenerEmpresaPorNumero(int numero_empresa) {
        Query q = em.createQuery("select e from Empresa e where e.nroEmpresa=" + numero_empresa);
        if(q.getResultList().isEmpty()) {
            return null;
        } else {
            Empresa e = (Empresa) q.getResultList().get(0);
            return new EmpresaDTO(e);
        }
    }
}