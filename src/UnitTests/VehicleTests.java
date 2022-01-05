package UnitTests;

import Logic.Controller;
import Logic.Data.User.User;
import Logic.Data.Vehicle.Vehicle;
import Logic.Data.Vehicle.VehicleManager;
import Logic.States.ManageVehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VehicleTests {

    VehicleManager vehicleManager;

    public VehicleTests() {
        vehicleManager = new VehicleManager();
        vehicleManager.addVehicle("Fiat", "AA-00-AA", 5, "Diesel", "Punto", true);
    }

    @Test
    public void addVehicleSuccess(){
        Assertions.assertTrue(vehicleManager.addVehicle("Fiat", "AA-00-AA", 5, "Diesel", "Punto", true));
    }

    @Test
    public void addVehicleFail(){
        Assertions.assertFalse(vehicleManager.addVehicle("Fiat", "83khhsfd", 5, "Diesel", "Punto", true));
    }

    @Test
    public void editVehicleSuccess(){
        Vehicle vehicle = vehicleManager.getVehicle("AA-00-AA");
        Assertions.assertTrue(vehicleManager.editVehicle(vehicle.getMake(), vehicle.getRegisterPlate(), 3, vehicle.getFuelType(), vehicle.getModel(), vehicle.getAvaliable()));
    }

    @Test
    public void editVehicleFail(){
        Vehicle vehicle = vehicleManager.getVehicle("AA-00-AA");
        Assertions.assertFalse(vehicleManager.editVehicle(vehicle.getMake(), vehicle.getRegisterPlate(),10, vehicle.getFuelType(), vehicle.getModel(), vehicle.getAvaliable()));
    }

    @Test
    public void deliverVehicle(){
        Vehicle vehicle = vehicleManager.getVehicle("AA-00-AA");
        Assertions.assertTrue(vehicleManager.editVehicle(vehicle.getMake(), vehicle.getRegisterPlate(),vehicle.getNumOfSeats(), vehicle.getFuelType(), vehicle.getModel(), true));
    }

    @Test
    public void removeVehicle(){
        Vehicle vehicle = vehicleManager.getVehicle("AA-00-AA");
        Assertions.assertTrue(vehicleManager.removeVehicle(vehicle.getRegisterPlate()));
    }

}
