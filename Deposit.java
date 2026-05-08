import java.io.Serializable;

public class Deposit extends Transaction implements Serializable {

    public Deposit(String id, double amount) {
        super(id, amount);
    }

    @Override
    public void execute(Account acc) throws IllegalArgumentException {
        // Validation check for negative or zero deposits
        if (amount <= 0) {
            throw new IllegalArgumentException("Transaction " + transactionID + ": Failed. Deposit amount must be greater than zero.");
        }

        // If the amount is valid, proceed with the deposit
        double newBalance = acc.getBalance() + amount;
        acc.setBalance(newBalance);
        System.out.println("Transaction " + transactionID + ": Deposited " + amount);
    }
}