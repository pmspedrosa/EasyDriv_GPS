package Logic.States;

import Logic.Controller;
import Logic.Data.Vehicle.Vehicle;

public class CheckMaintenance extends StateAdapter {

	public CheckMaintenance(Controller controller) {
		super(controller);
	}

	@Override
	public IState cancel() {
		return new ManageVehicle(getController());
	}

	@Override
	public IState editMaintenance(Vehicle vehicle) {
		getController().editMaintenance(vehicle);
		return new ManageVehicle(getController());
	}

	@Override
	public SystemState getActualState() {
		return SystemState.CHECK_MAINTENANCE;
	}

}