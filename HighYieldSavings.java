public class HighYieldSavings extends SavingsAccount implements Taxable
    //Level 2 Subclass for high yielding accounts.
{
    private double minimumBalance;

    public HighYieldSavings(String id, double bal, Date d,double min) {
        super(id, bal, d);
        this.minimumBalance = min;
    }

    @Override
    public double calculateTax()
    {
        if((getBalance() > minimumBalance)){
            setBalance(getBalance() - (getBalance() * 0.05));
            return getBalance();
        }

        else 
            return 0;
    }
    @Override
    public String toString() {
        return super.toString() + " | Min Balance: " + minimumBalance;
    }
}
