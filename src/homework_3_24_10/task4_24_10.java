package homework_3_24_10;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class task4_24_10 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите длинну массива");
        int length = in.nextInt();
        int[] arr = new int[length];
        Random random = new Random();
        for(int i=0;i<arr.length;i++){
            arr[i] = random.nextInt(11)-5;
        }
        System.out.println(Arrays.toString(arr));
    }
}
