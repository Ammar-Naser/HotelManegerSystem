package hoelbookingsystem.demo;


public class StandardRoom extends Room {
    private boolean hasWiFi;

    public StandardRoom(int roomNumber, double pricePerNight, int capacity, boolean hasWiFi) {
        super(roomNumber, pricePerNight, capacity);
        this.hasWiFi = hasWiFi;
    }

    @Override
    public double calculateCost(int nights) {
        return pricePerNight * nights;
    }
    
    @Override
    public String getAmenities(){
        if(hasWiFi)return "WI-FI";
        else return "";
    }
    
    //getters
    public boolean hasWiFi() {
        return hasWiFi; 
    }
}