package tse.java.persistance;

import tse.java.entity.Chofer;
import tse.java.entity.Funcionario;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IFuncionarioDAO {

    List<Funcionario> findAll();
    void agregarFuncionario(Funcionario funcionario);
    void modificarFuncionario(Funcionario funcionario);
    void eliminiarFuncionario(Funcionario funcionario);

}
