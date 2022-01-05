package Logic.States;

import Logic.Controller;
import Logic.Data.User.User;
import Logic.Data.Vehicle.Vehicle;

import java.sql.Timestamp;

public class Menu extends StateAdapter {

	public Menu(Controller controller) {
		super(controller);
	}

	@Override
	public IState booking(Timestamp startDatatime, Timestamp endDatatime, String destination, User user, Vehicle vehicle) {
		return new BookingState(getController());
	}
	@Override
	public IState editUser() { return new ManageProfile(getController()); }
	@Override
	public IState logout() { return new Login(getController()); }
	@Override
	public IState deliver() { return new Deliver(getController()); }
	@Override
	public IState manageUsers() { return new ManageUsers(getController()); }
	@Override
	public IState manageBookings() { return new ManageBookings(getController()); }
	@Override
	public IState manageVehicles() { return new ManageVehicle(getController()); }
	@Override
	public SystemState getActualState() { return  SystemState.MENU;	}
}