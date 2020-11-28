package model.servicos.persistencia.implementacaoCSV;

import model.servicos.persistencia.implementacaoCSV.conectores.CSVConnection;
import application.Configuracoes;
import model.entidades.Categoria;
import model.exceptions.PersistenciaException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import util.Utilities;
import model.servicos.persistencia.CategoriaDAO;

/**
 *
 * @author Alexsander
 */
public class CategoriaCSV implements CategoriaDAO {

    private final File ARQUIVO_DB;
    private final String PASTA_RAIZ;
    private final CSVConnection CONEXAO;

    public CategoriaCSV() {
        String caminhoDB = Configuracoes.getProperties().getProperty("db.categoria");
        PASTA_RAIZ = Configuracoes.getProperties().getProperty("canonicalPath");

        ARQUIVO_DB = new File(PASTA_RAIZ + caminhoDB);
        CONEXAO = new CSVConnection();
    }

    @Override
    public void inserir(Categoria categoria) throws PersistenciaException {
        if (categoria.getId() == null) {
            categoria.setId(getUltimoID() + 1);
        }
        if (buscar(categoria.getId()) != null) {
            throw new PersistenciaException("A categoria já  existe !");
        }
        CONEXAO.open(ARQUIVO_DB);

        CONEXAO.writer().write(categoria.toCSV());
        CONEXAO.writer().newLine();

        CONEXAO.close();
    }

    @Override
    public void atualizar(Categoria categoria) {
        File arquivoDBTemp = new File(PASTA_RAIZ + "\\temp\\categorias-temp.csv");
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

        Integer ultimoID = 1;
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            ultimoID = Utilities.tryParseToInteger(linha.split(";")[0]);
            linha = CONEXAO.reader().readLine();
        }
        if (ultimoID == null) {
            ultimoID = 1;
        }

        CONEXAO.close();
        return ultimoID;
    }

}
