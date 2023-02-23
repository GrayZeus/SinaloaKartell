import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String... args){

        String fullString = "Bohnenhandel";
        String[] outputStrings;

        String partString = "";

        System.out.println(partString);

        outputStrings = new String[fullString.length() * 2];

        for (int currentCharacterIndex = 0; currentCharacterIndex < fullString.length(); currentCharacterIndex++) {
            partString = fullString.substring(0,currentCharacterIndex);

            outputStrings[currentCharacterIndex] = partString;
            outputStrings[outputStrings.length - currentCharacterIndex - 1] = partString;
        }

        Arrays.stream(outputStrings).forEach(element -> System.out.println(element));

    }
}
