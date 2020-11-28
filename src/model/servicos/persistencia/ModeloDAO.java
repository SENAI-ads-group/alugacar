package model.servicos.persistencia;

import model.entidades.Marca;
import model.entidades.Modelo;
import model.exceptions.PersistenciaException;
import java.util.List;

/**
 *
 * @author patrick-ribeiro
 */
public interface ModeloDAO {

    void inserir(Modelo modelo) throws PersistenciaException;

    void atualizar(Modelo modelo);

    Modelo buscar(Integer id);

    List<Modelo> buscar(Marca marca);

    List<Modelo> buscarTodos();
}
