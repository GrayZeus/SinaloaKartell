import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Location extends Subscriber{
    private RSAKey privateKey;
    private RSAKey publicKey;
    private String locationID;
    private String deliveryRequired;
    private EventBus eventBus;
    private BigInteger[] cipher;
    private RSA rsa;
    private List<Drugs> oneGramDrugs;

    public Location(String locationID, RSAKey privateKey, EventBus eventBus) {
        this.privateKey = privateKey;
        this.locationID = locationID;
        this.deliveryRequired = "LOCATIONX" + locationID +  "XREQUESTXONEHUNDREDX";
        this.eventBus = eventBus;
        this.oneGramDrugs = new ArrayList<>();
        rsa = new RSA();
    }//end constructor

    public void doOrder(){
        cipher = rsa.encrypt(deliveryRequired, publicKey);
        sendOrder(cipher);
    }

    private void sendOrder(BigInteger[] cipher) {
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

    @Subscribe
    public void receive(EventPlaceOrder eventPlaceOrder){
        System.out.println("Order has been received! From location " + locationID + ".");
        BigInteger[] receivedOrderCipher = eventPlaceOrder.getCipher();
        String decryptedOrderText = rsa.decrypt(receivedOrderCipher,privateKey);
        System.out.println("Order has been decrypted, here is the message:");
        System.out.println(decryptedOrderText);

    }


    public void addDrugs(Collection<Drugs> drugs) {
        oneGramDrugs.addAll(drugs);
    }

    public void setDrugs(List<Drugs> drugs) {
        this.oneGramDrugs = drugs;
    }
}//end class
