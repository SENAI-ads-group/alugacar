package entidades.dao.interfaces;

import entidades.Marca;
import java.util.List;

/**
 *
 * @author Patrick-Ribeiro
 */
public interface IMarcaDAO {

    void inserir(Marca marca);

    void atualizar(Marca marca);
    
    void buscar(Integer id);
    
    List<Marca> listar();
}
