import com.google.common.eventbus.Subscribe;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;

public class MSA {

    RSAKey publicKey;

    @Subscribe
    public void receive(EventSendPublicKey eventSendPublicKey) {
        publicKey = eventSendPublicKey.getRsaKey();
    }

    @Subscribe
    public void receive(EventSendBroadcast eventSendBroadcast) throws RSACrackerException {
        String resultString = crack(eventSendBroadcast.getMessage());

        System.out.println("MSA: EventSendBroadcast: Result String: " + resultString);
    }

    private String crack(BigInteger[] cipher) throws RSACrackerException {
        // analyze
        RSACracker rsaCracker = new RSACracker(publicKey.part02(),publicKey.n());

        String resultString = "";

        try {
            BigInteger resultPartBigInteger;

            for (BigInteger messagePart:cipher
            ) {
                resultPartBigInteger = rsaCracker.execute(messagePart);
                resultString += bigIntegerToString(resultPartBigInteger);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return resultString;
    }



    private BigInteger byteArrayToBigInteger(byte[] bytes) {
        return new BigInteger(bytes);
    }

    private String bigIntegerToString(BigInteger bigInteger) {
        byte[] bigIntegerBytes =  bigInteger.toByteArray();
        return new String(bigIntegerBytes,Charset.defaultCharset());
    }

    public void setPublicKey(RSAKey publicKey) {
        this.publicKey = publicKey;
    }

    public static void main(String[] args) throws RSACrackerException {

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


}
