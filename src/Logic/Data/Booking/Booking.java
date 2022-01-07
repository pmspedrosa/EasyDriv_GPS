package Logic.Data.Booking;

import Logic.Data.User.User;
import Logic.Data.Vehicle.Vehicle;
import Utils.Logger;

import java.sql.Timestamp;
import java.util.*;

public class Booking {

	private final Timestamp startDatatime;
	private final Timestamp endDatatime;
	private final String destination;
	private boolean shared;
	private final ArrayList<User> users;
	private final Vehicle vehicle;
	private int numSeats; //just informational don't use

	public Booking(Timestamp startDatatime, Timestamp endDatatime, String destination, Vehicle vehicle) {
		this.startDatatime = startDatatime;
		this.endDatatime = endDatatime;
		this.destination = destination;
		this.vehicle = vehicle;
		users = new ArrayList<>();

		Logger.getInstance().debug("Booking criado");
	}

	public Timestamp getStartDatatime() {
		return this.startDatatime;
	}

	public Timestamp getEndDatatime() { return this.endDatatime; }

	public String getDestination() {
		return this.destination;
	}

	public boolean isShared() {
		return this.shared;
	}

	public void setShared(boolean shared) { this.shared = shared; }

	public boolean addUser(User user) {
		if (!users.contains(user)){
			shared = true;
		}
		Logger.getInstance().debug("User adicionado ao booking");
		return users.add(user);
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public User getUserFromBooking(String email) {
		for (var u:users) {
			if(u.getEmail().equals(email)) {
				return u;
			}
		}
		return null;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public int getNumSeats()
	{
		return numSeats;
	}

	public void setNumSeats(int numSeats)
	{
		this.numSeats = numSeats;
	}
}