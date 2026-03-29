public class InvestmentAccount extends Account
        //Level 1 Supclass an investment account with risk.
{
private String stockPortfolio;
private int riskLevel;
    public InvestmentAccount(String id, double bal, Date d,String portfolio, int risk) {
        super(id, bal, d);
        this.stockPortfolio = portfolio;
        //check risk level
        if(risk <0 || risk >5)
            this.riskLevel = 5;
        else
            this.riskLevel = risk;
    }
    // to change protfolio
public void adjustPortfolio(String newPortfolio)
{
    this.stockPortfolio = newPortfolio;
}
// to apply and add money through market change
public void applyMarketChange(){
    double change = getBalance() * riskLevel/10;
    setBalance(getBalance() + change);
}
@Override

public String toString()
{
    return super.toString() + " | Portfolio: " + this.stockPortfolio + " | Risk Level: " + riskLevel;
}

}
