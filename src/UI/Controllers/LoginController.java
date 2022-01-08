package UI.Controllers;

import Logic.EasyDriv;
import UI.ScenesControllers;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LoginController {
    private EasyDriv easyDriv;
    private ScenesControllers scenesControllers;

    @FXML private TextField tfEmail;
    @FXML private TextField tfPassword;

    public void set(ScenesControllers scenesControllers)
    {
        this.scenesControllers = scenesControllers;
        this.easyDriv = scenesControllers.getEasyDriv();
    }

    @FXML
    public void OnLogin() {
        String email = tfEmail.getText();
        String pass = tfPassword.getText();

        if(email.isEmpty() || email.isBlank()) {
            tfEmail.setStyle("-fx-border-color: red");
            return;
        }else{
            tfEmail.setStyle("-fx-border-color: none");
        }

        if(pass.isEmpty() || pass.isBlank()) {
            tfPassword.setStyle("-fx-border-color: red");
            return;
        }else{
            tfEmail.setStyle("-fx-border-color: none");
        }

        easyDriv.login(email, pass);

        switch (easyDriv.getActualState())
        {
            case LOGIN -> loginError();
            case MENU -> loginSucess();
        }
    }

    private void loginError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText("Wrong login data!");
        alert.setContentText("Please confirm your e-mail and password");
        alert.showAndWait();
    }

    private void loginSucess()
    {
        if (easyDriv.getUser().isAdmin())
            scenesControllers.setAdminScene();
        else
            scenesControllers.setUserScene();
    }

    public void clearPassword()
    {
        tfPassword.setText("");
    }

    public void OnEnter(KeyEvent keyEvent)
    {
        if (keyEvent.getCode() != KeyCode.ENTER) return;
        else OnLogin();
    }

    public void OnEnterEmail(KeyEvent keyEvent)
    {
        if (keyEvent.getCode() != KeyCode.ENTER && keyEvent.getCode() != KeyCode.TAB) return;
        else tfPassword.requestFocus();
    }
}
