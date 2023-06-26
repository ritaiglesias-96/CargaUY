package tse.java.persistance.impl;

import tse.java.dto.ChoferDTO;
import tse.java.dto.CiudadanoDTO;
import tse.java.dto.EmpresaDTO;
import tse.java.entity.Chofer;
import tse.java.entity.Ciudadano;
import tse.java.entity.Empresa;
import tse.java.persistance.ICiudadanoDAO;
import tse.java.util.qualifier.TSE2023DB;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Local
public class CiudadanoDAO implements ICiudadanoDAO {

    @TSE2023DB
    @Inject
    public EntityManager em;


    @Override
    public ArrayList<CiudadanoDTO> listarCiudadanos() {
        Query query = em.createNativeQuery("select * from public.\"Ciudadano\"", Ciudadano.class);
        List<Ciudadano> retorno = query.getResultList();
        ArrayList<CiudadanoDTO> ret = new ArrayList<>();
        retorno.forEach(c->ret.add(new CiudadanoDTO(c)));
        return ret;
    }

    @Override
    public void agregarCiudadano(Ciudadano ciudadano) {
        em.persist(ciudadano);
    }

    @Override
    public void modificarCiudadano(Ciudadano ciudadano) {
        em.merge(ciudadano);
    }

    @Override
    public void eliminiarCiudadano(Ciudadano ciudadano) {
        Ciudadano c = em.find(Ciudadano.class, ciudadano.getIdCiudadano());
        if(c!=null){
            em.remove(c);
        }
    }

    @Override
    public Ciudadano buscarCiudadanoPorId(int id) {
        return em.find(Ciudadano.class, id);
    }

    @Override
    public Ciudadano buscarCiudadanoPorCedula(String cedula) {
        Query q = em.createQuery("select c from Ciudadano c where c.cedula='" + cedula +"'");
        if (q.getResultList().isEmpty()) {
            System.out.println("Esta vacio");
            return null;
        } else {
            Ciudadano c = (Ciudadano) q.getResultList().get(0);
            System.out.println(c.getCedula());
            return c;
        }
    }
}
