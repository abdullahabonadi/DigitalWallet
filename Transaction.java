public abstract class Transaction 
{
    protected String transactionID;
    protected double amount;
public Transaction(String transactionID, double amount){
    this.transactionID = transactionID;
    this.amount = amount;
}

    public abstract void execute(Account acc);
}
