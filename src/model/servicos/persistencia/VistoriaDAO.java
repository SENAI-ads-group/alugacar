package model.servicos.persistencia;

import java.util.List;
import model.entidades.ItemVistoria;
import model.entidades.Vistoria;

/**
 *
 * @author patrick-ribeiro
 */
public interface VistoriaDAO {

    void inserir(Vistoria vistoria);

    Vistoria buscar(Integer id);

    List<Vistoria> buscar(ItemVistoria item);
}
