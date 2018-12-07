package homework_10_30_11_tableInFile;

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
