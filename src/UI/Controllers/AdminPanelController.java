package UI.Controllers;

import Logic.EasyDriv;
import Logic.States.SystemState;
import UI.ScenesControllers;
import javafx.fxml.FXML;

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
    public void OnManageUsers() {
        easyDriv.manageUsers();
        if (easyDriv.getActualState() == SystemState.MANAGE_USERS)
            scenesControllers.setManageUsersScene();

    }

    @FXML
    public void OnManageVehicles() {
        easyDriv.manageVehicles();
        if (easyDriv.getActualState() == SystemState.MANAGE_VEHICLE)
            scenesControllers.setManageVehiclesScene();

    }

    @FXML
    public void OnManageBookings() {
        easyDriv.manageBookings();
        if (easyDriv.getActualState() == SystemState.MANAGE_BOOKINGS)
            scenesControllers.setManageBookingsScene();

    }

    @FXML
    public void OnLogout()
    {
        easyDriv.logout();
        if (easyDriv.getActualState() == SystemState.LOGIN)
            scenesControllers.setLoginStage();
    }


    public void OnEditProfile() {
        if (easyDriv.getActualState() == SystemState.MENU)
            scenesControllers.setManageProfileScene();
    }
}
