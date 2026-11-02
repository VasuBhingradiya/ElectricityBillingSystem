# Electricity Billing System - Java Project

## Overview
A menu-driven console application that simulates an Electricity Billing System,
demonstrating core Java and Object-Oriented Programming concepts.

---

## Java Concepts Demonstrated

| Concept               | Where Used                                                                 |
|-----------------------|----------------------------------------------------------------------------|
| **Inheritance**       | `Customer` (abstract) → `ResidentialCustomer`, `CommercialCustomer`, `IndustrialCustomer` |
| **Polymorphism**      | `calculateBill()` and `getCustomerType()` overridden in each subclass     |
| **Collections**       | `HashMap<Integer, Customer>` for customer store; `ArrayList<Bill>` for bills |
| **Exception Handling**| `try-catch-finally`; custom `InvalidUnitsException`, `CustomerNotFoundException` |

---

## Project Structure

```
ElectricityBillingSystem/
└── src/
    ├── model/
    │   ├── Customer.java              ← Abstract base class (Inheritance root)
    │   ├── ResidentialCustomer.java   ← Subclass (Inheritance + Polymorphism)
    │   ├── CommercialCustomer.java    ← Subclass (Inheritance + Polymorphism)
    │   ├── IndustrialCustomer.java    ← Subclass (Inheritance + Polymorphism)
    │   └── Bill.java                  ← Bill model
    ├── exception/
    │   ├── InvalidUnitsException.java       ← Custom Exception
    │   └── CustomerNotFoundException.java   ← Custom Exception
    ├── service/
    │   └── BillingService.java        ← Business logic + Collections usage
    └── main/
        └── Main.java                  ← Entry point + Menu
```

---

## Billing Rate Chart

### Residential
| Units        | Rate / Unit | Fixed Charge |
|-------------|-------------|--------------|
| 0 – 100     | Rs. 3.50    | Rs. 50       |
| 101 – 300   | Rs. 5.00    |              |
| Above 300   | Rs. 7.00    |              |

### Commercial
| Units        | Rate / Unit | Fixed Charge | Surcharge |
|-------------|-------------|--------------|-----------|
| 0 – 200     | Rs. 6.00    | Rs. 150      | 10%       |
| Above 200   | Rs. 9.00    |              |           |

### Industrial
| Rate / Unit | Fixed Charge | Demand Charge |
|-------------|--------------|---------------|
| Rs. 10.00   | Rs. 500      | Rs. 200       |

---

## How to Compile and Run

### Using Command Line

```bash
# Step 1: Navigate to the src folder
cd ElectricityBillingSystem/src

# Step 2: Compile all Java files
javac -d . model/*.java exception/*.java service/*.java main/*.java

# Step 3: Run the application
java main.Main
```

### Using an IDE (Eclipse / IntelliJ IDEA)
1. Open IDE → Import Project → Select `ElectricityBillingSystem` folder
2. Mark `src` as the source root
3. Run `Main.java`

---

## Menu Options
```
1. Add Customer          - Register Residential / Commercial / Industrial customer
2. View All Customers    - List all registered customers
3. Generate Bill         - Generate bill for a customer by units consumed
4. View Bill Receipt     - Print formatted bill receipt
5. Pay Bill              - Mark a bill as paid
6. View All Bills        - List all generated bills
7. View Pending Bills    - List unpaid bills
8. Summary / Revenue     - Show total customers, bills, and revenue collected
9. Remove Customer       - Delete a customer record
0. Exit
```

---

## Group Members
- Student 1: ___________________________
- Student 2: ___________________________
