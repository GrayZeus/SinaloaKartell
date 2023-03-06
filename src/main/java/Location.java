import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.math.BigInteger;
import java.util.*;


public class Location extends Subscriber {
    private final RSAKey privateKey;
    private RSAKey publicKey;
    private final String locationID;
    private final String deliveryRequired;
    private final EventBus eventBus;
    private BigInteger[] cipher;
    private final RSA rsa;
    private List<Drugs> oneGramDrugs;

    private CartelLogging cartelLogging;

    public Location(String locationID, RSAKey privateKey, EventBus eventBus) {
        this.privateKey = privateKey;
        this.locationID = locationID;
        this.deliveryRequired = "LOCATIONX" + locationID + "XREQUESTXONEHUNDREDX";
        this.eventBus = eventBus;
        this.oneGramDrugs = new ArrayList<>();
        rsa = new RSA();
        cartelLogging = new CartelLogging();
    }//end constructor

    public void doOrder() {
        cipher = rsa.encrypt(deliveryRequired, publicKey);
        sendOrder(cipher);
    }

    private void sendOrder(BigInteger[] cipher) {
        eventBus.post(new EventPlaceOrder(cipher));
    }

    @Subscribe
    public void receive(EventSendBroadcast eventSendBroadcast) {
        System.out.println("Location " + locationID + " has received encrypted message via Broadcast channel.");
        System.out.println("Message will be decrypted and logged :) . ");
        BigInteger[] cipher = eventSendBroadcast.getMessage();

        String encryptedText = rsa.decrypt(cipher, privateKey);

        cartelLogging.addCartelLog(analyseDecryptedText(encryptedText,5) , convertCipherToString(cipher) , encryptedText);
        cartelLogging.outputCartelLog();

    }

    @Subscribe
    public void receive(EventSendPublicKey eventSendPublicKey) {
        System.out.println("Location: " + locationID + " has received Public RSA Key.");
        publicKey = eventSendPublicKey.getRsaKey();
        System.out.println(publicKey);
        System.out.println();
    }

    @Subscribe
    public void receive(EventPlaceOrder eventPlaceOrder) {
        System.out.println("Location " + locationID + " has received an order from an another Location!");
        System.out.println("Message will be decrypted and logged :) . ");
        BigInteger[] receivedOrderCipher = eventPlaceOrder.getCipher();

        String decryptedOrderText = rsa.decrypt(receivedOrderCipher, privateKey);


        cartelLogging.addCartelLog( analyseDecryptedText(decryptedOrderText, 1) , convertCipherToString(receivedOrderCipher) , decryptedOrderText);
        cartelLogging.outputCartelLog();
    }

    public String convertCipherToString(BigInteger[] cipher){
        String cipherString = "";
        for(int i = 0 ; i < cipher.length ; i++){
            cipherString = cipherString + cipher[i].toString();
        }
        return cipherString;
    }


    public String analyseDecryptedText(String encryptedText, int positioning1or5) {
        String[] substrings = encryptedText.split("X");
        Map<String, Integer> stringIntegerMap = IntegerStrings.getStringIntegerMap();
        String locationID = substrings[positioning1or5];
        stringIntegerMap.get(locationID);
        return locationID;
    }


    public void addDrugs(Collection<Drugs> drugs) {
        oneGramDrugs.addAll(drugs);
    }

    public void setDrugs(List<Drugs> drugs) {
        this.oneGramDrugs = drugs;
    }
}//end class
