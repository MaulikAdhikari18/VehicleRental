package gui;

import javax.swing.*;
import service.VehicleService;

public class AddVehicleForm {

    public AddVehicleForm() {

        JFrame frame = new JFrame("Add Vehicle");

        frame.setSize(400, 320);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel title = new JLabel("Add New Vehicle", SwingConstants.CENTER);
        title.setBounds(0, 15, 400, 25);
        title.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));

        JLabel modelLabel = new JLabel("Model:");
        modelLabel.setBounds(50, 60, 100, 25);

        JTextField modelField = new JTextField();
        modelField.setBounds(160, 60, 170, 25);

        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setBounds(50, 105, 100, 25);

        String[] types = {"Car", "Bike", "Scooty"};
        JComboBox<String> typeDropdown = new JComboBox<>(types);
        typeDropdown.setBounds(160, 105, 170, 25);

        JLabel priceLabel = new JLabel("Price/Day (₹):");
        priceLabel.setBounds(50, 150, 110, 25);

        JTextField priceField = new JTextField();
        priceField.setBounds(160, 150, 170, 25);

        JButton addBtn = new JButton("Add Vehicle");
        addBtn.setBounds(120, 210, 150, 32);
        addBtn.setFocusPainted(false);

        addBtn.addActionListener(e -> {
            String model = modelField.getText().trim();
            String type = (String) typeDropdown.getSelectedItem();
            String priceText = priceField.getText().trim();

            if (model.isEmpty() || priceText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
                return;
            }

            try {
                double price = Double.parseDouble(priceText);
                if (price <= 0) throw new NumberFormatException();

                VehicleService service = new VehicleService();
                boolean success = service.addVehicle(model, type, price);

                if (success) {
                    JOptionPane.showMessageDialog(frame, "Vehicle added successfully!");
                    modelField.setText("");
                    priceField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to add vehicle.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Enter a valid price.");
            }
        });

        frame.add(title);
        frame.add(modelLabel);
        frame.add(modelField);
        frame.add(typeLabel);
        frame.add(typeDropdown);
        frame.add(priceLabel);
        frame.add(priceField);
        frame.add(addBtn);

        frame.setVisible(true);
    }
}
