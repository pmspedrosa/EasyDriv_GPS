package UI.Controllers;

import Logic.Data.User.User;
import Logic.EasyDriv;
import Logic.States.SystemState;
import UI.ScenesControllers;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ManageProfileController
{
    private EasyDriv easyDriv;
    private ScenesControllers scenesControllers;

    @FXML TextField tfEmail;
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
        String confirmationPassword = tfPasswordConfirmation.getText();

        scenesControllers.edit(email, name, phoneNumber, drivingLicense, password, confirmationPassword);
    }

    @FXML
    public void OnCancel(MouseEvent mouseEvent)
    {
        easyDriv.cancel();
        if (easyDriv.getActualState() == SystemState.MENU) {
            if(easyDriv.getUser().isAdmin()){
                scenesControllers.setAdminScene();
            } else {
                scenesControllers.setUserScene();
            }
        }
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
