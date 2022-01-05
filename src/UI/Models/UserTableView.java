package UI.Models;

import Logic.Data.User.User;
import UI.ScenesControllers;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class UserTableView
{
    private final String email;
    private final String name;
    private final String phoneNumber;
    private final Button btnEdit;
    private final Button btnRemove;

    private ScenesControllers scenesControllers;
    private User user;

    public UserTableView(ScenesControllers scenesControllers, User user, ImageView imgEdit, ImageView imgRemove)
    {
        this.scenesControllers = scenesControllers;
        this.user = user;

        email = user.getEmail();
        name = user.getName();
        phoneNumber = user.getPhoneNumber();

        imgEdit.setFitHeight(20);
        imgEdit.setFitWidth(20);
        imgRemove.setFitHeight(20);
        imgRemove.setFitWidth(20);

        btnEdit = new Button("", imgEdit);
        btnRemove = new Button("", imgRemove);

        btnEdit.setOnMouseClicked(e -> {
          scenesControllers.edit(user.getEmail());

        });

        btnRemove.setOnMouseClicked(e -> {
            scenesControllers.remove(user);
        });
    }


    public String getEmail()
    {
        return email;
    }

    public String getName()
    {
        return name;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
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
