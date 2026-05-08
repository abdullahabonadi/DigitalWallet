public class InsufficientFundsException extends Exception {

    // Constructor that passes the custom error message to the superclass
    public InsufficientFundsException(String message) {
        super(message);
    }
}
