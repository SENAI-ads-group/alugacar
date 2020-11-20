package entidades.services.persistence.csv;

import entidades.Marca;
import entidades.services.persistence.csv.Connectors.CSVConnection;
import entidades.services.persistence.csv.Connectors.Configurations;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import entidades.services.persistence.MarcaPersistenceService;
import entidades.services.persistence.csv.Connectors.CustomWriter;
import entidades.services.persistence.exceptions.DatabaseConnectionException;
import java.io.FileWriter;

/**
 *
 * @author Patrick-Ribeiro
 */
public class MarcaPersistenceServiceCSV implements MarcaPersistenceService {

    private File arquivoDB;
    private File arquivoDBTemp;
    private CSVConnection connection;

    public MarcaPersistenceServiceCSV() {
        String caminhoDB = Configurations.getProperties().getProperty("arquivo.marca");
        String caminhoTempDB = Configurations.getProperties().getProperty("arquivo.marca.temp");
        try {
            arquivoDB = new File(new File("").getCanonicalPath() + caminhoDB);
            arquivoDBTemp = new File(new File("").getCanonicalPath() + caminhoTempDB);
        } catch (IOException ex) {
        }
        connection = new CSVConnection();
    }

    @Override
    public void inserir(Marca marca) throws DatabaseConnectionException {
        connection.open(arquivoDB);

        connection.writer().write(marca.toCSV());
        connection.writer().newLine();

        connection.close();
    }

    @Override
    public void atualizar(Marca marca) {
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
        boolean success = arquivoDBTemp.renameTo(arquivoDB);
        System.out.println(success);
    }

    @Override
    public Marca buscar(Integer id) {
        connection.open(arquivoDB);

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

}
