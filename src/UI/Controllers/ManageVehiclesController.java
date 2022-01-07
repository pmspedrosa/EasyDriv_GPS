package UI.Controllers;

import Logic.EasyDriv;
import Logic.States.SystemState;
import UI.Models.VehicleTableView;
import UI.ScenesControllers;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Objects;

public class ManageVehiclesController
{
    private EasyDriv easyDriv;
    private ScenesControllers scenesControllers;
    private Image edit, remove, maintenance;

    @FXML private TableView<VehicleTableView> tvVehicles;
    @FXML private TableColumn<VehicleTableView, String> tcMake;
    @FXML private TableColumn<VehicleTableView, String> tcModel;
    @FXML private TableColumn<VehicleTableView, String> tcRegistrationPlate;
    @FXML private TableColumn<VehicleTableView, Button> tcEdit;
    @FXML private TableColumn<VehicleTableView, Button> tcMaintenance;
    @FXML private TableColumn<VehicleTableView, Button> tcRemove;

    public void set(ScenesControllers scenesControllers) {
        this.scenesControllers = scenesControllers;
        this.easyDriv = scenesControllers.getEasyDriv();
        configTableVehicles();
    }

    private void configTableVehicles() {
        tcMake.setCellValueFactory(new PropertyValueFactory<>("make"));
        tcModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        tcRegistrationPlate.setCellValueFactory(new PropertyValueFactory<>("registrationPlate"));
        tcEdit.setCellValueFactory(new PropertyValueFactory<>("btnEdit"));
        tcMaintenance.setCellValueFactory(new PropertyValueFactory<>("btnMaintenance"));
        tcRemove.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));

        edit = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("../Resources/Images/editUser.png")));
        remove = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("../Resources/Images/remove.png")));
        maintenance = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("../Resources/Images/maintenance.png")));
    }

    public void updateTableVehicles() {
        tvVehicles.getItems().clear();

        var vehicles = new ArrayList<VehicleTableView>();
        for (var vehicle : easyDriv.listVehicles())
            vehicles.add(new VehicleTableView(scenesControllers, vehicle,
                    new ImageView(edit), new ImageView(maintenance) , new ImageView(remove)));

        tvVehicles.setItems(FXCollections.observableList(vehicles));
    }

    @FXML
    public void OnAddVehicle() {
        easyDriv.addVehicle();
        if (easyDriv.getActualState() == SystemState.ADD_VEHICLE)
            scenesControllers.addVehicle();
    }

    @FXML
    public void OnCancel() {
        easyDriv.cancel();
        if (easyDriv.getActualState() == SystemState.MENU)
            scenesControllers.setAdminScene();
    }
}
