package homework_7_19_11_calculator;

public class CalculationException extends Exception {
    public CalculationException() {
        super();
    }

    public CalculationException(String message) {
        super(message);
    }

    public CalculationException(String message, Throwable cause) {
        super(message, cause);
    }

}
