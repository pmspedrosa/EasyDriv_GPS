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
		loadBookings();
	}

	public ArrayList<Booking> getBookings(Timestamp startDatatime, Timestamp endDatatime, String destination, boolean shared) {
		ArrayList<Booking> auxBookings = new ArrayList<Booking>();

		for(Booking b:bookings) {
			//TODO: Operacoes com horas
			if(b.getDestination() == destination) {
				auxBookings.add(b);
			}
		}

		return auxBookings;
	}

	public void addBooking(Timestamp startDatatime, Timestamp endDatatime, String destination, User user, Vehicle vehicle) {
		ArrayList<User> listUsers = new ArrayList<User>();
		Booking b = new Booking(startDatatime, endDatatime, destination, listUsers, vehicle);
		bookings.add(b);
	}

	public boolean removeBooking(Timestamp startDatatime, Vehicle vehicle) {
		for (Booking b:bookings) {
			if(b.getVehicle() == vehicle && b.getStartDatatime() == startDatatime) {
				return bookings.remove(b);
			}
		}
		return false;
	}

	public boolean removeBooking(Timestamp startDatatime, String email) {
		return bookings.removeIf(b -> {
			if (b.getStartDatatime().equals(startDatatime)) {
				b.getUserFromBooking(email);
			}
			return false;
		});
	}

	public Booking getBooking(Timestamp startDatatime, String registrationPlate) {
		for (Booking b:bookings){
			if(b.getStartDatatime().equals(startDatatime) && b.getVehicle().getRegisterPlate().equals(registrationPlate)) {
				return b;
			}
		}
		return null;
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