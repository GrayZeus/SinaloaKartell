import com.google.common.eventbus.EventBus;
import jdk.jfr.Event;
import java.util.ArrayList;
import java.util.Arrays;


public class Base {
    private static Drugs drugs;
    private static EventBus eventBus = null;
    private static Location[] locations = new Location[20];
    private static RSAKey privateKey;
    private static RSAKey publicKey;

    private static RSA rsa;


    public static void main(String... args){
        MSA msa = new MSA();
        Base base = new Base();
        base.sendPublicKey(publicKey);
        String message = "TESTTTT";
        //Encryption
        byte[] cipher = rsa.encrypt(message, publicKey);
        System.out.println(Arrays.toString(cipher));

        base.sendBroadcastMessage(cipher);
        //Ende Code Marius

    }//end main

    public Base() {
            eventBus = new EventBus();

            rsa = new RSA();
            RSAKey[] rsaKeys = rsa.generateKeys(128);
            publicKey = rsaKeys[0];
            privateKey = rsaKeys[1];
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

            //Adding subscribers
            Arrays.stream(locations).forEach(this::addSubscriber);
    }//end constructor

    public void addSubscriber(Subscriber subscriber) {
        eventBus.register(subscriber);
    }

    public void sendBroadcastMessage(byte[] message) {
        eventBus.post(new EventSendBroadcast(message));
    }

    public void sendPublicKey(RSAKey publicKey) {
        eventBus.post(new EventSendPublicKey(publicKey));
    }

    public void sendDrugs() {
        eventBus.post(new EventSendDrugs());
    }
}
