package UI;

import Logic.EasyDriv;
import Logic.States.SystemState;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddUserController
{
    private EasyDriv easyDriv;
    private Stage stage;
    private Scene manageUsers;

    @FXML TextField tfEmail;
    @FXML TextField tfName;
    @FXML TextField tfPhoneNumber;
    @FXML TextField tfDrivingLicense;
    @FXML TextField tfPassword;
    @FXML TextField tfPasswordConfirmation;


    public void set(EasyDriv easyDriv, Stage stage, Scene manageUsers)
    {
        this.easyDriv = easyDriv;
        this.stage = stage;
        this.manageUsers = manageUsers;
    }


    @FXML
    public void OnSave(MouseEvent mouseEvent)
    {
        //TODO verificaçoes, e strings fora
        String email = tfEmail.getText();
        easyDriv.addUser(email, tfName.getText(), tfPhoneNumber.getText(), tfDrivingLicense.getText(), tfPassword.getText());
        if (easyDriv.getActualState() == SystemState.MANAGE_USERS)
            stage.setScene(manageUsers);
    }

    @FXML
    public void OnCancel(MouseEvent mouseEvent)
    {
        easyDriv.cancel();
        if (easyDriv.getActualState() == SystemState.MANAGE_USERS)
            stage.setScene(manageUsers);
    }
}
