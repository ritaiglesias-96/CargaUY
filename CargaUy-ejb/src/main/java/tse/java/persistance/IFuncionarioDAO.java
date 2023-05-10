package tse.java.persistance;

import tse.java.entity.Funcionario;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IFuncionarioDAO {

    void persist(Funcionario funcionario);

    void merge(Funcionario funcionario);

    List<Funcionario> findAll();

    void delete(Funcionario funcionario);

    Funcionario findById(Integer id);

}
