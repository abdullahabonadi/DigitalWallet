import java.io.Serializable;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Transaction implements Serializable {
    // Abstract class for financial operations
    protected String transactionID;
    protected double amount;

    public Transaction(String Id, double amount){
        this.transactionID = Id;
        this.amount = amount;
    }

    // Abstract method
    public abstract void execute(Account acc);

    //  Saves a text file receipt and handles the checked IOException
    public void saveReceipt(String type, String accountId, String status) {
        // The try-catch block immediately handles the checked IOException
        try {
            // "true" means it will append to the file instead of overwriting it
            FileWriter fileWriter = new FileWriter("Transaction_Receipts.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("ID: " + transactionID + " | Account: " + accountId + " | Type: " + type + " | Amount: " + amount + " | Status: " + status);

            printWriter.close();
        } catch (IOException e) {
            // Handled  where it occurs
            System.out.println("Error saving receipt to file: " + e.getMessage());
        }
    }
}