package Logic;

import Logic.Data.Booking.Booking;
import Logic.Data.User.User;
import Logic.Data.Vehicle.Vehicle;
import Logic.States.IState;
import Logic.States.Login;
import Logic.States.SystemState;

import java.sql.Timestamp;
import java.util.ArrayList;

public class EasyDriv {

	private final Controller controller;
	private IState state;

	public EasyDriv() {
		controller = new Controller();
		setState(new Login(controller));
	}

	private void setState(IState state){
		this.state = state;
	}

	public void login(String email, String password) {
		setState(state.login(email, password));
	}

	public void manageUsers() {
		setState(state.manageUsers());

	}

	public void addUser() {
		setState(state.addUser());
	}

	public void addUser(String name, String email, String phoneNumber, String drivingLicense, String password) {
		setState(state.addUser(name, email, phoneNumber, drivingLicense, password));

	}

	public void editUser() {
		setState(state.editUser());
	}

	public void search(Timestamp startDatatime, Timestamp endDatatime, String destination, int nrOfSeats) {
		setState(state.search(startDatatime,endDatatime,destination, nrOfSeats));
	}

	public void search(Timestamp startDatatime, Timestamp endDatatime, String destination, String user, String regPlate) {
		setState(state.search(startDatatime,endDatatime,destination, user, regPlate));
	}

	public ArrayList<Booking> getListOfSearchedBookings()
	{
		return controller.getListOfBookings();
	}

	public SystemState getActualState() {
		return state.getActualState();
	}

	public void editUser(String email, String name, String phoneNumber, String drivingLicense, String password) {
		setState(state.editUser(email, name, phoneNumber, drivingLicense, password));
	}

	public void manageBookings() {
		setState(state.manageBookings());
	}

	public void booking(Booking booking) {
		setState(state.booking(booking));
	}

	public void deliver(Booking booking) {
		setState(state.deliver(booking));
	}

	public void manageVehicles() {
		setState(state.manageVehicles());
	}

	public void addVehicle() {
		setState(state.addVehicle());
	}

	public void addVehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available) {
		setState(state.addVehicle(make, registerPlate, numOfSeats, fuelType, model, available));
	}

	public void editVehicle() {
		setState(state.editVehicle());
	}

	public void editVehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available) {
		setState(state.editVehicle(make, registerPlate, numOfSeats, fuelType, model, available));
	}

	public void checkMaintenance() {
		setState(state.checkMaintenance());
	}

	public void editMaintenance(Vehicle vehicle) { setState(state.editMaintenance(vehicle)); }

	public void remove(String key) {
		setState(state.remove(key));
	}

	public void remove(Timestamp timestamp, String key) {
		setState(state.remove(timestamp, key));
	}

	public void cancel() {
		setState(state.cancel());
	}

	public User getUser() {
		return controller.getUser();
	}

	public ArrayList<User> listUsers() {
		return controller.listUsers();
	}

	public Vehicle getVehicle(String registerPlate) {
		return controller.getVehicle(registerPlate);
	}

	public ArrayList<Vehicle> listVehicles() {
		return controller.listVehicles();
	}

	public void logout()
	{
		setState(state.logout());
	}

	public User getUser(String email)
	{
		return controller.getUser(email);
	}

    public ArrayList<Booking> listBooking()
    {
		return controller.listBookings();
    }

	public boolean emailAlreadyRegistered(String email) {
		return controller.emailAlreadyRegistered(email);
	}

	public boolean nameAlreadyExists(String name, String email) {
		return controller.nameAlreadyExists(name, email);
	}

	public void editBooking() {
		setState(state.editBooking());
	}

	public void editBooking(Booking booking) { setState(state.editBooking(booking)); }

	public boolean drivingLicenseAlreadyExists(String drivingLicense, String email)
	{
		return controller.drivingLicenseAlreadyExists(drivingLicense, email);
	}

	public boolean phoneNumberAlreadyExists(String phoneNumber, String email)
	{
		return controller.phoneNumberAlreadyExists(phoneNumber, email);
	}
}