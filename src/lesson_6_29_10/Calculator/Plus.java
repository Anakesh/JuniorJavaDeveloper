package lesson_6_29_10.Calculator;

public class Plus extends Operation {
    public Plus(float firstNum, float secondNum) {
        super(firstNum, secondNum);
    }

    @Override
    public float execute() {
        return firstNum+secondNum;
    }
}
