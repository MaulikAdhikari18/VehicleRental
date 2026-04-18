package service;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RentalService {

    // Rent Vehicle
    public void rentVehicle(int vehicleId, int customerId, int days) {

        String getPriceQuery = "SELECT price_per_day FROM vehicles WHERE vehicle_id=?";
        String insertRental = "INSERT INTO rentals(vehicle_id, customer_id, days, total_cost) VALUES(?,?,?,?)";
        String updateVehicle = "UPDATE vehicles SET available=false WHERE vehicle_id=?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pricePs = conn.prepareStatement(getPriceQuery);
                PreparedStatement rentalPs = conn.prepareStatement(insertRental);
                PreparedStatement vehiclePs = conn.prepareStatement(updateVehicle)) {

            // 1️⃣ Fetch price
            pricePs.setInt(1, vehicleId);
            ResultSet rs = pricePs.executeQuery();

            if (!rs.next()) {
                System.out.println("Vehicle not found!");
                return;
            }

            double pricePerDay = rs.getDouble("price_per_day");

            // 2️⃣ Calculate total
            double totalCost = pricePerDay * days;

            // 3️⃣ Insert rental
            rentalPs.setInt(1, vehicleId);
            rentalPs.setInt(2, customerId);
            rentalPs.setInt(3, days);
            rentalPs.setDouble(4, totalCost);
            rentalPs.executeUpdate();

            // 4️⃣ Update availability
            vehiclePs.setInt(1, vehicleId);
            vehiclePs.executeUpdate();

            System.out.println("Vehicle rented successfully!");
            System.out.println("Total Cost: " + totalCost);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Return Vehicle
    public void returnVehicle(int vehicleId) {

        String query = "UPDATE vehicles SET available=true WHERE vehicle_id=?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, vehicleId);
            ps.executeUpdate();

            System.out.println("Vehicle returned successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}