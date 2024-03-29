package Logic;

import Logic.Data.Booking.Booking;
import Logic.Data.Booking.BookingManager;
import Logic.Data.User.User;
import Logic.Data.User.UserManager;
import Logic.Data.Vehicle.Vehicle;
import Logic.Data.Vehicle.VehicleManager;
import Utils.EntityType;
import Utils.JSONManager;

import java.sql.Timestamp;
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
		loadUserManager();
		loadBookingManager();
		loadVehicleManager();
	}

	public void addUser(String name, String email, String phoneNumber, String drivingLicense, String password) {
		loadUserManager();
		userManager.addUser(name,email,phoneNumber,drivingLicense, password);
		saveUserManager();
	}

	public void editUser(String email, String name, String phoneNumber, String drivingLicense, String password, boolean mySelf) {
		loadUserManager();
		userManager.editUser(email, name, phoneNumber, drivingLicense, password);

		//when edited from manageProfile, need to get new reference.
		if (mySelf)
			user = userManager.getUser(email);
		saveUserManager();
	}

	public User getUser() {
		loadUserManager();
		user = userManager.getUser(user.getEmail());
		return this.user;
	}

	public void removeUser(String email) {
		loadUserManager();
		userManager.removeUser(email);
		saveUserManager();
	}

	public ArrayList<User> listUsers() {
		loadUserManager();
		return userManager.listUsers();
	}

	private void saveUserManager() {
		JSONManager.writeToFile(userManager, EntityType.USER);
	}

	private void loadUserManager() {
		userManager = (UserManager) JSONManager.readFromFile(EntityType.USER);
		if (userManager == null) userManager = new UserManager();
	}

	public ArrayList<Booking> getBookings(Timestamp startDatatime, Timestamp endDatatime, String destination, boolean shared) {
		loadBookingManager();
		return bookingManager.getBookings(startDatatime,endDatatime,destination);
	}

	public void addBooking(Booking booking) {
		loadBookingManager();
		bookingManager.addBooking(booking, user);
		saveBookingManager();
	}


	public boolean removeBooking(Timestamp startDatatime, String regPlate) {
		loadBookingManager();
		boolean result = bookingManager.removeBooking(startDatatime, regPlate);
		saveBookingManager();
		return result;
	}

	private void saveBookingManager() {
		JSONManager.writeToFile(bookingManager, EntityType.BOOKING);
	}

	private void loadBookingManager() {
		bookingManager = (BookingManager) JSONManager.readFromFile(EntityType.BOOKING);
		if (bookingManager == null) bookingManager = new BookingManager();
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
		loadVehicleManager();
		return vehicleManager.getVehicle(registerPlate);
	}

	public boolean removeVehicle(String registerPlate) {
		loadVehicleManager();
		boolean result = vehicleManager.removeVehicle(registerPlate);
		saveVehicleManager();
		return result;
	}

	public ArrayList<Vehicle> listVehicles() {
		loadVehicleManager();
		return vehicleManager.listVehicles();
	}

	public Vehicle getSelectedVehicle() { return this.selectedVehicle; }

	public void setSelectedVehicle(String registrationPlate) {
		selectedVehicle = vehicleManager.getVehicle(registrationPlate);
	}

	private void saveVehicleManager() {
		JSONManager.writeToFile(vehicleManager, EntityType.VEHICLE);
	}

	private void loadVehicleManager() {
		vehicleManager = (VehicleManager) JSONManager.readFromFile(EntityType.VEHICLE);
		if (vehicleManager == null) vehicleManager = new VehicleManager();
	}

	public Booking getSelectedBooking() {
		return this.selectedBooking;
	}

	public void setSelectedBooking(String registrationPlate) {
		selectedBooking = bookingManager.getBooking(registrationPlate);
	}

	public boolean login(String email, String password) {
		loadUserManager();
		if (userManager.login(email, password)) {
			 user = userManager.getUser(email);
			 return true;
		 }
		 return false;
	}

	public void search(Timestamp startDatatime, Timestamp endDatatime, String destination, int nrOfSeats) {
		loadBookingManager();
		loadVehicleManager();

		var allVehicles = vehicleManager.listVehicles();

		var sharedBookings = bookingManager.getSharedBookings(startDatatime, endDatatime, destination, nrOfSeats);
		var nonSharedBookings = new ArrayList<Booking>();
		var sharedVehicles = new ArrayList<Vehicle>();

		for (var sharedBooking : sharedBookings)
			sharedVehicles.add(sharedBooking.getVehicle());

		allVehicles.removeAll(sharedVehicles);

		var nonAvailableVehicles = bookingManager.getNonAvailableVehicles(startDatatime, endDatatime, nrOfSeats);

		allVehicles.removeAll(nonAvailableVehicles);

		allVehicles.removeIf(vehicle -> !vehicle.isAvailable());

		allVehicles.removeIf(vehicle -> vehicle.getNumOfSeats() < nrOfSeats);

		for (var vehicle : allVehicles)
			nonSharedBookings.add(new Booking(startDatatime,endDatatime,destination,vehicle));

		listOfBookings = new ArrayList<>();
		listOfBookings.addAll(sharedBookings);
		listOfBookings.addAll(nonSharedBookings);

		for (var booking : listOfBookings) {
			booking.setNumSeats(nrOfSeats);
		}
	}

	public void search(Timestamp startDatatime, Timestamp endDatatime, String destination, String user, String regPlate) {
		loadBookingManager();

		var allBookings = bookingManager.listBookings();

		if (destination != null)
			allBookings.removeIf(booking -> !booking.getDestination().equals(destination));

		if (regPlate != null)
			allBookings.removeIf(booking -> !booking.getVehicle().getRegisterPlate().equals(regPlate));

		if (user != null)
			allBookings.removeIf(booking -> !booking.containsUser(user));

		if (startDatatime != null)
			allBookings.removeIf(booking -> booking.getStartDatatime().before(startDatatime));

		if (endDatatime != null)
			allBookings.removeIf(booking -> booking.getEndDatatime().after(endDatatime));

		listOfBookings = new ArrayList<>(allBookings);
	}

	public ArrayList<Booking> getListOfBookings()
	{
		return listOfBookings;
	}

	public void editMaintenance(Vehicle vehicle) {
		loadVehicleManager();
		vehicleManager.editMaintenance(vehicle);
		saveVehicleManager();
	}

	public void deliver(Booking booking) {
		loadBookingManager();
		loadVehicleManager();
		vehicleManager.getVehicle(booking.getVehicle().getRegisterPlate()).setMaintenance(booking.getVehicle().getMaintenance());
		bookingManager.removeBooking(booking);
		saveVehicleManager();
		saveBookingManager();
	}

    public User getUser(String email) {
		loadUserManager();
    	return userManager.getUser(email);
    }

	public ArrayList<Booking> listBookings() {
		loadBookingManager();
		return bookingManager.listBookings();
	}

	public boolean emailAlreadyRegistered(String email) {
		return userManager.emailAlreadyRegistered(email);
	}

	public boolean nameAlreadyExists(String name, String email) {
		return userManager.nameAlreadyExists(name,email);
	}

	public void editBooking(Booking booking) {
		loadBookingManager();
		bookingManager.editBooking(booking);
		saveBookingManager();
	}

	public boolean drivingLicenseAlreadyExists(String drivingLicense, String email)
	{
		loadUserManager();
		return userManager.drivingLicenseAlreadyExists(drivingLicense,email);
	}

	public boolean phoneNumberAlreadyExists(String phoneNumber, String email)
	{
		loadUserManager();
		return userManager.phoneNumberAlreadyExists(phoneNumber,email);
	}
}