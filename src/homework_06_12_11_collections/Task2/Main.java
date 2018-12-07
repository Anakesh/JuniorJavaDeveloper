package homework_06_12_11_collections.Task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    //    Задача 2
//    Написать программу со следующим функционалом:
//    Считывание 20 чисел с клавиатуры,
//    сохранение их в список,
//    сортировка по трём другим спискам:
//    число нацело делится на 3,
//    нацело делится на 2 и
//    все остальные.
//    Числа, которые делятся на 3 и на 2 одновременно, например 6, попадают в оба списка.
//
//    Метод printList() должен выводить на экран все элементы всех списков с новой строки.
//    Метод printList(String listName) должен выводить на экран все элементы указанного списка
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Integer> intList = new ArrayList<>();
        while (intList.size()<20) {
            intList.add(input.nextInt());
        }

        SortedLists sortedLists = new SortedLists(intList);
        sortedLists.printList();
        sortedLists.printList("BaseList");
    }
}