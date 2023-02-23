import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import jdk.jfr.Event;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;


public class Base extends Subscriber {
    private EventBus eventBus;
    private Location[] locations = new Location[20];
    private RSAKey privateKey;
    private RSA rsa;

    public Base(EventBus eventBus) {
        this.eventBus = eventBus;
        instantiateLocations();
        Arrays.stream(locations).forEach(this::addSubscriber);

    }//end constructor



    @Subscribe
    public void receive(EventPlaceOrder eventPlaceOrder) {
        System.out.println("Base: has received encrypted message from Location.");
        BigInteger[] cipher = eventPlaceOrder.getCipher();
        String encryptedText = rsa.decrypt(cipher, privateKey);
        System.out.println("Encrypted Message is: ");
        System.out.println(encryptedText);
        String destination = analyseEncryptedText(encryptedText);



    }

    public String analyseEncryptedText(String encryptedText){
        switch(encryptedText){
            case "LOCATIONXONEXREQUESTXONEHUNDREDX":
                return "ONE";
            case "LOCATIONXTWOXREQUESTXONEHUNDREDX":
                return "TWO";

            case "LOCATIONXTHREEXREQUESTXONEHUNDREDX":
                return "ONE";
        }
        return encryptedText;
    }

    public void instantiateLocations(){
        locations[0] = new Location("ONE", privateKey);
        locations[1] = new Location("TWO", privateKey);
        locations[2] = new Location("THREE", privateKey);
        locations[3] = new Location("FOUR", privateKey);
        locations[4] = new Location("FIVE", privateKey);
        locations[5] = new Location("SIX", privateKey);
        locations[6] = new Location("SEVEN", privateKey);
        locations[7] = new Location("EIGHT", privateKey);
        locations[8] = new Location("NINE", privateKey);
        locations[9] = new Location("TEN", privateKey);
        locations[10] = new Location("ELEVEN", privateKey);
        locations[11] = new Location("TWELVE", privateKey);
        locations[12] = new Location("THIRTEEN", privateKey);
        locations[13] = new Location("FOURTEEN", privateKey);
        locations[14] = new Location("FIFTEEN", privateKey);
        locations[15] = new Location("SIXTEEN", privateKey);
        locations[16] = new Location("SEVENTEEN", privateKey);
        locations[17] = new Location("EIGHTEEN", privateKey);
        locations[18] = new Location("NINETEEN", privateKey);
        locations[19] = new Location("TWENTY", privateKey);
        Arrays.stream(locations).forEach(location -> location.setEventBus(eventBus));
    }


    public void addSubscriber(Subscriber subscriber) {
        eventBus.register(subscriber);
    }

    public void sendBroadcastMessage(BigInteger[] message) {
        eventBus.post(new EventSendBroadcast(message));
    }

    public void sendPublicKey(RSAKey publicKey) {
        eventBus.post(new EventSendPublicKey(publicKey));
    }
/*
    public void sendDrugs() {
        eventBus.post(new EventSendDrugs());
    }

 */
}
