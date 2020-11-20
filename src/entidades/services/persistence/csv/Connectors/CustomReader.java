package entidades.services.persistence.csv.Connectors;

import entidades.services.persistence.exceptions.DatabaseConnectionException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author patrick-ribeiro
 */
public class CustomReader extends BufferedReader {
    
    public CustomReader(Reader in) {
        super(in);
    }
    
    @Override
    public String readLine() {
        try {
            return super.readLine();
        } catch (IOException ex) {
            throw new DatabaseConnectionException(ex.getMessage());
        }
    }
    
}
