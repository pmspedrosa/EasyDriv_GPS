package UI.Controllers;

import Logic.Data.Booking.Booking;
import Logic.EasyDriv;
import Logic.States.SystemState;
import UI.ScenesControllers;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;


@SuppressWarnings({"rawtypes", "deprecation"})
public class EditBookingController
{
    private EasyDriv easyDriv;
    private ScenesControllers scenesControllers;

    private Booking booking;

    @FXML TextField tfEmail;
    @FXML TextField tfName;
    @FXML TextField tfPhoneNumber;
    @FXML TextField tfDrivingLicense;

    @FXML TextField tfRegPlate;
    @FXML TextField tfMake;
    @FXML TextField tfModel;
    @FXML TextField tfNumOfSeats;
    @FXML TextField tfActualStartDate;
    @FXML TextField tfActualEndDate;

    @FXML private ComboBox cbStartH;
    @FXML private ComboBox cbStartM;
    @FXML private ComboBox cbEndH;
    @FXML private ComboBox cbEndM;
    @FXML private DatePicker dpNewStartDate;
    @FXML private DatePicker dpNewEndDate;

    @FXML
    public void set(ScenesControllers scenesControllers) {
        this.scenesControllers = scenesControllers;
        this.easyDriv = scenesControllers.getEasyDriv();
        fillCBHours();
        fillCBMinutes();
    }

    @FXML
    public void OnSave() {
        String startHstring = (String) cbStartH.getValue();
        String startMstring = (String) cbStartM.getValue();
        String endHstring = (String) cbEndH.getValue();
        String endMstring = (String) cbEndM.getValue();

        if (dpNewEndDate.getValue() == null || dpNewStartDate.getValue() == null || startHstring == null || startMstring == null || endHstring == null || endMstring == null) {
            scenesControllers.pickerEmpty();
            return;
        }

        int startH = Integer.parseInt(startHstring);
        int startM = Integer.parseInt(startMstring);
        int endH = Integer.parseInt(endHstring);
        int endM = Integer.parseInt(endMstring);

        LocalDate startDate = dpNewStartDate.getValue();
        LocalDate endDate = dpNewEndDate.getValue();

        Timestamp startDateTime = new Timestamp(startDate.getYear() - 1900, startDate.getMonthValue() - 1, startDate.getDayOfMonth(), startH, startM, 0, 0);
        Timestamp endDateTime = new Timestamp(endDate.getYear() - 1900, endDate.getMonthValue() - 1, endDate.getDayOfMonth(), endH, endM, 0, 0);

        booking.setStartDatatime(startDateTime);
        booking.setEndDatatime(endDateTime);

        scenesControllers.editBookingAndRefresh(booking);
    }

    @FXML
    public void OnCancel() {
        easyDriv.cancel();
        if (easyDriv.getActualState() == SystemState.MANAGE_BOOKINGS)
            scenesControllers.setManageBookingsScene();
    }

    public void prepare(Booking booking) {
        this.booking = booking;
        var user = booking.getOwner();

        tfEmail.setText(user.getEmail());
        tfName.setText(user.getName());
        tfPhoneNumber.setText(user.getPhoneNumber());
        tfDrivingLicense.setText(user.getDrivingLicense());

        var vehicle = booking.getVehicle();

        tfRegPlate.setText(vehicle.getRegisterPlate());
        tfMake.setText(vehicle.getMake());
        tfModel.setText(vehicle.getModel());
        tfNumOfSeats.setText(booking.getUsers().size() + " / " + vehicle.getNumOfSeats());

        tfActualStartDate.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(booking.getStartDatatime()));
        tfActualEndDate.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(booking.getEndDatatime()));

        dpNewStartDate.setValue(booking.getStartDatatime().toLocalDateTime().toLocalDate());
        dpNewEndDate.setValue(booking.getEndDatatime().toLocalDateTime().toLocalDate());

        fillCBHours();
        fillCBMinutes();

        cbStartH.getSelectionModel().select(booking.getStartDatatime().getHours());
        cbStartM.getSelectionModel().select(booking.getStartDatatime().getMinutes());
        cbEndH.getSelectionModel().select(booking.getEndDatatime().getHours());
        cbEndM.getSelectionModel().select(booking.getEndDatatime().getMinutes());
    }

    public void OnStartH() { cbStartM.getSelectionModel().select(0); }

    public void OnEndH() { cbEndM.getSelectionModel().select(0); }

    public void fillCBHours(){
        ArrayList<String> hours = new ArrayList<>();
        for (int i = 0; i <=9; i++)
            hours.add("0" + i);
        for (int i = 10; i <= 24; i++)
            hours.add("" + i);
        cbStartH.setItems(FXCollections.observableList(hours));
        cbEndH.setItems(FXCollections.observableList(hours));
    }

    private void fillCBMinutes() {
        ArrayList<String> minutes = new ArrayList<>();
        for (int i = 0; i <=9; i++)
            minutes.add("0" + i);
        for (int i = 10; i <= 59; i++)
            minutes.add(""+i);

        cbStartM.setItems(FXCollections.observableList(minutes));
        cbEndM.setItems(FXCollections.observableList(minutes));
    }
}
