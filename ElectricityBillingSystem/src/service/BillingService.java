package service;

import model.*;
import exception.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BillingService handles all business logic for the billing system.
 *
 * Demonstrates:
 *   - COLLECTIONS FRAMEWORK : HashMap (customer store), ArrayList (bill store)
 *   - EXCEPTION HANDLING     : try-catch-finally, custom exceptions
 */
public class BillingService {

    // --- Collections Framework ---
    // HashMap to store customers: key = customerId, value = Customer object
    private Map<Integer, Customer> customerMap = new HashMap<>();

    // ArrayList to store all generated bills
    private List<Bill> billList = new ArrayList<>();

    // =====================================================================
    //  CUSTOMER MANAGEMENT
    // =====================================================================

    /**
     * Adds a new customer to the system.
     * Demonstrates: HashMap put()
     */
    public void addCustomer(Customer customer) {
        customerMap.put(customer.getCustomerId(), customer);
        System.out.println("✔ Customer added: " + customer.getName()
                           + " [" + customer.getCustomerType() + "]");
    }

    /**
     * Retrieves a customer by ID.
     * Demonstrates: EXCEPTION HANDLING - throws custom exception
     */
    public Customer getCustomer(int customerId) throws CustomerNotFoundException {
        // try-finally to ensure lookup is always logged
        try {
            Customer c = customerMap.get(customerId);
            if (c == null) {
                throw new CustomerNotFoundException(customerId);
            }
            return c;
        } finally {
            // finally block always executes
            System.out.println("[LOG] Customer lookup attempted for ID: " + customerId);
        }
    }

    /**
     * Removes a customer from the system.
     * Demonstrates: EXCEPTION HANDLING + HashMap remove()
     */
    public void removeCustomer(int customerId) throws CustomerNotFoundException {
        if (!customerMap.containsKey(customerId)) {
            throw new CustomerNotFoundException(customerId);
        }
        String name = customerMap.get(customerId).getName();
        customerMap.remove(customerId);
        System.out.println("✔ Customer removed: " + name);
    }

    /**
     * Displays all registered customers.
     * Demonstrates: HashMap iteration (values())
     */
    public void displayAllCustomers() {
        if (customerMap.isEmpty()) {
            System.out.println("No customers registered yet.");
            return;
        }
        System.out.println("\n--- Registered Customers ---");
        for (Customer c : customerMap.values()) {   // iterating HashMap values
            System.out.println(c);
        }
        System.out.println("Total customers: " + customerMap.size());
    }

    // =====================================================================
    //  BILL GENERATION
    // =====================================================================

    /**
     * Generates a bill for a customer.
     * Demonstrates: EXCEPTION HANDLING (try-catch-finally) + POLYMORPHISM (calculateBill)
     */
    public Bill generateBill(int customerId, int unitsConsumed)
            throws CustomerNotFoundException, InvalidUnitsException {

        try {
            // Validate units - throws custom exception if invalid
            if (unitsConsumed < 0) {
                throw new InvalidUnitsException(
                    "Units cannot be negative.", unitsConsumed);
            }
            if (unitsConsumed > 100000) {
                throw new InvalidUnitsException(
                    "Units exceed maximum allowed limit (100000).", unitsConsumed);
            }

            // Fetch customer - throws CustomerNotFoundException if not found
            Customer customer = getCustomer(customerId);

            // Create bill (internally calls polymorphic calculateBill())
            Bill bill = new Bill(customer, unitsConsumed);

            // Add to ArrayList
            billList.add(bill);

            System.out.println("✔ Bill generated successfully. Bill ID: " + bill.getBillId());
            return bill;

        } catch (InvalidUnitsException e) {
            // Catch custom exception and rethrow for caller to handle
            System.out.println("✘ Invalid units: " + e.getMessage());
            throw e;
        } finally {
            // finally block always executes - used for logging/cleanup
            System.out.println("[LOG] generateBill() called for Customer ID: " + customerId);
        }
    }

    // =====================================================================
    //  PAYMENT
    // =====================================================================

    /**
     * Marks a bill as paid.
     * Demonstrates: ArrayList iteration
     */
    public void payBill(int billId) {
        boolean found = false;
        for (Bill b : billList) {       // iterating ArrayList
            if (b.getBillId() == billId) {
                if (b.isPaid()) {
                    System.out.println("Bill #" + billId + " is already paid.");
                } else {
                    b.markAsPaid();
                    System.out.println("✔ Payment successful for Bill #" + billId
                                       + " | Amount: Rs." + b.getTotalAmount());
                }
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("✘ Bill ID " + billId + " not found.");
        }
    }

    // =====================================================================
    //  REPORTS
    // =====================================================================

    /**
     * Displays all bills.
     * Demonstrates: ArrayList iteration
     */
    public void displayAllBills() {
        if (billList.isEmpty()) {
            System.out.println("No bills generated yet.");
            return;
        }
        System.out.println("\n--- All Bills ---");
        for (Bill b : billList) {
            System.out.println(b);
        }
    }

    /**
     * Displays pending (unpaid) bills.
     * Demonstrates: ArrayList conditional iteration
     */
    public void displayPendingBills() {
        System.out.println("\n--- Pending Bills ---");
        boolean found = false;
        for (Bill b : billList) {
            if (!b.isPaid()) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) System.out.println("No pending bills.");
    }

    /**
     * Calculates total revenue collected (paid bills only).
     * Demonstrates: ArrayList iteration + accumulation
     */
    public double getTotalRevenue() {
        double total = 0;
        for (Bill b : billList) {
            if (b.isPaid()) {
                total += b.getTotalAmount();
            }
        }
        return total;
    }

    /**
     * Displays a bill receipt by bill ID.
     */
    public void printBillById(int billId) {
        for (Bill b : billList) {
            if (b.getBillId() == billId) {
                b.printBill();
                return;
            }
        }
        System.out.println("✘ Bill ID " + billId + " not found.");
    }

    /**
     * Returns total number of customers.
     */
    public int getTotalCustomers() {
        return customerMap.size();
    }

    /**
     * Returns total number of bills generated.
     */
    public int getTotalBills() {
        return billList.size();
    }
}
