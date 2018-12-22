package homework_15_17_12_safeColl.bank;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Bank {
    ConcurrentSkipListSet<Account> accounts = new ConcurrentSkipListSet<>();
    ConcurrentSkipListSet<User> users = new ConcurrentSkipListSet<>();

    public User createUser(String email) throws BankException {
        if(users.stream().noneMatch(user -> user.getEmail().equals(email))){
            User user = new User(users.isEmpty()? 0:users.last().getId()+1,email);
            users.add(user);
            return user;
        }
        else
            throw new BankException("There are already an user with this email");
    }

    public Account createAccount(User user){
        Account account = new Account(accounts.isEmpty()?0:accounts.last().getId()+1, user.getId());
        accounts.add(account);
        return account;
    }

    public String transferMoney(Account source, Account destination, int amount) throws ExecutionException, InterruptedException {
        if(amount<=0)
            return "Transfer failed. Invalid amount";
        if(source.equals(destination))
            return "Transfer failed. You cant transfer money to the same account";
        if(source.getMoneyAmount()<amount)
            return "Transfer failed. Source account doesn't have enough money.";
        FutureTask<String> task = new FutureTask<>(new Transaction(source,destination,amount));
        new Thread(task).start();
        return task.get();
    }
}
