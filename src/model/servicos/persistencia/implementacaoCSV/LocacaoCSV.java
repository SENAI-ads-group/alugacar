package model.servicos.persistencia.implementacaoCSV;

import application.Configuracoes;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.entidades.Locacao;
import model.exceptions.PersistenciaException;
import model.servicos.persistencia.LocacaoDAO;
import model.servicos.persistencia.implementacaoCSV.conectores.CSVConnection;
import util.Utilities;

/**
 *
 * @author Alexsander
 */
public class LocacaoCSV implements LocacaoDAO {

    private final File ARQUIVO_DB;
    private final String PASTA_RAIZ;
    private final CSVConnection CONEXAO;

    public LocacaoCSV() {
        String caminhoDB = Configuracoes.getProperties().getProperty("db.locacao");
        PASTA_RAIZ = Configuracoes.getProperties().getProperty("canonicalPath");

        ARQUIVO_DB = new File(PASTA_RAIZ + caminhoDB);
        CONEXAO = new CSVConnection();

    }

    @Override
    public void inserir(Locacao locacao) throws PersistenciaException {
        if (locacao.getId() == null) {
            locacao.setId(getUltimoID() + 1);
        }
        if (buscar(locacao.getId()) != null) {
            throw new PersistenciaException("A locação " + locacao.getId() + " já existe");
        }
        CONEXAO.open(ARQUIVO_DB);

        CONEXAO.writer().write(locacao.toCSV());
        CONEXAO.writer().newLine();

        CONEXAO.close();
    }

    @Override
    public void atualizar(Locacao locacao) {

        File arquivoDBTemp = new File(PASTA_RAIZ + "\\temp\\locacao-temp.csv");
        CSVConnection conexaoTemp = new CSVConnection();

        CONEXAO.open(ARQUIVO_DB);
        conexaoTemp.open(arquivoDBTemp);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Locacao locacaoEncontrada = new Locacao(linha.split(";"));

            if (locacaoEncontrada.equals(locacao)) {
                conexaoTemp.writer().write(locacao.toCSV());
            } else {
                conexaoTemp.writer().write(locacaoEncontrada.toCSV());
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
    public Locacao buscar(Integer id) {
        if (id == null) {
            throw new IllegalStateException("O ID da locação está nulo");
        }
        CONEXAO.open(ARQUIVO_DB);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Locacao locacaoEncontrada = new Locacao(linha.split(";"));

            if (locacaoEncontrada.getId().equals(id)) {
                CONEXAO.close();
                return locacaoEncontrada;
            }
            linha = CONEXAO.reader().readLine();
        }
        CONEXAO.close();
        return null;

    }

    @Override
    public List<Locacao> buscarTodos() {
        CONEXAO.open(ARQUIVO_DB);

        List<Locacao> locacoesEncontradas = new ArrayList<>();
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Locacao locacaoEncontrada = new Locacao(linha.split(";"));
            locacoesEncontradas.add(locacaoEncontrada);
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
        return locacoesEncontradas;
    }

    private Integer getUltimoID() {
        CONEXAO.open(ARQUIVO_DB);

        Integer ultimoID = 0;
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            ultimoID = Utilities.tryParseToInteger(linha.split(";")[0]);
            if (ultimoID == null) {
                ultimoID = 0;
            }
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
        return ultimoID;
    }

}
