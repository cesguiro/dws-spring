package es.cesguiro.common.exception;

public class InsertResourceException extends RuntimeException {

    private static final String DESCRIPTION = "Exception inserting resource";

    public InsertResourceException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
