package UI.Controllers;

import Logic.Data.Booking.Booking;
import Logic.Data.Maintenance.Maintenance;
import Logic.EasyDriv;
import Logic.States.SystemState;
import UI.ScenesControllers;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class MaintenanceController {

    @FXML private TextArea taOther;
    @FXML private RadioButton rbOperational;
    @FXML private RadioButton rbInoperational;
    @FXML private CheckBox cbTires;
    @FXML private CheckBox cbLight;
    @FXML private CheckBox cbAccident;
    @FXML private CheckBox cbCleaning;
    @FXML private CheckBox cbOther;
    @FXML private CheckBox cbAllWentWell;

    private Booking booking;

    private EasyDriv easyDriv;
    private ScenesControllers scenesControllers;

    public void set(ScenesControllers scenesControllers) {
        this.scenesControllers = scenesControllers;
        this.easyDriv = scenesControllers.getEasyDriv();

        cleanForm();
    }
    
    private void cleanForm()
    {
        rbOperational.setSelected(true);
        cbTires.setSelected(false);
        cbLight.setSelected(false);
        cbAccident.setSelected(false);
        cbCleaning.setSelected(false);
        cbOther.setSelected(false);
        taOther.setText("");
        taOther.setDisable(true);
        cbAllWentWell.setSelected(true);
    }

    @FXML
    private void OnOperationalAction() {
        rbInoperational.setSelected(false);
        cbAllWentWell.setDisable(false);
    }

    @FXML
    private void OnInoperationalAction(){
        rbOperational.setSelected(false);
        cbAllWentWell.setSelected(false);
        cbAllWentWell.setDisable(true);
        cbOther.setSelected(true);
        taOther.setDisable(false);
        taOther.requestFocus();
    }

    @FXML
    private void OnOtherAction() {
        taOther.setText("");
        taOther.setDisable(!cbOther.isSelected());
    }

    @FXML
    public void OnSave() {
        boolean operational = rbOperational.isSelected();
        boolean lowPressureTires = cbTires.isSelected();
        boolean lightsOnBoard = cbLight.isSelected();
        boolean accident = cbAccident.isSelected();
        boolean cleaning = cbCleaning.isSelected();
        String other = taOther.getText();
        boolean allWentWell = cbAllWentWell.isSelected();

        if(other == null)
            other = new String("");


        if(!operational && (!cbOther.isSelected() || other.length() <= 15 ))
        {
            scenesControllers.askForJustification();
            return;
        }

        if(!allWentWell && !cbOther.isSelected() && !lowPressureTires && !lightsOnBoard && !accident && !cleaning) {
            scenesControllers.deliverCheckboxesNotFilled();
            return;
        }


        var vehicle = booking.getVehicle();

        var maintenance = new Maintenance(operational,lowPressureTires,lightsOnBoard, accident, cleaning, other, allWentWell);
        vehicle.setMaintenance(maintenance);
        booking.setVehicle(vehicle);

        scenesControllers.deliver(booking);
    }

    @FXML
    public void OnCancel() {
        easyDriv.cancel();
        if (easyDriv.getActualState() == SystemState.MENU)
            scenesControllers.setUserScene();
    }

    public void setBooking(Booking booking){
        this.booking = booking;
        cleanForm();
        var maintenante = booking.getVehicle().getMaintenance();
        cbTires.setSelected(maintenante.getLowPressureTires());
        cbLight.setSelected(maintenante.getLightsOnBoard());
        cbAccident.setSelected(maintenante.getAccident());
        cbCleaning.setSelected(maintenante.getCleaning());
    }
}
