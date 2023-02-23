import java.util.HashMap;
import java.util.Map;

public class IntegerStrings {
    private static Map<Integer, String> integerStringMap = initializeIntegerStringMap();

    private static Map<String, Integer> stringIntegerMap = initializeStringIntegerMap();


    private static Map<Integer, String> initializeIntegerStringMap() {
        Map<Integer, String> tempStringIntegerMap = new HashMap<>();

        tempStringIntegerMap.put(1,"ONE");
        tempStringIntegerMap.put(2,"TWO");
        tempStringIntegerMap.put(3,"THREE");
        tempStringIntegerMap.put(4,"FOUR");
        tempStringIntegerMap.put(5,"FIVE");
        tempStringIntegerMap.put(6,"SIX");
        tempStringIntegerMap.put(7,"SEVEN");
        tempStringIntegerMap.put(8,"EIGHT");
        tempStringIntegerMap.put(9,"NINE");
        tempStringIntegerMap.put(10,"TEN");
        tempStringIntegerMap.put(11,"ELEVEN");
        tempStringIntegerMap.put(12,"TWELVE");
        tempStringIntegerMap.put(13,"THIRTEEN");
        tempStringIntegerMap.put(14,"FOURTEEN");
        tempStringIntegerMap.put(15,"FIFTEEN");
        tempStringIntegerMap.put(16,"SIXTEEN");
        tempStringIntegerMap.put(17,"SEVENTEEN");
        tempStringIntegerMap.put(18,"EIGHTEEN");
        tempStringIntegerMap.put(19,"NINETEEN");
        tempStringIntegerMap.put(20,"TWENTY");

        return tempStringIntegerMap;
    }

    private static Map<String, Integer> initializeStringIntegerMap() {
        Map<String, Integer> tempStringIntegerMap = new HashMap<>();

        tempStringIntegerMap.put("ONE",1);
        tempStringIntegerMap.put("TWO",2);
        tempStringIntegerMap.put("THREE",3);
        tempStringIntegerMap.put("FOUR",4);
        tempStringIntegerMap.put("FIVE",5);
        tempStringIntegerMap.put("SIX",6);
        tempStringIntegerMap.put("SEVEN",7);
        tempStringIntegerMap.put("EIGHT",8);
        tempStringIntegerMap.put("NINE",9);
        tempStringIntegerMap.put("TEN",10);
        tempStringIntegerMap.put("ELEVEN",11);
        tempStringIntegerMap.put("TWELVE",12);
        tempStringIntegerMap.put("THIRTEEN",13);
        tempStringIntegerMap.put("FOURTEEN",14);
        tempStringIntegerMap.put("FIFTEEN",15);
        tempStringIntegerMap.put("SIXTEEN",16);
        tempStringIntegerMap.put("SEVENTEEN",17);
        tempStringIntegerMap.put("EIGHTEEN",18);
        tempStringIntegerMap.put("NINETEEN",19);
        tempStringIntegerMap.put("TWENTY",20);

        return tempStringIntegerMap;
    }


    public static Map<Integer, String> getIntegerStringMap() {
        return integerStringMap;
    }

    public static Map<String, Integer> getStringIntegerMap() {
        return stringIntegerMap;
    }
}
