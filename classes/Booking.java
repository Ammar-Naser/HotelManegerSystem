package hoelbookingsystem.demo;


import java.time.LocalDate;

public class Booking implements Comparable<Booking> {
    private static int nextId = 1;
    private int bookingId;
    private Guest guest;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalCost;
    private PaymentMethod paymentMethod;
    private Offer offer;

     public Booking(Guest guest, Room room, LocalDate startDate, LocalDate endDate) {
        this.bookingId = nextId++;
        this.guest = guest;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    
        try{
        calculateTotal();
    }catch(NoOffersAppliedException e){System.out.println("No Offers Availables");}
    }
    
    public void calculateTotal() throws NoOffersAppliedException {
        int nights = (int) (endDate.toEpochDay() - startDate.toEpochDay());
        this.totalCost = room.calculateCost(nights);

        if (offer != null) {
            offer.applyOffer(this);
        }
        else{throw new NoOffersAppliedException("No Offers Availables");}
    }

    public boolean processPayment() throws NoPaymentMethodException {
        boolean p = false;
        if (paymentMethod != null) {
            return paymentMethod.processPayment(totalCost);
        }
        else{throw new NoPaymentMethodException("No payment method selected!");}
        
    }
    @Override
    public int compareTo(Booking other) {
        return this.startDate.compareTo(other.startDate);
    }

    // Getters and setters
    public int getBookingId() {
        return bookingId; 
    }
    public Guest getGuest() {
        return guest; 
    }
    public Room getRoom() {
        return room; 
    }
    public LocalDate getStartDate() {
        return startDate; 
    }
    public LocalDate getEndDate() {
        return endDate; 
    }
    public double getTotalCost() {
        return totalCost; 
    }
    
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost; 
    }
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod; 
    }
    public void setOffer(Offer offer) {
        this.offer = offer; 
    }
}

 class NoOffersAppliedException extends Exception {
    public NoOffersAppliedException(String message) {
        super(message);
    }
}
 class NoPaymentMethodException extends Exception {
    public NoPaymentMethodException(String message) {
        super(message);
    }
}
