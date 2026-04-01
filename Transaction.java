public abstract class Transaction
    //Abstract class for financial operations
{
    protected String transactionID;
    protected double amount;
public Transaction(String Id, double amount){
    this.transactionID = Id;
    this.amount = amount;
}
    // Abstract method
    public abstract void execute(Account acc);
}
