package Logic.States;

import Logic.Controller;
import Logic.Data.Booking.Booking;

import java.sql.Timestamp;

public class BookingState extends StateAdapter {

	public BookingState(Controller controller) {
		super(controller);
	}

	@Override
	public IState search(Timestamp startDatatime, Timestamp endDatatime, String destination, int nrOfSeats) {
		getController().search(startDatatime,endDatatime,destination, nrOfSeats);
		return new BookingState(getController());
	}

	@Override
	public IState booking(Booking booking)
	{
		getController().addBooking(booking);
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