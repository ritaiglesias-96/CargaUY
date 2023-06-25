package tse.java.persistance.impl;

import tse.java.dto.ChoferDTO;
import tse.java.entity.*;
import tse.java.persistance.IChoferDAO;
import tse.java.util.qualifier.TSE2023DB;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local
public class ChoferDAO implements IChoferDAO {

    @TSE2023DB
    @Inject
    public EntityManager em;

    @Override
    public List<Chofer> findAll() {
        Query q = em.createNativeQuery("select * from public.\"ciudadano\" where rol='Chofer'", Ciudadano.class);
        return q.getResultList();
    }

    @Override
    public void agregarChofer(Chofer chofer) {
        em.persist(chofer);
    }

    @Override
    public void modificarChofer(Chofer chofer) {
        em.merge(chofer);
    }

    @Override
    public void eliminiarChofer(Chofer chofer) {
        Chofer c = em.find(Chofer.class,chofer.getIdCiudadano());
        if(c!=null){
            em.remove(c);
        }
    }

    @Override
    public void asignarEmpresaChofer(int id, Empresa empresa) {
        Chofer c = em.find(Chofer.class,id);
        List<Empresa> empresas = c.getEmpresas();
        empresas.add(empresa);
        c.setEmpresas(empresas);
        em.merge(c);
    }

    @Override
    public void eliminarEmpresaChofer(int id, Empresa empresa) {
        Chofer c = em.find(Chofer.class,id);
        List<Empresa> empresas = c.getEmpresas();
        empresas.remove(empresa);
        c.setEmpresas(empresas);
        em.merge(c);
    }

    public ChoferDTO buscarChoferPorCedula(Integer cedula){
        Query q = em.createQuery("select c from Ciudadano c where c.cedula='" + cedula + "'");
        if(q.getResultList().isEmpty()) {
            return null;
        } else {
            Object o = q.getResultList().get(0);
            if(o instanceof Chofer){
                Chofer c = (Chofer) o;
                return c.darDTO();
            } else {
                return null;
            }
        }
    }
}
