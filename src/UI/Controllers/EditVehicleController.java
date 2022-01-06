package UI.Controllers;

import Logic.Data.Vehicle.Vehicle;
import Logic.EasyDriv;
import Logic.States.SystemState;
import UI.ScenesControllers;
import Utils.Validator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class EditVehicleController
{
    @FXML private TextField tfRegistrationPlate;
    @FXML private ComboBox<String> cbMake;
    @FXML private ComboBox<String> cbModel;
    @FXML private ComboBox<Integer> cbNumberOfSeats;
    @FXML private ComboBox<String> cbFuelType;

    private EasyDriv easyDriv;
    private ScenesControllers scenesControllers;

    public void set(ScenesControllers scenesControllers)
    {
        this.scenesControllers = scenesControllers;
        this.easyDriv = scenesControllers.getEasyDriv();

        ArrayList<String> fuelTypes = new ArrayList<>();
        fuelTypes.add("Diesel");
        fuelTypes.add("Gasoline");
        fuelTypes.add("Electric");
        fuelTypes.add("Hibrid");
        fuelTypes.add("Kulhon");

        ArrayList<Integer> nrSeats = new ArrayList<>();
        nrSeats.add(2);
        nrSeats.add(3);
        nrSeats.add(5);

        cbMake.setItems(FXCollections.observableArrayList(Validator.vehicleList.keySet()));
        cbNumberOfSeats.setItems(FXCollections.observableList(nrSeats));
        cbFuelType.setItems(FXCollections.observableList(fuelTypes));
    }

    @FXML
    public void OnSave(MouseEvent mouseEvent)
    {
        String make = cbMake.getValue();
        String model = cbModel.getValue();
        String registrationPlate = tfRegistrationPlate.getText();
        String fuelType = cbFuelType.getValue();
        Integer nrSeats = cbNumberOfSeats.getValue();

        scenesControllers.editVehicle(make, model, registrationPlate, fuelType, nrSeats);
    }

    @FXML
    public void OnCancel(MouseEvent mouseEvent)
    {
        easyDriv.cancel();
        if (easyDriv.getActualState() == SystemState.MANAGE_VEHICLE)
            scenesControllers.setManageVehiclesScene();
    }

    @FXML
    public void makeAction(ActionEvent actionEvent)
    {
        cbModel.setItems(FXCollections.observableList(Arrays.stream(Validator.vehicleList.get(cbMake.getValue())).toList()));
    }

    public void prepare(Vehicle vehicle)
    {
        tfRegistrationPlate.setText(vehicle.getRegisterPlate());
        cbMake.getSelectionModel().select(vehicle.getMake());
        cbModel.getSelectionModel().select(vehicle.getModel());
        cbNumberOfSeats.getSelectionModel().select(new Integer(vehicle.getNumOfSeats()));
        cbFuelType.getSelectionModel().select(vehicle.getFuelType());
        cbModel.setItems(FXCollections.observableList(Arrays.stream(Validator.vehicleList.get(cbMake.getValue())).toList()));
    }
}
