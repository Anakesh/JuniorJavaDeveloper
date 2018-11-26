package homework_8_21_11;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tasks tasks = new Tasks();
        Scanner input = new Scanner(System.in);
        //First task
        /*
        try{
            System.out.println("Path of file to copy:");
            String firstFileName = input.nextLine();
            System.out.println("Path of copy:");
            String secondFileName = input.next();
            System.out.println(tasks.doTaskOne(firstFileName,secondFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //Second task split
        try{
            System.out.println("Path of file to split:");
            String firstFileName = input.nextLine();
            System.out.println("Length in bytes of new files:");
            long lengthOfNewFiles = input.nextLong();
            tasks.doTaskTwoSplit(firstFileName,lengthOfNewFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
