package entidades.services.persistence;

import entidades.CNH;

import java.util.List;

/**
 *
 * @author Alexsander
 */
public interface CNHPersistenseService {

    void inserir(CNH cnh);

    void atualizar(CNH cnh);

    CNH buscar(Integer id);

    List<CNH> buscarTodos();

}
