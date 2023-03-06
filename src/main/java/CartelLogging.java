import java.time.LocalDateTime;
import java.util.ArrayList;

public class CartelLogging {
    ArrayList<LocalDateTime> localDateTimes = new ArrayList<>();
    ArrayList<String> locations = new ArrayList<>();
    ArrayList<String> ciphers = new ArrayList<>();
    ArrayList<String> plainTexts = new ArrayList<>();

    public void addCartelLog(String location, String cipher, String plainText) {
        localDateTimes.add(LocalDateTime.now());
        locations.add(location);
        ciphers.add(cipher);
        plainTexts.add(plainText);
    }

    public void outputCartelLog() {
        System.out.println("All Cartel Logs of LocationXY/Base will be presented:");
        for (int i = 0; i < localDateTimes.size(); i++) {
            System.out.println("Local Date Time:" + localDateTimes.get(i).toString() + " | Location: " +
                    locations.get(i) + " | Cipher: " + ciphers.get(i) + " | Plain Text: " + plainTexts.get(i));
        }
    }

}
