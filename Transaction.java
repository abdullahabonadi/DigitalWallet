public abstract class Transaction
    //Abstract class for financial operations
{
    protected String transactionID;
    protected double amount;
public Transaction(String transactionID, double amount){
    this.transactionID = transactionID;
    this.amount = amount;
}
    // Abstract method
    public abstract void execute(Account acc);
}
