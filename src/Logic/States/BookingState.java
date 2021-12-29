package Logic.States;

import Logic.Controller;

import java.security.Timestamp;

public class BookingState extends StateAdapter {

	public BookingState(Controller controller) {
		super(controller);
		// TODO - implement BookingState.BookingState
		throw new UnsupportedOperationException();
	}

	public IState search(Timestamp startDatatime, Timestamp endDatatime, String destination, boolean shared) {
		// TODO - implement BookingState.search
		throw new UnsupportedOperationException();
	}

	public IState confirm() {
		// TODO - implement BookingState.confirm
		throw new UnsupportedOperationException();
	}

	public IState cancel() {
		// TODO - implement BookingState.cancel
		throw new UnsupportedOperationException();
	}

	public IState exit() {
		// TODO - implement BookingState.exit
		throw new UnsupportedOperationException();
	}

	public SystemState getActualState() {
		// TODO - implement BookingState.getActualState
		throw new UnsupportedOperationException();
	}

}