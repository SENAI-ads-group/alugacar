package entidades.services.persistence.csv;

import application.Configurations;
import entidades.Categoria;
import entidades.services.persistence.CategoriaPersistenceService;
import entidades.services.persistence.exceptions.PersistenceException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import util.Utilities;

/**
 *
 * @author Alexsander
 */
public class CategoriaPersistenceServiceCSV implements CategoriaPersistenceService {

    private File arquivoDB;
    private String canonicalPath;
    private CSVConnection connection;

    public CategoriaPersistenceServiceCSV() {
        String caminhoDB = Configurations.getProperties().getProperty("db.categoria");
        canonicalPath = Configurations.getProperties().getProperty("canonicalPath");

        arquivoDB = new File(canonicalPath + caminhoDB);
        connection = new CSVConnection();
    }

    @Override
    public void inserir(Categoria categoria) throws PersistenceException {
        if (categoria.getId() == null) {
            categoria.setId(getUltimoID() + 1);
        }
        if (buscar(categoria.getId()) != null) {
            throw new PersistenceException("A categoria já  existe !");
        }
        connection.open(arquivoDB);

        connection.writer().write(categoria.toCSV());
        connection.writer().newLine();

        connection.close();
    }

    @Override
    public void atualizar(Categoria categoria) {
        File arquivoDBTemp = new File(canonicalPath + "\\temp\\categorias-temp.csv");
        CSVConnection connectionTemp = new CSVConnection();

        connection.open(arquivoDB);
        connectionTemp.open(arquivoDBTemp);

        String linha = connection.reader().readLine();
        while (linha != null) {
            Categoria categoriaEncontrada = new Categoria(linha.split(";"));

            if (categoriaEncontrada.equals(categoria)) {
                connectionTemp.writer().write(categoria.toCSV());
            } else {
                connectionTemp.writer().write(categoriaEncontrada.toCSV());
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
    public Categoria buscar(Integer id) {
        if (id == null) {
            throw new IllegalStateException("id está nulo");
        }
        connection.open(arquivoDB);

        String linha = connection.reader().readLine();
        while (linha != null) {
            Categoria categoriaEncontrada = new Categoria(linha.split(";"));
            if (categoriaEncontrada.getId().equals(id)) {
                connection.close();
                return categoriaEncontrada;
            }
            linha = connection.reader().readLine();
        }
        connection.close();
        return null;
    }

    @Override
    public List<Categoria> buscarTodos() {
        connection.open(arquivoDB);

        List<Categoria> categoriasEncontradas = new ArrayList<>();
        String linha = connection.reader().readLine();
        while (linha != null) {
            Categoria categoriaEncontrada = new Categoria(linha.split(";"));
            categoriasEncontradas.add(categoriaEncontrada);
            linha = connection.reader().readLine();
        }

        connection.close();
        return categoriasEncontradas;
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
