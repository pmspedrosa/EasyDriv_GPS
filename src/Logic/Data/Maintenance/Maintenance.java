package Logic.Data.Maintenance;

public class Maintenance {

	private boolean operational;
	private boolean lowPressureTires;
	private boolean ligthsOnBoard;
	private boolean accident;
	private boolean cleaning;
	private String other;
	private boolean allWentWell;

//	public Maintenance(boolean operational, boolean lowPressureTires, boolean lightsOnBoard, boolean accident, boolean cleaning, String other, boolean allWentWell) {
//		// TODO - implement Maintenance.Maintenance
//
//		throw new UnsupportedOperationException();
//	}

	public Maintenance() {
		this.operational = true;
		this.lowPressureTires = false;
		this.ligthsOnBoard=false;
		this.accident = false;
		this.cleaning=false;
		this.other = null;
		this.allWentWell = true;
		// TODO - implement Maintenance.Maintenance
		throw new UnsupportedOperationException();
	}

	public boolean getOperational() {
		return this.operational;
	}

	public boolean getLowPressureTires() {
		return this.lowPressureTires;
	}

	public boolean getLigthsOnBoard() {
		return this.ligthsOnBoard;
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

	public boolean getAllWentWell() {
		return this.allWentWell;
	}

	public void repair() {
		// TODO - implement Maintenance.repair
		this.operational = true;
		this.lowPressureTires = false;
		this.ligthsOnBoard=false;
		this.accident = false;
		this.cleaning=false;
		this.other = null;
		this.allWentWell = true;
		throw new UnsupportedOperationException();
	}


	public void edit(boolean operational, boolean lowPressureTires, boolean lightsOnBoard, boolean accident, boolean cleaning, String other, boolean allWentWell) {
		// TODO - implement Maintenance.edit
		this.operational = operational;
		this.lowPressureTires = lowPressureTires;
		this.ligthsOnBoard=lightsOnBoard;
		this.accident = accident;
		this.cleaning=cleaning;
		this.other = other;
		this.allWentWell = allWentWell;
		throw new UnsupportedOperationException();
	}

}