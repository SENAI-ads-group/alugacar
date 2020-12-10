package model.servicos.persistencia;

import model.entidades.Marca;
import model.entidades.Modelo;
import java.util.List;
import model.entidades.Categoria;
import model.exceptions.DBException;

/**
 *
 * @author patrick-ribeiro
 */
public interface ModeloDAO {

    void inserir(Modelo modelo) throws DBException;

    void atualizar(Modelo modelo);

    void excluir(Integer id);

    Modelo buscar(Integer id);

    List<Modelo> buscar(Marca marca);

    List<Modelo> buscar(String filtro);

    List<Modelo> buscar(Categoria categoria);

    List<Modelo> buscarTodos();
}
