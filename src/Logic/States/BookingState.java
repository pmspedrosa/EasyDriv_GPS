package Logic.States;

import Logic.Controller;
import Logic.Data.User.User;
import Logic.Data.Vehicle.Vehicle;

import java.sql.Timestamp;

public class BookingState extends StateAdapter {

	public BookingState(Controller controller) {
		super(controller);
	}

	@Override
	public IState search(Timestamp startDatatime, Timestamp endDatatime, String destination, boolean shared) {
		getController().search(startDatatime,endDatatime,destination,shared);
		return new BookingState(getController());
	}

	@Override
	public IState booking(Timestamp startDatatime, Timestamp endDatatime, String destination, User user, Vehicle vehicle)
	{
		getController().addBooking(startDatatime,endDatatime,destination,user,vehicle);
		return new Menu(getController());
	}

	@Override
	public IState cancel() {
		return new Menu(getController());
	}

	@Override
	public SystemState getActualState() {
		return SystemState.BOOKING;
	}

}