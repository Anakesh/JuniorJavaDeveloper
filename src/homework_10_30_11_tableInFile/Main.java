package homework_10_30_11_tableInFile;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter path to file with table:");
        String filePath = in.nextLine();
        try {
            TableHandler tableHandler = new TableHandler(filePath);
            while (true) {
                System.out.println("Enter your command: ");
                String command = in.nextLine();
                if (command.equals("-stop"))
                    break;
                try {
                    tableHandler.handleCommand(command);
                } catch (IOException | TableException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
//            e.printStackTrace();
        }
    }
}

