package homework_11_05_12_patterns.TextProcessor;


public class ChatException extends Exception {
    public ChatException() {
        super();
    }

    public ChatException(String message) {
        super(message);
    }

    public ChatException(String message, Throwable cause) {
        super(message, cause);
    }
}
