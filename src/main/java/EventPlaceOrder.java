import java.math.BigInteger;

public class EventPlaceOrder {

    private final BigInteger[] cipher;

    public EventPlaceOrder(BigInteger[] cipher) {
        this.cipher = cipher;
    }

    public BigInteger[] getCipher() {
        return cipher;
    }
}
