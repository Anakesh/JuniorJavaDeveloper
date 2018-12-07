package homework_02_22_10;

import java.util.Scanner;

public class task9_22_10 {
    public static void main(String[] args) {
        Scanner var = new Scanner(System.in);
        System.out.println("Введите количество тарелок:");
        float plates = var.nextFloat();
        System.out.println("Введите количество моющего средства");
        int soap = var.nextInt();
        while(soap>0 && plates>0){
            soap-=0.5;
            plates-=1;
            System.out.println("Осталось "+soap+" моющего средства и "+plates+" тарелок.");
        }
    }
}
