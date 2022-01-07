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

//	public ArrayList<Vehicle> getVehiclesAvailable(Timestamp startDatatime, Timestamp endDatatime){
//		var vehicles = new ArrayList<Vehicle>();
//
//		for (var booking: bookings) {
//			vehicles.add(booking.getVehicle());
//		}
//
//		for (var booking: bookings) {
//			if(endDatatime.getTime() >= booking.getStartDatatime().getTime() && endDatatime.getTime() <= booking.getEndDatatime().getTime()){
//				// Booking indisponivel porque o endDataTime está no meio
//				System.out.println("Booking indisponivel porque o endDataTime está no meio");
//				vehicles.remove(booking.getVehicle());
//			}
//			else if(startDatatime.getTime() <= booking.getEndDatatime().getTime() && startDatatime.getTime() >= booking.getStartDatatime().getTime()){
//				// Booking indisponivel porque o startDataTime está no meio
//				System.out.println("Booking indisponivel porque o startDataTime está no meio");
//				vehicles.remove(booking.getVehicle());
//			}
//			else if(startDatatime.getTime() <= booking.getStartDatatime().getTime() && endDatatime.getTime() <= booking.getEndDatatime().getTime()){
//				// Booking indisponivel porque o startDataTime e endDataTime está no meio
//				System.out.println("Booking indisponivel porque o startDataTime e endDataTime está no meio");
//				vehicles.remove(booking.getVehicle());
//			}
//			else if(startDatatime.getTime() >= booking.getStartDatatime().getTime() && endDatatime.getTime() <= booking.getEndDatatime().getTime()){
//				// Booking indisponivel porque o startDataTime e endDataTime está no meio
//				System.out.println("Booking indisponivel porque o startDataTime e endDataTime está no meio");
//				vehicles.remove(booking.getVehicle());
//			}
//		}
//
//		if(vehicles.size() == 0)
//			return null;
//
//		return vehicles;
//	}

	public boolean addBooking(Booking booking, User user) {
		if (booking.isShared())
		{
			for (var existingBooking : bookings)
				if (existingBooking.getVehicle().getRegisterPlate().equals(booking.getVehicle().getRegisterPlate()))
					existingBooking.addUser(user);
			return true;
		}

		int numSeats = booking.getNumSeats();
		while (numSeats > 0)
		{
			booking.addUser(user);
			numSeats--;
		}
		bookings.add(booking);
		return true;
//
//		for (var booking: bookings){
//			var curVehicle = booking.getVehicle();
//			if(curVehicle.equals(vehicle)){
//				if(endDatatime.getTime() >= booking.getStartDatatime().getTime() && endDatatime.getTime() <= booking.getEndDatatime().getTime()){
//					// Booking indisponivel porque o endDataTime está no meio
//					System.out.println("Booking indisponivel porque o endDataTime está no meio");
//					return false;
//				}
//				else if(startDatatime.getTime() <= booking.getEndDatatime().getTime() && startDatatime.getTime() >= booking.getStartDatatime().getTime()){
//					// Booking indisponivel porque o startDataTime está no meio
//					System.out.println("Booking indisponivel porque o startDataTime está no meio");
//					return false;
//				}
//				else if(startDatatime.getTime() <= booking.getStartDatatime().getTime() && endDatatime.getTime() <= booking.getEndDatatime().getTime()){
//					// Booking indisponivel porque o startDataTime e endDataTime está no meio
//					System.out.println("Booking indisponivel porque o startDataTime e endDataTime está no meio");
//					return false;
//				}
//				else if(startDatatime.getTime() >= booking.getStartDatatime().getTime() && endDatatime.getTime() <= booking.getEndDatatime().getTime()){
//					// Booking indisponivel porque o startDataTime e endDataTime está no meio
//					System.out.println("Booking indisponivel porque o startDataTime e endDataTime está no meio");
//					return false;
//				}
//			}
//		}
//
//		listUsers.add(user);
//		Booking b = new Booking(startDatatime, endDatatime, destination, listUsers, vehicle);
//		bookings.add(b);
//		return true;
	}

//	public boolean addUserToBooking(User user, Booking booking)
//	{
//		for (var book: bookings){
//			if(book.equals(booking)){
//				var curVehicle = booking.getVehicle();
//
//				if(curVehicle.getNumOfSeats() > book.ge){
//					listUsers.add(user);
//					return true;
//				}
//			}
//		}
//
//		return false;
//	}

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

	public ArrayList<Booking> getBookingsByEmail(String email) {
		var bookingByEmail = new ArrayList<Booking>();
		for (Booking booking : bookings ) {
			for (User user : booking.getUsers()) {
				if (user.getEmail().equals(email)) {
					bookingByEmail.add(booking);
					continue;
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

	private boolean isBetweed(Timestamp initial , Timestamp middle, Timestamp end)
	{
		return middle.after(initial) && middle.before(end);
	}

//	public ArrayList<Vehicle> getAllBookedVehicles(Timestamp pretendedStartTime, Timestamp pretendedEndTime)
//	{
//		var bookedVehicles = new ArrayList<Vehicle>();
//		for (var booking : bookings)
//		{
//			var bookingStartTime = booking.getStartDatatime();
//			var bookingEndTime = booking.getEndDatatime();
//			if (pretendedTimeIsEqualOrAfterBookingTime(pretendedStartTime,bookingStartTime))
//				if(pretendedTimeIsEqualOrBeforeBookingTime(pretendedEndTime, bookingEndTime))
//					bookedVehicles.add(booking.getVehicle());
//		}
//		return bookedVehicles;
//	}

//	private boolean pretendedTimeIsEqualOrAfterBookingTime(Timestamp pretendedTime, Timestamp bookingTime)
//	{
//		//true if pretendedTime is at same time          or      after then bookingTime
//		return pretendedTime.compareTo(bookingTime) == 0 || pretendedTime.compareTo(bookingTime) > 0;
//	}
//
//	private boolean pretendedTimeIsEqualOrBeforeBookingTime(Timestamp pretendedTime, Timestamp bookingTime)
//	{
//		//true if pretendedTime is at same time          or         before bookingTime
//		return pretendedTime.compareTo(bookingTime) == 0 || pretendedTime.compareTo(bookingTime) < 0;
//	}

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

	public ArrayList<Booking> getBookingsByDates(Timestamp startDatatime, Timestamp endDatatime) {
		var timeFilteredBookings = new ArrayList<Booking>();
		for (var booking : bookings){
			if(isBetweed(startDatatime, booking.getStartDatatime(), endDatatime) || isBetweed(startDatatime, booking.getEndDatatime(), endDatatime)){
				timeFilteredBookings.add(booking);
			}
		}
		return timeFilteredBookings;
	}

	public ArrayList<Booking> getBookingsByDestination(String destination) {
		var destinationFilteredBookings = new ArrayList<Booking>();
		for (var booking : bookings){
			if(booking.getDestination().equals(destination)){
				destinationFilteredBookings.add(booking);
			}
		}
		return destinationFilteredBookings;
	}


}