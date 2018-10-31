package lesson_6_29_10.Calculator;

abstract public class Operation {
    float firstNum;
    float secondNum;
    public Operation(float firstNum,float secondNum){
        this.firstNum = firstNum;
        this.secondNum = secondNum;
    }
    abstract public float execute();
}
