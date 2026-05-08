
public class GUIStarter {

public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        WalletManager wallet;

        //load data from the .ser file
        try {
            wallet = fileManager.loadWallet();
        } catch (Exception e) { //TURKI PLEASE (im joking)
            
            wallet = new WalletManager();
        }

        //Dashboard
        DashboardFrame mainFrame = new DashboardFrame(wallet, fileManager);
        mainFrame.setVisible(true);
    }
}