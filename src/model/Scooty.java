package model;

public class Scooty extends Vehicle {

    public Scooty(int vehicleId, String model, double pricePerDay, boolean available) {
        super(vehicleId, model, pricePerDay, available);
    }

    @Override
    public void displayDetails() {
        System.out.println("Scooty Model: " + getModel());
    }
}
