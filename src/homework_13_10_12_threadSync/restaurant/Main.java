package homework_13_10_12_threadSync.restaurant;

import java.util.Scanner;

/**
 * Created by Pavel on 16.12.2018.
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Restaurant restaurant = new Restaurant();
        System.out.println("Enter ur order");
        String order = in.nextLine();
        System.out.println(restaurant.serveClient(order));
    }
}
