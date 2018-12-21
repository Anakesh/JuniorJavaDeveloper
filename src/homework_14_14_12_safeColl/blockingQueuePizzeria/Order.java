package homework_14_14_12_safeColl.blockingQueuePizzeria;

public class Order {
    private String orderText;

    public Order(String orderText) {
        this.orderText = orderText;
    }

    public String getOrderText() {
        return orderText;
    }

    public void setOrderText(String orderText) {
        this.orderText = orderText;
    }
}
