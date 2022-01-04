package Logic.States;

import Logic.Controller;

public class ManageUsers extends StateAdapter {

	public ManageUsers(Controller controller) {
		super(controller);
	}

	@Override
	public IState addUser() {
		return new AddUser(getController());
	}

	@Override
	public IState editUser() {
		return new EditUser(getController());
	}

	@Override
	public IState remove(String email) {
		getController().removeUser(email);
		return new ManageUsers(getController());
	}

	@Override
	public IState cancel() {
		return new Menu(getController());
	}

	@Override
	public SystemState getActualState() {
		return SystemState.MANAGE_USERS;
	}

}