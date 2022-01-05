package Logic;

import Logic.Data.Booking.Booking;
import Logic.Data.Booking.BookingManager;
import Logic.Data.User.User;
import Logic.Data.User.UserManager;
import Logic.Data.Vehicle.Vehicle;
import Logic.Data.Vehicle.VehicleManager;
import Utils.EntityType;
import Utils.JSONManager;

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

	public Controller() {
		userManager = (UserManager) JSONManager.readFromFile(EntityType.USER);
		bookingManager = (BookingManager) JSONManager.readFromFile(EntityType.BOOKING);
		vehicleManager = (VehicleManager) JSONManager.readFromFile(EntityType.VEHICLE);
	}

	public void addUser(String name, String email, String phoneNumber, String drivingLicense, String password) {
		loadUserManager();
		userManager.addUser(name,email,phoneNumber,drivingLicense, password);
		saveUserManager();
	}

	public void editUser(String email, String name, String phoneNumber, String drivingLicense, String password, boolean mySelf) {
		userManager.editUser(email, name, phoneNumber, drivingLicense, password);

		//when edited from manageProfile, need to get new reference.
		if (mySelf)
			user = userManager.getUser(email);
	}

	public User getUser() {
		return this.user;
	}

	public void removeUser(String email) {
		userManager.removeUser(email);
	}

	public ArrayList<User> listUsers() {
		return userManager.listUsers();
	}

	private void saveUserManager() {
		JSONManager.writeToFile(userManager, EntityType.USER);
	}

	private void loadUserManager() {
		userManager = (UserManager) JSONManager.readFromFile(EntityType.USER);
	}

	public ArrayList<Booking> getBookings(Timestamp startDatatime, Timestamp endDatatime, String destination, boolean shared) {
		return bookingManager.getBookings(startDatatime,endDatatime,destination,shared);
	}

	public void addBooking(Timestamp startDatatime, Timestamp endDatatime, String destination, User user, Vehicle vehicle) {
		loadBookingManager();
		bookingManager.addBooking(startDatatime, endDatatime, destination, user, vehicle);
		saveBookingManager();
	}

	public boolean removeBooking(Timestamp startDatatime, Vehicle vehicle) {
		loadBookingManager();
		boolean result = bookingManager.removeBooking(startDatatime, vehicle);
		saveBookingManager();
		return result;
	}

	public boolean removeBooking(Timestamp startDatatime, String email) {
		loadBookingManager();
		boolean result = bookingManager.removeBooking(startDatatime, email);
		saveBookingManager();
		return result;
	}

	private void saveBookingManager() {
		JSONManager.writeToFile(bookingManager, EntityType.BOOKING);
	}

	private void loadBookingManager() {
		bookingManager = (BookingManager) JSONManager.readFromFile(EntityType.BOOKING);
	}

	public void addVehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available) {
		loadVehicleManager();
		vehicleManager.addVehicle(make,registerPlate,numOfSeats,fuelType,model,available);
		saveVehicleManager();
	}

	public void editVehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available) {
		loadVehicleManager();
		vehicleManager.editVehicle(make, registerPlate, numOfSeats, fuelType, model , available);
		saveVehicleManager();
	}

	public Vehicle getVehicle(String registerPlate) {
		return vehicleManager.getVehicle(registerPlate);
	}

	public boolean removeVehicle(String registerPlate) {
		loadVehicleManager();
		boolean result = vehicleManager.removeVehicle(registerPlate);
		saveVehicleManager();
		return result;
	}

	public ArrayList<Vehicle> listVehicles() {
		return vehicleManager.listVehicles();
	}

	public Vehicle getSelectedVehicle() {
		return this.selectedVehicle;
	}

	public void setSelectedVehicle(String registrationPlate) {
		selectedVehicle = vehicleManager.getVehicle(registrationPlate);
	}

	private void saveVehicleManager() {
		JSONManager.writeToFile(vehicleManager, EntityType.VEHICLE);
	}

	private void loadVehicleManager() {
		vehicleManager = (VehicleManager) JSONManager.readFromFile(EntityType.VEHICLE);
	}

	public Booking getSelectedBooking() {
		return this.selectedBooking;
	}

	public void setSelectedBooking(String registrationPlate) {
		selectedBooking = bookingManager.getBooking(registrationPlate);
	}

	public boolean login(String email, String password) {
		if (userManager.login(email, password)) {
			 user = userManager.getUser(email);
			 return true;
		 }
		 return false;
	}

	public void search(Timestamp startDatatime, Timestamp endDatatime, String destination, boolean shared) {
		listOfBookings = bookingManager.getBookings(startDatatime, endDatatime, destination, shared);
	}

	public ArrayList<Booking> getListOfBookings()
	{
		return listOfBookings;
	}

	public void editMaintenance(boolean operational, boolean lowPressureTires, boolean lightsOnBoard, boolean accident, boolean cleaning, String other, boolean allWentWell) {
		loadVehicleManager();
		selectedVehicle.getMaintenance().edit(operational, lowPressureTires, lightsOnBoard, accident, cleaning, other, allWentWell);
		saveVehicleManager();
	}

	public void deliver() {
		loadBookingManager();
		bookingManager.removeBooking(null, user.getEmail());
		saveBookingManager();
	}
}