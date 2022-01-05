package UnitTests;

import Logic.Controller;
import org.junit.jupiter.api.Test;

public class BookingTests {
    Controller controller;

    public BookingTests() {
        controller = new Controller();
        controller.addUser("teste3", "teste3@isec.pt", "", "", "12345");
    }

    @Test
    public void bookCar(){
        // TODO
    }
}
