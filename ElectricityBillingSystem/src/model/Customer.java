package model;

/**
 * Abstract base class representing a Customer.
 * Demonstrates: INHERITANCE (parent class)
 */
public abstract class Customer {

    // Instance variables
    private int customerId;
    private String name;
    private String address;
    private String meterNumber;

    // Constructor
    public Customer(int customerId, String name, String address, String meterNumber) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.meterNumber = meterNumber;
    }

    // Getters
    public int getCustomerId()    { return customerId; }
    public String getName()       { return name; }
    public String getAddress()    { return address; }
    public String getMeterNumber(){ return meterNumber; }

    // Setters
    public void setName(String name)       { this.name = name; }
    public void setAddress(String address) { this.address = address; }

    /**
     * Abstract method to calculate bill.
     * Demonstrates: POLYMORPHISM (overridden in subclasses)
     */
    public abstract double calculateBill(int unitsConsumed);

    /**
     * Abstract method to return customer type label.
     * Demonstrates: POLYMORPHISM
     */
    public abstract String getCustomerType();

    // Display basic customer details
    @Override
    public String toString() {
        return String.format(
            "ID: %d | Name: %-20s | Type: %-15s | Meter: %s",
            customerId, name, getCustomerType(), meterNumber
        );
    }
}
