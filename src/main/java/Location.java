import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import java.lang.reflect.Method;
import java.math.BigInteger;


public class Location extends Subscriber{
    private RSAKey privateKey;
    private RSAKey publicKey;
    private String locationID;
    private String deliveryRequired;
    private EventBus eventBus;
    private BigInteger[] cipher;
    private RSA rsa = new RSA();

    public Location(String locationID, RSAKey privateKey) {
        this.privateKey = privateKey;
        this.locationID = locationID;
        this.deliveryRequired = "LOCATIONX" + locationID +  "XREQUESTXONEHUNDREDX";
    }//end constructor

    public void doOrder(){
        cipher = rsa.encrypt(deliveryRequired, publicKey);
        sendOrder(cipher);
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void sendOrder(BigInteger[]  message) {
        eventBus.post(new EventPlaceOrder(cipher));
    }

    @Subscribe
    public void receive(EventSendBroadcast eventSendBroadcast) {
        System.out.println("Location: " + locationID + " has received encrypted message.");
        BigInteger[] cipher = eventSendBroadcast.getMessage();
        String encryptedText = rsa.decrypt(cipher, privateKey);
        System.out.println("Encrypted Message is: ");
        System.out.println(encryptedText);
    }

    @Subscribe
    public void receive(EventSendPublicKey eventSendPublicKey) {
        System.out.println("Location: " + locationID + " has received Public RSA Key.");
        publicKey = eventSendPublicKey.getRsaKey();
        System.out.println(publicKey);
    }
}//end class
