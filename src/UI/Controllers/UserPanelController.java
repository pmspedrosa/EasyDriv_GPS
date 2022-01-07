package UI.Controllers;

import Logic.EasyDriv;
import Logic.States.SystemState;
import UI.ScenesControllers;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class UserPanelController
{
    private EasyDriv easyDriv;
    private ScenesControllers scenesControllers;

    public void set(ScenesControllers scenesControllers)
    {
        this.scenesControllers = scenesControllers;
        this.easyDriv = scenesControllers.getEasyDriv();
    }

    @FXML
    public void OnLogout()
    {
        easyDriv.logout();
        if (easyDriv.getActualState() == SystemState.LOGIN)
            scenesControllers.setLoginStage();
    }

    @FXML
    public void OnManageProfile() {
        if (easyDriv.getActualState() == SystemState.MENU)
            scenesControllers.setManageProfileScene();
    }

    @FXML
    public void OnBooking()
    {
        scenesControllers.onBooking();
    }

    public void OnDeliver() {
        easyDriv.deliver(null);
        scenesControllers.onDeliver();
    }
}
