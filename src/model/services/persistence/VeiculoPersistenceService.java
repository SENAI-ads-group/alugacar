package model.services.persistence;

import model.entidades.Veiculo;
import java.util.List;
import model.services.persistence.exceptions.PersistenceException;

/**
 *
 * @author Alexsander
 */
public interface VeiculoPersistenceService {

    void inserir(Veiculo veiculo) throws PersistenceException;

    void atualizar(Veiculo veiculo);

    Veiculo buscar(Integer id);

    Veiculo buscar(String placa);

    List<Veiculo> buscarTodos();

}
