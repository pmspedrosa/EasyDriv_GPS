package UI.Controllers;

import Logic.EasyDriv;
import Logic.States.SystemState;
import UI.ScenesControllers;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddUserController
{
    private EasyDriv easyDriv;
    private ScenesControllers scenesControllers;

    @FXML private TextField tfEmail;
    @FXML private TextField tfName;
    @FXML private TextField tfPhoneNumber;
    @FXML private TextField tfDrivingLicense;
    @FXML private TextField tfPassword;
    @FXML private TextField tfPasswordConfirmation;

    public void set(ScenesControllers scenesControllers)
    {
        this.scenesControllers = scenesControllers;
        this.easyDriv = scenesControllers.getEasyDriv();
    }


    @FXML
    public void OnSave()
    {
        String email = tfEmail.getText();
        String name = tfName.getText();
        String phoneNumber = tfPhoneNumber.getText();
        String drivingLicense = tfDrivingLicense.getText();
        String password = tfPassword.getText();
        String confirmationPassword = tfPasswordConfirmation.getText();

        scenesControllers.addUser(name, email, phoneNumber, drivingLicense, password, confirmationPassword);
    }

    @FXML
    public void OnCancel()
    {
        easyDriv.cancel();
        if (easyDriv.getActualState() == SystemState.MANAGE_USERS)
            scenesControllers.setManageUsersScene();
    }

    public void clear()
    {
        tfEmail.setText("");
        tfName.setText("");
        tfPhoneNumber.setText("");
        tfDrivingLicense.setText("");
        tfPassword.setText("");
        tfPasswordConfirmation.setText("");
    }
}
