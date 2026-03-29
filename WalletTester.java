import java.util.Scanner;

public class WalletTester {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WalletManager wallet = new WalletManager(100); // Set wallet capacity to 100
        int choice;

        do {
            System.out.println("\n=== Digital Wallet Management System ===");
            System.out.println("1. Add a new Savings Account");
            System.out.println("2. Remove an Account");
            System.out.println("3. Search for an Account");
            System.out.println("4. Display All Accounts");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()

            switch (choice) {
                case 1:
                    System.out.print("Enter Account ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    double bal = scanner.nextDouble();
                    System.out.print("Enter Interest Rate (e.g., 0.05 for 5%): ");
                    double rate = scanner.nextDouble();

                    // Automatically generating a date for simplicity in the UI
                    Date today = new Date(28, 3, 2026);
                    SavingsAccount newAcc = new SavingsAccount(id, bal, today, rate);

                    if (wallet.addAccount(newAcc)) {
                        System.out.println("Success! Account " + id + " has been added.");
                    } else {
                        System.out.println("Error: Wallet capacity is full.");
                    }
                    break;

                case 2:
                    System.out.print("Enter the Account ID you want to remove: ");
                    String removeId = scanner.nextLine();
                    if (wallet.removeAccount(removeId)) {
                        System.out.println("Account " + removeId + " was successfully removed.");
                    } else {
                        System.out.println("Error: Account not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter the Account ID to search for: ");
                    String searchId = scanner.nextLine();

                    // Call the recursive search starting at index 0
                    Account foundAccount = wallet.searchAccount(searchId, 0);

                    if (foundAccount != null) {
                        System.out.println("\nAccount Found:");
                        System.out.println(foundAccount.toString());
                    } else {
                        System.out.println("\nAccount not found in the wallet.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- All Accounts in Wallet ---");
                    wallet.displayAll();
                    break;

                case 5:
                    System.out.println("Exiting Digital Wallet... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid input. Please select a number from 1 to 5.");
            }
        } while (choice != 5);

        scanner.close();
    }
}