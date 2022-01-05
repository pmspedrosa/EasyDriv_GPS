package Logic.Data.Booking;

import Logic.Data.User.User;
import Logic.Data.Vehicle.Vehicle;
import Utils.Constants;

import java.sql.Timestamp;
import java.util.ArrayList;

public class BookingManager {

	private ArrayList<Booking> bookings;
	ArrayList<User> listUsers;

	public BookingManager() {
		bookings = new ArrayList<>();
		listUsers = new ArrayList<>();
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

	public ArrayList<Vehicle> getVehiclesAvailable(Timestamp startDatatime, Timestamp endDatatime){
		var vehicles = new ArrayList<Vehicle>();

		for (var booking: bookings) {
			vehicles.add(booking.getVehicle());
		}

		for (var booking: bookings) {
			if(endDatatime.getTime() >= booking.getStartDatatime().getTime() && endDatatime.getTime() <= booking.getEndDatatime().getTime()){
				// Booking indisponivel porque o endDataTime está no meio
				System.out.println("Booking indisponivel porque o endDataTime está no meio");
				vehicles.remove(booking.getVehicle());
			}
			else if(startDatatime.getTime() <= booking.getEndDatatime().getTime() && startDatatime.getTime() >= booking.getStartDatatime().getTime()){
				// Booking indisponivel porque o startDataTime está no meio
				System.out.println("Booking indisponivel porque o startDataTime está no meio");
				vehicles.remove(booking.getVehicle());
			}
			else if(startDatatime.getTime() <= booking.getStartDatatime().getTime() && endDatatime.getTime() <= booking.getEndDatatime().getTime()){
				// Booking indisponivel porque o startDataTime e endDataTime está no meio
				System.out.println("Booking indisponivel porque o startDataTime e endDataTime está no meio");
				vehicles.remove(booking.getVehicle());
			}
			else if(startDatatime.getTime() >= booking.getStartDatatime().getTime() && endDatatime.getTime() <= booking.getEndDatatime().getTime()){
				// Booking indisponivel porque o startDataTime e endDataTime está no meio
				System.out.println("Booking indisponivel porque o startDataTime e endDataTime está no meio");
				vehicles.remove(booking.getVehicle());
			}
		}

		if(vehicles.size() == 0)
			return null;

		return vehicles;
	}

	public boolean addBooking(Timestamp startDatatime, Timestamp endDatatime, String destination, User user, Vehicle vehicle) {

		for (var booking: bookings){
			var curVehicle = booking.getVehicle();
			if(curVehicle.equals(vehicle)){
				if(endDatatime.getTime() >= booking.getStartDatatime().getTime() && endDatatime.getTime() <= booking.getEndDatatime().getTime()){
					// Booking indisponivel porque o endDataTime está no meio
					System.out.println("Booking indisponivel porque o endDataTime está no meio");
					return false;
				}
				else if(startDatatime.getTime() <= booking.getEndDatatime().getTime() && startDatatime.getTime() >= booking.getStartDatatime().getTime()){
					// Booking indisponivel porque o startDataTime está no meio
					System.out.println("Booking indisponivel porque o startDataTime está no meio");
					return false;
				}
				else if(startDatatime.getTime() <= booking.getStartDatatime().getTime() && endDatatime.getTime() <= booking.getEndDatatime().getTime()){
					// Booking indisponivel porque o startDataTime e endDataTime está no meio
					System.out.println("Booking indisponivel porque o startDataTime e endDataTime está no meio");
					return false;
				}
				else if(startDatatime.getTime() >= booking.getStartDatatime().getTime() && endDatatime.getTime() <= booking.getEndDatatime().getTime()){
					// Booking indisponivel porque o startDataTime e endDataTime está no meio
					System.out.println("Booking indisponivel porque o startDataTime e endDataTime está no meio");
					return false;
				}
			}
		}

		listUsers.add(user);
		Booking b = new Booking(startDatatime, endDatatime, destination, listUsers, vehicle);
		bookings.add(b);
		return true;
	}

	public boolean addUserToBooking(User user, Booking booking)
	{
		for (var book: bookings){
			if(book.equals(booking)){
				var curVehicle = booking.getVehicle();

				if(curVehicle.getNumOfSeats() > listUsers.size()){
					listUsers.add(user);
					return true;
				}
			}
		}

		return false;
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

	public boolean deliver(Booking selectedBooking) {
		for (Booking b : bookings ) {
			if(b.equals(selectedBooking)) {
				b.getVehicle().setAvaliable(true);
				return true;
			}
		}
		return false;
	}

    public ArrayList<Booking> listBookings()
    {
		return bookings;
    }
}