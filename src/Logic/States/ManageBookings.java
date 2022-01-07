package Logic.States;

import Logic.Controller;

import java.sql.Timestamp;

public class ManageBookings extends StateAdapter {

	public ManageBookings(Controller controller) {
		super(controller);
	}


	@Override
	public IState search(Timestamp startDatatime, Timestamp endDatatime, String destination, String user, String regPlate) {
		getController().search(startDatatime, endDatatime, destination, user, regPlate);
		return new ManageBookings(getController());
	}

	@Override
	public IState remove(Timestamp startDatatime, String email) {
		getController().removeBooking(startDatatime, email);
		return new ManageBookings(getController());
	}

	@Override
	public IState cancel() {
		return new Menu(getController());
	}

	@Override
	public SystemState getActualState() {
		return SystemState.MANAGE_BOOKINGS;
	}

}