package homework_9_26_11_clientServer;


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
