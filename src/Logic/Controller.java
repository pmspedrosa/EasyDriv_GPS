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
		return userManager.addUser(name,email,phoneNumber,drivingLicense, password);
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
		return userManager.removeUser(email);
	}

	public ArrayList<User> listUsers() {
		return userManager.listUsers();
	}

	public ArrayList<Booking> getBookings(Timestamp startDatatime, Timestamp endDatatime, String destination, boolean shared) {
		return bookingManager.getBookings(startDatatime,endDatatime,destination,shared);
	}

	public void addBoking(Timestamp startDatatime, Timestamp endDatatime, String destination, User user, Vehicle vehicle) {
		bookingManager.addBoking(startDatatime, endDatatime, destination, user, vehicle);
	}

	public boolean removeBooking(Vehicle vehicle) {
		return bookingManager.removeBooking(vehicle);
	}

	public boolean removeBooking(User user) {
		return bookingManager.removeBooking(user);
	}

//	public boolean removeBooking(String email) {
//		return bookingManager.
//	}

	public void addVehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available) {
		vehicleManager.addVehicle(make,registerPlate,numOfSeats,fuelType,model,available);
	}

	public void editVehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available) {
		vehicleManager.editVehicle(make, registerPlate, numOfSeats, fuelType, model , available);
	}

	public Vehicle getVehicle(String registerPlate) {
		return vehicleManager.getVehicle(registerPlate);
	}

	public boolean removeVehicle(String registerPlate) {
		return vehicleManager.removeVehicle(registerPlate);

	}

	public ArrayList<Vehicle> listVehicles() {
		return vehicleManager.listVehicles();
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