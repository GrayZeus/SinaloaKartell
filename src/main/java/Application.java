import com.google.common.eventbus.EventBus;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Map;

public class Application {
    public static void main(String... args){
        Network network = new Network();

        Location[] locations = network.getBase().getLocations();

        locations[0].doOrder();

        Map<Long,String> protocolMap = network.getMsa().getProtocolOfCryptoAnalytics().getProtocolMap();

        for (Map.Entry<Long, String> entry:protocolMap.entrySet()
             ) {
            System.out.println("Key: " + entry.getKey() + "; Value: " + entry.getValue());
        }
    }


    public static void testCrackRSA(){
        try {
            MSA msa = new MSA();

            String message = "Test123Drogen";
            System.out.println(message);

            RSA rsa = new RSA();
            RSAKey[] rsaKeys = rsa.generateKeys(50);
            RSAKey publicKey = rsaKeys[0];
            RSAKey privateKey = rsaKeys[1];

            BigInteger[] cipher = rsa.encrypt(message, publicKey);
            System.out.println(Arrays.toString(cipher));

            msa.setPublicKey(publicKey);

            String resultString = msa.crack(cipher);

            System.out.println("MSA: EventSendBroadcast: Result String: " + resultString);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
