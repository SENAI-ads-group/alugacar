package model.services.persistence.csv;

import model.services.persistence.exceptions.DBConnectionException;
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
            throw new DBConnectionException(ex.getMessage());
        }
    }
    
}
