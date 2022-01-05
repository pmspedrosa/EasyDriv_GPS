package Logic.Data.User;

import Utils.Logger;

public class User {

	private boolean admin;
	private String name;
	private String email;
	private String phoneNumber;
	private String drivingLicense;
	private String password;

	public User(boolean admin, String name, String email, String phoneNumber, String drivingLicense, String password) {
		this.admin = admin;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.drivingLicense = phoneNumber;
		this.password = password;

		Logger.getInstance().debug("User criado");
	}

	public boolean isAdmin() {
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

	public String getDrivingLicense() { return this.drivingLicense; }

	public String getPassword() { return this.password; }

	public void setAdmin(boolean admin) { this.admin = admin; }

	public void setName(String name) { this.name = name; }

	public void setEmail(String email) { this.email = name; }

	public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

	public void setDrivingLicense(String drivingLicense) { this.drivingLicense = drivingLicense; }

	public void setPassword(String password) { this.password = password; }

	public void edit(String name, String email, String phoneNumber, String drivingLicense) {
		this.admin = admin;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.drivingLicense = phoneNumber;
		this.password = password;
	}
}