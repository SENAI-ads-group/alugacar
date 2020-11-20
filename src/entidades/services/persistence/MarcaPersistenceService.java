package entidades.services.persistence;

import entidades.Marca;
import java.util.List;

/**
 *
 * @author Patrick-Ribeiro
 */
public interface MarcaPersistenceService {

    void inserir(Marca marca);

    void atualizar(Marca marca);
    
    Marca buscar(Integer id);
    
    List<Marca> listar();
}
