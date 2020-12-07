package model.servicos.persistencia.implementacaoCSV;

import application.Programa;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.entidades.ItemVistoria;
import model.entidades.Vistoria;
import model.servicos.persistencia.DAOFactory;
import model.servicos.persistencia.ImagemDAO;
import model.servicos.persistencia.VistoriaDAO;
import model.servicos.persistencia.implementacaoCSV.conectores.CSVConnection;
import util.Utilities;

/**
 *
 * @author patrick-ribeiro
 */
public class VistoriaCSV implements VistoriaDAO {

    private final File ARQUIVO_DB = new File(Programa.getPropriedade("absoluteDatabasePath") + "vistorias.csv");
    private final File ARQUIVO_ITEM_DB = new File(Programa.getPropriedade("absoluteDatabasePath") + "itens-vistoria.csv");
    private final CSVConnection CONEXAO = new CSVConnection();
    private final CSVConnection CONEXAO_ITENS = new CSVConnection();
    private final ImagemDAO IMAGEM_DAO = DAOFactory.createImagemDAO();

    @Override
    public void inserir(Vistoria vistoria) {
        if (vistoria.getId() == null) {
            vistoria.setId(getUltimoID() + 1);
        }
        CONEXAO.open(ARQUIVO_DB);
        CONEXAO.writer().write(vistoria.toCSV());
        CONEXAO.writer().newLine();
        CONEXAO.close();
        CONEXAO_ITENS.open(ARQUIVO_ITEM_DB);
        for (ItemVistoria item : vistoria.getItens()) {
            CONEXAO_ITENS.writer().write(vistoria.getId() + ";" + item.toCSV());
            CONEXAO_ITENS.writer().newLine();
        }
        CONEXAO_ITENS.close();
        IMAGEM_DAO.exportar(vistoria);
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
                IMAGEM_DAO.importar(vistoriaEncontrada);
                CONEXAO.close();
                return vistoriaEncontrada;
            }
            linha = CONEXAO.reader().readLine();
        }
        CONEXAO.close();
        return null;
    }

    @Override
    public List<Vistoria> buscar(ItemVistoria item) {
        CONEXAO.open(ARQUIVO_DB);

        List<Vistoria> vistorias = new ArrayList<>();
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Vistoria vistoriaEncontrada = new Vistoria(linha.split(";"));
            addItens(vistoriaEncontrada);
            for (ItemVistoria itemVistoria : vistoriaEncontrada.getItens()) {
                if (itemVistoria.getId().equals(item.getId())) {
                    IMAGEM_DAO.importar(vistoriaEncontrada);
                    vistorias.add(vistoriaEncontrada);
                    break;
                }
            }
            linha = CONEXAO.reader().readLine();
        }
        CONEXAO.close();
        return vistorias;
    }

    private void addItens(Vistoria vistoria) {
        CONEXAO_ITENS.open(ARQUIVO_ITEM_DB);

        String linha = CONEXAO_ITENS.reader().readLine();
        while (linha != null) {
            String[] csv = linha.split(";");
            Integer idVistoria = Utilities.tryParseToInteger(csv[0]);
            if (idVistoria.equals(vistoria.getId())) {
                String[] csvItem = {csv[1], csv[2], csv[3], csv[4]};
                vistoria.addItem(new ItemVistoria(csvItem));
            }
            linha = CONEXAO_ITENS.reader().readLine();
        }
        CONEXAO_ITENS.close();
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
