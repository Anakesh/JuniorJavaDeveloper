package lesson_24_19_12_lambda;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Lambda {

    private static int someVar = 12;

    public static void main(String[] args) {
        /*
        лямбды представляют собой реализацию функционального интерфейса
        синтаксис
        (аргумент)->тело

        ()->{
            тело;
            на;
            несколько строк;
            return возвращаемое значение; - необязательно
        }

        (a,b)->a+b;
        (a,b)->{
            return a+b;
        };
        */

        new Thread(new Runnable() { //Runnable - функциональный интерфейс
            @Override
            public void run() {
                System.out.println("Hello");
            }
        });

        new Thread(() -> System.out.println("Hello"));

        Runnable runnable = () -> System.out.println("Hello");
        new Thread(runnable);

        Operation plus = (a, b) -> a + b;
        Operation minus = (a, b) -> a - b;

        double result = plus.execute(12, 23);
        result = minus.execute(12, 23);

        int someLocalVar = 12;

        Operation operation = (a, b) -> {
            someVar -= a;        //к переменным класса можно обращатся
//            someLocalVar +=b;   //к локальным нельзя
            return someVar;
        };

        operation.execute(1, 2);
        System.out.println(someVar);

        //ссылка на метод через нотацию
        ButtonFactory<Button> factory = Button::new;// :: - нотация
        Button button = factory.set("Cancel", "pink");
        System.out.println(button);


        //Предикаты - для проверок, всегда возвращают boolean
        Predicate<Integer> pos = (num) -> num>0;
        boolean predicateRes = pos.test(4);
        Predicate<Integer> neg = (num)-> num<0;
        predicateRes = neg.test(23);
        predicateRes = pos.or(neg).test(2);
        System.out.println(predicateRes);
        predicateRes = pos.and(neg).test(2);
        System.out.println(predicateRes);

        //Функции - для функций, возвращает заданное значение(второе)
        Function<Integer, Integer> plusTen = (a)->a+10;
        Function<Integer, Integer> minusTwo = (a)->a-2;
        Function<Integer, Integer> plusSix = (a)->a+6;

        int functionRes = plusSix.apply(4);
        functionRes = plusSix
                .andThen(plusTen)
                .andThen(plusTen)
                .compose(plusTen) // в обратном порядке
                .apply(12);
        System.out.println(functionRes);

        //Consumer - ничего не возвращает
        Consumer<Button> makeReset = (b) -> b.setValue("Reset");
        makeReset.accept(button);
        System.out.println(button);

        //Компаратор - для сравнения, возвращает число
        Button button1 = new Button("First","gray");
        Button button2 = new Button("Second","gray");
//        Comparator<Button> buttonComparator = (b1,b2) -> b1.getValue().compareTo(b2.getValue());
        Comparator<Button> buttonComparator = Comparator.comparing(Button::getValue);
        int compareRes = buttonComparator.compare(button1,button2);
        System.out.println(compareRes);
        compareRes = buttonComparator.reversed().compare(button1,button2);
        System.out.println(compareRes);

        System.out.println("------------------------------------------------");

        //forEach(lambda)
        List<Integer> integers = new ArrayList<>(Arrays.asList(23,67,12,-12,0));

//        integers.forEach((i)-> System.out.println(i));
//        integers.forEach(System.out::println);
        integers.forEach((i)-> System.out.print(i+" "));
        System.out.println();

        List<Integer> integers1 = new ArrayList<>();
        integers.forEach((i)->integers1.add(i+10));
        System.out.println(integers1);

        //removeIf()
        integers.removeIf((i)->i<20);
        System.out.println(integers);
        integers.removeIf(pos);
        System.out.println(integers);


        //Map.forEach()
        Map<String,String> books = new HashMap<>();
        books.put("Book 1", "Author 1");
        books.put("Book 2", "Author 2");
        books.forEach((key, value)-> System.out.println("Key: "+key+", Value: "+value));

        //Map.compute()
        books.compute("Book 1",(key,value)->value+" one more author");
        System.out.println(books);
//        books.computeIfAbsent() - еслм ключа нет
//        books.computeIfPresent() - если ключ есть

        //Map.getOrDefault()
        String author = books.getOrDefault("Book3","No author"); // если ключ есть то вернет значение по ключу, если нет то default значение
        System.out.println(author);
        System.out.println(books);

    }
}

//функциональный интерефейс означает наличие только одного абстрактного метода
@FunctionalInterface
interface Operation{
    double execute(int a, int b);
}

@FunctionalInterface
interface ButtonFactory<T extends Button>{
    T set(String value, String color);
}

class Button{
    private String value;
    private String color;

    public Button(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public static void tstVoid(Predicate<Integer> predicate, int i){
        predicate.test(i);
    }

    @Override
    public String toString() {
        return "Button{" +
                "value='" + value + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
