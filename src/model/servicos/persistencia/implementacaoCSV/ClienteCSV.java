package model.servicos.persistencia.implementacaoCSV;

import model.servicos.persistencia.implementacaoCSV.conectores.CSVConnection;
import application.Programa;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.entidades.Cliente;
import model.exceptions.DBException;
import util.Utilities;
import model.servicos.persistencia.ClienteDAO;
import model.servicos.persistencia.DAOFactory;

/**
 *
 * @author Alexsander
 */
public class ClienteCSV implements ClienteDAO {

    private final File ARQUIVO_DB = new File(Programa.getPropriedade("absoluteDatabasePath") + "clientes.csv");
    private final CSVConnection CONEXAO = new CSVConnection();

    @Override
    public void inserir(Cliente cliente) throws DBException {
        if (cliente.getId() == null) {
            cliente.setId(getUltimoID() + 1);
        }
        if (buscar(cliente.getId()) != null) {
            throw new DBException("O cliente já existe");
        }
        CONEXAO.open(ARQUIVO_DB);

        CONEXAO.writer().write(cliente.toCSV());
        CONEXAO.writer().newLine();

        CONEXAO.close();
    }

    @Override
    public void atualizar(Cliente cliente) {
        File arquivoDBTemp = new File(Programa.getPropriedade("absoluteDatabasePath") + "temp-clientes.csv");
        CSVConnection conexaoTemp = new CSVConnection();

        CONEXAO.open(ARQUIVO_DB);
        conexaoTemp.open(arquivoDBTemp);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Cliente clienteEncontrado = new Cliente(linha.split(";"));

            if (clienteEncontrado.equals(cliente)) {
                conexaoTemp.writer().write(cliente.toCSV());
            } else {
                conexaoTemp.writer().write(clienteEncontrado.toCSV());
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
    public void excluir(int id) {
        if (buscar(id) == null) {
            throw new DBException("A marca não existe");
        }
        if (DAOFactory.createLocacaoDAO().buscar(buscar(id)).size() > 0) {
            throw new DBException("O cliente não pode ser excluído pois está associado à uma ou mais locações");
        }
        File arquivoDBTemp = new File(Programa.getPropriedade("absoluteDatabasePath") + "temp-clientes.csv");
        CSVConnection conexaoTemp = new CSVConnection();

        CONEXAO.open(ARQUIVO_DB);
        conexaoTemp.open(arquivoDBTemp);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Cliente clienteEncontrado = new Cliente(linha.split(";"));
            if (!clienteEncontrado.getId().equals(id)) {
                conexaoTemp.writer().write(clienteEncontrado.toCSV());
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
    public Cliente buscar(Integer id) {
        if (id == null) {
            throw new IllegalStateException("id está nulo");
        }
        CONEXAO.open(ARQUIVO_DB);

        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            Cliente clienteEncontrado = new Cliente(linha.split(";"));
            if (clienteEncontrado.getId().equals(id)) {
                CONEXAO.close();
                return clienteEncontrado;
            }
            linha = CONEXAO.reader().readLine();
        }
        CONEXAO.close();
        return null;
    }

    @Override
    public List<Cliente> buscarTodos() {
        CONEXAO.open(ARQUIVO_DB);

        List<Cliente> clientesEncontrados = new ArrayList<>();
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            String[] csv = linha.split(";");
            Cliente clienteEncontrado = new Cliente(csv);
            clientesEncontrados.add(clienteEncontrado);
            linha = CONEXAO.reader().readLine();
        }

        CONEXAO.close();
        return clientesEncontrados;

    }

    private Integer getUltimoID() {
        CONEXAO.open(ARQUIVO_DB);

        Integer ultimoID = 0;
        String linha = CONEXAO.reader().readLine();
        while (linha != null) {
            ultimoID = Utilities.tryParseToInteger(linha.split(";")[0]);
            linha = CONEXAO.reader().readLine();
        }
        if (ultimoID == null) {
            ultimoID = 0;
        }
        CONEXAO.close();
        return ultimoID;
    }

}
