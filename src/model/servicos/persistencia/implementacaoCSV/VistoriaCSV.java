package model.servicos.persistencia.implementacaoCSV;

import application.Configuracoes;
import java.io.File;
import model.entidades.ItemVistoria;
import model.entidades.Vistoria;
import model.servicos.persistencia.VistoriaDAO;
import model.servicos.persistencia.implementacaoCSV.conectores.CSVConnection;
import util.Utilities;

/**
 *
 * @author patrick-ribeiro
 */
public class VistoriaCSV implements VistoriaDAO {

    private final File ARQUIVO_DB;
    private final File ARQUIVO_ITEM_DB;
    private final String PASTA_RAIZ;
    private final CSVConnection CONEXAO;
    private final CSVConnection CONEXAO_ITEM;

    public VistoriaCSV() {
        String caminhoDB = Configuracoes.getProperties().getProperty("db.vistoria");
        String caminhoItemDB = Configuracoes.getProperties().getProperty("db.item-vistoria");
        PASTA_RAIZ = Configuracoes.getProperties().getProperty("canonicalPath");

        ARQUIVO_DB = new File(PASTA_RAIZ + caminhoDB);
        ARQUIVO_ITEM_DB = new File(PASTA_RAIZ + caminhoItemDB);

        CONEXAO = new CSVConnection();
        CONEXAO_ITEM = new CSVConnection();
    }

    @Override
    public void inserir(Vistoria vistoria) {
        CONEXAO.open(ARQUIVO_DB);
        CONEXAO.writer().write(vistoria.toCSV());
        CONEXAO.writer().newLine();
        CONEXAO.close();

        CONEXAO_ITEM.open(ARQUIVO_ITEM_DB);
        for (ItemVistoria item : vistoria.getItens()) {
            CONEXAO_ITEM.writer().write(vistoria.getId() + item.toCSV());
            CONEXAO_ITEM.writer().newLine();
        }
        CONEXAO_ITEM.close();
    }

    @Override
    public Vistoria buscar(Integer id) {
        if (id == null) {
            throw new IllegalStateException("id está nulo");
        }
        CONEXAO.open(ARQUIVO_DB);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Vistoria vistoriaEncontrada = new Vistoria(linha.split(";"));
            if (vistoriaEncontrada.getId().equals(id)) {
                addItens(vistoriaEncontrada);
                CONEXAO.close();
                return vistoriaEncontrada;
            }
            linha = CONEXAO.reader().readLine();
        }
        CONEXAO.close();
        return null;
    }

    private void addItens(Vistoria vistoria) {
        CONEXAO_ITEM.open(ARQUIVO_ITEM_DB);

        String linha = CONEXAO_ITEM.reader().readLine();
        while (linha != null) {
            String[] csv = linha.split(";");
            Integer idVistoria = Utilities.tryParseToInteger(csv[0]);
            if (idVistoria.equals(vistoria.getId())) {
                String[] csvItem = {csv[1], csv[2], csv[3]};
                vistoria.addItem(new ItemVistoria(csvItem));
            }
            linha = CONEXAO_ITEM.reader().readLine();
        }
        CONEXAO_ITEM.close();
    }
}
