package Logic.Data.Booking;

import Logic.Data.User.User;
import Logic.Data.Vehicle.Vehicle;
import Utils.Constants;

import java.sql.Timestamp;
import java.util.ArrayList;

public class BookingManager {

	private ArrayList<Booking> bookings;

	public BookingManager() {
		bookings = new ArrayList<>();
	}

	public ArrayList<Booking> getBookings(Timestamp startDatatime, Timestamp endDatatime, String destination, boolean shared) {
		ArrayList<Booking> auxBookings = new ArrayList<>();
		var startTime = startDatatime.getTime();
		var endTime = endDatatime.getTime();

		for(Booking b:bookings) {
			if(b.getStartDatatime().getTime() > startTime - Constants.ONEHOURMILLIS && b.getStartDatatime().getTime() < startTime + Constants.ONEHOURMILLIS){
				if((b.getEndDatatime().getTime() > endTime - Constants.ONEHOURMILLIS && b.getEndDatatime().getTime() < endTime + Constants.ONEHOURMILLIS)) {
					if(b.getDestination().equals(destination)) {
						auxBookings.add(b);
					}
				}
			}
		}
		return auxBookings;
	}

	public void addBooking(Timestamp startDatatime, Timestamp endDatatime, String destination, User user, Vehicle vehicle) {
		ArrayList<User> listUsers = new ArrayList<>();
		listUsers.add(user);
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

	public boolean removeBooking(String email) {
		return bookings.removeIf(b -> b.getUserFromBooking(email).getEmail().equals(email));
	}


	public Booking getBooking(Timestamp startDatatime, String registrationPlate) {
		for (Booking b : bookings) {
			if (b.getStartDatatime().equals(startDatatime) && b.getVehicle().getRegisterPlate().equals(registrationPlate)) {
				return b;
			}
		}
		return null;
	}

	public Booking getBooking(String registrationPlate) {
		for (Booking booking : bookings ) {
			if (booking.getVehicle().getRegisterPlate().equals(registrationPlate)) {
				return booking;
			}
		}
		return null;
	}

	public Booking getBookingByEmail(String email) {
		for (Booking booking : bookings ) {
			for (User user : booking.getUsers()) {
				if (user.getEmail().equals(email)) {
					return booking;
				}
			}
		}
		return null;
	}
}