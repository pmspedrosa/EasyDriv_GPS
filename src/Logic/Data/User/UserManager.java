package Logic.Data.User;

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

	public boolean addUser(String name, String email, String phoneNumber, String drivingLicense) {
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

	public boolean login(String email, String password)
	{
		//percorre lista de users e retorna true caso email e password estejam em algum user
		return false;
	}
}