package UI.Controllers;

import Logic.Data.Maintenance.Maintenance;
import Logic.Data.Vehicle.Vehicle;
import Logic.EasyDriv;
import Logic.States.SystemState;
import UI.ScenesControllers;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class EditMaintenanceController {

    @FXML
    private TextArea taOther;
    @FXML private RadioButton rbOperational;
    @FXML private RadioButton rbInoperational;
    @FXML private CheckBox cbTires;
    @FXML private CheckBox cbLight;
    @FXML private CheckBox cbAccident;
    @FXML private CheckBox cbCleaning;

    private EasyDriv easyDriv;
    private ScenesControllers scenesControllers;

    private Vehicle vehicle;

    public void set(ScenesControllers scenesControllers) {
        this.scenesControllers = scenesControllers;
        this.easyDriv = scenesControllers.getEasyDriv();
    }

    public void prepare(Vehicle vehicle) {
        this.vehicle = vehicle;
        var maintenance = vehicle.getMaintenance();
        rbOperational.setSelected(maintenance.getOperational());
        rbInoperational.setSelected(!maintenance.getOperational());
        cbTires.setSelected(maintenance.getLowPressureTires());
        cbLight.setSelected(maintenance.getLightsOnBoard());
        cbAccident.setSelected(maintenance.getAccident());
        cbCleaning.setSelected(maintenance.getCleaning());
        if(maintenance.getOther() != null) {
            taOther.setText(maintenance.getOther());
        }
        taOther.setDisable(true);
    }

    @FXML
    private void OnOperationalAction() {
        rbInoperational.setSelected(false);
    }

    @FXML
    private void OnInoperationalAction(){
        rbOperational.setSelected(false);
    }

    @FXML
    public void OnSave() {
        boolean operational = rbOperational.isSelected();
        boolean lowPressureTires = cbTires.isSelected();
        boolean lightsOnBoard = cbLight.isSelected();
        boolean accident = cbAccident.isSelected();
        boolean cleaning = cbCleaning.isSelected();
        String other = taOther.getText();

        if(other == null)
            other = "";

        var maintenance = new Maintenance(operational, lowPressureTires, lightsOnBoard, accident, cleaning, other, true);
        vehicle.setMaintenance(maintenance);

        scenesControllers.editMaintenance(vehicle);
    }

    @FXML
    public void OnCancel() {
        easyDriv.cancel();
        if (easyDriv.getActualState() == SystemState.MANAGE_VEHICLE)
            scenesControllers.setManageVehiclesScene();
    }

    public void setVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    public void OnRepair() {
        rbOperational.setSelected(true);
        rbInoperational.setSelected(false);
        cbTires.setSelected(false);
        cbLight.setSelected(false);
        cbAccident.setSelected(false);
        cbCleaning.setSelected(false);
        taOther.setText("");
    }
}

