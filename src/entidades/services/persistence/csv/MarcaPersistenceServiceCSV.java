package entidades.services.persistence.csv;

import application.Configurations;
import entidades.Marca;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import entidades.services.persistence.MarcaPersistenceService;
import entidades.services.persistence.exceptions.PersistenceException;
import util.Utilities;

/**
 *
 * @author Patrick-Ribeiro
 */
public class MarcaPersistenceServiceCSV implements MarcaPersistenceService {

    private final File arquivoDB;
    private final String canonicalPath;
    private final CSVConnection connection;

    public MarcaPersistenceServiceCSV() {
        String caminhoDB = Configurations.getProperties().getProperty("db.marca");
        canonicalPath = Configurations.getProperties().getProperty("canonicalPath");

        arquivoDB = new File(canonicalPath + caminhoDB);
        connection = new CSVConnection();
    }

    @Override
    public void inserir(Marca marca) throws PersistenceException {
        if (marca.getId() == null) {
            marca.setId(getUltimoID() + 1);
        }
        if (buscar(marca.getId()) != null) {
            throw new PersistenceException("A marca já existe");
        }
        connection.open(arquivoDB);

        connection.writer().write(marca.toCSV());
        connection.writer().newLine();

        connection.close();
    }

    @Override
    public void atualizar(Marca marca) {
        File arquivoDBTemp = new File(canonicalPath + "\\temp\\marcas-temp.csv");
        CSVConnection connectionTemp = new CSVConnection();

        connection.open(arquivoDB);
        connectionTemp.open(arquivoDBTemp);

        String linha = connection.reader().readLine();
        while (linha != null) {
            Marca marcaEncontrada = new Marca(linha.split(";"));

            if (marcaEncontrada.equals(marca)) {
                connectionTemp.writer().write(marca.toCSV());
            } else {
                connectionTemp.writer().write(marcaEncontrada.toCSV());
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
    public Marca buscar(Integer id) {
        if (id == null) {
            throw new IllegalStateException("id está nulo");
        }
        connection.open(arquivoDB);
        System.out.println(arquivoDB.toString());
        String linha = connection.reader().readLine();
        while (linha != null) {
            String[] csv = linha.split(";");
            Marca marcaEncontrada = new Marca(csv);

            if (marcaEncontrada.getId().equals(id)) {
                connection.close();
                return marcaEncontrada;
            }
            linha = connection.reader().readLine();
        }
        connection.close();
        return null;
    }

    @Override
    public List<Marca> buscarTodos() {
        connection.open(arquivoDB);

        List<Marca> marcasEncontradas = new ArrayList<>();
        String linha = connection.reader().readLine();
        while (linha != null) {
            String[] csv = linha.split(";");
            Marca marcaEncontrada = new Marca(csv);
            marcasEncontradas.add(marcaEncontrada);
            linha = connection.reader().readLine();
        }

        connection.close();
        return marcasEncontradas;
    }

    private Integer getUltimoID() {
        connection.open(arquivoDB);

        Integer ultimoID = 1;
        String linha = connection.reader().readLine();
        while (linha != null) {
            ultimoID = Utilities.tryParseToInteger(linha.split(";")[0]);
            if (ultimoID == null) {
                ultimoID = 1;
            }
            linha = connection.reader().readLine();
        }

        connection.close();
        return ultimoID;
    }

}
