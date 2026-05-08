import java.io.Serializable;

public class Withdrawal extends Transaction implements Serializable {
    // Level 1 Subclass for financial operations

    public Withdrawal(String id, double amount){
        super(id, amount);
    }

    public void execute(Account acc) {
        try {
            if(acc.getBalance() >= amount) {
                acc.setBalance(acc.getBalance() - amount);
                System.out.println("Transaction " + transactionID + ": Withdrew " + amount);
                System.out.println("Your New Balance: " + acc.getBalance());
                this.saveReceipt("Withdrawal", acc.getAccountId(), "Success");
            } else {
                throw new InsufficientFundsException("Transaction " + transactionID + ": Failed. Insufficient funds to withdraw " + amount + ".");
            }
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
            this.saveReceipt("Withdrawal", acc.getAccountId(), "Failed - Insufficient Funds");
        }

    }
}