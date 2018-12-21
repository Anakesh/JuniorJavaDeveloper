package homework_14_14_12_safeColl.blockingQueuePizzeria;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static Restaurant instance;
    private BlockingQueue<Order> newOrders;
    private BlockingQueue<Order> processedOrders;
    private BlockingQueue<Order> doneOrders;
    private List<Thread> waiterList;
    private List<Thread> cookList;
    private static boolean closed = true;

    private Restaurant(){
        newOrders = new LinkedBlockingQueue<>();
        processedOrders = new LinkedBlockingQueue<>();
        doneOrders = new LinkedBlockingQueue<>();
        waiterList = new ArrayList<>();
        cookList = new ArrayList<>();
        closed = false;
        int numOfThreads = Runtime.getRuntime().availableProcessors();
        int numOfCooks = (numOfThreads%2!=0)?numOfThreads/2+1:numOfThreads/2;
        int numOfWaiters = (numOfCooks==numOfThreads)?1:numOfThreads-numOfCooks;
        for(int i = 0;i<numOfCooks;i++){
            Cook cook = new Cook(processedOrders,doneOrders);
            Thread cookThread = new Thread(cook);
            cookThread.start();
            cookList.add(cookThread);
        }
        for(int i = 0;i<numOfWaiters;i++) {
            Waiter waiter = new Waiter(newOrders, processedOrders);
            Thread waiterThread = new Thread(waiter);
            waiterThread.start();
            waiterList.add(waiterThread);
        }
    }

    public static Restaurant getInstance(){
        if(instance==null)
            instance = new Restaurant();
        return instance;
    }

    public String doOrder(String stringOrder){
        if(closed)
            return "Restaurant is closed";
        Order order = new Order(stringOrder);
        try {
            newOrders.put(order);
            while(doneOrders.peek()!=order){
                order.wait();
            }
            doneOrders.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return order.getOrderText();
    }

    public static void close(){
        closed = true;
        while(!Restaurant.getInstance().newOrders.isEmpty()
                &&!Restaurant.getInstance().processedOrders.isEmpty()
                &&!Restaurant.getInstance().doneOrders.isEmpty());
        for(Thread cookThread:Restaurant.getInstance().cookList)
            cookThread.interrupt();
        for(Thread waiterThread:Restaurant.getInstance().waiterList)
            waiterThread.interrupt();
    }
}
