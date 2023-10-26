package org.example;
import java.util.Scanner;

import java.sql.*;
import static org.example.Constants.*;

public class Main {

    public static void addProduct(int product_id, String product_name, String price, String quantity) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String insertQuery = "INSERT INTO product_info.product (product_id, product_name, price, quantity) VALUES (?,?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
                preparedStatement.setInt(1, product_id);
                preparedStatement.setString(2, product_name);
                preparedStatement.setString(3, price);
                preparedStatement.setString(4, quantity);
                preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteProduct(int product_id){
        try (Connection cons = DriverManager.getConnection(url, user, password)) {
            String deletequery = "delete from product_info.product where  product_id = (?)";
            PreparedStatement prepareds = cons.prepareStatement(deletequery);
            prepareds.setInt(1, product_id);

            try {
                int rowsDeleted = prepareds.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Product with ID " + product_id + " deleted.");
                } else {
                    System.out.println("Product not found or deletion failed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void displayallproducts() {
        try (Connection cons = DriverManager.getConnection(url, user, password)) {
            String displayall = "select * from product_info.product";
            PreparedStatement prepareds = cons.prepareStatement(displayall);
            ResultSet res = prepareds.executeQuery();
            while (res.next()) {
                int id = res.getInt("product_id");
                String na = res.getString("product_name");
                String proname = res.getString("price");
                String qan = res.getString("quantity");

                System.out.println("Product ID: " + id);
                System.out.println("Product Name: " + na);
                System.out.println("Price: " + proname);
                System.out.println("Quantity: " + qan);
                System.out.println("-------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. Delete Product");
            System.out.println("3. DisplayAllProducts");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter product ID: ");
                    int productId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter product name: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter price: ");
                    String price = scanner.next();
                    System.out.print("Enter quantity: ");
                    String quantity = scanner.next();
                    addProduct(productId, productName, price, quantity);
                    break;
                case 2:
                    System.out.print("Enter product ID: ");
                    int products = scanner.nextInt();
                    deleteProduct(products);
                    break;
                case 3:
                    displayallproducts();
                    break;
                case 4:
                    System.out.println("Exiting.");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
