package Logic.Data.Booking;

import Logic.Data.User.User;
import Logic.Data.Vehicle.Vehicle;
import Utils.Constants;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class BookingManager {

	private final ArrayList<Booking> bookings;

	public BookingManager() {
		bookings = new ArrayList<>();
	}

	public ArrayList<Booking> getBookings(Timestamp startDatatime, Timestamp endDatatime, String destination) {
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

	public boolean addBooking(Booking booking, User user) {
		if (booking.isShared()) {
			for (var existingBooking : bookings)
				if (existingBooking.getVehicle().getRegisterPlate().equals(booking.getVehicle().getRegisterPlate()))
					existingBooking.addUser(user);
			return true;
		}

		int numSeats = booking.getNumSeats();
		while (numSeats > 0) {
			booking.addUser(user);
			numSeats--;
		}
		bookings.add(booking);
		return true;
	}

	public boolean removeBooking(Timestamp startDatatime, String regPlate) {
		return bookings.removeIf(b ->
			b.getStartDatatime().compareTo(startDatatime) == 0 && b.getVehicle().getRegisterPlate().equals(regPlate));
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

	public boolean deliver(Booking selectedBooking) {
		for (Booking b : bookings ) {
			if(b.equals(selectedBooking)) {
				b.getVehicle().setAvailable(true);
				return true;
			}
		}
		return false;
	}

    public ArrayList<Booking> listBookings()
    {
		return bookings;
    }

	public ArrayList<Booking>
	getSharedBookings(Timestamp pretendedStartTime, Timestamp pretendedEndTime, String destination, int pretendedSeats)
	{
		Timestamp pretendedStartTimeEarlyOneHour = Timestamp.from(pretendedStartTime.toInstant().minus(1, ChronoUnit.HOURS));
		Timestamp pretendedStartTimeLaterOneHour = Timestamp.from(pretendedStartTime.toInstant().plus(1, ChronoUnit.HOURS));

		Timestamp pretendedEndTimeEarlyOneHour = Timestamp.from(pretendedEndTime.toInstant().minus(1, ChronoUnit.HOURS));
		Timestamp pretendedEndTimeLaterOneHour = Timestamp.from(pretendedEndTime.toInstant().plus(1, ChronoUnit.HOURS));

		var sharedBookings = new ArrayList<Booking>();
		for (var booking : bookings)
		{
			var bookingStartTime = booking.getStartDatatime();
			var bookingEndTime = booking.getEndDatatime();
			int vehicleSeats = booking.getVehicle().getNumOfSeats();
			int availableSeats = vehicleSeats - booking.getUsers().size();
			//           if same destination                 and if vehicle have available seats
			if (booking.getDestination().equals(destination) && availableSeats >= pretendedSeats)
				if(isBetweed(pretendedStartTimeEarlyOneHour, bookingStartTime, pretendedStartTimeLaterOneHour))
					if(isBetweed(pretendedEndTimeEarlyOneHour, bookingEndTime, pretendedEndTimeLaterOneHour))
						sharedBookings.add(booking);
		}

		for (var sharedBooking : sharedBookings)
			sharedBooking.setShared(true);

		return sharedBookings;
	}

	private boolean isBetweed(Timestamp initial , Timestamp middle, Timestamp end) {
		return middle.after(initial) && middle.before(end);
	}

	public ArrayList<Vehicle> getNonAvailableVehicles(Timestamp startDatatime, Timestamp endDatatime, int nrOfSeats)
	{
		var nonAvailableVehicles = new ArrayList<Vehicle>();
		for (var booking : bookings)
		{
			var bookingStartTime = booking.getStartDatatime();
			var bookingEndTime = booking.getEndDatatime();
			var vehicle = booking.getVehicle();
			int vehicleSeats = vehicle.getNumOfSeats();
			int availableSeats = vehicleSeats - booking.getUsers().size();
			if (availableSeats <= 0 || isBetweed(bookingStartTime, endDatatime, bookingEndTime))
			{
				nonAvailableVehicles.add(vehicle);
				continue;
			}
			if(bookingStartTime.compareTo(startDatatime) == 0 || bookingEndTime.compareTo(endDatatime) == 0) {
				nonAvailableVehicles.add(vehicle);
				continue;
			}
			if (isBetweed(bookingStartTime, startDatatime, bookingEndTime))
			{
				nonAvailableVehicles.add(vehicle);
				continue;
			}
			if ( isBetweed(startDatatime, bookingStartTime ,endDatatime) || isBetweed(startDatatime, bookingEndTime ,endDatatime))
				nonAvailableVehicles.add(vehicle);
		}
		return nonAvailableVehicles;
	}

    public void editBooking(Booking booking) {
		for(int i=0; i<bookings.size(); i++){
			var b = bookings.get(i);

			var regPlate = b.getVehicle().getRegisterPlate();
			var newRegPlate = booking.getVehicle().getRegisterPlate();

			var destination = b.getDestination();
			var newDestination = booking.getDestination();

			var newUsers = booking.getUsers();

			if(regPlate.equals(newRegPlate) && destination.equals(newDestination) && b.sameUsers(newUsers)) {
				bookings.set(i, booking);
				return;
			}
		}
    }

	public void removeBooking(Booking bookingToRemove) {
		bookings.removeIf(booking -> booking.equals(bookingToRemove));
	}

}