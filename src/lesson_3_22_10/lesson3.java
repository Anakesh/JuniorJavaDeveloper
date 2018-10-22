package lesson_3_22_10;

import java.util.Arrays;

public class lesson3 {
    public static void main(String[] args) {
        int[] arr = new int[12];
        System.out.println(Arrays.toString(arr));
        int a = 5;
        int [] arr1 = {2,12,45,78,34,a};

        System.arraycopy(arr1, 2, arr, a, 2);
        int[] arr2 = Arrays.copyOf(arr1,14);
    }
}
