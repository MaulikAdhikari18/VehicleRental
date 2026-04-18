import service.VehicleService;
import service.RentalService;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Vehicle Rental System Test ===");

        VehicleService vehicleService = new VehicleService();
        RentalService rentalService = new RentalService();

        // View vehicles
        System.out.println("\n--- Available Vehicles ---");
        vehicleService.getAllVehicles();

        // Rent a vehicle
        System.out.println("\n--- Renting Vehicle ---");
        rentalService.rentVehicle(1, 1, 3);

        // Return vehicle
        System.out.println("\n--- Returning Vehicle ---");
        rentalService.returnVehicle(1);
    }
}
