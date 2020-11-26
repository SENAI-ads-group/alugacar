package model.entidades.services.persistence.csv;

import model.entidades.services.persistence.exceptions.DBConnectionException;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 *
 * @author patrick-ribeiro
 */
public class CustomWriter extends BufferedWriter {

    public CustomWriter(java.io.Writer out) {
        super(out);
    }

    @Override
    public void write(String text) {
        try {
            super.write(text);
        } catch (IOException ex) {
            throw new DBConnectionException(ex.getMessage());
        }
    }

    @Override
    public void newLine() {
        try {
            super.newLine();
        } catch (IOException ex) {
            throw new DBConnectionException(ex.getMessage());
        }
    }

    @Override
    public void flush() {
        try {
            super.flush();
        } catch (IOException ex) {
            throw new DBConnectionException(ex.getMessage());
        }
    }

}
