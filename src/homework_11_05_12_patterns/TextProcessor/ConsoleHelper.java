package homework_11_05_12_patterns.TextProcessor;

import java.util.Scanner;


public class ConsoleHelper {
    public static void writeString(String text){
        System.out.println(text);
    }
    public static String readString(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
