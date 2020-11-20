package entidades.services.persistence.csv;

import entidades.Marca;
import entidades.services.persistence.csv.Connectors.Connection;
import entidades.services.persistence.csv.Connectors.Configurations;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import entidades.services.persistence.MarcaPersistenceService;

/**
 *
 * @author Patrick-Ribeiro
 */
public class MarcaPersistenceServiceCSV implements MarcaPersistenceService {

    private static String caminhoArquivo;
    private Connection connection;

    public MarcaPersistenceServiceCSV() {
        connection = new Connection();
        String arquivo = Configurations.getProperties().getProperty("arquivo.marca");
        try {
            caminhoArquivo = new File("").getCanonicalPath() + arquivo;
        } catch (IOException ex) {
        }
    }

    @Override
    public void inserir(Marca marca) {
        connection.open(caminhoArquivo);

        connection.writer().write(marca.toCSV());
        connection.writer().newLine();

        connection.close();
    }

    @Override
    public void atualizar(Marca marca) {
        List<Marca> marcas = listar();

        int index = marcas.indexOf(marca);
        if (index > -1) {
            marcas.remove(index);
            marcas.add(index, marca);
        }
        File file = new File(caminhoArquivo);
        file.delete();

        for (Marca obj : marcas) {
            inserir(obj);
        }
    }

    @Override
    public Marca buscar(Integer id) {
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
    public List<Marca> listar() {
        connection.open(caminhoArquivo);
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
