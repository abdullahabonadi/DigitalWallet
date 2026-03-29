public class SavingsAccount extends Account 
{
    private double interestRate;
    public SavingsAccount(String id, double bal, Date d) {
        super(id, bal, d);
        //calculatign interest rate
        if (bal < 10000) {
            this.interestRate = 0.01; // 1%
        } else if (bal < 50000) {
            this.interestRate = 0.025; // 2.5%
        } else {
            this.interestRate = 0.05; // 5%
        }

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
