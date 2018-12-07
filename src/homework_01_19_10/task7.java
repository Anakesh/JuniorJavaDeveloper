package homework_01_19_10;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class task7 {
    public static void main(String[] args) {
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Натуральное число:");
        try {
            String[] strNumbers = buffReader.readLine().trim().split("");
            int[] intNumbers = new int[strNumbers.length];
            int highestNumber = Integer.MIN_VALUE;
            for(int i = 0;i<strNumbers.length;i++){
                intNumbers[i] = Integer.parseInt(strNumbers[i]);
                if(intNumbers[i]>highestNumber)
                    highestNumber = intNumbers[i];
            }
            System.out.println("Наибольшим числом является "+highestNumber);
        }
        catch (Exception e){
            System.out.println("Неверный формат.");
        }
    }
}
