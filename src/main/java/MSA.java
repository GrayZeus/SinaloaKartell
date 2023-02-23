import com.google.common.eventbus.Subscribe;

public class MSA {

    String publicKey;

    /*
    @Subscribe
    public void receive(EventSendPublicKey eventSendPublicKey) {
    }

     */

    @Subscribe
    public void receive(EventSendBroadcast eventSendBroadcast) {
        // analyze
    }

}
