package model;

public class Bike extends Vehicle {

    private int engineCC;

    public Bike(int vehicleId, String model, double pricePerDay, boolean available, int engineCC) {
        super(vehicleId, model, pricePerDay, available);
        this.engineCC = engineCC;
    }

    @Override
    public void displayDetails() {
        System.out.println("Bike Model: " + getModel() + " Engine: " + engineCC + "cc");
    }
}