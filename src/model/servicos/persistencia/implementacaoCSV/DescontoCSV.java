package model.servicos.persistencia.implementacaoCSV;

import aplicacao.Programa;
import java.io.File;
import java.util.List;
import model.entidades.Desconto;
import model.exceptions.DBException;
import model.servicos.persistencia.interfaces.DescontoDAO;
import model.servicos.persistencia.implementacaoCSV.conectores.CSVConnection;
import util.Utilities;
import java.util.ArrayList;

/**
 *
 * @author patrick-ribeiro
 */
public class DescontoCSV implements DescontoDAO {

    private final File ARQUIVO_DB = new File(Programa.getPropriedade("absoluteDatabasePath") + "descontos.csv");
    private final CSVConnection CONEXAO = new CSVConnection();

    @Override
    public void inserir(Desconto desconto) {
        if (desconto.getId() == null) {
            desconto.setId(getUltimoID() + 1);
        }
        if (buscar(desconto.getId()) != null) {
            throw new DBException("A taxa já existe");
        }
        CONEXAO.open(ARQUIVO_DB);
        CONEXAO.writer().write(desconto.toCSV());
        CONEXAO.writer().newLine();
        CONEXAO.close();
    }

    @Override
    public void atualizar(Desconto desconto) {
        File arquivoDBTemp = new File(Programa.getPropriedade("absoluteDatabasePath") + "temp-descontos.csv");
        CSVConnection conexaoTemp = new CSVConnection();
        CONEXAO.open(ARQUIVO_DB);
        conexaoTemp.open(arquivoDBTemp);
        String linha = CONEXAO.reader().readLine();

        while (linha != null) {

            Desconto descontoEncontrado = new Desconto(linha.split(";"));
            if (descontoEncontrado.equals(desconto)) {
                conexaoTemp.writer().write(desconto.toCSV());
            } else {
                conexaoTemp.writer().write(descontoEncontrado.toCSV());
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
    public Desconto buscar(Integer id) {
        if (id == null) {
            throw new IllegalStateException("id está nulo");
        }
        CONEXAO.open(ARQUIVO_DB);
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            String[] csv = linha.split(";");
            Desconto descontoEncontrado = new Desconto(csv);

            if (descontoEncontrado.getId().equals(id)) {
                CONEXAO.close();
                return descontoEncontrado;
            }
            linha = CONEXAO.reader().readLine();
        }
        CONEXAO.close();
        return null;
    }

    @Override
    public List<Desconto> buscarTodos() {
        CONEXAO.open(ARQUIVO_DB);

        List<Desconto> descontosEncontrados = new ArrayList<>();
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            String[] csv = linha.split(";");
            Desconto desc = new Desconto(csv);
            descontosEncontrados.add(desc);
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
        return descontosEncontrados;
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
