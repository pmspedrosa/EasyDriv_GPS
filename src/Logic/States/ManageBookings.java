package Logic.States;

import Logic.Controller;

import java.security.Timestamp;

public class ManageBookings extends StateAdapter {

	public ManageBookings(Controller controller) {
		super(controller);
		// TODO - implement ManageBookings.ManageBookings
		throw new UnsupportedOperationException();
	}

	public IState search(Timestamp startDatatime, Timestamp endDatatime, String destination, boolean shared) {
		// TODO - implement ManageBookings.search
		throw new UnsupportedOperationException();
	}

	public IState remove(String key) {
		// TODO - implement ManageBookings.remove
		throw new UnsupportedOperationException();
	}

	public IState cancel() {
		// TODO - implement ManageBookings.cancel
		throw new UnsupportedOperationException();
	}

	public IState exit() {
		// TODO - implement ManageBookings.exit
		throw new UnsupportedOperationException();
	}

	public SystemState getActualState() {
		// TODO - implement ManageBookings.getActualState
		throw new UnsupportedOperationException();
	}

}