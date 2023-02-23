import com.google.common.eventbus.Subscribe;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;

public class MSA extends Subscriber {

    RSAKey publicKey;
    Location[] locations;

    @Subscribe
    public void receive(EventSendPublicKey eventSendPublicKey) {
        publicKey = eventSendPublicKey.getRsaKey();
    }

    @Subscribe
    public void receive(EventSendBroadcast eventSendBroadcast) throws RSACrackerException {
        String resultString = crack(eventSendBroadcast.getMessage());

        System.out.println("MSA: EventSendBroadcast: Result String: " + resultString);

        String destinationId = analyseDecryptedText(resultString);

        confiscateDrugs(destinationId);
    }

    private void confiscateDrugs(String destinationId) {
        if (destinationId == null) {
            return;
        }

        Map<String,Integer> stringIntegerMap =  IntegerStrings.getStringIntegerMap();
        int destinationIndex = stringIntegerMap.get(destinationId) - 1;
        Location destination = locations[destinationIndex];

        destination.setDrugs(null);
    }

    public String analyseDecryptedText(String encryptedText){
        String[] substrings = encryptedText.split("X");

        System.out.println(Arrays.toString(substrings));

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

        Map<String, Integer> stringIntegerMap = IntegerStrings.getStringIntegerMap();

        String locationID = substrings[1];

        stringIntegerMap.get(locationID);

        if (stringIntegerMap.get(locationID) == null) {
            return null;
        }

        return locationID;
    }

    public String crack(BigInteger[] cipher) throws RSACrackerException {
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

    public void setLocations(Location[] locations) {
        this.locations = locations;
    }
}
