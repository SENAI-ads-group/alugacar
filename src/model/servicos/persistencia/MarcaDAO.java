package model.servicos.persistencia;

import model.entidades.Marca;
import model.exceptions.PersistenciaException;
import java.util.List;

/**
 *
 * @author Patrick-Ribeiro
 */
public interface MarcaDAO {

    void inserir(Marca marca) throws PersistenciaException;

    void atualizar(Marca marca);

    Marca buscar(Integer id);

    List<Marca> buscarTodos();
}
