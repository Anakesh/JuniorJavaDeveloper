package homework_13_10_12_threadSync.topHundred;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file path:");
        String filepath = scanner.nextLine();
        MultiThreadFileRead multiThreadFileRead = new MultiThreadFileRead();
        try {
            multiThreadFileRead.readFile(new File(filepath));
            System.out.println("Top hundred words:");
            for (Map.Entry<String,Integer> entry:multiThreadFileRead.getTopHundred().entrySet()){
                System.out.println("'"+entry.getKey()+"'\t number: "+ entry.getValue());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
