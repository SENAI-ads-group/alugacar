package model.servicos.persistencia;

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

    Cliente buscar(Integer id);

    List<Cliente> buscarTodos();

}
