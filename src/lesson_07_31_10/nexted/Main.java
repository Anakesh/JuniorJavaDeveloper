package lesson_07_31_10.nexted;

public class Main {
    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass("Объект OuterClass");
        outerClass.getInner().someVoid();
    }
}
