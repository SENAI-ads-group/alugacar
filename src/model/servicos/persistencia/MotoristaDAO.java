package model.servicos.persistencia;

import model.entidades.Motorista;
import model.exceptions.PersistenciaException;
import java.util.List;

/**
 *
 * @author Alexsander
 */
public interface MotoristaDAO {

    void inserir(Motorista motorista) throws PersistenciaException;

    void atualizar(Motorista motorista);

    Motorista buscar(Integer id);

    List<Motorista> buscarTodos();

}
