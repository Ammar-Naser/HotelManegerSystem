package hoelbookingsystem.demo;


public class Admin extends User {
    public Admin(String name, String email, String password) {
        super(name, email, password);
    }

    public void manageRooms(Room room, String action) {
        System.out.println("Admin " + name + " performing " + action + " on room " + room.getRoomNumber());
    }

    public void manageUsers(User user, String action) {
        System.out.println("Admin " + name + " performing " + action + " on user " + user.getName());
    }

    @Override
    public void displayInfo() {
        System.out.println("Admin: " + name + " (ID: " + userId + ")");
    }
}
