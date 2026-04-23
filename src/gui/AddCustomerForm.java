package gui;

import javax.swing.*;
import service.CustomerService;

public class AddCustomerForm {

    public AddCustomerForm() {

        JFrame frame = new JFrame("Add Customer");

        frame.setSize(400, 320);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel title = new JLabel("Add New Customer", SwingConstants.CENTER);
        title.setBounds(0, 15, 400, 25);
        title.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 60, 100, 25);

        JTextField nameField = new JTextField();
        nameField.setBounds(160, 60, 170, 25);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 105, 100, 25);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(160, 105, 170, 25);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 150, 100, 25);

        JTextField emailField = new JTextField();
        emailField.setBounds(160, 150, 170, 25);

        JButton addBtn = new JButton("Add Customer");
        addBtn.setBounds(120, 210, 150, 32);
        addBtn.setFocusPainted(false);

        addBtn.addActionListener(e -> {
            String name  = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();

            if (name.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Name and Phone are required.");
                return;
            }

            CustomerService service = new CustomerService();
            boolean success = service.addCustomer(name, phone, email);

            if (success) {
                JOptionPane.showMessageDialog(frame, "Customer added successfully!");
                nameField.setText("");
                phoneField.setText("");
                emailField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to add customer.");
            }
        });

        frame.add(title);
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(phoneLabel);
        frame.add(phoneField);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(addBtn);

        frame.setVisible(true);
    }
}
