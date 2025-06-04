package hoelbookingsystem.demo;


public class DeluxeRoom extends Room {
    private boolean hasBalcony;
    private boolean hasAirConditioner;

    public DeluxeRoom(int roomNumber, double pricePerNight, int capacity,
                      boolean hasBalcony, boolean hasAirConditioner) {
        super(roomNumber, pricePerNight, capacity);
        this.hasBalcony = hasBalcony;
        this.hasAirConditioner = hasAirConditioner;
    }

    @Override
    public double calculateCost(int nights) {
        return pricePerNight * nights * 1.2;
    }
    
    @Override
    public String getAmenities() {
        String Amenities = " ";
        if (hasAirConditioner) Amenities += "Air Conditioner , ";
        if (hasBalcony) Amenities += " Balcony";
        return Amenities;
    }
    
    //getters
    public boolean hasBalcony() {
        return hasBalcony;
    }

    public boolean hasMiniBar() {
        return hasAirConditioner;
    }
}
