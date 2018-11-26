package homework_6_12_11.Collections.Task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
//    Задача 3
//    Написать программу со следующим функционалом:
//    Считывание  с клавиатуры 10 слов в список строк.
//    Метод doubleValues должен удваивать слова по принципу a,b,c -> a,a,b,b,c,c.
//            Метод printList()  должен выводить результат на экран (каждое значение с новой строки).
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<String> list = new ArrayList<>();

        while(list.size()<10){
            list.add(input.nextLine());
        }

        AnotherListClaa<String> anotherListClaa = new AnotherListClaa<>(list);
        anotherListClaa.doubleValue();
        anotherListClaa.printList();
    }
}
