package homework_7_19_11;

public class Plus implements IOperation {

    @Override
    public String getString() {
        return "+";
    }

    @Override
    public double execute(double firstElem, double secondElem) {
        return firstElem+secondElem;
    }
}
