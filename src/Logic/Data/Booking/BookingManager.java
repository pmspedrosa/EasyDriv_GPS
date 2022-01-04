package Logic.Data.Booking;

import Logic.Data.User.User;
import Logic.Data.Vehicle.Vehicle;
import Utils.EntityType;
import Utils.JSONManager;
import Utils.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

	public void loadBookings() {
		Logger.getInstance().debug("Load Bookings");
		JSONArray listBookingsFromJson = JSONManager.readFromFile(EntityType.BOOKING);

		listBookingsFromJson.forEach(booking -> bookings.add(parseBookingObject( (JSONObject) booking)));
		Logger.getInstance().debug("Acabei o load vehicles");
	}

	private Booking parseBookingObject(JSONObject b) {
		JSONObject bookingObject = (JSONObject) b.get("booking");

		Timestamp startDatatime = (Timestamp) bookingObject.get("startDatatime");
		Timestamp endDatatime = (Timestamp) bookingObject.get("endDatatime");
		String destination = (String) bookingObject.get("destination");
		ArrayList<User> users = (ArrayList<User>) bookingObject.get("users"); //TODO: não sei se isto funciona
		Vehicle vehicle = (Vehicle) bookingObject.get("vehicle");

		Logger.getInstance().debug("Booking parsed");
		return new Booking(startDatatime, endDatatime, destination, users, vehicle);
	}
}