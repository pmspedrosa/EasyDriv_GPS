package UnitTests;

import Logic.Controller;
import Logic.Data.User.User;
import Logic.EasyDriv;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthenticationTests {
    private Controller controller;

    public AuthenticationTests(){
        // BASE STATE
        controller = new Controller();
        controller.addUser("admin", "admin@isec.pt", "", "", "1234");
    }

    @Test
    public void loginSuccessful(){
        Assertions.assertTrue(controller.login("admin@isec.pt", "1234"));
    }

    @Test
    public void loginFail(){
        Assertions.assertFalse(controller.login("admin@isec.pt", "3444"));
    }
}
