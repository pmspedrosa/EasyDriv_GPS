package Logic.States;

import Logic.Controller;

public class ManageProfile extends StateAdapter {

	public ManageProfile(Controller controller) {
		super(controller);
	}

	@Override
	public IState editUser(String email, String name, String phoneNumber, String drivingLicense, String password) {
		getController().editUser(email, name, phoneNumber, drivingLicense, password, true);
		return new Menu(getController());
	}

	@Override
	public IState cancel() { return new Menu(getController()); }

	@Override
	public SystemState getActualState() { return SystemState.MANAGE_PROFILE; }

}