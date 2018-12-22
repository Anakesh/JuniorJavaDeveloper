package homework_15_17_12_safeColl.bank;

import java.util.concurrent.Callable;

public class Transaction implements Callable<String> {
    private Account source;
    private Account destination;
    private int amount;

    public Transaction(Account source, Account destination, int amount) {

        this.source = source;
        this.destination = destination;
        this.amount = amount;
    }

    @Override
    public String call() throws Exception {

        synchronized (source){
            synchronized (destination){
                source.setMoneyAmount(source.getMoneyAmount()-amount);
                destination.setMoneyAmount(destination.getMoneyAmount()+amount);
                return "Transfer succeeded.";
            }
        }

    }
}