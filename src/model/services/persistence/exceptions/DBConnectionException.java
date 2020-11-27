package model.services.persistence.exceptions;

/**
 *
 * @author patrick-ribeiro
 */
public class DBConnectionException extends RuntimeException {

    public DBConnectionException(String message) {
        super(message);
    }

}
