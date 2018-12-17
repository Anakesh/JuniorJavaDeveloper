package homework_13_10_12_threadSync.restaurant;



public class Restaurant{
    private Kitchen kitchen;
    public Restaurant(){
        this.kitchen = new Kitchen();
        Thread waiter = new Thread(new Waiter(kitchen));
        Thread cook = new Thread(new Cook(kitchen));
        waiter.run();
        cook.run();
    }
    public String serveClient(String order) {
        try {
            return kitchen.serveClient(order);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "Error occurred while handling the order.";
        }
    }
}
class Kitchen {
    private boolean clientNeedsHelp;
    private boolean orderNeedsTobeCooked;
    private boolean orderIsCooked;
    private boolean orderIsDone;
    private boolean waiterIsActive;
    private boolean cookIsActive;
    private boolean clientIsActive;
    private String currentOrder;

    public Kitchen() {
    }

    public synchronized String serveClient(String order) throws InterruptedException {
        currentOrder = order;
        clientNeedsHelp = true;
        while (!waiterIsActive) {
            notifyAll();
        }
        clientNeedsHelp = false;
        while (!orderIsDone) {
            wait();
        }
        clientIsActive = true;
        orderIsDone = false;
        clientIsActive = false;
        return currentOrder;

    }

    synchronized void doOrder() throws InterruptedException {
        while (!clientNeedsHelp) {
            wait();
        }
        waiterIsActive = true;
        System.out.println("Waiter accepted the order.");
        orderNeedsTobeCooked = true;
        while (!cookIsActive) {
            notifyAll();
        }
        waiterIsActive = false;
        while (!orderIsCooked) {
            wait();
        }
        waiterIsActive = true;
        orderIsDone = true;
        while (clientIsActive) {
            notifyAll();
        }
        waiterIsActive = false;
    }

    synchronized void cookOrder() throws InterruptedException {
        while (!orderNeedsTobeCooked) {
            wait();
        }
        cookIsActive = true;
        currentOrder = "Your order: '" + currentOrder + "' is done.";
        orderNeedsTobeCooked = false;
        orderIsCooked = true;
        while (!waiterIsActive) {
            notifyAll();
        }
        orderIsCooked = false;
        cookIsActive = false;
    }
}


class Waiter implements Runnable {
    private Kitchen kitchen;

    Waiter(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                kitchen.doOrder();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}

class Cook implements Runnable {
    private Kitchen kitchen;

    Cook(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                kitchen.cookOrder();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}
