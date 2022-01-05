package Logic;

import Logic.Data.Booking.Booking;
import Logic.Data.User.User;
import Logic.Data.Vehicle.Vehicle;
import Logic.States.IState;
import Logic.States.Login;
import Logic.States.SystemState;

import java.security.Timestamp;
import java.util.ArrayList;

public class EasyDriv {

	private Controller controller;
	private IState state;

	public EasyDriv() {
		controller = new Controller();
		setState(new Login(controller));

		addUser("admin", "admin@admin.pt", "91231232", "123454", "12345");
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

	public void search(Timestamp startDatatime, Timestamp endDatatime, String destination, boolean shared) {
		setState(state.search(startDatatime,endDatatime,destination,shared));
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

	public void booking(Timestamp startDatatime, Timestamp endDatatime, String destination, User user, Vehicle vehicle) {
		setState(state.booking(startDatatime, endDatatime, destination, user, vehicle));

	}

	public void deliver() {
		setState(state.deliver());
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

	public void editMaintenance(boolean operational, boolean lowPressureTires, boolean lightsOnBoard, boolean accident, boolean cleaning, String other, boolean allWentWell) {
		setState(state.editMaintenance(operational, lowPressureTires, lightsOnBoard, accident, cleaning, other, allWentWell));
	}

	public void remove(String key) {
		setState(state.remove(null, key));
	}

	public void confirm() {
		setState(state.confirm());
	}

	public void cancel() {
		setState(state.cancel());
	}

	public void exit() {
		//exit, no need state call
	}

	public User getUser(String email) {
		return controller.getUser(email);
	}

	public ArrayList<User> listUsers() {
		return controller.listUsers();
	}

	public ArrayList<Booking> getBookings(Timestamp startDatatime, Timestamp endDatatime, String destination, boolean shared) {
		return controller.getBookings(startDatatime,endDatatime,destination,shared);
	}

	public Vehicle getVehicle(String registerPlate) {
		return controller.getVehicle(registerPlate);
	}

	public ArrayList<Vehicle> listVehicles() {
		return controller.listVehicles();
	}

	public void setSelectedVehicle(String registrationPlate) {
		controller.setSelectedVehicle(registrationPlate);
	}

	public Vehicle getSelectedVehicle() {
		return  controller.getSelectedVehicle();
	}

	public void setSelectedBooking(String registrationPlate) {
		controller.setSelectedBooking(registrationPlate);
	}

	public Booking getSelectedBooking() {
		return controller.getSelectedBooking();
	}

}