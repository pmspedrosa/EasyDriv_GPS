package UI.Controllers;

import Logic.EasyDriv;
import UI.Models.AdminBookingTableView;
import UI.Models.DeliverBookingTableView;
import UI.ScenesControllers;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Objects;

public class DeliverController
{
    private EasyDriv easyDriv;
    private ScenesControllers scenesControllers;
    private Image deliver;

    @FXML private TableView<DeliverBookingTableView> tvBookings;
    @FXML private TableColumn<AdminBookingTableView, String> tcMake;
    @FXML private TableColumn<AdminBookingTableView, String> tcModel;
    @FXML private TableColumn<AdminBookingTableView, String> tcRegistrationPlate;
    @FXML private TableColumn<AdminBookingTableView, String> tcStartDate;
    @FXML private TableColumn<AdminBookingTableView, String> tcEndDate;
    @FXML private TableColumn<AdminBookingTableView, Button> tcDeliver;

    public void set(ScenesControllers scenesControllers)
    {
        this.scenesControllers = scenesControllers;
        this.easyDriv = scenesControllers.getEasyDriv();

        configTableBookings();
    }

    private void configTableBookings()
    {
        tcMake.setCellValueFactory(new PropertyValueFactory<>("make"));
        tcModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        tcRegistrationPlate.setCellValueFactory(new PropertyValueFactory<>("regPlate"));
        tcStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        tcEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        tcDeliver.setCellValueFactory(new PropertyValueFactory<>("btnDeliver"));

        deliver = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("../Resources/Images/deliver.png")));

    }

    public void updateTableBookings()
    {
        tvBookings.getItems().clear();

        var bookings = new ArrayList<DeliverBookingTableView>();
        for (var booking : easyDriv.listBooking())
            bookings.add(new DeliverBookingTableView(scenesControllers, booking, new ImageView(deliver)));

        tvBookings.setItems(FXCollections.observableList(bookings));
    }

    public void OnCancel(MouseEvent mouseEvent)
    {
    }

    public void OnRefresh(MouseEvent mouseEvent)
    {
    }
}
