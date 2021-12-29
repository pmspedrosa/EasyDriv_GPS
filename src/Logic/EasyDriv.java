package Logic;

import Logic.Data.Booking.Booking;
import Logic.Data.User.User;
import Logic.Data.Vehicle.Vehicle;
import Logic.States.IState;
import Logic.States.SystemState;

import java.security.Timestamp;
import java.util.ArrayList;

public class EasyDriv {

	private Controller controller;
	private IState state;

	public EasyDriv() {
		// TODO - implement EasyDriv.EasyDriv
		throw new UnsupportedOperationException();
	}

	public void login(String email, String password) {
		// TODO - implement EasyDriv.login
		throw new UnsupportedOperationException();
	}

	public void manageUsers() {
		// TODO - implement EasyDriv.manageUsers
		throw new UnsupportedOperationException();
	}

	public void addUser() {
		// TODO - implement EasyDriv.addUser
		throw new UnsupportedOperationException();
	}

	public void addUser(String name, String email, String phoneNumber, String drivingLicense) {
		// TODO - implement EasyDriv.addUser
		throw new UnsupportedOperationException();
	}

	public void editUser() {
		// TODO - implement EasyDriv.editUser
		throw new UnsupportedOperationException();
	}

	public void search(Timestamp startDatatime, Timestamp endDatatime, String destination, boolean shared) {
		// TODO - implement EasyDriv.search
		throw new UnsupportedOperationException();
	}

	public SystemState getActualState() {
		// TODO - implement EasyDriv.getActualState
		throw new UnsupportedOperationException();
	}

	public void editUser(String email, String nome, String phoneNumber, String drivingLicense, String password) {
		// TODO - implement EasyDriv.editUser
		throw new UnsupportedOperationException();
	}

	public void manageBookings() {
		// TODO - implement EasyDriv.manageBookings
		throw new UnsupportedOperationException();
	}

	public void booking(Timestamp startDatatime, Timestamp endDatatime, String destination, User user, Vehicle vehicle) {
		// TODO - implement EasyDriv.booking
		throw new UnsupportedOperationException();
	}

	public void deliver() {
		// TODO - implement EasyDriv.deliver
		throw new UnsupportedOperationException();
	}

	public void manageVehicles() {
		// TODO - implement EasyDriv.manageVehicles
		throw new UnsupportedOperationException();
	}

	public void addVehicle() {
		// TODO - implement EasyDriv.addVehicle
		throw new UnsupportedOperationException();
	}

	public void addVehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available) {
		// TODO - implement EasyDriv.addVehicle
		throw new UnsupportedOperationException();
	}

	public void editVehicle() {
		// TODO - implement EasyDriv.editVehicle
		throw new UnsupportedOperationException();
	}

	public void editVehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available) {
		// TODO - implement EasyDriv.editVehicle
		throw new UnsupportedOperationException();
	}

	public void checkMaintenance() {
		// TODO - implement EasyDriv.checkMaintenance
		throw new UnsupportedOperationException();
	}

	public void editMaintenance(boolean operational, boolean lowPressureTires, boolean lightsOnBoard, boolean accident, boolean cleaning, String other, boolean allWentWell) {
		// TODO - implement EasyDriv.editMaintenance
		throw new UnsupportedOperationException();
	}

	public void remove(String key) {
		// TODO - implement EasyDriv.remove
		throw new UnsupportedOperationException();
	}

	public void confirm() {
		// TODO - implement EasyDriv.confirm
		throw new UnsupportedOperationException();
	}

	public void cancel() {
		// TODO - implement EasyDriv.cancel
		throw new UnsupportedOperationException();
	}

	public void exit() {
		// TODO - implement EasyDriv.exit
		throw new UnsupportedOperationException();
	}

	public User getUser(String email) {
		// TODO - implement EasyDriv.getUser
		throw new UnsupportedOperationException();
	}

	public ArrayList<User> listUsers() {
		// TODO - implement EasyDriv.listUsers
		throw new UnsupportedOperationException();
	}

	public ArrayList<Booking> getBookings(Timestamp startDatatime, Timestamp endDatatime, String destination, boolean shared) {
		// TODO - implement EasyDriv.getBookings
		throw new UnsupportedOperationException();
	}

	public Vehicle getVehicle(String registerPlate) {
		// TODO - implement EasyDriv.getVehicle
		throw new UnsupportedOperationException();
	}

	public ArrayList<Vehicle> listVehicles() {
		// TODO - implement EasyDriv.listVehicles
		throw new UnsupportedOperationException();
	}

	public void setSelectedVehicle(String registrationPlate) {
		// TODO - implement EasyDriv.setSelectedVehicle
		throw new UnsupportedOperationException();
	}

	public Vehicle getSelectedVehicle() {
		// TODO - implement EasyDriv.getSelectedVehicle
		throw new UnsupportedOperationException();
	}

	public void setSelectedBooking(String registrationPlate) {
		// TODO - implement EasyDriv.setSelectedBooking
		throw new UnsupportedOperationException();
	}

	public Booking getSelectedBooking() {
		// TODO - implement EasyDriv.getSelectedBooking
		throw new UnsupportedOperationException();
	}

}