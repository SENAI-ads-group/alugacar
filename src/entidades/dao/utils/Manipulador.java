package entidades.dao.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author patrick-ribeiro
 */
public class Manipulador {

    public static Properties getProperties() {
        try {
            Properties properties = new Properties();

            String caminhoPastaRaiz = new File("").getCanonicalPath();
            System.out.println("caminho raiz do programa " + caminhoPastaRaiz);
            FileInputStream fileInput = new FileInputStream(caminhoPastaRaiz + "\\config.properties");
            properties.load(fileInput);

            return properties;
        } catch (IOException ex) {
            return null;
        }
    }
}
