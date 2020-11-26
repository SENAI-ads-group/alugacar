package model.entidades.services.persistence;

import model.entidades.Marca;
import model.entidades.Modelo;
import model.entidades.services.persistence.exceptions.PersistenceException;
import java.util.List;

/**
 *
 * @author patrick-ribeiro
 */
public interface ModeloPersistenceService {

    void inserir(Modelo modelo) throws PersistenceException;

    void atualizar(Modelo modelo);

    Modelo buscar(Integer id);

    List<Modelo> buscar(Marca marca);

    List<Modelo> buscarTodos();
}
