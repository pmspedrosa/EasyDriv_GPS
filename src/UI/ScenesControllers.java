package UI;

import Logic.Data.User.User;
import Logic.EasyDriv;
import Logic.States.SystemState;
import UI.Controllers.*;
import Utils.Validator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
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
    private Parent editUserRoot;
    private Parent addVehicleRoot;
    private Parent manageVehiclesRoot;

    private LoginController loginController;
    private AdminPanelController adminPanelController;
    private ManageUsersController manageUsersController;
    private AddUserController addUserController;
    private UserPanelController userPanelController;
    private EditUserController editUserController;
    private AddVehicleController addVehicleController;
    private ManageVehiclesController manageVehiclesController;

    private Scene loginScene;
    private Scene adminScene;
    private Scene manageUsersScene;
    private Scene addUserScene;
    private Scene userScene;
    private Scene editUserScene;
    private Scene addVehicleScene;
    private Scene manageVehiclesScene;

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


            loader = loaderFXML("ManageUsers/addUser");
            addUserRoot = loader.load();
            addUserController = loader.getController();
            addUserScene = new Scene(addUserRoot, ADD_USER_WINDOW_WIDTH, ADD_USER_WINDOW_HEIGHT);

            loader = loaderFXML("ManageVehicles/addVehicle");
            addVehicleRoot = loader.load();
            addVehicleController = loader.getController();
            addVehicleScene = new Scene(addVehicleRoot, ADD_USER_WINDOW_WIDTH, ADD_USER_WINDOW_HEIGHT);

            loader = loaderFXML("ManageVehicles/manageVehiclesPanel");
            manageVehiclesRoot = loader.load();
            manageVehiclesController = loader.getController();
            manageVehiclesScene = new Scene(manageVehiclesRoot, WINDOW_WIDTH, WINDOW_HEIGHT);

            loader = loaderFXML("ManageUsers/editUser");
            editUserRoot = loader.load();
            editUserController = loader.getController();
            editUserScene = new Scene(editUserRoot, ADD_USER_WINDOW_WIDTH, ADD_USER_WINDOW_HEIGHT);

            loader = loaderFXML("adminPanel");
            adminPanelRoot = loader.load();
            adminPanelController = loader.getController();
            adminScene = new Scene(adminPanelRoot, WINDOW_WIDTH, WINDOW_HEIGHT);


            loader = loaderFXML("userPanel");
            userPanelRoot = loader.load();
            userPanelController = loader.getController();
            userScene = new Scene(userPanelRoot, WINDOW_WIDTH, WINDOW_HEIGHT);

            loader = loaderFXML("ManageUsers/manageUsersPanel");
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
        editUserController.set(this);
        addVehicleController.set(this);
        manageVehiclesController.set(this);

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

    public void edit(String email, String name, String phoneNumber, String drivingLicense, String password)
    {
        if (easyDriv.getActualState() != SystemState.EDIT_USER) return;
        if (!validate(email, name, phoneNumber, drivingLicense, password)) return;
        easyDriv.editUser(email, name, phoneNumber, drivingLicense, password);
        setManageUsersScene();
    }

    private boolean validate(String email, String name, String phoneNumber, String drivingLicense, String password)
    {
        if(!Validator.emailValidation(email))
        {
            alertDialog("Incorrect email format",
                    "Please introduce a valid email",
                    "Email must be valid! example@gmail.com");
            return false;
        }
        if(!Validator.nameValidation(name))
        {
            alertDialog("Incorrect name format",
                    "Please introduce a valid name",
                    "Name must contain between 3 and 15 characteres (no special characteres allowed)");
            return false;
        }

        if (!Validator.phoneNumberValidation(phoneNumber))
        {
            alertDialog("Incorrect phone number format",
                    "Please introduce a valid phone number",
                    "Phone number must contain 9 digits");
            return false;
        }

        if (!Validator.drivingLicenseValidation(drivingLicense))
        {
            alertDialog("Incorrect driving license format",
                    "Please introduce a valid driving license",
                    "driving license must contain 9 numbers");
            return false;
        }

        if (!Validator.passwordValidation(password)){
            alertDialog("Incorrect password format",
                    "Please introduce a valid password",
                    "Password must contain at least a number, a capital case, a lower case and a special character");
            return false;
        }
        return true;
    }

    private void alertDialog(String title, String header, String description)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);

        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);

        alert.showAndWait();
    }

    public void remove(User user)
    {
        easyDriv.remove(user.getEmail());
        manageUsersController.updateTableUsers();
    }

    public void addUser()
    {
        addUserController.clear();
        setAddUserScene();
    }

    public void edit(String email)
    {
        if (easyDriv.getActualState() != SystemState.MANAGE_USERS) return;
        easyDriv.editUser();
        editUserController.prepare(easyDriv.getUser(email));
        stage.setScene(editUserScene);
    }

    public void addUser(String name, String email, String phoneNumber, String drivingLicense, String password)
    {
        if(easyDriv.getActualState() != SystemState.ADD_USER) return;
        if (!validate(email, name, phoneNumber, drivingLicense, password)) return;
        easyDriv.addUser(name, email, phoneNumber, drivingLicense, password);
        if (easyDriv.getActualState() == SystemState.MANAGE_USERS)
            setManageUsersScene();
    }

    public void addVehicle()
    {
        addVehicleController.clear();
        stage.setScene(addVehicleScene);
    }

    public void setManageVehiclesScene()
    {
        manageVehiclesController.updateTableVehicles();
        stage.setScene(manageVehiclesScene);
    }

    public void addVehicle(String make, String model, String registrationPlate, String fuelType, int numOfSeats)
    {
        if(easyDriv.getActualState() != SystemState.ADD_VEHICLE) return;
//        if (!Validator.registerPlatevalidation(registrationPlate)){
//            alertDialog("Incorect registration plate format",
//                    "Please introduce a valid registration plate",
//                    "Registration plate must contain TODO"); //TODO <--- completar frase e testar validator
//            return;
//        }
        easyDriv.addVehicle(make,registrationPlate, numOfSeats, fuelType, model, true);
        if (easyDriv.getActualState() == SystemState.MANAGE_VEHICLE)
            setManageVehiclesScene();
    }
}
