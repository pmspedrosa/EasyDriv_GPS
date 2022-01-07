package UnitTests;

import Logic.Controller;
import Logic.Data.Booking.Booking;
import Logic.Data.Booking.BookingManager;
import Logic.Data.User.User;
import Logic.Data.Vehicle.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class BookingTests {
    BookingManager bookingManager;
    User user;
    Vehicle vehicle;
    Calendar calAtual;
    Calendar cal;

    public BookingTests() {
        bookingManager = new BookingManager();
        user = new User(false, "Renato", "renato@isec.pt", "912312343", "1", "Renas1!");
        vehicle = new Vehicle("Fiat", "AA-01-AA", 5, "Dielsel", "Punto", true);

        calAtual = Calendar.getInstance();

        cal = calAtual;
        cal.setTime(cal.getTime());
        cal.add(Calendar.HOUR, 1);
       // bookingManager.addBooking(new Timestamp(calAtual.getTime().getTime()), new Timestamp(cal.getTime().getTime()), "Coimbra", user, vehicle);
    }

    @Test
    public void bookCar(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(cal.getTime());
        cal.add(Calendar.HOUR, 1);

        //Assertions.assertTrue(bookingManager.addBooking(new Timestamp(Calendar.getInstance().getTime().getTime()), new Timestamp(cal.getTime().getTime()), "Coimbra", user, vehicle));
    }

    @Test
    public void bookWithoutCarAvailable(){
//        Assertions.assertNull(bookingManager.getVehiclesAvailable(new Timestamp(Calendar.getInstance().getTime().getTime()), new Timestamp(cal.getTime().getTime())));
    }

    @Test
    public void bookCarWithSharedDestination(){
        user = new User(false, "Renato", "renato@isec.pt", "912312343", "1", "Renas1!");
        Booking booking = bookingManager.getBooking(new Timestamp(calAtual.getTime().getTime()), "AA-01-AA");
        //Assertions.assertTrue(bookingManager.addUserToBooking(user, booking));

    }

    @Test
    public void deliverCar(){
        Booking booking = bookingManager.getBooking(new Timestamp(calAtual.getTime().getTime()), "AA-01-AA");
        Assertions.assertTrue(bookingManager.deliver(booking));
    }
}
