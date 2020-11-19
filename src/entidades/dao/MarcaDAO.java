package entidades.dao;

import entidades.Marca;
import entidades.dao.interfaces.IMarcaDAO;
import entidades.dao.utils.Manipulador;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Patrick-Ribeiro
 */
public class MarcaDAO implements IMarcaDAO {

    private Properties properties;
    private static String caminhoArquivo;

    public MarcaDAO() {
        properties = Manipulador.getProperties();
        String arquivo = properties.getProperty("arquivo.marca");
        try {
            caminhoArquivo = new File("").getCanonicalPath() + arquivo;
        } catch (IOException ex) {

        }
    }

    @Override
    public void inserir(Marca marca) {
        try (FileWriter fileWriter = new FileWriter(caminhoArquivo, true);
                BufferedWriter bfWriter = new BufferedWriter(fileWriter)) {

            bfWriter.write(marca.toCSV());
            bfWriter.newLine();

            bfWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace(); //implementar logs
        }
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
        try (FileReader fileReader = new FileReader(caminhoArquivo);
                BufferedReader bfReader = new BufferedReader(fileReader)) {

            String linha = bfReader.readLine();
            while (linha != null) {
                String[] csv = linha.split(";");
                Marca marcaEncontrada = new Marca(csv);

                if (marcaEncontrada.getId().equals(id)) {
                    bfReader.close();
                    return marcaEncontrada;
                }
                linha = bfReader.readLine();
            }
            bfReader.close();
            return null;
        } catch (IOException ex) {
            ex.printStackTrace(); //implementar logs
            return null;
        }
    }

    @Override
    public List<Marca> listar() {
        List<Marca> marcasEncontradas = new ArrayList<>();

        try (FileReader fileReader = new FileReader(caminhoArquivo);
                BufferedReader bfReader = new BufferedReader(fileReader)) {

            String linha = bfReader.readLine();
            while (linha != null) {
                String[] csv = linha.split(";");
                Marca marcaEncontrada = new Marca(csv);
                marcasEncontradas.add(marcaEncontrada);
                linha = bfReader.readLine();
            }
            bfReader.close();
            return marcasEncontradas;
        } catch (IOException ex) {
            ex.printStackTrace(); //implementar logs
            return new ArrayList<>();
        }
    }

}
