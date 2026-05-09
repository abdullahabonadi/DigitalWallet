import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddAccountFrame extends JFrame implements ActionListener {
    private JTextField idField, balanceField, dayField, monthField, yearField;
    private JButton addButton, openSearchButton;
    private WalletManager wallet;
    private FileManager fileManager;

    // NEW COMPONENTS
    private JComboBox<String> typeBox;
    private JComboBox<String> portfolioBox;
    private JLabel portfolioLabel;

    public AddAccountFrame(WalletManager wallet, FileManager fileManager) {
        this.wallet = wallet;
        this.fileManager = fileManager;

        setTitle("Add New Account");
        setSize(400, 400); // Increased size for new components
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2, 5, 5));

        // Account ID
        add(new JLabel(" Account ID:"));
        idField = new JTextField();
        add(idField);

        // Type Selection
        add(new JLabel(" Account Type:"));
        String[] types = {"Savings Account", "Investment Account"};
        typeBox = new JComboBox<>(types);
        add(typeBox);

        //Portfolio Selection
        portfolioLabel = new JLabel(" Portfolio (Investment only):");
        add(portfolioLabel);
        String[] portfolios = {"Saudi Aramco", "Al Rajhi Bank", "Amazon", "Microsoft", "Tesla"};
        portfolioBox = new JComboBox<>(portfolios);
        portfolioBox.setEnabled(false); // Disabled until Investment is chosen
        add(portfolioBox);

        //INitial Balance
        add(new JLabel(" Initial Balance:"));
        balanceField = new JTextField();
        add(balanceField);

        //date
        add(new JLabel(" Date (DD / MM / YYYY):"));
        JPanel datePanel = new JPanel(new FlowLayout());
        dayField = new JTextField(2);
        monthField = new JTextField(2);
        yearField = new JTextField(4);
        datePanel.add(dayField);
        datePanel.add(new JLabel("/"));
        datePanel.add(monthField);
        datePanel.add(new JLabel("/"));
        datePanel.add(yearField);
        add(datePanel);

        //Add Listener to TypeBox to toggle Portfolio selection
        typeBox.addActionListener(this::actionPerformed2);

        // Buttons
        addButton = new JButton("Add Account");
        addButton.addActionListener(this);
        add(addButton);

        openSearchButton = new JButton("Open Search Window");
        openSearchButton.addActionListener(this);
        add(openSearchButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            try {
                String id = idField.getText();
                double balance = Double.parseDouble(balanceField.getText());
                int d = Integer.parseInt(dayField.getText());
                int m = Integer.parseInt(monthField.getText());
                int y = Integer.parseInt(yearField.getText());
                Date openDate = new Date(d, m, y);

                Account newAcc = null;

                // logic for account choosing
                if (typeBox.getSelectedIndex() == 0) { // Savings selected
                    if (balance >= 75000) {
                        int choice = JOptionPane.showConfirmDialog(this,
                                "Balance is 75,000+ SAR. Create High Yield account?",
                                "High Yield Option", JOptionPane.YES_NO_OPTION);

                        if (choice == JOptionPane.YES_OPTION) {
                            newAcc = new HighYieldSavings(id, balance, openDate, 75000);
                        } else {
                            newAcc = new SavingsAccount(id, balance, openDate);
                        }
                    } else {
                        newAcc = new SavingsAccount(id, balance, openDate);
                    }
                } else { // Investment selected
                    String selectedPortfolio = (String) portfolioBox.getSelectedItem();
                    newAcc = new InvestmentAccount(id, balance, openDate, selectedPortfolio);
                }

                // Add to LinkedList and Save using your FileManager
                if (newAcc != null && wallet.addAccount(newAcc)) {
                    fileManager.saveWallet(wallet); //
                    JOptionPane.showMessageDialog(this, "Success! " + id + " added to wallet.");

                    // Clear fields
                    idField.setText(""); balanceField.setText("");
                    dayField.setText(""); monthField.setText(""); yearField.setText("");
                }
            } catch (NumberFormatException ex) {
                //handling unchecked exception
                JOptionPane.showMessageDialog(this, "Error: Enter valid numbers for Balance and Date.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == openSearchButton) {
            new SearchAccountFrame(wallet).setVisible(true);
        }
    }

    private void actionPerformed2(ActionEvent e) {
        boolean isInvestment = typeBox.getSelectedIndex() == 1;
        portfolioBox.setEnabled(isInvestment);
    }
}