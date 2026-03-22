public abstract class Account 
//Abstract Superclass.
{
    protected String accountId;
    protected double balance;
    protected Date dateOpened;
    public Account(String id, double bal, Date d){
        this.acountId = id;
        this.balance = bal;
        this.dateOpened = d;
    }

    public double getBalance()
    {
    return balance;
    }
    
    public void setBalance(double bal)
    {
    balance = bal;
    }

    @Override
    public String toString()
    {
        return "abc";
    }
}
