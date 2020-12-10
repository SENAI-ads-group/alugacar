package model.servicos.persistencia.implementacaoCSV;

import application.Programa;
import java.io.File;
import model.entidades.Desconto;
import model.entidades.Locacao;
import model.entidades.Taxa;
import model.servicos.persistencia.DescontoLocacaoDAO;
import model.servicos.persistencia.implementacaoCSV.conectores.CSVConnection;
import util.Utilities;

/**
 *
 * @author patrick-ribeiro
 */
public class DescontoLocacaoCSV implements DescontoLocacaoDAO {

    private final File ARQUIVO_DB = new File(Programa.getPropriedade("absoluteDatabasePath") + "descontos-locacao.csv");
    private final CSVConnection CONEXAO = new CSVConnection();

    @Override
    public void importar(Locacao locacao) {
        CONEXAO.open(ARQUIVO_DB);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            String[] csv = linha.split(";");
            String[] csvDesconto = {csv[1], csv[2], csv[3]};
            Integer idLocacao = Utilities.tryParseToInteger(csv[0]);
            if (idLocacao.equals(locacao.getId())) {
                locacao.addDesconto(new Desconto(csvDesconto));
            }
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
    }

    @Override
    public void exportar(Locacao locacao, Desconto desconto) {
        CONEXAO.open(ARQUIVO_DB);
        CONEXAO.writer().write(locacao.getId() + ";" + desconto.toCSV());
        CONEXAO.writer().newLine();
        CONEXAO.close();
    }

    @Override
    public void atualizarLocacao(Locacao locacao) {
        File arquivoDBTemp = new File(Programa.getPropriedade("absoluteDatabasePath") + "temp-descontos-locacao.csv");
        CSVConnection conexaoTemp = new CSVConnection();

        CONEXAO.open(ARQUIVO_DB);
        conexaoTemp.open(arquivoDBTemp);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Integer idLocacao = Utilities.tryParseToInteger(linha.split(";")[0]);

            if (idLocacao.equals(locacao.getId())) {
                for (Desconto desconto : locacao.getDescontos()) {
                    conexaoTemp.writer().write(locacao.getId() + ";" + desconto.toCSV());
                    conexaoTemp.writer().newLine();
                }
            } else {
                conexaoTemp.writer().write(linha);
                conexaoTemp.writer().newLine();
            }
            conexaoTemp.writer().flush();
            linha = CONEXAO.reader().readLine();
        }

        conexaoTemp.close();
        CONEXAO.close();
        ARQUIVO_DB.delete();
        arquivoDBTemp.renameTo(ARQUIVO_DB);
    }

}
