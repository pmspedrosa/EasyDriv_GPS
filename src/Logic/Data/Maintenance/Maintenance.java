package Logic.Data.Maintenance;

import Utils.Logger;

public class Maintenance {

	private boolean operational;
	private boolean lowPressureTires;
	private boolean lightsOnBoard;
	private boolean accident;
	private boolean cleaning;
	private String other;

	public Maintenance(boolean operational, boolean lowPressureTires, boolean lightsOnBoard, boolean accident, boolean cleaning, String other, boolean allWentWell) {
		this.operational = operational;
		this.lowPressureTires = lowPressureTires;
		this.lightsOnBoard = lightsOnBoard;
		this.accident = accident;
		this.cleaning = cleaning;
		this.other = other;

		Logger.getInstance().debug("Maintenance criada");
	}

	public Maintenance() {
		this.operational = true;
		this.lowPressureTires = false;
		this.lightsOnBoard=false;
		this.accident = false;
		this.cleaning=false;
		this.other = null;
		Logger.getInstance().debug("Maintenance criada");
	}

	public boolean getOperational() {
		return this.operational;
	}

	public boolean getLowPressureTires() {
		return this.lowPressureTires;
	}

	public boolean getLightsOnBoard() {
		return this.lightsOnBoard;
	}

	public boolean getAccident() {
		return this.accident;
	}

	public boolean getCleaning() {
		return this.cleaning;
	}

	public String getOther() {
		return this.other;
	}

	public void edit(boolean operational, boolean lowPressureTires, boolean lightsOnBoard, boolean accident, boolean cleaning, String other, boolean allWentWell) {
		this.operational = operational;
		this.lowPressureTires = lowPressureTires;
		this.lightsOnBoard=lightsOnBoard;
		this.accident = accident;
		this.cleaning=cleaning;
		this.other = other;
		Logger.getInstance().debug("Edição a Maintenance");
	}
}