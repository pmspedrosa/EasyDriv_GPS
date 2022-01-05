package UnitTests;

import Logic.Data.User.UserManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UsersTests {

    private UserManager userManager;

    public UsersTests() {
        userManager = new UserManager();
        userManager.addUser("Liliana", "liliana@isec.pt", "913464545", "1", "Aa12345!");
    }

    // ADD USER
    @Test
    void addUser(){
        Assertions.assertTrue(userManager.addUser("Joao", "user2@isec.pt", "912839959", "1", "Aa12345!"));
    }

    @Test
    void addUserFail(){
        Assertions.assertFalse(userManager.addUser(String.valueOf(232734), "user2", "123456789", "555555", "1234"));
    }

    // EDIT USER
    @Test
    void editUser(){
        var user = userManager.getUser("liliana@isec.pt");
        Assertions.assertTrue(userManager.editUser(user.getEmail(), "Joao", "912384858", "9", "Aa12345!"));
    }

    // REMOVE USER
    @Test
    void removeUser(){
        Assertions.assertTrue(userManager.removeUser("liliana@isec.pt"));
    }

}
