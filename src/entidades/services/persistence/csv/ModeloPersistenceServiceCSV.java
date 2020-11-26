package entidades.services.persistence.csv;

import application.Configurations;
import entidades.Marca;
import entidades.Modelo;
import entidades.services.persistence.ModeloPersistenceService;
import entidades.services.persistence.exceptions.PersistenceException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author patrick-ribeiro
 */
public class ModeloPersistenceServiceCSV implements ModeloPersistenceService {

    private File arquivoDB;
    private String canonicalPath;
    private CSVConnection connection;

    public ModeloPersistenceServiceCSV() {
        String caminhoDB = Configurations.getProperties().getProperty("db.modelo");
        canonicalPath = Configurations.getProperties().getProperty("canonicalPath");

        arquivoDB = new File(canonicalPath + caminhoDB);
        connection = new CSVConnection();
    }

    @Override
    public void inserir(Modelo modelo) throws PersistenceException {
        if (modelo.getId() == null) {

        }
        if (buscar(modelo.getId()) != null) {
            throw new PersistenceException("O modelo já existe");
        }
        connection.open(arquivoDB);

        connection.writer().write(modelo.toCSV());
        connection.writer().newLine();

        connection.close();
    }

    @Override
    public void atualizar(Modelo modelo) {
        File arquivoDBTemp = new File(canonicalPath + "\\temp\\marcas-temp.csv");
        CSVConnection connectionTemp = new CSVConnection();

        connection.open(arquivoDB);
        connectionTemp.open(arquivoDBTemp);

        String linha = connection.reader().readLine();
        while (linha != null) {
            Modelo modeloEncontrado = new Modelo(linha.split(";"));

            if (modeloEncontrado.equals(modelo)) {
                connectionTemp.writer().write(modelo.toCSV());
            } else {
                connectionTemp.writer().write(modeloEncontrado.toCSV());
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
    public Modelo buscar(Integer id) {
        if (id == null) {
            throw new IllegalStateException("id está nulo");
        }
        connection.open(arquivoDB);

        String linha = connection.reader().readLine();
        while (linha != null) {
            Modelo modeloEncontrado = new Modelo(linha.split(";"));
            if (modeloEncontrado.getId().equals(id)) {
                connection.close();
                return modeloEncontrado;
            }
            linha = connection.reader().readLine();
        }
        connection.close();
        return null;
    }

    @Override
    public List<Modelo> buscar(Marca marca) {
        connection.open(arquivoDB);
        List<Modelo> modelos = new ArrayList<>();

        String linha = connection.reader().readLine();
        while (linha != null) {
            Modelo modelo = new Modelo(linha.split(";"));
            if (modelo.getMarca().equals(marca)) {
                modelos.add(modelo);
            }
            linha = connection.reader().readLine();
        }

        connection.close();
        return modelos;
    }

    @Override
    public List<Modelo> buscarTodos() {
        connection.open(arquivoDB);
        List<Modelo> modelos = new ArrayList<>();

        String linha = connection.reader().readLine();
        while (linha != null) {
            Modelo modelo = new Modelo(linha.split(";"));
            modelos.add(modelo);
            linha = connection.reader().readLine();
        }

        connection.close();
        return modelos;
    }

    public Modelo getUltimoRegistro() {
        connection.open(arquivoDB);

        String linhaAnterior = null;
        String linha = connection.reader().readLine();
        while (linha != null) {
            linhaAnterior = linha;
            linha = connection.reader().readLine();
        }

        connection.close();
        return new Modelo(linhaAnterior.split(";"));
    }

}
