package Logic.States;

import Logic.Controller;

public class Deliver extends StateAdapter {

	public Deliver(Controller controller) {
		super(controller);
	}

	@Override
	public IState confirm() {
		getController().deliver();
		return new Menu(getController());
	}

	@Override
	public IState cancel() {
		return new Menu(getController());
	}

	@Override
	public SystemState getActualState() {
		return SystemState.DELIVER;
	}

}