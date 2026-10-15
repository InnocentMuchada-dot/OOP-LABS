package lab07;

public class L7_BankThrows {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount(500);
        try {
            acc.withdraw(100);
            System.out.println("Balance: " + acc.getBalance());
            acc.withdraw(5000);                        // caller cannot ignore the failure now
            System.out.println("never reached");
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage() + " (short by " + e.getShortfall() + ")");
        } finally {
            System.out.println("Final balance: " + acc.getBalance());
        }
    }
}
class InsufficientFundsException extends Exception {
    private final double shortfall;
    InsufficientFundsException(String msg, double shortfall) { super(msg); this.shortfall = shortfall; }
    double getShortfall() { return shortfall; }
}
class BankAccount {
    private double balance;
    BankAccount(double b) { balance = b; }
    double getBalance() { return balance; }
    void withdraw(double amt) throws InsufficientFundsException {
        if (amt <= 0) throw new IllegalArgumentException("Withdrawal must be positive");
        if (amt > balance) throw new InsufficientFundsException("Cannot withdraw " + amt + "; balance is " + balance, amt - balance);
        balance -= amt;                                // state changes only after every check has passed
    }
}
