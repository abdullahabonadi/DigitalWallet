import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public abstract class Transaction implements Serializable {
    protected String transactionID;
    protected double amount;

    public Transaction(String Id, double amount){
        this.transactionID = Id;
        this.amount = amount;
    }

    public abstract void execute(Account acc) throws Exception;

    public void saveReceipt(String type, String accountId, String status) {
        try {
            FileWriter fileWriter = new FileWriter("Transaction_Receipts.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("ID: " + transactionID + " | Account: " + accountId + " | Type: " + type + " | Amount: " + amount + " | Status: " + status);
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }
}