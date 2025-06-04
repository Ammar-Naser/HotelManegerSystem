package hoelbookingsystem.demo;

public class PercentageDiscountOffer implements Offer {
    private double discountRate;

    public PercentageDiscountOffer(double discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public void applyOffer(Booking booking) {
        double discount = booking.getTotalCost() * discountRate;
        booking.setTotalCost(booking.getTotalCost() - discount);
        System.out.println("Applied " + (discountRate * 100) + "% discount: -$" + discount);
    }
}