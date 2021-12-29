package Logic;

import Logic.Data.Booking.Booking;
import Logic.Data.Booking.BookingManager;
import Logic.Data.User.User;
import Logic.Data.User.UserManager;
import Logic.Data.Vehicle.Vehicle;
import Logic.Data.Vehicle.VehicleManager;
import Logic.States.IState;
import Logic.States.SystemState;

import java.security.Timestamp;
import java.util.ArrayList;

public class Controller {

	private User user;
	private UserManager userManager;
	private BookingManager bookingManager;
	private VehicleManager vehicleManager;
	private Vehicle selectedVehicle;
	private Booking selectedBooking;

	public boolean addUser(String name, String email, String phoneNumber, String drivingLicense) {
		// TODO - implement Controller.addUser
		throw new UnsupportedOperationException();
	}

	public boolean editUser(String name, String email, String phoneNumber, String drivingLicense) {
		// TODO - implement Controller.editUser
		throw new UnsupportedOperationException();
	}


	public User getUser(String email) {
		return this.user;
	}


	public boolean removeUser(String email) {
		// TODO - implement Controller.removeUser
		throw new UnsupportedOperationException();
	}

	public ArrayList<User> listUsers() {
		// TODO - implement Controller.listUsers
		throw new UnsupportedOperationException();
	}

	public ArrayList<Booking> getBookings(Timestamp startDatatime, Timestamp endDatatime, String destination, boolean shared) {
		// TODO - implement Controller.getBookings
		throw new UnsupportedOperationException();
	}

	public void addBoking(Timestamp startDatatime, Timestamp endDatatime, String destination, User user, Vehicle vehicle) {
		// TODO - implement Controller.addBoking
		throw new UnsupportedOperationException();
	}

	public boolean removeBooking(Vehicle vehicle) {
		// TODO - implement Controller.removeBooking
		throw new UnsupportedOperationException();
	}

	public boolean removeBooking(User user) {
		// TODO - implement Controller.removeBooking
		throw new UnsupportedOperationException();
	}

	public void addVehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available) {
		// TODO - implement Controller.addVehicle
		throw new UnsupportedOperationException();
	}

	public Vehicle getVehicle(String registerPlate) {
		// TODO - implement Controller.getVehicle
		throw new UnsupportedOperationException();
	}

	public boolean removeVehicle(String registerPlate) {
		// TODO - implement Controller.removeVehicle
		throw new UnsupportedOperationException();
	}

	public ArrayList<Vehicle> listVehicles() {
		// TODO - implement Controller.listVehicles
		throw new UnsupportedOperationException();
	}

	public void setState(IState state) {
		// TODO - implement Controller.setState
		throw new UnsupportedOperationException();
	}

	public Vehicle getSelectedVehicle() {
		return this.selectedVehicle;
	}

	public void setSelectedVehicle(String registrationPlate) {
		// TODO - implement Controller.setSelectedVehicle
		throw new UnsupportedOperationException();
	}

	public Booking getSelectedBooking() {
		return this.selectedBooking;
	}

	public void setSelectedBooking(String registrationPlate) {
		// TODO - implement Controller.setSelectedBooking
		throw new UnsupportedOperationException();
	}

	public SystemState getActualState() {
		// TODO - implement Controller.getActualState
		throw new UnsupportedOperationException();
	}

}