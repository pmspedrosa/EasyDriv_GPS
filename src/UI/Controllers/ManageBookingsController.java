package UI.Controllers;

import Logic.Data.Booking.Booking;
import Logic.EasyDriv;
import Logic.States.SystemState;
import UI.Models.AdminBookingTableView;
import UI.ScenesControllers;
import Utils.Validator;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class ManageBookingsController
{
    private EasyDriv easyDriv;
    private ScenesControllers scenesControllers;
    private Image edit, remove, refresh;
    private boolean first = true;
    private boolean lastListAllList = true;

    private ArrayList<AdminBookingTableView> lastList;

    @FXML private ComboBox cbUser;
    @FXML private ComboBox cbDestination;
    @FXML private ComboBox cbRegPlate;
    @FXML private DatePicker dpStartData;
    @FXML private DatePicker dpEndData;
    @FXML private TableView<AdminBookingTableView> tvBookings;
    @FXML private TableColumn<AdminBookingTableView, String> tcUser;
    @FXML private TableColumn<AdminBookingTableView, String> tcDestination;
    @FXML private TableColumn<AdminBookingTableView, String> tcRegPlate;
    @FXML private TableColumn<AdminBookingTableView, String> tcStartDate;
    @FXML private TableColumn<AdminBookingTableView, String> tcEndDate;
    @FXML private TableColumn<AdminBookingTableView, String> tcName;
    @FXML private TableColumn<AdminBookingTableView, String> tcEmail;
    @FXML private TableColumn<AdminBookingTableView, String> tcDate;
    @FXML private TableColumn<AdminBookingTableView, Button> tcRefresh;
    @FXML private TableColumn<AdminBookingTableView, Button> tcEdit;
    @FXML private TableColumn<AdminBookingTableView, Button> tcRemove;

    public void set(ScenesControllers scenesControllers) {
        this.scenesControllers = scenesControllers;
        this.easyDriv = scenesControllers.getEasyDriv();

        ArrayList<String> usersName = new ArrayList<>();
        usersName.add("All");

        for(var u : easyDriv.listUsers()) {
            if (u.isAdmin()) continue;
            usersName.add(u.getName());
        }

        cbUser.setItems(FXCollections.observableList(usersName));
        cbUser.getSelectionModel().select(0);

        ArrayList<String> cities = new ArrayList<>();
        cities.add("All");
        cities.addAll(Validator.cities);
        cbDestination.setItems(FXCollections.observableArrayList(cities));
        cbDestination.getSelectionModel().select(0);

        ArrayList<String> vehiclesRegPlates = new ArrayList<>();
        vehiclesRegPlates.add("All");
        for(var v : easyDriv.listVehicles()) {
            vehiclesRegPlates.add(v.getRegisterPlate());
        }
        cbRegPlate.setItems(FXCollections.observableList(vehiclesRegPlates));
        cbRegPlate.getSelectionModel().select(0);

        configTableBookings();
        listAllBookings();
    }

    private void configTableBookings() {
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        tcRegPlate.setCellValueFactory(new PropertyValueFactory<>("regPlate"));
        tcStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        tcEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        tcEdit.setCellValueFactory(new PropertyValueFactory<>("btnEdit"));
        tcRemove.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));


//        refresh = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("../Resources/Images/refresh.png")));
        edit = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("../Resources/Images/editUser.png")));
        remove = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("../Resources/Images/remove.png")));
    }

    public void updateTableBookings() {
        tvBookings.getItems().clear();

        if(easyDriv.getListOfSearchedBookings() == null) {
            if(first) {
                first = false;
                listAllBookings();
            }
            return;
        }
        lastListAllList = false;
        lastList = new ArrayList<AdminBookingTableView>();
        for (var booking : easyDriv.getListOfSearchedBookings())
            lastList.add(new AdminBookingTableView(scenesControllers, booking, new ImageView(edit), new ImageView(remove)));

        tvBookings.setItems(FXCollections.observableList(lastList));
    }

    public void OnCancel() {
        easyDriv.cancel();
        if (easyDriv.getActualState() == SystemState.MENU)
            scenesControllers.setAdminScene();
    }

    public void OnRefresh() {
        LocalDate startDate = dpStartData.getValue();
        LocalDate endDate = dpEndData.getValue();

        Timestamp startDateTime = null;
        Timestamp endDateTime = null;

        if(startDate != null) {
           startDateTime = new Timestamp(startDate.getYear() - 1900, startDate.getMonthValue() - 1, startDate.getDayOfMonth(), 0, 0, 0, 0);
        }

        if(endDate != null) {
            endDateTime = new Timestamp(endDate.getYear() - 1900, endDate.getMonthValue() - 1, endDate.getDayOfMonth(), 0, 0, 0, 0);
        }

        String destination = (String) cbDestination.getValue();
        String user = (String) cbUser.getValue();
        String regPlate = (String) cbRegPlate.getValue();

        if (destination != null && destination.equals("All"))
            destination = null;
        if (user != null && user.equals("All"))
            user = null;
        if (regPlate != null && regPlate.equals("All"))
            regPlate = null;

        scenesControllers.onRefreshBookings(startDateTime, endDateTime, destination, user, regPlate);
    }

    public void OnListAllBookings() {
        clear();
        listAllBookings();
    }

    public void listAllBookings(){
        lastListAllList = true;
        lastList = new ArrayList<AdminBookingTableView>();
        for (var booking : easyDriv.listBooking())
            lastList.add(new AdminBookingTableView(scenesControllers, booking, new ImageView(edit), new ImageView(remove)));

        tvBookings.setItems(FXCollections.observableList(lastList));
    }

    public void clear() {
        if(cbUser.getValue() != null) {
            cbUser.getSelectionModel().select(0);
        }

        if(cbDestination.getValue() != null) {
            cbDestination.getSelectionModel().select(0);
        }

        if(cbRegPlate.getValue() != null) {
            cbRegPlate.getSelectionModel().select(0);
        }

        dpStartData.getEditor().clear();
        dpEndData.getEditor().clear();
        dpStartData.setValue(null);
        dpEndData.setValue(null);
    }

    public void updateLastList(){
        if(lastListAllList) {
            listAllBookings();
        } else {
            updateTableBookings();
        }
    }

    public void OnCleanStartDate()
    {
        dpStartData.getEditor().clear();
        dpStartData.setValue(null);
        OnRefresh();
    }

    public void OnCleanEndDate()
    {
        dpEndData.getEditor().clear();
        dpEndData.setValue(null);
        OnRefresh();
    }

    public void OnUserClick(MouseEvent mouseEvent)
    {
        ArrayList<String> usersName = new ArrayList<>();
        usersName.add("All");

        for(var u : easyDriv.listUsers()) {
            if (u.isAdmin()) continue;
            usersName.add(u.getName());
        }

        cbUser.setItems(FXCollections.observableList(usersName));
        cbUser.getSelectionModel().select(0);
    }

    public void OnDestinationClick(MouseEvent mouseEvent)
    {
        ArrayList<String> vehiclesRegPlates = new ArrayList<>();
        vehiclesRegPlates.add("All");
        for(var v : easyDriv.listVehicles()) {
            vehiclesRegPlates.add(v.getRegisterPlate());
        }
        cbRegPlate.setItems(FXCollections.observableList(vehiclesRegPlates));
        cbRegPlate.getSelectionModel().select(0);
    }
}
