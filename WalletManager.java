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
    public Account searchAccount(String id, Account current) {
        //End of list reached
        if (current == null) {
            return null;
        }
        // The ID matches the current account
        if (current.getAccountId().equals(id)) {
            return current;
        }
        //move to the next account in the chain
        return searchAccount(id, current.next);
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