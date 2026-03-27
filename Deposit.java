public class Deposit extends Transaction 
{
public Deposit(String id, double amount){
        super(id, amount);
}
public void execute(Account acc)
{
        double newBalance = acc.getBalance() + amount;
        acc.setBalance(newBalance);
        System.out.println("Transaction " + transactionID + ": Deposited " + amount);
}

}
