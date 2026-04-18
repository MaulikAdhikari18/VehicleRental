package gui;

import java.sql.*;
import database.DBConnection;
import javax.swing.*;
import service.RentalService;

public class RentVehicleForm {

    public RentVehicleForm() {

        JFrame frame = new JFrame("Rent Vehicle");

        frame.setSize(400, 400);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel customerLabel = new JLabel("Customer ID:");
        customerLabel.setBounds(50, 50, 100, 25);

        JTextField customerField = new JTextField();
        customerField.setBounds(160, 50, 150, 25);

        JLabel vehicleLabel = new JLabel("Select Vehicle:");
        vehicleLabel.setBounds(50, 100, 120, 25);

        JComboBox<String> vehicleDropdown = new JComboBox<>();
        vehicleDropdown.setBounds(160, 100, 150, 25);

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn
                        .prepareStatement("SELECT vehicle_id, model FROM vehicles WHERE available=true");
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("vehicle_id");
                String model = rs.getString("model");

                // Store both ID + name
                vehicleDropdown.addItem(id + " - " + model);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel daysLabel = new JLabel("Days:");
        daysLabel.setBounds(50, 150, 100, 25);

        JTextField daysField = new JTextField();
        daysField.setBounds(160, 150, 150, 25);

        JButton rentButton = new JButton("Rent");
        rentButton.setBounds(60, 220, 120, 30);

        rentButton.addActionListener(e -> {
            try {
                int customerId = Integer.parseInt(customerField.getText());
                int days = Integer.parseInt(daysField.getText());

                // Extract ID from dropdown
                String selected = (String) vehicleDropdown.getSelectedItem();
                int vehicleId = Integer.parseInt(selected.split(" - ")[0]);

                RentalService service = new RentalService();
                service.rentVehicle(vehicleId, customerId, days);

                JOptionPane.showMessageDialog(frame, "Vehicle Rented Successfully!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Input!");
            }
        });

        JButton returnButton = new JButton("Return");
        returnButton.setBounds(200, 220, 120, 30);
        returnButton.setFocusPainted(false);

        returnButton.addActionListener(e -> {
            try {
                String selected = (String) vehicleDropdown.getSelectedItem();
                int vehicleId = Integer.parseInt(selected.split(" - ")[0]);

                RentalService service = new RentalService();
                service.returnVehicle(vehicleId);

                JOptionPane.showMessageDialog(frame, "Vehicle Returned Successfully!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Select a valid vehicle!");
            }
        });

        frame.add(customerLabel);
        frame.add(customerField);
        frame.add(vehicleLabel);
        frame.add(daysLabel);
        frame.add(daysField);
        frame.add(rentButton);
        frame.add(returnButton);
        frame.add(vehicleLabel);
        frame.add(vehicleDropdown);

        frame.setVisible(true);
    }
}