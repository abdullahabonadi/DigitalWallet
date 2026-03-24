public class SavingsAccount extends Account 
{
    private double interestRate;
    public SavingsAccount(String id, double bal, Date d,double rate) {
        super(id, bal, d);
        this.interestRate = rate;
    }
    public void applyInterest()
    {
        double interest = getBalance() * this.interestRate;
        setBalance(getBalance() + interest);
    }
    @Override
    public String toString(){
        return super.toString() + " | Interest Rate: " + (this.interestRate * 100) + "%";
    }
}
