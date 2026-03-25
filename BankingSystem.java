import java.io.*;
import java.util.*;

class MinimumBalanceException extends Exception {
    public MinimumBalanceException(String message) {
        super(message);
    }
}

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class InvalidCustomerIdException extends Exception {
    public InvalidCustomerIdException(String message) {
        super(message);
    }
}

class NegativeAmountException extends Exception {
    public NegativeAmountException(String message) {
        super(message);
    }
}

class Customer implements Serializable {
    private int cid;
    private String cname;
    private double amount;
    
    public Customer(int cid, String cname, double amount) throws InvalidCustomerIdException, 
                                                                  MinimumBalanceException, 
                                                                  NegativeAmountException {
        if (cid < 1 || cid > 20) {
            throw new InvalidCustomerIdException("Customer ID must be between 1 and 20");
        }
        
        if (amount < 1000) {
            throw new MinimumBalanceException("Minimum opening balance should be Rs. 1000");
        }
        
        if (amount < 0) {
            throw new NegativeAmountException("Amount cannot be negative");
        }
        
        this.cid = cid;
        this.cname = cname;
        this.amount = amount;
    }
    
    public int getCid() {
        return cid;
    }
    
    public String getCname() {
        return cname;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void deposit(double depositAmount) throws NegativeAmountException {
        if (depositAmount < 0) {
            throw new NegativeAmountException("Deposit amount must be positive");
        }
        this.amount += depositAmount;
    }
    
    public void withdraw(double withdrawAmount) throws InsufficientFundsException, 
                                                        NegativeAmountException {
        if (withdrawAmount < 0) {
            throw new NegativeAmountException("Withdrawal amount must be positive");
        }
        
        if (withdrawAmount > this.amount) {
            throw new InsufficientFundsException("Insufficient balance. Available: Rs. " + this.amount);
        }
        
        this.amount -= withdrawAmount;
    }
    
    @Override
    public String toString() {
        return "Customer ID: " + cid + " | Name: " + cname + " | Balance: Rs. " + amount;
    }
}

public class BankingSystem {
    private static final String FILE_NAME = "customers_v1.dat";
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        loadCustomersFromFile();
        
        while (true) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            try {
                switch (choice) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        depositMoney();
                        break;
                    case 3:
                        withdrawMoney();
                        break;
                    case 4:
                        displayAllAccounts();
                        break;
                    case 5:
                        searchAccount();
                        break;
                    case 6:
                        saveCustomersToFile();
                        System.out.println("Thank you for using our banking system!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            System.out.println();
        }
    }
    
    private static void displayMenu() {
        System.out.println("\n========== BANKING SYSTEM MENU ==========");
        System.out.println("1. Create New Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Display All Accounts");
        System.out.println("5. Search Account");
        System.out.println("6. Exit");
        System.out.println("=========================================");
    }
    
    private static void createAccount() throws InvalidCustomerIdException, 
                                                MinimumBalanceException, 
                                                NegativeAmountException {
        System.out.println("\n--- Create New Account ---");
        System.out.print("Enter Customer ID (1-20): ");
        int cid = scanner.nextInt();
        scanner.nextLine();
        
        for (Customer c : customers) {
            if (c.getCid() == cid) {
                System.out.println("Customer ID already exists!");
                return;
            }
        }
        
        System.out.print("Enter Customer Name: ");
        String cname = scanner.nextLine();
        
        System.out.print("Enter Opening Balance (min Rs. 1000): ");
        double amount = scanner.nextDouble();
        
        Customer newCustomer = new Customer(cid, cname, amount);
        customers.add(newCustomer);
        
        System.out.println("Account created successfully!");
        System.out.println(newCustomer);
    }
    
    private static void depositMoney() throws NegativeAmountException {
        System.out.println("\n--- Deposit Money ---");
        System.out.print("Enter Customer ID: ");
        int cid = scanner.nextInt();
        
        Customer customer = findCustomer(cid);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }
        
        System.out.print("Enter deposit amount: ");
        double depositAmount = scanner.nextDouble();
        
        customer.deposit(depositAmount);
        System.out.println("Deposit successful!");
        System.out.println(customer);
    }
    
    private static void withdrawMoney() throws InsufficientFundsException, 
                                                NegativeAmountException {
        System.out.println("\n--- Withdraw Money ---");
        System.out.print("Enter Customer ID: ");
        int cid = scanner.nextInt();
        
        Customer customer = findCustomer(cid);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }
        
        System.out.print("Enter withdrawal amount: ");
        double withdrawAmount = scanner.nextDouble();
        
        customer.withdraw(withdrawAmount);
        System.out.println("Withdrawal successful!");
        System.out.println(customer);
    }
    
    private static void displayAllAccounts() {
        System.out.println("\n--- All Customer Accounts ---");
        if (customers.isEmpty()) {
            System.out.println("No accounts found!");
            return;
        }
        
        for (Customer c : customers) {
            System.out.println(c);
        }
    }
    
    private static void searchAccount() {
        System.out.println("\n--- Search Account ---");
        System.out.print("Enter Customer ID: ");
        int cid = scanner.nextInt();
        
        Customer customer = findCustomer(cid);
        if (customer == null) {
            System.out.println("Customer not found!");
        } else {
            System.out.println(customer);
        }
    }
    
    private static Customer findCustomer(int cid) {
        for (Customer c : customers) {
            if (c.getCid() == cid) {
                return c;
            }
        }
        return null;
    }
    
    private static void saveCustomersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(customers);
            System.out.println("Data saved to file successfully!");
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    private static void loadCustomersFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            customers = (ArrayList<Customer>) ois.readObject();
            System.out.println("Previous data loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous data found or error loading data.");
        }
    }
}
