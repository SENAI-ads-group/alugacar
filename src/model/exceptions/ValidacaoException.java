package model.exceptions;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author patrick-ribeiro
 */
public class ValidacaoException extends RuntimeException {

    private final Map<String, String> errors = new HashMap<>();

    public ValidacaoException(String message) {
        super(message);
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void addError(String field, String message) {
        errors.put(field, message);
    }
}
