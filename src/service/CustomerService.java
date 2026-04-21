package service;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerService {

    // Add Customer
    public boolean addCustomer(String name, String phone, String email) {

        String query = "INSERT INTO customers(name, phone, email) VALUES(?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, email);

            ps.executeUpdate();

            System.out.println("Customer added successfully!");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete Customer
    public boolean deleteCustomer(int customerId) {

        String query = "DELETE FROM customers WHERE customer_id=?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, customerId);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Customer deleted successfully!");
                return true;
            } else {
                System.out.println("Customer not found!");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all customers (console)
    public void getAllCustomers() {

        String query = "SELECT * FROM customers";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("customer_id") +
                        " Name: " + rs.getString("name") +
                        " Phone: " + rs.getString("phone") +
                        " Email: " + rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Check if customer exists
    public boolean customerExists(int customerId) {

        String query = "SELECT customer_id FROM customers WHERE customer_id=?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
