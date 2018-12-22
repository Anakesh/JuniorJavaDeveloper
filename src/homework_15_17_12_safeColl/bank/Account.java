package homework_15_17_12_safeColl.bank;

public class Account implements Comparable<Account> {
    private int id;
    private int userId;
    private int moneyAmount;

    public Account(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userId=" + userId +
                ", moneyAmount=" + moneyAmount +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    @Override
    public int compareTo(Account o) {
        return this.id - o.id;
    }
}
