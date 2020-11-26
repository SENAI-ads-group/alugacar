package entidades.services.persistence;

import entidades.Veiculo;
import entidades.services.persistence.exceptions.PersistenceException;
import java.util.List;

/**
 *
 * @author Alexsander
 */
public interface VeiculoPersistenceService {

    void inserir(Veiculo veiculo)throws PersistenceException;

    void atualizar(Veiculo veiculo);

    Veiculo buscar(String placa);
    

    List<Veiculo> buscarTodos();

}
