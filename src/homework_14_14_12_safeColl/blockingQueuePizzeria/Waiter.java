package homework_14_14_12_safeColl.blockingQueuePizzeria;

import java.util.concurrent.BlockingQueue;

public class Waiter extends Thread {
    private BlockingQueue<Order> newOrders;
    private BlockingQueue<Order> processedOrders;

    public Waiter(BlockingQueue<Order> newOrders, BlockingQueue<Order> processedOrders) {
        this.newOrders = newOrders;
        this.processedOrders = processedOrders;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            try {
                processedOrders.put(newOrders.take());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }

    }
}
