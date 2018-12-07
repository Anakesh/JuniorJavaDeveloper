package lesson_07_31_10.AnonymousClass;

public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator(3,5);
        calc.summ(new Operation() {
            @Override
            public double apply(double var1, double var2) {
                return var1 + var2;
            }
        }, new SomeInterface() {
            @Override
            public String SomeInterfaceStr(String string) {
                return string+string+string;
            }
        });

    }
}
