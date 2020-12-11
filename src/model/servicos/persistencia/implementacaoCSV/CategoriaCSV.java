package model.servicos.persistencia.implementacaoCSV;

import model.servicos.persistencia.implementacaoCSV.conectores.CSVConnection;
import aplicacao.Programa;
import model.entidades.Categoria;
import model.exceptions.DBException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import util.Utilities;
import model.servicos.persistencia.interfaces.CategoriaDAO;
import model.servicos.persistencia.DAOFactory;

/**
 *
 * @author Alexsander
 */
public class CategoriaCSV implements CategoriaDAO {

    private final File ARQUIVO_DB = new File(Programa.getPropriedade("absoluteDatabasePath") + "categorias.csv");
    private final CSVConnection CONEXAO = new CSVConnection();

    @Override
    public void inserir(Categoria categoria) throws DBException {
        if (categoria.getId() == null) {
            categoria.setId(getUltimoID() + 1);
        }
        if (buscar(categoria.getId()) != null) {
            throw new DBException("A categoria já  existe!");
        }
        if (buscarTodos().contains(categoria)) {
            throw new DBException("Já existe uma categoria com a descrição " + categoria.getDescricao());
        }
        CONEXAO.open(ARQUIVO_DB);

        CONEXAO.writer().write(categoria.toCSV());
        CONEXAO.writer().newLine();

        CONEXAO.close();
    }

    @Override
    public void atualizar(Categoria categoria) {
        File arquivoDBTemp = new File(Programa.getPropriedade("absoluteDatabasePath") + "temp-categorias.csv");
        CSVConnection conexaoTemp = new CSVConnection();

        CONEXAO.open(ARQUIVO_DB);
        conexaoTemp.open(arquivoDBTemp);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Categoria categoriaEncontrada = new Categoria(linha.split(";"));

            if (categoriaEncontrada.equals(categoria)) {
                conexaoTemp.writer().write(categoria.toCSV());
            } else {
                conexaoTemp.writer().write(categoriaEncontrada.toCSV());
            }
            conexaoTemp.writer().flush();
            conexaoTemp.writer().newLine();
            linha = CONEXAO.reader().readLine();
        }

        conexaoTemp.close();
        CONEXAO.close();
        ARQUIVO_DB.delete();
        arquivoDBTemp.renameTo(ARQUIVO_DB);
    }

    @Override
    public void excluir(Integer id) throws DBException {
        if (buscar(id) == null) {
            throw new DBException("A categoria não existe");
        }
        if (DAOFactory.createModeloDAO().buscar(buscar(id)).size() > 0) {
            throw new DBException("Não foi possível excluir a categoria pois está associada à um ou mais modelo(s)");
        }
        File arquivoDBTemp = new File(Programa.getPropriedade("absoluteDatabasePath") + "temp-categorias.csv");
        CSVConnection conexaoTemp = new CSVConnection();

        CONEXAO.open(ARQUIVO_DB);
        conexaoTemp.open(arquivoDBTemp);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Categoria categoriaEncontrada = new Categoria(linha.split(";"));
            if (!categoriaEncontrada.getId().equals(id)) {
                conexaoTemp.writer().write(categoriaEncontrada.toCSV());
                conexaoTemp.writer().flush();
                conexaoTemp.writer().newLine();
            }
            linha = CONEXAO.reader().readLine();
        }

        conexaoTemp.close();
        CONEXAO.close();
        ARQUIVO_DB.delete();
        arquivoDBTemp.renameTo(ARQUIVO_DB);
    }

    @Override
    public Categoria buscar(Integer id) {
        if (id == null) {
            throw new IllegalStateException("id está nulo");
        }
        CONEXAO.open(ARQUIVO_DB);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Categoria categoriaEncontrada = new Categoria(linha.split(";"));
            if (categoriaEncontrada.getId().equals(id)) {
                CONEXAO.close();
                return categoriaEncontrada;
            }
            linha = CONEXAO.reader().readLine();
        }
        CONEXAO.close();
        return null;
    }

    @Override
    public List<Categoria> buscar(String filtro) {
        List<Categoria> categorias = new ArrayList<>();
        if (filtro == null) {
            return categorias;
        }
        filtro = filtro.toLowerCase();
        CONEXAO.open(ARQUIVO_DB);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Categoria categoria = new Categoria(linha.split(";"));
            if (categoria.getDescricao().toLowerCase().contains(filtro) || ("" + categoria.getId()).contains(filtro)) {
                categorias.add(categoria);
            }
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
        return categorias;
    }

    @Override
    public List<Categoria> buscarTodos() {
        CONEXAO.open(ARQUIVO_DB);

        List<Categoria> categoriasEncontradas = new ArrayList<>();
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Categoria categoriaEncontrada = new Categoria(linha.split(";"));
            categoriasEncontradas.add(categoriaEncontrada);
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
        return categoriasEncontradas;
    }

    private Integer getUltimoID() {
        CONEXAO.open(ARQUIVO_DB);

        Integer ultimoID = 0;
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            ultimoID = Utilities.tryParseToInteger(linha.split(";")[0]);
            linha = CONEXAO.reader().readLine();
        }
        if (ultimoID == null) {
            ultimoID = 0;
        }

        CONEXAO.close();
        return ultimoID;
    }

}
