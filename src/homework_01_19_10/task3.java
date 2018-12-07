package homework_01_19_10;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class task3
{
    public static void main(String[] args) {
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Площаль первого овала в дм2:");
        try {
            int firstCircleSquare = Integer.parseInt(buffReader.readLine().trim()) * 100;
            System.out.println("Площаль второго овала в см2:");
            int secondCircleSquare = Integer.parseInt(buffReader.readLine().trim());
            int ringSquare = (firstCircleSquare>secondCircleSquare) ? firstCircleSquare-secondCircleSquare: secondCircleSquare-firstCircleSquare;
            System.out.println("Площадь овального кольца равна "+ringSquare+" см2");
        }
        catch(Exception e){
            System.out.println("Неверный формат");
        }
    }
}
