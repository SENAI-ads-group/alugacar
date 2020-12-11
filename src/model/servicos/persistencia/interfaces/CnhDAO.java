package model.servicos.persistencia.interfaces;

import model.entidades.CNH;
import java.util.List;
import model.exceptions.DBException;

/**
 *
 * @author Alexsander
 */
public interface CnhDAO {

    void inserir(CNH cnh) throws DBException;

    void atualizar(CNH cnh);

    void excluir(String numeroRegistro);

    CNH buscar(String numeroRegistro);

    List<CNH> buscarTodos();

}
