package lesson_17_03_12.patterns.singletorn;

public class Singleton2 {
    private static final Singleton2 INSTANCE = new Singleton2();
    private Singleton2(){}

    //создание объекта при инициализации класса
    public static Singleton2 getInstance(){
        return INSTANCE;
    }

}
