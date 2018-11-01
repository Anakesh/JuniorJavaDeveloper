package lesson_7_31_10.AnonymousClass;

import lesson_7_31_10.AnonymousClass.Operation;

public class Calculator {

    int a;
    int b;
    public Calculator(int a, int b){
        this.a = a;
        this.b = b;
    }
    public double summ(Operation operation, SomeInterface someInterface){
        System.out.println(someInterface.SomeInterfaceStr("Text"));
        return operation.apply(a,b);
    }
    public void minus(SomeClass someClass){
        someClass.apply();
    }
}
