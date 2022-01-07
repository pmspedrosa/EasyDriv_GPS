package UI.Models;

import Logic.Data.Booking.Booking;
import UI.ScenesControllers;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.text.SimpleDateFormat;

public class DeliverBookingTableView
{
    private String make;
    private String model;
    private String regPlate;
    private String startDate;
    private String endDate;
    private Button btnDeliver;

    private ScenesControllers scenesControllers;
    private Booking booking;

    public DeliverBookingTableView(ScenesControllers scenesControllers, Booking booking, ImageView imgDeliver)
    {
        this.scenesControllers = scenesControllers;
        this.booking = booking;

        this.make = booking.getVehicle().getMake();
        this.model = booking.getVehicle().getModel();
        this.regPlate = booking.getVehicle().getRegisterPlate();
        this.startDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(booking.getStartDatatime());
        this.endDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(booking.getEndDatatime());

        imgDeliver.setFitHeight(20);
        imgDeliver.setFitWidth(20);

        btnDeliver = new Button("", imgDeliver);
        btnDeliver.setOnMouseClicked(e -> {
            scenesControllers.fillMaintenenceBeforeDeliver(booking);
        });
    }

    public String getMake()
    {
        return make;
    }

    public String getModel()
    {
        return model;
    }

    public String getRegPlate()
    {
        return regPlate;
    }

    public String getStartDate() { return startDate; }

    public String getEndDate() { return endDate; }

    public Button getBtnDeliver()
    {
        return btnDeliver;
    }
}
