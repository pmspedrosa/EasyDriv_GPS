package UI.Controllers;

import Logic.EasyDriv;
import UI.Models.AdminBookingTableView;
import UI.Models.UserTableView;
import UI.ScenesControllers;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Objects;

public class ManageBookingsController
{
    private EasyDriv easyDriv;
    private ScenesControllers scenesControllers;
    private Image edit, remove, refresh;

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

    public void set(ScenesControllers scenesControllers)
    {
        this.scenesControllers = scenesControllers;
        this.easyDriv = scenesControllers.getEasyDriv();

        configTableBookings();
    }

    private void configTableBookings()
    {
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcRegPlate.setCellValueFactory(new PropertyValueFactory<>("regPlate"));
        tcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tcEdit.setCellValueFactory(new PropertyValueFactory<>("btnEdit"));
        tcRemove.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));


//        refresh = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("../Resources/Images/refresh.png")));
        edit = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("../Resources/Images/editUser.png")));
        remove = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("../Resources/Images/remove.png")));
    }

    public void updateTableBookings()
    {
        tvBookings.getItems().clear();

        var bookings = new ArrayList<AdminBookingTableView>();
        for (var booking : easyDriv.listBooking())
            bookings.add(new AdminBookingTableView(scenesControllers, booking, new ImageView(edit), new ImageView(remove)));

        tvBookings.setItems(FXCollections.observableList(bookings));
    }

    public void OnCancel(MouseEvent mouseEvent)
    {
    }

    public void OnRefresh(MouseEvent mouseEvent)
    {
    }
}
