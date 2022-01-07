package UI.Controllers;

import Logic.Data.Booking.Booking;
import Logic.EasyDriv;
import Logic.States.SystemState;
import UI.Models.BookingTableView;
import UI.Models.VehicleTableView;
import UI.ScenesControllers;
import Utils.Validator;
import com.dlsc.gemsfx.TimePicker;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tornadofx.control.DateTimePicker;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Objects;

public class BookingController
{

    private EasyDriv easyDriv;
    private ScenesControllers scenesControllers;
    private Image shared, book;

    @FXML
    private TableView<BookingTableView> tvBookings;
    @FXML
    private TableColumn<BookingTableView, String> tcMake;
    @FXML
    private TableColumn<BookingTableView, String> tcModel;
    @FXML
    private TableColumn<BookingTableView, String> tcRegistrationPlate;
    @FXML
    private TableColumn<BookingTableView, String> tcStartDate;
    @FXML
    private TableColumn<BookingTableView, String> tcEndDate;
    @FXML
    private TableColumn<BookingTableView, ImageView> tcShare;
    @FXML
    private TableColumn<BookingTableView, Button> tcBook;
    @FXML
    private Button btnRefresh;
    @FXML
    private ComboBox cbDestination;
    @FXML
    private ComboBox cbNumSeats;
    @FXML
    private ComboBox cbStartH;
    @FXML
    private ComboBox cbStartM;
    @FXML
    private ComboBox cbEndH;
    @FXML
    private ComboBox cbEndM;
    @FXML
    private DatePicker dpStartData;
    @FXML
    private DatePicker dpEndData;

    public void set(ScenesControllers scenesControllers)
    {
        this.scenesControllers = scenesControllers;
        this.easyDriv = scenesControllers.getEasyDriv();
        ArrayList<Integer> nrSeats = new ArrayList<>();
        for (int i = 1; i <= 5; i++)
            nrSeats.add(i);
        cbNumSeats.setItems(FXCollections.observableList(nrSeats));

        ArrayList<String> hours = new ArrayList<>();
        for (int i = 0; i <=9; i++)
            hours.add("0" + i);
        for (int i = 10; i <= 24; i++)
            hours.add("" + i);

        cbStartH.setItems(FXCollections.observableList(hours));
        cbEndH.setItems(FXCollections.observableList(hours));


        ArrayList<String> minutes = new ArrayList<>();
        for (int i = 0; i <=9; i++)
            minutes.add("0" + i);
        for (int i = 10; i <= 59; i++)
            minutes.add(""+i);

        cbStartM.setItems(FXCollections.observableList(minutes));
        cbEndM.setItems(FXCollections.observableList(minutes));


        cbDestination.setItems(FXCollections.observableArrayList(Validator.cities));
        configTableBookings();
    }

    private void configTableBookings()
    {
        tcMake.setCellValueFactory(new PropertyValueFactory<>("make"));
        tcModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        tcRegistrationPlate.setCellValueFactory(new PropertyValueFactory<>("regPlate"));
        tcStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        tcEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        tcShare.setCellValueFactory(new PropertyValueFactory<>("shared"));
        tcBook.setCellValueFactory(new PropertyValueFactory<>("btnBook"));

        shared = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("../Resources/Images/shared.png")));
        book = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("../Resources/Images/book.png")));
    }

    public void updateTableBookings()
    {
        tvBookings.getItems().clear();

        var bookings = new ArrayList<BookingTableView>();
        for (var booking : easyDriv.getListOfSearchedBookings())
            bookings.add(new BookingTableView(scenesControllers, booking,
                    new ImageView(shared), new ImageView(book)));

        tvBookings.setItems(FXCollections.observableList(bookings));
    }

    @FXML
    public void OnRefresh()
    {
        String startHstring = (String) cbStartH.getValue();
        String startMstring = (String) cbStartM.getValue();
        String endHstring = (String) cbEndH.getValue();
        String endMstring = (String) cbEndM.getValue();


        if (dpEndData.getValue() == null || dpStartData.getValue() == null || startHstring == null || startMstring == null || endHstring == null || endMstring == null)
        {
            scenesControllers.pickerEmpty();
            return;
        }

        Integer startH = Integer.parseInt(startHstring);
        Integer startM = Integer.parseInt(startMstring);
        Integer endH = Integer.parseInt(endHstring);
        Integer endM = Integer.parseInt(endHstring);

        LocalDate startDate = dpStartData.getValue();
        LocalDate endDate = dpEndData.getValue();

        Timestamp startDateTime = new Timestamp(startDate.getYear() - 1900, startDate.getMonthValue() - 1, startDate.getDayOfMonth(), startH, startM, 0, 0);
        Timestamp endDateTime = new Timestamp(endDate.getYear() - 1900, endDate.getMonthValue() - 1, endDate.getDayOfMonth(), endH, endM, 0, 0);
        String destination = (String) cbDestination.getValue();
        Integer nrSeats = (Integer) cbNumSeats.getValue();

        scenesControllers.onRefreshBookings(startDateTime,endDateTime,destination,nrSeats);
    }

    @FXML
    public void OnCancel()
    {
        easyDriv.cancel();
        if (easyDriv.getActualState() == SystemState.MENU)
            scenesControllers.setUserScene();
    }

    public void OnStartH(ActionEvent actionEvent)
    {
        cbStartM.getSelectionModel().select(0);
    }

    public void OnEndH(ActionEvent actionEvent)
    {
        cbEndM.getSelectionModel().select(0);
    }
}