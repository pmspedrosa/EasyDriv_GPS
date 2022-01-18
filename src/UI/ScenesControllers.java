package UI;

import Logic.Data.Booking.Booking;
import Logic.Data.User.User;
import Logic.Data.Vehicle.Vehicle;
import Logic.EasyDriv;
import Logic.States.SystemState;
import UI.Controllers.*;
import Utils.Validator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

import static UI.Resources.Constants.*;

public class ScenesControllers
{

    private final EasyDriv easyDriv;
    private final Stage stage;

    private LoginController loginController;
    private AdminPanelController adminPanelController;
    private ManageUsersController manageUsersController;
    private AddUserController addUserController;
    private UserPanelController userPanelController;
    private EditUserController editUserController;
    private AddVehicleController addVehicleController;
    private ManageVehiclesController manageVehiclesController;
    private EditBookingController editBookingController;
    private EditVehicleController editVehicleController;
    private ManageBookingsController manageBookingsController;
    private ManageProfileController manageProfileController;
    private BookingController bookingController;
    private DeliverController deliverController;
    private MaintenanceController maintenanceController;
    private EditMaintenanceController editMaintenanceController;

    private Scene loginScene;
    private Scene adminScene;
    private Scene manageUsersScene;
    private Scene addUserScene;
    private Scene userScene;
    private Scene editUserScene;
    private Scene addVehicleScene;
    private Scene manageVehiclesScene;
    private Scene editVehicleScene;
    private Scene manageBookingsScene;
    private Scene editBookingScene;
    private Scene manageProfileScene;
    private Scene bookingScene;
    private Scene deliverScene;
    private Scene maintenanceScene;
    private Scene editMaintenanceScene;

    public ScenesControllers(EasyDriv easyDriv, Stage stage)
    {
        this.easyDriv = easyDriv;
        this.stage = stage;

        try
        {
            FXMLLoader loader = loaderFXML("login");
            Parent loginRoot = loader.load();
            loginController = loader.getController();
            loginScene = new Scene(loginRoot, WINDOW_WIDTH, WINDOW_HEIGHT);

            loader = loaderFXML("ManageUsers/addUser");
            Parent addUserRoot = loader.load();
            addUserController = loader.getController();
            addUserScene = new Scene(addUserRoot, ADD_USER_WINDOW_WIDTH, ADD_USER_WINDOW_HEIGHT);

            loader = loaderFXML("ManageVehicles/addVehicle");
            Parent addVehicleRoot = loader.load();
            addVehicleController = loader.getController();
            addVehicleScene = new Scene(addVehicleRoot, ADD_VEHICLE_WINDOW_HEIGHT, ADD_VEHICLE_WINDOW_HEIGHT);

            loader = loaderFXML("ManageVehicles/manageVehiclesPanel");
            Parent manageVehiclesRoot = loader.load();
            manageVehiclesController = loader.getController();
            manageVehiclesScene = new Scene(manageVehiclesRoot, MANAGE_VEHICLES_WINDOW_WIDTH, WINDOW_HEIGHT);

            loader = loaderFXML("ManageUsers/editUser");
            Parent editUserRoot = loader.load();
            editUserController = loader.getController();
            editUserScene = new Scene(editUserRoot, ADD_USER_WINDOW_WIDTH, ADD_USER_WINDOW_HEIGHT);

            loader = loaderFXML("adminPanel");
            Parent adminPanelRoot = loader.load();
            adminPanelController = loader.getController();
            adminScene = new Scene(adminPanelRoot, WINDOW_WIDTH, WINDOW_HEIGHT);

            loader = loaderFXML("userPanel");
            Parent userPanelRoot = loader.load();
            userPanelController = loader.getController();
            userScene = new Scene(userPanelRoot, WINDOW_WIDTH, WINDOW_HEIGHT);

            loader = loaderFXML("ManageUsers/manageUsersPanel");
            Parent manageUsersRoot = loader.load();
            manageUsersController = loader.getController();
            manageUsersScene = new Scene(manageUsersRoot, MANAGE_USERS_WINDOW_WIDTH, WINDOW_HEIGHT);

            loader = loaderFXML("ManageVehicles/editVehicle");
            Parent editVehicleRoot = loader.load();
            editVehicleController = loader.getController();
            editVehicleScene = new Scene(editVehicleRoot, ADD_USER_WINDOW_WIDTH, ADD_USER_WINDOW_HEIGHT);

            loader = loaderFXML("ManageBookings/manageBookingsAdmin");
            Parent manageBookingsRoot = loader.load();
            manageBookingsController = loader.getController();
            manageBookingsScene = new Scene(manageBookingsRoot,MANAGE_BOOKINGS_WINDOW_WIDTH,MANAGE_BOOKINGS_WINDOW_HEIGHT );

            loader = loaderFXML("ManageBookings/editBooking");
            Parent editBookingRoot = loader.load();
            editBookingController = loader.getController();
            editBookingScene = new Scene(editBookingRoot, EDIT_BOOKING_WINDOW_WIDTH, EDIT_BOOKING_WINDOW_HEIGHT);

            loader = loaderFXML("manageProfile");
            Parent manageProfileRoot = loader.load();
            manageProfileController = loader.getController();
            manageProfileScene = new Scene(manageProfileRoot, ADD_USER_WINDOW_WIDTH, ADD_USER_WINDOW_HEIGHT);

            loader = loaderFXML("booking");
            Parent bookingRoot = loader.load();
            bookingController = loader.getController();
            bookingScene = new Scene(bookingRoot, BOOKINGS_WINDOW_WIDTH, BOOKINGS_WINDOW_HEIGHT);

            loader = loaderFXML("deliver");
            Parent deliverRoot = loader.load();
            deliverController = loader.getController();
            deliverScene = new Scene(deliverRoot, DELIVER_WINDOW_WIDTH, DELIVER_WINDOW_HEIGHT);

            loader = loaderFXML("maintenance");
            Parent maintenanceRoot = loader.load();
            maintenanceController = loader.getController();
            maintenanceScene = new Scene(maintenanceRoot, MAINTENANCE_WINDOW_WIDTH, MAINTENANCE_WINDOW_HEIGHT);

            loader = loaderFXML("ManageVehicles/maintenance");
            Parent editMaintenanceRoot = loader.load();
            editMaintenanceController = loader.getController();
            editMaintenanceScene = new Scene(editMaintenanceRoot, MAINTENANCE_WINDOW_WIDTH, MAINTENANCE_WINDOW_HEIGHT);

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
        editVehicleController.set(this);
        manageBookingsController.set(this);
        editBookingController.set(this);
        manageProfileController.set(this);
        bookingController.set(this);
        deliverController.set(this);
        maintenanceController.set(this);
        editMaintenanceController.set(this);
    }

    private FXMLLoader loaderFXML(String fxml) {
        return new FXMLLoader(this.getClass().getResource("Resources/" + fxml + ".fxml"));
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

    public void edit(String email, String name, String phoneNumber, String drivingLicense, String password, String passwordConfirmation)
    {
        if (easyDriv.getActualState() == SystemState.EDIT_USER || easyDriv.getActualState() == SystemState.MANAGE_PROFILE) {
            if (!validate(email, name, phoneNumber, drivingLicense, password, passwordConfirmation)) return;

            easyDriv.editUser(email, name, phoneNumber, drivingLicense, password);

            switch (easyDriv.getActualState()) {
                case MENU -> {
                    if(easyDriv.getUser().isAdmin()) {
                        setAdminScene();
                    } else {
                        setUserScene();
                    }
                }
                case MANAGE_USERS -> setManageUsersScene();
            }
        }
    }

    private boolean validate(String email, String name, String phoneNumber, String drivingLicense, String password, String confirmationPassword)
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
                    "Phone number must contain 9 digits and start with 91/92/93 or 96.");
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
        if(!password.equals(confirmationPassword)) {
            alertDialog("Password Error",
                    "Error on password",
                    "Password and password confirmation need to be equal.");
            return false;
        }

        if(easyDriv.nameAlreadyExists(name, email)) {
            alertDialog("Name already in use",
                    "Please use another name",
                    "You can't register the same name.");
            return false;
        }

        if (easyDriv.drivingLicenseAlreadyExists(drivingLicense, email)) {
            alertDialog("Driving License already in use",
                    "Please use another driving license number",
                    "You can't have the same driving license number.");
            return false;
        }

        if (easyDriv.phoneNumberAlreadyExists(phoneNumber, email)) {
            alertDialog("Phone number already in use",
                    "Please use another phone number",
                    "You can't have the same phone number.");
            return false;
        }

        return true;
    }

    private void alertDialog(String title, String header, String description)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);

        Label label = new Label(description);
        label.setWrapText(true);

        alert.getDialogPane().setContent(label);

        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);

        alert.showAndWait();
    }

    private boolean successDialog(String title, String contentText)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(contentText);

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    public void remove(User user)
    {
        easyDriv.remove(user.getEmail());
        manageUsersController.updateTableUsers();
    }

    public void remove(Vehicle vehicle)
    {
        easyDriv.remove(vehicle.getRegisterPlate());
        manageVehiclesController.updateTableVehicles();
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

    public void edit(Vehicle vehicle)
    {
        if (easyDriv.getActualState() != SystemState.MANAGE_VEHICLE) return;
        easyDriv.editVehicle();
        editVehicleController.prepare(vehicle);
        stage.setScene(editVehicleScene);
    }

    public void addUser(String name, String email, String phoneNumber, String drivingLicense, String password, String confirmationPassword)
    {
        if(easyDriv.getActualState() != SystemState.ADD_USER) return;
        if (!validate(email, name, phoneNumber, drivingLicense, password, confirmationPassword)) return;

        if(easyDriv.emailAlreadyRegistered(email)) {
            alertDialog("E-mail already Registered",
                    "Please use another e-mail",
                    "You can't register the same e-mail.");
            return;
        }

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

    public void addVehicle(String make, String model, String registrationPlate, String fuelType, Integer numOfSeats)
    {
        if (make == null || model == null || fuelType == null || numOfSeats == null)
        {
            alertDialog("Uncompleted fields",
                    "Some fields don't have any value",
                    "Complete all fields to continue");
            return;
        }

        if(easyDriv.getActualState() != SystemState.ADD_VEHICLE) return;
        if (!Validator.registerPlatevalidation(registrationPlate)){
            alertDialog("Incorect registration plate format",
                    "Please introduce a valid registration plate",
                    "Registration plate must be like AA-00-00 or 00-AA-00 or 00-00-AA or AA-00-AA");
            return;
        }

        if (easyDriv.getVehicle(registrationPlate) != null)
        {
            alertDialog("Incorect registration plate",
                    "Please introduce a valid registration plate",
                    registrationPlate + " already registred.");
            return;
        }
        easyDriv.addVehicle(make,registrationPlate, numOfSeats, fuelType, model, true);
        if (easyDriv.getActualState() == SystemState.MANAGE_VEHICLE)
            setManageVehiclesScene();
    }

    public void editVehicle(String make, String model, String registrationPlate, String fuelType, Integer numOfSeats)
    {
        if (make == null || model == null || fuelType == null || numOfSeats == null)
        {
            alertDialog("Uncompleted fields",
                    "Some fields don't have any value",
                    "Complete all fields to continue");
            return;
        }

        if(easyDriv.getActualState() != SystemState.EDIT_VEHICLE) return;
        if (!Validator.registerPlatevalidation(registrationPlate)){
            alertDialog("Incorrect registration plate format",
                    "Please introduce a valid registration plate",
                    "Registration plate must be like AA-00-00 or 00-AA-00 or 00-00-AA or AA-00-AA");
            return;
        }

        successDialog("Edit car", "A car is edited with success!");

        easyDriv.editVehicle(make,registrationPlate, numOfSeats, fuelType, model, true);
        if (easyDriv.getActualState() == SystemState.MANAGE_VEHICLE)
            setManageVehiclesScene();
    }

    public void setManageBookingsScene()
    {
        manageBookingsController.updateLastList();
        stage.setScene(manageBookingsScene);
    }

    public void setManageProfileScene() {
        if (easyDriv.getActualState() != SystemState.MENU) return;
            easyDriv.editUser();
        if(easyDriv.getActualState() != SystemState.MANAGE_PROFILE) return;
            manageProfileController.prepare(easyDriv.getUser());

        stage.setScene(manageProfileScene);
    }

    public void onBooking()
    {
        if (easyDriv.getActualState() != SystemState.MENU) return;
            easyDriv.booking(new Booking(null,null,null,null));
        if (easyDriv.getActualState() == SystemState.BOOKING)
            stage.setScene(bookingScene);
    }

    public void onDeliver() {
        deliverController.updateTableBookings();
        stage.setScene(deliverScene);
    }

    public void book(Booking booking)
    {
        successDialog("Book", "A book was made!");
        easyDriv.booking(booking);
        setUserScene();
    }

    public void onRefreshBookings(Timestamp startTime, Timestamp endTime, String destination, Integer nrSeats)
    {
        if (destination == null || nrSeats == null)
        {
            alertDialog("Uncompleted fields",
                    "Some fields don't have any value",
                    "Complete all fields to continue");
            return;
        }
        if (startTime.after(endTime))
        {
            alertDialog("Incorrect dates",
                    "Please introduce a start time before end time",
                    "Start date must be before end date.");
            return;
        }
        if (startTime.before(Timestamp.from(Instant.now())))
        {
            alertDialog("Incorrect start date",
                    "Please introduce a start time after current time",
                    "Start date must be after current time.");
            return;
        }
        LocalDateTime futureDate = LocalDateTime.now().plusMonths(1);
        Timestamp oneMonthFromNow = Timestamp.valueOf(futureDate);

        if (endTime.after(oneMonthFromNow))
        {
            alertDialog("Incorrect end date",
                    "Please introduce a end time within one month from current time",
                    "Start date must be after within one month from current time.");
            return;
        }
        easyDriv.search(startTime,endTime,destination,nrSeats);
        bookingController.updateTableBookings();
    }

    public void onRefreshBookings(Timestamp startTime, Timestamp endTime, String destination, String user, String regPlate){
        easyDriv.search(startTime, endTime, destination, user, regPlate);
        manageBookingsController.updateTableBookings();
    }

    public void pickerEmpty()
    {
        alertDialog("Uncompleted fields",
                "Some fields don't have any value",
                "Complete all fields to continue");
    }

    public void deliverCheckboxesNotFilled() {
        alertDialog("Please fill the information",
                "This deliver needs more information",
                "If there is none information to report, please select 'All went well'");
    }

    public void remove(Booking booking) {
        easyDriv.remove(booking.getStartDatatime(),booking.getVehicle().getRegisterPlate());
        manageBookingsController.listAllBookings();
    }

    public void edit(Booking booking) {
        if (easyDriv.getActualState() != SystemState.MANAGE_BOOKINGS) return;
        easyDriv.editBooking();
        editBookingController.prepare(booking);
        stage.setScene(editBookingScene);
    }

    public void editBookingAndRefresh(Booking booking) {
        easyDriv.editBooking(booking);
        manageBookingsController.updateLastList();
        stage.setScene(manageBookingsScene);
    }

    public void fillMaintenenceBeforeDeliver(Booking booking) {
        if(easyDriv.getActualState() != SystemState.DELIVER) {
            return;
        }
        maintenanceController.setBooking(booking);
        stage.setScene(maintenanceScene);
    }

    public void deliver(Booking booking) {
        if(easyDriv.getActualState() != SystemState.DELIVER) {
            return;
        }
        easyDriv.deliver(booking);

        if(easyDriv.getActualState() != SystemState.MENU) {
            return;
        }
        stage.setScene(userScene);
    }

    public void checkMaintenance(Vehicle vehicle) {
        if(easyDriv.getActualState() != SystemState.MANAGE_VEHICLE) {
            return;
        }
        easyDriv.checkMaintenance();
        if(easyDriv.getActualState() != SystemState.CHECK_MAINTENANCE) {
            return;
        }

        editMaintenanceController.prepare(vehicle);
        stage.setScene(editMaintenanceScene);
    }

    public void editMaintenance(Vehicle vehicle) {
        if(easyDriv.getActualState() != SystemState.CHECK_MAINTENANCE) {
            return;
        }
        easyDriv.editMaintenance(vehicle);
        if(easyDriv.getActualState() != SystemState.MANAGE_VEHICLE) {
            return;
        }
        manageVehiclesController.updateTableVehicles();
        stage.setScene(manageVehiclesScene);
    }

    public void askForJustification() {
        alertDialog("Vehicle not operational",
                "Fill in the information",
                "If vehicle is not operational we need to know why. (Min 15 characters)");
    }
}
