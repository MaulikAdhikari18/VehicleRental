package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import database.DBConnection;
import service.VehicleService;

import java.sql.*;

public class ViewVehicles {

    public ViewVehicles() {

        JFrame frame = new JFrame("View Vehicles");

        frame.setSize(650, 450);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("All Vehicles", SwingConstants.CENTER);
        title.setBounds(0, 10, 650, 25);
        title.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(new String[]{"ID", "Model", "Type", "Price/Day (₹)", "Available"});

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM vehicles ORDER BY vehicle_id");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("vehicle_id"),
                        rs.getString("model"),
                        rs.getString("type"),
                        rs.getDouble("price_per_day"),
                        rs.getBoolean("available") ? "Yes" : "No"
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JTable table = new JTable(model);
        table.setRowHeight(22);
        table.getTableHeader().setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 50, 580, 300);

        // Delete button
        JButton deleteBtn = new JButton("Delete Selected");
        deleteBtn.setBounds(240, 370, 160, 30);
        deleteBtn.setFocusPainted(false);
        deleteBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a vehicle to delete.");
                return;
            }
            int vehicleId = (int) model.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(frame,
                    "Delete Vehicle ID " + vehicleId + "?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                VehicleService service = new VehicleService();
                if (service.deleteVehicle(vehicleId)) {
                    model.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(frame, "Vehicle deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Could not delete vehicle.");
                }
            }
        });

        frame.add(title);
        frame.add(scrollPane);
        frame.add(deleteBtn);

        frame.setVisible(true);
    }
}
