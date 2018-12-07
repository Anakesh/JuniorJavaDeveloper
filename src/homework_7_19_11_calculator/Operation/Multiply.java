package homework_7_19_11_calculator.Operation;

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
