package inventory;



import java.util.Scanner;

import collections.InventoryManagementSystem;
import model.Product;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final InventoryManagementSystem inventory =
            new InventoryManagementSystem();

    public static void main(String[] args) {

        boolean running = true;

        while (running) {
            displayMenu();

            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addProduct();
                    break;

                case 2:
                    updateQuantity();
                    break;

                case 3:
                    displaySortedProducts();
                    break;

                case 4:
                    searchProduct();
                    break;

                case 5:
                    inventory.displayLowStockAlerts();
                    break;

                case 6:
                    displayTransactionHistory();
                    break;

                case 7:
                    inventory.displayInventoryStatistics();
                    break;

                case 8:
                    inventory.undoLastUpdate();
                    break;

                case 9:
                    running = false;
                    System.out.println("Thank you for using Inventory Management System.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n=== INVENTORY MANAGEMENT SYSTEM ===");
        System.out.println("1. Add Product");
        System.out.println("2. Update Quantity");
        System.out.println("3. View Products (Sorted)");
        System.out.println("4. Search Product");
        System.out.println("5. Low Stock Alerts");
        System.out.println("6. Transaction History");
        System.out.println("7. Inventory Statistics");
        System.out.println("8. Undo Last Update");
        System.out.println("9. Exit");
    }

    private static void addProduct() {

        System.out.println("\n=== ADD NEW PRODUCT ===");

        String sku = readText("Enter SKU: ");
        String name = readText("Enter Name: ");
        String category = readText("Enter Category: ");
        double price = readDouble("Enter Price: ");
        int quantity = readInt("Enter Quantity: ");

        if (price < 0 || quantity < 0) {
            System.out.println("Price and quantity cannot be negative.");
            return;
        }

        Product product = new Product(
                sku,
                name,
                category,
                price,
                quantity
        );

        inventory.addProduct(product);
    }

    private static void updateQuantity() {

        System.out.println("\n=== UPDATE QUANTITY ===");

        String sku = readText("Enter SKU: ");
        int newQuantity = readInt("Enter New Quantity: ");

        inventory.updateProductQuantity(sku, newQuantity);
    }

    private static void displaySortedProducts() {

        System.out.println("\nSort by:");
        System.out.println("1. SKU");
        System.out.println("2. Price");
        System.out.println("3. Inventory Value");
        System.out.println("4. Name");

        int choice = readInt("Enter sorting option: ");

        switch (choice) {
            case 1:
                inventory.displayProductsSortedBy("sku");
                break;

            case 2:
                inventory.displayProductsSortedBy("price");
                break;

            case 3:
                inventory.displayProductsSortedBy("value");
                break;

            case 4:
                inventory.displayProductsSortedBy("name");
                break;

            default:
                System.out.println("Invalid sorting option.");
        }
    }

    private static void searchProduct() {

        String sku = readText("Enter SKU to search: ");

        Product product = inventory.searchProduct(sku);

        if (product == null) {
            System.out.println("Product not found.");
        } else {
            System.out.println("\nProduct Found:");
            System.out.println(product);
        }
    }

    private static void displayTransactionHistory() {

        int count = readInt("Enter number of transactions to view: ");

        if (count <= 0) {
            System.out.println("Enter a positive number.");
            return;
        }

        inventory.displayTransactionHistory(count);
    }

    private static String readText(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}