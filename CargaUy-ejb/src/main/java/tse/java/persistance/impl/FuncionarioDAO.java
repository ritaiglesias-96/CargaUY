package tse.java.persistance.impl;

import tse.java.entity.Funcionario;
import tse.java.persistance.IFuncionarioDAO;
import tse.java.util.qualifier.TSE2023DB;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class FuncionarioDAO implements IFuncionarioDAO {

    @TSE2023DB
    @Inject
    public EntityManager em;

    @Override
    public void persist(Funcionario funcionario) {
        em.persist(funcionario);
    }

    @Override
    public void merge(Funcionario funcionario) {
        em.merge(funcionario);
    }

    @Override
    public List<Funcionario> findAll() {
        Query q = em.createQuery("SELECT f FROM Funcionario f", Funcionario.class);
        return q.getResultList();
    }

    @Override
    public void delete(Funcionario funcionario) {
        em.remove(em.contains(funcionario) ? funcionario : em.merge(funcionario));
    }

    @Override
    public Funcionario findById(Integer id) {
        return em.find(Funcionario.class, id);
    }
}
