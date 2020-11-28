package model.servicos.persistencia;

import model.entidades.CNH;
import model.exceptions.PersistenciaException;

import java.util.List;

/**
 *
 * @author Alexsander
 */
public interface CnhDAO {

    void inserir(CNH cnh) throws PersistenciaException;

    void atualizar(CNH cnh);

    CNH buscar(Integer id);

    List<CNH> buscarTodos();

}
