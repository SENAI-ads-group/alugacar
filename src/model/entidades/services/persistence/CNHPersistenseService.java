package model.entidades.services.persistence;

import model.entidades.CNH;
import model.entidades.services.persistence.exceptions.PersistenceException;

import java.util.List;

/**
 *
 * @author Alexsander
 */
public interface CNHPersistenseService {

    void inserir(CNH cnh) throws PersistenceException;

    void atualizar(CNH cnh);

    CNH buscar(Integer id);

    List<CNH> buscarTodos();

}
