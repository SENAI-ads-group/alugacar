package entidades.services.persistence.csv;

import entidades.Marca;
import entidades.Modelo;
import entidades.services.persistence.ModeloPersistenceService;
import entidades.services.persistence.csv.Connectors.CSVConnection;
import entidades.services.persistence.csv.Connectors.Configurations;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author patrick-ribeiro
 */
public class ModeloPersistenceServiceCSV implements ModeloPersistenceService {

    private String caminhoArquivo;
    CSVConnection connection = new CSVConnection();

    public ModeloPersistenceServiceCSV() {
        connection = new CSVConnection();
        String arquivo = Configurations.getProperties().getProperty("arquivo.marca");
        try {
            caminhoArquivo = new File("").getCanonicalPath() + arquivo;
        } catch (IOException ex) {
        }
    }

    @Override
    public void inserir(Modelo modelo) {
        connection.open(caminhoArquivo);

        connection.writer().write(modelo.toCSV());
        connection.writer().newLine();

        connection.close();
    }

    @Override
    public void atualizar(Modelo modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Modelo buscar(Integer id) {
        String linha = connection.reader().readLine();
        while (linha != null) {
            Modelo modeloEcontrado = new Modelo(linha.split(";"));
            if (modeloEcontrado.getId().equals(id)) {
                connection.close();
                return modeloEcontrado;
            }
            linha = connection.reader().readLine();
        }
        connection.close();
        return null;
    }

    @Override
    public List<Modelo> buscar(Marca marca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Modelo> buscarTodos() {
        connection.open(caminhoArquivo);
        List<Modelo> modelos = new ArrayList<>();

        String linha = connection.reader().readLine();
        while (linha != null) {
            String[] csv = linha.split(";");
            Modelo modelo = new Modelo(csv);
            modelos.add(modelo);
            linha = connection.reader().readLine();
        }

        connection.close();
        return modelos;
    }

}
