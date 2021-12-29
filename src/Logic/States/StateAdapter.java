package Logic.States;

import Logic.Controller;
import Logic.Data.User.User;
import Logic.Data.Vehicle.Vehicle;

import java.security.Timestamp;

public abstract class StateAdapter implements IState {

	private Controller controller;
	private IState state;

	public StateAdapter(Controller controller) {
		this.controller = controller;
	}

	public IState login(String email, String password) { return this; }
	public IState manageUsers() { return this; }
	public IState addUser(String name, String email, String phoneNumber, String drivingLicense) { return this; }
	public IState editUser() { return this; }
	public IState manageBookings() { return this; }
	public IState search(Timestamp startDatatime, Timestamp endDatatime, String destination, boolean shared) {
		return this;
	}
	public IState booking(Timestamp startDatatime, Timestamp endDatatime, String destination, User user, Vehicle vehicle) {
		return this;
	}
	public IState deliver() { return this; }
	public IState manageVehicles() { return this; }
	public IState addVehicle() { return this; }
	public IState editVehicle() { return this; }
	public IState checkMaintenance() { return this; }
	public IState remove(String key) { return this; }
	public IState confirm() { return this; }
	public IState cancel() { return this; }
	public IState exit() { return this; }
	public IState addUser() { return this; }
	public IState editUser(String email, String nome, String phoneNumber, String drivingLicense, String password) {
		return this;
	}
	public IState addVehicle(String make, String registerPlate, int numOfSeats, String fuelType,
							 String model, boolean available) {
		return this;
	}

	public IState editVehicle(String make, String registerPlate, int numOfSeats, String fuelType,
							  String model, boolean available) {
		return this;
	}

	public IState editMaintenance(boolean operational, boolean lowPressureTires, boolean lightsOnBoard,
								  boolean accident, boolean cleaning, String other, boolean allWentWell) {
		return this;
	}

	public Controller getController() { return controller; }
}
