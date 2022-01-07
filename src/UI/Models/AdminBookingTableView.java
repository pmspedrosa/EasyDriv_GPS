package UI.Models;

import Logic.Data.Booking.Booking;
import Logic.Data.User.User;
import Logic.Data.Vehicle.Vehicle;
import UI.ScenesControllers;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AdminBookingTableView
{
    private String name;
    private String destination;
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
        this.destination = booking.getDestination();
        this.regPlate = vehicle.getRegisterPlate();
        this.startDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(booking.getStartDatatime());
        this.endDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(booking.getEndDatatime());

        if(imgEdit != null) {
            imgEdit.setFitHeight(20);
            imgEdit.setFitWidth(20);
        }
        if(imgRemove != null) {
            imgRemove.setFitHeight(20);
            imgRemove.setFitWidth(20);
        }

        btnEdit = new Button("", imgEdit);
        btnRemove = new Button("", imgRemove);

        btnRemove.setOnAction(e -> {
            scenesControllers.remove(booking);
        });

        btnEdit.setOnAction(e -> {
            scenesControllers.edit(booking);
        });
    }

    public String getName()
    {
        return name;
    }

    public String getDestination()
    {
        return destination;
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
