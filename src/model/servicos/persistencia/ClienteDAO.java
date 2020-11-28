package model.servicos.persistencia;

import model.entidades.Cliente;

import java.util.List;
import model.exceptions.PersistenciaException;

/**
 *
 * @author Alexsander
 */
public interface ClienteDAO {

    void inserir(Cliente cliente) throws PersistenciaException;

    void atualizar(Cliente cliente);

    Cliente buscar(Integer id);

    List<Cliente> buscarTodos();

}
