package tse.java.persistance.impl;

import tse.java.entity.Ciudadano;
import tse.java.entity.Funcionario;
import tse.java.persistance.IFuncionarioDAO;
import tse.java.util.qualifier.TSE2023DB;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local
public class FuncionarioDAO implements IFuncionarioDAO {

    @TSE2023DB
    @Inject
    public EntityManager em;

    @Override
    public List<Funcionario> findAll() {
        Query q = em.createNativeQuery("select * from public.\"ciudadano\" where rol='Funcionario'", Ciudadano.class);
        return q.getResultList();
    }

    @Override
    public void agregarFuncionario(Funcionario funcionario) {
        em.persist(funcionario);
    }

    @Override
    public void modificarFuncionario(Funcionario funcionario) {
        em.merge(funcionario);
    }

    @Override
    public void eliminiarFuncionario(Funcionario funcionario) {
        Funcionario f = em.find(Funcionario.class,funcionario.getIdCiudadano());
        if(f!=null){
            em.remove(f);
        }
    }
}
