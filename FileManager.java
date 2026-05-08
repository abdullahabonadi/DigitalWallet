import java.io.*;
public class FileManager {
    private String fileName = "wallet_data.ser";

    // Passing WalletManager
    public void saveWallet(WalletManager w) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(w); // This saves  manager and the  chain of accounts
            oos.close();
            System.out.println("Wallet data saved successfully.");
        } catch (IOException e) {

            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public WalletManager loadWallet() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        WalletManager wm = (WalletManager) ois.readObject();
        ois.close();
        return wm;
    }
}
