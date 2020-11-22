package entidades.services.persistence;

import entidades.Endereco;

import java.util.List;

/**
 *
 * @author Alexsander
 */
public interface EnderecoPersistenseService {

    void inserir(Endereco endereco);

    void atualizar(Endereco endereco);

    Endereco buscar(Integer id);

    List<Endereco> buscarTodos();

}
