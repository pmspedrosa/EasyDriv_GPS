package Logic.States;

import Logic.Controller;

public class AddVehicle extends StateAdapter {
	public AddVehicle(Controller controller) {
		super(controller);
	}

	@Override
	public IState cancel() {
		return new ManageVehicle(getController());
	}

	@Override
	public IState addVehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available) {
		getController().addVehicle(make, registerPlate, numOfSeats, fuelType, model, available);
		return new ManageVehicle(getController());
	}

	@Override
	public SystemState getActualState() {
		return SystemState.ADD_VEHICLE;
	}

}