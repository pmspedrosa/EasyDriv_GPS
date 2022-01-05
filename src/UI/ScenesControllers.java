package UI;

import Logic.Data.User.User;
import Logic.EasyDriv;
import Logic.States.SystemState;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static UI.Resources.Constants.*;
import static UI.Resources.Constants.ADD_USER_WINDOW_HEIGHT;

public class ScenesControllers
{
    private FXMLLoader loader;

    private EasyDriv easyDriv;
    private Stage stage;

    private Parent loginRoot;
    private Parent adminPanelRoot;
    private Parent manageUsersRoot;
    private Parent addUserRoot;
    private Parent userPanelRoot;

    private LoginController loginController;
    private AdminPanelController adminPanelController;
    private ManageUsersController manageUsersController;
    private AddUserController addUserController;
    private UserPanelController userPanelController;

    private Scene loginScene;
    private Scene adminScene;
    private Scene manageUsersScene;
    private Scene addUserScene;
    private Scene userScene;

    public ScenesControllers(EasyDriv easyDriv, Stage stage)
    {
        this.easyDriv = easyDriv;
        this.stage = stage;

        try
        {
            loader = loaderFXML("login");
            loginRoot = loader.load();
            loginController = loader.getController();
            loginScene = new Scene(loginRoot, WINDOW_WIDTH, WINDOW_HEIGHT);


            loader = loaderFXML("addUser");
            addUserRoot = loader.load();
            addUserController = loader.getController();
            addUserScene = new Scene(addUserRoot, ADD_USER_WINDOW_WIDTH, ADD_USER_WINDOW_HEIGHT);

            loader = loaderFXML("adminPanel");
            adminPanelRoot = loader.load();
            adminPanelController = loader.getController();
            adminScene = new Scene(adminPanelRoot, WINDOW_WIDTH, WINDOW_HEIGHT);


            loader = loaderFXML("userPanel");
            userPanelRoot = loader.load();
            userPanelController = loader.getController();
            userScene = new Scene(userPanelRoot, WINDOW_WIDTH, WINDOW_HEIGHT);

            loader = loaderFXML("manageUsersPanel");
            manageUsersRoot = loader.load();
            manageUsersController = loader.getController();
            manageUsersScene = new Scene(manageUsersRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        loginController.set(this);
        adminPanelController.set(this);
        manageUsersController.set(this);
        addUserController.set(this);
        userPanelController.set(this);

    }

    private FXMLLoader loaderFXML(String fxml) {
        return new FXMLLoader(StartUI.class.getResource("Resources/" + fxml + ".fxml"));
    }

    public EasyDriv getEasyDriv()
    {
        return easyDriv;
    }

    public void setAdminScene()
    {
        stage.setScene(adminScene);
    }

    public void setUserScene()
    {
        stage.setScene(userScene);
    }

    public void setManageUsersScene()
    {
        manageUsersController.updateTableUsers();
        stage.setScene(manageUsersScene);
    }

    public void setLoginStage()
    {
        loginController.clearPassword();
        stage.setScene(loginScene);
    }

    public void setAddUserScene()
    {
        stage.setScene(addUserScene);
    }

    public void edit()
    {
        easyDriv.editUser();
        if (easyDriv.getActualState() == SystemState.EDIT_USER)
            ;
       //     stage.setScene(editUser);
    }

    public void remove(User user)
    {
        easyDriv.remove(user.getEmail());
        manageUsersController.updateTableUsers();
    }
}