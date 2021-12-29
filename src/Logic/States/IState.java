package Logic.States;

import Logic.Data.User.User;
import Logic.Data.Vehicle.Vehicle;

import java.security.Timestamp;

public interface IState {

	IState login(String email, String password);

	IState manageUsers();

	IState addUser(String name, String email, String phoneNumber, String drivingLicense);

	IState editUser();

	IState manageBookings();

	IState search(Timestamp startDatatime, Timestamp endDatatime, String destination, boolean shared);

	IState booking(Timestamp startDatatime, Timestamp endDatatime, String destination, User user, Vehicle vehicle);

	IState deliver();

	IState manageVehicles();

	IState addVehicle();

	IState editVehicle();

	IState checkMaintenance();

	IState remove(String key);

	IState confirm();

	IState cancel();

	IState exit();

}