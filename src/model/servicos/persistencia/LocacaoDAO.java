package model.servicos.persistencia;

import java.util.List;
import model.entidades.Locacao;
import model.exceptions.PersistenciaException;

/**
 *
 * @author Alexsander
 */
public interface LocacaoDAO {

    void inserir(Locacao locacao) throws PersistenciaException;

    void atualizar(Locacao locacao);

    Locacao buscar(Integer id);

    List<Locacao> buscarTodos();

}
