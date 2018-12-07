package homework_03_26_10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class task6_24_10 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите строку:");
        String str = in.nextLine();
        str = str.replaceAll(" ", "");
        List<Character> letters = new ArrayList<>();
        StringBuilder newStr = new StringBuilder();
        for(int i = 0;i<str.length();i++){
            if(!letters.contains(str.charAt(i))){
                letters.add(str.charAt(i));
                newStr.append(str.charAt(i));
            }
        }
        System.out.println("Новая строка: "+newStr.toString());
    }
}
