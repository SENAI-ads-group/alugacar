package entidades.services.persistence.csv;

import application.Configurations;
import entidades.Cliente;
import entidades.services.persistence.ClientePersistenceService;
import entidades.services.persistence.exceptions.PersistenceException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import util.Utilities;

/**
 *
 * @author Alexsander
 */
public class ClientePersistenceServiceCSV implements ClientePersistenceService {

    private File arquivoDB;
    private String canonicalPath;
    private CSVConnection connection;

    public ClientePersistenceServiceCSV() {

        String caminhoDB = Configurations.getProperties().getProperty("db.cliente");
        canonicalPath = Configurations.getProperties().getProperty("canonicalPath");

        arquivoDB = new File(canonicalPath + caminhoDB);
        connection = new CSVConnection();

    }

    @Override
    public void inserir(Cliente cliente) throws PersistenceException {

        if (cliente.getId() == null) {
            cliente.setId(getUltimoID() + 1);
        }
        if (buscar(cliente.getId()) != null) {
            throw new PersistenceException("O cliente já existe");
        }

        connection.open(arquivoDB);

        connection.writer().write(cliente.toCSV());
        connection.writer().newLine();

        connection.close();

    }

    @Override
    public void atualizar(Cliente cliente) {
        File arquivoDBTemp = new File(canonicalPath + "\\temp\\clientes-temp.csv");
        CSVConnection connectionTemp = new CSVConnection();

        connection.open(arquivoDB);
        connectionTemp.open(arquivoDBTemp);

        String linha = connection.reader().readLine();
        while (linha != null) {
            Cliente clienteEncontrado = new Cliente(linha.split(";"));

            if (clienteEncontrado.equals(cliente)) {
                connectionTemp.writer().write(cliente.toCSV());
            } else {
                connectionTemp.writer().write(clienteEncontrado.toCSV());
            }
            connectionTemp.writer().flush();
            connectionTemp.writer().newLine();
            linha = connection.reader().readLine();
        }

        connectionTemp.close();
        connection.close();
        arquivoDB.delete();
        arquivoDBTemp.renameTo(arquivoDB);
    }

    @Override
    public Cliente buscar(Integer id) {
        if (id == null) {
            throw new IllegalStateException("id está nulo");
        }
        connection.open(arquivoDB);

        String linha = connection.reader().readLine();
        while (linha != null) {
            Cliente clienteEncontrado = new Cliente(linha.split(";"));
            if (clienteEncontrado.getId().equals(id)) {
                connection.close();
                return clienteEncontrado;
            }
            linha = connection.reader().readLine();
        }
        connection.close();
        return null;
    }

    @Override
    public List<Cliente> buscarTodos() {
        connection.open(arquivoDB);

        List<Cliente> clientesEncontrados = new ArrayList<>();
        String linha = connection.reader().readLine();
        while (linha != null) {
            String[] csv = linha.split(";");
            Cliente clienteEncontrado = new Cliente(csv);
            clientesEncontrados.add(clienteEncontrado);
            linha = connection.reader().readLine();
        }

        connection.close();
        return clientesEncontrados;

    }

    private Integer getUltimoID() {
        connection.open(arquivoDB);

        Integer ultimoID = 1;
        String linha = connection.reader().readLine();
        while (linha != null) {
            ultimoID = Utilities.tryParseToInteger(linha.split(";")[0]);
            linha = connection.reader().readLine();
        }
        if (ultimoID == null) {
            ultimoID = 1;
        }

        connection.close();
        return ultimoID;
    }

}
