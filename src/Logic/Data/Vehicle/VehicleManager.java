package Logic.Data.Vehicle;

import Utils.EntityType;
import Utils.JSONManager;
import Utils.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class VehicleManager {

	private ArrayList<Vehicle> vehicles;

	public VehicleManager() {
		// TODO - implement VehicleManager.VehicleManager
		throw new UnsupportedOperationException();
	}

	public boolean addVehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available) {
		if(make == null || !isRegisterPlateValid(registerPlate) || numOfSeats <= 0 || fuelType == null || model == model) {
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

	public void loadVehicles() {
		Logger.getInstance().debug("Load Vehicles");
		JSONArray listVehiclesFromJson = JSONManager.readFromFile(EntityType.VEHICLE);

		listVehiclesFromJson.forEach(vehicle -> vehicles.add(parseVehicleObject( (JSONObject) vehicle)));
		Logger.getInstance().debug("Acabei o load vehicles");
	}

	private Vehicle parseVehicleObject(JSONObject v) {
		JSONObject vehicleObject = (JSONObject) v.get("vehicle");
		String make = (String) vehicleObject.get("make");
		String registerPlate = (String) vehicleObject.get("registerPlate");
		int numOfSeats = (Integer) vehicleObject.get("numOfSeats");
		String fuelType = (String) vehicleObject.get("fuelType");
		String model = (String) vehicleObject.get("model");
		boolean available = (boolean) vehicleObject.get("available");

		Logger.getInstance().debug("Vehicle parsed");
		return new Vehicle(make, registerPlate, numOfSeats, fuelType, model, available);
	}
}