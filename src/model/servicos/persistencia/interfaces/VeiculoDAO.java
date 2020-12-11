package model.servicos.persistencia.interfaces;

import model.entidades.Veiculo;
import java.util.List;
import model.entidades.Modelo;
import model.entidades.enums.StatusVeiculo;
import model.exceptions.DBException;

/**
 *
 * @author Alexsander
 */
public interface VeiculoDAO {

    void inserir(Veiculo veiculo) throws DBException;

    void atualizar(Veiculo veiculo);

    Veiculo buscar(Integer id);

    List<Veiculo> buscar(String filtro);

    List<Veiculo> buscar(Modelo modelo);

    List<Veiculo> buscar(StatusVeiculo status);

    List<Veiculo> buscarTodos();

}
