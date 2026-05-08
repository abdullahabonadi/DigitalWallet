import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchAccountFrame extends JFrame implements ActionListener {
    private JTextField searchIdField;
    private JButton findButton;
    private JTextArea resultArea;
    private WalletManager wallet;

    public SearchAccountFrame(WalletManager wallet) {
        this.wallet = wallet;

        setTitle("Find Account");
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Top Panel for Input
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Enter Account ID:"));
        searchIdField = new JTextField(10);
        topPanel.add(searchIdField);
        
        findButton = new JButton("Find");
        findButton.addActionListener(this);
        topPanel.add(findButton);

        add(topPanel, BorderLayout.NORTH);

        // Center Area for Results
        resultArea = new JTextArea();
        resultArea.setEditable(false); // Make it read-only
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == findButton) {
            String searchId = searchIdField.getText();
            
            
            Account found = wallet.searchAccount(searchId, wallet.getHead());

            if (found != null) {
                resultArea.setText("Account Found:\n\n" + found.toString());
            } else {
                resultArea.setText("Error: No account matches ID #" + searchId);
            }
        }
    }
}