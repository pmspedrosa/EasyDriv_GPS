package UI.Controllers;

import Logic.EasyDriv;
import Logic.States.SystemState;
import UI.ScenesControllers;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MaintenanceController {

    @FXML private TextField tfOther;
    @FXML private CheckBox cbOperational;
    @FXML private CheckBox cbNotOperational;
    @FXML private CheckBox cbTireLowPressure;
    @FXML private CheckBox cbLightsOnBoard;
    @FXML private CheckBox cbAccdentHappened;
    @FXML private CheckBox cbNeedCleaning;
    @FXML private CheckBox cbOther;
    @FXML private CheckBox cbAllWentWell;

    private EasyDriv easyDriv;
    private ScenesControllers scenesControllers;

    public void set(ScenesControllers scenesControllers)
    {
        this.scenesControllers = scenesControllers;
        this.easyDriv = scenesControllers.getEasyDriv();

        cbOperational.setSelected(true);
        tfOther.setDisable(true);
        cbAllWentWell.setSelected(true);
    }


    @FXML
    private void OperationalAction()
    {
        if(cbOperational.isSelected()) {
            cbNotOperational.setSelected(false);
            cbAllWentWell.setSelected(true);
        } else
            cbNotOperational.setSelected(true);
        if(cbNotOperational.isSelected()) {
            cbOperational.setSelected(false);
            cbAllWentWell.setSelected(false);
        } else
            cbOperational.setSelected(true);
    }

    @FXML
    private void otherAction()
    {
        if(cbOther.isSelected())
            tfOther.setDisable(false);
        tfOther.setDisable(true);
    }

    @FXML
    public void OnOk(MouseEvent mouseEvent) {

        //email -> idk se é necessário

        Boolean operational;
        if (cbOperational.isSelected()){
            operational = true;
        }else{
            operational = false;
        }
        Boolean lowPressureTires = cbTireLowPressure.isSelected();
        Boolean lightsOnBoard = cbLightsOnBoard.isSelected();
        Boolean accident = cbAccdentHappened.isSelected();
        Boolean cleaning = cbNeedCleaning.isSelected();
        String other = tfOther.getText();
        Boolean allWentWell = cbAllWentWell.isSelected();

        //scenesControllers.editMaintenance(email, operational, lowPressureTires, lightsOnBoard, accident, cleaning, other, allWentWell);
    }

    @FXML
    public void OnCancel(MouseEvent mouseEvent)
    {
        easyDriv.cancel();
        if (easyDriv.getActualState() == SystemState.DELIVER)
            scenesControllers.setUserScene();
    }
}
