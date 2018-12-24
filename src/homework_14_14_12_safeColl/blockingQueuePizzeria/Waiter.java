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
                Order order = newOrders.take();
                order.setOrderText(order.getOrderText()/*+" "+Thread.currentThread().getName()*/);
                processedOrders.put(order);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Waiter interrupted "+Thread.currentThread().getName());
//                e.printStackTrace();
            }
        }

    }
}
