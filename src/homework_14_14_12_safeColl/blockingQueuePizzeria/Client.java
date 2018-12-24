package homework_14_14_12_safeColl.blockingQueuePizzeria;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class Client implements Callable<String>,Comparable<Client> {
    private Order order;
    private BlockingQueue<Order> newOrders;
    private BlockingQueue<Order> doneOrders;

    public Client(String strOrder, BlockingQueue<Order> newOrders, BlockingQueue<Order> doneOrders) {
        this.order = new Order(strOrder);
        this.newOrders = newOrders;
        this.doneOrders = doneOrders;
    }

    @Override
    public String call() throws Exception {
        newOrders.put(order);
        order.getExchanger().exchange(order);
        return doneOrders.take().getOrderText();

    }

    @Override
    public int compareTo(Client o) {
        return this.order.equals(o.order)?0:1;
    }
}
