package Logic.Data.Booking;

import Logic.Data.User.User;
import Logic.Data.Vehicle.Vehicle;
import Utils.Logger;

import java.security.Timestamp;
import java.util.*;

public class Booking {

	private Timestamp startDatatime;
	private Timestamp endDatatime;
	private String destination;
	private boolean shared;
	private ArrayList<User> users;
	private Vehicle vehicle;

	public Booking(Timestamp startDatatime, Timestamp endDatatime, String destination, ArrayList<User> users, Vehicle vehicle) {
		this.startDatatime = startDatatime;
		this.endDatatime = endDatatime;
		this.destination = destination;
		this.users = users;
		this.vehicle = vehicle;

		Logger.getInstance().debug("Booking criado");
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

	public boolean addUser(User user) {
		if(users.size()>1){
			shared = true;
		}
		Logger.getInstance().debug("User adicionado ao booking");
		return users.add(user);
	}

	public boolean removeUser(User user) {
		if(users.size()<=1){
			shared = false;
		}
		Logger.getInstance().debug("User removido do booking");
		return users.remove(user);
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

}