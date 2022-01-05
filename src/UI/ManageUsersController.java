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

import static UI.Resources.Constants.*;

public class ManageUsersController
{
    private EasyDriv easyDriv;
    private Stage stage;
    private Scene addUserScene;
    private Scene adminScene;
    Parent addUserRoot;
    AddUserController addUserController;

    public void set(EasyDriv easyDriv, Stage stage, Scene manageUserScene, Scene adminScene) throws IOException
    {
        this.easyDriv = easyDriv;
        this.stage = stage;
        this.adminScene = adminScene;

        FXMLLoader loader = loaderFXML("addUser");
        addUserRoot = loader.load();
        addUserController = loader.getController();
        addUserController.set(easyDriv, stage, manageUserScene);
        addUserScene = new Scene(addUserRoot, ADD_USER_WINDOW_WIDTH, ADD_USER_WINDOW_HEIGHT);
    }

    @FXML
    public void OnAddUser(MouseEvent mouseEvent) {
        easyDriv.addUser();
        if (easyDriv.getActualState() == SystemState.ADD_USER)
            stage.setScene(addUserScene);
    }

    private FXMLLoader loaderFXML(String fxml) {
        return new FXMLLoader(StartUI.class.getResource("Resources/" + fxml + ".fxml"));
    }

    @FXML
    public void OnCancel(MouseEvent mouseEvent)
    {
        easyDriv.cancel();
        if (easyDriv.getActualState() == SystemState.MENU)
            stage.setScene(adminScene);
    }
}
