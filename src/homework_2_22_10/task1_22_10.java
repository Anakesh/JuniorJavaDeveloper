package homework_2_22_10;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Pavel on 21.10.2018.
 */
public class task1_22_10 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Pattern pattern = Pattern.compile(",| |\\.");
        String regex = "a|an|the";
        System.out.println("Введите числа через запятую:");
        String str = in.nextLine();
        System.out.println(str.matches(regex));
        try {

            String[] strNumbers = pattern.split(in.nextLine().trim());
            int[] intNumbers = new int[strNumbers.length];
            for (int i = 0; i < strNumbers.length; i++) {
                intNumbers[i] = Integer.parseInt(strNumbers[i]);
            }
            int buff;
            for (int i = 0; i < intNumbers.length; i++) {
                for (int j = 1; j < intNumbers.length; j++) {
                    if (intNumbers[j] < intNumbers[j - 1]) {
                        buff = intNumbers[j];
                        intNumbers[j] = intNumbers[j - 1];
                        intNumbers[j - 1] = buff;
                    }
                }
            }
            for (int number : intNumbers) {
                System.out.print(number + " ");
            }
        } catch (Exception e) {
            System.out.println("Неверный формат.");
        }
    }
}
