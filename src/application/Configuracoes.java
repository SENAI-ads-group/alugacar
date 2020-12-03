package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import model.entidades.ItemVistoria;

/**
 *
 * @author patrick-ribeiro
 */
public class Configuracoes {

    public static Properties getProperties() {
        try {
            Properties properties = new Properties();

            String caminhoPastaRaiz = new File("").getCanonicalPath();
            FileInputStream fileInput = new FileInputStream(caminhoPastaRaiz + "\\config.properties");
            properties.load(fileInput);

            String canonicalPath = new File("").getCanonicalPath();
            properties.put("canonicalPath", canonicalPath);
            return properties;
        } catch (IOException ex) {
            return null;
        }
    }

    public static List<ItemVistoria> loadItensVistoria() {
        List<ItemVistoria> itens = new ArrayList<>();
        try {
            Properties properties = new Properties();
            String caminhoPastaRaiz = new File("").getCanonicalPath();
            FileInputStream fileInput = new FileInputStream(caminhoPastaRaiz + "\\vistoria.properties");
            properties.load(fileInput);

            Set<String> fields = properties.stringPropertyNames();
            for (String str : fields) {
                boolean obrigatorio = Boolean.parseBoolean(properties.getProperty(str));
                itens.add(new ItemVistoria(str, obrigatorio));
            }
        } catch (IOException ex) {

        } finally {
            return itens;
        }
    }
}
