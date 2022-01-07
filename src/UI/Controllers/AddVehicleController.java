package UI.Controllers;

import Logic.EasyDriv;
import Logic.States.SystemState;
import UI.ScenesControllers;
import Utils.Validator;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;

public class AddVehicleController
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
    private void makeAction()
    {
        cbModel.setItems(FXCollections.observableList(Arrays.stream(Validator.vehicleList.get(cbMake.getValue())).toList()));
    }

    @FXML
    public void OnSave()
    {
        String make = cbMake.getValue();
        String model = cbModel.getValue();
        String registrationPlate = tfRegistrationPlate.getText();
        String fuelType = cbFuelType.getValue();
        Integer nrSeats = cbNumberOfSeats.getValue();

        scenesControllers.addVehicle(make, model, registrationPlate, fuelType, nrSeats);
    }

    @FXML
    public void OnCancel()
    {
        easyDriv.cancel();
        if (easyDriv.getActualState() == SystemState.MANAGE_VEHICLE)
            scenesControllers.setManageVehiclesScene();
    }

    public void clear()
    {
        tfRegistrationPlate.setText("");
        if(cbMake.getValue() != null) {
            cbMake.getSelectionModel().clearSelection();
        }
        if(cbModel.getValue() != null) {
            cbModel.getSelectionModel().clearSelection();
        }

        cbNumberOfSeats.getSelectionModel().clearSelection();
        cbFuelType.getSelectionModel().clearSelection();
    }
}
