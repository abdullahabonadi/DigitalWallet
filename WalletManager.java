public class WalletManager {
    private Account[] accounts;
    private int count;

    public WalletManager(int capacity) {
        accounts = new Account[capacity];
        count = 0;
    }

    public boolean addAccount(Account acc) {
        if (count < accounts.length) {
            accounts[count] = acc;
            count++;
            return true;
        }
        return false; // Array is full
    }

    public boolean removeAccount(String id) {
        for (int i = 0; i < count; i++) {
            if (accounts[i].getAccountId().equals(id)) {
                // Shift elements to the left to fill the gap and keep the array contiguous
                for (int j = i; j < count - 1; j++) {
                    accounts[j] = accounts[j + 1];
                }
                accounts[count - 1] = null;
                count--;
                return true;
            }
        }
        return false; // Account not found
    }

    // Recursive search method
    public Account searchAccount(String id, int index) {
        // Base case 1: We searched all active accounts and didn't find it
        if (index >= count) {
            return null;
        }
        // Base case 2: We found the matching account ID
        if (accounts[index].getAccountId().equals(id)) {
            return accounts[index];
        }
        // Recursive step: Call the method again, moving to the next index
        return searchAccount(id, index + 1);
    }

    public void displayAll() {
        if (count == 0) {
            System.out.println("The wallet is currently empty.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println(accounts[i].toString());
        }
    }
}