package model;

public abstract class Vehicle {

    private int vehicleId;
    private String model;
    private double pricePerDay;
    private boolean available;

    public Vehicle(int vehicleId, String model, double pricePerDay, boolean available) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.available = available;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getModel() {
        return model;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract void displayDetails();
}