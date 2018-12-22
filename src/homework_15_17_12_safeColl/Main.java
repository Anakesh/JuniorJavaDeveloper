package homework_15_17_12_safeColl;

import homework_15_17_12_safeColl.bank.Account;
import homework_15_17_12_safeColl.bank.Bank;
import homework_15_17_12_safeColl.bank.BankException;
import homework_15_17_12_safeColl.bank.User;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws BankException, ExecutionException, InterruptedException {
        Bank bank = new Bank();
        User user1 = bank.createUser("email1");
        Account account1 = bank.createAccount(user1);
        account1.setMoneyAmount(500);
        User user2 = bank.createUser("email2");
        Account account2 = bank.createAccount(user2);
        account2.setMoneyAmount(1000);

        System.out.println(account1);
        System.out.println(account2);
        System.out.println("Transfer 250 from 1 to 2");
        System.out.println(bank.transferMoney(account1,account2,250));
        System.out.println(account1);
        System.out.println(account2);
        System.out.println("Transfer 500 from 1 to 2");
        System.out.println(bank.transferMoney(account1,account2,500));
        System.out.println("Transfer 100 from 1 to 1");
        System.out.println(bank.transferMoney(account1,account1,100));
        System.out.println("Transfer -100 from 1 to 2");
        System.out.println(bank.transferMoney(account1,account2,-100));
    }
}

