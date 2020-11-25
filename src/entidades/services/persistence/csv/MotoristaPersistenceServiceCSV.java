package entidades.services.persistence.csv;

import application.Configurations;
import entidades.Motorista;
import entidades.services.persistence.CNHPersistenseService;
import entidades.services.persistence.MotoristaPersistenceService;
import entidades.services.persistence.PersistenceFactory;
import entidades.services.persistence.exceptions.PersistenceException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import util.Utilities;

/**
 *
 * @author Alexsander
 */
public class MotoristaPersistenceServiceCSV implements MotoristaPersistenceService {

    private final File arquivoDB;
    private final String canonicalPath;
    private final CSVConnection connection;

    public MotoristaPersistenceServiceCSV() {
        String caminhoDB = Configurations.getProperties().getProperty("db.motorista");
        canonicalPath = Configurations.getProperties().getProperty("canonicalPath");

        arquivoDB = new File(canonicalPath + caminhoDB);
        connection = new CSVConnection();
    }

    @Override
    public void inserir(Motorista motorista) throws PersistenceException {
        if (motorista.getId() == null) {
            motorista.setId(getUltimoID() + 1);
        }
        if (buscar(motorista.getId()) != null) {
            throw new PersistenceException("O motorista já existe");
        }
        connection.open(arquivoDB);

        connection.writer().write(motorista.toCSV());
        connection.writer().newLine();

        connection.close();

        CNHPersistenseService cnhPersistenceService = PersistenceFactory.createCNHService();
        cnhPersistenceService.inserir(motorista.getCnh());
    }

    @Override
    public void atualizar(Motorista motorista) {
        File arquivoDBTemp = new File(canonicalPath + "\\temp\\motorista-temp.csv");
        CSVConnection connectionTemp = new CSVConnection();

        connection.open(arquivoDB);
        connectionTemp.open(arquivoDBTemp);

        String linha = connection.reader().readLine();
        while (linha != null) {
            Motorista motoristaEncontrado = new Motorista(linha.split(";"));

            if (motoristaEncontrado.equals(motorista)) {
                connectionTemp.writer().write(motorista.toCSV());
            } else {
                connectionTemp.writer().write(motoristaEncontrado.toCSV());
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
    public Motorista buscar(Integer id) {
        if (id == null) {
            throw new IllegalStateException("id está nulo");
        }
        connection.open(arquivoDB);
        System.out.println(arquivoDB.toString());
        String linha = connection.reader().readLine();
        while (linha != null) {
            String[] csv = linha.split(";");
            Motorista motoristaEncontrado = new Motorista(csv);

            if (motoristaEncontrado.getId().equals(id)) {
                connection.close();
                return motoristaEncontrado;
            }
            linha = connection.reader().readLine();
        }
        connection.close();
        return null;
    }

    @Override
    public List<Motorista> buscarTodos() {
        connection.open(arquivoDB);

        List<Motorista> motoristasEncontrados = new ArrayList<>();
        String linha = connection.reader().readLine();

        while (linha != null) {
            Motorista motoristaEncontrado = new Motorista(linha.split(";"));
            motoristasEncontrados.add(motoristaEncontrado);
            linha = connection.reader().readLine();
        }

        connection.close();
        return motoristasEncontrados;
    }

    private Integer getUltimoID() {
        connection.open(arquivoDB);

        Integer ultimoID = 0;
        String linha = connection.reader().readLine();
        while (linha != null) {
            ultimoID = Utilities.tryParseToInteger(linha.split(";")[0]);
            if (ultimoID == null) {
                ultimoID = 0;
            }
            linha = connection.reader().readLine();
        }

        connection.close();
        return ultimoID;
    }

}
