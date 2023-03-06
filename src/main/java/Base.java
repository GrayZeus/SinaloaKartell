import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class Base extends Subscriber {
    private EventBus eventBus;
    private Location[] locations;
    private final RSAKey privateKey;
    private final RSAKey publicKey;
    private final RSA rsa;
    private final List<Drugs> oneGramDrugs;

    public Base() {
        rsa = new RSA();
        eventBus = new EventBus();
        RSAKey[] keys = rsa.generateKeys(50);
        publicKey = keys[0];
        privateKey = keys[1];
        oneGramDrugs = new ArrayList<>();
        for (int i = 0; i < (15 * 1000 * 1000); i++) {
            oneGramDrugs.add(new Drugs());
        }

    }//end constructor


    public void instantiateLocationsAndAddThemAsSubscribers() {
        instantiateLocations();
        Arrays.stream(locations).forEach(this::addSubscriber);
    }


    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Subscribe
    public void receive(EventPlaceOrder eventPlaceOrder) {
        System.out.println("Base has received order message from a Location.");
        BigInteger[] cipher = eventPlaceOrder.getCipher();
        String encryptedText = rsa.decrypt(cipher, privateKey);
        System.out.println("Encrypted Message is: ");
        System.out.println(encryptedText);

        String destination = analyseDecryptedText(encryptedText);

        System.out.println("Drug amount: " + oneGramDrugs.size());

        if (oneGramDrugs.size() >= 100) {
            sendDrugs(destination);
        }

    }

    @Subscribe
    private void receive(EventSendDrugs eventSendDrugs) {
        System.out.println("Base received instruction to send Drugs. Drugs will be sent.");

        String destination = eventSendDrugs.getDestination();
        Map<String, Integer> stringIntegerMap = IntegerStrings.getStringIntegerMap();
        int destinationIndex = stringIntegerMap.get(destination) - 1;

        List<Drugs> drugsToSend = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Drugs currentDrugs = oneGramDrugs.get(i);
            drugsToSend.add(currentDrugs);
            oneGramDrugs.remove(currentDrugs);
        }
        locations[destinationIndex].addDrugs(drugsToSend);
        BigInteger[] encryptedMessage = rsa.encrypt("DRUGSXONEHUNDREDXSENDXTOXLOCATIONX" + destination, publicKey);
        sendBroadcastMessage(encryptedMessage);
    }

    public String analyseDecryptedText(String encryptedText) {
        String[] substrings = encryptedText.split("X");
        System.out.println(Arrays.toString(substrings));
        /*
        if (substrings.length != 4) {
            return null;
        }
        if (!(substrings[0].equals("LOCATION"))) {
            return null;
        }
        if (!(substrings[2].equals("REQUEST"))) {
            return null;
        }
        if (!(substrings[3].equals("ONEHUNDRED"))) {
            return null;
        }
         */
        Map<String, Integer> stringIntegerMap = IntegerStrings.getStringIntegerMap();
        String locationID = substrings[1];
        stringIntegerMap.get(locationID);
        if (stringIntegerMap.get(locationID) == null) {
            return null;
        }
        return locationID;
    }

    public void instantiateLocations() {
        locations = new Location[20];

        Map<Integer, String> integerStringMap = IntegerStrings.getIntegerStringMap();

        for (int currentLocation = 1; currentLocation <= locations.length; currentLocation++) {
            locations[currentLocation - 1] = new Location(integerStringMap.get(currentLocation), privateKey, eventBus);
        }
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

    public void sendDrugs(String destination) {
        eventBus.post(new EventSendDrugs(destination));
    }

    public Location[] getLocations() {
        return locations;
    }

    public RSAKey getPublicKey() {
        return publicKey;
    }
}
