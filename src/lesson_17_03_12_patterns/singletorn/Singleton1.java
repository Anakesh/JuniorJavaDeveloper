package lesson_17_03_12_patterns.singletorn;

public class Singleton1 {

    private static Singleton1 instance;
    //Запретить создание объектов из вне
    private Singleton1(){}

    //создание объекта по требованию
    public static Singleton1 getInstance(){
        if(instance==null)
            instance = new Singleton1();
        return instance;
    }
}
