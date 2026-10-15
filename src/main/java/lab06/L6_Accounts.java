package lab06;

public class L6_Accounts {
    public static void main(String[] args) {
        Account[] accounts = { new SavingsAccount("S-1", 1000), new CurrentAccount("C-1", 1000) };
        for (Account a : accounts) {
            a.deposit(500);
            a.applyMonthlyFee();
            System.out.println(a);
        }
    }
}
abstract class Account {
    private final String id; protected double balance;
    Account(String id, double opening) { this.id = id; this.balance = opening; }
    void deposit(double amt) {                        // concrete, shared by every account
        if (amt <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amt;
    }
    abstract double monthlyFee();                     // each type decides its own fee
    void applyMonthlyFee() { balance -= monthlyFee(); }
    @Override public String toString() { return String.format("%s [%s] fee %.2f balance %.2f", getClass().getSimpleName(), id, monthlyFee(), balance); }
}
class SavingsAccount extends Account {
    SavingsAccount(String id, double o) { super(id, o); }
    double monthlyFee() { return 2.00; }
}
class CurrentAccount extends Account {
    CurrentAccount(String id, double o) { super(id, o); }
    double monthlyFee() { return 10.00; }
}
