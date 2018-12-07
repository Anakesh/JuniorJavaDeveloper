package lesson_06_29_10.Calculator;

public class Minus extends Operation {

    public Minus(float firstNum, float secondNum) {
        super(firstNum, secondNum);
    }


    @Override
    public float execute() {
        return firstNum-secondNum;
    }
}
