package UI;

import Logic.Data.User.User;
import Logic.EasyDriv;
import Logic.States.SystemState;
import Utils.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EditUserController
{
    private EasyDriv easyDriv;
    private ScenesControllers scenesControllers;

    @FXML
    TextField tfEmail;
    @FXML TextField tfName;
    @FXML TextField tfPhoneNumber;
    @FXML TextField tfDrivingLicense;
    @FXML TextField tfPassword;
    @FXML TextField tfPasswordConfirmation;

    public void set(ScenesControllers scenesControllers)
    {
        this.scenesControllers = scenesControllers;
        this.easyDriv = scenesControllers.getEasyDriv();
    }


    @FXML
    public void OnSave(MouseEvent mouseEvent)
    {
        String email = tfEmail.getText();
        String name = tfName.getText();
        String phoneNumber = tfPhoneNumber.getText();
        String drivingLicense = tfDrivingLicense.getText();
        String password = tfPassword.getText();

        scenesControllers.edit(email, name, phoneNumber, drivingLicense, password);
    }

    @FXML
    public void OnCancel(MouseEvent mouseEvent)
    {
        easyDriv.cancel();
        if (easyDriv.getActualState() == SystemState.MANAGE_USERS)
            scenesControllers.setManageUsersScene();
    }

    public void prepare(User user)
    {
        tfEmail.setText(user.getEmail());
        tfName.setText(user.getName());
        tfPhoneNumber.setText(user.getPhoneNumber());
        tfDrivingLicense.setText(user.getDrivingLicense());
        tfPassword.setText(user.getPassword());
        tfPasswordConfirmation.setText(user.getPassword());
    }
}
