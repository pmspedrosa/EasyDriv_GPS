package Logic.Data.User;

import Utils.Logger;
import Utils.Validator;

import java.util.ArrayList;

public class UserManager {

	private ArrayList<User> users;

	public UserManager() {
		users = new ArrayList<>();
	}

	public ArrayList<User> listUsers() {
		return users;
	}

	public boolean addUser(String name, String email, String phoneNumber, String drivingLicense, String password) {
		if(!Validator.nameValidation(name) || !Validator.emailValidation(email) || !Validator.phoneNumberValidation(phoneNumber) || !Validator.drivingLicenseValidation(drivingLicense) || !Validator.passwordValidation(password)) {
			return false;
		}
		if(emailAlreadyRegistered(email) || nameAlreadyExists(name)) {
			return false;
		}

		users.add(new User(false, name, email, phoneNumber, drivingLicense, password));
		Logger.getInstance().debug("User adicionado");
		return true;
	}

	public boolean nameAlreadyExists(String name) {
		for (User u:users) {
			if(u.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public boolean emailAlreadyRegistered(String email) {
		for (User u:users) {
			if(u.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}

	public User getUser(String email) {
		// return users.stream().filter(user -> user.getEmail().equals(email)).toList().get(0);
		for (User u:users) {
			if(u.getEmail().equals(email)) {
				return u;
			}
		}
		return null;
	}

	public boolean editUser(String email, String name, String phoneNumber, String drivingLicense, String password) {
		if(!Validator.nameValidation(name) || !Validator.emailValidation(email) || !Validator.phoneNumberValidation(phoneNumber) || !Validator.drivingLicenseValidation(drivingLicense) || !Validator.passwordValidation(password)) {
			return false;
		}

		for (User u:users) {
			if(u.getEmail().equals(email)) {
				u.setName(name);
				u.setPhoneNumber(phoneNumber);
				u.setDrivingLicense(drivingLicense);
				u.setPassword(password);
				Logger.getInstance().debug("User editado");
				return true;
			}
		}
		Logger.getInstance().error("Erro ao editar user");
		return false;
	}

	public boolean removeUser(String email) {
		boolean result = users.removeIf(user -> user.getEmail().equals(email));
		Logger.getInstance().debug("User removido");
		return result;
	}

	public boolean login(String email, String password) {
		for (User u:users) {
			if(u.getEmail().equals(email) && u.getPassword().equals(password)) {
				Logger.getInstance().debug("Login com sucesso");
				return true;
			}
		}
		Logger.getInstance().debug("Login errado!");
		return false;
	}
}
