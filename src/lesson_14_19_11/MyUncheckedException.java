package lesson_14_19_11;

public class MyUncheckedException extends RuntimeException {
    public MyUncheckedException(String message) {
        super(message);
    }
    public MyUncheckedException(String message, Throwable cause) {
        super(message,cause);
    }

}
