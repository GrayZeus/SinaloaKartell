import java.math.BigInteger;

public class EventSendPublicKey {
    private RSAKey rsaKey;

    public EventSendPublicKey(RSAKey rsaKey) {
        this.rsaKey = rsaKey;
    }

    public RSAKey getRsaKey() {
        return rsaKey;
    }
}
