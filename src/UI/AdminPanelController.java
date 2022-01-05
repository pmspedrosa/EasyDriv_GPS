package UI;

import Logic.EasyDriv;
import Logic.States.SystemState;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static UI.Resources.Constants.WINDOW_HEIGHT;
import static UI.Resources.Constants.WINDOW_WIDTH;

public class AdminPanelController
{
    private EasyDriv easyDriv;
    private Scene loginScene;
    private Scene manageUsersScene;
    private Stage stage;
    private Parent manageUsersRoot;
    private ManageUsersController manageUsersController;

    public void set(EasyDriv easyDriv, Stage stage, Scene loginScene, Scene adminScene) throws IOException
    {
        this.easyDriv = easyDriv;
        this.stage = stage;
        this.loginScene = loginScene;

        FXMLLoader loader = loaderFXML("manageUsersPanel");
        manageUsersRoot = loader.load();
        manageUsersController = loader.getController();
        manageUsersScene = new Scene(manageUsersRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
        manageUsersController.set(easyDriv, stage, manageUsersScene, adminScene);
    }

    @FXML
    public void OnManageUsers(MouseEvent mouseEvent) {
        easyDriv.manageUsers();
        if (easyDriv.getActualState() == SystemState.MANAGE_USERS)
            stage.setScene(manageUsersScene);
    }

    @FXML
    public void OnLogout(MouseEvent mouseEvent)
    {
        easyDriv.logout();
        if (easyDriv.getActualState() == SystemState.LOGIN)
            stage.setScene(loginScene);
    }

    private FXMLLoader loaderFXML(String fxml) {
        return new FXMLLoader(StartUI.class.getResource("Resources/" + fxml + ".fxml"));
    }
}
