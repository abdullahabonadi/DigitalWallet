import java.io.Serializable;
public class WalletManager implements Serializable{
    //(Aggregation).
    private Account head;

    public WalletManager(){
        this.head = null;
    }

    public boolean addAccount(Account acc) {
        if (acc == null) return false;
        // Linked List insertion at the front
        acc.next = head;
        head = acc;
        return true;
    }
    public Account getHead() {
        return head;
    }

    public boolean removeAccount(String id) {
        if (head == null) return false;

        //removing the first account
        if (head.getAccountId().equals(id)) {
            head = head.next;
            return true;
        }

        Account curr = head;
        while (curr.next != null) {
            if (curr.next.getAccountId().equals(id)) {
                curr.next = curr.next.next; // Bypass the account to remove it
                return true;
            }
            curr = curr.next;
        }
        return false;
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
            System.out.println(accounts[i]);
        }
    }

    public void displayIDsOnly(){
        if(count == 0){
            System.out.println("The wallet is currently empty.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println("Account: "+accounts[i].getAccountId());
        }
    }


}