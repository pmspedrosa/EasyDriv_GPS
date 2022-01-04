package Logic.States;

import Logic.Controller;

public class EditVehicle extends StateAdapter {

	public EditVehicle(Controller controller) {
		super(controller);
	}

	@Override
	public IState cancel() {
		return new ManageVehicle(getController());
	}

	@Override
	public IState editVehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available) {
		getController().editVehicle(make,registerPlate,numOfSeats,fuelType,model,available);
		return new ManageVehicle(getController());
	}

	@Override
	public SystemState getActualState() {
		return SystemState.EDIT_VEHICLE;

	}
}