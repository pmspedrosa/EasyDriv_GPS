package Logic.Data.User;

import Logic.Data.Booking.Booking;
import Utils.Logger;

public class User {

	private boolean admin;
	private String name;
	private String email;
	private String phoneNumber;
	private String drivingLicense;
	private Booking booking;
	private String password;

	public User(boolean admin, String name, String email, String phoneNumber, String drivingLicense, String password) {
		this.admin = admin;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.drivingLicense = phoneNumber;
		this.password = password;

		Logger.getInstance().debug("User criado");
		// throw new UnsupportedOperationException();
	}

	public boolean getAdmin() {
		return this.admin;
	}

	public String getName() {
		return this.name;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public String getDrivingLicense() {
		return this.drivingLicense;
	}

	public void edit(String name, String email, String phoneNumber, String drivingLicense) {
		// TODO - implement User.edit
		this.admin = admin;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.drivingLicense = phoneNumber;
		this.password = password;
		throw new UnsupportedOperationException();
	}

	public boolean setBooking(Booking booking) {
		// TODO - implement User.setBooking
		throw new UnsupportedOperationException();
	}

	public Booking getBooking() {
		return this.booking;
	}

}