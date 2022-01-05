package UI.Models;

import Logic.Data.Vehicle.Vehicle;
import UI.ScenesControllers;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class VehicleTableView
{
    private final String make;
    private final String model;
    private final String registrationPlate;
    private final Button btnEdit;
    private final Button btnMaintenance;
    private final Button btnRemove;

    private ScenesControllers scenesControllers;
    private Vehicle vehicle;

    public VehicleTableView(ScenesControllers scenesControllers, Vehicle vehicle, ImageView imgEdit, ImageView imgMaintenance, ImageView imgRemove)
    {
        this.scenesControllers = scenesControllers;
        this.vehicle = vehicle;

        make = vehicle.getMake();
        model = vehicle.getModel();
        registrationPlate = vehicle.getRegisterPlate();

        imgEdit.setFitHeight(20);
        imgEdit.setFitWidth(20);
        imgMaintenance.setFitHeight(20);
        imgMaintenance.setFitWidth(20);
        imgRemove.setFitHeight(20);
        imgRemove.setFitWidth(20);

        btnEdit = new Button("", imgEdit);
        btnMaintenance = new Button("", imgMaintenance);
        btnRemove = new Button("", imgRemove);

        btnEdit.setOnMouseClicked(e -> {
            scenesControllers.edit(vehicle);

        });

        btnRemove.setOnMouseClicked(e -> {
            scenesControllers.remove(vehicle);
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

    public String getRegistrationPlate()
    {
        return registrationPlate;
    }

    public Button getBtnEdit()
    {
        return btnEdit;
    }

    public Button getBtnMaintenance()
    {
        return btnMaintenance;
    }

    public Button getBtnRemove()
    {
        return btnRemove;
    }
}
