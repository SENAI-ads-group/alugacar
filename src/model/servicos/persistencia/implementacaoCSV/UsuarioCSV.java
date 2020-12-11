package model.servicos.persistencia.implementacaoCSV;

import aplicacao.Programa;
import java.io.File;
import java.util.ArrayList;
import model.entidades.Usuario;
import java.util.List;
import model.servicos.persistencia.interfaces.UsuarioDAO;
import model.servicos.persistencia.implementacaoCSV.conectores.CSVConnection;
import model.exceptions.DBException;
import util.Utilities;

/**
 *
 * @author Alexsander
 */
public class UsuarioCSV implements UsuarioDAO {

    private final File ARQUIVO_DB = new File(Programa.getPropriedade("absoluteDatabasePath") + "usuarios.csv");
    private final CSVConnection CONEXAO = new CSVConnection();

    @Override
    public void inserir(Usuario usuario) throws DBException {
        if (buscar(usuario.getId()) != null) {
            throw new DBException("O usuário já existe");
        }
        CONEXAO.open(ARQUIVO_DB);
        CONEXAO.writer().write(usuario.toCSV());
        CONEXAO.writer().newLine();
        CONEXAO.close();
    }

    @Override
    public void atualizar(Usuario usuario) {
        File arquivoDBTemp = new File(Programa.getPropriedade("absoluteDatabasePath") + "temp-usuarios.csv");
        CSVConnection conexaoTemp = new CSVConnection();
        CONEXAO.open(ARQUIVO_DB);
        conexaoTemp.open(arquivoDBTemp);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Usuario usuarioEncontrado = new Usuario(linha.split(";"));
            if (usuarioEncontrado.equals(usuario)) {
                conexaoTemp.writer().write(usuario.toCSV());
            } else {
                conexaoTemp.writer().write(usuarioEncontrado.toCSV());
            }
            conexaoTemp.writer().flush();
            conexaoTemp.writer().newLine();
            linha = CONEXAO.reader().readLine();
        }
    }

    @Override
    public Usuario buscar(Integer id) {
        if (id == null) {
            throw new IllegalStateException("O ID está nulo");
        }
        CONEXAO.open(ARQUIVO_DB);
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Usuario usuarioEncontrado = new Usuario(linha.split(";"));

            if (usuarioEncontrado.getId().equals(id)) {
                CONEXAO.close();
                return usuarioEncontrado;
            }
            linha = CONEXAO.reader().readLine();
        }
        CONEXAO.close();
        return null;
    }

    @Override
    public List<Usuario> buscarTodos() {
        CONEXAO.open(ARQUIVO_DB);

        List<Usuario> usuariosEncontrados = new ArrayList<>();
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            String[] csv = linha.split(";");
            Usuario usuarioEncontrado = new Usuario(csv);
            usuariosEncontrados.add(usuarioEncontrado);
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
        return usuariosEncontrados;
    }

    private Integer getUltimoID() {
        CONEXAO.open(ARQUIVO_DB);

        Integer ultimoID = 1;
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            ultimoID = Utilities.tryParseToInteger(linha.split(";")[0]);
            if (ultimoID == null) {
                ultimoID = 1;
            }
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
        return ultimoID;
    }

}
