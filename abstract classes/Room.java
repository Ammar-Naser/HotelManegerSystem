package hoelbookingsystem.demo;


public abstract class Room implements Comparable<Room> {
    protected int roomNumber;
    protected double pricePerNight;
    protected int capacity;
    protected boolean isAvailable;

    public Room(int roomNumber, double pricePerNight, int capacity) {
        this.roomNumber = roomNumber;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
        this.isAvailable = true;
    }

    public abstract double calculateCost(int nights);

    @Override
    public int compareTo(Room other) {
        return Double.compare(this.pricePerNight, other.pricePerNight);
    }

    public String getAmenities() {
        return null;
    }
    
    //getters
    public int getRoomNumber() {
        return roomNumber;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
