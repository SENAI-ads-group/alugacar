package model.entidades.services.persistence;

import model.entidades.Veiculo;
import java.util.List;

/**
 *
 * @author Alexsander
 */
public interface VeiculoPersistenceService {

    void inserir(Veiculo veiculo);

    void atualizar(Veiculo veiculo);

    Veiculo buscar(Integer id);

    List<Veiculo> buscarTodos();

}
