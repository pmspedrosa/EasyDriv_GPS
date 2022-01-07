package UI.Controllers;

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

        for(var u : easyDriv.listUsers()) {
            usersName.add(u.getName());
        }
        cbUser.setItems(FXCollections.observableList(usersName));

        cbDestination.setItems(FXCollections.observableArrayList(Validator.cities));

        ArrayList<String> vehiclesRegPlates = new ArrayList<>();

        for(var v : easyDriv.listVehicles()) {
            vehiclesRegPlates.add(v.getRegisterPlate());
        }
        cbRegPlate.setItems(FXCollections.observableList(vehiclesRegPlates));

        configTableBookings();
    }

    private void configTableBookings() {
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
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
        var bookings = new ArrayList<AdminBookingTableView>();

        if(easyDriv.getListOfSearchedBookings() == null) {
            if(first) {
                first = false;
                for (var booking : easyDriv.listBooking())
                    bookings.add(new AdminBookingTableView(scenesControllers, booking, new ImageView(edit), new ImageView(remove)));

            }
            tvBookings.setItems(FXCollections.observableList(bookings));
            return;
        }

        for (var booking : easyDriv.getListOfSearchedBookings())
            bookings.add(new AdminBookingTableView(scenesControllers, booking, new ImageView(edit), new ImageView(remove)));

        tvBookings.setItems(FXCollections.observableList(bookings));
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

        scenesControllers.onRefreshBookings(startDateTime, endDateTime, destination, user, regPlate);
    }
}
