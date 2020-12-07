package model.servicos.persistencia.implementacaoCSV;

import application.Programa;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.entidades.ItemVistoria;
import model.entidades.Marca;
import model.exceptions.DBException;
import model.servicos.persistencia.DAOFactory;
import model.servicos.persistencia.ItemVistoriaDAO;
import model.servicos.persistencia.implementacaoCSV.conectores.CSVConnection;
import util.Utilities;

/**
 *
 * @author patrick-ribeiro
 */
public class ItemVistoriaCSV implements ItemVistoriaDAO {

    private final File ARQUIVO_DB = new File(Programa.getPropriedade("absoluteDatabasePath") + "itens-vistoria-global.csv");
    private final CSVConnection CONEXAO = new CSVConnection();

    @Override
    public void inserir(ItemVistoria item) {
        if (item.getId() == null) {
            item.setId(getUltimoID() + 1);
        }
        if (buscar(item.getId()) != null) {
            throw new DBException("O item já existe");
        }
        CONEXAO.open(ARQUIVO_DB);
        CONEXAO.writer().write(item.toCSV());
        CONEXAO.writer().newLine();
        CONEXAO.close();
    }

    @Override
    public void atualizar(ItemVistoria item) {
        File arquivoDBTemp = new File(Programa.getPropriedade("absoluteDatabasePath") + "temp-itens-vistoria-global.csv");
        CSVConnection conexaoTemp = new CSVConnection();
        CONEXAO.open(ARQUIVO_DB);
        conexaoTemp.open(arquivoDBTemp);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            ItemVistoria itemEncontrado = new ItemVistoria(linha.split(";"));
            if (itemEncontrado.equals(item)) {
                conexaoTemp.writer().write(item.toCSV());
            } else {
                conexaoTemp.writer().write(itemEncontrado.toCSV());
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
            throw new DBException("A marca não existe");
        }
        if (DAOFactory.createVistoriaDAO().buscar(buscar(id)).size() > 0) {
            throw new DBException("O item não pode ser excluída pois está associado à uma ou mais vistoria");
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
    public ItemVistoria buscar(Integer id) {
        if (id == null) {
            throw new IllegalStateException("id está nulo");
        }
        CONEXAO.open(ARQUIVO_DB);
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            String[] csv = linha.split(";");
            ItemVistoria itemEncontrado = new ItemVistoria(csv);

            if (itemEncontrado.getId().equals(id)) {
                CONEXAO.close();
                return itemEncontrado;
            }
            linha = CONEXAO.reader().readLine();
        }
        CONEXAO.close();
        return null;
    }

    @Override
    public List<ItemVistoria> buscarTodos() {
        CONEXAO.open(ARQUIVO_DB);

        List<ItemVistoria> itensEncontrados = new ArrayList<>();
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            String[] csv = linha.split(";");
            ItemVistoria item = new ItemVistoria(csv);
            itensEncontrados.add(item);
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
        return itensEncontrados;
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
