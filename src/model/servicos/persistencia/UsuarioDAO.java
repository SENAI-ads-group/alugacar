package model.servicos.persistencia;

import model.entidades.Usuario;
import java.util.List;
import model.exceptions.PersistenciaException;

/**
 *
 * @author Alexsander
 */
public interface UsuarioDAO {

    void inserir(Usuario usuario) throws PersistenciaException;

    void atualizar(Usuario usuario);

    Usuario buscar(Integer id);

    List<Usuario> buscarTodos();

}
