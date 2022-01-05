package Logic.Data.Vehicle;

import Utils.Logger;

import java.util.ArrayList;

public class VehicleManager {

	private ArrayList<Vehicle> vehicles;

	public VehicleManager() {
		vehicles = new ArrayList<>();
	}

	public boolean addVehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available) {
		if(make.isEmpty() || !isRegisterPlateValid(registerPlate) || numOfSeats <= 0 || fuelType.isEmpty() || model.isEmpty()) {
			Logger.getInstance().error("Add Vehicle: um dos parametros está errado.");
			return false;
		}

		Vehicle newVehicle = new Vehicle(make, registerPlate, numOfSeats, fuelType, model, available);
		vehicles.add(newVehicle);
		return true;
	}

	private boolean isRegisterPlateValid(String registerPlate) {
		//TODO: Opá não sei garantir que a matricula está bem, fica para depois
		return true;
	}

	public Vehicle getVehicle(String registerPlate) {
		
		// TODO - implement VehicleManager.getVehicle
		throw new UnsupportedOperationException();
	}

	public boolean removeVehicle(String registerPlate) {
		// TODO - implement VehicleManager.removeVehicle
		throw new UnsupportedOperationException();
	}

	public ArrayList<Vehicle> listVehicles() {
		// TODO - implement VehicleManager.listVehicles
		throw new UnsupportedOperationException();
	}

    public void editVehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available) {
    }
}
