package homework_14_14_12_safeColl.blockingQueuePizzeria;

import java.util.concurrent.CountDownLatch;

public class TestTask implements Runnable {
    private int num;
    private Restaurant restaurant;
    private CountDownLatch countDownLatch;

    public TestTask(int num, Restaurant restaurant, CountDownLatch countDownLatch) {
        this.num = num;
        this.restaurant = restaurant;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        String currentOrder = "Order number:"+(num+1);
        System.out.println(currentOrder);
        countDownLatch.countDown();
        try {
            countDownLatch.await();
            System.out.println(restaurant.doOrder(currentOrder));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
