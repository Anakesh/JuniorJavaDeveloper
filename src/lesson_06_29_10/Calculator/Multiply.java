package lesson_06_29_10.Calculator;

public class Multiply extends Operation {
    public Multiply(float firstNum, float secondNum) {
        super(firstNum, secondNum);
    }
    @Override
    public float execute() {
        return firstNum*secondNum;
    }

}
