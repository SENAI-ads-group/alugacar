package entidades.services.persistence;

import entidades.Usuario;
import java.util.List;

/**
 *
 * @author Alexsander
 */
public interface UsuarioPersistenceService {

    void inserir(Usuario usuario);

    void atualizar(Usuario usuario);

    Usuario buscar(Integer id);

    List<Usuario> buscarTodos();

}
