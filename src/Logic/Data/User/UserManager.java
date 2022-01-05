package Logic.Data.User;

import Utils.EntityType;
import Utils.JSONManager;
import Utils.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
			if(u.getEmail() == email) {
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
/*
	public void loadUsers() {
		Logger.getInstance().debug("Load Users");
		//JSONArray listUserFromJson = JSONManager.readFromFile(EntityType.USER);

		if(listUserFromJson != null) {
			// listUserFromJson.forEach(user -> users.add(parseUserObject((JSONObject) user)));
			Logger.getInstance().debug("Acabei o load users");
		}
	}*/

	private User parseUserObject(JSONObject u) {
		JSONObject userObject = (JSONObject) u.get("user");
		boolean admin = (boolean) userObject.get("admin");
		String name = (String) userObject.get("name");
		String email = (String) userObject.get("email");
		String phoneNumber = (String) userObject.get("phoneNumber");
		String drivingLicense = (String) userObject.get("drivingLicense");
		String password = (String) userObject.get("password");

		Logger.getInstance().debug("User parsed");
		return new User(admin, name, email, phoneNumber, drivingLicense, password);
	}

	public boolean login(String email, String password) {
		for (User u:users) {
			if(u.getEmail() == email && u.getPassword() == password) {
				Logger.getInstance().debug("Login com sucesso");
				return true;
			}
		}
		Logger.getInstance().debug("Login errado!");
		return false;
	}
}
