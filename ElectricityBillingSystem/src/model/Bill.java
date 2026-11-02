package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a generated electricity bill for a customer.
 */
public class Bill {

    private int billId;
    private Customer customer;       // Association with Customer
    private int unitsConsumed;
    private double totalAmount;
    private LocalDate billDate;
    private boolean isPaid;

    // Static counter for auto-generating bill IDs
    private static int billCounter = 1;

    // Constructor
    public Bill(Customer customer, int unitsConsumed) {
        this.billId = billCounter++;
        this.customer = customer;
        this.unitsConsumed = unitsConsumed;
        this.totalAmount = customer.calculateBill(unitsConsumed); // polymorphic call
        this.billDate = LocalDate.now();
        this.isPaid = false;
    }

    // Getters
    public int getBillId()          { return billId; }
    public Customer getCustomer()   { return customer; }
    public int getUnitsConsumed()   { return unitsConsumed; }
    public double getTotalAmount()  { return totalAmount; }
    public LocalDate getBillDate()  { return billDate; }
    public boolean isPaid()         { return isPaid; }

    // Mark bill as paid
    public void markAsPaid() {
        this.isPaid = true;
    }

    // Print a formatted bill receipt
    public void printBill() {
        String line = "=".repeat(50);
        String formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                                            .format(billDate);
        System.out.println(line);
        System.out.println("       ELECTRICITY BILLING SYSTEM");
        System.out.println("              BILL RECEIPT");
        System.out.println(line);
        System.out.printf("Bill ID        : %d%n",   billId);
        System.out.printf("Bill Date      : %s%n",   formatter);
        System.out.printf("Customer ID    : %d%n",   customer.getCustomerId());
        System.out.printf("Customer Name  : %s%n",   customer.getName());
        System.out.printf("Customer Type  : %s%n",   customer.getCustomerType());
        System.out.printf("Address        : %s%n",   customer.getAddress());
        System.out.printf("Meter Number   : %s%n",   customer.getMeterNumber());
        System.out.println(line);
        System.out.printf("Units Consumed : %d units%n", unitsConsumed);
        System.out.printf("Total Amount   : Rs. %.2f%n",  totalAmount);
        System.out.printf("Payment Status : %s%n",   isPaid ? "PAID" : "PENDING");
        System.out.println(line);
    }

    // Short summary line
    @Override
    public String toString() {
        return String.format(
            "Bill#%d | %s | Units: %d | Rs.%.2f | %s",
            billId, customer.getName(), unitsConsumed,
            totalAmount, isPaid ? "PAID" : "PENDING"
        );
    }
}
