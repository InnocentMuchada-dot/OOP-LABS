package lab03;

import java.util.*;
public class L3_BankHistory {
    public static void main(String[] args) {
        Account acc = new Account("ACC-001", 500);
        acc.deposit(250);
        acc.withdraw(100);
        acc.withdraw(5000);
        List<String> h = acc.getHistory();
        h.forEach(System.out::println);
        try {
            h.clear();                     // tampering attempt
        } catch (UnsupportedOperationException e) {
            System.out.println("History is read-only");
        }
        System.out.println("Entries still stored: " + acc.getHistory().size());
    }
}
class Account {
    private final String number;
    private double balance;
    private final List<String> history = new ArrayList<>();
    Account(String number, double opening) {
        this.number = number; this.balance = opening;
        history.add("OPEN " + opening);
    }
    void deposit(double amt) {
        if (amt <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amt; history.add("DEPOSIT " + amt);
    }
    boolean withdraw(double amt) {
        if (amt <= 0) throw new IllegalArgumentException("Withdrawal must be positive");
        if (amt > balance) { history.add("DECLINED " + amt); return false; }
        balance -= amt; history.add("WITHDRAW " + amt); return true;
    }
    // Returns an unmodifiable COPY: later changes to the account do not alter a list already handed out
    List<String> getHistory() { return List.copyOf(history); }
}
