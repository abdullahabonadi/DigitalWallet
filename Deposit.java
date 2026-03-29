public class Deposit extends Transaction
    //Level 1 Supclass for financial operations
{
public Deposit(String id, double amount){
        super(id, amount);
}
    //logic for depositing funds with a balance checking
public void execute(Account acc)
{
        double newBalance = acc.getBalance() + amount;
        acc.setBalance(newBalance);
        System.out.println("Transaction " + transactionID + ": Deposited " + amount);
}

}
