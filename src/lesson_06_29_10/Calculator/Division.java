package lesson_06_29_10.Calculator;

public class Division extends Operation {
    public Division(float firstNum, float secondNum) {
        super(firstNum, secondNum);
    }

    @Override
    public float execute() {
        if(secondNum!=0)
        return firstNum/secondNum;
        else{
            System.out.println("Деление на ноль запрещенно");
            return Float.MAX_VALUE;
        }
    }
}
