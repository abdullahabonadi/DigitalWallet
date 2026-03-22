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
        return 1.0;
    }
}
