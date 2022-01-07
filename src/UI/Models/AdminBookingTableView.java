package UI.Models;

import Logic.Data.Booking.Booking;
import Logic.Data.User.User;
import Logic.Data.Vehicle.Vehicle;
import UI.ScenesControllers;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class AdminBookingTableView
{
    private String name;
    private String email;
    private String regPlate;
    private String startDate;
    private String endDate;
    private Button btnEdit;
    private Button btnRemove;

    private ScenesControllers scenesControllers;
    private Booking booking;

    public AdminBookingTableView(ScenesControllers scenesControllers, Booking booking, ImageView imgEdit, ImageView imgRemove)
    {
        this.scenesControllers = scenesControllers;
        this.booking = booking;

        User user;
        Vehicle vehicle = booking.getVehicle();
        ArrayList<User> users = booking.getUsers();
        if (users != null)
            user = users.get(0);
        else return;

        this.name = user.getName();
        this.email = user.getEmail();
        this.regPlate = vehicle.getRegisterPlate();
        this.startDate = booking.getStartDatatime().toString();
        this.endDate = booking.getEndDatatime().toString();

        imgEdit.setFitHeight(20);
        imgEdit.setFitWidth(20);
        imgRemove.setFitHeight(20);
        imgRemove.setFitWidth(20);

        btnEdit = new Button("", imgEdit);
        btnRemove = new Button("", imgRemove);
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getRegPlate()
    {
        return regPlate;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public Button getBtnEdit()
    {
        return btnEdit;
    }

    public Button getBtnRemove()
    {
        return btnRemove;
    }
}
