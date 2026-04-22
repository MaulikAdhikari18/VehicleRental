package gui;

import java.sql.*;
import database.DBConnection;
import javax.swing.*;
import service.RentalService;
import service.CustomerService;

public class RentVehicleForm {

    public RentVehicleForm() {

        JFrame frame = new JFrame("Rent Vehicle");

        frame.setSize(420, 360);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel title = new JLabel("Rent a Vehicle", SwingConstants.CENTER);
        title.setBounds(0, 15, 420, 25);
        title.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));

        JLabel customerLabel = new JLabel("Customer ID:");
        customerLabel.setBounds(50, 60, 110, 25);

        JTextField customerField = new JTextField();
        customerField.setBounds(170, 60, 160, 25);

        JLabel vehicleLabel = new JLabel("Select Vehicle:");
        vehicleLabel.setBounds(50, 105, 120, 25);

        JComboBox<String> vehicleDropdown = new JComboBox<>();
        vehicleDropdown.setBounds(170, 105, 160, 25);

        // Load available vehicles
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT vehicle_id, model, type, price_per_day FROM vehicles WHERE available=true ORDER BY vehicle_id");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("vehicle_id");
                String model = rs.getString("model");
                String type = rs.getString("type");
                double price = rs.getDouble("price_per_day");
                vehicleDropdown.addItem(id + " - " + model + " (" + type + ") ₹" + price + "/day");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel daysLabel = new JLabel("Number of Days:");
        daysLabel.setBounds(50, 150, 120, 25);

        JTextField daysField = new JTextField();
        daysField.setBounds(170, 150, 160, 25);

        JLabel totalLabel = new JLabel("Total Cost: ₹ --");
        totalLabel.setBounds(50, 195, 300, 25);
        totalLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 13));

        // Calculate total on days input change
        daysField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                try {
                    int days = Integer.parseInt(daysField.getText().trim());
                    String selected = (String) vehicleDropdown.getSelectedItem();
                    if (selected != null) {
                        // Extract price from dropdown label
                        String priceStr = selected.split("₹")[1].split("/")[0];
                        double price = Double.parseDouble(priceStr);
                        totalLabel.setText("Total Cost: ₹ " + (price * days));
                    }
                } catch (Exception ex) {
                    totalLabel.setText("Total Cost: ₹ --");
                }
            }
        });

        JButton rentBtn = new JButton("Confirm Rent");
        rentBtn.setBounds(100, 250, 200, 32);
        rentBtn.setFocusPainted(false);

        rentBtn.addActionListener(e -> {
            try {
                int customerId = Integer.parseInt(customerField.getText().trim());
                int days = Integer.parseInt(daysField.getText().trim());

                // Validate customer exists
                CustomerService customerService = new CustomerService();
                if (!customerService.customerExists(customerId)) {
                    JOptionPane.showMessageDialog(frame, "Customer ID not found! Please add customer first.");
                    return;
                }

                // Extract vehicle ID from dropdown
                String selected = (String) vehicleDropdown.getSelectedItem();
                if (selected == null) {
                    JOptionPane.showMessageDialog(frame, "No available vehicles!");
                    return;
                }
                int vehicleId = Integer.parseInt(selected.split(" - ")[0]);

                RentalService service = new RentalService();
                service.rentVehicle(vehicleId, customerId, days);

                JOptionPane.showMessageDialog(frame, "Vehicle Rented Successfully!\n" + totalLabel.getText());
                frame.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid Customer ID and Days.");
            }
        });

        frame.add(title);
        frame.add(customerLabel);
        frame.add(customerField);
        frame.add(vehicleLabel);
        frame.add(vehicleDropdown);
        frame.add(daysLabel);
        frame.add(daysField);
        frame.add(totalLabel);
        frame.add(rentBtn);

        frame.setVisible(true);
    }
}
