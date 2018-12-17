package homework_14_14_12_safeColl.blockingQueuePizzeria;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Pizzeria {
    private BlockingQueue<String> newOrders = new LinkedBlockingQueue<>();
    private BlockingQueue<String> ordersToCook = new LinkedBlockingQueue<>();
    private BlockingQueue<String> cookedOrders = new LinkedBlockingQueue<>();

//    public String
}
