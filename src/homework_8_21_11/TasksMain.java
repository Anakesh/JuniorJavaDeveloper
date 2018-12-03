package homework_8_21_11;

import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

public class TasksMain {
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
        }
*/

        //Second task split
/*
        try{
            System.out.println("Path of file to split:");
            String firstFileName = input.nextLine();
            System.out.println("Length in bytes of new files:");
            long lengthOfNewFiles = input.nextLong();
            tasks.doTaskTwoSplit(firstFileName,lengthOfNewFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        //Second task combine
/*
        try{
            System.out.println("Number of files to combine:");
            int numOfFiles = Integer.parseInt(input.nextLine());
            String[] filePaths = new String[numOfFiles];
            for(int i = 0;i<numOfFiles;i++){
                System.out.println("Enter path of file №"+(i+1));
                filePaths[i] = input.nextLine();
            }
            System.out.println("Path of new file:");
            String newFilePath = input.nextLine();
            tasks.doTaskTwoCombine(filePaths,newFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        //Third task XOR key encrypt/decrypt
/*
        try{
            System.out.println("Path of file to encrypt/decrypt:");
            String fileName = input.nextLine();
            System.out.println("Enter your key:");
            String key = input.nextLine();
            tasks.doTaskThreeXorKey(fileName,key.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        //Third task XOR file encrypt/decrypt
/*
        try{
            System.out.println("Path of file to encrypt/decrypt:");
            String targetFileName = input.nextLine();
            System.out.println("Path of key file:");
            String keyFileName = input.nextLine();
            tasks.doTaskThreeXorFile(targetFileName,keyFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        //Fourth task
/*
        try{
            System.out.println("Path of file to get byte tree set:");
            String fileName = input.nextLine();
            TreeSet<Byte> byteTreeSet = tasks.doTaskFour(fileName);
            System.out.println("Sorted bytes without repeat:");
            System.out.println(byteTreeSet);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        //Fifth task
/*
        try{
            System.out.println("Path of file to get number of commas:");
            String fileName = input.nextLine();
            int numberOfCommas = tasks.doTaskFive(fileName);
            System.out.println("Number of commas:");
            System.out.println(numberOfCommas);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        //Sixth task
/*
        try{
            System.out.println("Path of file to split:");
            String firstFileName = input.nextLine();
            System.out.println("Percentage amount of data that will be written in one file:");
            int percentage = input.nextInt();
            tasks.doTaskSix(firstFileName,percentage);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
    }
}


