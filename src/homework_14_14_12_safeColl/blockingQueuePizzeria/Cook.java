package homework_14_14_12_safeColl.blockingQueuePizzeria;

import java.util.concurrent.BlockingQueue;

public class Cook extends Thread{
    private BlockingQueue<Order> processedOrders;
    private BlockingQueue<Order> doneOrders;

    public Cook(BlockingQueue<Order> processedOrders, BlockingQueue<Order> doneOrders) {
        this.processedOrders = processedOrders;
        this.doneOrders = doneOrders;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            try {
                Order order = processedOrders.take();
                order.setOrderText("Your order: '"+order.getOrderText()+"' is done");

                doneOrders.put(order);
                order.notify();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }

    }
}
