package Logic.Data.Vehicle;

import Logic.Data.Maintenance.Maintenance;

public class Vehicle {

	private String make;
	private String registerPlate;
	private int numOfSeats;
	private String fuelType;
	private String model;
	private boolean avaliable;
	private Maintenance maintenance;


	public Vehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available) {
		this.make = make;
		this.registerPlate = registerPlate;
		this.numOfSeats = numOfSeats;
		this.fuelType = fuelType;
		this.model = model;
		this.avaliable = available;
		//throw new UnsupportedOperationException();
	}

	public String getMake() {
		return this.make;
	}

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

	public boolean getAvaliable() {
		return this.avaliable;
	}

	public Maintenance getMaintenance() {
		return this.maintenance;
	}


	public void setAvaliable(boolean avaliable) {
		this.avaliable = avaliable;
	}


	public boolean edit(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available) {
		// TODO - implement Vehicle.edit
		throw new UnsupportedOperationException();
	}

}