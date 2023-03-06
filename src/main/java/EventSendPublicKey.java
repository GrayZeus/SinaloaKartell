public class EventSendPublicKey {
    private final RSAKey rsaKey;

    public EventSendPublicKey(RSAKey rsaKey) {
        this.rsaKey = rsaKey;
    }

    public RSAKey getRsaKey() {
        return rsaKey;
    }
}
