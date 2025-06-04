package hoelbookingsystem.demo;

import java.lang.reflect.Array;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class Main extends Application {


    public static Stage primary;
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Room> rooms = new ArrayList<>();
    public static HotelManager manage;

    public static void main(String[] args) {

        //for GUI
        HotelManager manager = new HotelManager(rooms,users);

        // Add rooms
        manager.addRoom(new StandardRoom(301, 100.0, 2, true));
        manager.addRoom(new DeluxeRoom(210, 200.0, 4, true, true));
        manager.addRoom(new SuiteRoom(150, 300.0, 2, true, true));

        // Add users
        Guest guest1 = new Guest("Ammar", "Ammar@gmail.com","123");
        Guest guest2 = new Guest( "Kareem", "Kareem@gmail.com","123");
        Admin admin = new Admin( "Ganna", "Ganna@gmail.com","123");

        manager.addUser(guest1);
        manager.addUser(guest2);
        manager.addUser(admin);

        // Create bookings
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        LocalDate nextWeek = today.plusDays(7);

        Booking booking1 = manager.createBooking(guest1, manager.getRooms().get(0), today, tomorrow);
        Booking booking2 = manager.createBooking(guest2, manager.getRooms().get(1), today, nextWeek);

        // Apply offers
        try{
            booking1.setOffer(manager.getOffers().get(0));
            booking1.calculateTotal();
            booking2.setOffer(manager.getOffers().get(1));
            booking2.calculateTotal();
            // Set payment methods
            booking1.setPaymentMethod(new CreditCardPayment("4111111111111111", "Ammar"));
            booking1.processPayment();
            booking2.setPaymentMethod(new PayPalPayment("Kareem@gmail.com"));
            booking2.processPayment();

        }
        catch (NoOffersAppliedException e){
            System.out.println("No Offers Applied Exception");
        }
        catch (NullPointerException ex){
            System.out.println("Booking is null");
        }
        catch (NoPaymentMethodException e){
            System.out.println("No Payment Method Exception");
        }

        // Sort and display rooms
        manager.sortRooms();
        System.out.println("\nRooms sorted by price:");
        for (Room room : manager.getRooms()) {
            System.out.println("Room " + room.getRoomNumber() + ": $" + room.getPricePerNight() + "/night");
        }

        // Sort and display bookings
        manager.sortBookingsByDate();
        System.out.println("\nBookings sorted by date:");
        for (Booking booking : manager.getBookings()) {
            System.out.println("Booking #" + booking.getBookingId() +
                    ": " + booking.getStartDate() + " to " + booking.getEndDate() +
                    " - Total: $" + booking.getTotalCost());
        }

        // Admin actions
        System.out.println(" ");
        admin.manageRooms(manager.getRooms().get(0), "cleaning");
        admin.manageUsers(guest1, "sending WIFI password");

        launch();
    }

    public void initUsers() {
        users.add(new Guest("Ammar", "Ammar@gmail.com", "123"));
        users.add(new Admin("Ganna", "Ganna@gmail.com", "123"));
        users.add(new Guest("Sara", "Sara@gmail.com", "123"));
        users.add(new Admin("Kareem", "Kareem@gmail.com", "123"));
        users.add(new Guest("test Guest", "123", "123"));
        users.add(new Admin("test Admin", "12", "12"));
        users.add(new Guest("Guest", "guest", "123"));
        users.add(new Admin("Admin", "admin", "123"));
    }

    public void inithotelRooms() {
        rooms.add(new DeluxeRoom(101, 2000, 3, true, true));
        rooms.add(new DeluxeRoom(423, 2500, 6, true, true));
        rooms.add(new DeluxeRoom(67, 400, 2, false, false));
        rooms.add(new DeluxeRoom(145, 900, 2, false, true));
        rooms.add(new DeluxeRoom(4543, 1500, 5, true, false));
        rooms.add(new StandardRoom(23, 1344, 5, true));
        rooms.add(new StandardRoom(223, 3455, 8, true));
        rooms.add(new StandardRoom(721, 2000, 2, false));
        rooms.add(new StandardRoom(566, 520, 2, false));
        rooms.add(new StandardRoom(657, 1500, 5, true));
        rooms.add(new SuiteRoom(213, 3333, 3, true, true));
        rooms.add(new SuiteRoom(433, 2222, 2, true, false));
        rooms.add(new SuiteRoom(435, 1111, 2, true, false));
        rooms.add(new SuiteRoom(54, 4333, 6, false, true));
        rooms.add(new SuiteRoom(897, 1546, 2, true, false));

    }



    @Override
    public void start(Stage primaryStage) {
        initUsers();
        inithotelRooms();
        manage = new HotelManager(rooms, users);

        SignInterfacePane signImplmentaation = new SignInterfacePane();
        StackPane root = signImplmentaation.SignPane(primaryStage);

        Scene scene = new Scene(root, 800, 650);
        primaryStage.setTitle("Booking App");
        primaryStage.setScene(scene);
        primaryStage.show();
        primary = primaryStage;
    }

}

