package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import database.DBConnection;

import java.sql.*;

public class ViewVehicles {

    public ViewVehicles() {

        JFrame frame = new JFrame("View Vehicles");

        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Model", "Type", "Price", "Available"});

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM vehicles");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("vehicle_id"),
                        rs.getString("model"),
                        rs.getString("type"),
                        rs.getDouble("price_per_day"),
                        rs.getBoolean("available")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 50, 520, 250);

        frame.add(scrollPane);

        frame.setVisible(true);
    }
}