package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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
}
