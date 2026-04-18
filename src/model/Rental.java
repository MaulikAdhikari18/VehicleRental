package model;

public class Rental {

    private int rentalId;
    private Customer customer;
    private Vehicle vehicle;
    private int days;

    public Rental(int rentalId, Customer customer, Vehicle vehicle, int days) {
        this.rentalId = rentalId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
    }

    public double calculateTotal() {
        return vehicle.getPricePerDay() * days;
    }
}