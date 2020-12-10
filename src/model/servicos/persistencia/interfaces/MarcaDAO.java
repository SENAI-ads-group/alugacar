package model.servicos.persistencia.interfaces;

import model.entidades.Marca;
import java.util.List;
import model.exceptions.DBException;

/**
 *
 * @author Patrick-Ribeiro
 */
public interface MarcaDAO {

    void inserir(Marca marca) throws DBException;

    void atualizar(Marca marca);

    void excluir(int id) throws DBException;

    Marca buscar(Integer id);

    List<Marca> buscar(String filtro);

    List<Marca> buscarTodos();

}
