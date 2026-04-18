package gui;

import javax.swing.*;
import gui.RentVehicleForm;
import gui.ViewVehicles;

public class Dashboard {

    public Dashboard() {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Vehicle Rental System");

        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new java.awt.Color(240, 240, 240));

        JLabel title = new JLabel("Vehicle Rental Dashboard");
        title.setBounds(140, 30, 250, 30);
        title.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));

        JButton viewVehicles = new JButton("View Vehicles");
        viewVehicles.setBounds(170, 100, 150, 30);
        viewVehicles.setFocusPainted(false);
        viewVehicles.addActionListener(e -> new ViewVehicles());

        JButton rentVehicle = new JButton("Rent Vehicle");
        rentVehicle.setBounds(170, 150, 150, 30);
        rentVehicle.setFocusPainted(false);
        rentVehicle.addActionListener(e -> new RentVehicleForm());

        JButton returnVehicle = new JButton("Return Vehicle");
        returnVehicle.setBounds(170, 200, 150, 30);
        returnVehicle.setFocusPainted(false);

        frame.add(title);
        frame.add(viewVehicles);
        frame.add(rentVehicle);
        frame.add(returnVehicle);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}