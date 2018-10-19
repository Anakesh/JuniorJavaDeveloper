package homework_19_10;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class task4 {
    public static void main(String[] args) {
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Первое число:");
        try {
            float firstNumber = Float.parseFloat(buffReader.readLine().trim());
            System.out.println("Второе число:");
            float secondNumber = Float.parseFloat(buffReader.readLine().trim());
            System.out.println((Math.abs(10-firstNumber)<Math.abs(10-secondNumber))? "Первое число ближе к десяти":"Второе число ближе к десяти");
        }catch(Exception e){
            System.out.println("Неверный формат");
        }

    }
}
