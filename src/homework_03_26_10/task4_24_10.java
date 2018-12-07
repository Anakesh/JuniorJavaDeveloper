package homework_03_26_10;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class task4_24_10 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите длинну массива");
        int length;
        do{
            length = in.nextInt();
        }
        while(length%2!=0);
        int[] arr = new int[length];
        Random random = new Random();
        int leftSum =0 ;
        int rightSum = 0;
        for(int i=0;i<arr.length;i++){
            arr[i] = random.nextInt(11)-5;
            if(i+1<=arr.length/2) leftSum+=arr[i];
            else rightSum +=arr[i];
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(leftSum==rightSum ? "Суммы равны.":((leftSum>rightSum)?"Сумма левой части больше.":"Сумма правой части больше."));

    }
}
