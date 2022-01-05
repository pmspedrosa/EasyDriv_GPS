package UI;

import Logic.EasyDriv;
import Logic.States.SystemState;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UserPanelController
{
    private EasyDriv easyDriv;
    Scene loginScene;
    private Stage stage;

    public void set(EasyDriv easyDriv, Stage stage, Scene loginScene) {
        this.easyDriv = easyDriv;
        this.stage = stage;
        this.loginScene = loginScene;
    }

    @FXML
    public void OnLogout(MouseEvent mouseEvent)
    {
        easyDriv.logout();
        if (easyDriv.getActualState() == SystemState.LOGIN)
            stage.setScene(loginScene);
    }
}