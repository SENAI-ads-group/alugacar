package model.servicos.persistencia;

import model.entidades.Categoria;
import model.exceptions.PersistenciaException;

import java.util.List;

/**
 *
 * @author Alexsander
 */
public interface CategoriaDAO {

    void inserir(Categoria categoria) throws PersistenciaException;

    void atualizar(Categoria categoria);

    Categoria buscar(Integer id);

    List<Categoria> buscarTodos();

}
