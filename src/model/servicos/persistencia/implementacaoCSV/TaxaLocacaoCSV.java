package model.servicos.persistencia.implementacaoCSV;

import application.Programa;
import java.io.File;
import model.entidades.Locacao;
import model.entidades.Taxa;
import model.servicos.persistencia.implementacaoCSV.conectores.CSVConnection;
import util.Utilities;
import model.servicos.persistencia.TaxaLocacaoDAO;

/**
 *
 * @author patrick-ribeiro
 */
public class TaxaLocacaoCSV implements TaxaLocacaoDAO {

    private final File ARQUIVO_DB = new File(Programa.getPropriedade("absoluteDatabasePath") + "taxas-locacao.csv");
    private final CSVConnection CONEXAO = new CSVConnection();

    @Override
    public void importar(Locacao locacao) {
        CONEXAO.open(ARQUIVO_DB);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            String[] csv = linha.split(";");
            String[] csvTaxa = {csv[1], csv[2], csv[3]};
            Integer idLocacao = Utilities.tryParseToInteger(csv[0]);
            if (idLocacao.equals(locacao.getId())) {
                locacao.addTaxa(new Taxa(csvTaxa));
            }
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
    }

    @Override
    public void atualizarLocacao(Locacao locacao) {
        File arquivoDBTemp = new File(Programa.getPropriedade("absoluteDatabasePath") + "temp-marcas.csv");
        CSVConnection conexaoTemp = new CSVConnection();

        CONEXAO.open(ARQUIVO_DB);
        conexaoTemp.open(arquivoDBTemp);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Integer idLocacao = Utilities.tryParseToInteger(linha.split(";")[0]);

            if (idLocacao.equals(locacao.getId())) {
                for (Taxa taxa : locacao.getTaxas()) {
                    conexaoTemp.writer().write(locacao.getId() + ";" + taxa.toCSV());
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

    @Override
    public void exportar(Locacao locacao, Taxa taxa) {
        CONEXAO.open(ARQUIVO_DB);
        CONEXAO.writer().write(locacao.getId() + ";" + taxa.toCSV());
        CONEXAO.writer().newLine();
        CONEXAO.close();
    }

}
