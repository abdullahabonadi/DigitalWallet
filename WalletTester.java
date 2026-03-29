import java.util.Scanner;

public class WalletTester {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WalletManager wallet = new WalletManager(100); // Set wallet capacity to 100
        int choice;

        do {
            System.out.println("\n=== Digital Wallet Management System ===");
            System.out.println("1. Add a new Account");
            System.out.println("2. Remove an Account");
            System.out.println("3. Choose an Account for transactions");
            System.out.println("4. make actions on Account");
            System.out.println("5. Search for an Account");
            System.out.println("6. Display All Accounts");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()





            switch (choice) {
                case 1:
                    System.out.print("Enter Account type\n1.Savings Account\n2.Investment Account\nChoose:");
                    int type = scanner.nextInt();
                    scanner.nextLine();
                    switch (type){
                        //case 1 creates a SavingsAccount account
                        case 1:{
                            System.out.print("Enter Account ID: ");
                            String id = scanner.nextLine();
                            System.out.print("Enter Initial Balance: ");
                            double bal = scanner.nextDouble();
                            // asks user if they want to make a high yield savings account
                            if(bal >= 75000){
                                System.out.println("you make more than 75000 do you want to make a High Yield Savings Account?\n1. yes\n2. no");
                                int highYieldChoice = scanner.nextInt();
                                scanner.nextLine();
                                if(highYieldChoice == 1){
                                    //same day as the deadLine
                                    Date today = new Date(2, 4, 2026);
                                    HighYieldSavings newAcc = new HighYieldSavings(id, bal, today, 75000);
                                    if (wallet.addAccount(newAcc)) {
                                        System.out.println("Success! Account " + id + " has been added.");
                                    } else {
                                        System.out.println("Error: Wallet capacity is full.");
                                    }
                                } else if (highYieldChoice == 2) {
                                    //same day as the deadLine
                                    Date today = new Date(2, 4, 2026);
                                    SavingsAccount newAcc = new SavingsAccount(id, bal, today);
                                    if (wallet.addAccount(newAcc)) {
                                        System.out.println("Success! Account " + id + " has been added.");
                                    } else {
                                        System.out.println("Error: Wallet capacity is full.");
                                    }
                                }else
                                    System.out.println("Invalid input.");
                            }

                            break;
                        }
                        //case 2 creates an Investment account
                        case 2:{
                            System.out.print("Enter Account ID: ");
                            String id = scanner.nextLine();
                            System.out.print("Enter Initial Balance: ");
                            double bal = scanner.nextDouble();
                            System.out.print("Enter Stock Portfolio Description: ");
                            String portfolio = scanner.nextLine();
                            System.out.print("Enter Risk Level (1-5): ");
                            int risk = scanner.nextInt();
                            //same day as the deadLine
                            Date today = new Date(2, 4, 2026);
                            InvestmentAccount newAcc = new InvestmentAccount(id, bal, today, portfolio, risk);
                            if (wallet.addAccount(newAcc)) {
                                System.out.println("Success! Account " + id + " has been added.");
                            } else {
                                System.out.println("Error: Wallet capacity is full.");
                            }
                            break;
                        }

                        default:
                            System.out.println("Invalid input.");
                            break;
                    }
                    break;






                //removing an account
                case 2:
                    System.out.print("Enter the Account ID you want to remove: ");
                    String removeId = scanner.nextLine();
                    if (wallet.removeAccount(removeId)) {
                        System.out.println("Account " + removeId + " was successfully removed.");
                    } else {
                        System.out.println("Error: Account not found.");
                    }
                    break;


                //transactions
                case 3:
                    wallet.displayIDsOnly();
                    System.out.print("Enter the account Id to transact:");
                    String id = scanner.nextLine();
                    Account accountToTransact = wallet.searchAccount(id, 0);
                    if(accountToTransact == null){
                        System.out.println("No account has that Id!");
                    }
                    else {
                        System.out.println("Enter type of transaction\n1. Deposit\n2. Withdrawal\n3. transfer");
                        int typeOfTransaction = scanner.nextInt();
                        scanner.nextLine();

                        switch (typeOfTransaction){
                            //deposit
                            case 1:
                                System.out.print("Enter the amount to deposit:");
                                int amountToDeposit = scanner.nextInt();
                                scanner.nextLine();
                                Deposit depositTransaction = new Deposit(accountToTransact.getAccountId(), amountToDeposit);
                                depositTransaction.execute(accountToTransact);
                                System.out.println("Your New Balance: " + accountToTransact.getBalance());
                                break;

                            //withdrawal
                            case 2:
                                System.out.print("Enter the amount to withdrawal:");
                                int amountToWithdrawal = scanner.nextInt();
                                if(amountToWithdrawal <= accountToTransact.getBalance()) {
                                    scanner.nextLine();
                                    Withdrawal withdrawalTransaction = new Withdrawal(accountToTransact.getAccountId(), amountToWithdrawal);
                                    withdrawalTransaction.execute(accountToTransact);
                                    System.out.println("Your New Balance: " + accountToTransact.getBalance());
                                }
                                else
                                    System.out.println("Sorry! not enough funds for this transaction");
                                break;

                            //transfer
                            case 3:
                                System.out.print("enter Account Id you want to transfer to: ");
                                String transferId = scanner.nextLine();
                                System.out.print("Enter the amount to transfer:");
                                int amountToTransfer = scanner.nextInt();
                                scanner.nextLine();
                                if(amountToTransfer <= accountToTransact.getBalance()) {
                                    Account transferTo = wallet.searchAccount(transferId, 0);
                                    if(transferTo == null){
                                        System.out.println("No account has that Id!");
                                    }
                                    else {
                                        //transfer from
                                        Withdrawal withdrawalTransfer = new Withdrawal(accountToTransact.getAccountId(), amountToTransfer);
                                        withdrawalTransfer.execute(accountToTransact);

                                        //transfer to
                                        Deposit depositTransfer = new Deposit(transferTo.getAccountId(), amountToTransfer);
                                        depositTransfer.execute(transferTo);

                                        //applying service fees
                                        ServiceFee serviceFee = new ServiceFee("Transfer",amountToTransfer );
                                        System.out.println("service fees: " + serviceFee.calculateTax());
                                        if(serviceFee.calculateTax() > accountToTransact.getBalance()){
                                            accountToTransact.setBalance(0);
                                        }else
                                            accountToTransact.setBalance(accountToTransact.getBalance() - serviceFee.calculateTax());
                                    }
                                }
                                else
                                    System.out.println("Sorry! not enough funds for this transaction");
                                break;

                            default:
                                System.out.println("Invalid input.");
                                break;

                        }

                    }
                    break;

                case 4:
                    System.out.println("Enter the Account ID to make an action:");
                    String actionId = scanner.nextLine();
                    Account accountToAction = wallet.searchAccount(actionId, 0);
                    if (accountToAction != null) {
                        if(accountToAction instanceof InvestmentAccount){
                            System.out.println("1. adjust Portfolio\n2.apply Market Change");
                            int checkChoice = scanner.nextInt();
                            scanner.nextLine();
                            switch (checkChoice){
                                case 1:
                                    System.out.println("new stock Portfolio: ");
                                    String newPortfolio = scanner.nextLine();
                                    ((InvestmentAccount)accountToAction).adjustPortfolio(newPortfolio);
                                    break;
                                case 2:
                                    ((InvestmentAccount)accountToAction).applyMarketChange();
                                    System.out.println("market change applied\nnew balance :"+ accountToAction.getBalance());
                                    break;
                                default:
                                    System.out.println("Invalid input.");
                            }
                        } else if (accountToAction instanceof HighYieldSavings) {
                            System.out.println("Calculating taxes...");
                            ((HighYieldSavings)accountToAction).calculateTax();
                            System.out.println("taxes deducted \nnew balance : "+ accountToAction.getBalance());
                        }
                        else if (accountToAction instanceof SavingsAccount){
                            System.out.println("applying interest...");
                            ((SavingsAccount)accountToAction).applyInterest();
                            System.out.println("interest applied\nnew balance : "+ accountToAction.getBalance());
                        }
                        }
                         else {
                        System.out.println("\nAccount not found in the wallet.");
                    }
                    break;
                //searches for an account
                case 5:
                    System.out.print("Enter the Account ID to search for: ");
                    String searchId = scanner.nextLine();

                    // Call the recursive search starting at index 0
                    Account foundAccount = wallet.searchAccount(searchId, 0);

                    if (foundAccount != null) {
                        System.out.println("\nAccount Found:");
                        System.out.println(foundAccount);
                    } else {
                        System.out.println("\nAccount not found in the wallet.");
                    }
                    break;
                // shows all accounts
                case 6:
                    System.out.println("\n--- All Accounts in Wallet ---");
                    wallet.displayAll();
                    break;
                //leaves program
                case 7:
                    System.out.println("Exiting Digital Wallet... Goodbye!");
                    break;


                default:
                    System.out.println("Invalid input. Please select a number from 1 to 5.");
            }
        } while (choice != 7);

        scanner.close();
    }
}