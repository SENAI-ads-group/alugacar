package aplicacao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author patrick-ribeiro
 */
public class Configuracoes {

    public static Properties getProperties() {
        try {
            Properties properties = new Properties();

            String caminhoPastaRaiz = new File("").getCanonicalPath();
            FileInputStream fileInput = new FileInputStream(caminhoPastaRaiz + "/config.properties");
            properties.load(fileInput);

            String canonicalPath = new File("").getCanonicalPath();
            properties.put("pasta-raiz", canonicalPath);

            String databasePath = "\\\\" + properties.getProperty("servidor") + "/" + properties.getProperty("database") + "/";
            properties.put("absoluteDatabasePath", databasePath);

            String databaseImagePath = "\\\\" + properties.getProperty("servidor") + "/" + properties.getProperty("database") + "/" + "imagens/";
            properties.put("absoluteDatabaseImagePath", databaseImagePath);

            return properties;
        } catch (IOException ex) {
            return null;
        }
    }

    public static void setProperties(Properties properties) {
        try (FileOutputStream output = new FileOutputStream("/config.properties")) {
            properties.store(output, null);
        } catch (IOException ex) {
            Logger.getLogger(Configuracoes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
