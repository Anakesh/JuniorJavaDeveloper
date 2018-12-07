package homework_09_26_11_clientServer;

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
