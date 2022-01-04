package Logic.States;

import Logic.Controller;

public class AddUser extends StateAdapter {

	public AddUser(Controller controller) {
		super(controller);
	}

	@Override
	public IState cancel() {
		return new ManageUsers(getController());
	}

	@Override
	public IState addUser(String name, String email, String phoneNumber, String drivingLicense, String password) {
		getController().addUser(name, email, phoneNumber, drivingLicense, password);
		return new ManageUsers(getController());
	}

	@Override
	public SystemState getActualState() {
		return SystemState.ADD_USER;
	}

}