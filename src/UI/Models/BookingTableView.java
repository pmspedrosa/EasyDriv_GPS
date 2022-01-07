package UI.Models;

import Logic.Data.Booking.Booking;
import UI.ScenesControllers;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class BookingTableView
{
    private String make;
    private String model;
    private String regPlate;
    private String startDate;
    private String deliverDate;
    private ImageView shared;
    private Button btnBook;

    private ScenesControllers scenesControllers;
    private Booking booking;

    public BookingTableView(ScenesControllers scenesControllers, Booking booking, ImageView imgShared, ImageView imgBook)
    {
        this.scenesControllers = scenesControllers;
        this.booking = booking;

        this.make = booking.getVehicle().getMake();
        this.model = booking.getVehicle().getModel();
        this.regPlate = booking.getVehicle().getRegisterPlate();
        this.startDate = booking.getStartDatatime().toString();
        this.deliverDate = booking.getEndDatatime().toString();

        imgShared.setFitHeight(20);
        imgShared.setFitWidth(20);
        imgBook.setFitHeight(20);
        imgBook.setFitWidth(20);

        btnBook = new Button("", imgBook);
        if (booking.isShared())
            shared = imgShared;

        btnBook.setOnMouseClicked(e -> {
            scenesControllers.book(booking);
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

    public String getDeliverDate() { return deliverDate; }

    public ImageView getShared()
    {
        return shared;
    }

    public Button getBtnBook()
    {
        return btnBook;
    }
}
