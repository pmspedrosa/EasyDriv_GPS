package UI.Controllers;

import Logic.EasyDriv;
import Logic.States.SystemState;
import UI.ScenesControllers;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UserPanelController
{
    private EasyDriv easyDriv;
    private ScenesControllers scenesControllers;
    Scene loginScene;
    private Stage stage;

//    public void set(EasyDriv easyDriv, Stage stage, Scene loginScene) {
//        this.easyDriv = easyDriv;
//        this.stage = stage;
//        this.loginScene = loginScene;
//    }
    public void set(ScenesControllers scenesControllers)
    {
        this.scenesControllers = scenesControllers;
        this.easyDriv = scenesControllers.getEasyDriv();
    }

    @FXML
    public void OnLogout(MouseEvent mouseEvent)
    {
        easyDriv.logout();
        if (easyDriv.getActualState() == SystemState.LOGIN)
            stage.setScene(loginScene);
    }
}
