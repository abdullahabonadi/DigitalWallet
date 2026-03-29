public abstract class Account 
//Abstract Superclass for all wallet types.
{
    protected String accountId;
    protected double balance;
    protected Date dateOpened;
    public Account(String id, double bal, Date d){
        this.accountId = id;
        this.balance = bal;
        this.dateOpened = d;
    }
//setters and getters
    public double getBalance()
    {
    return balance;
    }
    public void setBalance(double bal)
    {
    balance = bal;
    }
    public String getAccountId() {
        return accountId;
    }


    @Override
    public String toString()
    {
        return "Account ID: " + this.accountId + " | Balance: " + getBalance()+ " | Opened: " + dateOpened;
    }
}
