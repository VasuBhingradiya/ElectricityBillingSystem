package exception;

/**
 * Custom Exception thrown when a customer ID is not found.
 * Demonstrates: EXCEPTION HANDLING - custom exception class
 */
public class CustomerNotFoundException extends Exception {

    private int customerId;

    public CustomerNotFoundException(int customerId) {
        super("Customer with ID " + customerId + " not found.");
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }
}
