package collections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeSet;

import model.Product;
import model.Transaction;
import comparators.NameComparator;
import comparators.PriceComparator;
import comparators.ValueComparator;

public class InventoryManagementSystem {

    private static final int LOW_STOCK_LIMIT = 10;

    private HashSet<Product> productSet;
    private TreeSet<Product> sortedProducts;
    private LinkedList<Transaction> transactionHistory;
    private Stack<Product> undoStack;
    private Queue<Product> lowStockQueue;

    public InventoryManagementSystem() {
        productSet = new HashSet<>();
        sortedProducts = new TreeSet<>();
        transactionHistory = new LinkedList<>();
        undoStack = new Stack<>();
        lowStockQueue = new LinkedList<>();
    }

    public void addProduct(Product product) {

        if (productSet.add(product)) {
            sortedProducts.add(product);

            Transaction transaction = new Transaction(
                    "ADD",
                    product.getSku(),
                    0,
                    product.getQuantity()
            );

            transactionHistory.addFirst(transaction);

            if (product.getQuantity() < LOW_STOCK_LIMIT) {
                lowStockQueue.offer(product);
            }

            System.out.println("Product added successfully.");
        } else {
            System.out.println(
                    "Product with SKU " + product.getSku() + " already exists."
            );
        }
    }

    public Product searchProduct(String sku) {

        for (Product product : productSet) {
            if (product.getSku().equalsIgnoreCase(sku)) {
                return product;
            }
        }

        return null;
    }

    public void updateProductQuantity(String sku, int newQuantity) {

        Product product = searchProduct(sku);

        if (product == null) {
            System.out.println("Product with SKU " + sku + " not found.");
            return;
        }

        if (newQuantity < 0) {
            System.out.println("Quantity cannot be negative.");
            return;
        }

        int oldQuantity = product.getQuantity();

        Product previousState = new Product(
                product.getSku(),
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                oldQuantity
        );

        undoStack.push(previousState);

        product.setQuantity(newQuantity);

        Transaction transaction = new Transaction(
                "UPDATE",
                sku,
                oldQuantity,
                newQuantity
        );

        transactionHistory.addFirst(transaction);

        updateLowStockQueue(product);

        System.out.println("Quantity updated successfully.");
    }

    private void updateLowStockQueue(Product product) {

        lowStockQueue.remove(product);

        if (product.getQuantity() < LOW_STOCK_LIMIT) {
            lowStockQueue.offer(product);
        }
    }

    public void undoLastUpdate() {

        if (undoStack.isEmpty()) {
            System.out.println("No operation to undo.");
            return;
        }

        Product previousState = undoStack.pop();

        Product currentProduct = searchProduct(previousState.getSku());

        if (currentProduct == null) {
            System.out.println("Product not found.");
            return;
        }

        int currentQuantity = currentProduct.getQuantity();

        currentProduct.setQuantity(previousState.getQuantity());

        transactionHistory.addFirst(
                new Transaction(
                        "UNDO",
                        previousState.getSku(),
                        currentQuantity,
                        previousState.getQuantity()
                )
        );

        updateLowStockQueue(currentProduct);

        System.out.println("Last update undone.");
    }

    public void displayProductsSortedBy(String criteria) {

        List<Product> productList = new ArrayList<>(productSet);

        switch (criteria.toLowerCase()) {

            case "sku":
                productList.sort(null);
                break;

            case "price":
                productList.sort(new PriceComparator());
                break;

            case "value":
                productList.sort(new ValueComparator());
                break;

            case "name":
                productList.sort(new NameComparator());
                break;

            default:
                System.out.println("Invalid sorting criteria.");
                return;
        }

        displayProductList(productList);
    }

    private void displayProductList(List<Product> products) {

        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        System.out.printf(
                "%-10s %-20s %-15s %-12s %-10s %-15s%n",
                "SKU", "Name", "Category", "Price", "Quantity", "Value"
        );

        System.out.println("-".repeat(85));

        for (Product product : products) {
            System.out.printf(
                    "%-10s %-20s %-15s ₹%-10.2f %-10d ₹%-12.2f%n",
                    product.getSku(),
                    product.getName(),
                    product.getCategory(),
                    product.getPrice(),
                    product.getQuantity(),
                    product.getInventoryValue()
            );
        }
    }

    public void displayLowStockAlerts() {

        System.out.println("\n=== LOW STOCK ALERTS ===");

        if (lowStockQueue.isEmpty()) {
            System.out.println("No low stock items.");
            return;
        }

        int count = 1;

        for (Product product : lowStockQueue) {
            System.out.printf(
                    "%d. %s - %s (Current Stock: %d)%n",
                    count++,
                    product.getSku(),
                    product.getName(),
                    product.getQuantity()
            );
        }
    }

    public void displayTransactionHistory(int count) {

        System.out.println("\n=== TRANSACTION HISTORY ===");

        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        Iterator<Transaction> iterator = transactionHistory.iterator();

        int displayed = 0;

        while (iterator.hasNext() && displayed < count) {
            System.out.println(iterator.next());
            displayed++;
        }
    }

    public void displayInventoryStatistics() {

        int totalProducts = productSet.size();
        double totalInventoryValue = 0;

        for (Product product : productSet) {
            totalInventoryValue += product.getInventoryValue();
        }

        System.out.println("\n=== INVENTORY STATISTICS ===");
        System.out.println("Total Products: " + totalProducts);
        System.out.printf(
                "Total Inventory Value: ₹%.2f%n",
                totalInventoryValue
        );
    }

    public TreeSet<Product> getSortedProducts() {
        return sortedProducts;
    }

    public HashSet<Product> getProductSet() {
        return productSet;
    }

    public LinkedList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}