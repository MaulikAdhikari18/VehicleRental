package model;

public class Car extends Vehicle {

    private int seats;

    public Car(int vehicleId, String model, double pricePerDay, boolean available, int seats) {
        super(vehicleId, model, pricePerDay, available);
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }

    @Override
    public void displayDetails() {
        System.out.println("Car Model: " + getModel() + " Seats: " + seats);
    }
}
