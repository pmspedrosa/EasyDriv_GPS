package Logic.Data.Vehicle;

import Utils.Logger;
import Utils.Validator;

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

		if(registerPlateAlreadyExists(registerPlate)) { return false; }

		Vehicle newVehicle = new Vehicle(make, registerPlate, numOfSeats, fuelType, model, available);
		vehicles.add(newVehicle);
		return true;
	}

	private boolean registerPlateAlreadyExists(String registerPlate) {
		for (Vehicle v:vehicles) {
			if(v.getRegisterPlate().equals(registerPlate)) { return true; }
		}
		return false;
	}

	private boolean isRegisterPlateValid(String registerPlate) { return Validator.registerPlatevalidation(registerPlate); }

	public Vehicle getVehicle(String registerPlate) {
		for (Vehicle v:vehicles) {
			if(v.getRegisterPlate().equals(registerPlate)) { return v; }
		}
		return null;
	}

	public boolean removeVehicle(String registerPlate) {
		for (Vehicle v:vehicles) {
			if(v.getRegisterPlate().equals(registerPlate)) { return vehicles.remove(v); }
		}
		return false;
	}

	public ArrayList<Vehicle> listVehicles() { return vehicles; }

    public boolean editVehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available) {
		if(registerPlateAlreadyExists(registerPlate)) {
			return false;
		}

		for(Vehicle v:vehicles){
			if(v.getRegisterPlate().equals(registerPlate)){
				v.setMake(make);
				v.setRegisterPlate(registerPlate);
				if(numOfSeats > 7) {
					return false;
				}
				v.setNumOfSeats(numOfSeats);
				v.setFuelType(fuelType);
				v.setModel(model);
				v.setAvaliable(available);
				Logger.getInstance().debug("Veículo editado");
				return true;
			}
		}
		return false;
    }
}
