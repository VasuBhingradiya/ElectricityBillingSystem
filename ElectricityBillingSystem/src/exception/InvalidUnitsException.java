package exception;

/**
 * Custom Exception for invalid unit input.
 * Demonstrates: EXCEPTION HANDLING - custom exception class
 */
public class InvalidUnitsException extends Exception {

    private int invalidUnits;

    // Constructor with message
    public InvalidUnitsException(String message) {
        super(message);
    }

    // Constructor with message and the bad value
    public InvalidUnitsException(String message, int invalidUnits) {
        super(message);
        this.invalidUnits = invalidUnits;
    }

    public int getInvalidUnits() {
        return invalidUnits;
    }
}
