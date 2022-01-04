package Logic.States;

import Logic.Controller;

public class CheckMaintenance extends StateAdapter {

	public CheckMaintenance(Controller controller) {
		super(controller);
	}

	@Override
	public IState confirm() {
		return new ManageVehicle(getController());
	}

	@Override
	public IState editMaintenance(boolean operational, boolean lowPressureTires, boolean lightsOnBoard,
								  boolean accident, boolean cleaning, String other, boolean allWentWell) {
		getController().editMaintenance(operational, lowPressureTires, lightsOnBoard, accident, cleaning, other, allWentWell);
		return new CheckMaintenance(getController());
	}

	@Override
	public SystemState getActualState() {
		return SystemState.CHECK_MAINTENANCE;
	}

}