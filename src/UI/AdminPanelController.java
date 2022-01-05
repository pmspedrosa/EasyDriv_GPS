package UI;

import Logic.EasyDriv;
import Logic.States.SystemState;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;

public class AdminPanelController
{
    private EasyDriv easyDriv;
    private ScenesControllers scenesControllers;

    public void set(ScenesControllers scenesControllers)
    {
        this.scenesControllers = scenesControllers;
        this.easyDriv = scenesControllers.getEasyDriv();
    }

    @FXML
    public void OnManageUsers(MouseEvent mouseEvent) {
        easyDriv.manageUsers();
        if (easyDriv.getActualState() == SystemState.MANAGE_USERS)
            scenesControllers.setManageUsersScene();

    }

    @FXML
    public void OnLogout(MouseEvent mouseEvent)
    {
        easyDriv.logout();
        if (easyDriv.getActualState() == SystemState.LOGIN)
            scenesControllers.setLoginStage();
    }

    private FXMLLoader loaderFXML(String fxml) {
        return new FXMLLoader(StartUI.class.getResource("Resources/" + fxml + ".fxml"));
    }
}
