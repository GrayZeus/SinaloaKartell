import com.google.common.eventbus.Subscribe;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;

public class MSA {

    RSAKey publicKey;
    RSACracker rsaCracker;


    @Subscribe
    public void receive(EventSendPublicKey eventSendPublicKey) {
        publicKey = eventSendPublicKey.getRsaKey();
    }


    @Subscribe
    public void receive(EventSendBroadcast eventSendBroadcast) {
        // analyze

        try {
            rsaCracker = new RSACracker(publicKey.part02(),publicKey.n(), byteArrayToBigInteger(eventSendBroadcast.getMessage()));
            BigInteger resultBinInteger = rsaCracker.execute();
            String resultString = bigIntegerToString(resultBinInteger);
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    private BigInteger byteArrayToBigInteger(byte[] bytes) {
        return new BigInteger(bytes);
    }

    private String bigIntegerToString(BigInteger bigInteger) {
        byte[] bigIntegerBytes =  bigInteger.toByteArray();
        return new String(bigIntegerBytes,Charset.defaultCharset());
    }


    public static void main(String[] args) {
        String message = "Test123Drogen";
        System.out.println(message);

        RSA rsa = new RSA();
        RSAKey[] rsaKeys = rsa.generateKeys(128);
        RSAKey publicKey = rsaKeys[0];
        RSAKey privateKey = rsaKeys[1];

        byte[] cipher = rsa.encrypt(message, publicKey);
        System.out.println(Arrays.toString(cipher));

        try {
            BigInteger result = new RSACracker(publicKey.part02(),publicKey.n(),new BigInteger(cipher)).execute();

            System.out.println(result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


}
