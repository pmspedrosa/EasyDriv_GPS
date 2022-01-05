package UI;

import Logic.Data.User.User;
import Logic.EasyDriv;
import Logic.States.SystemState;
import UI.Models.UserTableView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Objects;


public class ManageUsersController
{
    private EasyDriv easyDriv;
    private ScenesControllers scenesControllers;
    private Image edit, remove;

    @FXML private TableView<UserTableView> tvUsers;
    @FXML private TableColumn<UserTableView, String> tcName;
    @FXML private TableColumn<UserTableView, String> tcEmail;
    @FXML private TableColumn<UserTableView, String> tcPhoneNumber;
    @FXML private TableColumn<UserTableView, Button> tcEdit;
    @FXML private TableColumn<UserTableView, Button> tcRemove;

    public void set(ScenesControllers scenesControllers)
    {
        this.scenesControllers = scenesControllers;
        this.easyDriv = scenesControllers.getEasyDriv();
        configTableUsers();
    }

    private void configTableUsers()
    {
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tcEdit.setCellValueFactory(new PropertyValueFactory<>("btnEdit"));
        tcRemove.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));

        edit = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("Resources/editUser.png")));
        remove = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("Resources/remove.png")));
    }

    public void updateTableUsers()
    {
        tvUsers.getItems().clear();

        var users = new ArrayList<UserTableView>();
        for (var user : easyDriv.listUsers())
            users.add(new UserTableView(scenesControllers, user, new ImageView(edit), new ImageView(remove)));

        tvUsers.setItems(FXCollections.observableList(users));
    }

    @FXML
    public void OnAddUser(MouseEvent mouseEvent) {
        easyDriv.addUser();
        if (easyDriv.getActualState() == SystemState.ADD_USER)
            scenesControllers.setAddUserScene();
    }

    @FXML
    public void OnCancel(MouseEvent mouseEvent)
    {
        easyDriv.cancel();
        if (easyDriv.getActualState() == SystemState.MENU)
            scenesControllers.setAdminScene();
    }
}