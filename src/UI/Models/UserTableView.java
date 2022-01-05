package UI.Models;

import Logic.Data.User.User;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UserTableView
{
    private final String email;
    private final String name;
    private final String phoneNumber;
    private final ImageView imgEdit;
    private final ImageView imgRemove;

    public UserTableView(User user, Image edit, Image remove)
    {
        email = user.getEmail();
        name = user.getName();
        phoneNumber = user.getPhoneNumber();
        imgEdit = new ImageView(edit);
        imgRemove = new ImageView(remove);
        
        imgEdit.setFitHeight(20);
        imgEdit.setFitWidth(20);
        imgRemove.setFitHeight(20);
        imgRemove.setFitWidth(20);
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

    public ImageView getImgEdit()
    {
        return imgEdit;
    }

    public ImageView getImgRemove()
    {
        return imgRemove;
    }
}
