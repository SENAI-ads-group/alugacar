package model.servicos.persistencia.implementacaoCSV;

import model.servicos.persistencia.implementacaoCSV.conectores.CSVConnection;
import application.Configuracoes;
import model.entidades.Marca;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.exceptions.PersistenciaException;
import util.Utilities;
import model.servicos.persistencia.MarcaDAO;

/**
 *
 * @author Patrick-Ribeiro
 */
public class MarcaCSV implements MarcaDAO {

    private final File ARQUIVO_DB;
    private final String PASTA_RAIZ;
    private final CSVConnection CONEXAO;

    public MarcaCSV() {
        String caminhoDB = Configuracoes.getProperties().getProperty("db.marca");
        PASTA_RAIZ = Configuracoes.getProperties().getProperty("canonicalPath");

        ARQUIVO_DB = new File(PASTA_RAIZ + caminhoDB);
        CONEXAO = new CSVConnection();
    }

    @Override
    public void inserir(Marca marca) throws PersistenciaException {
        if (marca.getId() == null) {
            marca.setId(getUltimoID() + 1);
        }
        if (buscar(marca.getId()) != null) {
            throw new PersistenciaException("A marca já existe");
        }
        CONEXAO.open(ARQUIVO_DB);

        CONEXAO.writer().write(marca.toCSV());
        CONEXAO.writer().newLine();

        CONEXAO.close();
    }

    @Override
    public void atualizar(Marca marca) {
        File arquivoDBTemp = new File(PASTA_RAIZ + "\\temp\\marcas-temp.csv");
        CSVConnection conexaoTemp = new CSVConnection();

        CONEXAO.open(ARQUIVO_DB);
        conexaoTemp.open(arquivoDBTemp);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Marca marcaEncontrada = new Marca(linha.split(";"));

            if (marcaEncontrada.equals(marca)) {
                conexaoTemp.writer().write(marca.toCSV());
            } else {
                conexaoTemp.writer().write(marcaEncontrada.toCSV());
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
    public Marca buscar(Integer id) {
        if (id == null) {
            throw new IllegalStateException("id está nulo");
        }
        CONEXAO.open(ARQUIVO_DB);
        System.out.println(ARQUIVO_DB.toString());
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            String[] csv = linha.split(";");
            Marca marcaEncontrada = new Marca(csv);

            if (marcaEncontrada.getId().equals(id)) {
                CONEXAO.close();
                return marcaEncontrada;
            }
            linha = CONEXAO.reader().readLine();
        }
        CONEXAO.close();
        return null;
    }

    @Override
    public List<Marca> buscarTodos() {
        CONEXAO.open(ARQUIVO_DB);

        List<Marca> marcasEncontradas = new ArrayList<>();
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            String[] csv = linha.split(";");
            Marca marcaEncontrada = new Marca(csv);
            marcasEncontradas.add(marcaEncontrada);
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
        return marcasEncontradas;
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
