public class HighYieldSavings extends SavingsAccount implements Taxable 
{
    private double minimumBalance;

    public HighYieldSavings(String id, double bal, Date d, double rate,double min) {
        super(id, bal, d, rate);
        this.minimumBalance = min;
    }

    @Override
    public double calculateTax()
    {
        if((getBalance() > minimumBalance)){
            return getBalance() * 0.05;
        }

        else 
            return 0;
    }
    @Override
    public String toString() {
        return super.toString() + " | Min Balance: " + minimumBalance;
    }
}
