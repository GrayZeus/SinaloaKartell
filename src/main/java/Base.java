import com.google.common.eventbus.EventBus;
import jdk.jfr.Event;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;


public class Base {
    private static EventBus eventBus = null;
    private static Location[] locations = new Location[20];
    private static RSAKey privateKey;


    public static void main(String... args){
        MSA msa = new MSA();
        Base base = new Base();

        //Marius Code - Snippet
        String message = "Test123Drogen";
        System.out.println(message);

        RSA rsa = new RSA();
        RSAKey[] rsaKeys = rsa.generateKeys(128);
        RSAKey publicKey = rsaKeys[0];
        privateKey = rsaKeys[1];

        BigInteger[] cipher = rsa.encrypt(message, publicKey);
        System.out.println(Arrays.toString(cipher));

        String encryptedText = rsa.decrypt(cipher, privateKey);
        System.out.println(encryptedText);
        //Ende Code Marius

    }

    public Base() {
            eventBus = new EventBus();
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

            Arrays.stream(locations).forEach(this::addSubscriber);
    }//end constructor

    public void addSubscriber(Subscriber subscriber) {
        eventBus.register(subscriber);
    }

    public void sendBroadcastMessage(BigInteger[] message) {
        eventBus.post(new EventSendBroadcast(message));
    }

    public void sendPublicKey(RSAKey publicKey) {
        eventBus.post(new EventSendPublicKey(publicKey));
    }

    public void sendDrugs() {
        eventBus.post(new EventSendDrugs());
    }
}
