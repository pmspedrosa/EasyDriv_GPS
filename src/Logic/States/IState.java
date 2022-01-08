package Logic.States;

import Logic.Controller;
import Logic.Data.Booking.Booking;
import Logic.Data.Vehicle.Vehicle;

import java.sql.Timestamp;

public interface IState {

	IState login(String email, String password);

	IState logout();

	IState manageUsers();

	IState addUser(String name, String email, String phoneNumber, String drivingLicense, String password);

	IState editUser();

	IState manageBookings();

	IState search(Timestamp startDatatime, Timestamp endDatatime, String destination, int nrOfSeats);

	IState search(Timestamp startDatatime, Timestamp endDatatime, String destination, String user, String regPlate);

	IState booking(Booking booking);

	IState deliver(Booking booking);

	IState manageVehicles();

	IState addVehicle();

	IState editVehicle();

	IState checkMaintenance();

	IState remove(Timestamp timestamp, String key);

	IState remove(String key);

	IState confirm();

	IState cancel();

	SystemState getActualState();

	IState addUser();

	IState editUser(String email, String nome, String phoneNumber, String drivingLicense, String password);

	IState addVehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available);

	IState editVehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available);

	IState editMaintenance(Vehicle vehicle);

	Controller getController();

	IState editBooking();

	IState editBooking(Booking booking);
}