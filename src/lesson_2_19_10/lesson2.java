package lesson_2_19_10;

import java.util.Scanner;

public class lesson2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите код. Трехзначное число");
        int code = in.nextInt();
        if(code==123){
            System.out.println("Вы вошли как администратор");
        }
        int ticket = in.nextInt();
        switch(ticket){
            case 33333:
                System.out.println("Большой");
            case 33355:
            case 3452345:
                System.out.println("Маленький");
                break;
        }
        int x = 5;
        int y = 5;
        System.out.println((x<y)?"x<y":((x>y)?"x>y":"x=y"));
        outer: for(int i =1;i<100;i++){
            for(int j=1;j<5;j++)
                if(j==2)
                    break outer;
        }
    }
}
