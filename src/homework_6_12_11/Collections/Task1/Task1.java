package homework_6_12_11.Collections.Task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task1 {
//    Задача 1
//    Написать программу со следующим функционалом:
//    Считывание максимум 5 строк с клавиатуры,
//    занесение строк в список,
//    поиск самой короткой строки,
//    вывод самой короткой строки на экран по запросу пользователя (команда в консоле /short string)
//    Если одинаково коротких строк несколько, выводить каждую с новой строки.

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<String> inputList = new ArrayList<>();
        for(int i=0;i<5;i++)
            inputList.add(input.nextLine());
        System.out.println("Waiting for command.");
        while(true){
            String in = input.nextLine();
            if(in.equals("/short string")){
                List<String> shortestStrings = findShortestStrings(inputList);
                System.out.println("Shortest string(s):");
                for(String str:shortestStrings)
                    System.out.print(str+" ");
                break;
            }
            else
                System.out.println("Unknown command");
        }
    }
    private static List<String> findShortestStrings(List<String> list){
        int minLength = Integer.MAX_VALUE;
        List<String> shortestStrings= new ArrayList<>();
        for(String str:list){
            if(str.length()<minLength){
                minLength = str.length();
                shortestStrings = new ArrayList<>();
                shortestStrings.add(str);
            }
            else if(str.length()==minLength)
                shortestStrings.add(str);
        }
        return shortestStrings;
    }
}
