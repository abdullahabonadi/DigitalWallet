public class ServiceFee implements Taxable {
    private String feeName;
    private double amount;
    public ServiceFee(String name, double amt)
    {
        this.feeName = name;
        this.amount = amt;
    }
    
    public double calculateTax()
    {
        return 1.0; 
    }
    public String toString()
    {
        return "abc";
    }
}
