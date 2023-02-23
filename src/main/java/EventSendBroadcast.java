public class EventSendBroadcast {
    private byte[] message;

    public EventSendBroadcast(byte[] message) {
        this.message = message;
    }

    public byte[] getMessage() {
        return message;
    }
}
