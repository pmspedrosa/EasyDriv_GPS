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
		// TODO - implement UserManager.UserManager
		throw new UnsupportedOperationException();
	}

	public ArrayList<User> listUsers() {
		// TODO - implement UserManager.listUsers
		throw new UnsupportedOperationException();
	}

	public boolean addUser(String name,String email,String phoneNumber,String drivingLicense,String password) {
		// TODO - implement UserManager.addUser
		throw new UnsupportedOperationException();
	}

	public User getUser(String email) {
		// TODO - implement UserManager.getUser
		throw new UnsupportedOperationException();
	}

	public boolean editUser(String email, String name, String phoneNumber, String drivingLicense, String password) {
		// TODO - implement UserManager.editUser
		throw new UnsupportedOperationException();
	}

	public boolean removeUser(String email) {
		// TODO - implement UserManager.removeUser
		throw new UnsupportedOperationException();
	}

	public void loadUsers() {
		Logger.getInstance().debug("Load Users");
		JSONArray listUserFromJson = JSONManager.readFromFile(EntityType.USER);

		listUserFromJson.forEach(user -> users.add(parseUserObject( (JSONObject) user)));
		Logger.getInstance().debug("Acabei o load users");
	}

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

	public boolean login(String email, String password)
	{
		//percorre lista de users e retorna true caso email e password estejam em algum user
		return false;
	}
}
