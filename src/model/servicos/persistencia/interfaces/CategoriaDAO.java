package model.servicos.persistencia.interfaces;

import model.entidades.Categoria;
import model.exceptions.DBException;

import java.util.List;

/**
 *
 * @author Alexsander
 */
public interface CategoriaDAO {

    void inserir(Categoria categoria) throws DBException;

    void atualizar(Categoria categoria);

    void excluir(Integer id) throws DBException;

    Categoria buscar(Integer id);

    List<Categoria> buscar(String filtro);

    List<Categoria> buscarTodos();

}
