package model.servicos.persistencia.interfaces;

import model.entidades.Cliente;

import java.util.List;
import model.exceptions.DBException;

/**
 *
 * @author Alexsander
 */
public interface ClienteDAO {

    void inserir(Cliente cliente) throws DBException;

    void atualizar(Cliente cliente);

    void excluir(int id);

    Cliente buscar(Integer id);

    List<Cliente> buscar(String filtro);

    List<Cliente> buscarTodos();

}
