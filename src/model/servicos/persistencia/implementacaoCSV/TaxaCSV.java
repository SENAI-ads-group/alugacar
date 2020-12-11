package model.servicos.persistencia.implementacaoCSV;

import aplicacao.Programa;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.entidades.Marca;
import model.entidades.Taxa;
import model.exceptions.DBException;
import model.servicos.persistencia.interfaces.TaxaDAO;
import model.servicos.persistencia.implementacaoCSV.conectores.CSVConnection;
import util.Utilities;

/**
 *
 * @author patrick-ribeiro
 */
public class TaxaCSV implements TaxaDAO {

    private final File ARQUIVO_DB = new File(Programa.getPropriedade("absoluteDatabasePath") + "taxas.csv");
    private final CSVConnection CONEXAO = new CSVConnection();

    @Override
    public void inserir(Taxa taxa) {
        if (taxa.getId() == null) {
            taxa.setId(getUltimoID() + 1);
        }
        if (buscar(taxa.getId()) != null) {
            throw new DBException("A taxa já existe");
        }
        CONEXAO.open(ARQUIVO_DB);
        CONEXAO.writer().write(taxa.toCSV());
        CONEXAO.writer().newLine();
        CONEXAO.close();
    }

    @Override
    public void atualizar(Taxa taxa) {
        File arquivoDBTemp = new File(Programa.getPropriedade("absoluteDatabasePath") + "temp-taxas.csv");
        CSVConnection conexaoTemp = new CSVConnection();
        CONEXAO.open(ARQUIVO_DB);
        conexaoTemp.open(arquivoDBTemp);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Taxa taxaEncontrada = new Taxa(linha.split(";"));
            if (taxaEncontrada.equals(taxa)) {
                conexaoTemp.writer().write(taxa.toCSV());
            } else {
                conexaoTemp.writer().write(taxaEncontrada.toCSV());
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
    public void excluir(Integer id) {
        if (buscar(id) == null) {
            throw new DBException("A taxa não existe");
        }
        File arquivoDBTemp = new File(Programa.getPropriedade("absoluteDatabasePath") + "temp-itens-vistoria-global.csv");
        CSVConnection conexaoTemp = new CSVConnection();
        CONEXAO.open(ARQUIVO_DB);
        conexaoTemp.open(arquivoDBTemp);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Marca marcaEncontrada = new Marca(linha.split(";"));
            if (!marcaEncontrada.getId().equals(id)) {
                conexaoTemp.writer().write(marcaEncontrada.toCSV());
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
    public Taxa buscar(Integer id) {
        if (id == null) {
            throw new IllegalStateException("id está nulo");
        }
        CONEXAO.open(ARQUIVO_DB);
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            String[] csv = linha.split(";");
            Taxa taxaEncontrada = new Taxa(csv);

            if (taxaEncontrada.getId().equals(id)) {
                CONEXAO.close();
                return taxaEncontrada;
            }
            linha = CONEXAO.reader().readLine();
        }
        CONEXAO.close();
        return null;
    }

    @Override
    public List<Taxa> buscarTodos() {
        CONEXAO.open(ARQUIVO_DB);

        List<Taxa> taxasEncontradas = new ArrayList<>();
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            String[] csv = linha.split(";");
            Taxa taxa = new Taxa(csv);
            taxasEncontradas.add(taxa);
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
        return taxasEncontradas;
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
