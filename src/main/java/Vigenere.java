import java.util.Arrays;

public class Vigenere  {
    private Engine engine;
    public void crack(String cipher, int keyspace) {
        String key;
        String clear;
        int[] keyAscii = new int[keyspace];
        Arrays.fill(keyAscii, 65);
        double n = (Math.pow(26.0, keyspace));

        StringBuilder stringBuilder = new StringBuilder();

        for (int j = 0; j < keyspace; j++) {
            stringBuilder.append((char) keyAscii[j]);
        }

        key = stringBuilder.toString();

        for (int i = 0; i < n; i++) {
            clear = engine.encrypt(key, cipher);
            System.out.println(key + ": " + clear);
            key = nextKey(key);
        }
    }
    private String nextKey(String key) {
        int keyspace = key.length();
        StringBuilder stringBuilder = new StringBuilder(key);

        if ((int) key.charAt(keyspace - 1) == 90) {
            for (int i = 1; i < keyspace; i++) {
                if ((int) key.charAt(keyspace - i) == 90) {
                    stringBuilder.setCharAt(keyspace - i, 'A');
                    int current = stringBuilder.charAt(keyspace - (i + 1));
                    char next = (char) (current + 1);
                    stringBuilder.setCharAt(keyspace - (i + 1), next);
                }
            }
        } else {
            int current = stringBuilder.charAt(keyspace - 1);
            char next = (char) (current + 1);
            stringBuilder.setCharAt(keyspace - 1, next);
        }
        key = stringBuilder.toString();
        return key;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
