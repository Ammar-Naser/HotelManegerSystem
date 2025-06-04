package hoelbookingsystem.demo;



public class CreditCardPayment implements PaymentMethod {
    private String cardNumber;
    private String cardHolder;

    public CreditCardPayment(String cardNumber, String cardHolder) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount + 
                         " with card ending in " + cardNumber.substring(cardNumber.length() - 4));
        return true;
    }   
}


