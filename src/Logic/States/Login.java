package Logic.States;

import Logic.Controller;

public class Login extends StateAdapter {

	public Login(Controller controller) {
		super(controller);
	}

	@Override
	public IState login(String email, String password) {
		boolean loggedIn = getController().login(email, password);
		if (loggedIn) return new Menu(getController());
		else return this;
	}

	@Override
	public SystemState getActualState() {
		return SystemState.LOGIN;
	}

}