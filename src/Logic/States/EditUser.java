package Logic.States;

import Logic.Controller;

public class EditUser extends StateAdapter {

	public EditUser(Controller controller) {
		super(controller);
	}

	@Override
	public IState cancel() {
		return new ManageUsers(getController());
	}

	@Override
	public IState editUser(String email, String nome, String phoneNumber, String drivingLicense, String password) {
		getController().editUser(email, nome, phoneNumber, drivingLicense, password, false);
		return new ManageUsers(getController());
	}

	@Override
	public SystemState getActualState() {
		return SystemState.EDIT_USER;
	}

}