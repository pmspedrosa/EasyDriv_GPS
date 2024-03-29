package Logic.Data.Booking;

import Logic.Data.User.User;
import Logic.Data.Vehicle.Vehicle;
import Utils.Logger;

import java.sql.Timestamp;
import java.util.*;

public class Booking {

	private Timestamp startDatatime;
	private Timestamp endDatatime;
	private final String destination;
	private boolean shared;
	private final ArrayList<User> users;
	private Vehicle vehicle;
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

	public boolean containsUser(String name)
	{
		for (var user : users)
			if (user.getName().equals(name))
				return true;
		return false;
	}

	public boolean sameUsers(ArrayList<User> receivedUsers) {
		if(users.size() != receivedUsers.size()) {
			return false;
		}

		for(int i=0; i<users.size(); i++){
			if(!users.get(i).getEmail().equals(receivedUsers.get(i).getEmail())){
				return false;
			}
		}
		return true;
	}

	public User getOwner(){ return this.users.get(0); }

	public void setStartDatatime(Timestamp newStartTime) {
		this.startDatatime = newStartTime; }

	public void setEndDatatime(Timestamp newEndTime) {
		this.endDatatime = newEndTime; }

	public void setVehicle(Vehicle v) { this.vehicle = v; }

	@Override
	public boolean equals(Object obj) {
		Booking receivedBooking = (Booking) obj;
		if (startDatatime.compareTo(receivedBooking.startDatatime) == 0)
			if (endDatatime.compareTo(receivedBooking.endDatatime) == 0)
				if (destination.equals(receivedBooking.destination))
					if (shared == receivedBooking.shared)
						if (this.sameUsers(receivedBooking.users))
							return vehicle.getRegisterPlate().equals(receivedBooking.getVehicle().getRegisterPlate());
		return false;
	}
}