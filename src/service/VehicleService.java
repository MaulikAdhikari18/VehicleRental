package service;

import database.DBConnection;
import model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleService {

    // Add Vehicle
    public void addVehicle(Vehicle vehicle, String type) {

        String query = "INSERT INTO vehicles(model,type,price_per_day,available) VALUES(?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, vehicle.getModel());
            ps.setString(2, type);
            ps.setDouble(3, vehicle.getPricePerDay());
            ps.setBoolean(4, vehicle.isAvailable());

            ps.executeUpdate();

            System.out.println("Vehicle added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllVehicles() {

        String query = "SELECT * FROM vehicles";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                System.out.println(
                        "ID: " + rs.getInt("vehicle_id") +
                                " Model: " + rs.getString("model") +
                                " Type: " + rs.getString("type") +
                                " Price: " + rs.getDouble("price_per_day") +
                                " Available: " + rs.getBoolean("available"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAvailability(int vehicleId, boolean available) {

        String query = "UPDATE vehicles SET available=? WHERE vehicle_id=?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setBoolean(1, available);
            ps.setInt(2, vehicleId);

            ps.executeUpdate();

            System.out.println("Vehicle availability updated!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
