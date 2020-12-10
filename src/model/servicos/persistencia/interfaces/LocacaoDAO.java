package model.servicos.persistencia.interfaces;

import java.util.List;
import model.entidades.Cliente;
import model.entidades.Locacao;
import model.entidades.Motorista;
import model.entidades.Vistoria;
import model.exceptions.DBException;

/**
 *
 * @author Alexsander
 */
public interface LocacaoDAO {

    void registrar(Locacao locacao) throws DBException;

    void entregarVeiculo(Locacao locacao, Vistoria vistoria);

    void devolverVeiculo(Locacao locacao, Vistoria vistoria);

    Locacao buscar(Integer id);

    List<Locacao> buscar(Cliente cliente);

    List<Locacao> buscar(Motorista motorista);

    List<Locacao> buscarTodos();

    void atualizar(Locacao locacao);

}
