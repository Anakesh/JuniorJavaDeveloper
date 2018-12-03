package chat;

import java.util.Scanner;

public class ConsoleHelper {
    public static void writeString(String str){
        System.out.println(str);
    }
    public static String readString(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
