package homework_14_14_12_safeColl.blockingQueuePizzeria;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Restaurant restaurant = Restaurant.getInstance();
//        Scanner in = new Scanner(System.in);
//        System.out.println("Enter ur order");
//        String order = in.nextLine();
//        String answer = restaurant.doOrder(order);
//        System.out.println(answer);

        CountDownLatch countDownLatch = new CountDownLatch(10);
        for(int i=0;i<10;i++){
            new Thread(new TestTask(i,restaurant,countDownLatch)).start();
//            Thread.sleep(1);
        }
        countDownLatch.await();
        Thread.sleep(10);
        Restaurant.close();
        new Thread(new TestTask(999,restaurant,countDownLatch)).start();
    }
}



