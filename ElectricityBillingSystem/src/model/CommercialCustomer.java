package model;

/**
 * Commercial Customer - extends Customer.
 * Demonstrates: INHERITANCE + POLYMORPHISM (method overriding)
 *
 * Commercial Rates:
 *   0  - 200  units : Rs. 6.00 / unit
 *   Above 200 units : Rs. 9.00 / unit
 *   Fixed Charge    : Rs. 150.00
 *   Surcharge       : 10% on total bill
 */
public class CommercialCustomer extends Customer {

    private static final double FIXED_CHARGE = 150.0;
    private static final double SURCHARGE_PERCENT = 0.10; // 10%

    // Constructor
    public CommercialCustomer(int customerId, String name, String address, String meterNumber) {
        super(customerId, name, address, meterNumber);
    }

    /**
     * Overrides calculateBill() - different rate logic than Residential.
     * Demonstrates: POLYMORPHISM - method overriding
     */
    @Override
    public double calculateBill(int unitsConsumed) {
        double amount = FIXED_CHARGE;

        if (unitsConsumed <= 200) {
            amount += unitsConsumed * 6.00;
        } else {
            amount += (200 * 6.00) + ((unitsConsumed - 200) * 9.00);
        }

        // Add 10% surcharge
        amount += amount * SURCHARGE_PERCENT;
        return amount;
    }

    /**
     * Overrides getCustomerType().
     * Demonstrates: POLYMORPHISM
     */
    @Override
    public String getCustomerType() {
        return "Commercial";
    }
}
