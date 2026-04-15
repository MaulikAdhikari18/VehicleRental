package model;

public class Rental {

    private int rentalId;
    private Customer customer;
    private Vehicle vehicle;
    private int days;
    private double totalCost;
    private String rentalDate;
    private String returnDate;

    public Rental(int rentalId, Customer customer, Vehicle vehicle, int days) {
        this.rentalId = rentalId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
        this.totalCost = calculateTotal();
    }

    public int getRentalId() {
        return rentalId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getDays() {
        return days;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public double calculateTotal() {
        return vehicle.getPricePerDay() * days;
    }
}
