import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DashboardFrame extends JFrame implements ActionListener {
    private WalletManager wallet;
    private FileManager fileManager;
    private JButton btnAdd, btnRemove, btnAction, btnSearch, btnDisplay, btnExit;

    public DashboardFrame(WalletManager wallet, FileManager fileManager) {
        this.wallet = wallet;
        this.fileManager = fileManager;

        setTitle("Digital Wallet Dashboard");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        setLayout(new GridLayout(7, 1, 10, 10));

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                fileManager.saveWallet(wallet);
                JOptionPane.showMessageDialog(DashboardFrame.this, "Data Saved Automatically. Goodbye!");
                System.exit(0);
            }
        });

        JLabel titleLabel = new JLabel(" Wallet Main Menu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        add(titleLabel);

        btnAdd = new JButton("1. Add a new Account");
        btnRemove = new JButton("2. Remove an Account");
        btnSearch = new JButton("4. Search for an Account");
        btnDisplay = new JButton("5. Display All Accounts");
        btnExit = new JButton("6. Save Data and Exit");

        Font comicFont = new Font("Comic Sans MS", Font.BOLD, 14); 
        btnAdd.setFont(comicFont);
        btnRemove.setFont(comicFont);
        btnSearch.setFont(comicFont);
        btnDisplay.setFont(comicFont);
        btnExit.setFont(comicFont);

        btnAction = new JButton("3. Make a Transaction");
        btnAction.setFont(comicFont);

        Timer rainbowTimer = new Timer(100, new ActionListener() {
            int offset = 0; 
            String text = "3. Make a Transaction (Deposit/Withdraw)";
            String[] colors = {"red", "orange", "#CCCC00", "green", "blue", "purple", "magenta"};

            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder("<html>");
                int colorIndex = offset;
                for (char c : text.toCharArray()) {
                    if (c == ' ') sb.append(" ");
                    else {
                        sb.append("<font color='").append(colors[colorIndex % colors.length]).append("'>").append(c).append("</font>");
                        colorIndex++;
                    }
                }
                sb.append("</html>");
                btnAction.setText(sb.toString());
                offset--;
                if (offset < 0) offset = colors.length - 1;
            }
        });
        rainbowTimer.start(); 

        btnAdd.addActionListener(this);
        btnRemove.addActionListener(this);
        btnAction.addActionListener(this);
        btnSearch.addActionListener(this);
        btnDisplay.addActionListener(this);
        btnExit.addActionListener(this);

        add(btnAdd); add(btnRemove); add(btnAction); 
        add(btnSearch); add(btnDisplay); add(btnExit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            new AddAccountFrame(wallet, fileManager).setVisible(true);
        } else if (e.getSource() == btnSearch) {
            new SearchAccountFrame(wallet).setVisible(true);
        } else if (e.getSource() == btnRemove) {
            String id = JOptionPane.showInputDialog(this, "Enter Account ID to Remove:");
            if (id != null && wallet.removeAccount(id)) {
                fileManager.saveWallet(wallet); // SAVE AFTER REMOVAL
                JOptionPane.showMessageDialog(this, "Account removed successfully!");
            } else if (id != null) {
                JOptionPane.showMessageDialog(this, "Account not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else if (e.getSource() == btnAction) {
            String id = JOptionPane.showInputDialog(this, "Enter Account ID for Transaction:");
            Account sourceAcc = wallet.searchAccount(id, wallet.getHead()); // Uses your recursive search [cite: 23]

            if(sourceAcc != null) {
                String[] options = {"Deposit", "Withdraw", "Transfer"};
                int choice = JOptionPane.showOptionDialog(this, "Choose Action", "Transaction", 0, 3, null, options, options[0]);

                if(choice >= 0 && choice <= 2) {
                    String amountStr = JOptionPane.showInputDialog(this, "Enter Amount:");
                    if (amountStr != null) {
                        try {
                            double amount = Double.parseDouble(amountStr);
                            if (choice == 0) { //Deposit
                                new Deposit("D" + System.currentTimeMillis(), amount).execute(sourceAcc);
                                JOptionPane.showMessageDialog(this, "Deposit Successful!");
                            }
                            else if (choice == 1) { //withdraw
                                try {
                                    new Withdrawal("W" + System.currentTimeMillis(), amount).execute(sourceAcc);
                                    JOptionPane.showMessageDialog(this, "Withdrawal Successful!");
                                } catch (InsufficientFundsException ex) { // Your custom exception [cite: 18]
                                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Declined", 0);
                                }
                            }
                            else if (choice == 2) { //Transfer
                                String targetId = JOptionPane.showInputDialog(this, "Enter Target Account ID:");
                                Account targetAcc = wallet.searchAccount(targetId, wallet.getHead());

                                if (targetAcc != null && !targetId.equals(id)) {
                                    try {
                                        //Withdraw from source, Deposit to target
                                        new Withdrawal("TW" + System.currentTimeMillis(), amount).execute(sourceAcc);
                                        new Deposit("TD" + System.currentTimeMillis(), amount).execute(targetAcc);

                                        //Handling Service Fees for transfers
                                        ServiceFee fee = new ServiceFee("Transfer Fee", amount);
                                        double tax = fee.calculateTax();
                                        sourceAcc.setBalance(sourceAcc.getBalance() - tax);

                                        JOptionPane.showMessageDialog(this, "Transfer Successful!\nFee applied: " + tax);
                                    } catch (InsufficientFundsException ex) {
                                        JOptionPane.showMessageDialog(this, "Transfer Failed: " + ex.getMessage(), "Error", 0);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(this, "Invalid Target Account.", "Error", 0);
                                }
                            }
                            fileManager.saveWallet(wallet); //Save all changes to the .ser file
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "Invalid amount.", "Error", 0);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                        }
                    }
                }
            } else if(id != null) {
                JOptionPane.showMessageDialog(this, "Source account not found.", "Error", 0);
            }
        } else if (e.getSource() == btnDisplay) {

            if (wallet.getHead() == null) {
                JOptionPane.showMessageDialog(this, "The wallet is currently empty.");
                return;
            }
            StringBuilder sb = new StringBuilder("--- Wallet Accounts ---\n\n");
            Account curr = wallet.getHead();
            while (curr != null) {
                sb.append(curr.toString()).append("\n\n");
                curr = curr.next;
            }
            JOptionPane.showMessageDialog(this, new JScrollPane(new JTextArea(sb.toString(), 15, 30)));
        } else if (e.getSource() == btnExit) {
            fileManager.saveWallet(wallet);
            System.exit(0);
        }
    }
}