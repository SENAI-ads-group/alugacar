package model.servicos.persistencia;

import java.util.List;
import model.entidades.Locacao;
import model.exceptions.DBException;

/**
 *
 * @author Alexsander
 */
public interface LocacaoDAO {

    void inserir(Locacao locacao) throws DBException;

    void atualizar(Locacao locacao);

    Locacao buscar(Integer id);

    List<Locacao> buscarTodos();

}
