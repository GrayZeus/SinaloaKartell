import com.google.common.eventbus.Subscribe;
import java.lang.reflect.Method;


public class Location extends Subscriber{

    private RSAKey privateKey;
    private RSAKey publicKey;
    private String locationID;
    private String deliveryRequired;

    private RSA rsa = new RSA();


    public Location(String locationID, RSAKey privateKey) {
        this.privateKey = privateKey;
        this.locationID = locationID;
        this.deliveryRequired = "LOCATIONX" + locationID +  "XREQUESTXONEHUNDREDX";

    }

        @Subscribe
        public void receive(EventSendBroadcast eventSendBroadcast) {
            System.out.println("Location: " + locationID + " has received encrypted message.");
            byte[] cipher = eventSendBroadcast.getMessage();
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


    }
