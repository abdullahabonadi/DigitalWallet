import java.io.Serializable;

public class Withdrawal extends Transaction implements Serializable {
    // Level 1 Subclass for financial operations

    public Withdrawal(String id, double amount){
        super(id, amount);
    }

    @Override
    public void execute(Account acc) throws InsufficientFundsException {
        // Propagating the exception to be handled in the calling method (GUI)
        if(acc.getBalance() >= amount) {
            acc.setBalance(acc.getBalance() - amount);
            System.out.println("Transaction " + transactionID + ": Withdrew " + amount);
            System.out.println("Your New Balance: " + acc.getBalance());
            this.saveReceipt("Withdrawal", acc.getAccountId(), "Success");
        } else {
            this.saveReceipt("Withdrawal", acc.getAccountId(), "Failed - Insufficient Funds");
            throw new InsufficientFundsException("Transaction " + acc.getAccountId() + ": Failed. Insufficient funds to withdraw " + amount + ".");
        }
    }
}