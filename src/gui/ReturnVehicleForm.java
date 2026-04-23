package gui;

import java.sql.*;
import database.DBConnection;
import javax.swing.*;
import service.RentalService;

public class ReturnVehicleForm {

    public ReturnVehicleForm() {

        JFrame frame = new JFrame("Return Vehicle");

        frame.setSize(420, 320);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel title = new JLabel("Return a Vehicle", SwingConstants.CENTER);
        title.setBounds(0, 15, 420, 25);
        title.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));

        JLabel vehicleLabel = new JLabel("Select Vehicle:");
        vehicleLabel.setBounds(50, 70, 120, 25);

        JComboBox<String> vehicleDropdown = new JComboBox<>();
        vehicleDropdown.setBounds(170, 70, 180, 25);

        // Load rented (not available) vehicles
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT v.vehicle_id, v.model, v.type, c.name AS customer_name " +
                     "FROM vehicles v " +
                     "JOIN rentals r ON v.vehicle_id = r.vehicle_id " +
                     "JOIN customers c ON r.customer_id = c.customer_id " +
                     "WHERE v.available = false AND r.return_date IS NULL " +
                     "ORDER BY v.vehicle_id");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("vehicle_id");
                String model = rs.getString("model");
                String type = rs.getString("type");
                String customer = rs.getString("customer_name");
                vehicleDropdown.addItem(id + " - " + model + " (" + type + ") | Rented by: " + customer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel infoLabel = new JLabel("This will mark the vehicle as available.");
        infoLabel.setBounds(60, 120, 300, 20);
        infoLabel.setFont(new java.awt.Font("Arial", java.awt.Font.ITALIC, 11));
        infoLabel.setForeground(java.awt.Color.GRAY);

        JButton returnBtn = new JButton("Confirm Return");
        returnBtn.setBounds(110, 180, 190, 32);
        returnBtn.setFocusPainted(false);

        returnBtn.addActionListener(e -> {
            try {
                String selected = (String) vehicleDropdown.getSelectedItem();

                if (selected == null) {
                    JOptionPane.showMessageDialog(frame, "No rented vehicles found!");
                    return;
                }

                int vehicleId = Integer.parseInt(selected.split(" - ")[0]);

                RentalService service = new RentalService();
                service.returnVehicle(vehicleId);

                JOptionPane.showMessageDialog(frame, "Vehicle Returned Successfully!");
                frame.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error returning vehicle.");
            }
        });

        frame.add(title);
        frame.add(vehicleLabel);
        frame.add(vehicleDropdown);
        frame.add(infoLabel);
        frame.add(returnBtn);

        frame.setVisible(true);
    }
}
