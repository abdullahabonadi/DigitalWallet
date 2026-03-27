public class Withdrawal extends Transaction 
{
    public Withdrawal(String id, double amount){
        super(id, amount);
    }

    public void execute(Account acc)
    {
        if(acc.getBalance() >= amount)
        {
            acc.setBalance(acc.getBalance() - amount);
            System.out.println("Transaction " + transactionID + ": Withdrew " + amount);
        }
        else 
            System.out.println("Transaction " + transactionID + ": Failed. Insufficient funds.");
    }
}
