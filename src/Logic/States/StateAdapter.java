package Logic.States;

import Logic.Controller;
import Logic.Data.Booking.Booking;
import Logic.Data.User.User;
import Logic.Data.Vehicle.Vehicle;

import java.sql.Timestamp;

public abstract class StateAdapter implements IState {

	private Controller controller;
	private IState state;

	public StateAdapter(Controller controller) { this.controller = controller; }

	@Override
	public IState login(String email, String password) { return this; }
	@Override
	public IState logout() { return this; }
	@Override
	public IState manageUsers() { return this; }
	@Override
	public IState addUser(String name, String email, String phoneNumber, String drivingLicense, String password) { return this; }
	@Override
	public IState editUser() { return this; }
	@Override
	public IState manageBookings() { return this; }
	@Override
	public IState search(Timestamp startDatatime, Timestamp endDatatime, String destination, int nrOfSeats) { return this; }
	@Override
	public IState search(Timestamp startDatatime, Timestamp endDatatime, String destination, String user, String regPlate) { return this; }
	@Override
	public IState booking(Booking booking) {	return this; }
	@Override
	public IState deliver() { return this; }
	@Override
	public IState manageVehicles() { return this; }
	@Override
	public IState addVehicle() { return this; }
	@Override
	public IState editVehicle() { return this; }
	@Override
	public IState checkMaintenance() { return this; }
	@Override
	public IState remove(Timestamp timestamp,String key) { return this; }
	@Override
	public IState remove(String key) { return this; }
	@Override
	public IState confirm() { return this; }
	@Override
	public IState cancel() { return this; }
	@Override
	public IState exit() { return this; }
	@Override
	public IState addUser() { return this; }
	@Override
	public IState editUser(String email, String nome, String phoneNumber, String drivingLicense, String password) {	return this; }
	@Override
	public IState addVehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available) {	return this; }
	@Override
	public IState editVehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available) { return this; }
	@Override
	public IState editMaintenance(boolean operational, boolean lowPressureTires, boolean lightsOnBoard, boolean accident, boolean cleaning, String other, boolean allWentWell) { return this; }
	@Override
	public Controller getController() { return controller; }
}
