package homework_14_14_12_safeColl.blockingQueuePizzeria;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = Restaurant.getInstance();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter ur order");
        String order = in.nextLine();
        String answer = restaurant.doOrder(order);
        System.out.println(answer);
    }
}


