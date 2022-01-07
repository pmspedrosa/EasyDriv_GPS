package Logic.States;

import Logic.Controller;
import Logic.Data.Booking.Booking;

public class EditBooking extends StateAdapter {
    public EditBooking(Controller controller) { super(controller); }

    @Override
    public IState cancel() {
        return new ManageBookings(getController());
    }

    @Override
    public SystemState getActualState() {
        return SystemState.EDIT_BOOKING;
    }

    @Override
    public IState editBooking(Booking booking) {
        getController().editBooking(booking);
        return new ManageBookings(getController());
    }
}
