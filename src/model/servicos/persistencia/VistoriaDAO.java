package model.servicos.persistencia;

import model.entidades.Vistoria;

/**
 *
 * @author patrick-ribeiro
 */
public interface VistoriaDAO {

    void inserir(Vistoria vistoria);

    Vistoria buscar(Integer id);
}
