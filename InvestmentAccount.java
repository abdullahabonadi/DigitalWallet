public class InvestmentAccount extends Account
{
private String stockPortfolio;
private int riskLevel;
    public InvestmentAccount(String id, double bal, Date d,String portfolio, int risk) {
        super(id, bal, d);
        this.stockPortfolio = portfolio;
        this.riskLevel = risk;
    }
public void adjustPortfolio(String newPortfolio)
{
    this.stockPortfolio = newPortfolio;
}

public void applyMarketChange(double percent){
    double change = getBalance() * percent;
    setBalance(getBalance() + change);
}
@Override

public String toString()
{
    return super.toString() + " | Portfolio: " + stockPortfolio + " | Risk Level: " + riskLevel;
}

}
