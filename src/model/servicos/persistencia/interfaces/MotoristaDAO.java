package model.servicos.persistencia.interfaces;

import model.entidades.Motorista;
import java.util.List;
import model.exceptions.DBException;

/**
 *
 * @author Alexsander
 */
public interface MotoristaDAO {

    void inserir(Motorista motorista) throws DBException;

    void atualizar(Motorista motorista);

    void excluir(int id);

    Motorista buscar(Integer id);

    List<Motorista> buscarTodos();

}
