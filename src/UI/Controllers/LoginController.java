package UI.Controllers;

import Logic.EasyDriv;
import Logic.States.SystemState;
import UI.ScenesControllers;
import UI.StartUI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static UI.Resources.Constants.WINDOW_HEIGHT;
import static UI.Resources.Constants.WINDOW_WIDTH;

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
    public void OnLogin(MouseEvent mouseEvent) {
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
            case LOGIN -> System.out.println("Erro no login");        //TODO Dialog com erro
            case MENU -> loginSucess();
        }
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
}
