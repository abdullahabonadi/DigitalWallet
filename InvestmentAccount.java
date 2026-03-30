public class InvestmentAccount extends Account
        //Level 1 Supclass an investment account with risk.
{
private String stockPortfolio;
private int riskLevel;
    public InvestmentAccount(String id, double bal, Date d,String portfolio) {
        super(id, bal, d);
        this.stockPortfolio = portfolio;
        //check risk level
        switch (portfolio){
            case "Saudi Aramco": this.riskLevel = 1; break;
            case "Al Rajhi Bank": this.riskLevel = 2; break;
            case "Amazon": this.riskLevel = 3; break;
            case "Microsoft": this.riskLevel = 4; break;
            case "Tesla": this.riskLevel = 5; break;
        }
    }
    // to change protfolio
public void adjustPortfolio(String newPortfolio)
{
    // changing your stock portfolio will change your risk level
    this.stockPortfolio = newPortfolio;
    switch (newPortfolio){
        case "Saudi Aramco": this.riskLevel = 1; break;
        case "Al Rajhi Bank": this.riskLevel = 2; break;
        case "Amazon": this.riskLevel = 3; break;
        case "Microsoft": this.riskLevel = 4; break;
        case "Tesla": this.riskLevel = 5; break;
    }
}
// to apply and add money through market change
public void applyMarketChange(){
    double change = getBalance() * riskLevel/100;
    setBalance(getBalance() + change);
}
@Override

public String toString()
{
    return super.toString() + " | Portfolio: " + this.stockPortfolio + " | Risk Level: " + riskLevel;
}

}
