package homework_14_14_12_safeColl.blockingQueuePizzeria;

import java.util.concurrent.Exchanger;

public class Order {
    private String orderText;
    private Exchanger<Order> exchanger;


    public Order(String orderText) {
        this.orderText = orderText;
        this.exchanger = new Exchanger<>();
    }

    public Exchanger<Order> getExchanger() {
        return exchanger;
    }

    public String getOrderText() {
        return orderText;
    }

    public void setOrderText(String orderText) {
        this.orderText = orderText;
    }
}
