package Logic.Data.Vehicle;

import Logic.Data.Maintenance.Maintenance;
import Utils.Logger;

public class Vehicle {

	private String make;
	private String registerPlate;
	private int numOfSeats;
	private String fuelType;
	private String model;
	private boolean available;
	private Maintenance maintenance;


	public Vehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available) {
		this.make = make;
		this.registerPlate = registerPlate;
		this.numOfSeats = numOfSeats;
		this.fuelType = fuelType;
		this.model = model;
		this.available = available;
		this.maintenance = new Maintenance();

		Logger.getInstance().debug("Vehicle criado");
	}

	public String getMake() { return this.make; }

	public String getRegisterPlate() {
		return this.registerPlate;
	}

	public int getNumOfSeats() {
		return this.numOfSeats;
	}

	public String getFuelType() {
		return this.fuelType;
	}

	public String getModel() {
		return this.model;
	}

	public boolean isAvailable() {
		return this.available;
	}

	public Maintenance getMaintenance() {
		return this.maintenance;
	}

	public void setMake(String make) { this.make = make; }

	public void setModel(String model) {this.model = model; }

	public void setFuelType(String fuelType){this.fuelType = fuelType;}

	public void setMaintenance(Maintenance maintenance){
		this.maintenance = maintenance;
		available = maintenance.getOperational();
	}

	public void edit(String make, int numOfSeats, String fuelType, String model, boolean available) {
		this.make = make;
		this.numOfSeats = numOfSeats;
		this.fuelType = fuelType;
		this.model = model;
		this.available = available;
		Logger.getInstance().debug("Vehicle editado");
	}

	public void setAvailable(boolean available) { this.available = available; }

	@Override
	public boolean equals(Object obj) {
		Vehicle receivedVehicle = (Vehicle) obj;
		return this.registerPlate.equals(receivedVehicle.getRegisterPlate());
	}
}