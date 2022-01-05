package Logic.Data.User;

import Utils.Logger;


import java.util.ArrayList;

public class UserManager {

	private ArrayList<User> users;

	public UserManager() {
		users = new ArrayList<>();
		//loadUsers();
	}

	public ArrayList<User> listUsers() {
		return users;
	}

	public void addUser(String name, String email, String phoneNumber, String drivingLicense, String password) {
		users.add(new User(false, name, email, phoneNumber, drivingLicense, password));
		Logger.getInstance().debug("User adicionado");
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



	public void editUser(String email, String name, String phoneNumber, String drivingLicense, String password) {
		for (User u:users) {
			if(u.getEmail() == email) {
				u.setEmail(email);
				u.setName(name);
				u.setPhoneNumber(phoneNumber);
				u.setDrivingLicense(drivingLicense);
				u.setPassword(password);
				Logger.getInstance().debug("User editado");
				return;
			}
		}
		Logger.getInstance().error("Erro ao editar user");
		return;
	}

	public void removeUser(String email) {
		users.removeIf(user -> user.getEmail().equals(email));
		Logger.getInstance().debug("User removido");
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
