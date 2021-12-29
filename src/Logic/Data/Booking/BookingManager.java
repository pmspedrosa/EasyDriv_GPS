package Logic.Data.Booking;

import Logic.Data.User.User;
import Logic.Data.Vehicle.Vehicle;

import java.security.Timestamp;
import java.util.ArrayList;

public class BookingManager {

	private ArrayList<Booking> bookings;

	public BookingManager() {
		// TODO - implement BookingManager.BookingManager
		throw new UnsupportedOperationException();
	}

	public ArrayList<Booking> getBookings(Timestamp startDatatime, Timestamp endDatatime, String destination, boolean shared) {
		// TODO - implement BookingManager.getBookings
		throw new UnsupportedOperationException();
	}

	public void addBoking(Timestamp startDatatime, Timestamp endDatatime, String destination, User user, Vehicle vehicle) {
		// TODO - implement BookingManager.addBoking
		throw new UnsupportedOperationException();
	}

	public boolean removeBooking(Vehicle vehicle) {
		// TODO - implement BookingManager.removeBooking
		throw new UnsupportedOperationException();
	}

	public boolean removeBooking(User user) {
		// TODO - implement BookingManager.removeBooking
		throw new UnsupportedOperationException();
	}

	public void getBooking(String registrationPlate) {
		// TODO - implement BookingManager.getBooking
		throw new UnsupportedOperationException();
	}

}