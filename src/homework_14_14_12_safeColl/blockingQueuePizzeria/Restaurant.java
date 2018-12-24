package homework_14_14_12_safeColl.blockingQueuePizzeria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static Restaurant instance;
    private BlockingQueue<Order> newOrders;
    private BlockingQueue<Order> processedOrders;
    private BlockingQueue<Order> doneOrders;
    private List<Thread> waiterList;
    private List<Thread> cookList;
    private List<Client> clientList;
    private static Boolean closed = true;

    private Restaurant(){
        newOrders = new LinkedBlockingQueue<>();
        processedOrders = new LinkedBlockingQueue<>();
        doneOrders = new LinkedBlockingQueue<>();
        waiterList = new ArrayList<>();
        cookList = new ArrayList<>();
        clientList = Collections.synchronizedList(new ArrayList<>());
        closed = false;
        int numOfThreads = Runtime.getRuntime().availableProcessors();
        int numOfCooks = (numOfThreads%2!=0)?numOfThreads/2+1:numOfThreads/2;
        int numOfWaiters = (numOfCooks==numOfThreads)?1:numOfThreads-numOfCooks;
        numOfCooks = 1;
        numOfWaiters = 1;
        System.out.println("num "+numOfThreads);
        System.out.println("cooks "+numOfCooks);
        System.out.println("waiters "+ numOfWaiters);
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

    public  String doOrder(String stringOrder){
        if(closed)
            return "Cant do order: '"+stringOrder+"' because restaurant is closed";
        Client client = new Client(stringOrder,newOrders,doneOrders);
        clientList.add(client);
        FutureTask<String> clientTask = new FutureTask<>(client);
        Thread clientThread = new Thread(clientTask);
        try {
            clientThread.start();
            String answer = clientTask.get();
            clientList.remove(client);
            synchronized (this) {
                if (closed)
                    this.notifyAll();
            }
            return answer;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            clientList.remove(client);
            return "Error while doing your order";
        }
    }

    public static void close(){
        closed = true;
        while(/*!Restaurant.getInstance().newOrders.isEmpty()
                ||!Restaurant.getInstance().processedOrders.isEmpty()
                ||!Restaurant.getInstance().doneOrders.isEmpty()
                ||*/!Restaurant.getInstance().clientList.isEmpty()){
            try {
                System.out.println("Waiting for orders to complete");
                synchronized (getInstance()){
                    getInstance().wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(Thread cookThread:Restaurant.getInstance().cookList)
            cookThread.interrupt();
        for(Thread waiterThread:Restaurant.getInstance().waiterList)
            waiterThread.interrupt();
        System.out.println("Restaurant closed");
    }
}
