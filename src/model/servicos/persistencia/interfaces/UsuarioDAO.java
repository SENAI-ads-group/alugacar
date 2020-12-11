package model.servicos.persistencia.interfaces;

import model.entidades.Usuario;
import java.util.List;
import model.exceptions.DBException;

/**
 *
 * @author Alexsander
 */
public interface UsuarioDAO {

    void inserir(Usuario usuario) throws DBException;

    void atualizar(Usuario usuario);

    Usuario buscar(Integer id);

    List<Usuario> buscarTodos();

}
