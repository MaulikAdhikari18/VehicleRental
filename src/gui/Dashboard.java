package gui;

import javax.swing.*;
import java.awt.*;

public class Dashboard {

    public Dashboard() {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Vehicle Rental System");

        frame.setSize(520, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(240, 240, 240));

        // Title
        JLabel title = new JLabel("Vehicle Rental Dashboard", SwingConstants.CENTER);
        title.setBounds(60, 25, 390, 35);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        // --- Vehicle Section Label ---
        JLabel vehicleSection = new JLabel("── Vehicles ──");
        vehicleSection.setBounds(185, 80, 150, 20);
        vehicleSection.setFont(new Font("Arial", Font.ITALIC, 12));
        vehicleSection.setForeground(Color.GRAY);

        JButton viewVehicles = new JButton("View Vehicles");
        viewVehicles.setBounds(170, 110, 170, 32);
        viewVehicles.setFocusPainted(false);
        viewVehicles.addActionListener(e -> new ViewVehicles());

        JButton addVehicle = new JButton("Add Vehicle");
        addVehicle.setBounds(170, 152, 170, 32);
        addVehicle.setFocusPainted(false);
        addVehicle.addActionListener(e -> new AddVehicleForm());

        // --- Customer Section Label ---
        JLabel customerSection = new JLabel("── Customers ──");
        customerSection.setBounds(178, 200, 160, 20);
        customerSection.setFont(new Font("Arial", Font.ITALIC, 12));
        customerSection.setForeground(Color.GRAY);

        JButton viewCustomers = new JButton("View Customers");
        viewCustomers.setBounds(170, 230, 170, 32);
        viewCustomers.setFocusPainted(false);
        viewCustomers.addActionListener(e -> new ViewCustomers());

        JButton addCustomer = new JButton("Add Customer");
        addCustomer.setBounds(170, 272, 170, 32);
        addCustomer.setFocusPainted(false);
        addCustomer.addActionListener(e -> new AddCustomerForm());

        // --- Rental Section Label ---
        JLabel rentalSection = new JLabel("── Rentals ──");
        rentalSection.setBounds(185, 320, 150, 20);
        rentalSection.setFont(new Font("Arial", Font.ITALIC, 12));
        rentalSection.setForeground(Color.GRAY);

        JButton rentVehicle = new JButton("Rent Vehicle");
        rentVehicle.setBounds(170, 350, 170, 32);
        rentVehicle.setFocusPainted(false);
        rentVehicle.addActionListener(e -> new RentVehicleForm());

        JButton returnVehicle = new JButton("Return Vehicle");
        returnVehicle.setBounds(170, 392, 170, 32);
        returnVehicle.setFocusPainted(false);
        returnVehicle.addActionListener(e -> new ReturnVehicleForm());

        JButton viewRentals = new JButton("View Rental History");
        viewRentals.setBounds(155, 434, 200, 32);
        viewRentals.setFocusPainted(false);
        viewRentals.addActionListener(e -> new ViewRentals());

        frame.add(title);
        frame.add(vehicleSection);
        frame.add(viewVehicles);
        frame.add(addVehicle);
        frame.add(customerSection);
        frame.add(viewCustomers);
        frame.add(addCustomer);
        frame.add(rentalSection);
        frame.add(rentVehicle);
        frame.add(returnVehicle);
        frame.add(viewRentals);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}
