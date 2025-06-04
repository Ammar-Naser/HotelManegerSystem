package hoelbookingsystem.demo;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class HotelManager {
    private List<Room> rooms;
    private List<User> users;
    private List<Booking> bookings;
    private List<Offer> offers;

    public HotelManager() {
        this.rooms = new ArrayList<>();
        this.users = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.offers = new ArrayList<>();
        
        offers.add(new PercentageDiscountOffer(0.1));
        offers.add(new FixedDiscountOffer(50));
    }
    public HotelManager(ArrayList<Room> rooms, ArrayList<User> users) {
        this.rooms = rooms;
        this.users = users;
        this.bookings = new ArrayList<>();
        this.offers = new ArrayList<>();

        offers.add(new PercentageDiscountOffer(0.1));
        offers.add(new FixedDiscountOffer(50));
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public boolean removeRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                rooms.remove(room);
                return true;
            }
        }
        return false;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean removeUser(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                users.remove(user);
                return true;
            }
        }
        return false;
    }

    public Booking createBooking(Guest guest, Room room, LocalDate startDate, LocalDate endDate) {
        if (!room.isAvailable()) {
            System.out.println("Room " + room.getRoomNumber() + " is not available!");
            return null;
        }
        
        Booking booking = guest.bookRoom(room, startDate, endDate);
        bookings.add(booking);
        room.setAvailable(false);
        return booking;
    }

    public boolean cancelBooking(int bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                booking.getRoom().setAvailable(true);
                bookings.remove(booking);
                booking.getGuest().getBookingHistory().remove(booking);
                return true;
            }
        }
        return false;
    }

    public void sortRooms() {
        Collections.sort(rooms); 
    }

    public void sortBookingsByDate() {
        Collections.sort(bookings);
    }

    //getters
    public List<Room> getRooms() {
        return rooms;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public List<Offer> getOffers() {
        return offers;
    }
}
