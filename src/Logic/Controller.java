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

	//to get after someone do search
	private ArrayList<Booking> listOfBookings;

	public boolean addUser(String name, String email, String phoneNumber, String drivingLicense, String password) {
		// TODO - implement Controller.addUser
		throw new UnsupportedOperationException();
	}

	public void editUser(String email, String name, String phoneNumber, String drivingLicense, String password, boolean mySelf) {
		userManager.editUser(email, name, phoneNumber, drivingLicense, password);

		//when edited from manageProfile, need to get new reference.
		if (mySelf)
			user = userManager.getUser(email);
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
		bookingManager.addBoking(startDatatime, endDatatime, destination, user, vehicle);
	}

	public boolean removeBooking(Vehicle vehicle) {
		// TODO - implement Controller.removeBooking
		throw new UnsupportedOperationException();
	}

	public boolean removeBooking(User user) {
		// TODO - implement Controller.removeBooking
		throw new UnsupportedOperationException();
	}

	public boolean removeBooking(String email) {
		// TODO - implement Controller.removeBooking
		throw new UnsupportedOperationException();
	}

	public void addVehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available) {
		// TODO - implement Controller.addVehicle
		throw new UnsupportedOperationException();
	}

	public void editVehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available) {
		vehicleManager.editVehicle(make, registerPlate, numOfSeats, fuelType, model , available);
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

	public boolean login(String email, String password)
	{
		return userManager.login(email, password);
	}

	public void search(Timestamp startDatatime, Timestamp endDatatime, String destination, boolean shared)
	{
		listOfBookings = bookingManager.getBookings(startDatatime, endDatatime, destination, shared);
	}

	public ArrayList<Booking> getListOfBookings()
	{
		return listOfBookings;
	}

	public void editMaintenance(boolean operational, boolean lowPressureTires, boolean lightsOnBoard, boolean accident, boolean cleaning, String other, boolean allWentWell)
	{
		selectedVehicle.getMaintenance().edit(operational, lowPressureTires, lightsOnBoard, accident, cleaning, other, allWentWell);
	}

	public void deliver()
	{
		//deliver
	}
}