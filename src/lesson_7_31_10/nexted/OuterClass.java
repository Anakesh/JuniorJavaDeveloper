package lesson_7_31_10.nexted;

public class OuterClass {
    String value;
    private static int intVar = 1;
    public OuterClass(String value){
        this.value = value;
    }

    public InnerClass getInner(){
        return new InnerClass("Объект InnerClass");
    }

    static class InnerClass{
        String val;
        public InnerClass(String val){
            this.val = val;
        }

        public void someVoid(){
            intVar+=12;
            System.out.println(intVar);
        }
    }
}
