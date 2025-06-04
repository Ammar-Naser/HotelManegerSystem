package hoelbookingsystem.demo;


public class SuiteRoom extends Room {
    private boolean hasJacuzzi;
    private boolean hasLivingArea;

    public SuiteRoom(int roomNumber, double pricePerNight, int capacity,
                    boolean hasJacuzzi, boolean hasLivingArea) {
        super(roomNumber, pricePerNight, capacity);
        this.hasJacuzzi = hasJacuzzi;
        this.hasLivingArea = hasLivingArea;
    }

    @Override
    public double calculateCost(int nights) {
        return pricePerNight * nights * 1.5;
    }

    @Override
    public String getAmenities(){
        String Amenities="";
        if(hasJacuzzi)Amenities+="Jacuzzi ";
        if(hasLivingArea)Amenities+="Living Area ";
        return Amenities;
    }
    
    //getters
    public boolean hasJacuzzi() {
        return hasJacuzzi; 
    }
    public boolean hasLivingArea() {
        return hasLivingArea; 
    }
}