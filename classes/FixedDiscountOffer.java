package hoelbookingsystem.demo;


public class FixedDiscountOffer implements Offer {
    private double discountAmount;

    public FixedDiscountOffer(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public void applyOffer(Booking booking) {
        booking.setTotalCost(booking.getTotalCost() - discountAmount);
        System.out.println("Applied fixed discount: -$" + discountAmount);
    }
}
