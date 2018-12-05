package homework_7_19_11;

public class Power implements IOperation {

    @Override
    public String getString() {
        return "^";
    }

    @Override
    public double execute(double firstElem, double secondElem) {
        return Math.pow(firstElem,secondElem);
    }
}
