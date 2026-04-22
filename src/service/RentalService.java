package service;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RentalService {

    // Rent Vehicle
    public void rentVehicle(int vehicleId, int customerId, int days) {

        String checkAvailable = "SELECT available FROM vehicles WHERE vehicle_id=?";
        String getPriceQuery  = "SELECT price_per_day FROM vehicles WHERE vehicle_id=?";
        String insertRental   = "INSERT INTO rentals(vehicle_id, customer_id, days, total_cost) VALUES(?, ?, ?, ?)";
        String updateVehicle  = "UPDATE vehicles SET available=false WHERE vehicle_id=?";

        try (Connection conn = DBConnection.getConnection()) {

            // 1. Check if vehicle is available
            PreparedStatement checkPs = conn.prepareStatement(checkAvailable);
            checkPs.setInt(1, vehicleId);
            ResultSet checkRs = checkPs.executeQuery();

            if (!checkRs.next() || !checkRs.getBoolean("available")) {
                System.out.println("Vehicle is not available!");
                return;
            }

            // 2. Fetch price
            PreparedStatement pricePs = conn.prepareStatement(getPriceQuery);
            pricePs.setInt(1, vehicleId);
            ResultSet rs = pricePs.executeQuery();

            if (!rs.next()) {
                System.out.println("Vehicle not found!");
                return;
            }

            double pricePerDay = rs.getDouble("price_per_day");
            double totalCost = pricePerDay * days;

            // 3. Insert rental record
            PreparedStatement rentalPs = conn.prepareStatement(insertRental);
            rentalPs.setInt(1, vehicleId);
            rentalPs.setInt(2, customerId);
            rentalPs.setInt(3, days);
            rentalPs.setDouble(4, totalCost);
            rentalPs.executeUpdate();

            // 4. Mark vehicle as unavailable
            PreparedStatement vehiclePs = conn.prepareStatement(updateVehicle);
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

        String updateVehicle  = "UPDATE vehicles SET available=true WHERE vehicle_id=?";
        String updateRental   = "UPDATE rentals SET return_date=CURDATE() WHERE vehicle_id=? AND return_date IS NULL";

        try (Connection conn = DBConnection.getConnection()) {

            // 1. Mark vehicle as available
            PreparedStatement vehiclePs = conn.prepareStatement(updateVehicle);
            vehiclePs.setInt(1, vehicleId);
            vehiclePs.executeUpdate();

            // 2. Set return date in rental record
            PreparedStatement rentalPs = conn.prepareStatement(updateRental);
            rentalPs.setInt(1, vehicleId);
            rentalPs.executeUpdate();

            System.out.println("Vehicle returned successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
