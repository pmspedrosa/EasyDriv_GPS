package Logic.States;

import Logic.Controller;

public class ManageVehicle extends StateAdapter {

	public ManageVehicle(Controller controller) {
		super(controller);
	}

	@Override
	public IState addVehicle() {
		return new AddVehicle(getController());
	}

	@Override
	public IState editVehicle() {
		return new EditVehicle(getController());
	}

	@Override
	public IState remove(String registrationPlate) {
		getController().removeVehicle(registrationPlate);
		return new ManageVehicle(getController());
	}

	@Override
	public IState checkMaintenance() {
		return new CheckMaintenance(getController());
	}

	@Override
	public IState cancel() {
		return new Menu(getController());
	}

	@Override
	public SystemState getActualState() {
		return SystemState.MANAGE_VEHICLE;
	}

}