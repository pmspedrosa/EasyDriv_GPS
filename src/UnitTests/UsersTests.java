package UnitTests;

import Logic.Data.User.User;
import Logic.Data.User.UserManager;
import Logic.States.ManageUsers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsersTests {

    private UserManager userManager;

    public UsersTests() {
        userManager = new UserManager();
    }

    // ADD USER
    @Test
    void addUser(){
        userManager.addUser("João", "user2@isec.pt", "123456789", "555555", "12345");
        Assertions.assertEquals(1, userManager.listUsers().size());
    }

    // EDIT USER
    @Test
    void editUser(){
        User user = new User(false, "João", "user2@isec.pt", "123456789", "555555", "12345");
       // var userGet = us
    }

}
