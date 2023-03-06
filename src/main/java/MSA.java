import com.google.common.eventbus.Subscribe;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;

public class MSA extends Subscriber {

    private RSAKey publicKey;
    private Location[] locations;
    private final Protocol<Long, String> protocolOfCryptoAnalytics;

    public MSA() {
        protocolOfCryptoAnalytics = new Protocol<>();
    }

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

        Map<String, Integer> stringIntegerMap = IntegerStrings.getStringIntegerMap();
        int destinationIndex = stringIntegerMap.get(destinationId) - 1;
        Location destination = locations[destinationIndex];

        destination.setDrugs(null);

        protocol("Drugs confiscated");
    }

    public String analyseDecryptedText(String encryptedText) {
        String[] substrings = encryptedText.split("X");

        System.out.println(Arrays.toString(substrings));

        if (substrings.length != 6) {
            return null;
        }
        if (!(substrings[0].equals("DRUGS"))) {
            return null;
        }
        if (!(substrings[1].equals("ONEHUNDRED"))) {
            return null;
        }
        if (!(substrings[2].equals("SEND"))) {
            return null;
        }
        if (!(substrings[3].equals("TO"))) {
            return null;
        }
        if (!(substrings[4].equals("LOCATION"))) {
            return null;
        }

        Map<String, Integer> stringIntegerMap = IntegerStrings.getStringIntegerMap();

        String locationID = substrings[5];

        stringIntegerMap.get(locationID);

        if (stringIntegerMap.get(locationID) == null) {
            return null;
        }

        protocol("Identified location " + locationID);
        return locationID;
    }

    public String crack(BigInteger[] cipher) throws RSACrackerException {
        RSACracker rsaCracker = new RSACracker(publicKey.part02(), publicKey.n());

        String resultString = "";

        try {
            BigInteger resultPartBigInteger;

            for (BigInteger messagePart : cipher
            ) {
                resultPartBigInteger = rsaCracker.execute(messagePart);
                resultString += bigIntegerToString(resultPartBigInteger);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        protocol("Cracked message: " + resultString);
        return resultString;
    }

    private void protocol(String description) {
        protocolOfCryptoAnalytics.addToProtocolMap(System.nanoTime(), description);
    }


    private BigInteger byteArrayToBigInteger(byte[] bytes) {
        return new BigInteger(bytes);
    }

    private String bigIntegerToString(BigInteger bigInteger) {
        byte[] bigIntegerBytes = bigInteger.toByteArray();
        return new String(bigIntegerBytes, Charset.defaultCharset());
    }

    public void setPublicKey(RSAKey publicKey) {
        this.publicKey = publicKey;
    }

    public void setLocations(Location[] locations) {
        this.locations = locations;
    }

    public Protocol<Long, String> getProtocolOfCryptoAnalytics() {
        return protocolOfCryptoAnalytics;
    }
}
