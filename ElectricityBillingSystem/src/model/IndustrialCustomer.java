package model;

/**
 * Industrial Customer - extends Customer.
 * Demonstrates: INHERITANCE + POLYMORPHISM (method overriding)
 *
 * Industrial Rates:
 *   Flat rate       : Rs. 10.00 / unit
 *   Fixed Charge    : Rs. 500.00
 *   Demand Charge   : Rs. 200.00 (flat extra for industrial usage)
 */
public class IndustrialCustomer extends Customer {

    private static final double FIXED_CHARGE  = 500.0;
    private static final double DEMAND_CHARGE = 200.0;
    private static final double RATE_PER_UNIT = 10.0;

    // Constructor
    public IndustrialCustomer(int customerId, String name, String address, String meterNumber) {
        super(customerId, name, address, meterNumber);
    }

    /**
     * Overrides calculateBill() - flat rate with demand charge.
     * Demonstrates: POLYMORPHISM - method overriding
     */
    @Override
    public double calculateBill(int unitsConsumed) {
        return FIXED_CHARGE + DEMAND_CHARGE + (unitsConsumed * RATE_PER_UNIT);
    }

    /**
     * Overrides getCustomerType().
     * Demonstrates: POLYMORPHISM
     */
    @Override
    public String getCustomerType() {
        return "Industrial";
    }
}
