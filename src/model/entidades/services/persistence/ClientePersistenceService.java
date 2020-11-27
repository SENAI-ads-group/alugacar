package model.entidades.services.persistence;

import model.entidades.Cliente;

import java.util.List;

/**
 *
 * @author Alexsander
 */
public interface ClientePersistenceService {

    void inserir(Cliente cliente);

    void atualizar(Cliente cliente);

    Cliente buscar(Integer id);

    List<Cliente> buscarTodos();

}
