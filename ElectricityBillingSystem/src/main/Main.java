package main;

import model.*;
import service.BillingService;
import exception.*;

import java.util.Scanner;

/**
 * Main class - Entry point for Electricity Billing System.
 *
 * Demonstrates all required concepts:
 *   ✔ Inheritance         - Customer → ResidentialCustomer, CommercialCustomer, IndustrialCustomer
 *   ✔ Polymorphism        - calculateBill() overridden in each subclass
 *   ✔ Collections         - HashMap & ArrayList used in BillingService
 *   ✔ Exception Handling  - try-catch-finally + custom exceptions
 */
public class Main {

    static BillingService service = new BillingService();
    static Scanner scanner = new Scanner(System.in);
    static int customerIdCounter = 101; // Auto-increment customer IDs

    public static void main(String[] args) {

        // Pre-load some sample customers for demonstration
        loadSampleData();

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1:  addCustomer();        break;
                case 2:  service.displayAllCustomers(); break;
                case 3:  generateBill();       break;
                case 4:  viewBillReceipt();    break;
                case 5:  payBill();            break;
                case 6:  service.displayAllBills();    break;
                case 7:  service.displayPendingBills();break;
                case 8:  showSummary();        break;
                case 9:  removeCustomer();     break;
                case 0:
                    System.out.println("\nThank you for using Electricity Billing System. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("✘ Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    // ------------------------------------------------------------------
    //  MENU
    // ------------------------------------------------------------------
    private static void printMainMenu() {
        System.out.println("\n" + "=".repeat(45));
        System.out.println("      ELECTRICITY BILLING SYSTEM");
        System.out.println("=".repeat(45));
        System.out.println(" 1. Add Customer");
        System.out.println(" 2. View All Customers");
        System.out.println(" 3. Generate Bill");
        System.out.println(" 4. View Bill Receipt");
        System.out.println(" 5. Pay Bill");
        System.out.println(" 6. View All Bills");
        System.out.println(" 7. View Pending Bills");
        System.out.println(" 8. Summary / Revenue Report");
        System.out.println(" 9. Remove Customer");
        System.out.println(" 0. Exit");
        System.out.println("=".repeat(45));
    }

    // ------------------------------------------------------------------
    //  ADD CUSTOMER
    // ------------------------------------------------------------------
    private static void addCustomer() {
        System.out.println("\n-- Add New Customer --");
        System.out.println("Select Customer Type:");
        System.out.println("  1. Residential");
        System.out.println("  2. Commercial");
        System.out.println("  3. Industrial");
        int type = readInt("Enter type: ");

        System.out.print("Enter Name    : ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Address : ");
        String address = scanner.nextLine().trim();
        System.out.print("Enter Meter # : ");
        String meter = scanner.nextLine().trim();

        int id = customerIdCounter++;
        Customer customer;

        // Demonstrates POLYMORPHISM - different subclass objects created
        switch (type) {
            case 1: customer = new ResidentialCustomer(id, name, address, meter); break;
            case 2: customer = new CommercialCustomer(id, name, address, meter);  break;
            case 3: customer = new IndustrialCustomer(id, name, address, meter);  break;
            default:
                System.out.println("✘ Invalid type. Defaulting to Residential.");
                customer = new ResidentialCustomer(id, name, address, meter);
        }

        service.addCustomer(customer);
        System.out.println("  Assigned Customer ID: " + id);
    }

    // ------------------------------------------------------------------
    //  GENERATE BILL
    // ------------------------------------------------------------------
    private static void generateBill() {
        System.out.println("\n-- Generate Bill --");
        int customerId = readInt("Enter Customer ID : ");
        int units      = readInt("Enter Units Consumed: ");

        try {
            // Demonstrates EXCEPTION HANDLING - catching multiple custom exceptions
            Bill bill = service.generateBill(customerId, units);
            bill.printBill();

        } catch (CustomerNotFoundException e) {
            // Catching CustomerNotFoundException
            System.out.println("✘ Error: " + e.getMessage());
        } catch (InvalidUnitsException e) {
            // Catching InvalidUnitsException
            System.out.println("✘ Error: " + e.getMessage()
                               + " (Entered: " + e.getInvalidUnits() + ")");
        }
    }

    // ------------------------------------------------------------------
    //  VIEW BILL RECEIPT
    // ------------------------------------------------------------------
    private static void viewBillReceipt() {
        System.out.println("\n-- View Bill Receipt --");
        int billId = readInt("Enter Bill ID: ");
        service.printBillById(billId);
    }

    // ------------------------------------------------------------------
    //  PAY BILL
    // ------------------------------------------------------------------
    private static void payBill() {
        System.out.println("\n-- Pay Bill --");
        int billId = readInt("Enter Bill ID to pay: ");
        service.payBill(billId);
    }

    // ------------------------------------------------------------------
    //  REMOVE CUSTOMER
    // ------------------------------------------------------------------
    private static void removeCustomer() {
        System.out.println("\n-- Remove Customer --");
        int id = readInt("Enter Customer ID to remove: ");
        try {
            service.removeCustomer(id);
        } catch (CustomerNotFoundException e) {
            System.out.println("✘ Error: " + e.getMessage());
        }
    }

    // ------------------------------------------------------------------
    //  SUMMARY REPORT
    // ------------------------------------------------------------------
    private static void showSummary() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("         SYSTEM SUMMARY REPORT");
        System.out.println("=".repeat(40));
        System.out.printf("Total Customers  : %d%n", service.getTotalCustomers());
        System.out.printf("Total Bills      : %d%n", service.getTotalBills());
        System.out.printf("Total Revenue    : Rs. %.2f%n", service.getTotalRevenue());
        System.out.println("=".repeat(40));
    }

    // ------------------------------------------------------------------
    //  SAMPLE DATA LOADER
    // ------------------------------------------------------------------
    private static void loadSampleData() {
        System.out.println("Loading sample data...");

        // Adding sample customers - demonstrates INHERITANCE (different subclasses)
        service.addCustomer(new ResidentialCustomer(101, "Ravi Sharma",   "12 MG Road, Pune",    "MTR-001"));
        service.addCustomer(new ResidentialCustomer(102, "Priya Mehta",   "45 Laxmi Nagar, Delhi","MTR-002"));
        service.addCustomer(new CommercialCustomer (103, "Raj Enterprises","7 MIDC Area, Mumbai", "MTR-003"));
        service.addCustomer(new IndustrialCustomer (104, "Steel Corp Ltd", "Plot 9, Nashik MIDC", "MTR-004"));
        customerIdCounter = 105;

        // Generating sample bills - demonstrates EXCEPTION HANDLING
        try {
            service.generateBill(101, 180);
            service.generateBill(102, 95);
            service.generateBill(103, 350);
            service.generateBill(104, 1200);
        } catch (CustomerNotFoundException | InvalidUnitsException e) {
            System.out.println("Sample data error: " + e.getMessage());
        }

        // Pay one bill
        service.payBill(1);

        System.out.println("Sample data loaded successfully!\n");
    }

    // ------------------------------------------------------------------
    //  UTILITY: Safe integer input
    // ------------------------------------------------------------------
    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                // Demonstrates EXCEPTION HANDLING - catching NumberFormatException
                System.out.println("✘ Invalid input. Please enter a valid number.");
            }
        }
    }
}
