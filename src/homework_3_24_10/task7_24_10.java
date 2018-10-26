package homework_3_24_10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class task7_24_10 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите строку: ");
        char[] arr = in.nextLine().toCharArray();
        StringBuilder currNum = new StringBuilder();
        List<Integer> numbers = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            if (Character.isDigit(arr[i])) {
                currNum.append(arr[i]);
            }
            if (!Character.isDigit(arr[i]) || i == arr.length - 1) {
                if (currNum.length() > 0) {
                    numbers.add(Integer.parseInt(currNum.toString()));
                    currNum = new StringBuilder();
                }
            }
        }
        System.out.println(numbers.toString());
    }
}
