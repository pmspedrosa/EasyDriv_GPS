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
    @FXML private CheckBox cbTireLowPressure;
    @FXML private CheckBox cbLightsOnBoard;
    @FXML private CheckBox cbAccdentHappened;
    @FXML private CheckBox cbNeedCleaning;
    @FXML private CheckBox cbOther;
    @FXML private CheckBox cbAllWentWell;

    private Booking booking;

    private EasyDriv easyDriv;
    private ScenesControllers scenesControllers;

    public void set(ScenesControllers scenesControllers)
    {
        this.scenesControllers = scenesControllers;
        this.easyDriv = scenesControllers.getEasyDriv();

        rbOperational.setSelected(true);
        taOther.setDisable(true);
        cbAllWentWell.setSelected(true);
    }


    @FXML
    private void OnOperationalAction()
    {
        /*if(rbOperational.isSelected()) {
            rbNotOperational.setSelected(false);
            cbAllWentWell.setSelected(true);
        } else
            rbNotOperational.setSelected(true);
        if(rbNotOperational.isSelected()) {
            rbOperational.setSelected(false);
            cbAllWentWell.setSelected(false);
        } else
            rbOperational.setSelected(true);*/

        rbInoperational.setSelected(false);
    }

    @FXML
    private void OnInoperationalAction(){
        rbOperational.setSelected(false);
        cbAllWentWell.setSelected(false);
        cbAllWentWell.setDisable(true);
    }

    @FXML
    private void otherAction()
    {   taOther.setText("");
        taOther.setDisable(!cbOther.isSelected());
      /*  if(cbOther.isSelected())
            taOther.setDisable(false);
        taOther.setDisable(true);*/
    }

    @FXML
    public void OnSave(MouseEvent mouseEvent) {
        boolean operational = rbOperational.isSelected();
        boolean lowPressureTires = cbTireLowPressure.isSelected();
        boolean lightsOnBoard = cbLightsOnBoard.isSelected();
        boolean accident = cbAccdentHappened.isSelected();
        boolean cleaning = cbNeedCleaning.isSelected();
        String other = taOther.getText();
        boolean allWentWell = cbAllWentWell.isSelected();

        if(other == null)
            other = new String("");

        var vehicle = booking.getVehicle();

        var maintenance = new Maintenance(operational,lowPressureTires,lightsOnBoard, accident, cleaning, other, allWentWell);
        vehicle.setMaintenance(maintenance);
        booking.setVehicle(vehicle);

        scenesControllers.deliver(booking);
    }

    @FXML
    public void OnCancel(MouseEvent mouseEvent)
    {
        easyDriv.cancel();
        if (easyDriv.getActualState() == SystemState.DELIVER)
            scenesControllers.setUserScene();
    }

    public void setBooking(Booking booking){
        this.booking = booking;
    }
}
