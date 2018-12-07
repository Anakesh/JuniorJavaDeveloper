package homework_08_21_11_files;

import java.util.Scanner;

public class MoreTasksMain {
    public static void main(String[] args) {
        MoreTasks moreTasks = new MoreTasks();
        Scanner input = new Scanner(System.in);

        //First task
/*
        try{
            System.out.println("Path of file to filter:");
            String fileName = input.nextLine();
            System.out.println("Directory path for new file:");
            String newDirectoryName = input.next();
            System.out.println("Lower range to filter:");
            int lowerRange = input.nextInt();
            System.out.println("Higher range to filter");
            int higherRange = input.nextInt();
            moreTasks.doTaskOne(fileName,newDirectoryName,lowerRange,higherRange);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        //Second task
/*
        try {
            System.out.println("Directory path:");
            String folder = input.nextLine();
            moreTasks.doTaskTwo(folder);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        //Third task
/*
        try {
            System.out.println("Source directory path to copy from:");
            String sourceFolder = input.nextLine();
            System.out.println("Destination directory path to copy from:");
            String destinationFolder = input.nextLine();
            moreTasks.doTaskThree(sourceFolder, destinationFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
    }
}
