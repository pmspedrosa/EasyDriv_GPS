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
		if (numOfSeats > 5 ) return false;
		for(Vehicle vehicle:vehicles){
			if(vehicle.getRegisterPlate().equals(registerPlate)){
				vehicle.edit(make, numOfSeats, fuelType, model, available);
				Logger.getInstance().debug("Veículo editado");
				return true;
			}
		}
		return false;
    }

    public void editMaintenance(Vehicle receivedVehicle) {
		for (int i = 0; i < vehicles.size(); i++)
		{
			var vehicle = vehicles.get(i);
			if (vehicle.getRegisterPlate().equals(receivedVehicle.getRegisterPlate()))
			{
				vehicle.setMaintenance(receivedVehicle.getMaintenance());
				vehicles.set(i, vehicle);
				return;
			}
		}

    }
}
