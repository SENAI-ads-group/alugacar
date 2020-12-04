package model.servicos.persistencia;

import java.awt.image.BufferedImage;
import java.util.List;
import model.entidades.CNH;
import model.entidades.Vistoria;

/**
 *
 * @author Patrick-Ribeiro
 */
public interface ImagemDAO {

    void inserir(BufferedImage image, Vistoria vistoria);

    void inserir(BufferedImage image, CNH cnh);

    List<BufferedImage> buscar(Vistoria vistoria);

    List<BufferedImage> buscar(CNH cnh);
}
