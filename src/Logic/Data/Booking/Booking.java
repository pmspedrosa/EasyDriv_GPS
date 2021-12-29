package Logic.Data.Booking;

import Logic.Data.User.User;
import Logic.Data.Vehicle.Vehicle;

import java.security.Timestamp;
import java.util.*;

public class Booking {

	private Timestamp startDatatime;
	private Timestamp endDatatime;
	private String destination;
	private boolean shared;
	private ArrayList<User> users;
	private Vehicle vehicle;

	public Booking(Timestamp startDatatime, Timestamp endDatatime, String destination, User user, Vehicle vehicle) {
		// TODO - implement Booking.Booking
	}

	public Timestamp getStartDatatime() {
		return this.startDatatime;
	}

	public Timestamp getEndDatatime() {
		return this.endDatatime;
	}

	public String getDestination() {
		return this.destination;
	}

	public boolean getShared() {
		return this.shared;
	}

	public boolean addUser(int user) {
		// TODO - implement Booking.addUser
		throw new UnsupportedOperationException();
	}

	public boolean removeUser(int username) {
		// TODO - implement Booking.removeUser
		throw new UnsupportedOperationException();
	}

	public ArrayList<User> getUsers() {
		// TODO - implement Booking.getUsers
		throw new UnsupportedOperationException();
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}

}