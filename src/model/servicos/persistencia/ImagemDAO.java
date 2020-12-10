package model.servicos.persistencia;

import model.entidades.CNH;
import model.entidades.Vistoria;

/**
 *
 * @author Patrick-Ribeiro
 */
public interface ImagemDAO {

    void exportar(Vistoria vistoria);

    void exportar(CNH cnh);

    void importar(Vistoria vistoria);

    void importar(CNH cnh);
}
