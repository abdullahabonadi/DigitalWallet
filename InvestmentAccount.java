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

}
@Override

public String toString()
{
    return "abc";
}

}
