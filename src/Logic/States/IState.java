package Logic.States;

import Logic.Controller;
import Logic.Data.User.User;
import Logic.Data.Vehicle.Vehicle;

import java.security.Timestamp;

public interface IState {

	IState login(String email, String password);

	IState logout();

	IState manageUsers();

	IState addUser(String name, String email, String phoneNumber, String drivingLicense, String password);

	IState editUser();

	IState manageBookings();

	IState search(Timestamp startDatatime, Timestamp endDatatime, String destination, boolean shared);

	IState booking(Timestamp startDatatime, Timestamp endDatatime, String destination, User user, Vehicle vehicle);

	IState deliver();

	IState manageVehicles();

	IState addVehicle();

	IState editVehicle();

	IState checkMaintenance();

	IState remove(Timestamp timestamp, String key);

	IState remove(String key);

	IState confirm();

	IState cancel();

	SystemState getActualState();

	IState exit();

	IState addUser();

	IState editUser(String email, String nome, String phoneNumber, String drivingLicense, String password);

	IState addVehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available);

	IState editVehicle(String make, String registerPlate, int numOfSeats, String fuelType, String model, boolean available);

	IState editMaintenance(boolean operational, boolean lowPressureTires, boolean lightsOnBoard, boolean accident, boolean cleaning, String other, boolean allWentWell);

	Controller getController();
}