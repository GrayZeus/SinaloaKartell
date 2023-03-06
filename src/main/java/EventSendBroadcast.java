import java.math.BigInteger;

public class EventSendBroadcast {
    private final BigInteger[] message;

    public EventSendBroadcast(BigInteger[] message) {
        this.message = message;
    }

    public BigInteger[] getMessage() {
        return message;
    }
}
