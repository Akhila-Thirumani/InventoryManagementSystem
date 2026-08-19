# Inventory Management System

## Project Overview

The Inventory Management System is a Java-based console application designed to manage products, inventory quantities, stock alerts, transactions, and product sorting.

The project demonstrates Java Collections, Object-Oriented Programming, Generics, Comparable, and Comparator concepts through a practical inventory management application.

## Features

- Add products with unique SKUs
- Search products by SKU
- Update product quantities
- Sort products by SKU, price, name, and inventory value
- Track transaction history
- Display low-stock alerts
- Undo the last quantity update
- Display inventory statistics
- Use generic utility methods

## Technologies Used

- Java
- Eclipse IDE
- Java Collections Framework
- Object-Oriented Programming
- Generics
- Comparable and Comparator

## Data Structures Used

- HashSet - Maintains unique products
- TreeSet - Maintains products in natural SKU order
- LinkedList - Stores transaction history
- Stack - Supports undo functionality
- Queue - Manages low-stock products

## Project Structure

```text
src
├── model
│   ├── Product.java
│   └── Transaction.java
│
├── comparators
│   ├── NameComparator.java
│   ├── PriceComparator.java
│   └── ValueComparator.java
│
├── collections
│   └── InventoryManagementSystem.java
│
└── inventory
    ├── InventoryUtils.java
    └── Main.java
