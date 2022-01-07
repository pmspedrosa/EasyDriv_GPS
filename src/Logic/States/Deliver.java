package Logic.States;

import Logic.Controller;
import Logic.Data.Booking.Booking;

public class Deliver extends StateAdapter {

	public Deliver(Controller controller) {
		super(controller);
	}

	@Override
	public IState deliver(Booking booking) {
		getController().deliver(booking);
		return new Menu(getController());
	}

	@Override
	public IState cancel() {
		return new Menu(getController());
	}

	@Override
	public SystemState getActualState() {
		return SystemState.DELIVER;
	}

}