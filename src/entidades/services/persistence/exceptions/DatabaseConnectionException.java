package entidades.services.persistence.exceptions;

/**
 *
 * @author patrick-ribeiro
 */
public class DatabaseConnectionException extends RuntimeException {

    public DatabaseConnectionException(String message) {
        super(message);
    }

}
