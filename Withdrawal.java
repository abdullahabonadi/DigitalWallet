public class Withdrawal extends Transaction
        //Level 1 Supclass for financial operations
{
    public Withdrawal(String id, double amount){
        super(id, amount);
    }
    //logic for withdrawing funds with a balance checking
    public void execute(Account acc)
    {
        if(acc.getBalance() >= amount)
        {
            acc.setBalance(acc.getBalance() - amount);
            System.out.println("Transaction " + transactionID + ": Withdrew " + amount);
            System.out.println("Your New Balance: " + acc.getBalance());
        }
        else 
            System.out.println("Transaction " + transactionID + ": Failed. Insufficient funds.");
    }
}
