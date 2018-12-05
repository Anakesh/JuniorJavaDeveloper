package homework_7_19_11.Operation;

public class Multiply implements IOperation {
    @Override
    public String getString() {
        return "*";
    }

    @Override
    public double execute(double firstElem, double secondElem) {
        return firstElem*secondElem;
    }
}
