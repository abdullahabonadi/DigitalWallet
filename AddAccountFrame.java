import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddAccountFrame extends JFrame implements ActionListener {
    private JTextField idField, balanceField, dayField, monthField, yearField;
    private JButton addButton, openSearchButton;
    private WalletManager wallet;
    private FileManager fileManager;

    public AddAccountFrame(WalletManager wallet, FileManager fileManager) {
        this.wallet = wallet;
        this.fileManager = fileManager;

        setTitle("Add New Account");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));

        // Creating UI Components
        add(new JLabel(" Account ID:"));
        idField = new JTextField();
        add(idField);

        add(new JLabel(" Initial Balance:"));
        balanceField = new JTextField();
        add(balanceField);

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

        // Buttons
        addButton = new JButton("Add Account");
        addButton.addActionListener(this); // Registering Action Listener
        add(addButton);

        openSearchButton = new JButton("Open Search Window");
        openSearchButton.addActionListener(this);
        add(openSearchButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            try {
                // Read text fields
                String id = idField.getText();
                double balance = Double.parseDouble(balanceField.getText());
                int d = Integer.parseInt(dayField.getText());
                int m = Integer.parseInt(monthField.getText());
                int y = Integer.parseInt(yearField.getText());

                // Create the objects
                Date openDate = new Date(d, m, y);
                // Creating a default Savings Account for simplicity
                SavingsAccount newAcc = new SavingsAccount(id, balance, openDate);

                // Add to LinkedList and Save
                if (wallet.addAccount(newAcc)) {
                    fileManager.saveWallet(wallet);
                    JOptionPane.showMessageDialog(this, "Account Added Successfully!");
                    
                    // Clear fields
                    idField.setText(""); balanceField.setText("");
                    dayField.setText(""); monthField.setText(""); yearField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add account.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                // Handling unchecked exception (Project Requirement)
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for Balance and Date.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == openSearchButton) {
            // Open the second frame
            SearchAccountFrame searchFrame = new SearchAccountFrame(wallet);
            searchFrame.setVisible(true);
        }
    }
}