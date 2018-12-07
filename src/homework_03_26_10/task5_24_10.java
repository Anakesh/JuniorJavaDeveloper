package homework_03_26_10;

import java.util.Scanner;

public class task5_24_10 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите строку:");
        String str = in.nextLine();
        System.out.println("Введите подстроку для замены: ");
        String oldSubStr = in.nextLine();
        System.out.println("Введите новую подстроку: ");
        String newSubStr = in.nextLine();
        if(str.contains(oldSubStr)){
            str = str.replaceAll(oldSubStr,newSubStr);
            System.out.println("Новая подстрока: " + str);
        }else{
            System.out.println("В введенной строке отсутствует указанная подстрока.");
        }
    }
}
