package Logic.States;

import Logic.Controller;
import Logic.Data.User.User;
import Logic.Data.Vehicle.Vehicle;

import java.security.Timestamp;

public class Menu extends StateAdapter {

	public Menu(Controller controller) {
		super(controller);
		// TODO - implement Menu.Menu
		throw new UnsupportedOperationException();
	}

	public IState manageUsers() {
		// TODO - implement Menu.manageUsers
		throw new UnsupportedOperationException();
	}

	public IState editUser() {
		// TODO - implement Menu.editUser
		throw new UnsupportedOperationException();
	}

	public IState manageBookings() {
		// TODO - implement Menu.manageBookings
		throw new UnsupportedOperationException();
	}

	public IState booking(Timestamp startDatatime, Timestamp endDatatime, String destination, User user, Vehicle vehicle) {
		// TODO - implement Menu.booking
		throw new UnsupportedOperationException();
	}

	public IState deliver() {
		// TODO - implement Menu.deliver
		throw new UnsupportedOperationException();
	}

	public IState manageVehicles() {
		// TODO - implement Menu.manageVehicles
		throw new UnsupportedOperationException();
	}

	public IState exit() {
		// TODO - implement Menu.exit
		throw new UnsupportedOperationException();
	}

	public SystemState getActualState() {
		// TODO - implement Menu.getActualState
		throw new UnsupportedOperationException();
	}

}