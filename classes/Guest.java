package hoelbookingsystem.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Guest extends User {
    private List<Booking> bookingHistory;

    public Guest(String name, String email, String Password) {
        super(name, email, Password);
        this.bookingHistory = new ArrayList<>();
    }

    public Booking bookRoom(Room room, LocalDate startDate, LocalDate endDate) {
        Booking booking = new Booking(this, room, startDate, endDate);
        bookingHistory.add(booking);
        return booking;
    }

    public List<Booking> getBookingHistory() {
        return bookingHistory;
    }

    @Override
    public void displayInfo() {
        System.out.println("Guest: " + name + " (ID: " + userId + ")");
    }
}