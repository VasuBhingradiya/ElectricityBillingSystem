package model;

/**
 * Residential Customer - extends Customer.
 * Demonstrates: INHERITANCE + POLYMORPHISM (method overriding)
 *
 * Residential Slab Rates:
 *   0  - 100  units : Rs. 3.50 / unit
 *   101 - 300 units : Rs. 5.00 / unit
 *   Above 300 units : Rs. 7.00 / unit
 *   Fixed Charge    : Rs. 50.00
 */
public class ResidentialCustomer extends Customer {

    // Fixed monthly charge for residential customers
    private static final double FIXED_CHARGE = 50.0;

    // Constructor
    public ResidentialCustomer(int customerId, String name, String address, String meterNumber) {
        super(customerId, name, address, meterNumber); // calling parent constructor
    }

    /**
     * Overrides calculateBill() from Customer.
     * Demonstrates: POLYMORPHISM - method overriding
     */
    @Override
    public double calculateBill(int unitsConsumed) {
        double amount = FIXED_CHARGE;

        if (unitsConsumed <= 100) {
            amount += unitsConsumed * 3.50;
        } else if (unitsConsumed <= 300) {
            amount += (100 * 3.50) + ((unitsConsumed - 100) * 5.00);
        } else {
            amount += (100 * 3.50) + (200 * 5.00) + ((unitsConsumed - 300) * 7.00);
        }
        return amount;
    }

    /**
     * Overrides getCustomerType() from Customer.
     * Demonstrates: POLYMORPHISM - method overriding
     */
    @Override
    public String getCustomerType() {
        return "Residential";
    }
}
