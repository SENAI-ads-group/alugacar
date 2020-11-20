package entidades.services.persistence.csv.Connectors;

import entidades.services.persistence.exceptions.DatabaseConnectionException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author patrick-ribeiro
 */
public class Connection {

    CustomWriter writer;
    CustomReader reader;

    public CustomWriter writer() {
        if (writer == null) {
            throw new IllegalStateException("A conexão não foi aberta");
        }
        return writer;
    }

    public CustomReader reader() {
        if (reader == null) {
            throw new IllegalStateException("A conexão não foi aberta");
        }
        return reader;
    }

    public void open(String caminho) {
        try {
            writer = new CustomWriter(new FileWriter(caminho, true));
            reader = new CustomReader(new FileReader(caminho));
        } catch (IOException ex) {
            throw new DatabaseConnectionException(ex.getMessage());
        }
    }

    public void close() {
        try {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        } catch (IOException ex) {
            throw new DatabaseConnectionException(ex.getMessage());
        }
    }

}
