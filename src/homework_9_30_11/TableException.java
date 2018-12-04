package homework_9_30_11;

/**
 * Created by Pavel on 04.12.2018.
 */
public class TableException extends Exception {
    public TableException() {
        super();
    }

    public TableException(String message) {
        super(message);
    }

    public TableException(String message, Throwable cause) {
        super(message, cause);
    }
}
